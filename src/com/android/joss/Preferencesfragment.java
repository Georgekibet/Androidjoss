package com.android.joss;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.actionbarsherlock.app.SherlockFragment;
import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.android.joss.DBAdapter.DBHelper;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.Toast;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.RadioGroup;

public class Preferencesfragment extends SherlockFragment {
	
	CheckBox proteinsCb,carbsCb,sugarCb,fatCb,caloresCb,fibreCb,vitaminAcB,
	             cholestrolCb,sodiumCB,potasiumCB;
	RadioGroup proteinradio,carbsRg,fatRg,caloriesRg,fibreRg,vitaminArG,sugar
	              ,cholestrolRg,sodiumRG,potasiumRG;
	Button revert,save;
	
	//create a shared preference to store the preferences selected by the user
	
	
	
	
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.preference, container, false);
		((SherlockFragmentActivity) getActivity()).getSupportActionBar().hide();
		//checkboxes
		
		
		final SharedPreferences visibility_check = getActivity().getSharedPreferences(
		        "visibility", FragmentActivity.MODE_PRIVATE); 
		
		
	final   int proteinVisibility=visibility_check.getInt("proteinvisible", 0);
	final   int carbsVisibility=visibility_check.getInt("carbsvisible", 0);
	final   int sugarVisibility=visibility_check.getInt("sugarvisible", 0);
	final   int cholestrolVisibility=visibility_check.getInt("cholestrolvisible", 0);
	final   int fatVisibility=visibility_check.getInt("fatvisible", 0);
	final   int caloriesVisibility=visibility_check.getInt("caloriesvisible", 0);
	final   int vitaminAVisibility=visibility_check.getInt("vitaminAvisible", 0);
	final   int fibreVisibility=visibility_check.getInt("fibrevisible", 0);
	final   int potasiumVisibility=visibility_check.getInt("potasiumvisible", 0);
	final   int sodiumVisibility=visibility_check.getInt("sodiumvisible", 0);
	
	
	
		proteinsCb=(CheckBox) rootView.findViewById(R.id.proteincheckBox);
		carbsCb=(CheckBox) rootView.findViewById(R.id.carbscheckBox);
		sugarCb=(CheckBox) rootView.findViewById(R.id.sugarcheckBox);
		fatCb=(CheckBox) rootView.findViewById(R.id.fatcheckBox);
		caloresCb=(CheckBox) rootView.findViewById(R.id.caloriescheckBox);
		fibreCb=(CheckBox) rootView.findViewById(R.id.fibreCb);
		vitaminAcB=(CheckBox) rootView.findViewById(R.id.vitaminAcheckBox);
		cholestrolCb=(CheckBox) rootView.findViewById(R.id.cholestrolcheckBox);
		potasiumCB=(CheckBox) rootView.findViewById(R.id.potasiumCb);
		sodiumCB=(CheckBox) rootView.findViewById(R.id.sodiumCb);
		
		
		
		
		
		
		
		
		
		//radio groups
		proteinradio=(RadioGroup) rootView.findViewById(R.id.proteinGroup);
		
		//try
		carbsRg=(RadioGroup) rootView.findViewById(R.id.carbsradioGroup);
		//carbsRg.setOnCheckedChangeListener((android.widget.RadioGroup.OnCheckedChangeListener) getActivity());
		
		
		fatRg=(RadioGroup) rootView.findViewById(R.id.fatradioGroup);
		caloriesRg=(RadioGroup) rootView.findViewById(R.id.caloriesradioGroup);
		vitaminArG=(RadioGroup) rootView.findViewById(R.id.vitamiAradioGroup);
		fibreRg=(RadioGroup) rootView.findViewById(R.id.fibreradioGroup);
		sugar=(RadioGroup) rootView.findViewById(R.id.sugarradioGroup);
		cholestrolRg=(RadioGroup) rootView.findViewById(R.id.cholestrolradioGroup);
		potasiumRG=(RadioGroup) rootView.findViewById(R.id.potasiumradioGroup);
		sodiumRG=(RadioGroup) rootView.findViewById(R.id.sodiumradioGroup);
		
		
		
		
		if (proteinVisibility==1) {
			proteinsCb.setChecked(true);
			proteinradio.setVisibility(View.VISIBLE);
		}
		if (carbsVisibility==1) {
			carbsCb.setChecked(true);
			carbsRg.setVisibility(View.VISIBLE);
		}
		if (cholestrolVisibility==1) {
			cholestrolCb.setChecked(true);
			cholestrolRg.setVisibility(View.VISIBLE);
		
		}
		if (sugarVisibility==1) {
			sugarCb.setChecked(true);
			sugar.setVisibility(View.VISIBLE);
		}
			
		if (fatVisibility==1) {
			
			fatCb.setChecked(true);
			fatRg.setVisibility(View.VISIBLE);
		}
		if (caloriesVisibility==1) {
			caloresCb.setChecked(true);
			caloriesRg.setVisibility(View.VISIBLE);
		}
		if (vitaminAVisibility==1) {
			vitaminAcB.setChecked(true);
			vitaminArG.setVisibility(View.VISIBLE);
		}
		if (fibreVisibility==1) {
			fibreCb.setChecked(true);
			fibreRg.setVisibility(View.VISIBLE);
		}
		if (potasiumVisibility==1) {
			potasiumCB.setChecked(true);
			potasiumRG.setVisibility(View.VISIBLE);
		}
		if (sodiumVisibility==1) {
			sodiumCB.setChecked(true);
			sodiumRG.setVisibility(View.VISIBLE);
		}
		
	
		
		
		RadioButton proteinmore=(RadioButton) rootView.findViewById(R.id.radioproteinmore);
		RadioButton proteinless=(RadioButton) rootView.findViewById(R.id.radioproteinless);
		
		RadioButton carbsmore=(RadioButton) rootView.findViewById(R.id.radiocarbsmore);
		RadioButton carbsless=(RadioButton) rootView.findViewById(R.id.radiocarbsless);
		
		RadioButton fatmore=(RadioButton) rootView.findViewById(R.id.radiofatmore);
		RadioButton fatless=(RadioButton) rootView.findViewById(R.id.radiofatless);
		
		RadioButton sugarmore=(RadioButton) rootView.findViewById(R.id.radiosugarmore);
		RadioButton sugarless=(RadioButton) rootView.findViewById(R.id.radiosugarless);
		
		RadioButton caloriesmore=(RadioButton) rootView.findViewById(R.id.radiocaloriesmore);
		RadioButton caloriesless=(RadioButton) rootView.findViewById(R.id.radiocaloriesless);
		
		RadioButton fibremore=(RadioButton) rootView.findViewById(R.id.radiofibreMore);
		RadioButton fibreless=(RadioButton) rootView.findViewById(R.id.radiofibreless);
		
		RadioButton vitaminamore=(RadioButton) rootView.findViewById(R.id.radiovitAmore);
		RadioButton vitaminaless=(RadioButton) rootView.findViewById(R.id.radiovitAless);
		
		RadioButton cholestrolmore=(RadioButton) rootView.findViewById(R.id.radiocholmore);
		RadioButton cholestrolless=(RadioButton) rootView.findViewById(R.id.radiocholestrless);
		
		RadioButton potasiummore=(RadioButton) rootView.findViewById(R.id.radiopotasiumMore);
		RadioButton potasiumless=(RadioButton) rootView.findViewById(R.id.radioproteinless);
		
		RadioButton sodiummore=(RadioButton) rootView.findViewById(R.id.radiosodiumMore);
		RadioButton sodiumless=(RadioButton) rootView.findViewById(R.id.radiosodiumless);
		
		
		
		
		SharedPreferences prefer = getActivity().getSharedPreferences
				("prefname", FragmentActivity.MODE_PRIVATE);
		
		
		
		int proteinpref=prefer.getInt("proteinpref", 0);
		int  sugarpref=prefer.getInt("sugarpref", 0);
		int cholestrolpref=prefer.getInt("proteinpref", 0);
		int carbspref=prefer.getInt("carbspref", 0);
		int  fatpref=prefer.getInt("fatpref", 0);
		int caloriesref=prefer.getInt("caloriespref", 0);
		int vitaminApref=prefer.getInt("vitaminApref", 0);
		int fibrepref=prefer.getInt("fibrepref", 0);
		int potasiumpref=prefer.getInt("potasiumpref", 0);
		int sodiumref=prefer.getInt("sodiumpref", 0);
		
		
		if (proteinpref==1&&proteinsCb.isChecked()) {
			proteinmore.setChecked(true);
		}
		if (carbspref==1&&carbsCb.isChecked()) {
			carbsmore.setChecked(true);
		}
		if (cholestrolpref==1&&cholestrolCb.isChecked()) {
			cholestrolmore.setChecked(true);
		
		}
		if (sugarpref==1&&sugarCb.isChecked()) {
			sugarless.setChecked(true);
		}
			
		if (fatpref==1&&fatCb.isChecked()) {
			
			fatmore.setChecked(true);
		}
		if (caloriesref==1&&caloresCb.isChecked()) {
			caloriesmore.setChecked(true);
		}
		if (vitaminApref==1&&vitaminAcB.isChecked()) {
			vitaminamore.setChecked(true);
		}
		if (fibrepref==1&&fibreCb.isChecked()) {
			fibremore.setChecked(true);
		}
		if (potasiumpref==1&&potasiumCB.isChecked()) {
			potasiummore.setChecked(true);
		}
		if (sodiumref==1&&sodiumCB.isChecked()) {
			sodiummore.setChecked(true);
		}
		
		
		
		
		
		
		//Butons
		save=(Button) rootView.findViewById(R.id.saveButton);
		//revert=(Button) rootView.findViewById(R.id.revertButton);
		
		
		@SuppressWarnings("static-access")
		
		final SharedPreferences pref = getActivity().getSharedPreferences(
		        "prefname", FragmentActivity.MODE_PRIVATE);
		
		final SharedPreferences visibility = getActivity().getSharedPreferences(
		        "visibility", FragmentActivity.MODE_PRIVATE);
		 
		//final SharedPreferences p=getActivity().getSharedPreferences("food", ge)
		
		final Editor editor = pref.edit();
		final Editor visibilityEdit=visibility.edit();
		
	   
		proteinsCb.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				if (isChecked) {
					proteinradio.setVisibility(View.VISIBLE);
				}
				else {
					proteinradio.setVisibility(View.INVISIBLE);	
				}
				
			}
		});
		
		
		
		
 proteinsCb.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				//int prefProtein=0;
				
				if (isChecked) {
					proteinradio.setVisibility(View.VISIBLE);
					visibilityEdit.putInt("proteinvisible", 1);
					   
					//proteinradio.getCheckedRadioButtonId();
					
						
				}
				else {
					proteinradio.setVisibility(View.INVISIBLE);
					visibilityEdit.putInt("proteinvisible", 0);
				}
				
			}
		});
