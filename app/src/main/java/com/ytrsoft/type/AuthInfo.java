package com.ytrsoft.type;

import androidx.annotation.NonNull;

import com.ytrsoft.annotation.Use;

@Use("com.immomo.im.AuthInfo")
public class AuthInfo extends Bean {

    private String cflag;
    private String extraData;
    private int gradsInterval;
    private String resource;
    private String session;
    private String uid;
    private String username;
    private int version;

    public AuthInfo(Object target) {
        super(target);
        cflag = getString("cflag");
        extraData = getString("extraData");
        gradsInterval = getInt("gradsInterval");
        resource = getString("resource");
        session = getString("session");
        uid = getString("uid");
        username = getString("username");
        version = getInt("version");
    }

    public String getCflag() {
        return cflag;
    }

    public void setCflag(String cflag) {
        this.cflag = cflag;
    }

    public String getExtraData() {
        return extraData;
    }

    public void setExtraData(String extraData) {
        this.extraData = extraData;
    }

    public int getGradsInterval() {
        return gradsInterval;
    }

    public void setGradsInterval(int gradsInterval) {
        this.gradsInterval = gradsInterval;
    }

    public String getResource() {
        return resource;
    }

    public void setResource(String resource) {
        this.resource = resource;
    }

    public String getSession() {
        return session;
    }

    public void setSession(String session) {
        this.session = session;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    @NonNull
    @Override
    public String toString() {
        return "AuthInfo{" +
                "cflag='" + cflag + '\'' +
                ", extraData='" + extraData + '\'' +
                ", gradsInterval=" + gradsInterval +
                ", resource='" + resource + '\'' +
                ", session='" + session + '\'' +
                ", uid='" + uid + '\'' +
                ", username='" + username + '\'' +
                ", version=" + version +
                '}';
    }
}
