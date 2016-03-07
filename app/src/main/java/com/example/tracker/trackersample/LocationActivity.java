package com.example.tracker.trackersample;

import android.app.Activity;
import android.content.Context;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.location.LocationProvider;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

/**
 * Created by MOON on 3/7/16.
 */
public class LocationActivity extends Activity {

	LocationManager locManager; // 위치 정보 프로바이더

	TextView textView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_location);

		initView();

		// LocationManager의 인스턴스와 레이아웃 내 위짓의 인스턴스를 받아옵니다.
		locManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
	}

	private void initView() {
		textView = (TextView) findViewById(R.id.result);
	}

	public void onResume() {
		super.onResume();
		String locProv = locManager.getBestProvider(getCriteria(), true);
		locManager.requestLocationUpdates(locProv, 3000, 3, mLocListener);

		textView.setText("Location Service Start");

		String locationProvider = LocationManager.GPS_PROVIDER;
		Location lastKnownLocation = locManager.getLastKnownLocation(locationProvider);
		if (lastKnownLocation != null) {
			double lng = lastKnownLocation.getLatitude();
			double lat = lastKnownLocation.getLatitude();
			Log.d("Main", "longtitude=" + lng + ", latitude=" + lat);
		}
	}

	public static Criteria getCriteria() {
		Criteria criteria = new Criteria();
		criteria.setAccuracy(Criteria.ACCURACY_FINE);
		criteria.setAltitudeRequired(true);
		criteria.setBearingRequired(true);
		criteria.setSpeedRequired(true);
		criteria.setCostAllowed(true);
		criteria.setPowerRequirement(Criteria.POWER_HIGH);
		return criteria;
	}

	public void onPause() {
		super.onPause();
		locManager.removeUpdates(mLocListener);
		textView.setText("Location Service Stop");
	}

	LocationListener mLocListener = new LocationListener() {
		public void onLocationChanged(Location location) {
			textView.setText("Lat: " + location.getLatitude() + " / Lng: " + location.getLongitude());
		}

		public void onProviderDisabled(String provider) {
			textView.setText("Provider Disabled");
		}

		public void onProviderEnabled(String provider) {
			textView.setText("Provider Enabled");
		}

		public void onStatusChanged(String provider, int status, Bundle extras) {
			switch (status) {
				case LocationProvider.OUT_OF_SERVICE:
					textView.setText("Provider Out of Service");
					break;
				case LocationProvider.TEMPORARILY_UNAVAILABLE:
					textView.setText("Provider Temporarily Unavailable");
					break;
				case LocationProvider.AVAILABLE:
					textView.setText("Provider Available");
					break;
			}
		}
	};
}