carbsCb.setOnCheckedChangeListener(new OnCheckedChangeListener() {
	
	@Override
	public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
		if (isChecked) {
			carbsRg.setVisibility(View.VISIBLE);
			visibilityEdit.putInt("carbsvisible", 1);
		}
		else {
			carbsRg.setVisibility(View.INVISIBLE);	
			visibilityEdit.putInt("carbsvisible", 0);
		}
		
	}
});
sugarCb.setOnCheckedChangeListener(new OnCheckedChangeListener() {
	
	@Override
	public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
		if (isChecked) {
			sugar.setVisibility(View.VISIBLE);
			visibilityEdit.putInt("sugarvisible", 1);
		}
		else {
			sugar.setVisibility(View.INVISIBLE);
			visibilityEdit.putInt("sugarvisible", 0);
		}
		
	}
});
fatCb.setOnCheckedChangeListener(new OnCheckedChangeListener() {
	
	@Override
	public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
		if (isChecked) {
			fatRg.setVisibility(View.VISIBLE);
			visibilityEdit.putInt("fatvisible", 1);
		}
		else {
			fatRg.setVisibility(View.INVISIBLE);
			visibilityEdit.putInt("fatvisible", 0);
		}
		
	}
});
caloresCb.setOnCheckedChangeListener(new OnCheckedChangeListener() {
	
	@Override
	public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
		if (isChecked) {
			caloriesRg.setVisibility(View.VISIBLE);
			visibilityEdit.putInt("vitamincvisible", 1);
		}
		else {
			caloriesRg.setVisibility(View.INVISIBLE);
			visibilityEdit.putInt("vitamincvisible", 0);
		}
		
	}
});
fibreCb.setOnCheckedChangeListener(new OnCheckedChangeListener() {
	
	@Override
	public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
		
		if (isChecked) {
			fibreRg.setVisibility(View.VISIBLE);
			visibilityEdit.putInt("fibrevisible", 1);
			
			
			
		}
		else {
			fibreRg.setVisibility(View.INVISIBLE);	
			visibilityEdit.putInt("fibrevisible", 0);
		}
		
	}
});
	
