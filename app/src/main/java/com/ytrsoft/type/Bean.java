package com.ytrsoft.type;

import com.ytrsoft.utils.RObject;

import cn.hutool.core.util.ReflectUtil;

public abstract class Bean {

    private final Object target;

    public Bean(Object target) {
        this.target = target;
    }

    public String getString(String key) {
        return (String) ReflectUtil.getFieldValue(target, key);
    }

    public int getInt(String key) {
        return (int) ReflectUtil.getFieldValue(target, key);
    }

    public RObject getObject(String key) {
        return new RObject(ReflectUtil.getFieldValue(target, key));
    }

    public RObject invoke(String name, Object... args) {
        return new RObject(ReflectUtil.invoke(target, name, args));
    }

}
