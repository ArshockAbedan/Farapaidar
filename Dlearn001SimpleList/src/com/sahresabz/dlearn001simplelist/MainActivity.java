package com.sahresabz.dlearn001simplelist;

import android.R.color;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.app.ListActivity;

public class MainActivity extends ListActivity {
	
	
	TextView txtMsg;
	
	String[] items = { "Data-1","Data-2","Data-3","Data-4","Data-5"};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		
		setListAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,items));
		

		
		txtMsg = (TextView) findViewById(R.id.txtMsg);
		
		
		//ListView lv  = (ListView) findViewById(R.id.listView1);
		getListView().setBackgroundColor(color.white);
		
	}

	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		// TODO Auto-generated method stub
		super.onListItemClick(l, v, position, id);
		String str = "Position="+ position + "    Name:"+ items[position];
		txtMsg.setText(str);
	}

	
	
	
	
}
