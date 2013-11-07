package com.android.joss;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.concurrent.ExecutionException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.actionbarsherlock.app.SherlockFragment;


import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;

public class Loginfragment extends SherlockFragment {
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.activity_main, container, false);
		
		
		ImageView rejister=(ImageView) rootView.findViewById(R.id.imageView2);
		rejister.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// Create new fragment and transaction
				Fragment regester = new RegisterFragment();
				FragmentTransaction transaction = getFragmentManager().beginTransaction();

				// Replace whatever is in the fragment_container view with this fragment,
				// and add the transaction to the back stack
				transaction.replace(R.id.content_frame, regester);
				transaction.addToBackStack(null);

				// Commit the transaction
				transaction.commit();
				
				
			}
		});
		
		ImageView login=(ImageView) rootView.findViewById(R.id.imageView3);
		login.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// Create new fragment and transaction
				String url="jossomar.netai.net/login.php";
				PostTask data=(PostTask) new PostTask().execute(url);
				 //data.execute(url);
				 
				
				
				try {
					JSONObject fetchedObject=data.get();
					
					JSONArray	items = fetchedObject.getJSONArray("menu_obj");
					
					String p=items.getJSONObject(1).toString();
					Log.v("data", p);
					
					//le.setText(p);
					
					//JSONObject food=items.getJSONObject(1);
					
					
					
					
					
					} 
						catch (JSONException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (ExecutionException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						
				
				
			}
				
			
		});
		
		
		return rootView;
	}

}

//The definition of our asynktask class
/*Asynk task class runs in the back ground allowing the app
  to continue running as the asysnktask class fetches data from
  the api via a separate thread  */
class PostTask extends AsyncTask<String, Void, JSONObject> {
	public static InputStream is = null;
	static JSONObject jObj = null;
	static JSONArray sobj=null;
	static String json = "";
@Override
protected void onPreExecute() {
super.onPreExecute();
displayProgressBar("Downloading...");
}

private void displayProgressBar(String string) {
	
	// TODO Auto-generated method stub	
}


protected JSONObject doInBackground(String... params) {
String url=params[0];
//Making HTTP request
		try {
			// defaultHttpClient
			DefaultHttpClient httpClient = new DefaultHttpClient();
			HttpPost httpPost = new HttpPost(url);

			HttpResponse httpResponse = httpClient.execute(httpPost);
			HttpEntity httpEntity = httpResponse.getEntity();
			is = httpEntity.getContent();			

		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		try {
			BufferedReader reader = new BufferedReader(new InputStreamReader(
					is, "iso-8859-1"), 8);
			StringBuilder sb = new StringBuilder();
			String line = null;
			while ((line = reader.readLine()) != null) {
				sb.append(line + "\n");
			}
			is.close();
			json = sb.toString();
		} catch (Exception e) {
			Log.e("Buffer Error", "Error converting result " + e.toString());
		}

		// try parse the string to a JSON object
		try {
			jObj = new JSONObject(json);
		} catch (JSONException e) {
			Log.e("JSON Parser", "Error parsing data " + e.toString());
		}

		// return JSON String
		return jObj;
//return jobj;

}



}
