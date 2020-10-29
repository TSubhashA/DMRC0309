package com.nextgentele.dmrc.apis.apiModel;

import java.util.List;

public class MasterResponsePayload {


    String vehicleNo;
    String busType;
    String manufacturer;
    List<RoutesList> routes;
    List<FareRulesList> fareRules;
    List<DiscountList> discounts;
    List<PassAllowedList> passAllowed;

    public String getVehicleNo() {
        return vehicleNo;
    }

    public String getBusType() {
        return busType;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public List<RoutesList> getRoutes() {
        return routes;
    }

    public List<FareRulesList> getFareRules() {
        return fareRules;
    }

    public List<DiscountList> getDiscounts() {
        return discounts;
    }

    public List<PassAllowedList> getPassAllowed() {
        return passAllowed;
    }
}
