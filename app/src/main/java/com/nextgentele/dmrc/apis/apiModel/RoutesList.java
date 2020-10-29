package com.nextgentele.dmrc.apis.apiModel;

import java.util.List;

public class RoutesList {

    String routeNo;
           String srcStation;
            String destStation;
            List<StopsList> stops;

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
