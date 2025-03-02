package com.ytrsoft.mod;

import com.ytrsoft.base.AppContext;
import com.ytrsoft.base.Overload;

public class BaseModule implements Module {

    private AppContext context;

    public void setContext(AppContext context) {
        this.context = context;
    }

    @Override
    public Overload build() {
        Overload overload = new Overload();
        if (context != null) {
            Class<?> clz = context.getLoaderClass(getPackage());
            overload.setTarget(clz);
        }
        return overload;
    }

    protected String getPackage() {
        return "";
    }

}
