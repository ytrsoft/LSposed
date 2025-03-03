package com.ytrsoft.utils;

import de.robv.android.xposed.XposedHelpers;

public final class Xposed {

    private Xposed() {
        throw new UnsupportedOperationException();
    }

    public static void invoke(Class<?> clz, Object[] args) {
        XposedHelpers.findAndHookConstructor(clz, args);
    }

    public static void invoke(Class<?> clz, String name, Object[] args) {
        XposedHelpers.findAndHookMethod(clz, name, args);
    }

    public static Object call(Object inst, String name, Object[] args) {
        return XposedHelpers.callMethod(inst, name, args);
    }

    public static Object call(Class<?> clz, String name, Object[] args) {
        return XposedHelpers.callStaticMethod(clz, name, args);
    }

    public static Class<?> find(String pkg, ClassLoader classLoader) {
        return XposedHelpers.findClass(pkg, classLoader);
    }

    public static Object get(Object inst, String key) {
        return XposedHelpers.getObjectField(inst, key);
    }

}