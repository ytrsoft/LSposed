package com.ytrsoft.base;

import com.ytrsoft.handler.DumpType;

public class Overload {

    private DumpType type;

    private String name;

    private Class<?> target;

    private Object[] types;

    public DumpType getType() {
        return type;
    }

    public void setType(DumpType type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Class<?> getTarget() {
        return target;
    }

    public void setTarget(Class<?> target) {
        this.target = target;
    }

    public Object[] getTypes() {
        return types;
    }

    public void setTypes(Object[] types) {
        this.types = types;
    }
}
