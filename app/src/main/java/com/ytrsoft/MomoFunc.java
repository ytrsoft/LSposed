package com.ytrsoft;

import android.os.Bundle;

public class MomoAdapter {

    private final AppContext context;

    private static final String PKG_IMJ = "com.immomo.momo.im.e$1";

    public MomoAdapter(AppContext context) {
        this.context = context;
    }

    public Overload getIMJ() {
        Class<?>[] pts = new Class[] {
            String.class,
            Bundle.class,
            Object.class
        };
        Overload overload = new Overload();
        Class<?> t = context.getClass(PKG_IMJ);
        overload.setParamTypes(pts);
        overload.setTarget(t);
        overload.setName("a");
        overload.setPath(PKG_IMJ + "." + overload.getName());
        return overload;
    }

}
