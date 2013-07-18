package com.example.lecture13;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class RatingActivity extends BaseActivity {
	private static final int TAKE_AVATAR_CAMERA_REQUEST = 1;

	SharedPreferences settings;
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_rating);
		

		settings = getSharedPreferences(SETTINGS_PREFS, Context.MODE_PRIVATE);
		
		initImageView(SETTINGS_PREFS_AVATAR, R.id.imgview_pho);
		initTextView(SETTINGS_PREFS_LOCATION, R.id.edittext_location_search);
		initContButton();
	}
	
	public void initImageView(String key, int id)
	{
		if(!settings.contains(key)) return;
		
		ImageView imageView = (ImageView)findViewById(id);
		String uriString = settings.getString(key,  "");
		
		if(uriString.equals(""))return;
		
		Uri imageUri = Uri.parse(uriString);
		imageView.setImageURI(imageUri);
	}
	
	public void initContButton()
	{
		Button button = (Button)findViewById(R.id.Button_Rating);
		button.setOnClickListener(new OnClickListener(){
			public void onClick(View view) {
				
				startActivity(new Intent(RatingActivity.this, VarietyActivity.class));
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
}
