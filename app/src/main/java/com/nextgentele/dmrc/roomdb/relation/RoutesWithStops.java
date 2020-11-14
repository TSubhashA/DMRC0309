package com.nextgentele.dmrc.roomdb.relation;

import androidx.room.Embedded;
import androidx.room.Relation;

import com.nextgentele.dmrc.apis.apiModel.StopsList;
import com.nextgentele.dmrc.roomdb.tables.RouteMaster;
import com.nextgentele.dmrc.roomdb.tables.StopMaster;

import java.util.List;

public class RoutesWithStops {

    @Embedded
    public RouteMaster routes;
    @Relation(
            parentColumn = "routeNo",
            entityColumn = "stopId"
    )
    public List<StopMaster> lists;

}
