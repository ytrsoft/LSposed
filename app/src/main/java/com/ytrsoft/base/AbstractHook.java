package com.ytrsoft.base;

import android.content.Context;

import com.ytrsoft.handler.DumpFactory;
import com.ytrsoft.handler.DumpType;
import com.ytrsoft.mod.BaseModule;
import com.ytrsoft.mod.ModuleFactory;

import de.robv.android.xposed.IXposedHookLoadPackage;
import de.robv.android.xposed.IXposedHookZygoteInit;
import de.robv.android.xposed.callbacks.XC_LoadPackage;

public abstract class AbstractHook implements IXposedHookZygoteInit, IXposedHookLoadPackage, InvokeHandler {

    private AppContext mContext;
    private DumpFactory mDumpFactory;
    private ModuleFactory mModuleFactory;

    @Override
    public void handleLoadPackage(XC_LoadPackage.LoadPackageParam params) {
        String s1 = getPackage();
        String s2 = params.packageName;
        if (s1.equals(s2)) {
            mContext = new AppContext(params);
            onCreated(mContext);
            Overload app = mModuleFactory.getModule(DumpType.APP).build();
            registerMethod(app);
        }
    }

    private void registerMethod(Overload overload) {
        JMethod method = new JMethod(overload);
        method.setHandler(this);
    }

    protected void registerMethod(AppContext context, DumpType type) {
        BaseModule module = mModuleFactory.getModule(type);
        module.setContext(context);
        Overload overload = module.build();
        registerMethod(overload);
    }

    @Override
    public void initZygote(StartupParam params) {
        mDumpFactory = new DumpFactory();
        mModuleFactory = new ModuleFactory();
    }

    protected abstract String getPackage();

    protected void onCreated(AppContext context) {}

    protected void onMounted(AppContext context) {}

    @Override
    public void activated(Dump dump) {
        if (dump.getType() == DumpType.APP) {
            Context ctx = (Context) dump.getArgs()[0];
            ClassLoader loader = ctx.getClassLoader();
            mContext.setLoader(loader);
            onMounted(mContext);
        } else {
            mDumpFactory.getHandler(dump.getType()).enter(dump.getArgs());
        }
    }

    @Override
    public void deactivated(Dump dump) {
        if (dump.getType() != DumpType.APP) {
            mDumpFactory.getHandler(dump.getType()).leave(dump.getArgs());
        }
    }


}
