package com.ytrsoft.core;

import android.app.Application;
import android.content.Context;

import com.ytrsoft.annotation.Path;
import com.ytrsoft.annotation.Inject;
import com.ytrsoft.annotation.Match;
import com.ytrsoft.annotation.Use;
import com.ytrsoft.hook.IHook;
import com.ytrsoft.utils.Lang;
import com.ytrsoft.utils.Xposed;

import de.robv.android.xposed.IXposedHookLoadPackage;
import de.robv.android.xposed.IXposedHookZygoteInit;
import de.robv.android.xposed.callbacks.XC_LoadPackage;

public abstract class HookApplication implements IXposedHookZygoteInit, IXposedHookLoadPackage, ContextListener {

    private AppContext mAppContext;
    private String mPackageName;

    public HookApplication() {
        Class<? extends HookApplication> aClz = this.getClass();
        if (aClz.isAnnotationPresent(Inject.class)) {
            Inject inject = aClz.getAnnotation(Inject.class);
            if (inject != null) {
                mPackageName = inject.value();
            }
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
        Class<? extends IHook> hClz = hook.getClass();
        if (hClz.isAnnotationPresent(Path.class)) {
            Path path = hClz.getAnnotation(Path.class);
            if (path != null) {
                String[] xPath = Lang.xPath(path.value());
                if (xPath.length == 2) {
                    handleXPath(hClz, xPath[0], xPath[1], hook);
                }
            }
        }
    }

    private void handleXPath(Class<? extends IHook> hClz, String pkg, String name, IHook hook) {
        boolean isInit = name.equals("$init");
        if (hClz.isAnnotationPresent(Match.class)) {
            Match overload = hClz.getAnnotation(Match.class);
            if (overload != null) {
                Class<?>[] types = overload.types();
                handleTypes(pkg, name, types, hook, isInit);
            } else {

                handleTypes(pkg, name, new Class[] {}, hook, isInit);
            }
        }
    }

    private void handleTypes(String pkg, String name, Class<?>[] types, IHook hook, boolean isInit) {
        wrapTypes(types);
        Class<?> clz = mAppContext.loadClass(pkg);
        XMethod xMethod = new XMethod(hook);
        Object[] args = Lang.merge(types, xMethod);
        if (isInit) {
            Xposed.invoke(clz, args);
        } else {
            Xposed.invoke(clz, name, args);
        }
    }

    private void wrapTypes(Class<?>[] args) {
        for (int i = 0; i < args.length; i++) {
            if (args[i].isAnnotationPresent(Use.class)) {
                Use type = args[i].getAnnotation(Use.class);
                if (type != null) {
                    args[i] = Xposed.find(type.value(), mAppContext.getContextLoader());
                }
            }
        }
    }

    @Override
    public void attach(Context context) {
        mAppContext.setContext(context);
        handleMounted(mAppContext);
    }
}
