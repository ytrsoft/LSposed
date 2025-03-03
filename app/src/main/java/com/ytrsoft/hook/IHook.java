package com.ytrsoft.hook;

import com.ytrsoft.core.Param;

public interface IHook {
    void enter(Param param);

    void leave(Param param);
}
