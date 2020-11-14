package com.nextgentele.dmrc.roomdb.daointerface;


import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;


import com.nextgentele.dmrc.roomdb.tables.StopMaster;

import java.util.List;

@Dao
public interface StopMasterDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void addStops(List<StopMaster> routeMaster);

}
