package com.ytrsoft.type;

import com.ytrsoft.annotation.Use;

@Use("com.immomo.im.Address")
public class Address extends Bean {

    private String host;
    private int port;

    public Address(Object target) {
        super(target);
        host = getString("host");
        port = getInt("port");
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }
}
