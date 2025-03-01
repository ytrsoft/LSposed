package com.ytrsoft;

import de.robv.android.xposed.IXposedHookLoadPackage;
import de.robv.android.xposed.callbacks.XC_LoadPackage;

public abstract class AbstractHook implements IXposedHookLoadPackage, ClassMethod.Caller {

    @Override
    public void handleLoadPackage(XC_LoadPackage.LoadPackageParam params) {
        String s1 = registerPackage();
        String s2 = params.packageName;
        if (!s1.equals(s2)) {
            AppContext ctx = new AppContext(params);
            onInit(ctx);
        }
    }

    protected void registerMethod(Overload overload) {
        ClassMethod cm = new ClassMethod(overload);
        cm.setCaller(this);
    }

    protected abstract String registerPackage();

    protected void onInit(AppContext context) {}

    @Override
    public void onMethodHook(ClassMethod.Dump dump) {}

    @Override
    public void onMethodLeave(ClassMethod.Dump dump) {}

}
