package com.ytrsoft.core;

import android.app.Application;
import android.content.Context;

import com.ytrsoft.annotation.Path;
import com.ytrsoft.annotation.Inject;
import com.ytrsoft.annotation.Overload;
import com.ytrsoft.hook.IHook;
import com.ytrsoft.utils.Lang;
import com.ytrsoft.utils.OAnnotation;
import com.ytrsoft.utils.Xposed;

import de.robv.android.xposed.IXposedHookLoadPackage;
import de.robv.android.xposed.IXposedHookZygoteInit;
import de.robv.android.xposed.callbacks.XC_LoadPackage;

public abstract class HookApplication implements IXposedHookZygoteInit, IXposedHookLoadPackage, ContextListener {

    private AppContext mAppContext;
    private String mPackageName;

    public HookApplication() {
        OAnnotation oa = new OAnnotation(this, Inject.class);
        if (oa.isPresent()) {
            mPackageName = ((Inject) oa.get()).value();
        }
    }

    private void initContext() {
        Class<?>[] types = new Class[] {
            Context.class
        };
        XContext xContext = new XContext();
        Object[] args = Lang.merge(types, xContext);
        xContext.setOnContextListener(this);
        Xposed.invoke(Application.class, "attach", args);
    }

    @Override
    public void handleLoadPackage(XC_LoadPackage.LoadPackageParam param) {
        if (mPackageName.equals(param.packageName)) {
            mAppContext.setPackageParam(new PackageParam(param));
            initContext();
        }
    }

    @Override
    public void initZygote(StartupParam param) {
        mAppContext = new AppContext();
        mAppContext.setSetupParam(new SetupParam(param));
        handleCreated(mAppContext);
    }

    protected void handleCreated(AppContext context) {}

    protected void handleMounted(AppContext context) {}

    protected void registerHook(IHook hook) {
        OAnnotation oa = new OAnnotation(hook, Path.class);
        if (oa.isPresent()) {
            String[] xPath = Lang.xPath(((Path) oa.get()).value());
            if (xPath.length == 2) {
                handleXPath(xPath[0], xPath[1], hook);
            }
        }
    }

    private void handleXPath(String pkg, String name, IHook hook) {
        boolean isInit = name.equals("$init");
        OAnnotation oa = new OAnnotation(hook, Overload.class);
        if (oa.isPresent()) {
            Class<?>[] types = ((Overload) oa.get()).types();
            handleTypes(pkg, name, types, hook, isInit);
        } else {
            handleTypes(pkg, name, new Object[] {}, hook, isInit);
        }
    }

    private void handleTypes(String pkg, String name, Object[] types, IHook hook, boolean isInit) {
        Class<?> clz = mAppContext.loadClass(pkg);
        XMethod xMethod = new XMethod(hook);
        Object[] args = Lang.merge(types, xMethod);
        if (isInit) {
            Xposed.invoke(clz, args);
        } else {
            Xposed.invoke(clz, name, args);
        }
    }

    @Override
    public void attach(Context context) {
        mAppContext.setContext(context);
        handleMounted(mAppContext);
    }
}
