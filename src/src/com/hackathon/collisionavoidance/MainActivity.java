package com.hackathon.collisionavoidance;

import android.util.Log;
import android.os.Bundle;
import android.view.Menu;
import android.app.Activity;
import android.content.Intent;
import android.content.Context;
import android.widget.TextView;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;

public class MainActivity extends Activity implements LocationListener {

	/**
	 * Gps Service Thread
	 */
	protected GpsService gpsService;

	/**
	 * Wifi Service Thread
	 */
	protected WifiService wifiService;

	/**
	 * Identify State of GpsService
	 */
	protected boolean gpsServiceEnabled;

	/**
	 * Identify State of WifiService
	 */
	protected boolean wifiServiceEnabled;

	/**
	 * Constant activity tag
	 */
	public static final String TAG = "MainActivity";

	/**
	 * Text View (for Activity)
	 */
	protected TextView tv;

	/**
	 * Location Manager (for gps service)
	 */
	protected LocationManager lm;

	/**
	 * ???
	 */
	protected Context mCtx;

	/**
	 * Executed on activity launch
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		// Set Default States
		this.gpsServiceEnabled = false;
		this.wifiServiceEnabled = false;

		// @todo add start button
		// @todo add bindings to allow activity communication
		/**
		 *  Move these to start button click event
		 */
		Intent gpsIntent = new Intent(this, GpsService.class);
		if (this.gpsServiceEnabled != true) {
			startService(gpsIntent);
			this.gpsServiceEnabled = true;
		} else {
			stopService(gpsIntent);
			this.gpsServiceEnabled = false;
		}

		/**
		 *  Move these to start button click event
		 */
		Intent wifiIntent = new Intent(this, WifiService.class);
		if (this.wifiServiceEnabled != true) {
			startService(wifiIntent);
			this.wifiServiceEnabled = true;
		} else {
			stopService(wifiIntent);
			this.wifiServiceEnabled = false;
		}
		// @todo logic to swap text/color to toggle start to stop button

		this.network_enabled = false;

		LocationManager lm = (LocationManager) this
				.getSystemService(Context.LOCATION_SERVICE);

		lm.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, this);
//
//		Log.d(TAG, "here");
//		gpsServiceEnabled = lm.isProviderEnabled(LocationManager.GPS_PROVIDER);
//		network_enabled = lm
//				.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
//
//		Log.d(TAG, "here2");
//
//		Location net_loc = null, gps_loc = null, finalLoc = null;
//
//		if (gpsServiceEnabled) {
//			gps_loc = lm.getLastKnownLocation(LocationManager.GPS_PROVIDER);
//			Log.d(TAG, "here4");
//		}
//		if (network_enabled) {
//			net_loc = lm.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
//			Log.d(TAG, "here5");
//		}
//
//		if (gps_loc != null && net_loc != null) {
//
//			if (gps_loc.getAccuracy() >= net_loc.getAccuracy())
//				finalLoc = gps_loc;
//			else
//				finalLoc = net_loc;
//
//			// I used this just to get an idea (if both avail, its upto you
//			// which you want to take as I taken location with more accuracy)
//		} else {
//
//			if (gps_loc != null) {
//				finalLoc = net_loc;
//			} else if (net_loc != null) {
//				finalLoc = gps_loc;
//			}
//		}
//		Log.d(TAG, "here7");
//		if (finalLoc != null) {
//			Log.d(TAG, finalLoc.toString());
//		}
		setContentView(R.layout.activity_main);
	}

	/**
	 * Create Options Menu
	 */
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	/**
	 * Catch location change event
	 */
	@Override
	public void onLocationChanged(Location location) {
		// TODO Auto-generated method stub
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

	}

	/**
	 * Catch provider disabled
	 */
	@Override
	public void onProviderDisabled(String provider) {
		// TODO Auto-generated method stub

	}

	/**
	 * Catch provider enabled
	 */
	@Override
	public void onProviderEnabled(String provider) {
		// TODO Auto-generated method stub

	}

	/**
	 * Catch status change
	 */
	@Override
	public void onStatusChanged(String provider, int status, Bundle extras) {
		// TODO Auto-generated method stub

	}
}