package com.ytrsoft.core;

import java.lang.reflect.Member;

import de.robv.android.xposed.XC_MethodHook;

public class Param {

    public Member method;

    public Object thisObject;

    public Object[] args;

    private final XC_MethodHook.MethodHookParam param;

    public Param(XC_MethodHook.MethodHookParam param) {
       method = param.method;
       thisObject = param.thisObject;
       args = param.args;
       this.param = param;
    }

    public void setArg(int index, Object arg) {
        this.param.args[index] = arg;
    }

    public Member getMethod() {
        return method;
    }

    public void setMethod(Member method) {
        this.method = method;
    }

    public Object getThisObject() {
        return thisObject;
    }

    public void setThisObject(Object thisObject) {
        this.thisObject = thisObject;
    }

    public Object getResult() {
        return param.getResult();
    }

    public Throwable getThrowable() {
        return param.getThrowable();
    }

}
