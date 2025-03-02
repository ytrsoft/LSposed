package com.ytrsoft;

import android.app.Application;
import android.content.Context;

import de.robv.android.xposed.IXposedHookLoadPackage;
import de.robv.android.xposed.callbacks.XC_LoadPackage;

public abstract class AbstractHook implements IXposedHookLoadPackage, ClassMethod.Caller {

    private final String APP_TAG = "APP";

    public Overload getApplicationOverload() {
        Class<?>[] pts = new Class[] {
            Context.class,
        };
        Overload overload = new Overload();
        overload.setParamTypes(pts);
        overload.setTarget(Application.class);
        overload.setName("attach");
        overload.setTag(APP_TAG);
        return overload;
    }

    @Override
    public void handleLoadPackage(XC_LoadPackage.LoadPackageParam params) {
        String s1 = registerPackage();
        String s2 = params.packageName;
        if (!s1.equals(s2)) {
            AppContext ctx = new AppContext(params);
            onInit(ctx);
            Overload app = getApplicationOverload();
            registerMethod(app);
        }
    }

    protected void registerMethod(Overload overload) {
        ClassMethod cm = new ClassMethod(overload);
        cm.setCaller(this);
    }

    protected abstract String registerPackage();

    protected void onInit(AppContext context) {}

    protected void onStart(ClassLoader loader) {}

    @Override
    public void onMethodHook(ClassMethod.Dump dump) {}

    @Override
    public void onMethodLeave(ClassMethod.Dump dump) {
        if (dump.getTag().equals(APP_TAG)) {
            Context context = (Context) dump.getArgs()[0];
            ClassLoader loader = context.getClassLoader();
            onStart(loader);
        }
    }

}
