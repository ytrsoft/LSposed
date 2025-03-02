package com.ytrsoft.mod;

import android.app.Application;
import android.content.Context;

import com.ytrsoft.base.Overload;
import com.ytrsoft.handler.DumpType;

public class AppModule extends BaseModule {

    @Override
    public Overload build() {
        Class<?>[] types = new Class[] {
            Context.class
        };
        Overload overload = new Overload();
        overload.setTypes(types);
        overload.setTarget(Application.class);
        overload.setName("attach");
        overload.setType(DumpType.APP);
        return overload;
    }

}
