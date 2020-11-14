package com.nextgentele.dmrc.roomdb.daointerface;


import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Transaction;

import com.nextgentele.dmrc.roomdb.relation.RoutesWithStops;
import com.nextgentele.dmrc.roomdb.tables.DriverDetails;
import com.nextgentele.dmrc.roomdb.tables.RouteMaster;

import java.util.List;

@Dao
public interface RouteMasterDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void addRoutes(List<RouteMaster> routeMaster);

    @Transaction
    @Query("SELECT * FROM route_master")
    public List<RouteMaster> getList();

}
