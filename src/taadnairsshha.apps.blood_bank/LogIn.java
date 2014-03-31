package taadnairsshha.apps.bloodbank;

import java.util.concurrent.ExecutionException;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
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
		String phone = edit.getText().toString();
		edit = (EditText) (findViewById(R.id.edit_password));
		String password = edit.getText().toString();
		
		// Checking if they are not empty.
		if(phone.length()==0 || password.length()==0)
		{
			// creating a toast depending on which field is empty.
			Context context = getApplicationContext();
			String text = "";
			if(phone.length()==0 && password.length()==0)
				text = "Please enter Phone No. and Password!";
			else if(phone.length()==0)
				text = "Please enter Phone Number!";
			else
				text = "Please enter Password!";
			
			int duration = Toast.LENGTH_SHORT;
			
			Toast toast = Toast.makeText(context, text, duration);
			toast.show();
		}
		else
		{
			try
			{
				String reply = new SendRequest().execute("login.php", "2", "Phone", phone, "Password", password).get();
				
				if(reply.equals("0"))
					Toast.makeText(getApplicationContext(), "This number is not registered!", Toast.LENGTH_SHORT).show();
				else if(reply.equals("1"))
					Toast.makeText(getApplicationContext(), "Incorrect Password!", Toast.LENGTH_SHORT).show();
				else if(reply.equals("2"))
					Toast.makeText(getApplicationContext(), "Error. Please try again!", Toast.LENGTH_SHORT).show();
				else
				{
					
					String[] user_info = reply.split("\\$");
					SharedPreferences sharedPref = getSharedPreferences(getString(R.string.pref_file), Context.MODE_PRIVATE);
					SharedPreferences.Editor editor = sharedPref.edit();
					editor.putString("Name", user_info[0]);
					editor.putString("Phone", user_info[1]);
					editor.putString("Blood Group", user_info[2]);
					editor.putString("City", user_info[3]);
					editor.commit();
								
					// starting the home activity.
					Intent intent_home = new Intent(this, Home.class);
					startActivity(intent_home);
				}
				
			}
			catch (InterruptedException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			catch (ExecutionException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	
	public void newUser(View view)
	{
		Intent intent = new Intent(this, VerifyContact.class);
		startActivity(intent);
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
	}
}
