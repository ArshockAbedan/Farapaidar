package com.farapaidar.firstmenu001;

import static com.farapaidar.firstmenu001.Constant.FIRST_COLUMN;
import static com.farapaidar.firstmenu001.Constant.SECOND_COLUMN;
import static com.farapaidar.firstmenu001.Constant.THIRD_COLUMN;
import static com.farapaidar.firstmenu001.Constant.FOURTH_COLUMN;
import static com.farapaidar.firstmenu001.Constant.FIFTH_COLUMN;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import android.widget.AdapterView;

import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ListView;
import android.app.Activity;
import android.app.DialogFragment;

public class CartableActivity extends Activity {
	
	

	@SuppressWarnings("rawtypes")
	private ArrayList<HashMap> list;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_cartable);

		DatabaseHandler db = new DatabaseHandler(this);

        // Reading all Inboxes
        Log.d("Reading: ", "Reading all Inboxes..");
        
        
        String OpertorGuidStr = ConvertGuidToGuidBinaryString(UUID.fromString(MainMenuActivity.OperatorGuid));
        List<Inboxes> inboxes = db.getAllInboxes(OpertorGuidStr);   

        ListView lview = (ListView) findViewById(R.id.listViewCartable);

        list =  GridAdapterList(inboxes);
        ListviewAdapterCartable adapter = new ListviewAdapterCartable(this, list);
        lview.setAdapter(adapter);
        
        lview.setOnItemLongClickListener(new OnItemLongClickListener() {

        	@SuppressWarnings("rawtypes")
			HashMap hashMapItem = new HashMap();
        	
			@Override
			public boolean onItemLongClick(AdapterView<?> arg0, View arg1,
					int position, long id) {
	        	  OnitemClickFunc(hashMapItem,  position,id);
				return true;
	        	  }
		});
       
	}


	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		
		DatabaseHandler db = new DatabaseHandler(this);

        // Reading all Inboxes
        Log.d("Reading: ", "Reading all Inboxes..");
        String OpertorGuidStr = ConvertGuidToGuidBinaryString(UUID.fromString(MainMenuActivity.OperatorGuid));
        List<Inboxes> inboxes = db.getAllInboxes(OpertorGuidStr);  

        ListView lview = (ListView) findViewById(R.id.listViewCartable);

        list =  GridAdapterList(inboxes);
        ListviewAdapterCartable adapter = new ListviewAdapterCartable(this, list);
        lview.setAdapter(adapter);
        
        lview.setOnItemLongClickListener(new OnItemLongClickListener() {

        	@SuppressWarnings("rawtypes")
			HashMap hashMapItem = new HashMap();
        	
			@Override
			public boolean onItemLongClick(AdapterView<?> arg0, View arg1,
					int position, long id) {
	        	  OnitemClickFunc(hashMapItem,  position,id);
				return true;
	        	  }
		});
       

	}

	
	
	// making header & Contents of grid's Adapter  
		@SuppressWarnings({ "rawtypes", "unchecked" })
		private ArrayList<HashMap> GridAdapterList(List<Inboxes> inboxes)
		{
			   list = new ArrayList<HashMap>();

			    // Adding Items
			        for (Inboxes inbox : inboxes) {        		        	       	 
			        	 
			             HashMap temp = new HashMap();
			                 temp.put(FIRST_COLUMN,inbox.getTaskTitle());
			                 temp.put(SECOND_COLUMN,inbox.getLocalDate());
			                 temp.put(THIRD_COLUMN, inbox.getLocalTime());
			                 temp.put(FOURTH_COLUMN, inbox.getGuid());
			                 temp.put(FIFTH_COLUMN, inbox.getVisited());
			             list.add(temp);
			        }
			        
			        return list;
		}
		
		
		// Run after an Item (a Cartable) clicked in List(DataGrid)
		@SuppressWarnings("rawtypes")
		public void OnitemClickFunc(HashMap hashMapItem, int position, long id)
		{
			  hashMapItem =  (HashMap) ((ListView) findViewById(R.id.listViewCartable)).getItemAtPosition(position);

			  String ItemGuid =  hashMapItem.get(FOURTH_COLUMN).toString();				
			  //String ItemName =  hashMapItem.get(FIRST_COLUMN).toString();
			  
			  FragmentOfCartableFirstPage fragment = FragmentOfCartableFirstPage.newInstance();
			  fragment.GuidInbox = ItemGuid;
			  fragment.setStyle(DialogFragment.STYLE_NO_TITLE, 0);
			  fragment.show(getFragmentManager(), "dialog");

				//Toast.makeText(getApplicationContext(),ItemName, Toast.LENGTH_SHORT).show();  
  				
		}
		
		public static String ConvertGuidToGuidBinaryString(UUID guid)
	    {
			String guidstr = guid.toString();
	        char[] guidBytes =  guidstr.toCharArray();
	        StringBuilder guidBinary = new StringBuilder();
	        for (char guidByte : guidBytes) {			
	        	guidBinary.append(guidByte);
			}
	        
	        return String.valueOf(guidBinary);
	    }

		
}
