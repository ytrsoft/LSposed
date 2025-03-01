package com.ytrsoft;

import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XposedHelpers;

public class ClassMethod extends XC_MethodHook {

    private final String name;
    private Caller caller;

    public static class Dump {
        private Object[] args;
        private Object result;
        private String tag;

        public Object[] getArgs() {
            return args;
        }

        public void setArgs(Object[] args) {
            this.args = args;
        }

        public Object getResult() {
            return result;
        }

        public void setResult(Object result) {
            this.result = result;
        }

        public String getTag() {
            return tag;
        }

        public void setTag(String tag) {
            this.tag = tag;
        }
    }

    public interface Caller {
        void onMethodHook(Dump dump);
        void onMethodLeave(Dump dump);
    }

    public ClassMethod(Overload overload) {
        this.name = overload.getTag();
        Object[] types = overload.getParamTypes();
        int len = overload.getParamTypes().length;
        Object[] merge = new Object[len + 1];
        System.arraycopy(types, 0, merge, 0, len);
        merge[merge.length - 1] = this;
        String name = overload.getName();
        Class<?> clz = overload.getTarget();
        XposedHelpers.findAndHookMethod(clz, name, merge);
    }

    public void setCaller(Caller caller) {
        this.caller = caller;
    }

    @Override
    protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
        super.beforeHookedMethod(param);
        if (caller != null) {
            Dump dump = new Dump();
            dump.setArgs(param.args);
            dump.setTag(name);
            caller.onMethodHook(dump);
        }
    }

    @Override
    protected void afterHookedMethod(MethodHookParam param) throws Throwable {
        super.afterHookedMethod(param);
        if (caller != null) {
            Dump dump = new Dump();
            dump.setResult(param.getResult());
            dump.setTag(name);
            caller.onMethodLeave(dump);
        }
    }
}
