package com.android.joss;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.database.Cursor;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.actionbarsherlock.app.SherlockFragment;
import com.actionbarsherlock.app.SherlockFragmentActivity;

public class DashboardFragment extends SherlockFragment {
	
	Dialog start,carts;
	Button newcart,oldcart;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		    View rootView = inflater.inflate(R.layout.dashboard_new, container, false);
		    ((SherlockFragmentActivity) getActivity()).getSupportActionBar().hide();
		    
		    
	SharedPreferences checkout_visibility = getActivity().getSharedPreferences(
			        "checkout", FragmentActivity.MODE_PRIVATE);
	int see_checkout=checkout_visibility.getInt("check_o", 1);
	
	
	  //Animation move = AnimationUtils.loadAnimation(getActivity(), R.anim.move);
		
		start=new Dialog(getSherlockActivity());
		start.setTitle("Start Shoping ");
		start.setContentView(R.layout.startpopup);
		    
		Button scan=(Button) rootView.findViewById(R.id.compare);
		Button profile=(Button) rootView.findViewById(R.id.Profile);
		//Button profile2=(Button) rootView.findViewById(R.id.profile2);
		
		Button finish=(Button) rootView.findViewById(R.id.checkout);
		Button cartButton=(Button) rootView.findViewById(R.id.history);
		
		newcart=(Button) start.findViewById(R.id.new_shopingcart);
		oldcart=(Button) start.findViewById(R.id.previus);
		
		carts=new Dialog(getSherlockActivity());
		carts.setTitle("Soping Carts ");
		carts.setContentView(R.layout.carts);
		
		Button profile2=(Button) rootView.findViewById(R.id.profile2);
		Button History2=(Button) rootView.findViewById(R.id.History2);
		//Button profile2=(Button) rootView.findViewById(R.id.profile2);
		
	if (see_checkout==0) {
	
	profile2.setVisibility(View.VISIBLE);
	History2.setVisibility(View.VISIBLE);
	
	finish.setVisibility(View.INVISIBLE);
	profile.setVisibility(View.INVISIBLE);
	cartButton.setVisibility(View.INVISIBLE);
	
	//profile.setAnimation(move);
	//cartButton.setAnimation(move);
	
	}	
	else {
		finish.setVisibility(View.VISIBLE);
		profile.setVisibility(View.VISIBLE);
		cartButton.setVisibility(View.VISIBLE);
		
		profile2.setVisibility(View.INVISIBLE);
		History2.setVisibility(View.INVISIBLE);
		
	}
		
		
		
scan.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
			
			Fragment snapFragment = new Snapfragment();
			FragmentTransaction transaction = getFragmentManager().beginTransaction();
			transaction.replace(R.id.content_frame, snapFragment);
			transaction.addToBackStack(null);
			
			final SharedPreferences checkout_visibility = getActivity().getSharedPreferences(
			        "checkout", FragmentActivity.MODE_PRIVATE);
			Editor ed=checkout_visibility.edit();
			ed.putInt("check_o", 1);
			ed.commit();

			// Commit the transaction
			transaction.commit();
			
		
				
