package com.example.lecture13;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainSearchActivity extends BaseActivity {
	private static final int TAKE_AVATAR_CAMERA_REQUEST = 1;

	SharedPreferences settings;
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main_search);
		
		settings = getSharedPreferences(SETTINGS_PREFS, Context.MODE_PRIVATE);
		
		initContButton();
	}
	
	public void initContButton()
	{
		Button button = (Button)findViewById(R.id.Button_main_search);
		button.setOnClickListener(new OnClickListener(){
			public void onClick(View view) {
				
				startActivity(new Intent(MainSearchActivity.this, RatingActivity.class));
			}
		});
	}
}