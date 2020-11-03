package com.nextgentele.dmrc.roomdb;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.nextgentele.dmrc.roomdb.daointerface.DriverDao;
import com.nextgentele.dmrc.roomdb.daointerface.TripsDetailsDao;
import com.nextgentele.dmrc.roomdb.tables.DriverDetails;
import com.nextgentele.dmrc.roomdb.tables.TripDetails;


@Database(entities = {DriverDetails.class, TripDetails.class}, version = 1,exportSchema = true)
public abstract class AppDatabase extends RoomDatabase {
    public abstract DriverDao driverDao();
    public abstract TripsDetailsDao tripsDetailsDao();

}

