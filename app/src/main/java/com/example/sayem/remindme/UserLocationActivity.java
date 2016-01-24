package com.example.sayem.remindme;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class UserLocationActivity extends Activity {

    private TextView textLatitude;
    private TextView textLongitude;
    private TextView textAccuracy;
    private TextView textProvider;

    private LocationManager locationManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location);
        locationManager = (LocationManager) getSystemService(getApplicationContext().LOCATION_SERVICE);
        textLatitude = (TextView) findViewById(R.id.textLatitude);
        textLongitude = (TextView) findViewById(R.id.textLongitude);
        textAccuracy = (TextView) findViewById(R.id.textAccuracy);
        textProvider = (TextView) findViewById(R.id.textProvider);
    }

    @Override
    protected void onStart() {
        super.onStart();
        boolean isGPSEnabled = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
        boolean isNetworkEnabled = locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
        if (!isGPSEnabled && !isNetworkEnabled){
            Toast.makeText(UserLocationActivity.this, "Please enable the GPS and Mobile Data", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        setUp();
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (getApplicationContext().checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && getApplicationContext().checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
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

    @Override
    protected void onStop() {
        super.onStop();
    }

    private void setUp() {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (getApplicationContext().checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && getApplicationContext().checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
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

        Location gpsLocation = null;
        Location networkLocation = null;
        locationManager.removeUpdates(locationListener);
        gpsLocation = requestUpdateFromProvider(LocationManager.GPS_PROVIDER);
        networkLocation = requestUpdateFromProvider(LocationManager.NETWORK_PROVIDER);

        if (gpsLocation != null && networkLocation != null){
            Location userCurrentLocation = getBetterLocation(gpsLocation, networkLocation);
            setLocationToast(userCurrentLocation);
        }
        else if (gpsLocation != null){
            setLocationToast(gpsLocation);
        }
        else if (networkLocation != null){
            setLocationToast(networkLocation);
        }
        else{
            Toast.makeText(getApplicationContext(), "No data available", Toast.LENGTH_SHORT).show();
        }

    }

    private void setLocationToast(Location userCurrentLocation) {
        double latitude = userCurrentLocation.getLatitude();
        double longitude = userCurrentLocation.getLongitude();
        float accuracy = userCurrentLocation.getAccuracy();
        String provider = userCurrentLocation.getProvider();
        /*String text = "Latitude: " + latitude + "\n" +
                        "Longitude: " + longitude + "\n" +
                        "Accuracy: " + accuracy + "\n" +
                        "Provider: " + provider;
        Toast.makeText(getApplicationContext(), text, Toast.LENGTH_LONG).show();*/
        textLatitude.setText("Latitude: " + latitude);
        textLongitude.setText("Longitude: " + longitude);
        textAccuracy.setText("Accuracy: " + accuracy);
        textProvider.setText("Provider: " + provider);
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

    private Location requestUpdateFromProvider(String provider){

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (getApplicationContext().checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && getApplicationContext().checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
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
            locationManager.requestLocationUpdates(provider, 1000, 10, locationListener);
            location = locationManager.getLastKnownLocation(provider);
        }
        else{
            Toast.makeText(getApplicationContext(), provider + " is not enabled", Toast.LENGTH_SHORT).show();
        }
        return location;
    }

    LocationListener locationListener = new LocationListener() {
        @Override
        public void onLocationChanged(Location location) {

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
    };
}
