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
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import com.actionbarsherlock.app.SherlockFragment;
import com.actionbarsherlock.app.SherlockFragmentActivity;


public class HistoryFragmet extends SherlockFragment  {
	
	 ListView list;
	 
	 
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.catsummary_new, container, false);
		//((SherlockFragmentActivity) getActivity()).getSupportActionBar().show();
		
		//final DBAdapter db=new DBAdapter(getActivity());
		((SherlockFragmentActivity) getActivity()).getSupportActionBar().hide();
		
		
		list=(ListView) rootView.findViewById(R.id.listInc);
		
		ListAdapter adapt=new HistoryAdapterData(getActivity(), returnCarts());
				
		list.setAdapter(adapt);

		
		return rootView;
		
		
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
