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

    public static Class<?> findClass(String pkg, ClassLoader cl) {
        return XposedHelpers.findClass(pkg, cl);
    }

}