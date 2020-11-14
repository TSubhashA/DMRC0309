package com.nextgentele.dmrc.roomdb.tables;

import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "driver_details",indices = @Index(value = {"userId"}, unique = true))
public class DriverDetails implements Serializable {

    @PrimaryKey(autoGenerate = true)
    public int id;
    public String userId;
    public String empCode;
    public String firstName;
    public String lastName;
    public String role;
    public String depotId;
    public String depotName;
    public String busAssigned;
    public String emaild;
    public String mobile;
    public String status;
    public String mpin;



    public int getId() {
        return id;
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

    public String getUserId() {
        return userId;
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
