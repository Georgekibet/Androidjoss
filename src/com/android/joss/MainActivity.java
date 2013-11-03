package com.android.joss;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;

public class MainActivity extends Activity {

	//declaring our variables
	ImageView login,register;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		//referencing our view elements from the xml files and assigning them
		//to the declared variables
		login=(ImageView) findViewById(R.id.imageView3);
		register=(ImageView) findViewById(R.id.imageView2);
		
		//setting onclick listener to the login button
		login.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent k=new Intent(MainActivity.this, Prefernce.class);
				startActivity(k);
				
			}
		});
		
	//setting the onclick listener to the register button	
register.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent i=new Intent(MainActivity.this, Register.class);
				startActivity(i);
				
			}
		});
		
	}
	

	
	//Our method for the options menu
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
