package com.nextgentele.dmrc.roomdb.tables;


import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;

@Entity(tableName = "stop_List",indices = @Index(value = {"stopId"}, unique = true))
public class StopMaster {

    @PrimaryKey(autoGenerate = true)
   public int id;
    public String stopId;
    public String stopName;
    public String distanceFromSrc;
    public String stopLat;
    public String stopLong;
}
