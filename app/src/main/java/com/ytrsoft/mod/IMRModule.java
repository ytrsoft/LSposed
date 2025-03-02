package com.ytrsoft.mod;

import android.os.Bundle;

import com.ytrsoft.base.Overload;
import com.ytrsoft.handler.DumpType;

public class IMRModule extends BaseModule {

    @Override
    public Overload build() {
        Overload overload = super.build();
        Class<?>[] ts = new Class[] {
            String.class,
            Bundle.class,
            Object.class
        };
        overload.setTypes(ts);
        overload.setName("a");
        overload.setType(DumpType.IMR);
        return overload;
    }

    @Override
    protected String getPackage() {
        return "com.immomo.momo.im.e$1";
    }

}
