package com.projet.taxi;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;
 
public class MonPremierService extends Service
{
	JSONParser jsonParser = new JSONParser();

	// single product url
	private static final String url_product_inserer = "http://taxidroid.hebergratuit.com/taxidroid/client_coordonnees.php";
	private static final String TAG_SUCCESS = "success";
	private static final String TAG_CONTACT = "contact";

 private LocationManager            locationMgr             = null;
 private LocationListener       onLocationChange    = new LocationListener()
 {
 public void onStatusChanged(String provider, int status, Bundle extras)
 {
 }
 

 public void onProviderEnabled(String provider)
 {
 }
 

 public void onProviderDisabled(String provider)
 {
 }
 

 public void onLocationChanged(Location location)
 {
 
 Double latitude = location.getLatitude();
 Double longitude = location.getLongitude();
 Log.d("latitude", latitude.toString());
 Log.d("longitude", longitude.toString());

 Toast.makeText(getBaseContext(),
 "Voici les coordonnées de votre téléphone : " + latitude + " " + longitude,
 Toast.LENGTH_LONG).show();
 int success;
	try {
		List<NameValuePair> params= new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("lat", latitude.toString()));
		params.add(new BasicNameValuePair("longi", longitude.toString()));
		params.add(new BasicNameValuePair("id", String.valueOf(1)));
		JSONObject json = jsonParser.makeHttpRequest(url_product_inserer, "POST", params);
		Log.d("Delete Product", json.toString());
		// json success tag
		success = json.getInt(TAG_SUCCESS);
		if (success == 1) {
			JSONArray productObj = json
					.getJSONArray(TAG_CONTACT);
		
		}
	} catch (JSONException e) {
		e.printStackTrace();
	}
 stopSelf();
 }
 };
 
 @Override
 public IBinder onBind(Intent arg0)
 {
 return null;
 }
 
 @Override
 public void onCreate()
 {
	 Log.d("latitude", "latitude");
 locationMgr = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
 locationMgr.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 10000,
 0, onLocationChange);
 locationMgr.requestLocationUpdates(LocationManager.GPS_PROVIDER, 10000, 0,
 onLocationChange);
 super.onCreate();
 }
 

 
 @Override
 public void onDestroy()
 {
 super.onDestroy();
 locationMgr.removeUpdates(onLocationChange);
 }
}