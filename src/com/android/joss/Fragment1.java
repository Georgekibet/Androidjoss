package com.android.joss;

import com.actionbarsherlock.app.SherlockFragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;

public class Fragment1 extends SherlockFragment {
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment1, container, false);
		
		/*
		ImageView login=(ImageView) inflater.inflate(R.id.imageView3, null);
		login.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent k=new Intent();
				k.setClass(getActivity(), Prefernce.class);
				startActivity(k);
				
			}
		});*/
		
		return rootView;
	}

}
