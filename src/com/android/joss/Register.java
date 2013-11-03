package com.android.joss;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;

public class Register extends Activity {

	private ImageView register;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.register);
		
		register=(ImageView) findViewById(R.id.imageView1);
		register.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// action when the button is clicked
				Intent i=new Intent(Register.this, Prefernce.class);
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
