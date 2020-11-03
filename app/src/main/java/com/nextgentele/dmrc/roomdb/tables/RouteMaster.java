package com.nextgentele.dmrc.roomdb.tables;


import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "route_master")
public class RouteMaster {

            @PrimaryKey
            public int routeId;    // NOT NULL AUTO_INCREMENT,

  public String routeNumber;

    public String routeDesc;

    public String srcStation;

    public String destStation;

    public int noOfStops;

    public int noOfBuses;

    public int busFrequency;    // 'Bus frequency in minutes.',

    public String runningTime;   //'Running time in hrs',

    public String distanceCovered;

    public String firstTripTime;    // 'example 06:30 AM',

    public String lastTripTime;      //'example 09:30 PM',

    public String idealGapTime;     // 'Ideal Gap time between two trips',

    public int routeStatus;   //'1-Active, 0-Deactive',


    public String srcLatitude;   //'Temporary fields for testing',

    public String srcLongitude;

    public String destLatitude;

    public String destLongitude;

    public String depotAssigned;  //'Depot ID from depot Master',


}
