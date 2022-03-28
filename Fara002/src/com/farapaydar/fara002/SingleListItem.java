package com.farapaydar.fara002;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.widget.TextView;

public class SingleListItem extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//setContentView(R.layout.activity_single_list_item);
		
		 this.setContentView(R.layout.single_list_item_view);
		
		TextView txtProduct = (TextView) findViewById(R.id.item_label);
        
        Intent i = getIntent();
        // getting attached intent data
        String item = i.getStringExtra("item");
        // displaying selected product name
        txtProduct.setText(item);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.single_list_item, menu);
		return true;
	}

}
