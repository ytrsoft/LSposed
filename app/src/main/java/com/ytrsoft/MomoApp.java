package com.ytrsoft;

import com.ytrsoft.core.HookApplication;
import com.ytrsoft.annotation.Inject;
import com.ytrsoft.core.AppContext;
import com.ytrsoft.hook.RecHook;
import com.ytrsoft.utils.Logger;

@Inject("com.immomo.momo")
public class MomoApp extends HookApplication {

    @Override
    protected void handleCreated(AppContext context) {
        super.handleCreated(context);
        Logger.i("陌陌模块启动");
    }

    @Override
    protected void handleMounted(AppContext context) {
        super.handleMounted(context);
        registerHook(new RecHook());
    }

}
