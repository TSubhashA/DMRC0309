package com.nextgentele.dmrc.roomdb.tables;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "driver_details")
public class DriverDetails {

    @PrimaryKey
    public String id;

    public String empCode;
    public String firstName;
    public String lastName;
    public String busNumber;
    public String busType;
    public String seatingCapacity;
    public String standingCapacity;
    public String loadCapacity;
    public String manufacturedDate;
    public String manufacturedBy;
    public String depotAssigned;
    public String depottName;
    public String fuelType;
    public String role;
    public String empStatus;
    public String mobileNo;
    public String mpin;

}
