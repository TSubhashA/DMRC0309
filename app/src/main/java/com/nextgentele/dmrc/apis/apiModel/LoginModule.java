package com.nextgentele.dmrc.apis.apiModel;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class LoginModule implements Serializable {


    int channelId;
    String tokenId;
    LoginPayload payload;

    public LoginModule(int channelId, String tokenId, LoginPayload payload) {
        this.channelId = channelId;
        this.tokenId = tokenId;
        this.payload = payload;
    }

    public int getChannelId() {
        return channelId;
    }

    public void setChannelId(int channelId) {
        this.channelId = channelId;
    }

    public String getTokenId() {
        return tokenId;
    }

    public void setTokenId(String tokenId) {
        this.tokenId = tokenId;
    }

    public LoginPayload getPayload() {
        return payload;
    }

    public void setPayload(LoginPayload payload) {
        this.payload = payload;
    }
}
