package com.nextgentele.dmrc.roomdb;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.nextgentele.dmrc.roomdb.daointerface.DriverDao;
import com.nextgentele.dmrc.roomdb.daointerface.RouteMasterDao;
import com.nextgentele.dmrc.roomdb.daointerface.StopMasterDao;
import com.nextgentele.dmrc.roomdb.daointerface.TripsDetailsDao;
import com.nextgentele.dmrc.roomdb.tables.DriverDetails;
import com.nextgentele.dmrc.roomdb.tables.RouteMaster;
import com.nextgentele.dmrc.roomdb.tables.StopMaster;
import com.nextgentele.dmrc.roomdb.tables.TripDetails;


@Database(entities = {DriverDetails.class, TripDetails.class, RouteMaster.class, StopMaster.class}, version = 1,exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {
    public abstract DriverDao driverDao();
    public abstract TripsDetailsDao tripsDetailsDao();
    public abstract StopMasterDao stopMasterDao();
    public abstract RouteMasterDao RouteMasterDao();


    private static AppDatabase INSTANCE;

    public static final String NAME = "MyBusDataBase";

    public static AppDatabase getAppDatabase(Context context) {
        if (INSTANCE == null) {
            INSTANCE =
                    Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class, AppDatabase.NAME)
                            .allowMainThreadQueries()
                            .build();
        }
        return INSTANCE;
    }

    public static void destroyInstance() {
        INSTANCE = null;
    }

}

