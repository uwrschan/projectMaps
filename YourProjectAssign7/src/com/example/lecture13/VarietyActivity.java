package com.example.lecture13;

import java.io.File;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore.Images.Media;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

public class VarietyActivity extends BaseActivity {
	private static final int TAKE_AVATAR_CAMERA_REQUEST = 1;
	private static final int TAKE_AVATAR_GALLERY_REQUEST = 2;

	SharedPreferences settings;
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_variety);
		

		settings = getSharedPreferences(SETTINGS_PREFS, Context.MODE_PRIVATE);
		
		initAvatar();
		initContButton();
		initTextField(R.id.edittext_location_search, SETTINGS_PREFS_LOCATION);
		
	}
	
	private void initAvatar()
	{
		ImageButton avatarButton = (ImageButton)findViewById(R.id.imagebutton_avatar);
		
		//show the picture saved in the shared preferences
		if(settings.contains(SETTINGS_PREFS_AVATAR))
		{
			String uriString = settings.getString(SETTINGS_PREFS_AVATAR, "");
			
			if(!uriString.equals(""))
			{
				Uri imageUri = Uri.parse(uriString);
				avatarButton.setImageURI(imageUri);
			}
			else
			{
				avatarButton.setImageResource(R.drawable.main_search_image);
			}
		}

		avatarButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
				startActivityForResult(Intent.createChooser(cameraIntent, "Take your photo"), TAKE_AVATAR_CAMERA_REQUEST);
			}
		});
		
		avatarButton.setOnLongClickListener(new OnLongClickListener() {
			@Override
			public boolean onLongClick(View v) {
				// TODO Auto-generated method stub
				Intent galleryIntent = new Intent(Intent.ACTION_PICK);
				galleryIntent.setType("image/*");
				startActivityForResult(Intent.createChooser(galleryIntent, "Choose your Avatar"), TAKE_AVATAR_GALLERY_REQUEST);
				return true;
			}
		});
	}
	
	public void onActivityResult(int requestCode, int resultCode, Intent intent)
	{
		//if they cancelled taking their picture, don't attempt to save it
		if(resultCode == Activity.RESULT_CANCELED) return;
		
		//as long as you are returning from the camera
		if(requestCode == TAKE_AVATAR_CAMERA_REQUEST)
		{
			if(resultCode == Activity.RESULT_OK)
			{
				//get data from camera and turn it into a bitmap
				Bitmap cameraPic = (Bitmap)intent.getExtras().get("data");
				if(cameraPic == null) return;
				
				saveAvatar(cameraPic);
			}
		}
		else if (requestCode == TAKE_AVATAR_GALLERY_REQUEST)
		{
			Uri photoUri = intent.getData();
			if(photoUri == null) return;
			
			try
			{
				Bitmap bitmap = Media.getBitmap(getContentResolver(), photoUri);
				bitmap = scaleBitmap(bitmap, 500);
				saveAvatar(bitmap);
			}
			catch(Exception e)
			{
				
			}
		}
	}
	
	private Bitmap scaleBitmap(Bitmap bitmap, int maxSize)
	{
		int originalWidth = bitmap.getWidth();
		int originalHeight = bitmap.getHeight();
		
		int width = scaleSide(originalWidth, originalHeight, maxSize);
		int height = scaleSide(originalHeight, originalWidth, maxSize);
		
		//return Bitmap.createScaledBitmap(src, dstWidth, dstHeight, filter);
		return Bitmap.createScaledBitmap(bitmap, width, height, false);
	}
	
	private int scaleSide(int side1, int side2, int max)
	{
		if(side1 > side2)
		{
			return max;
		}
		
		return (int)(side1 * (double)max/(double)side2);
	}
	
	private void saveAvatar(Bitmap pic)
	{
		String avatarFile = "main_search_image.png";
		
		try
		{
			//pic.compress(format, quality, stream);
			pic.compress(CompressFormat.PNG, 100, openFileOutput(avatarFile, MODE_PRIVATE));
		}
			catch(Exception e)
		{
			return;
		}
		
		//make a new file and gives you the path.
		Uri avatarUri = Uri.fromFile(new File(this.getFilesDir(), avatarFile));
		
		//get the image button
		ImageButton avatarButton = (ImageButton)findViewById(R.id.imagebutton_avatar);
		
		//save avatar to button
		avatarButton.setImageURI(null);
		avatarButton.setImageURI(avatarUri);
		
		//save the path to the shared preferences (put into edit mode)
		Editor editor = settings.edit();
		editor.putString(SETTINGS_PREFS_AVATAR,  avatarUri.getPath());
		editor.commit();
	}


	public void initContButton()
	{
		Button button = (Button)findViewById(R.id.Button_Variety);
		button.setOnClickListener(new OnClickListener(){
			public void onClick(View view) {
				
				savePreferences();
				startActivity(new Intent(VarietyActivity.this, LocationActivity.class));
			}
		});
	}
	
	
	private void initTextField(final int fieldId, String prefString)
	{
		EditText editText = (EditText)findViewById(fieldId);
		
		if(settings.contains(prefString))
		{
			String firstName = settings.getString(prefString, "");
			editText.setText(firstName);
		}
	}	
/*
	private void initTextView(String key, int id)
    {
    	if(settings.contains(key))
        {
        	String prefText = settings.getString(key, "");
        	TextView textView = (TextView)findViewById(id);
        	textView.setText(prefText);
        }
    }
*/
	
	public void savePreferences()
	{
		String location = ((EditText)findViewById(R.id.edittext_location_search)).getText().toString();
		
		Editor editor = settings.edit();
		editor.putString(SETTINGS_PREFS_LOCATION, location);
		editor.commit();
	}
}
