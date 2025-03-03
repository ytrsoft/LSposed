package com.ytrsoft.hook;


import android.os.Bundle;

import com.ytrsoft.annotation.Path;
import com.ytrsoft.annotation.Match;
import com.ytrsoft.core.Param;
import com.ytrsoft.type.Message;
import com.ytrsoft.utils.Logger;

import java.util.ArrayList;
import java.util.List;


@Path("com.immomo.momo.im.e$1.a")
@Match(types = {
    String.class,
    Bundle.class,
    Object.class
})
public class RecHook extends DefaultHook {

    @Override
    public void enter(Param param) {
        super.enter(param);
        Bundle bundle = (Bundle) param.getArg(1);
        if (bundle.containsKey("msgs")) {
            Object msgs = bundle.get("msgs");
            if (msgs instanceof ArrayList<?>) {
                List<?> list = (List<?>) msgs;
                for (int i = 0; i < list.size(); i++) {
                    handleMessage(list.get(i));
                }
            } else {
                if (msgs != null) {
                    handleMessage(msgs);
                }
            }
        }
    }

    private void handleMessage(Object msg) {
        Message message = new Message(msg);
        String id = message.getRemoteId();
        String content = message.getContent();
        Logger.i("<%s - %s>", id, content);
    }
}
