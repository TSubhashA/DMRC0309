package com.nextgentele.dmrc.apis.apiModel;

public class MasterRequestPayload {

     String mobile;
     String deviceId;

    public MasterRequestPayload(String mobile, String deviceId) {
        this.mobile = mobile;
        this.deviceId = deviceId;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }
}
