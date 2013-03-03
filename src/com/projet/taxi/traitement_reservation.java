package com.projet.taxi;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;


public class traitement_reservation extends Activity{
	
	String id = ListeAdresse.pid;
	String latitude=ListeAdresse.latitude;
	String longitude=ListeAdresse.logitude;
	String day = String.valueOf(dateActivity.day);
	String month = String.valueOf(dateActivity.month);
	String year = String.valueOf(dateActivity.year);
	String hour = String.valueOf(dateActivity.hour);
	String min = String.valueOf(dateActivity.min);
	private static final String TAG_SUCCESS = "success";
	private ProgressDialog pDialog;
	JSONParser jParser = new JSONParser();
	JSONArray contact = null;
	private static String url_reservation = "http://taxidroid.hebergratuit.com/taxidroid/reservation.php";
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	   // contactList = new ArrayList<HashMap<String, String>>();
	    new LoadAdresse().execute();
			}
	
	
	
		
		class LoadAdresse extends AsyncTask<String, String, String> {
			protected void onPreExecute() {
				pDialog = new ProgressDialog(traitement_reservation.this);
				pDialog.setMessage("Please wait...");
				pDialog.setIndeterminate(false);
				pDialog.setCancelable(false);
				pDialog.show();
				Log.d("id",id+"");
				Log.d("jour",day+"");
				Log.d("mois", month +" ");
				Log.d("annee", year +" ");
				Log.d("minuteD", min +" ");
				Log.d("heureD", String.valueOf(hour)+"");
				
				
			}
			protected String doInBackground(String... args) {
				int success;
		       
				try {
					// Building Parameters
				List<NameValuePair> params = new ArrayList<NameValuePair>();
					params.add(new BasicNameValuePair("id", id ));
					params.add(new BasicNameValuePair("lat", latitude ));
					params.add(new BasicNameValuePair("longi", longitude ));

					params.add(new BasicNameValuePair("day", day ));
					params.add(new BasicNameValuePair("month", month ));
					params.add(new BasicNameValuePair("year", year ));
					params.add(new BasicNameValuePair("hour", hour ));
					params.add(new BasicNameValuePair("min", min ));

					
					// getting JSON string from URL
					JSONObject json = jParser.makeHttpRequest(url_reservation, "GET", params);
					
					// Check your log cat for JSON reponse
					Log.d("All Products: ", json.toString());
					// Checking for SUCCESS TAG
					success = json.getInt(TAG_SUCCESS);
				finish();
					
				} catch (JSONException e) {
					e.printStackTrace();
				}

				return null;
			}
			protected void onPostExecute(String file_url) {
				pDialog.dismiss();
				

			}
		}
	
}
