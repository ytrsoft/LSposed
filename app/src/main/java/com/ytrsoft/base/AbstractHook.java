package com.ytrsoft.base;

import android.app.Application;
import android.content.Context;

import com.ytrsoft.utils.Console;

import de.robv.android.xposed.IXposedHookLoadPackage;
import de.robv.android.xposed.IXposedHookZygoteInit;
import de.robv.android.xposed.callbacks.XC_LoadPackage;

public abstract class AbstractHook implements IXposedHookZygoteInit, IXposedHookLoadPackage, InvokeHandler {

    private AppContext mContext;

    private final String TAG_APP = "APP";

    public Overload getApplicationOverload() {
        Class<?>[] types = new Class[] {
            Context.class
        };
        Overload overload = new Overload();
        overload.setTypes(types);
        overload.setTarget(Application.class);
        overload.setName("attach");
        overload.setTag(TAG_APP);
        return overload;
    }

    @Override
    public void handleLoadPackage(XC_LoadPackage.LoadPackageParam params) {
        String s1 = getPackage();
        String s2 = params.packageName;
        if (s1.equals(s2)) {
            mContext = new AppContext(params);
            onCreated(mContext);
            Overload app = getApplicationOverload();
            registerMethod(app);
        }
    }

    protected void registerMethod(Overload overload) {
        JMethod method = new JMethod(overload);
        method.setHandler(this);
    }

    @Override
    public void initZygote(StartupParam params) {
        Console.log("初始化", getPackage());
    }

    protected abstract String getPackage();

    protected void onCreated(AppContext context) {}

    protected void onMounted(AppContext context) {}

    @Override
    public void activated(Dump dump) {
        if (dump.getTag().equals(TAG_APP)) {
            Context ctx = (Context) dump.getArgs()[0];
            ClassLoader loader = ctx.getClassLoader();
            mContext.setLoader(loader);
            onMounted(mContext);
        }
    }

    @Override
    public void deactivated(Dump dump) {}

}
