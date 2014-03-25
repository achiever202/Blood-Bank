package taadnairsshha.apps.bloodbank;

import android.app.Activity;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v4.app.NavUtils;
import android.telephony.SmsManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class VerifyContact extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_verify_contact);
		// Show the Up button in the action bar.
		setupActionBar();
	}

	/**
	 * Set up the {@link android.app.ActionBar}.
	 */
	private void setupActionBar() {

		getActionBar().setDisplayHomeAsUpEnabled(true);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.verify_contact, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			// This ID represents the Home or Up button. In the case of this
			// activity, the Up button is shown. Use NavUtils to allow users
			// to navigate up one level in the application structure. For
			// more details, see the Navigation pattern on Android Design:
			//
			// http://developer.android.com/design/patterns/navigation.html#up-vs-back
			//
			NavUtils.navigateUpFromSameTask(this);
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
	public void sendSMS(View view)
	{
		String message = "";
		TextView text = (TextView) findViewById(R.id.text_error);
		text.setText(message);
		
		EditText edit = (EditText)findViewById(R.id.edit_phone);
		String phone_number = edit.getText().toString();
		if(phone_number.length()==10)
		{
			SmsManager sms = SmsManager.getDefault();
			sms.sendTextMessage(phone_number, null, "+91"+phone_number, null, null);
			
			new CountDownTimer(60000, 10000)
			{
				public void onTick(long millisUntilFinished)
				{
				}
				
				public void onFinish()
				{
					String message = "Your number could not be verified.\nPlease check your number and try again.";
					TextView text = (TextView) findViewById(R.id.text_error);
					text.setText(message);
				}
			}.start();
		}
		else
			Toast.makeText(getApplicationContext(), "Please enter a valid Phone Number!", Toast.LENGTH_SHORT).show();
	}

}
