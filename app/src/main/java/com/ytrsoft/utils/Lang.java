package com.ytrsoft.utils;

import org.apache.commons.lang3.StringUtils;

public final class Lang {

    private Lang() {
        throw new UnsupportedOperationException();
    }

    public static String[] xPath(String path) {
        if (StringUtils.isEmpty(path)) {
            return new String[]{};
        }
        int pos = path.lastIndexOf('.');
        if (pos == -1) {
            return new String[]{};
        }
        String[] result = new String[2];
        result[0] = path.substring(0, pos);
        result[1] = path.substring(pos + 1);
        return result;
    }

    public static Object[] merge(Object[] src, Object call) {
        int len = src.length;
        Object[] result = new Object[len + 1];
        System.arraycopy(src, 0, result, 0, src.length);
        result[len] = call;
        return result;
    }

}
