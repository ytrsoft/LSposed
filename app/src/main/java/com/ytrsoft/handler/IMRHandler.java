package com.ytrsoft.handler;

import android.os.Bundle;

import com.ytrsoft.utils.Console;
import com.ytrsoft.utils.RObject;

import java.util.ArrayList;
import java.util.List;


public class IMRHandler extends DefaultHandler {

    @Override
    public void enter(Object[] e) {
        Bundle bundle = (Bundle) e[1];
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
        RObject message = new RObject(msg);
        String id = message.getString("remoteId");
        String content = message.getString("content");
        Console.log(id, content);
    }

}
