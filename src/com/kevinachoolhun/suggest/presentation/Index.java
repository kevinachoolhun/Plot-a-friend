package com.kevinachoolhun.suggest.presentation;

import com.kevinachoolhun.suggest.R;
import com.kevinachoolhun.suggest.R.id;
import com.kevinachoolhun.suggest.R.layout;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Index extends Activity{
	
	Button btnSuggest = null;
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.index);
       
               
        btnSuggest = (Button)findViewById(R.id.btnSuggest);
        btnSuggest.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				displayGoogleMaps();
			}
		});
        
	}
	
	 private void displayGoogleMaps() {
			// TODO Auto-generated method stub
			Intent i = new Intent(this, MapLocation.class);
			//startActivityForResult(i, 0);
			startActivity(i);
		}
        
}
