package com.android.joss;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;



public class CompareFormatAdapter extends BaseAdapter {

	// Declare Variables
	Context context;
	Double[] nutr1;
	String[] nutrName;
	Double[] nutr2;
	Integer[] pref;
	
	int[] mIcon;
	LayoutInflater inflater;

	public CompareFormatAdapter(Context context, Double[] nutr1,String[] nutrName,
			Double[] nutr2,Integer[] preference) {
		this.context = context;
		this.nutr1=nutr1;
		this.nutrName = nutrName;
		this.nutr2 = nutr2;
		this.pref=preference;
		
	}

	
	@Override
	public int getCount() {
		return nutrName.length;
	}
	

	@Override
	public Object getItem(int position) {
		return nutrName[position];
	}
	

	@Override
	public long getItemId(int position) {
		return position;
	}
	

public View getView(int position, View convertView, ViewGroup parent) {
		// Declare Variables
	    TextView nutrient1;
		TextView nutrientname;
		TextView nutrient2;
		

		inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View itemView = inflater.inflate(R.layout.compare_adapter, parent,
				false);

		// Locate the TextViews in compare adapter
		
		nutrient1=(TextView) itemView.findViewById(R.id.nutr1label);
		nutrientname = (TextView) itemView.findViewById(R.id.nutrlabel);
		nutrient2 = (TextView) itemView.findViewById(R.id.nutr2label);

		
		nutrient1.setText(String.valueOf(nutr1[position]));
		nutrientname.setText(nutrName[position]);
		nutrient2.setText(String.valueOf(nutr2[position]));

		// Set the results into TextViews
		
		    
		try {
			
		
		if (nutr1[position]>nutr2[position]&&pref[position]==1) {
			nutrient1.setBackgroundResource(R.color.android_green);
		}
		else if (nutr1[position]>nutr2[position]&&pref[position]==0) {
			nutrient2.setBackgroundResource(R.color.android_green);
		}
		else if (nutr1[position]<nutr2[position]&&pref[position]==1) {
			nutrient2.setBackgroundResource(R.color.android_green);
		}
		else if (nutr1[position]<nutr2[position]&&pref[position]==0) {
			nutrient1.setBackgroundResource(R.color.android_green);
		}
		
		
		
		//if (Double.parseDouble(nutrValue[position])>1.0) {
			
			//nutrientname.setBackgroundResource(R.color.android_red);
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		

		return itemView;
	}	


}
