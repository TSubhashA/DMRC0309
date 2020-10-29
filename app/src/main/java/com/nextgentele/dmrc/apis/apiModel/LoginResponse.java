package com.nextgentele.dmrc.apis.apiModel;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class LoginResponse {

    private int status;

    private String message;

    private List<LoginResponsePayload> payload;

    public int getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public List<LoginResponsePayload> getPayload() {
        return payload;
    }
}

