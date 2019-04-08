package com.example.ecommerce.project;

import android.content.Context;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.List;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    EditText addresstext ;
    LocationManager locationManager ;
    myLocationListener locationListener ;
    Button  getlocation ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_maps);
        addresstext = (EditText)findViewById(R.id.edittext1) ;
        getlocation = (Button)findViewById(R.id.button1) ;
        locationListener = new myLocationListener(getApplicationContext()) ;
        locationManager =(LocationManager)getSystemService(Context.LOCATION_SERVICE) ;

        try {
            locationManager.requestLocationUpdates(LocationManager.PASSIVE_PROVIDER,6000,0,locationListener);
            //locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,6000,0,locationListener);

        }
        catch (SecurityException ex)
        {

            Toast.makeText(this, "you are not allowed to access the current location", Toast.LENGTH_SHORT).show();
        }
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(30.04441960,31.235711600),8));


        getlocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mMap.clear();
                Geocoder geocoder = new Geocoder(getApplicationContext()) ;
                List<Address> addresslist ;
                Location loc = null ;
                try {
                    // loc = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER) ;
                    loc = locationManager.getLastKnownLocation(LocationManager.PASSIVE_PROVIDER) ;
                    //loc = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER) ;


                }
                catch (SecurityException x)
                {
                    Toast.makeText(MapsActivity.this, "you did not allow to access curent location", Toast.LENGTH_SHORT).show();
                }
                if (loc!=null)
                {

                    LatLng myposition = new LatLng(loc.getLatitude(),loc.getLongitude()) ;

                    try{
                        addresslist = geocoder.getFromLocation(myposition.latitude,myposition.longitude,1) ;

                        if (! addresslist.isEmpty())
                        {
                            String address= "" ;

                            for (int i =0 ; i<= addresslist.get(0).getMaxAddressLineIndex();i++)
                                address += addresslist.get(0).getAddressLine(i) + " , " ;
                            mMap.addMarker( new MarkerOptions().position(myposition).title("My Location").snippet(address)).setDraggable(true);
                            addresstext.setText(address);

                        }

                    }
                    catch (IOException e)
                    {
                        mMap.addMarker(new MarkerOptions().position(myposition).title("My Location")) ;
                    }
                    mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(myposition,15));

                }
                else
                {
                    Intent intent = new Intent(MapsActivity.this , mycart.class);
                    startActivity(intent);
                }
//                    Toast.makeText(MapsActivity.this, "please wait untill your position is determined", Toast.LENGTH_SHORT).show();

            } });
        mMap.setOnMarkerDragListener(new GoogleMap.OnMarkerDragListener() {
            @Override
            public void onMarkerDragStart(Marker marker) {

            }

            @Override
            public void onMarkerDrag(Marker marker) {

            }

            @Override
            public void onMarkerDragEnd(Marker marker) {

                Geocoder coder = new Geocoder(getApplicationContext()) ;
                List<Address> addressList ;
                try
                {

                    addressList = coder.getFromLocation(marker.getPosition().latitude,marker.getPosition().longitude,1) ;
                    if(!addressList.isEmpty())
                    {
                        String address= "" ;

                        for (int i =0 ; i<= addressList.get(0).getMaxAddressLineIndex();i++)
                            address += addressList.get(0).getAddressLine(i) + " , " ;
                        addresstext.setText(address);
                    }
                    else
                    {
                        Toast.makeText(MapsActivity.this, "no address for this location", Toast.LENGTH_SHORT).show();
                        addresstext.getText().clear();
                    }
                }
                catch (IOException e)
                {
                    Toast.makeText(MapsActivity.this, "can't get address check your network", Toast.LENGTH_SHORT).show();

                }

            }
        });

        // Add a marker in Sydney and move the camera
        /*LatLng sydney = new LatLng(-34, 151);
        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));*/

    }

}
