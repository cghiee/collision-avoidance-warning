package com.hackathon.collisionavoidance;

import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.Menu;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;

public class GpsService extends Service implements LocationListener {

    /**
     * Intent Definition
     */
    public static final String TRANSMIT_GPS_DATA = "com.hackathon.collisionavoidance.transmit_gps_data";

    /**
     * Intent Definition
     */
    public static final String GPS_DATA_KEY = "gpsData";

	private static final String TAG = "GPSservice";

    /**
     * Location Manager (for gps service)
     */
    protected LocationManager lm;

    public void onCreate()
    {

        LocationManager lm = (LocationManager) this
                .getSystemService(Context.LOCATION_SERVICE);

        lm.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, this);

    }

    public void onStartCommand()
    {
        // Begin Polling on 100ms intervals
        // Prepare BSM Packet
        // Emit GPS Data
    }

	@Override
	public IBinder onBind(Intent arg0) {
		// TODO Auto-generated method stub
		return null;
	}

    

    /**
     * Catch location change event
     */
    @Override
    public void onLocationChanged(Location location) {

        // @todo remove test data
        int lat = (int) (location.getLatitude());
        int lng = (int) (location.getLongitude());
        int t = (int)(location.getTime());
        float s;
        if ( location.hasSpeed() ) {
             s = (location.getSpeed());
        } else s = 10;

        String disp = "lat:" + lat + " long:" + lng + " time:" + t + " speed:" + s;
        Log.d(TAG, disp);
        Log.d(TAG, location.toString());
        // END TEST DATA

        // Transmit event data
        Intent intent = new Intent();
        intent.putExtra(this.GPS_DATA_KEY, location.toString());
        intent.setAction(this.TRANSMIT_GPS_DATA);
        sendBroadcast(intent);
    }

	@Override
	public void onProviderDisabled(String provider) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onProviderEnabled(String provider) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onStatusChanged(String provider, int status, Bundle extras) {
		// TODO Auto-generated method stub
		
	}
}