vitaminAcB.setOnCheckedChangeListener(new OnCheckedChangeListener() {
	
	@Override
	public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
		if (isChecked) {
			vitaminArG.setVisibility(View.VISIBLE);
			visibilityEdit.putInt("vitaminAvisible", 1);
			//vitaminArG.getCheckedRadioButtonId();
		}
		else {
			vitaminArG.setVisibility(View.INVISIBLE);	
			visibilityEdit.putInt("vitaminAvisible", 0);
		}
		
	}
});
	
cholestrolCb.setOnCheckedChangeListener(new OnCheckedChangeListener() {
	
	@Override
	public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
		if (isChecked) {
			cholestrolRg.setVisibility(View.VISIBLE);
			visibilityEdit.putInt("cholestrolvisible", 1);
		}
		else {
			cholestrolRg.setVisibility(View.INVISIBLE);	
			visibilityEdit.putInt("cholestrolvisible", 0);
		}
		
	}
});

caloresCb.setOnCheckedChangeListener(new OnCheckedChangeListener() {
	
	@Override
	public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
		if (isChecked) {
			caloriesRg.setVisibility(View.VISIBLE);
			visibilityEdit.putInt("caloriesvisible", 1);
		}
		else {
			caloriesRg.setVisibility(View.INVISIBLE);	
			visibilityEdit.putInt("caloriesvisible", 0);
		}
		
	}
});

