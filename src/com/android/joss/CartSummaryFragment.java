package com.android.joss;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

import com.actionbarsherlock.app.SherlockFragment;
import com.actionbarsherlock.app.SherlockFragmentActivity;


public class CartSummaryFragment extends SherlockFragment  {
	Double protein, cholestrol,sugar,carbs,fat,fibre,vitaminA,potasium,sodium,calories;
	 SimpleCursorAdapter   dbAdapter;
	 ListViewAdapter adapter,adapterdec;
	 ListView list,list2;
	 
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.cartsummary_fragment_new, container, false);
		((SherlockFragmentActivity) getActivity()).getSupportActionBar().hide();
		
		//final DBAdapter db=new DBAdapter(getActivity());
		
		
		list=(ListView) rootView.findViewById(R.id.listInc);
		list2=(ListView) rootView.findViewById(R.id.listDEc);
		TextView dateTv=(TextView) rootView.findViewById(R.id.summary_date);
		
		//db.openDatabase();
		//int protein, cholestrol,sugar,carbs,fat;
		
		
		
		final SharedPreferences visibility = getActivity().getSharedPreferences(
		        "visibility", FragmentActivity.MODE_PRIVATE);
		
		final SharedPreferences History = getActivity().getSharedPreferences(
		        "history", FragmentActivity.MODE_PRIVATE);
	   
	
			
	    	//create a time and date instance to uniquely identify the summary period
	    	final Date cTime=new Date();
	    	final SimpleDateFormat sdf= new SimpleDateFormat("EEE,MMM d,yyyy");	
	    	String defoult=sdf.format(cTime);
	    	
	    	String date=History.getString("history_id",defoult );
	    					
	    	dateTv.setText(date);
		
		String[] names=returnNames(1,date);
		String[]   values=returnValues(1,date);
		
		String[] names2=returnNames(0,date);
		String[]   values2=returnValues(0,date);
		
		
		 adapter=new ListViewAdapter(getActivity(), names, values);
		 adapterdec=new ListViewAdapter(getActivity(), names2, values2);
		
		list.setAdapter(adapter);
		list2.setAdapter(adapterdec);

		
		return rootView;
		
		
	}
	
	
	//Method to return nutrient names
	protected String[] returnNames(int method, String date_time) {
		
		 
		
		String[] dates={},
				day={};
		
		DBAdapter db=new DBAdapter(getSherlockActivity());
		db.openDatabase();
		//Cursor c= db.getAllRecords();
		
		if (method==1) {
			
		
		final Cursor c= db.getcartSummaryINc(date_time);
		
		
		ArrayList<String> columnArray1 = new ArrayList<String>();
		//ArrayList<String> columnArray2 = new ArrayList<String>();
		for(c.moveToFirst(); c.moveToNext(); c.isAfterLast()) {
		    columnArray1.add(c.getString(0));
		    }
	     dates = (String[]) columnArray1.toArray(new String[columnArray1.size()]);
		}
		
		if (method==0) {

			
			
			final Cursor c= db.getcartSummaryDEc(date_time);
			
			
			ArrayList<String> columnArray1 = new ArrayList<String>();
			//ArrayList<String> columnArray2 = new ArrayList<String>();
			for(c.moveToFirst(); c.moveToNext(); c.isAfterLast()) {
			    columnArray1.add(c.getString(0));
			    }
		     dates = (String[]) columnArray1.toArray(new String[columnArray1.size()]);
			
		}
			
		return dates;
			
		}
	
	//Method to return nutrient values
	protected String[] returnValues(int method, String date_time) {
		
		
		String[] dates={},day={};
		
		DBAdapter db=new DBAdapter(getSherlockActivity());
		db.openDatabase();
		//Cursor c= db.getAllRecords();
		
		if (method==1) {
			
		
		final Cursor c= db.getcartSummaryINc(date_time);
		
		
		ArrayList<String> columnArray1 = new ArrayList<String>();
		//ArrayList<String> columnArray2 = new ArrayList<String>();
		for(c.moveToFirst(); c.moveToNext(); c.isAfterLast()) {
		    columnArray1.add(c.getString(1));
		    }
	     dates = (String[]) columnArray1.toArray(new String[columnArray1.size()]);
		}
		
		else if (method==0) {

			
			
			final Cursor c= db.getcartSummaryDEc(date_time);
			
			
			ArrayList<String> columnArray1 = new ArrayList<String>();
			//ArrayList<String> columnArray2 = new ArrayList<String>();
			for(c.moveToFirst(); c.moveToNext(); c.isAfterLast()) {
			    columnArray1.add(c.getString(1));
			    }
		     dates = (String[]) columnArray1.toArray(new String[columnArray1.size()]);
			
		}
			
		return dates;
			
		}


	
	
	private void movetoNextFrag() {
		Fragment newFragment = new DashboardFragment();
		FragmentTransaction transaction = getFragmentManager().beginTransaction();

		// Replace whatever is in the fragment_container view with this fragment,
		// and add the transaction to the back stack
		transaction.replace(R.id.content_frame, newFragment);
		transaction.addToBackStack(null);

		// Commit the transaction
		transaction.commit();
		
	}

}
