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
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;

public class Handlandlebarcode extends Fragment  {
 
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
  
  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.snap, container, false);
		setRetainInstance(true);
	
 
    // Retain this fragment across configuration changes.
    setRetainInstance(true);
 
    // Create and execute the background task.
    
	return rootView;
  }
  
  
 
  protected void fetchFromAsynctask(String jstring) {
	  
	  String jstrings=jstring;

		JSONObject json;
		try {
			json = new JSONObject(jstrings);
			String pass=json.getString("userdetails").toString();
		
		Log.d("IT finally worked", pass);
		email.setText(pass);
	
		} catch (JSONException e) {
			// TODO Auto-generated catch block
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
    	   
    	    
    	    httppost = new HttpPost("http://10.0.2.2/api/login.php");
    	    //httppost = new HttpPost("http://10.0.2.2/hotel/menu.php");
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
        progress.setVisibility(getView().INVISIBLE);
        
        fetchFromAsynctask(returnedString);
      }
    }
  }
}