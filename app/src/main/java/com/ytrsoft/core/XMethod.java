package com.ytrsoft.core;

import com.ytrsoft.hook.IHook;

import de.robv.android.xposed.XC_MethodHook;

public class XMethod extends XC_MethodHook {

    private final IHook hook;

    public XMethod(IHook hook) {
        this.hook = hook;
    }

    @Override
    protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
        super.beforeHookedMethod(param);
        hook.enter(new com.ytrsoft.core.Param(param));
    }

    @Override
    protected void afterHookedMethod(MethodHookParam param) throws Throwable {
        super.afterHookedMethod(param);
        hook.leave(new com.ytrsoft.core.Param(param));
    }
}
