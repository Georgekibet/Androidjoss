package com.android.joss;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;

public class Snap extends Activity{
	
	ImageView snap1,snap2;
	TextView text,product1, product2;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.snap);
		
		
	
	
		//referencing our view elements from the xml files and assigning them
				//to the declared variables
				snap1=(ImageView) findViewById(R.id.imageView1);
				snap2=(ImageView) findViewById(R.id.imageView2);
				text=(TextView) findViewById(R.id.textView1);
				product1=(TextView) findViewById(R.id.productname);
				product2=(TextView) findViewById(R.id.textView2);
				
				
				//referencing our view elements from the xml files and assigning them
				//to the declared variables
               
				
				
				//setting onclick listener to the  imageView
				snap1.setOnClickListener(new OnClickListener() {
					
					@Override
					public void onClick(View v) {
						snap1.setImageResource(R.drawable.barcode);
						snap2.setImageResource(R.drawable.two_page);
						product1.setVisibility(BIND_ABOVE_CLIENT);
						
						text.setText("Take your second Snapshot");
						
					}
				});
			
				text.setOnClickListener(new OnClickListener() {
					
					@Override
					public void onClick(View v) {
					Intent i =new Intent(Snap.this, Compare.class);
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
