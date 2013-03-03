package com.projet.taxi;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TimePicker;

public class dateActivity extends Activity {
	private DatePicker picker;
	private TimePicker picker2;
	private Button btn;
	JSONParser jParser = new JSONParser();
	static int day;
	static int month;
	static int year;
	static int hour;
	static int min;
	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.date);
        picker = (DatePicker) findViewById(R.id.datePicker1);
        picker2 = (TimePicker) findViewById(R.id.timePicker1);
        btn = (Button) findViewById(R.id.button1);
		
		// view products click event
	btn.setOnClickListener(new View.OnClickListener(){
		
		public void onClick(View view) {
			day = picker.getDayOfMonth();
	        month = picker.getMonth()+1;
	        year = picker.getYear();
	        hour=picker2.getCurrentHour();
	        min=picker2.getCurrentMinute();
	   
	        Log.d("day",String.valueOf(day));
	        Log.d("month",String.valueOf(month));
	        Log.d("year",String.valueOf(year));
	        Log.d("hour",String.valueOf(hour));
	        Log.d("min",String.valueOf(min));
			
			finish();



		}
	});
    }

}

