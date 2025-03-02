package com.ytrsoft.handler;

public class DumpFactory {

    public DumpHandler getHandler(DumpType type){
        if (type == DumpType.IMR) {
            return new IMRHandler();
        }
        return new DefaultHandler();
    }

}
