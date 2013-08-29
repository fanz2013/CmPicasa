package com.example.cmpicasa;

import com.example.cmpicasa.R;
import com.example.cmpicasa.contentprovider.ImageDatabase;
import com.example.cmpicasa.contentprovider.ImageProvider;

import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.content.ContentValues;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}


	public void onClickAddName(View view) {
		ContentValues values = new ContentValues();
		EditText editTextName = (EditText) findViewById(R.id.txtName);
		EditText editTextAddress = (EditText) findViewById(R.id.txtAdress);
		values.put(ImageDatabase.TITLE, editTextName.getText().toString());
		values.put(ImageDatabase.HTTP_URL, editTextAddress.getText().toString());
		Uri uri = getContentResolver().insert(ImageProvider.IMAGE_URI, values);
		
		Toast.makeText(getBaseContext(), 
			      uri.toString(), Toast.LENGTH_LONG).show();
	}
}
