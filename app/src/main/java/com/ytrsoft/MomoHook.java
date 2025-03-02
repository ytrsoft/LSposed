package com.ytrsoft;

import android.os.Bundle;

public class MomoHook extends AbstractHook {

    private static final String TAG_REC = "REC";

    @Override
    protected String registerPackage() {
        return "com.immomo.momo";
    }

    @Override
    protected void onStart(ClassLoader loader) {
        super.onStart(loader);
        registerIMJMethod(loader);
    }

    private void registerIMJMethod(ClassLoader loader) {
        String path = "com.immomo.momo.im.e$1";
        Class<?>[] pts = new Class[] {
            String.class,
            Bundle.class,
            Object.class
        };
        Overload overload = new Overload();
        overload.setParamTypes(pts);
        overload.setName("a");
        overload.setTag(TAG_REC);
        ClassThread thread = new ClassThread(loader, path);
        thread.setFuture((clz) -> {
            overload.setTarget(clz);
            registerMethod(overload);
        });
        thread.start();
    }

    @Override
    public void onMethodHook(ClassMethod.Dump dump) {
        super.onMethodHook(dump);
        Console.log(dump.getTag() + "@进入");
    }

    @Override
    public void onMethodLeave(ClassMethod.Dump dump) {
        super.onMethodLeave(dump);
        Console.log(dump.getTag() + "@离开");
    }

}
