package com.example.shatterdome;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;

import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;
import android.view.Window;

public class MainActivity extends Activity {

	 private static final String FIREBASE_URL = "https://shatterdome.firebaseio.com/";
	 private Firebase ref_lat;
	 private Firebase ref_lon;
	 private Firebase Geiger;
	 private double lon;
	 private double lat;
	 private Location here;
	 
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
 
       
        requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
 
        setContentView(R.layout.activity_main);
 
        getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE, R.layout.window_title);

      
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
        

        // Write data to Firebase
        
        ref_lon.setValue(lon);
        ref_lat.setValue(lat);
        
        
  
     
    
	}



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;

    }
    
}
