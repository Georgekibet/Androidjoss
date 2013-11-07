package com.android.joss;

import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuItem;

import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.Fragment;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.widget.DrawerLayout;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.support.v4.view.GravityCompat;

public class MainActivityForFragments extends SherlockFragmentActivity {

	// Declare Variable
	
	DrawerLayout mDrawerLayout;
	ListView mDrawerList;
	ActionBarDrawerToggle mDrawerToggle;
	MenuListAdapter mMenuAdapter;
	String[] title;
	String[] subtitle;
	int[] icon;
	Fragment summary = new Summaryfragment();
	Fragment preference = new Preferencesfragment();
	Fragment login = new Loginfragment();
	Fragment dashboardfragment = new DashboardFragment();
	Fragment comparefragment = new CompareFragment();
	Fragment scan = new Snapfragment();

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.drawer_main);

		// Generate title
		title = new String[] {"    " +"Dashboard","    " + "Preferences",
				"    " +"Logout","    " +"My shopping Cart" ,
				"    " +"My shopping Cart","    " +"Comparison","    " +"Scan products"};

		// Generate subtitle
		subtitle = new String[] { "", ""," ","" ,"","",""};

		// Generate icon
		icon = new int[] { R.drawable.dashboard, R.drawable.preferences_icon,
				R.drawable.loginnow ,R.drawable.shoping_icons,
				R.drawable.kompare,R.drawable.dashboard,R.drawable.qr_code};

		// Locate DrawerLayout in drawer_main.xml
		mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);

		// Locate ListView in drawer_main.xml
		mDrawerList = (ListView) findViewById(R.id.left_drawer);

		// Set a custom shadow that overlays the main content when the drawer
		// opens
		mDrawerLayout.setDrawerShadow(R.drawable.drawer_shadow,
				GravityCompat.START);

		// Pass results to MenuListAdapter Class
		mMenuAdapter = new MenuListAdapter(this, title, subtitle, icon);

		// Set the MenuListAdapter to the ListView
		mDrawerList.setAdapter(mMenuAdapter);

		// Capture button clicks on side menu
		mDrawerList.setOnItemClickListener(new DrawerItemClickListener());

		// Enable ActionBar app icon to behave as action to toggle nav drawer
		getSupportActionBar().setHomeButtonEnabled(false);
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		getSupportActionBar().setIcon(R.color.abs__bright_foreground_holo_light);
		getSupportActionBar().setDisplayShowTitleEnabled(false);
		
	
		

		// ActionBarDrawerToggle ties together the the proper interactions
		// between the sliding drawer and the action bar app icon
		mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout,
				R.drawable.dashboard, R.string.drawer_open,
				R.string.drawer_close) {

			public void onDrawerClosed(View view) {
				// TODO Auto-generated method stub
				super.onDrawerClosed(view);
			}

			public void onDrawerOpened(View drawerView) {
				// TODO Auto-generated method stub
				super.onDrawerOpened(drawerView);
			}
		};

		mDrawerLayout.setDrawerListener(mDrawerToggle);
		
		//The fragment to loaded when the app is first launched
	if (savedInstanceState == null) {
			selectItem(3);
		}
	}
	
	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getSupportMenuInflater().inflate(R.menu.activity_itemlist, menu);
		
		//menu.hasVisibleItems();
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {

		if (item.getItemId() == android.R.id.home) {

			if (mDrawerLayout.isDrawerOpen(mDrawerList)) {
				mDrawerLayout.closeDrawer(mDrawerList);
			} else {
				mDrawerLayout.openDrawer(mDrawerList);
			}
		}
		
		if (item.getItemId()==R.id.login) {
			// Create new fragment and transaction
			Fragment regester = new RegisterFragment();
			FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

			// Replace whatever is in the fragment_container view with this fragment,
			// and add the transaction to the back stack
			transaction.replace(R.id.content_frame, login);
			transaction.addToBackStack(null);

			// Commit the transaction
			transaction.commit();

		}

		return super.onOptionsItemSelected(item);
	}
  
	
	// The click listener for ListView in the navigation drawer
	private class DrawerItemClickListener implements
			ListView.OnItemClickListener {
		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position,
				long id) {
			selectItem(position);
		}
	}

	public void selectItem(int position) {

	 FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
		// Locate Position
		switch (position) {
		case 0:
			ft.replace(R.id.content_frame, dashboardfragment);
			break;
		case 1:
			ft.replace(R.id.content_frame, preference);
			break;
		case 2:
			ft.replace(R.id.content_frame, login);
			break;
		case 3:
			ft.replace(R.id.content_frame, dashboardfragment);
			break;
		case 4:
			ft.replace(R.id.content_frame, comparefragment);
			break;
		case 5:
			ft.replace(R.id.content_frame, summary);
			break;
		case 6:
			ft.replace(R.id.content_frame, scan);
			break;
		}
		ft.commit();
		mDrawerList.setItemChecked(position, true);
		// Close drawer
		mDrawerLayout.closeDrawer(mDrawerList);
	}

	@Override
	protected void onPostCreate(Bundle savedInstanceState) {
		super.onPostCreate(savedInstanceState);
		// Sync the toggle state after onRestoreInstanceState has occurred.
		mDrawerToggle.syncState();
	}

	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		super.onConfigurationChanged(newConfig);
		// Pass any configuration change to the drawer toggles
		mDrawerToggle.onConfigurationChanged(newConfig);
	}
}
