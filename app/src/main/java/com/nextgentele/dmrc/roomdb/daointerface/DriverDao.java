package com.nextgentele.dmrc.roomdb.daointerface;


import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;

import com.nextgentele.dmrc.roomdb.tables.DriverDetails;

@Dao
public interface DriverDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void addDriver(DriverDetails driverDetails);


}
