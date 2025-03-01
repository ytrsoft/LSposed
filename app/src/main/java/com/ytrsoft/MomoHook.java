package com.ytrsoft;

public class MomoHook extends AbstractHook {

    @Override
    protected String registerPackage() {
        return "com.immomo.momo";
    }

    @Override
    protected void onInit(AppContext context) {
        super.onInit(context);
        MomoFunc fns = new MomoFunc(context);
        registerMethod(fns.getIMJ());
    }

    @Override
    public void onMethodHook(ClassMethod.Dump dump) {
        super.onMethodHook(dump);
        LogUtils.print(dump.getTag() + "@进入");
    }

    @Override
    public void onMethodLeave(ClassMethod.Dump dump) {
        super.onMethodLeave(dump);
        LogUtils.print(dump.getTag() + "@离开");
    }

}
