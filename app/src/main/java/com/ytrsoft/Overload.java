package com.ytrsoft;

public class Overload {

    private Class<?> target;

    private Object[] paramTypes;

    private String name;

    private String tag;

    public Class<?> getTarget() {
        return target;
    }

    public void setTarget(Class<?> target) {
        this.target = target;
    }

    public Object[] getParamTypes() {
        return paramTypes;
    }

    public void setParamTypes(Object[] paramTypes) {
        this.paramTypes = paramTypes;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }
}
