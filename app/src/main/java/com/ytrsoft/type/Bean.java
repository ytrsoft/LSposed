package com.ytrsoft.type;

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

}
