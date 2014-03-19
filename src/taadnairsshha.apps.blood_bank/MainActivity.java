package taadnairsshha.apps.bloodbank;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		// creating a shared preference object.
		SharedPreferences sharedPref = getSharedPreferences(getString(R.string.pref_file), Context.MODE_PRIVATE);
		
		// if the shared preference file exists, that is user is registered.
		// forwarding to the home screen.
		if(sharedPref.contains(getString(R.string.user_id)))
		{
			Intent intent = new Intent(this, Home.class);
			startActivity(intent);
		}
		
		// else forwarding the user to the log in page.
		else
		{
			Intent intent = new Intent(this, LogIn.class);
			startActivity(intent);
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
