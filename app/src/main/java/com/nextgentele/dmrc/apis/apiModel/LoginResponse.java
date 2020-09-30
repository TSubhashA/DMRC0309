package com.nextgentele.dmrc.apis.apiModel;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class LoginResponse {

    @SerializedName("statusMessage")
    private String statusMessage;


    @SerializedName("statusCode")
    private int statusCode;


    @SerializedName("responseEntity")
    private LoginResponseData responseEntity;

    @SerializedName("responseEnties")
    private String responseEnties;

    public String getStatusMessage() {
        return statusMessage;
    }

    public void setStatusMessage(String statusMessage) {
        this.statusMessage = statusMessage;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public LoginResponseData getResponseEntity() {
        return responseEntity;
    }

    public void setResponseEntity(LoginResponseData responseEntity) {
        this.responseEntity = responseEntity;
    }

    public String getResponseEnties() {
        return responseEnties;
    }

    public void setResponseEnties(String responseEnties) {
        this.responseEnties = responseEnties;
    }
}

