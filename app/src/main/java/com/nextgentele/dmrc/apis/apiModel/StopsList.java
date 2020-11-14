package com.nextgentele.dmrc.apis.apiModel;


import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.nextgentele.dmrc.roomdb.tables.StopMaster;


public class StopsList extends StopMaster {


    public String getStopId() {
        return stopId;
    }

    public String getStopName() {
        return stopName;
    }

    public String getDistanceFromSrc() {
        return distanceFromSrc;
    }

    public String getStopLat() {
        return stopLat;
    }

    public String getStopLong() {
        return stopLong;
    }
}
