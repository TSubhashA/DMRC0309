package com.nextgentele.dmrc.db;

import java.io.Serializable;

public class Users implements Serializable {

    private String userName;
    private float userBal;
    private long dateTimeIn;
    private long datetimeOut;
    private String latIn;
    private String longIn;
    private String latOut;
    private String longOut;
    private long fare;


    public Users(String userName, float userBal, long dateTimeIn, long datetimeOut, String latIn, String longIn, String latOut, String longOut, long fare) {
        this.userName = userName;
        this.userBal = userBal;
        this.dateTimeIn = dateTimeIn;
        this.datetimeOut = datetimeOut;
        this.latIn = latIn;
        this.longIn = longIn;
        this.latOut = latOut;
        this.longOut = longOut;
        this.fare = fare;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public float getUserBal() {
        return userBal;
    }

    public void setUserBal(float userBal) {
        this.userBal = userBal;
    }

    public long getDateTimeIn() {
        return dateTimeIn;
    }

    public void setDateTimeIn(long dateTimeIn) {
        this.dateTimeIn = dateTimeIn;
    }

    public long getDatetimeOut() {
        return datetimeOut;
    }

    public void setDatetimeOut(long datetimeOut) {
        this.datetimeOut = datetimeOut;
    }

    public String getLatIn() {
        return latIn;
    }

    public void setLatIn(String latIn) {
        this.latIn = latIn;
    }

    public String getLongIn() {
        return longIn;
    }

    public void setLongIn(String longIn) {
        this.longIn = longIn;
    }

    public String getLatOut() {
        return latOut;
    }

    public void setLatOut(String latOut) {
        this.latOut = latOut;
    }

    public String getLongOut() {
        return longOut;
    }

    public void setLongOut(String longOut) {
        this.longOut = longOut;
    }

    public long getFare() {
        return fare;
    }

    public void setFare(long fare) {
        this.fare = fare;
    }
}
