package com.ytrsoft.base;

import com.ytrsoft.handler.DumpType;

import de.robv.android.xposed.XC_MethodHook;

public class Dump {

    private DumpType type;
    private Object[] args;
    private Object result;
    private XC_MethodHook.MethodHookParam param;

    public Dump() {}

    public Dump(XC_MethodHook.MethodHookParam param) {
        this.param = param;
    }

    public DumpType getType() {
        return type;
    }

    public void setType(DumpType type) {
        this.type = type;
    }

    public Object[] getArgs() {
        return args;
    }

    public void setArgs(Object[] args) {
        this.args = args;
    }

    public Object getResult() {
        return result;
    }

    public void setParam(int index, Object value) {
        this.param.args[index] = value;
    }

    public void setResult(Object result) {
        this.param.setResult(result);
        this.result = result;
    }
}
