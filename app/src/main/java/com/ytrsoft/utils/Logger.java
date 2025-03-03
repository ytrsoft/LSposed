package com.ytrsoft.utils;

import android.util.Log;

import java.util.Objects;

import de.robv.android.xposed.XposedBridge;

public final class Logger {

    private Logger() {
        throw new UnsupportedOperationException();
    }

    public static void i(String text) {
        XposedBridge.log(text);
    }

    public static void i(String format, Object... args) {
        XposedBridge.log(String.format(format, args));
    }

    public static void e(Exception e) {
        XposedBridge.log(e);
    }

}
