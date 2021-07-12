package com.example.iot_asma_support;

public class Support {
    private String username;
    private String uid;

    public Support(){}

    public Support(String username, String uid){
        this.username = username;
        this.uid = uid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }
}
