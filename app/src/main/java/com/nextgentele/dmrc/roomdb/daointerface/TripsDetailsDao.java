package com.nextgentele.dmrc.roomdb.daointerface;

import androidx.room.Dao;
import androidx.room.Insert;

import com.nextgentele.dmrc.roomdb.tables.DriverDetails;
import com.nextgentele.dmrc.roomdb.tables.TripDetails;


@Dao
public interface TripsDetailsDao {
    @Insert
    public void addTrips(TripDetails tripDetails);


}
