package com.example.sayem.remindme;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.widget.Toast;

import java.util.Timer;
import java.util.TimerTask;


/**
 * Created by Sayem on 1/9/2016.
 */
public class MyLocation implements LocationListener {

    LocationResult locationResult;
    LocationManager locationManager;
    Timer timer;
    String bestProvider;
    Context context;
    boolean gps_enabled = false;
    boolean network_enabled = false;

    public MyLocation() {
    }

    public boolean getLocation(Context context, LocationResult locationResult) {


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (context.checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && context.checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                // TODO: Consider calling
                //    public void requestPermissions(@NonNull String[] permissions, int requestCode)
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for Activity#requestPermissions for more details.
                //return TODO;

            }
        }


        boolean flag = false;

        // I use locationResult callback to pass the location value from Mylocation to user code.
        this.locationResult = locationResult;
        if (locationManager == null) {
            locationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
        }

        // Exception will be provided if provider is not permitted.
        try {
            gps_enabled = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
        } catch (Exception e) {
            Toast.makeText(context, "GPS is unavailable", Toast.LENGTH_SHORT).show();
        }

        try {
            network_enabled = locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
        } catch (Exception e) {
            Toast.makeText(context, "Network is unavailable", Toast.LENGTH_SHORT).show();
        }

        // don't start listeners is provider is disabled
        if (!gps_enabled && !network_enabled) {
            flag = false;
        }
        if (gps_enabled) {
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, this);
            flag = true;
        }
        if (network_enabled) {
            locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, this);
            flag = true;
        }
        timer = new Timer();
        timer.schedule(new GetLastLocation(), 20000);
        return flag;
    }


    @Override
    public void onLocationChanged(Location location) {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (context.checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && context.checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                // TODO: Consider calling
                //    public void requestPermissions(@NonNull String[] permissions, int requestCode)
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for Activity#requestPermissions for more details.
                //return TODO;

            }
        }
        timer.cancel();
        locationResult.gotLocation(location);
        locationManager.removeUpdates(this);
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }


    class GetLastLocation extends TimerTask{

        @Override
        public void run() {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                if (context.checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && context.checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                    // TODO: Consider calling
                    //    public void requestPermissions(@NonNull String[] permissions, int requestCode)
                    // here to request the missing permissions, and then overriding
                    //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                    //                                          int[] grantResults)
                    // to handle the case where the user grants the permission. See the documentation
                    // for Activity#requestPermissions for more details.
                    //return TODO;

                }
            }
            locationManager.removeUpdates((LocationListener) this);
            Location gps_loc = null, net_loc = null;
            if (gps_enabled){
                gps_loc = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
            }
            if (network_enabled){
                net_loc = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
            }

            // if there are both values use the latest one
            if (gps_loc != null && net_loc != null){
                if (gps_loc.getTime() > net_loc.getTime()){
                    locationResult.gotLocation(gps_loc);
                }
                else{
                    locationResult.gotLocation(net_loc);
                }
                return;
            }
            if (gps_loc != null){
                locationResult.gotLocation(gps_loc);
                return;
            }
            if (net_loc != null){
                locationResult.gotLocation(net_loc);
                return;
            }
            locationResult.gotLocation(null);
        }
    }


    public static abstract class LocationResult{
        public abstract void gotLocation(Location location);
    }
}
