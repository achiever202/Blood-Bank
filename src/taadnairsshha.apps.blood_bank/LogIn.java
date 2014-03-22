package taadnairsshha.apps.bloodbank;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class LogIn extends Activity{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_log_in);
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.log_in, menu);
		return true;
	}
	
	// This function is called when Log In button is clicked.
	public void login(View view)
	{
		// Getting the phone number and password from fields.
		EditText edit = (EditText) (findViewById(R.id.edit_phone));
		String ph = edit.getText().toString();
		edit = (EditText) (findViewById(R.id.edit_password));
		String pass = edit.getText().toString();
		
		// Checking if they are not empty.
		if(ph.length()==0 || pass.length()==0)
		{
			// creating a toast depending on which field is empty.
			Context context = getApplicationContext();
			String text = "";
			if(ph.length()==0 && pass.length()==0)
				text = "Please enter Phone No. and Password!";
			else if(ph.length()==0)
				text = "Please enter Phone Number!";
			else
				text = "Please enter Password!";
			
			int duration = Toast.LENGTH_SHORT;
			
			Toast toast = Toast.makeText(context, text, duration);
			toast.show();
		}
		else
		{
			
			// Creating Shared Preferences and writing into file.
			SharedPreferences sharedPref = getSharedPreferences(getString(R.string.pref_file), Context.MODE_PRIVATE);
			SharedPreferences.Editor editor = sharedPref.edit();
			editor.putString(getString(R.string.user_phone), ph);
			editor.putString(getString(R.string.user_password), pass);
			editor.commit();
						
			// starting the home activity.
			Intent intent = new Intent(this, Home.class);
			startActivity(intent);
		}
		
	}
	
	public void newUser(View view)
	{
		Intent intent = new Intent(this, NewUser.class);
		startActivity(intent);
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		finish();
	}
}
