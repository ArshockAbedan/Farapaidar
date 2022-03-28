package com.farapaidar.fara009dialogfragment;

import android.os.Bundle;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.DialogFragment;
import android.app.FragmentTransaction;
import android.view.Menu;
import android.view.View;

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
	
	
	@SuppressLint("NewApi")
	public void Dialog(View view)
	{
		DialogFragment newFragment = MyDialogFragment.newInstance();
		newFragment.setStyle(DialogFragment.STYLE_NO_TITLE, 0);
	    newFragment.show(getFragmentManager(), "dialog");

	}

}
