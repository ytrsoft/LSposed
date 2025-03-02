package com.ytrsoft;

import android.os.Bundle;

import com.ytrsoft.base.AbstractHook;
import com.ytrsoft.base.AppContext;
import com.ytrsoft.base.Dump;
import com.ytrsoft.base.Overload;
import com.ytrsoft.utils.Console;

import java.util.ArrayList;
import java.util.List;

import cn.hutool.core.util.ReflectUtil;

public class MomoHook extends AbstractHook {

    private static final String TAG_REC = "REC";

    private static final String PKG_IMJ = "com.immomo.momo.im.e$1";

    @Override
    protected String getPackage() {
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
        Class<?> clz = context.getLoaderClass(PKG_IMJ);
        overload.setTarget(clz);
        registerMethod(overload);
    }

    @Override
    public void activated(Dump dump) {
        super.activated(dump);
        if (dump.getTag().equals(TAG_REC)) {
            receiveHandle((Bundle) dump.getArgs()[1]);
        }
    }

    private void receiveHandle(Bundle bundle) {
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
        String remoteId = (String) ReflectUtil.getFieldValue(msg, "remoteId");
        String content = (String) ReflectUtil.getFieldValue(msg, "content");
        Console.log(remoteId, content);
    }

}
