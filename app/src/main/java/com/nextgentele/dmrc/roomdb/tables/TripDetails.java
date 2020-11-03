package com.nextgentele.dmrc.roomdb.tables;


import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "tripdetails")
public class TripDetails {

            @PrimaryKey(autoGenerate = true)
           public int tripId; //NOT NULL AUTO_INCREMENT,

    public String rosterId;

    public String routeNo;

    public String busNo;

    public String srcStation;

    public String destStation;

    public String tripStartTime;

    public String tripEndTime;

    public String actualStartTime;

    public String actualEndTime;

    public String idealGapTime; // 'Gap Time between each trip',

    public String tripStatus;  //'1-Completed, 2-Not Completed',

    public String upDownRoute;   //'1-Up, 2= Down',


}
