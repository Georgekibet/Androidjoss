package com.android.joss;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;



public class ListViewAdapter extends BaseAdapter {

	// Declare Variables
	Context context;
	String[] nutrName;
	String[] nutrValue;
	int[] mIcon;
	LayoutInflater inflater;

	public ListViewAdapter(Context context, String[] Name, String[] value) {
		this.context = context;
		this.nutrName = Name;
		this.nutrValue = value;
		
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
		TextView nutrientname;
		TextView nutrientValue;
		

		inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View itemView = inflater.inflate(R.layout.cart_items, parent,
				false);

		// Locate the TextViews in drawer_list_item.xml
		nutrientname = (TextView) itemView.findViewById(R.id.nutrient_name);
		nutrientValue = (TextView) itemView.findViewById(R.id.nutrient_value);

		
		

		// Set the results into TextViews
		nutrientname.setText(nutrName[position]);
		nutrientValue.setText(nutrValue[position]);
		
		/*if (Double.parseDouble(nutrValue[position])>1.0) {
			
			nutrientname.setBackgroundResource(R.color.android_red);
		}*/

		

		return itemView;
	}	


}
