package com.nextgentele.dmrc.apis.apiModel;

import com.nextgentele.dmrc.roomdb.tables.RouteMaster;

import java.util.List;

public class RoutesList extends RouteMaster {

    public List<StopsList> stops;

    public String getSrcStation() {
        return srcStation;
    }

    public String getDestStation() {
        return destStation;
    }

    public List<StopsList> getStops() {
        return stops;
    }

    public String getRouteNo() {
        return routeNo;
    }
}
