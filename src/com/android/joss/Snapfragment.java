package com.android.joss;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.actionbarsherlock.app.SherlockActivity;
import com.actionbarsherlock.app.SherlockFragment;
import com.actionbarsherlock.view.Window;

import eu.livotov.zxscan.ZXScanHelper;

public class Snapfragment extends SherlockFragment {
	
	//declare variables

	Dialog scandialogue;
	Button scannext;
	protected String product1id;
	protected  String product2id;
	ProgressBar scanprog;

	  /**
	   * Callback interface through which the fragment will report the
	   * task's progress and results back to the Activity.
	   */
	
	
		ImageView login,register;
		EditText email,password;
		protected String getemail;
		protected ProgressBar progress;
		protected String returnedString;
	    static interface TaskCallbacks {
	    void onPreExecute();
	    void onProgressUpdate(int percent);
	    void onCancelled();
	    void onPostExecute();
	   
	  }
	 
	  private TaskCallbacks mCallbacks;
	  private DummyTask mTask;
	 
	  
	  
	  /**
	   * Hold a reference to the parent Activity so we can report the
	   * task's current progress and results. The Android framework
	   * will pass us a reference to the newly created Activity after
	   * each configuration change.
	   */
	  @Override
	  public void onAttach(Activity activity) {
	    super.onAttach(activity);
	    mCallbacks = (TaskCallbacks) activity;
	  }
	 
	  /**
	   * This method will only be called once when the retained
	   * Fragment is first created.
	   */

	
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		View rootView = inflater.inflate(R.layout.snap, container, false);
		
		Snapfragment.this.setRetainInstance(true);
		
		scanprog=(ProgressBar) rootView.findViewById(R.id.progressBarScan);
		scanprog.setVisibility(View.INVISIBLE);
		
		//setRetainInstance(true);
		
		//Animation anim=AnimationUtils.loadAnimation(getActivity(), R.anim.move);
		ZXScanHelper.setCustomScanLayout(R.layout.capture);
         
	    ZXScanHelper.scan(getSherlockActivity(),12345);
		
		    
		
		
			    scandialogue=new Dialog(getActivity());
			    scandialogue.requestWindowFeature((int) Window.FEATURE_NO_TITLE); 
				scandialogue.setContentView(R.layout.scandialog);
				//scandialogue.setTitle("Scanning product contents");
				
				

				//snap1.setImageResource(R.drawable.scan_inactive);
				/**scannext=(Button) scandialogue.findViewById(R.id.scan1button);
				
				scannext.setOnClickListener(new OnClickListener() {
					
					@Override
					public void onClick(View v) {
						MainActivityForFragments._tempFragment = Snapfragment.this;

						ZXScanHelper.scan(getSherlockActivity(),1);   
						
					}
				});**/
				
				
				
				MainActivityForFragments._tempFragment = Snapfragment.this;
				
				
				
				
			
