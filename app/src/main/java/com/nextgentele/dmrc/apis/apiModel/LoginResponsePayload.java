package com.nextgentele.dmrc.apis.apiModel;

import com.nextgentele.dmrc.roomdb.tables.DriverDetails;

public class LoginResponsePayload extends DriverDetails {


    public String getUserId() {
        return userId;
    }

    public String getEmpCode() {
        return empCode;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getRole() {
        return role;
    }

    public String getDepotId() {
        return depotId;
    }

    public String getDepotName() {
        return depotName;
    }

    public String getBusAssigned() {
        return busAssigned;
    }

    public String getEmaild() {
        return emaild;
    }

    public String getMobile() {
        return mobile;
    }

    public String getStatus() {
        return status;
    }

    public String getMpin() {
        return mpin;
    }
}
