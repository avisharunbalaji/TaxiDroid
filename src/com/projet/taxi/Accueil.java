package com.projet.taxi;



import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;
import greendroid.app.GDActivity;
import greendroid.widget.ActionBarItem;
import greendroid.widget.ActionBarItem.Type;
public class Accueil extends GDActivity{
	 private final int LOCATE = 0;
	    private final int REFRESH = 1;
	    private final int SHARE = 2;
	 /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        setActionBarContentView(R.layout.accueil);
        initActionBar();
        ImageButton char2 = (ImageButton) this.findViewById(R.id.button22);
 char2.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View view) {
				
				Intent i = new Intent(getApplicationContext(), Reservation.class);
				startActivity(i);	
			}
 });
        ImageButton char1 = (ImageButton) this.findViewById(R.id.button11);
 char1.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View view) {
				// Launching create new product activity
				//startService(new Intent(Accueil.this, MonPremierService.class));
				  LayoutInflater factory = LayoutInflater.from(Accueil.this);
           	   final View alertDialogView = factory.inflate(R.layout.allert1, null);
           	   AlertDialog.Builder ad = new AlertDialog.Builder(alertDialogView.getContext());
           	   ad.setView(alertDialogView);
           	   ad.setTitle("localiser immediattement");
           	 ad.setPositiveButton("a partir dune adresse precise", new DialogInterface.OnClickListener() {
                 public void onClick(DialogInterface dialog, int arg1) {
                	  Intent i = new Intent(getApplicationContext(), ListeAdresseFavori.class);
     					startActivity(i);
                     dialog.dismiss();
                 }});
           	 ad.setNegativeButton("de ma position", new DialogInterface.OnClickListener(){
            	   public void onClick(DialogInterface dialog, int arg1) {
            		   startService(new Intent(Accueil.this, MonPremierService.class));
            	   }
            	   });
           	   
           	  //adresse=(EditText)alertDialogView.findViewById(R.id.EditText1);
         /*	  Button btn=(Button)alertDialogView.findViewById(R.id.button1);
              btn.setOnClickListener(new View.OnClickListener() {
				
				public void onClick(View v) {
					
					// TODO Auto-generated method stub
		           	   Log.d("coordonneessll","sara");

					Intent i = new Intent(getApplicationContext(), ListeAdresseFavori.class);
					startActivity(i);
					
					Log.d("test","test");
					
				}
			});
              Button btn2=(Button)alertDialogView.findViewById(R.id.button2);
              btn2.setOnClickListener(new View.OnClickListener() {
				
				public void onClick(View v) {
					// TODO Auto-generated method stub
					Log.d("test2","test");
					//Intent i = new Intent(getApplicationContext(), insererActivity.class);
					//startActivity(i);
					

					startService(new Intent(Accueil.this, MonPremierService.class));
					this.finalize();

				}
			});
           	  ad.setOnCancelListener(new DialogInterface.OnCancelListener() {
           	   public void onCancel(DialogInterface dialog) {
           	   // do nothing
           	   }
           	  });*/
           	  ad.show();
			}
		});
    }

	private void initActionBar() {
		// TODO Auto-generated method stub
		addActionBarItem(Type.Locate, LOCATE);
		addActionBarItem(Type.Refresh, REFRESH);
		addActionBarItem(Type.Share, SHARE);
	}
	@Override
    public boolean onHandleActionBarItemClick(ActionBarItem item, int position) {
	switch (item.getItemId()) {
	case LOCATE:
	    Toast.makeText(getApplicationContext(),
		    "Vous avez cliquez sur le bouton LOCATE",
		    Toast.LENGTH_SHORT).show();
	    break;

	case REFRESH:
	    Toast.makeText(getApplicationContext(),
		    "Vous avez cliquez sur le bouton REFRESH",
		    Toast.LENGTH_SHORT).show();
	    break;

	case SHARE:
	    Toast.makeText(getApplicationContext(),
		    "Vous avez cliquez sur le bouton SHARE",
		    Toast.LENGTH_SHORT).show();
	    break;
	default:
	    return super.onHandleActionBarItemClick(item, position);
	}

	return true;
    }
}