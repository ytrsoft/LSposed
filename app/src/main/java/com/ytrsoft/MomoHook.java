package com.ytrsoft;

public class MomoHook extends AbstractHook {

    @Override
    protected String registerPackage() {
        return "com.immomo.momo";
    }

    @Override
    protected void onStart(AppContext context) {
        LogUtils.print("加载陌陌LSposed模块");
    }

}
