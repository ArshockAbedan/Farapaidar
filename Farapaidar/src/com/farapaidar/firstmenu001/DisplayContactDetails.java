package com.farapaidar.firstmenu001;

import com.farapaidar.firstmenu001.Contact;
import com.farapaidar.firstmenu001.DatabaseHandler;
import com.farapaidar.firstmenu001.ContactFirstPage;
import com.farapaidar.firstmenu001.R;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.widget.TextView;

public class DisplayContactDetails extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_display_contact_details);
		
		

		// Get the message from the intent
	    Intent intent = getIntent();
	    String Id = intent.getStringExtra(ContactFirstPage.EXTRA_MESSAGE);

	    
	    DatabaseHandler db = new DatabaseHandler(this);
	    Contact contact = db.getContactById(Id);

	    

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
	    
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.display_contact_details, menu);
		return true;
	}

}