fibreCb.setOnCheckedChangeListener(new OnCheckedChangeListener() {
	
	@Override
	public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
		if (isChecked) {
			fibreRg.setVisibility(View.VISIBLE);
			visibilityEdit.putInt("fibrevisible", 1);
		}
		else {
		fibreRg.setVisibility(View.INVISIBLE);	
			visibilityEdit.putInt("fibrevisible", 0);
		}
		
	}
});
potasiumCB.setOnCheckedChangeListener(new OnCheckedChangeListener() {
	
	@Override
	public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
		if (isChecked) {
			potasiumRG.setVisibility(View.VISIBLE);
			visibilityEdit.putInt("potasiumvisible", 1);
		}
		else {
		potasiumRG.setVisibility(View.INVISIBLE);	
			visibilityEdit.putInt("potasiumvisible", 0);
		}
		
	}
});

sodiumCB.setOnCheckedChangeListener(new OnCheckedChangeListener() {
	
	@Override
	public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
		if (isChecked) {
			sodiumRG.setVisibility(View.VISIBLE);
			visibilityEdit.putInt("sodiumvisible", 1);
		}
		else {
		    sodiumRG.setVisibility(View.INVISIBLE);	
			visibilityEdit.putInt("sodiumvisible", 0);
		}
		
	}
});




		
/**revert.setOnClickListener(new OnClickListener() {
	
	@Override
	public void onClick(View v) {
		proteinsCb.setChecked(false);
		carbsCb.setChecked(false);
		sugarCb.setChecked(false);
		fatCb.setChecked(false);
		caloresCb.setChecked(false);
		fibreCb.setChecked(false);
		vitaminAcB.setChecked(false);
		cholestrolCb.setChecked(false);
		
	}
});	 **/

//RadioButton rb1=(RadioButton)getActivity().findViewById(R.id.radiocarbsless);
//if (rb1.isChecked()==true) {



