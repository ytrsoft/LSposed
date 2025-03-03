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

    public static Object call(Object instance, String name, Object[] args) {
        return XposedHelpers.callMethod(instance, name, args);
    }

    public static Object call(Class<?> clz, String name, Object[] args) {
        return XposedHelpers.callStaticMethod(clz, name, args);
    }

}