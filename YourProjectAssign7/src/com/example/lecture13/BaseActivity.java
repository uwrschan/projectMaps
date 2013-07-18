package com.example.lecture13;

import android.app.Activity;
import android.content.Intent;
import android.view.Menu;

public class BaseActivity extends Activity{
	/* The keys for all the settings shared preferences */
	public static final String SETTINGS_PREFS = "SETTINGS PREFS";
	public static final String SETTINGS_PREFS_FIRST_NAME = "FIRST NAME";
	public static final String SETTINGS_PREFS_AVATAR = "AVATAR";
	public static final String SETTINGS_PREFS_LOCATION = "LOCATION";
	
	public boolean onCreateOptionsMenu(Menu menu)
	{
		super.onCreateOptionsMenu(menu);
		
		getMenuInflater().inflate(R.menu.nav_menu, menu);
		
		menu.findItem(R.id.menuitem_loginRegister).setIntent(new Intent(this, LoginRegisterActivity.class));
		menu.findItem(R.id.menuitem_variety).setIntent(new Intent(this, VarietyActivity.class));
		menu.findItem(R.id.menuitem_rating).setIntent(new Intent(this, RatingActivity.class));
		menu.findItem(R.id.menuitem_location).setIntent(new Intent(this, LocationActivity.class));
		return true;
	}
}
