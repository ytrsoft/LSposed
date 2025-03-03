package com.ytrsoft.hook;

import com.ytrsoft.annotation.Overload;
import com.ytrsoft.annotation.Path;
import com.ytrsoft.core.Param;
import com.ytrsoft.type.Address;
import com.ytrsoft.utils.Logger;

@Path("com.immomo.momo.android.service.MyAddressProvider.connectSuccess")
@Overload(types = {
    Address.class
})
public class ConnectHook extends DefaultHook {
    @Override
    public void enter(Param param) {
        super.enter(param);
        Object address = param.getArg(0);
        Logger.i("连接成功 %s ", address.toString());
    }
}
