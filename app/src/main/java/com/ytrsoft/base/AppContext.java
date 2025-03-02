package com.ytrsoft.base;

import com.ytrsoft.utils.Xposed;

import de.robv.android.xposed.callbacks.XC_LoadPackage;

public class AppContext {

    private ClassLoader loader;

    private final XC_LoadPackage.LoadPackageParam params;

    public void setLoader(ClassLoader loader) {
        this.loader = loader;
    }

    public AppContext(XC_LoadPackage.LoadPackageParam params) {
        this.params = params;
    }

    public Class<?> getClass(String pkg) {
        return Xposed.findClass(pkg, params.classLoader);
    }

    public Class<?> getLoaderClass(String pkg) {
        try {
            return loader.loadClass(pkg);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

}
