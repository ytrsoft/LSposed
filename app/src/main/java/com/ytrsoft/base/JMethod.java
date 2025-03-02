package com.ytrsoft.base;

import com.ytrsoft.utils.Lang;
import com.ytrsoft.utils.Xposed;

import de.robv.android.xposed.XC_MethodHook;

public class JMethod extends XC_MethodHook {

    private final Overload overload;
    private InvokeHandler handler;

    public JMethod(Overload overload) {
        this.overload = overload;
        initialized();
    }

    public void setHandler(InvokeHandler handler) {
        this.handler = handler;
    }

    private void initialized() {
        Object[] merge = Lang.merge(overload.getTypes(), this);
        Xposed.invoke(overload.getTarget(), overload.getName(), merge);
    }

    @Override
    protected void afterHookedMethod(MethodHookParam param) throws Throwable {
        super.afterHookedMethod(param);
        if (handler != null) {
            Dump dump = new Dump();
            dump.setArgs(param.args);
            dump.setTag(overload.getName());
            handler.activated(dump);
        }
    }

    @Override
    protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
        super.beforeHookedMethod(param);
        if (handler != null) {
            Dump dump = new Dump();
            dump.setArgs(param.args);
            dump.setTag(overload.getName());
            handler.deactivated(dump);
        }
    }
}
