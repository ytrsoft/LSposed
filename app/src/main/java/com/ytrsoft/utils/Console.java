package com.ytrsoft.utils;

import de.robv.android.xposed.XposedBridge;

public final class Console {

    private Console() {
        throw new UnsupportedOperationException();
    }

    public static void log(String tag, String msg) {
        XposedBridge.log(String.format("[%s] %s", tag, msg));
    }

}
