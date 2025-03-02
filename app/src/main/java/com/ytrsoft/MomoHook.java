package com.ytrsoft;

import com.ytrsoft.base.AbstractHook;
import com.ytrsoft.base.AppContext;

import com.ytrsoft.handler.DumpType;

public class MomoHook extends AbstractHook {

    @Override
    protected String getPackage() {
        return "com.immomo.momo";
    }

    @Override
    protected void onMounted(AppContext context) {
        super.onMounted(context);
        registerMethod(context, DumpType.IMR);
    }

}
