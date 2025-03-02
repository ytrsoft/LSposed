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

}
