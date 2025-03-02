package com.ytrsoft;

import android.os.Bundle;

import com.ytrsoft.base.AbstractHook;
import com.ytrsoft.base.AppContext;
import com.ytrsoft.base.Dump;
import com.ytrsoft.base.Overload;
import com.ytrsoft.utils.Console;

public class MomoHook extends AbstractHook {

    private static final String TAG_REC = "REC";

    private static final String PKG_IMJ = "com.immomo.momo.im.e$1";

    @Override
    protected String registerPackage() {
        return "com.immomo.momo";
    }

    @Override
    protected void onMounted(AppContext context) {
        super.onMounted(context);
        registerIMJMethod(context);
    }

    private void registerIMJMethod(AppContext context) {
        Class<?>[] ts = new Class[] {
            String.class,
            Bundle.class,
            Object.class
        };
        Overload overload = new Overload();
        overload.setTypes(ts);
        overload.setName("a");
        overload.setTag(TAG_REC);
        Class<?> clz = context.getClass(PKG_IMJ);
        overload.setTarget(clz);
        registerMethod(overload);
    }

    @Override
    public void activated(Dump dump) {
        super.activated(dump);
        Console.log("进入", dump.getTag());
    }

    @Override
    public void deactivated(Dump dump) {
        super.deactivated(dump);
        Console.log("离开", dump.getTag());
    }

}