save.setOnClickListener(new OnClickListener() {
	
	@Override
	public void onClick(View v) {
		
		final SharedPreferences History = getActivity().getSharedPreferences(
		        "history", FragmentActivity.MODE_PRIVATE);
	   
	
			
	    	//create a time and date instance to uniquely identify the summary period
	    	final Date cTime=new Date();
	    	final SimpleDateFormat sdf= new SimpleDateFormat("EEE,MMM d,yyyy");	
	    					
	    	String date=sdf.format(cTime);
	    	//String time=sdf.format(cTime);
	    	
	    	
	    	Editor e=History.edit();
			e.putString("history_id", date);
			e.putString("cart_key", "1");
			e.commit();
			
			

			
			final SharedPreferences checkout_visibility = getActivity().getSharedPreferences(
			        "checkout", FragmentActivity.MODE_PRIVATE);
			Editor ed=checkout_visibility.edit();
			ed.putInt("check_o", 0);
			ed.commit();
						
		
		
		//proteins
		if (proteinradio.getCheckedRadioButtonId()==R.id.radioproteinless) {
			editor.putInt("proteinpref", 0);
			
		}
		
		 if (proteinradio.getCheckedRadioButtonId()==R.id.radioproteinmore) {
			editor.putInt("proteinpref",1 );
		}
		
		//carbs
		
		if (carbsRg.getCheckedRadioButtonId()==R.id.radiocarbsless) {
			RadioButton rb1=(RadioButton)getActivity().findViewById(R.id.radiocarbsless);
			
			editor.putInt("carbspref",0 );
			//RadioButton rb1=(RadioButton)getActivity().findViewById(R.id.radiocarbsless); 
		}
		
		 if (carbsRg.getCheckedRadioButtonId()==R.id.radiocarbsmore) {
			editor.putInt("carbspref", 1);
			
			//sugar
		}
		 if (sugar.getCheckedRadioButtonId()==R.id.radiosugarless) {
			editor.putInt("sugarpref", 0);
		}
		
		 if (sugar.getCheckedRadioButtonId()==R.id.radiosugarmore) {
			editor.putInt("sugarpref", 1);
			
		}
		
		
		//fat
		
		 if (fatRg.getCheckedRadioButtonId()==R.id.radiofatless) {
			editor.putInt("fatpref", 0);
		}
		 if (fatRg.getCheckedRadioButtonId()==R.id.radiofatmore) {
			editor.putInt("fatpref", 1);
		}
		
		
		//calories
		 if (caloriesRg.getCheckedRadioButtonId()==R.id.radiocaloriesless) {
			editor.putInt("caloriespref", 0);
					
		}
		 if (caloriesRg.getCheckedRadioButtonId()==R.id.radiocaloriesmore) {
			editor.putInt("caloriespref", 1);
					
		}
		
		
		//vitamin A
		 if (vitaminArG.getCheckedRadioButtonId()==R.id.radiovitAless) {
			editor.putInt("vitaminApref", 0);
		}
		 if (vitaminArG.getCheckedRadioButtonId()==R.id.radiovitAmore) {
			editor.putInt("vitaminApref", 1);
		}
		
		
		//fibre
		 if (fibreRg.getCheckedRadioButtonId()==R.id.radiofibreless) {
			editor.putInt("fibrepref", 0);
		}
		 if (fibreRg.getCheckedRadioButtonId()==R.id.radiofibreMore) {
			editor.putInt("fibrepref", 1);
		}
		
		
		
		//cholestrol
		 if (cholestrolRg.getCheckedRadioButtonId()==R.id.radiocholestrless) {
			editor.putInt("cholestrolpref", 0);
		}
		
		 if (cholestrolRg.getCheckedRadioButtonId()==R.id.radiocholmore) {
			editor.putInt("cholestrolpref", 1);
		}
		 
		//potasuim
		 if (potasiumRG.getCheckedRadioButtonId()==R.id.radiopotasiumless) {
			editor.putInt("potasiumpref", 0);
		}
		
		 if (potasiumRG.getCheckedRadioButtonId()==R.id.radiopotasiumMore) {
			editor.putInt("potasiumpref", 1);
		}
		 
		//sodium
		 if (sodiumRG.getCheckedRadioButtonId()==R.id.radiosodiumless) {
			editor.putInt("sodiumpref", 0);
		}
		
		 if (sodiumRG.getCheckedRadioButtonId()==R.id.radiosodiumMore) {
			editor.putInt("sodiumpref", 1);
		}
		 
		 
		
		editor.commit();
		visibilityEdit.commit();
		
		int m= pref.getInt("sugarpref", (Integer) 0);
		
		
		Toast.makeText(getActivity(), "you prefer"+ ":"+ String.valueOf(m), Toast.LENGTH_LONG).show();
		
		Toast.makeText(getActivity(), "Preferences saved", Toast.LENGTH_SHORT).show();
		Fragment dashboard = new DashboardFragment();
		FragmentTransaction transaction = getFragmentManager().beginTransaction();

		// Replace whatever is in the fragment_container view with this fragment,
		// and add the transaction to the back stack
		transaction.replace(R.id.content_frame, dashboard);
		transaction.addToBackStack(null);

		// Commit the transaction
		  transaction.commit();
		  
		  DBAdapter db= new DBAdapter(getActivity());
		  db.openDatabase();
		  db.DeleteSumming();
		  db.close();
		  db.openDatabase();
		  db.DeleteAllnutrients();
		  db.close();
		
	}
});
		
		
		
		return rootView;
	}

}
