package com.ytrsoft.base;

import android.app.Application;
import android.content.Context;

import de.robv.android.xposed.IXposedHookLoadPackage;
import de.robv.android.xposed.callbacks.XC_LoadPackage;

public abstract class AbstractHook implements IXposedHookLoadPackage, InvokeHandler {

    private AppContext mContext;

    private final String TAG_APP = "APP";

    public Overload getApplicationOverload() {
        Class<?>[] types = new Class[] {
            Context.class
        };
        Overload overload = new Overload();
        overload.setTypes(types);
        overload.setTarget(Application.class);
        overload.setName("attach");
        overload.setTag(TAG_APP);
        return overload;
    }

    @Override
    public void handleLoadPackage(XC_LoadPackage.LoadPackageParam params) {
        String s1 = registerPackage();
        String s2 = params.packageName;
        if (!s1.equals(s2)) {
            mContext = new AppContext(params);
            onCreated(mContext);
            Overload app = getApplicationOverload();
            registerMethod(app);
        }
    }

    protected void registerMethod(Overload overload) {
        JMethod method = new JMethod(overload);
        method.setHandler(this);
    }

    protected abstract String registerPackage();

    protected void onCreated(AppContext context) {}

    protected void onMounted(AppContext context) {}

    @Override
    public void activated(Dump dump) {}

    @Override
    public void deactivated(Dump dump) {
        if (dump.getTag().equals(TAG_APP)) {
            onMounted(mContext);
        }
    }

}
