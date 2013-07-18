package com.example.lecture13;

import java.io.File;

import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

public class LoginRegisterActivity extends BaseActivity {
	private static final int TAKE_AVATAR_CAMERA_REQUEST = 1;
	
	SharedPreferences settings;
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login_register);
		
		settings = getSharedPreferences(SETTINGS_PREFS, Context.MODE_PRIVATE);
		
		initContButton();
	}

	public void initContButton()
	{
		Button button = (Button)findViewById(R.id.Button_Register);
		button.setOnClickListener(new OnClickListener(){
			public void onClick(View view) {
				
				savePreferences();
				startActivity(new Intent(LoginRegisterActivity.this, RegisterActivity.class));
			}
		});
	}
	
	public void savePreferences()
	{
		
		Editor editor = settings.edit();
		editor.commit();
	}
	
	//I removed this auto item to allow baseactivity menu to show
/*	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
*/
}
