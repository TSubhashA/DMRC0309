package com.nextgentele.dmrc.roomdb.daointerface;


import androidx.room.Dao;
import androidx.room.Insert;

import com.nextgentele.dmrc.roomdb.tables.DriverDetails;

@Dao
public interface DriverDao {

    @Insert
    public void addDriver(DriverDetails driverDetails);


}
