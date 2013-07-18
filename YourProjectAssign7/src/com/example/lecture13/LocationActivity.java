package com.example.lecture13;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

public class LocationActivity extends BaseActivity {
	private static final int TAKE_AVATAR_CAMERA_REQUEST = 1;

	SharedPreferences settings;
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_location);
		

		settings = getSharedPreferences(SETTINGS_PREFS, Context.MODE_PRIVATE);
		
		//initContButton();
	}
	/*
	public void initContButton()
	{
		Button button = (Button)findViewById(R.id.Button_Location);
		button.setOnClickListener(new OnClickListener(){
			public void onClick(View view) {
				
//				savePreferences();
				startActivity(new Intent(LocationActivity.this, LoginRegisterActivity.class));
			}
		});
	}
	*/
	
	private void initTextView(String key, int id)
    {
    	if(settings.contains(key))
        {
        	String prefText = settings.getString(key, "");
        	TextView textView = (TextView)findViewById(id);
        	textView.setText(prefText);
        }
    }
}
