package com.projet.taxi;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;

public class TaxiDroidActivity extends Activity {
    /** Called when the activity is first created. */
	private final int SPLASH_DISPLAY_LENGTH = 3000;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
    }
    @Override
	protected void onResume()
	{
		super.onResume();
		SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(this);
		// Obtain the sharedPreference, default to true if not available
		boolean isSplashEnabled = sp.getBoolean("isSplashEnabled", true);

		if (isSplashEnabled)
		{
			new Handler().postDelayed(new Runnable()
			{
				public void run()
				{
					//Finish the splash activity so it can't be returned to.
					TaxiDroidActivity.this.finish();
					// Create an Intent that will start the main activity.
					Intent mainIntent = new Intent(TaxiDroidActivity.this, Accueil.class);
					TaxiDroidActivity.this.startActivity(mainIntent);
				}
			}, SPLASH_DISPLAY_LENGTH);
		}
		else
		{
			// if the splash is not enabled, then finish the activity immediately and go to main.
			finish();
			Intent mainIntent = new Intent(TaxiDroidActivity.this, TaxiDroidActivity.class);
			TaxiDroidActivity.this.startActivity(mainIntent);
		}
	}
}