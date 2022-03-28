package com.farapaydar.fara002;

import android.os.Bundle;
import android.app.ListActivity;
import android.content.Intent;
import android.view.*;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends ListActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		 
        // storing string resources into Array
        String[] Main_menu = getResources().getStringArray(R.array.Main_menu);
         
        // Binding resources Array to ListAdapter
        this.setListAdapter(new ArrayAdapter<String>(this, R.layout.list_item, R.id.label, Main_menu));
        
        ListView lv = getListView();
        
        // listening to single list item on click
        lv.setOnItemClickListener(new OnItemClickListener() {
          public void onItemClick(AdapterView<?> parent, View view,
              int position, long id) {
               
              // selected item
              String item = ((TextView) view).getText().toString();
               
              // Launching new Activity on selecting single List Item
              Intent i = new Intent(getApplicationContext(), SingleListItem.class);
              // sending data to new activity
              i.putExtra("item", item);
              startActivity(i);
             
          }
        });
         
        //setContentView(R.layout.activity_main);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
