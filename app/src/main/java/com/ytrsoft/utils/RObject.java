package com.ytrsoft.utils;

import cn.hutool.core.util.ReflectUtil;

public class RObject {

    private final Object target;

    public RObject(Object target) {
        this.target = target;
    }

    public String getString(String key) {
        return (String) ReflectUtil.getFieldValue(target, key);
    }

    public RObject getObject(String key) {
        return new RObject(ReflectUtil.getFieldValue(target, key));
    }

    public RObject invoke(String name, Object... args) {
        return new RObject(ReflectUtil.invoke(target, name, args));
    }

}
