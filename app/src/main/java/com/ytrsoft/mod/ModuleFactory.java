package com.ytrsoft.mod;

import com.ytrsoft.handler.DumpType;

public class ModuleFactory {

    public BaseModule getModule(DumpType type){
        if (type == DumpType.APP) {
            return new AppModule();
        }
        if (type == DumpType.IMR) {
            return new IMRModule();
        }
        return new BaseModule();
    }

}
