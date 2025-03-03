package com.ytrsoft.hook;

import com.ytrsoft.annotation.Overload;
import com.ytrsoft.annotation.Path;
import com.ytrsoft.core.Param;
import com.ytrsoft.type.Address;
import com.ytrsoft.utils.Logger;
import com.ytrsoft.utils.RObject;

@Path("com.immomo.momo.android.service.MyAddressProvider.connectSuccess")
@Overload(types = {
    Address.class
})
public class ConnectHook extends DefaultHook {
    @Override
    public void enter(Param param) {
        super.enter(param);
        Object address = param.getArg(0);
        RObject message = new RObject(address);
        String host = message.getString("host");
        int port = message.getInt("port");
        Logger.i("IMJ %s:%d", host, port);
    }
}