		return rootView;
	}
	
	
	
	
	protected void fetchFromAsynctask(String jstring) {
		  
		  String jstrings=jstring;
		  
		  
			
     try {
		
    	 Log.v("stringBrot", jstrings);
    	 
	} catch (NullPointerException e) {
		Toast.makeText(getActivity(), "Sorry Server Problem", Toast.LENGTH_LONG).show();
	}		
		  
			
		
		  
	    	  
		  
		  
           
			try {
				
				//Cast the returned string into json and decode to get the result
				
				JSONObject alldata=new JSONObject(jstrings);
				JSONArray jsonprod1;
				JSONArray jsonprod12;
				
				//first product specific details
				jsonprod1 = alldata.getJSONArray("first");
				
				JSONObject proddetails=jsonprod1.getJSONObject(0);
				String     prod1_code=proddetails.getString("productid");
				String     protein1=proddetails.getString("Protein");
				String     url1=proddetails.getString("image_url");
				String     nutrient_units=proddetails.getString("fibre");
				String     carbs=proddetails.getString("carbs");
				String     fat=proddetails.getString("fat");
				String     sugar=proddetails.getString("sugar");
				String     name1=proddetails.getString("productname");
				String     cholestrol=proddetails.getString("cholestrol");
				String     calories1=proddetails.getString("calories");
				String     fibre1=proddetails.getString("fibre");
				String     vitaminA1=proddetails.getString("vitaminA");
				String     potasium1=proddetails.getString("potasium");
				String     sodium1=proddetails.getString("sodium");
				String     serving1=proddetails.getString("serving");
				
			
				
				
				//second product specific details
				jsonprod12 = alldata.getJSONArray("second");
				
				JSONObject prod2details=jsonprod12.getJSONObject(0);
				String     prod2_code=prod2details.getString("productid");
				String     protein2=prod2details.getString("Protein");
				String     url2=prod2details.getString("image_url");
				String     nutrient_units2=prod2details.getString("fibre");
				String     carbs2=prod2details.getString("carbs");
				String     fat2=prod2details.getString("fat");
				String     sugar2=prod2details.getString("sugar");
				String     cholestrol2=prod2details.getString("cholestrol");
				String     name2=prod2details.getString("productname");
				String     calories2=prod2details.getString("calories");
				String     fibre2=prod2details.getString("fibre");
				String     vitaminA2=prod2details.getString("vitaminA");
				String     potasium2=prod2details.getString("potasium");
				String     sodium2=prod2details.getString("sodium");
				String     serving2=prod2details.getString("serving");
				
				
		//Toast.makeText(getActivity(), "proteins ni:"   +protein1, Toast.LENGTH_LONG).show();		
			
			Log.d("IT finally worked", protein1+protein2);
			
		
			        
			       
            // Toast.makeText(getActivity(), protein1+protein2, Toast.LENGTH_LONG).show();	
             
             getActivity();
			//Create a shared preference and store the returned values
             
         SharedPreferences preff=getActivity(). 		 
                        getSharedPreferences("storedata", FragmentActivity.MODE_PRIVATE);
             
  /*           
             int protei_1=preff.getInt("product1content", 0)+Integer.parseInt(protein1);
             int proten_2=preff.getInt("product1content", 0)+Integer.parseInt(protein2);
             int carbs_1=preff.getInt("product1content", 0)+Integer.parseInt(carbs);
             int carbs_2=preff.getInt("product1content", 0)+Integer.parseInt(carbs2);
             int fat_1=preff.getInt("product1content", 0)+Integer.parseInt(fat);
             int fat_2=preff.getInt("product1content", 0)+Integer.parseInt(fat2);
             int sugar_1=preff.getInt("product1content", 0)+Integer.parseInt(sugar);
             int sugar_2=preff.getInt("product1content", 0)+Integer.parseInt(sugar2);
             int cholestrol_1=preff.getInt("product1content", 0)+Integer.parseInt(cholestrol);
             int cholestrol_2=preff.getInt("product1content", 0)+Integer.parseInt(cholestrol2);
*/
             
             
             Editor editor=preff.edit();
                     
                    editor.putString("product1code", prod1_code);
                    editor.putString("protein1", protein1);
                    
                    editor.putString("url1", url1);
                    editor.putString("nutrient_units", nutrient_units);
                    editor.putString("carbs", carbs);
                    editor.putString("fat",fat);
                    editor.putString("sugar", sugar);
                    editor.putString("cholestrol",cholestrol);
                    
                    editor.putString("name1",name1);
                    editor.putString("calories1",calories1);
                    editor.putString("fibre1",fibre1);
                    editor.putString("vitaminA1",vitaminA1);
                    editor.putString("potasium1",potasium1);
                    editor.putString("sodium1",sodium1);
                    editor.putString("serving1",serving1);
                    
                    
                    editor.putString("protein2",protein2);
                    editor.putString("url2", url2);
                    editor.putString("product2code", prod2_code);
                   editor.putString("nutrient_units2", nutrient_units2);
                    editor.putString("carbs2", carbs2);
                    editor.putString("fat2",fat2);
                    editor.putString("sugar2",sugar2);
                    editor.putString("cholestrol2",cholestrol2);
                    
                    editor.putString("name2",name2);
                    editor.putString("calories2",calories2);
                    editor.putString("fibre2",fibre2);
                    editor.putString("vitaminA2",vitaminA2);
                    editor.putString("potasium2",potasium2);
                    editor.putString("sodium2",sodium2);
                    editor.putString("serving2",serving2);
                   
             
                    editor.commit();
             
            
                  // Toast.makeText(getActivity(), name1 +" "   +protein1, Toast.LENGTH_LONG).show();
                    
                 
			
		
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			catch (NullPointerException e) {
				Toast.makeText(getActivity(), "F", Toast.LENGTH_SHORT).show();
				e.printStackTrace();
			}
			
			
		  }
	

	/**
	   * Set the callback to null so we don't accidentally leak the
	   * Activity instance.
	   */
	  @Override
	  public void onDetach() {
	    super.onDetach();
	    mCallbacks = null;
	    
	    
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

	
	
	
public void onActivityResult(final int requestCode, final int resultCode, final Intent data) {
		//retrieve result of scanning - instantiate ZXing object
	scanprog.setVisibility(View.VISIBLE);
	
	//get first product scan results
		 if (resultCode == SherlockActivity.RESULT_OK && requestCode == 12345 && data!=null)
	        {
			
	          
	             
				String  scannedCode1 = ZXScanHelper.getScannedCode(data);
				
				if (scannedCode1.equals("")) {
			Toast.makeText(getActivity(),"error", Toast.LENGTH_LONG).show();
				}
				else if (!scannedCode1.equals("")) {
					   //Create a handler to delay the system for 1 second before 
					//scanning the next product
					   new Handler().postDelayed(new Runnable() {
					        @Override
					        public void run() {
					        	MainActivityForFragments._tempFragment = Snapfragment.this;

								ZXScanHelper.scan(getSherlockActivity(),1);     
					        }
					    }, 2000);

				}
			   scandialogue.show();  
	         			 
			 
			 Toast.makeText(getActivity(), scannedCode1, Toast.LENGTH_LONG).show();

			            product1id=scannedCode1;
			
			 
		/*create a shared preference to store a flag
	    this will ensure that a product is picked once
		 after scanning*/	            
			            
	 SharedPreferences check = getActivity().getSharedPreferences
			    				("check_double", FragmentActivity.MODE_PRIVATE);
			   Editor edit=check.edit();
			    edit.putInt("flag", 1);
			    edit.commit();
			            
     final SharedPreferences history_flag = getActivity().getSharedPreferences(
				        "history_flag", FragmentActivity.MODE_PRIVATE);
				Editor e=history_flag.edit();
				e.putInt("flagg", 1);
				e.commit();				    
	        }
		 
		 //Second product scan results
		
		 else if (resultCode == SherlockActivity.RESULT_OK && requestCode == 1 && data!=null)
	        {
	            String scannedCode2 = ZXScanHelper.getScannedCode(data);
	            Toast.makeText(getActivity(), scannedCode2, Toast.LENGTH_SHORT).show();
	            
	                     if (scannedCode2!=null) {
					           product2id=scannedCode2;
				              }
	                     else {
				            	Toast.makeText(getActivity(), "no id resolved", 
				            			Toast.LENGTH_LONG).show();
							       }
				
	                     
	            scandialogue.dismiss();
	            
	                
	            
	        }
           
		 
		 //pass the fetched codes online to retrieve product info.
		 if (isNetworkAvailable()) {
	new DummyTask().execute(String.valueOf(product1id),String.valueOf(product2id));
	
	}
	    // MainActivityForFragments._tempFragment = null;	
}


/**
 * A dummy task that performs some (dumb) background work and
 * proxies progress updates and results back to the Activity.
 *
 * Note that we need to check if the callbacks are null in each
 * method in case they are invoked after the Activity's and
 * Fragment's onDestroy() method have been called.
 */




private class DummyTask extends AsyncTask<String, Integer, String> {

  @Override
  protected void onPreExecute() {
    if (mCallbacks != null) {
      mCallbacks.onPreExecute();
    }
  }

  /**
   * Note that we do NOT call the callback object's methods
   * directly from the background thread, as this could result
   * in a race condition.
   */
  @Override
  protected String doInBackground(String... params) {
   /* for (int i = 0; !isCancelled() && i < 100; i++) {
      SystemClock.sleep(100);
      publishProgress(i);
    }*/
  	
  	String dataFrmjson = "";
  	String result = null;
  	InputStream is = null;
  	StringBuilder sb=null;
  	HttpClient httpclient;
  	HttpPost httppost;
  	HttpResponse response;
  	HttpEntity entity;

  	ArrayList<NameValuePair> nameValuePairs;
  	String product1=params[0];
  	String product2=params[1];
  	//ArrayList<SearchResults> results1 = new ArrayList<SearchResults>();

  	try{
  		//String sex = ch2.toString();
  	    httpclient = new DefaultHttpClient();
  	    nameValuePairs = new ArrayList<NameValuePair>(2);
  	    nameValuePairs.add(new BasicNameValuePair("product1", product1.trim()));
  	    nameValuePairs.add(new BasicNameValuePair("product2", product2.trim()));
  	    //nameValuePairs.add(new BasicNameValuePair("gender", sex.trim()));
  	   
  	    
  	   // httppost = new HttpPost("http://10.0.2.2/api/login.php");
  	    httppost = new HttpPost("http://jossomar.netai.net/productinfo.php");
  	    httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
  	    response = httpclient.execute(httppost);
  	   	entity = response.getEntity();
  	   	is = entity.getContent();
  	   
  	   	//Toast.makeText(getApplicationContext(), "pass", Toast.LENGTH_SHORT).show();
  	   }catch(Exception e){
  	       Log.e("log_tag", "Error in http connection"+e.toString());
  	  }
  	//convert response to string
  	try{
  	    BufferedReader reader = new BufferedReader(new InputStreamReader(is,"iso-8859-1"),8);
  	     sb = new StringBuilder();
  	     sb.append(reader.readLine() + "\n");
  	     String line="0";
  	     while ((line = reader.readLine()) != null) {
  	                    sb.append(line + "\n");
  	      }
  	      is.close();
  	     
  	      
  	      result=sb.toString();
  	      
  	     
  	}
  	catch(Exception e){
  	    	 
  	      }

  	   
  	
  	
    return result;
  }

  @Override
  protected void onProgressUpdate(Integer... percent) {
    if (mCallbacks != null) {
      mCallbacks.onProgressUpdate(percent[0]);
    }
  }

  @Override
  protected void onCancelled() {
    if (mCallbacks != null) {
      mCallbacks.onCancelled();
    }
  }

  
  
  @Override
  protected void onPostExecute(String result) {
    if (mCallbacks != null) {
  	 returnedString=result;
      mCallbacks.onPostExecute();
      super.onPostExecute(result);
      scanprog.setVisibility(View.INVISIBLE);
      
      fetchFromAsynctask(returnedString);
      movetonextfragment();
    }
  }


}

private void movetonextfragment() {
	   //Go to preferences screen
	Fragment summaFragment = new CompareFragment();
	//preferencFragment.setArguments(args)
	FragmentTransaction transaction = getFragmentManager().beginTransaction();

	// Replace whatever is in the fragment_container view with this fragment,
	// and add the transaction to the back stack
	transaction.replace(R.id.content_frame, summaFragment);
	transaction.addToBackStack(null);

	// Commit the transaction
	transaction.commitAllowingStateLoss();

	
}
@Override
public void onDestroy() {
	// TODO Auto-generated method stub
	super.onDestroy();
}




}
