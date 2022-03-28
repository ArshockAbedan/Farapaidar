package com.farapaidar.fara003simpledb;


import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.support.v4.app.NavUtils;
import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;

public class DisplayContactDetails extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_display_contact_details);
		// Show the Up button in the action bar.
		setupActionBar();
		
		
		// Get the message from the intent
	    Intent intent = getIntent();
	    String idString = intent.getStringExtra(FirstPage.EXTRA_MESSAGE);
	    
	    
	    int id = Integer.parseInt(idString);
	    
	    DatabaseHandler db = new DatabaseHandler(this);
	    Contact contact = db.getContact(id);

	    

	    // fill value columns
	    ((TextView) findViewById(R.id.txtVIdValue)).setText(String.valueOf(contact.getID()));
	    ((TextView) findViewById(R.id.txtVNameValue)).setText(String.valueOf(contact.getName()));
	    ((TextView) findViewById(R.id.txtVRegionNameValue)).setText(String.valueOf(contact.getRegionName()));
	    ((TextView) findViewById(R.id.txtVPhoneNumber1Value)).setText(String.valueOf(contact.getPhoneNumber1()));
	    ((TextView) findViewById(R.id.txtVPhoneNumber2Value)).setText(String.valueOf(contact.getPhoneNumber2()));
	    ((TextView) findViewById(R.id.txtVPhoneNumber3Value)).setText(String.valueOf(contact.getPhoneNumber3()));
	    ((TextView) findViewById(R.id.txtVPhoneNumber4Value)).setText(String.valueOf(contact.getPhoneNumber4()));
	    ((TextView) findViewById(R.id.txtVAddressValue)).setText(String.valueOf(contact.getAddress()));
	    ((TextView) findViewById(R.id.txtVMobileValue)).setText(String.valueOf(contact.getMobile()));
	    ((TextView) findViewById(R.id.txtVFaxValue)).setText(String.valueOf(contact.getFax()));
	    ((TextView) findViewById(R.id.txtVDepositValue)).setText(String.valueOf(contact.getDeposit()));
	    ((TextView) findViewById(R.id.txtVDetectionValue)).setText(String.valueOf(contact.getDetection()));
	    
	   
	    /*
	    // Create the text view
	    TextView textView = new TextView(this);
	    textView.setTextSize(40);
	    textView.setText(idString);

	    // Set the text view as the activity layout
	    setContentView(textView);
		*/
		
	}

	/**
	 * Set up the {@link android.app.ActionBar}, if the API is available.
	 */
	@TargetApi(Build.VERSION_CODES.HONEYCOMB)
	private void setupActionBar() {
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
			getActionBar().setDisplayHomeAsUpEnabled(true);
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.display_contact_details, menu);
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

}
