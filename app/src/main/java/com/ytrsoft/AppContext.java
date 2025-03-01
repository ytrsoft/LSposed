package com.ytrsoft;

import de.robv.android.xposed.XposedHelpers;
import de.robv.android.xposed.callbacks.XC_LoadPackage;

public class AppContext {

    private final XC_LoadPackage.LoadPackageParam params;

    public AppContext(XC_LoadPackage.LoadPackageParam params) {
        this.params = params;
    }

    public Class<?> getClass(String pkg) {
        return XposedHelpers.findClass(pkg, params.classLoader);
    }

}
