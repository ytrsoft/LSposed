package com.ytrsoft.core;

import android.content.Context;

public class AppContext {

    private Context context;
    private PackageParam packageParam;
    private SetupParam setupParam;

    public PackageParam getPackageParam() {
        return packageParam;
    }

    public void setPackageParam(PackageParam packageParam) {
        this.packageParam = packageParam;
    }

    public SetupParam getSetupParam() {
        return setupParam;
    }

    public void setSetupParam(SetupParam setupParam) {
        this.setupParam = setupParam;
    }

    public Context getContext() {
        return context;
    }

    public ClassLoader getContextClassLoader() {
        return context.getClassLoader();
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public Class<?> loadClass(String pkg) {
        ClassLoader loader = context.getClassLoader();
        try {
            return loader.loadClass(pkg);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
