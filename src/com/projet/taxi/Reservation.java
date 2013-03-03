package com.projet.taxi;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Reservation extends Activity{
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        setContentView(R.layout.acceuil2);
        Button char2 = (Button) this.findViewById(R.id.button18);
        char2.setOnClickListener(new View.OnClickListener() {
       			
       			public void onClick(View view) {
       				
       				Intent i = new Intent(getApplicationContext(), ListeAdresse.class);
       				startActivity(i);	
       			}
        });
        Button char3 = (Button) this.findViewById(R.id.button28);
        char3.setOnClickListener(new View.OnClickListener() {
       			
       			public void onClick(View view) {
       				
       				Intent i = new Intent(getApplicationContext(), dateActivity.class);
       				startActivity(i);	
       			}
        });
        Button char4 = (Button) this.findViewById(R.id.button38);
        char4.setOnClickListener(new View.OnClickListener() {
       			
       			public void onClick(View view) {
       				
       				Intent i = new Intent(getApplicationContext(), traitement_reservation.class);
       				startActivity(i);	
       				finish();
       			}
        });
	
	}
}
