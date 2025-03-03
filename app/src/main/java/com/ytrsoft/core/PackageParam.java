package com.ytrsoft.core;

import android.content.pm.ApplicationInfo;

import de.robv.android.xposed.callbacks.XC_LoadPackage;

public class PackageParam {

    public String packageName;

    public String processName;

    public ClassLoader classLoader;

    public ApplicationInfo appInfo;

    public boolean isFirstApplication;

    public PackageParam() {}

    public PackageParam(XC_LoadPackage.LoadPackageParam param) {
        packageName = param.packageName;
        processName = param.processName;
        classLoader = param.classLoader;
        appInfo = param.appInfo;
        isFirstApplication = param.isFirstApplication;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public String getProcessName() {
        return processName;
    }

    public void setProcessName(String processName) {
        this.processName = processName;
    }

    public ClassLoader getClassLoader() {
        return classLoader;
    }

    public void setClassLoader(ClassLoader classLoader) {
        this.classLoader = classLoader;
    }

    public ApplicationInfo getAppInfo() {
        return appInfo;
    }

    public void setAppInfo(ApplicationInfo appInfo) {
        this.appInfo = appInfo;
    }

    public boolean isFirstApplication() {
        return isFirstApplication;
    }

    public void setFirstApplication(boolean firstApplication) {
        isFirstApplication = firstApplication;
    }
}
