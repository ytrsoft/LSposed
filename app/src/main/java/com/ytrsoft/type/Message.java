package com.ytrsoft.type;

import com.ytrsoft.annotation.Use;

@Use("com.immomo.momo.service.bean.Message")
public class Message extends Bean {

    private String remoteId;
    private String content;

    public Message(Object target) {
        super(target);
        remoteId = getString("remoteId");
        content = getString("content");
    }

    public String getRemoteId() {
        return remoteId;
    }

    public void setRemoteId(String remoteId) {
        this.remoteId = remoteId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