			/**	
				((SherlockFragmentActivity) getActivity()).getSupportActionBar().hide();
			//start.show();
		final SharedPreferences cart_id = getActivity().getSharedPreferences(
			        "cart_id", FragmentActivity.MODE_PRIVATE);
		   
		if (cart_id.getString("cart_key", "0").equals("0")) {
				
		    	//create a time and date instance to uniquely identify the particular cart
		    	final Date cTime=new Date();
		    	final SimpleDateFormat sdf= new SimpleDateFormat("EEE,MMM d,yyyy HH:mm:ss ");
		    	//final SimpleDateFormat sdf= new SimpleDateFormat("EEE,MMM d,yyyy");
		    	//final SimpleDateFormat timeOnly=new SimpleDateFormat(" HH:mm:ss ");		
		    					
		    	String date=sdf.format(cTime);
		    	//String time=sdf.format(cTime);
		    	Editor e=cart_id.edit();
				e.putString("date", date);
				e.putString("cart_key", "1");
				e.commit();
			}**/
			
			
				
			}
		});
		
  profile.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				start.dismiss();
				// Create new fragment and transaction
				Fragment profile = new Preferencesfragment();
				FragmentTransaction transaction = getFragmentManager().beginTransaction();

				// Replace whatever is in the fragment_container view with this fragment,
				// and add the transaction to the back stack
				transaction.replace(R.id.content_frame, profile);
				transaction.addToBackStack(null);

				// Commit the transaction
				transaction.commit();
				start.dismiss();
				
				
			}
		});
  profile2.setOnClickListener(new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			
			start.dismiss();
			// Create new fragment and transaction
			Fragment profile = new Preferencesfragment();
			FragmentTransaction transaction = getFragmentManager().beginTransaction();

			// Replace whatever is in the fragment_container view with this fragment,
			// and add the transaction to the back stack
			transaction.replace(R.id.content_frame, profile);
			transaction.addToBackStack(null);

			// Commit the transaction
			transaction.commit();
			start.dismiss();
			
			
		}
	});
  
  
  
newcart.setOnClickListener(new OnClickListener() {
	
	@Override
	public void onClick(View v) {
		((SherlockFragmentActivity) getActivity()).getSupportActionBar().show();
		if (isNetworkAvailable()) {
			
			
		}
		else Toast.makeText(getActivity(), "check your netwok", Toast.LENGTH_SHORT ).show();
		start.dismiss();
	}
});
	
oldcart.setOnClickListener(new OnClickListener() {
	
	@Override
	public void onClick(View v) {
		
		((SherlockFragmentActivity) getActivity()).getSupportActionBar().show();
		
		//Define and iniate dialogue to display the list of carts
		AlertDialog.Builder builder = new AlertDialog.Builder(getSherlockActivity());
		builder.setTitle("View your previous carts");
		
		ListView modeList = new ListView(getSherlockActivity());
		
		//String[] stringArray = new String[] { date, date,date,date,date,date };
		final String[]  stringArray=returnCarts();
		
		ArrayAdapter<String> modeAdapter = new ArrayAdapter<String>(getSherlockActivity(), 
		android.R.layout.simple_list_item_1, android.R.id.text1, stringArray);
		
		modeList.setAdapter(modeAdapter);
		builder.setView(modeList);
		final Dialog dialog = builder.create();
		dialog.setCanceledOnTouchOutside(true);
		
		dialog.show();
		
		//set a click listener for the items on the list
		modeList.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				
			
				
				//
		     TextView	 mTextView = (TextView) arg1;
			 String txtstr;
			 txtstr = mTextView.getText().toString();
			Toast.makeText(getActivity(), txtstr, Toast.LENGTH_LONG).show();
			
			//define a shared preference to store the arguments to be passed
			//for the display of cart details
			final SharedPreferences date_pref = getActivity().getSharedPreferences(
			        "history", FragmentActivity.MODE_PRIVATE);
			Editor e=date_pref.edit();
			e.putString("history_id", txtstr);
			e.commit();
			/**
			 * Move to Cartdetails fragment
			 * */
			// Create new fragment and transaction
			Fragment newFragment = new HistoryFragmet();
			FragmentTransaction transaction = getFragmentManager().beginTransaction();

			// Replace whatever is in the fragment_container view with this fragment,
			// and add the transaction to the back stack
			transaction.replace(R.id.content_frame, newFragment);
			transaction.addToBackStack(null);

			// Commit the transaction
			transaction.commit();
			
			dialog.dismiss();
			start.dismiss();
				
				
			}
		});
		

		
	}
});
cartButton.setOnClickListener(new OnClickListener() {
	
	@Override
	public void onClick(View v) {
		
		//createCartsDialogue();
		history();

		
	}
});

History2.setOnClickListener(new OnClickListener() {
	
	@Override
	public void onClick(View v) {
		
		//createCartsDialogue();
		
history();
		
	}
});


