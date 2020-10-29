package com.nextgentele.dmrc.apis.apiModel;

public class LoginResponsePayload {

     String userId;
             String empCode;
             String firstName;
             String lastName;
             String role;
             String depotId;
             String depotName;
             String busAssigned;
             String emaild;
             String mobile;
             String status;
             String mpin;

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
