package com.ytrsoft.base;

import com.ytrsoft.handler.DumpType;

public class Dump {

    private DumpType type;
    private Object[] args;
    private Object result;

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

    public void setResult(Object result) {
        this.result = result;
    }
}
