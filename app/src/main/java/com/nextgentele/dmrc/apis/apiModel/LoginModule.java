package com.nextgentele.dmrc.apis.apiModel;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class LoginModule implements Serializable {


    private String cardId;

    private long startDateTime;

    private long endDateTime;

    private String startLattitude;

    private String startLongitude;

    private String endLattitude;

    private String endLongitude;

    private double fareAmount;

    public LoginModule(String cardId, long startDateTime, long endDateTime, String startLattitude, String startLongitude, String endLattitude, String endLongitude, double fareAmount) {
        this.cardId = cardId;
        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;
        this.startLattitude = startLattitude;
        this.startLongitude = startLongitude;
        this.endLattitude = endLattitude;
        this.endLongitude = endLongitude;
        this.fareAmount = fareAmount;
    }

    public String getCardId() {
        return cardId;
    }

    public void setCardId(String cardId) {
        this.cardId = cardId;
    }

    public long getStartDateTime() {
        return startDateTime;
    }

    public void setStartDateTime(long startDateTime) {
        this.startDateTime = startDateTime;
    }

    public long getEndDateTime() {
        return endDateTime;
    }

    public void setEndDateTime(long endDateTime) {
        this.endDateTime = endDateTime;
    }

    public String getStartLattitude() {
        return startLattitude;
    }

    public void setStartLattitude(String startLattitude) {
        this.startLattitude = startLattitude;
    }

    public String getStartLongitude() {
        return startLongitude;
    }

    public void setStartLongitude(String startLongitude) {
        this.startLongitude = startLongitude;
    }

    public String getEndLattitude() {
        return endLattitude;
    }

    public void setEndLattitude(String endLattitude) {
        this.endLattitude = endLattitude;
    }

    public String getEndLongitude() {
        return endLongitude;
    }

    public void setEndLongitude(String endLongitude) {
        this.endLongitude = endLongitude;
    }

    public double getFareAmount() {
        return fareAmount;
    }

    public void setFareAmount(double fareAmount) {
        this.fareAmount = fareAmount;
    }
}
