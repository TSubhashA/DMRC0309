package com.nextgentele.dmrc.util;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;

public class GeoFencing implements LocationListener {

    Location location, location2;
    LocationManager locationManager;
    Context context;
    double R = 6378.137; // Radius of earth in KM
    CheckPermission checkPermission;
    private static final int GPS_PERMISSION = 100;


    double lat1,lat2,lon1,lon2;

    public GeoFencing(Context context) {
        this.context = context;
    }

    public void geoCalc() {
        locationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
        checkPermission=new CheckPermission(context);

        try {
            Log.i("Location", "Started");
            if (ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                // TODO: Consider calling
                //    ActivityCompat#requestPermissions
                checkPermission.checkPermission(
                        Manifest.permission.ACCESS_FINE_LOCATION,
                        GPS_PERMISSION);
                return;
            } else {
                location = new Location("LocationA");
                location2 = new Location("LocationA");
                Log.i("Location", "Ended");
                Log.i("Location_Result", String.valueOf(location));
                if (location != null) {
                    location.setLatitude(17.372102);
                    location.setLongitude(78.484196);

                    location2.setLatitude(17.372102);
                    location2.setLongitude(78.484195);

                }
                    lat1=location.getLatitude();
                    lon1=location.getLongitude();
                    lat2=location2.getLatitude();
                    lon2=location2.getLongitude();
                    Log.e("TAG", "GPS is on");
                    //onLocationChanged(location);
                    //Toast.makeText(context, "latitude:" + location.getLatitude() + " longitude:" + location.getLatitude(), Toast.LENGTH_SHORT).show();
            }
        } catch (Exception e) {
            Toast.makeText(context, "" + e, Toast.LENGTH_LONG).show();
            Log.i("Exception : ", String.valueOf(e));
        }


       double dist= location.distanceTo(location2);
        Toast.makeText(context, "FROM 2 :"+dist, Toast.LENGTH_SHORT).show();
        Log.i("from 2 ", String.valueOf(dist));

        double dLat = lat2 * Math.PI / 180 - lat1 * Math.PI / 180;
        double dLon = lon2 * Math.PI / 180 - lon1 * Math.PI / 180;
        double a = Math.sin(dLat / 2) * Math.sin(dLat / 2) +
                Math.cos(lat1 * Math.PI / 180) * Math.cos(lat2 * Math.PI / 180) *
                        Math.sin(dLon / 2) * Math.sin(dLon / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        double d = R * c;

       double dm= d * 1000; // meters
        Toast.makeText(context, "From 1 :"+dm, Toast.LENGTH_LONG).show();
        Log.i("From 1 ", String.valueOf(dm));

    }

    @Override
    public void onLocationChanged(@NonNull Location location) {

        Toast.makeText(context, "Location : "+location.getLatitude()+" : "+ location.getLatitude(), Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onStatusChanged(String s, int i, Bundle bundle) {

    }

    @Override
    public void onProviderEnabled(String s) {

    }

    @Override
    public void onProviderDisabled(String s) {

    }
}
