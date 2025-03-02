package com.ytrsoft;

import de.robv.android.xposed.XposedBridge;

public final class Console {

    private Console() {
        throw new UnsupportedOperationException();
    }

    public static void log(String msg) {
        XposedBridge.log(msg);
    }

}
