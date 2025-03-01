package com.ytrsoft;

import de.robv.android.xposed.IXposedHookLoadPackage;
import de.robv.android.xposed.callbacks.XC_LoadPackage;

public abstract class AbstractHook implements IXposedHookLoadPackage {

    @Override
    public void handleLoadPackage(XC_LoadPackage.LoadPackageParam params) {
        String s1 = registerPackage();
        String s2 = params.packageName;
        if (!s1.equals(s2)) {
            AppContext ctx = new AppContext(params);
            onStart(ctx);
        }
    }

    protected abstract void onStart(AppContext context);


    protected abstract String registerPackage();


}
