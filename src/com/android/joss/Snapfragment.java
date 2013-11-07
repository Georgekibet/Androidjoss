package com.android.joss;

import com.actionbarsherlock.app.SherlockFragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;

public class Snapfragment extends SherlockFragment {
	
	//declare variables
	ImageView snap1,snap2;
	TextView text,product1, product2;
	
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.snap, container, false);
		
		

		//referencing our view elements from the xml files and assigning them
				//to the declared variables
				snap1=(ImageView) rootView.findViewById(R.id.imageView1);
				snap2=(ImageView) rootView.findViewById(R.id.imageView2);
				text=(TextView) rootView.findViewById(R.id.textView1);
				product1=(TextView) rootView.findViewById(R.id.productname);
				product2=(TextView) rootView.findViewById(R.id.textView2);
				
				
				//referencing our view elements from the xml files and assigning them
				//to the declared variables
               
				
				
				//setting onclick listener to the  imageView
				snap1.setOnClickListener(new OnClickListener() {
					
					@Override
					public void onClick(View v) {
						snap1.setImageResource(R.drawable.barcode);
						snap2.setImageResource(R.drawable.two_page);
						//product1.rootView.setVisibility(BIND_ABOVE_CLIENT);
						
						text.setText("Take your second Snapshot");
						
					}
				});
			
				text.setOnClickListener(new OnClickListener() {
					
					@Override
					public void onClick(View v) {
						// Create new fragment and transaction
						Fragment summary = new Summaryfragment();
						FragmentTransaction transaction = getFragmentManager().beginTransaction();

						// Replace whatever is in the fragment_container view with this fragment,
						// and add the transaction to the back stack
						transaction.replace(R.id.content_frame, summary);
						transaction.addToBackStack(null);

						// Commit the transaction
						transaction.commit();		
					}
				});
		return rootView;
	}

}
