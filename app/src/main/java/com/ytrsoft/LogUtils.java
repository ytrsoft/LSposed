package com.ytrsoft;

import de.robv.android.xposed.XposedBridge;

public final class LogUtils {

    private LogUtils() {
        throw new UnsupportedOperationException();
    }

    public static void print(String msg) {
        XposedBridge.log("[LSposed] - " + msg);
    }

}
