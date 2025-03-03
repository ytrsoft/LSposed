package com.ytrsoft.hook;

import com.ytrsoft.annotation.Path;
import com.ytrsoft.core.Param;
import com.ytrsoft.utils.Logger;

@Path("com.immomo.im.IMJConnectionManager.startIMJ")
public class StartHook extends DefaultHook {
    @Override
    public void enter(Param param) {
        super.enter(param);
        Logger.i("启动IMJ服务器");
    }
}
