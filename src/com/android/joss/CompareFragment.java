package com.android.joss;

import com.actionbarsherlock.app.SherlockFragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;

public class CompareFragment extends SherlockFragment {
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.compare, container, false);
		
		
		/*ImageView login=(ImageView) rootView.findViewById(R.id.exit);
		login.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// Create new fragment and transaction
				Fragment newFragment = new Fragment2();
				FragmentTransaction transaction = getFragmentManager().beginTransaction();

				// Replace whatever is in the fragment_container view with this fragment,
				// and add the transaction to the back stack
				transaction.replace(R.id.content_frame, newFragment);
				transaction.addToBackStack(null);

				// Commit the transaction
				transaction.commit();
				
				
				
			}
		});*/
		
		return rootView;
	}

}
