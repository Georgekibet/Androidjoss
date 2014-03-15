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

import com.actionbarsherlock.app.SherlockActivity;
import com.actionbarsherlock.app.SherlockFragment;
import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.actionbarsherlock.view.Window;

import android.annotation.SuppressLint;
import android.app.Activity;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.graphics.Typeface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.SystemClock;
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
import android.widget.TextView;
import android.widget.Toast;

@SuppressLint("NewApi")
public class Loginfragment extends Fragment  {
 
  /**
   * Callback interface through which the fragment will report the
   * task's progress and results back to the Activity.
   */
	ImageView login;
	EditText email,password;
	Button loginB;
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
  
  @SuppressLint("NewApi")
@Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
	  //((getActivity()) ).requestWindowFeature( (int) Window.FEATURE_NO_TITLE);
		View rootView = inflater.inflate(R.layout.activity_main, container, false);
		setRetainInstance(true);
		
		//getActivity().getActionBar().hide();
		//SherlockFragmentActivity k = new MainActivityForFragments();
		((SherlockFragmentActivity) getActivity()).getSupportActionBar().hide();
		
		//getSherlockActivity()
		
		//String url="http://10.0.2.2/hotel/menu.php";
		//mTask = new DummyTask();
       // mTask.execute();
        
		Typeface font = Typeface.createFromAsset(getActivity().getAssets(), "Yahoo.ttf");
        
	      TextView	logo =(TextView) rootView.findViewById(R.id.logotext);
	      //logo.setTypeface(font);
		//login =(ImageView) rootView.findViewById(R.id.imageView2);
	      loginB=(Button) rootView.findViewById(R.id.buttonlogin);
		TextView register =(TextView) rootView.findViewById(R.id.registerTv);
		email=(EditText) rootView.findViewById(R.id.email);
		password=(EditText) rootView.findViewById(R.id.password);
		
		progress=(ProgressBar) rootView.findViewById(R.id.progressBarLogin);
		loginB.setOnClickListener(new OnClickListener() {
			
			@SuppressLint("NewApi")
			@Override
			public void onClick(View v) {
				
				if (isNetworkAvailable()) {
					
				
				
                //fetchFromAsynctask();

				progress.setVisibility(View.VISIBLE);
				//SystemClock.sleep(1000);
				if (email.getText().toString().isEmpty() 
						||password.getText().toString().isEmpty()) {
					
			Toast.makeText(getActivity(), 
					"kindly fill in your all details", Toast.LENGTH_SHORT).show();
					progress.setVisibility(View.INVISIBLE);
					
					
				}
				
				
				else{
					
					
				new DummyTask().execute(email.getText().toString(),
						password.getText().toString());
								

				}
				}				    
			    else {
			    	Toast.makeText(getActivity(),
			    		"Please check your network first", Toast.LENGTH_SHORT).show();
				}
			}
			
		});
		
		register.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				
				Fragment regesterFrag = new RegisterFragment();
				FragmentTransaction transaction = getFragmentManager().
						beginTransaction();

				// Replace whatever is in the fragment_container view with this fragment,
				// and add the transaction to the back stack
				transaction.replace(R.id.content_frame, regesterFrag);
				transaction.addToBackStack(null);

				// Commit the transaction
				transaction.commit();
				
			}
		});
		
	//public void fetchFromAsynctask() {}
 
    // Retain this fragment across configuration changes.
    setRetainInstance(true);
 
    // Create and execute the background task.
    
	return rootView;
  }
  
  
 //Method to deal with data returned from asynctask
  protected void fetchFromAsynctask(String jstring) {
	  
	  try {	  
	  
	  if (jstring.equals("")) {
		  
		  
		  Toast.makeText(getActivity(),
		    		"Please check your network first", Toast.LENGTH_SHORT).show();

	  }
	  
	  else {
		  

	
	  
	  String jstrings=jstring;

		JSONObject json;
		
		try {
			json = new JSONObject(jstrings);
			String pass=json.getString("userdetails").toString();
		
		Log.d("IT finally worked", pass);
		//email.setText(pass);
		int testPass=Integer.parseInt(pass);
		        if (testPass==1) {
			
			       Toast.makeText(getActivity(), 
			    		   "Success", Toast.LENGTH_SHORT).show();
			      
			       
			       Fragment dashbod = new DashboardFragment();
					FragmentTransaction transaction = 
							getFragmentManager().beginTransaction();

					// Replace whatever is in the fragment_container view with this fragment,
					// and add the transaction to the back stack
					transaction.replace(R.id.content_frame, dashbod);
					transaction.addToBackStack(null);

					// Commit the transaction
					 
					transaction.commit();
					//email.setText("");
					//email.setFocusableInTouchMode(false);
			SharedPreferences login = getActivity().getSharedPreferences
							("login", FragmentActivity.MODE_PRIVATE);
			Editor edit=login.edit();
			 edit.putInt("login_success", 1);
             edit.commit();
			
		             }
		        else {
		        Toast.makeText(getActivity(), 
		        		"Wrong Credentials kinly try again", Toast.LENGTH_SHORT).show();	
				}
		
	
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (NullPointerException e) {
			Toast.makeText(getActivity(),
		    		"Please check your network first", Toast.LENGTH_SHORT).show();

			e.printStackTrace();
		}
	  }
	  } 
	  catch(NullPointerException e) {
			Toast.makeText(getActivity(),
		    		"Please check your network first", Toast.LENGTH_SHORT).show();

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
    	String email=params[0];
    	String pass=params[1];
    	//ArrayList<SearchResults> results1 = new ArrayList<SearchResults>();

    	try{
    		//String sex = ch2.toString();
    	    httpclient = new DefaultHttpClient();
    	    nameValuePairs = new ArrayList<NameValuePair>(2);
    	    nameValuePairs.add(new BasicNameValuePair("email", email.trim()));
    	    nameValuePairs.add(new BasicNameValuePair("password", pass.trim()));
    	    //nameValuePairs.add(new BasicNameValuePair("gender", sex.trim()));
    	   
    	    
    	   // httppost = new HttpPost("http://10.0.2.2/api/login.php");
    	    httppost = new HttpPost("http://jossomar.netai.net/login.php");
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
        progress.setVisibility(View.INVISIBLE);
        
        fetchFromAsynctask(returnedString);
      }
    }
  }
  
  
  
}