finish.setOnClickListener(new OnClickListener() {
	
	@Override
	public void onClick(View v) {
		movetoNextFrag();
		}
	
	
});



		
		return rootView;
	}
	 protected void history() {
		 Fragment newFragment = new HistoryFragmet();
			FragmentTransaction transaction = getFragmentManager().beginTransaction();

			// Replace whatever is in the fragment_container view with this fragment,
			// and add the transaction to the back stack
			transaction.replace(R.id.content_frame, newFragment);
			transaction.addToBackStack(null);

			// Commit the transaction
			transaction.commit();
		
	}
	private boolean isNetworkAvailable() {
			ConnectivityManager manager = (ConnectivityManager)
					getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
			NetworkInfo networkInfo = manager.getActiveNetworkInfo();
			
			boolean isAvailable = false;
			if (networkInfo != null && networkInfo.isConnected()) {
				isAvailable = true;
			}
			
			return isAvailable;
		}



	protected void movetoNextFrag() {
	
				// Create new fragment and transaction
				Fragment newFragment = new CartSummaryFragment();
				FragmentTransaction transaction = getFragmentManager().beginTransaction();

				// Replace whatever is in the fragment_container view with this fragment,
				// and add the transaction to the back stack
				transaction.replace(R.id.content_frame, newFragment);
				transaction.addToBackStack(null);

				// Commit the transaction
				transaction.commit();
				
				
					
		
	}



	protected String[] returnCarts() {
	String[] dates={},day={};
	
	DBAdapter db=new DBAdapter(getSherlockActivity());
	db.openDatabase();
	//Cursor c= db.getAllRecords();
	Cursor c= db.getProductnames();
	
	
	ArrayList<String> columnArray1 = new ArrayList<String>();
	//ArrayList<String> columnArray2 = new ArrayList<String>();
	for(c.moveToFirst(); c.moveToNext(); c.isAfterLast()) {
	    columnArray1.add(c.getString(0));
	    }
     dates = (String[]) columnArray1.toArray(new String[columnArray1.size()]);
	return dates;
		
	}
	/**
	 * 
	 */
	public void createCartsDialogue() {
		//Define and iniate dialogue to display the list of carts
		AlertDialog.Builder builder = new AlertDialog.Builder(getSherlockActivity());
		builder.setTitle("View your previous carts");
		
		ListView modeList = new ListView(getSherlockActivity());
		
		//String[] stringArray = new String[] { date, date,date,date,date,date };
		final String[]  stringArray=returnCarts();
		ArrayAdapter<String> modeAdapter = new ArrayAdapter<String>(getSherlockActivity(), 
				android.R.layout.simple_list_item_1, android.R.id.text1, stringArray);
		modeList.setAdapter(modeAdapter);
		builder.setView(modeList);
		final Dialog dialog = builder.create();
		dialog.setCanceledOnTouchOutside(true);
		
		dialog.show();
		
		//set a click listener for the items on the list
		modeList.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				
			
				
				//
		     TextView	 mTextView = (TextView) arg1;
			 String txtstr;
			 txtstr = mTextView.getText().toString();
			Toast.makeText(getActivity(), txtstr, Toast.LENGTH_LONG).show();
			
			//define a shared preference to store the arguments to be passed
			//for the display of cart details
			final SharedPreferences date_pref = getActivity().getSharedPreferences(
			        "date_store", FragmentActivity.MODE_PRIVATE);
			Editor e=date_pref.edit();
			e.putString("date", txtstr);
			e.commit();
			/**
			 * Move to Cartdetails fragment
			 * */
			// Create new fragment and transaction
			Fragment newFragment = new HistoryFragmet();
			FragmentTransaction transaction = getFragmentManager().beginTransaction();

			// Replace whatever is in the fragment_container view with this fragment,
			// and add the transaction to the back stack
			transaction.replace(R.id.content_frame, newFragment);
			transaction.addToBackStack(null);

			// Commit the transaction
			transaction.commit();
			
			dialog.dismiss();
				
				
			}
		});
	}

}
