package com.example.ecommerce.project;

import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;
import android.widget.Toast;

public class myLocationListener implements LocationListener {
    private Context activitycontext ;

    public myLocationListener(Context con )
    {

        activitycontext=con ;

    }

    @Override
    public void onLocationChanged(Location location) {
        Toast.makeText(activitycontext,  location.getLatitude() +"  "+ location.getLongitude(), Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onStatusChanged(String s, int i, Bundle bundle) {

    }

    @Override
    public void onProviderEnabled(String s) {
        Toast.makeText(activitycontext, "GPS Enabled", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onProviderDisabled(String s) {
        Toast.makeText(activitycontext, "GPS Disabled", Toast.LENGTH_SHORT).show();

    }
}
