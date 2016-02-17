package com.example.sayem.remindme;

import android.Manifest;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.location.LocationProvider;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.IBinder;
import android.provider.Settings;
import android.support.annotation.Nullable;
import android.widget.Toast;

/**
 * Created by Sayem on 2/13/2016.
 */
public class CurrentLocation extends Service {


    private Context context = this;
    private LocationManager locationManager;
    private static final int LOCATION_INTERVAL = 1000;
    private static final float LOCATION_DISTANCE = 10;
    public Location lastLocation;


    public CurrentLocation(){

    }

    /*public CurrentLocation(Context context) {
        this.context = context;
    }*/

    private class LocationListener implements android.location.LocationListener{

        public LocationListener(String provider){
            lastLocation = new Location(provider);
        }

        @Override
        public void onLocationChanged(Location location) {
            if (location == null) {
                Toast.makeText(getBaseContext(), "Searching for location", Toast.LENGTH_SHORT).show();
                lastLocation.set(location);
            }else{
                lastLocation.set(location);
                if (distance(lastLocation.getLatitude(), lastLocation.getLongitude(), 22.8986909, 89.5014969) < 0.0124274 ){

                    Toast.makeText(getBaseContext(), "Changed location: " + lastLocation.getAccuracy() +
                            " " + lastLocation.getLatitude() +
                            " " + lastLocation.getLongitude() +
                            " " + lastLocation.getProvider(), Toast.LENGTH_LONG).show();

                    startRingtonePlayingService();
                } else {
                    Toast.makeText(context, "Distance is too long", Toast.LENGTH_SHORT).show();
                    Toast.makeText(getBaseContext(), "Changed location: " + lastLocation.getAccuracy() +
                            " " + lastLocation.getLatitude() +
                            " " + lastLocation.getLongitude() +
                            " " + lastLocation.getProvider(), Toast.LENGTH_LONG).show();
                    stopRingtonePlayingService();
                }

            }
        }

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {
            /* This is called when the GPS status alters */
            switch (status) {
                case LocationProvider.OUT_OF_SERVICE:
                    Toast.makeText(getBaseContext(), "Status Changed: Out of Service",
                            Toast.LENGTH_SHORT).show();
                    break;
                case LocationProvider.TEMPORARILY_UNAVAILABLE:
                    Toast.makeText(getBaseContext(), "Status Changed: Temporarily Unavailable",
                            Toast.LENGTH_SHORT).show();
                    break;
                case LocationProvider.AVAILABLE:
                    Toast.makeText(getBaseContext(), "Status Changed: Available",
                            Toast.LENGTH_SHORT).show();
                    break;
            }
        }

        @Override
        public void onProviderEnabled(String provider) {

        }

        @Override
        public void onProviderDisabled(String provider) {

        }
    }

    LocationListener[] locationListeners = new LocationListener[]{
            new LocationListener(LocationManager.GPS_PROVIDER),
            new LocationListener(LocationManager.NETWORK_PROVIDER)
    };

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        super.onStartCommand(intent, flags, startId);

        initializeLocationManager();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (getBaseContext().checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && getBaseContext().checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
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

        Location gpsLocation;
        Location networkLocation;
        gpsLocation = requestUpdateFromProvider(LocationManager.GPS_PROVIDER, locationListeners[0]);
        networkLocation = requestUpdateFromProvider(LocationManager.NETWORK_PROVIDER, locationListeners[1]);

        if (gpsLocation != null && networkLocation != null){
            Location userCurrentLocation = getBetterLocation(gpsLocation, networkLocation);
            lastLocation.set(userCurrentLocation);
        }
        else if (gpsLocation != null){
            lastLocation.set(gpsLocation);
        }
        else if (networkLocation != null){
            lastLocation.set(networkLocation);
        }
        else{
            Toast.makeText(getBaseContext(), "No data available", Toast.LENGTH_SHORT).show();
        }

        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (getBaseContext().checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && getBaseContext().checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
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
        if (locationManager != null){
            for (int i = 0; i < locationListeners.length; i++){
                locationManager.removeUpdates(locationListeners[i]);
            }
        }
        Toast.makeText(getBaseContext(), "Please turn off gps for saving battery", Toast.LENGTH_LONG).show();
        stopRingtonePlayingService();
        /*Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
        getBaseContext().startActivity(intent);*/
    }

    private Location requestUpdateFromProvider(String provider, LocationListener locationListener){

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (getBaseContext().checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && getBaseContext().checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
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

        Location location = null;
        boolean isEnabled = locationManager.isProviderEnabled(provider);
        if (isEnabled){
            locationManager.requestLocationUpdates(provider, LOCATION_INTERVAL, LOCATION_DISTANCE, locationListener);
            location = locationManager.getLastKnownLocation(provider);
            return location;
        } else {
            Toast.makeText(getBaseContext(), "Network or GPS is disabled", Toast.LENGTH_SHORT).show();
        }
        /*Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
        getBaseContext().startActivity(intent);*/
        return location;
    }

    private Location getBetterLocation(Location gpsLocation, Location networkLocation) {

        // GPS location is always better than Network Location
        Location betterLocation = null;
        if (networkLocation == null){
            betterLocation = gpsLocation;
        }
        // Check whether the GPS location fix is newer or older
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

    private void initializeLocationManager(){
        if (locationManager == null){
            locationManager = (LocationManager) getBaseContext().getSystemService(Context.LOCATION_SERVICE);
        }
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    /* Calculate the distance between two locations in Miles unit */
    private double distance(double userLatitude, double userLongitude, double givenLatitude, double givenLongitude){
        double distance = 0;
        double earthRadius = 3958.75; // In miles

        double dLatitude = Math.toRadians(userLatitude - givenLatitude);
        double dLongitude = Math.toRadians(userLongitude - givenLongitude);

        double sinOfdLatitude = Math.sin(dLatitude / 2);
        double sinOfdLongitude = Math.sin(dLongitude / 2);

        double a = Math.pow(sinOfdLatitude, 2) + Math.pow(sinOfdLongitude, 2)
                * Math.cos(Math.toDegrees(givenLatitude)) * Math.cos(Math.toRadians(userLatitude));

        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));
        distance = earthRadius * c;
        return distance; // output distance in Miles unit
    }

    /*Start Ringtone playing service*/
    private void startRingtonePlayingService(){
        AlarmStateDatabase alarmStateDatabase = new AlarmStateDatabase(context);
        String[] alarmToneUri = new String[]{};
        alarmToneUri = alarmStateDatabase.readAlarmToneTable();
        String alarmToneNameTemp = new String();
        for (int i = 0; i < alarmToneUri.length; i++){
            alarmToneNameTemp = alarmToneUri[i];
        }
        if (alarmToneUri.length > 0){
            Intent startIntent = new Intent(context, RingtonePlayingService.class);
            startIntent.putExtra("ringtone_uri", alarmToneNameTemp);
            context.startService(startIntent);
        } else {
            Toast.makeText(context, "Please set an alarm tone at first!!", Toast.LENGTH_SHORT).show();
        }
    }

    /*Stop Ringtone playing service*/
    private void stopRingtonePlayingService(){
        Intent stopIntent = new Intent(context, RingtonePlayingService.class);
        context.stopService(stopIntent);
    }

}
