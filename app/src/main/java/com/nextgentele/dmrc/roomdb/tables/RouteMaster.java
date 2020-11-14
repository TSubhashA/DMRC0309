package com.nextgentele.dmrc.roomdb.tables;


import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;

@Entity(tableName = "route_master", indices = @Index(value = {"routeNo"}, unique = true))
public class RouteMaster {

    @PrimaryKey(autoGenerate = true)
    public int id;    // NOT NULL AUTO_INCREMENT,

    public String routeNo;

    public String srcStation;

    public String destStation;

    public String routeDesc;

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
