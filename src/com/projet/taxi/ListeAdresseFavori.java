package com.projet.taxi;


import greendroid.app.ActionBarActivity;
import greendroid.app.GDListActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;




import android.app.Activity;
import android.app.ListActivity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;

public class ListeAdresseFavori extends ListActivity{
	// Progress Dialog
		private ProgressDialog pDialog;
		String Serie;
		// Creating JSON Parser object
		JSONParser jParser = new JSONParser();

		ArrayList<HashMap<String, String>> contactList;

		// url to get all products list
		private static String url_adresse_favori = "http://taxidroid.hebergratuit.com/taxidroid/adresse_favori.php";
		private static String url_adresse_client = "http://taxidroid.hebergratuit.com/taxidroid/client_coordonnees.php";

		private static final String TAG_SUCCESS = "success";
		private static final String TAG_ADRESSE = "adresse";
		private static final String TAG_IDADRESSE = "idadresse";
		private static final String TAG_IDCLIENT = "id_client";
		private static final String TAG_NOM = "nom_adresse";
		private static final String TAG_LATITUDE = "latitude";
		private static final String TAG_LONGITUDE = "longitude";

		private static final String TAG_NUM = "numtel";
		private static final String TAG_NUMSERIETEL = "numSerieTel";
		String latitude;
		String logitude;

		// products JSONArray
		JSONArray adresse = null;

		@Override
		public void onCreate(Bundle savedInstanceState) {
			super.onCreate(savedInstanceState);
			setContentView(R.layout.adresse_favori);

			contactList = new ArrayList<HashMap<String, String>>();

			// Loading products in Background Thread
			new LoadAdresse().execute();
			ListView lv = getListView();

			// on seleting single product
			// launching Edit Product Screen
			lv.setOnItemClickListener(new OnItemClickListener() {

				public void onItemClick(AdapterView<?> parent, View view,
						int position, long id) {
					String pid=((TextView) view.findViewById(R.id.pid)).getText().toString();
					
					int success;
					try {
						List<NameValuePair> params= new ArrayList<NameValuePair>();
						params.add(new BasicNameValuePair("lat", latitude));
						params.add(new BasicNameValuePair("longi", logitude));
						params.add(new BasicNameValuePair("id", String.valueOf(1)));
						JSONObject json = jParser.makeHttpRequest(url_adresse_client, "POST", params);
						finish();
						Log.d("Delete Product", json.toString());
						// json success tag
						success = json.getInt(TAG_SUCCESS);
						if (success == 1) {
							Log.d("Delete Product", json.toString());

						}
					} catch (JSONException e) {
						e.printStackTrace();
					}// sending pid to next activity
				//	in.putExtra("TAG_IDADRESSE", pid);
					//in.putExtra("TAG_NUM", tel);
				//	in.putExtra("TAG_NUMSERIETEL", numserie);
					
					// starting new activity and expecting some response back
					//startActivityForResult(in, 100);
				}
			});
			
		}
		class LoadAdresse extends AsyncTask<String, String, String> {

			/**
			 * Before starting background thread Show Progress Dialog
			 * */
			@Override
			protected void onPreExecute() {
				super.onPreExecute();
				pDialog = new ProgressDialog(ListeAdresseFavori.this);
				pDialog.setMessage("Please wait...");
				pDialog.setIndeterminate(false);
				pDialog.setCancelable(false);
				pDialog.show();
			}

			/**
			 * getting All products from url
			 * */
			protected String doInBackground(String... args) {
				int success;

				try {
					// Building Parameters
					
					List<NameValuePair> params = new ArrayList<NameValuePair>();
					params.add(new BasicNameValuePair("id", "1"));
					// getting JSON string from URL
					JSONObject json = jParser.makeHttpRequest(url_adresse_favori, "GET", params);
					// Check your log cat for JSON reponse
					//Log.d("Adresse favori: ", json.toString());
					// Checking for SUCCESS TAG
					Log.d("adresse","adresse");

				success = json.getInt(TAG_SUCCESS);

					if (success == 1) {
						
						// products found
						// Getting Array of Products
						adresse = json.getJSONArray(TAG_ADRESSE);

						// looping through All Products
						for (int i = 0; i < adresse.length(); i++) {
							JSONObject c = adresse.getJSONObject(i);

							// Storing each json item in variable
							String id = c.getString(TAG_IDCLIENT);
							String name = c.getString(TAG_NOM);
							latitude=c.getString(TAG_LATITUDE);
							logitude=c.getString(TAG_LONGITUDE);
							Log.d("hgjkdl ", id +" "+name);

							// creating new HashMap
							HashMap<String, String> map = new HashMap<String, String>();

							// adding each child node to HashMap key => value
							map.put(TAG_IDCLIENT, id);
							map.put(TAG_NOM, name);

							// adding HashList to ArrayList
							contactList.add(map);
						}
					} else {
						// n
						finish();
					}
				} catch (JSONException e) {
					e.printStackTrace();
				}

				return null;
			}

			/**
			 * After completing background task Dismiss the progress dialog
			 * **/
			protected void onPostExecute(String file_url) {
				pDialog.dismiss();
				// updating UI from Background Thread
				runOnUiThread(new Runnable() {
					public void run() {
						/**
						 * Updating parsed JSON data into ListView
						 * */
						ListAdapter adapter = new SimpleAdapter(
								ListeAdresseFavori.this, contactList,
								R.layout.list_item, new String[] { TAG_IDCLIENT,
										TAG_NOM},
								new int[] { R.id.pid, R.id.name });
						// updating listview
						setListAdapter(adapter);
					}
				});


			}

		}
		public String Serie(){
	        TelephonyManager tm = (TelephonyManager) getSystemService(TELEPHONY_SERVICE);
	return tm.getSimSerialNumber();
	    
	    }

}