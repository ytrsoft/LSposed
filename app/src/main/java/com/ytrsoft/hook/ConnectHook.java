package com.ytrsoft.hook;

import com.ytrsoft.annotation.Match;
import com.ytrsoft.annotation.Path;
import com.ytrsoft.core.Param;
import com.ytrsoft.type.Address;
import com.ytrsoft.utils.Logger;

@Path("com.immomo.momo.android.service.MyAddressProvider.connectSuccess")
@Match(types = {
    Address.class
})
public class ConnectHook extends DefaultHook {
    @Override
    public void enter(Param param) {
        super.enter(param);
        Address address = new Address(param.getArg(0));
        String host = address.getHost();
        int port = address.getPort();
        Logger.i("连接到 %s:%d", host, port);
    }
}
