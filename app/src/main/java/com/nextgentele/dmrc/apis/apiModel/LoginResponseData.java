package com.nextgentele.dmrc.apis.apiModel;

import com.google.gson.annotations.SerializedName;

public class LoginResponseData {

    @SerializedName("id")
    private int id;
    @SerializedName("cardId")
    private String cardId;
    @SerializedName("startDateTime")
    private String startDateTime;
    @SerializedName("endDateTime")
    private String endDateTime;
    @SerializedName("startLattitude")
    private String startLattitude;
    @SerializedName("startLongitude")
    private String startLongitude;
    @SerializedName("endLattitude")
    private String endLattitude;
    @SerializedName("endLongitude")
    private String endLongitude;
    @SerializedName("fareAmount")
    private String fareAmount;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCardId() {
        return cardId;
    }

    public void setCardId(String cardId) {
        this.cardId = cardId;
    }

    public String getStartDateTime() {
        return startDateTime;
    }

    public void setStartDateTime(String startDateTime) {
        this.startDateTime = startDateTime;
    }

    public String getEndDateTime() {
        return endDateTime;
    }

    public void setEndDateTime(String endDateTime) {
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

    public String getFareAmount() {
        return fareAmount;
    }

    public void setFareAmount(String fareAmount) {
        this.fareAmount = fareAmount;
    }
}
