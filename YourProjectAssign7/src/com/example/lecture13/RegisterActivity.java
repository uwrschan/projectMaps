package com.example.lecture13;

import android.os.Bundle;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class RegisterActivity extends BaseActivity {
	SharedPreferences settings;
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_register);
		
		settings = getSharedPreferences(SETTINGS_PREFS, Context.MODE_PRIVATE);
		
		initContButton();

	}
	
	public void initContButton()
	{
		Button button = (Button)findViewById(R.id.Button_profile_submit);
		button.setOnClickListener(new OnClickListener(){
			public void onClick(View view) {
				
				savePreferences();
				startActivity(new Intent(RegisterActivity.this, MainSearchActivity.class));
			}
		});
	}
	
	private void initTextView(String key, int id)
    {
    	if(settings.contains(key))
        {
        	String prefText = settings.getString(key, "");
        	TextView textView = (TextView)findViewById(id);
        	textView.setText(prefText);
        }
    }
	
	public void savePreferences()
	{
		
		Editor editor = settings.edit();
		editor.commit();
	}

	//I removed this auto item to allow baseactivity menu to show
/*	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
*/
}
