package com.android.joss;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;

public class Prefernce extends Activity{
	
	ImageView save;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.preference);
		
		
		
		//referencing our view elements from the xml files and assigning them
				//to the declared variables
				save=(ImageView) findViewById(R.id.imageView1);
				
				
				//setting onclick listener to the save button
				save.setOnClickListener(new OnClickListener() {
					
					@Override
					public void onClick(View v) {
						Intent i=new Intent(Prefernce.this, Snap.class);
						startActivity(i);
						
					}
				});
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		
		
		return true;
	}

}
