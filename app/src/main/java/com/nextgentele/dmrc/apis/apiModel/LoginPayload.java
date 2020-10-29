package com.nextgentele.dmrc.apis.apiModel;

public class LoginPayload {

    String mobile;
    String password;
    String deviceId;

    public LoginPayload(String mobile, String password, String deviceId) {
        this.mobile = mobile;
        this.password = password;
        this.deviceId = deviceId;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getMobile() {
        return mobile;
    }

    public String getPassword() {
        return password;
    }

    public String getDeviceId() {
        return deviceId;
    }
}

