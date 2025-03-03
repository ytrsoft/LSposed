package com.ytrsoft.core;

import de.robv.android.xposed.IXposedHookZygoteInit;

public class SetupParam {

    public String modulePath;

    public boolean startsSystemServer;

    public SetupParam() {}

    public SetupParam(IXposedHookZygoteInit.StartupParam startupParam) {
        modulePath = startupParam.modulePath;
        startsSystemServer = startupParam.startsSystemServer;
    }

    public String getModulePath() {
        return modulePath;
    }

    public void setModulePath(String modulePath) {
        this.modulePath = modulePath;
    }

    public boolean isStartsSystemServer() {
        return startsSystemServer;
    }

    public void setStartsSystemServer(boolean startsSystemServer) {
        this.startsSystemServer = startsSystemServer;
    }
}
