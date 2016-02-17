package com.example.sayem.remindme;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.location.LocationProvider;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.PermissionChecker;
import android.widget.Toast;

/**
 * Created by Sayem on 2/12/2016.
 */
public class UserCurrentLocation {

    private Context context;
    private LocationManager locationManager;

    public UserCurrentLocation(Context context, LocationManager locationManager) {
        this.context = context;
        this.locationManager = locationManager;
    }


    public void stopListeningLocation() {
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
        locationManager.removeUpdates(locationListener);
    }

    public Location startListeningLocation() {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {

            if (context.checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && context.checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                // TODO: Consider calling
                //    public void requestPermissions(@NonNull String[] permissions, int requestCode)
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for Activity#requestPermissions for more details.
                //return;
            }
        }
        Location gpsLocation = null;
        Location networkLocation = null;
        Location userCurrentLocation = null;
        locationManager.removeUpdates(locationListener);
        gpsLocation = requestUpdateFromProvider(LocationManager.GPS_PROVIDER);
        networkLocation = requestUpdateFromProvider(LocationManager.NETWORK_PROVIDER);
        if (gpsLocation != null && networkLocation != null){
            userCurrentLocation = getBetterLocation(gpsLocation, networkLocation);
        }else if (gpsLocation != null){
            userCurrentLocation = gpsLocation;
        }else if (networkLocation != null){
            userCurrentLocation = networkLocation;
        }else{
            Toast.makeText(context, "No data available", Toast.LENGTH_SHORT).show();
        }
        return userCurrentLocation;

    }

    private Location requestUpdateFromProvider(String provider) {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            if (context.checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && context.checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                // TODO: Consider calling
                //    public void requestPermissions(@NonNull String[] permissions, int requestCode)
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for Activity#requestPermissions for more details.
                //return;
            }
        }

        Location location = null;
        boolean isEnabled = locationManager.isProviderEnabled(provider);
        if (isEnabled) {

            locationManager.requestLocationUpdates(provider, 1000, 10, locationListener);
            location = locationManager.getLastKnownLocation(provider);
        }else{
            Toast.makeText(context, provider + " is not enabled", Toast.LENGTH_SHORT).show();
        }
        return location;
    }

    private Location getBetterLocation(Location gpsLocation, Location networkLocation){
        Location betterLocation = null;

        // GPS location is always better than Network location
        if (networkLocation != null){
            betterLocation = networkLocation;
        }
        long timeDelta = gpsLocation.getTime() - networkLocation.getTime();
        int accuracyDelta = (int) gpsLocation.getAccuracy() - (int) networkLocation.getAccuracy();

        // If it's been more than two minutes since the network location, use the gps location
        // because the user has likely moved
        if (timeDelta > 60000 || accuracyDelta < 0){
            betterLocation = gpsLocation;
        }

        // If it's been less than two minutes since the network location, use the network location
        // and it must be worse
        else if (timeDelta < 60000 || accuracyDelta > 0){
            betterLocation = networkLocation;
        }
        else if (timeDelta > 0 && accuracyDelta < 0){
            betterLocation = gpsLocation;
        }

        return betterLocation;
    }

    LocationListener locationListener = new LocationListener() {
        @Override
        public void onLocationChanged(Location location) {
            if (location == null) {
                Toast.makeText(context, "Searching for location", Toast.LENGTH_SHORT).show();
                location = startListeningLocation();
                onLocationChanged(location);
            }else{

                int status = 1;
                Toast.makeText(context, "" + location.getAccuracy() +
                        " " + location.getLatitude() +
                        " " + location.getLongitude() +
                        " " + location.getProvider(), Toast.LENGTH_LONG).show();

            }
        }

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {


            /* This is called when the GPS status alters */
            switch (status) {
                case LocationProvider.OUT_OF_SERVICE:

                    Toast.makeText(context, "Status Changed: Out of Service",
                        Toast.LENGTH_SHORT).show();
                    break;
                case LocationProvider.TEMPORARILY_UNAVAILABLE:
                    Toast.makeText(context, "Status Changed: Temporarily Unavailable",
                            Toast.LENGTH_SHORT).show();
                    break;
                case LocationProvider.AVAILABLE:
                    Toast.makeText(context, "Status Changed: Available",
                            Toast.LENGTH_SHORT).show();
                    break;
            }

        }

        @Override
        public void onProviderEnabled(String provider) {
            Toast.makeText(context, "GPS is enabled", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onProviderDisabled(String provider) {
            // this is called if/when GPS is disabled in settings
            Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
        }
    };

}
