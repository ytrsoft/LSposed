package com.ytrsoft.hook;

import com.ytrsoft.annotation.Match;
import com.ytrsoft.annotation.Path;
import com.ytrsoft.core.Param;
import com.ytrsoft.type.AuthInfo;
import com.ytrsoft.utils.Logger;

@Path("com.immomo.im.IMJConnectionManager.setAuthInfo")
@Match(types = {
  AuthInfo.class
})
public class AuthHook extends DefaultHook {
    @Override
    public void enter(Param param) {
        super.enter(param);
        AuthInfo auth = new AuthInfo(param.getArg(0));
        Logger.i("Auth: %s", auth.toString());
    }
}
