package com.ytrsoft.utils;

public final class Lang {

    private Lang() {
        throw new UnsupportedOperationException();
    }

    public static Object[] merge(Object[] src, Object call) {
        int len = src.length;
        Object[] result = new Object[len + 1];
        System.arraycopy(src, 0, result, 0, src.length);
        result[len] = call;
        return result;
    }

}
