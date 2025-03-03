package com.ytrsoft.core;

import android.content.Context;

import de.robv.android.xposed.XC_MethodHook;

public class XContext extends XC_MethodHook {

    private ContextListener listener;

    public void setOnContextListener(ContextListener listener) {
        this.listener = listener;
    }

    @Override
    protected void afterHookedMethod(MethodHookParam param) throws Throwable {
        super.afterHookedMethod(param);
        if (listener != null) {
            listener.attach((Context) param.args[0]);
        }
    }
}
