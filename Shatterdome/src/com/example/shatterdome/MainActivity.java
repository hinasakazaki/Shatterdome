package com.example.shatterdome;

import java.util.EventListener;

import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;

import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;
import android.view.Window;

import com.google.android.gms.maps.*;
import com.google.android.gms.maps.model.*;
import android.app.Activity;
import android.os.Bundle;


public class MainActivity extends Activity {

	 private static final String FIREBASE_URL = "https://shatterdome.firebaseio.com/";
	 private Firebase ref_lat;
	 private Firebase ref_lon;
	 private Firebase Geiger;
	 private double lon;
	 private double lat;
	 private Location here;
	 private GoogleMap googleMap;
     
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        try {
            // Loading map
            initializeMap();
 
        } catch (Exception e) {
            e.printStackTrace();
        }
       
//        requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
// 
//        setContentView(R.layout.activity_main);
// 
//        getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE, R.layout.window_title);
//
//      
        //First, get location for GPS from Mobile Device
        String provider = "";

        LocationManager loc = (LocationManager) getSystemService(LOCATION_SERVICE);
       
        if (loc.getAllProviders().size()> 0){
        	provider = loc.getAllProviders().get(0);
        	here = loc.getLastKnownLocation(provider); 
       }
        
        
        lon = here.getLongitude();
        lat = here.getLatitude();
        
        //Go to database
        ref_lat = new Firebase(FIREBASE_URL + "/GPS_lat");
        ref_lon = new Firebase(FIREBASE_URL + "/GPS_lon");
        

        //Get data
        
        ref_lat.addChildEventListener(new ChildEventListener() {

			@Override
			public void onCancelled(FirebaseError arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onChildAdded(DataSnapshot arg0, String arg1) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onChildChanged(DataSnapshot arg0, String arg1) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onChildMoved(DataSnapshot arg0, String arg1) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onChildRemoved(DataSnapshot arg0) {
				// TODO Auto-generated method stub
				
			}
        });
        
        // Write data to Firebase
        
        ref_lon.setValue(lon);
        ref_lat.setValue(lat);
        
        
       
        
        
       
        
        /**
         * function to load map. If map is not created it will create it for you
         * */
        private void initializeMap() {
            if (googleMap == null) {
                googleMap = ((MapFragment) getFragmentManager().findFragmentById(
                        R.id.map)).getMap();
     
                // check if map is created successfully or not
                if (googleMap == null) {
                    Toast.makeText(getApplicationContext(),
                            "Sorry! unable to create maps", Toast.LENGTH_SHORT)
                            .show();
                }
            }
        }
     
        @Override
        protected void onResume() {
            super.onResume();
            initiliazeMap();
        }
     
    
	}



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;

    }
    
}
