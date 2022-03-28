package com.farapaidar.firstmenu001;

import static com.farapaidar.firstmenu001.Constant.FIFTH_COLUMN;
import static com.farapaidar.firstmenu001.Constant.FIRST_COLUMN;
import static com.farapaidar.firstmenu001.Constant.FOURTH_COLUMN;
import static com.farapaidar.firstmenu001.Constant.SECOND_COLUMN;
import static com.farapaidar.firstmenu001.Constant.THIRD_COLUMN;
import static com.farapaidar.firstmenu001.Constant.SIXTH_COLUMN;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


import com.farapaidar.firstmenu001.Contact;
import com.farapaidar.firstmenu001.DatabaseHandler;
import com.farapaidar.firstmenu001.DisplayContactDetails;
import com.farapaidar.firstmenu001.R;
import com.farapaidar.firstmenu001.ListviewAdapterContact;

import android.os.Bundle;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Toast;

public class ContactFirstPage extends Activity {
	
	
	 public final static String EXTRA_MESSAGE = "com.farapaidar.firstmenu001.MESSAGE";
	    
	 //public static boolean flagcontact = false;  
	 public static boolean FlagForOrderInContacts;
	 
	    @SuppressWarnings("rawtypes")
		private ArrayList<HashMap> list;

	@SuppressLint("SdCardPath")
	@SuppressWarnings({ "rawtypes"})
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_contact_first_page);

		DatabaseHandler db = new DatabaseHandler(this);

        // Reading all contacts
        Log.d("Reading: ", "Reading all contacts..");
        List<Contact> contacts = db.getAllContacts(MainMenuActivity.OperatorGuid); 
        
        
        ListView lview = (ListView) findViewById(R.id.listViewContacts);
        
        list =  GridAdapterList(contacts);
        
        ListviewAdapterContact adapter = new ListviewAdapterContact(this, list);
        lview.setAdapter(adapter);
        
       
        
        // listening to single list item on click
        lview.setOnItemClickListener(new OnItemClickListener() {
        	
        	HashMap hashMapItem = new HashMap();
        	
          @Override
		public void onItemClick(AdapterView<?> parent, View view,
              int position, long id) {
        	  OnitemClickFunc(hashMapItem,  position,id);       	  
          }     

        });
		
	}

	
	
	@SuppressWarnings({ "rawtypes"})
	@SuppressLint({ "NewApi", "CutPasteId" })
	public void SearchButtonFunc(View View) {
		
		ListView lview = (ListView) findViewById(R.id.listViewContacts);
		lview.setAdapter(null);		
		
		EditText textEdit = (EditText)  findViewById(R.id.editText1);
		

	    String PersonName = textEdit.getText().toString().replace('+', '%');
	    
	    if (PersonName.isEmpty())// search when textbox is NOT Clear
	    {
	    	
	    	lview = (ListView) findViewById(R.id.listViewContacts);
	        lview.setAdapter(null);	
	        DatabaseHandler db = new DatabaseHandler(this);
	    	  List<Contact> contacts = db.getAllContacts(MainMenuActivity.OperatorGuid);      

	    	  list =  GridAdapterList(contacts);
	    	  
		        ListviewAdapterContact adapter = new ListviewAdapterContact(this, list);
		        lview.setAdapter(adapter);
		     // listening to single list item on click
		        lview.setOnItemClickListener(new OnItemClickListener() {
		        	
		        	HashMap hashMapItem = new HashMap();
		        	
		          @Override
				public void onItemClick(AdapterView<?> parent, View view,
		              int position, long id) {
		            	  OnitemClickFunc(hashMapItem,  position,id);
		            	  }     
		        });
		    
	        
	    } 
	    else{// search when textbox is NOT Clear
	    	

	    	DatabaseHandler db1 = new DatabaseHandler(this);
	    
	    List<Contact> contacts = db1.getSpecificContacts(PersonName.toString());

	    
	    ListView lviewspecific = (ListView) findViewById(R.id.listViewContacts);
	    
	    list =  GridAdapterList(contacts);
        
        ListviewAdapterContact adapter = new ListviewAdapterContact(this, list);
        lviewspecific.setAdapter(adapter);
        
        // listening to single list item on click
        lview.setOnItemClickListener(new OnItemClickListener() {
        	HashMap hashMapItem = new HashMap();
        	
          public void onItemClick(AdapterView<?> parent, View view,
              int position, long id) {
        	  OnitemClickFunc(hashMapItem,  position,id);
        	  }
          });
	    }

	}


	// After click on an item in Grid, item's details will be shown in next page 
	public void SendContact(long id, List<Contact> contacts){
		Intent intent = new Intent(this,DisplayContactDetails.class);
		intent.putExtra(EXTRA_MESSAGE, id);
	    startActivity(intent);
		
	}
	
	
	
	
	// making header & Contents of grid's Adapter  
		@SuppressWarnings({ "rawtypes", "unchecked" })
		private ArrayList<HashMap> GridAdapterList(List<Contact> contacts)
		{
			list = new ArrayList<HashMap>();
		   /* 
		    
		    // Setting Headers
		    HashMap tempH = new HashMap();
		    tempH.put(FIRST_COLUMN,"کد");
		    tempH.put(SECOND_COLUMN,"عنوان");
		    tempH.put(THIRD_COLUMN, "موبایل");
		    tempH.put(FOURTH_COLUMN, "مانده حساب");
		    tempH.put(FIFTH_COLUMN, "تشخیص");
	    list.add(tempH);
*/
	    
	    // Adding Items
	        for (Contact cn : contacts) {        	   	       	 
	        	 
	             HashMap temp = new HashMap();
	                 temp.put(FIRST_COLUMN,cn.getID());
	                 temp.put(SECOND_COLUMN,cn.getName());
	                 temp.put(THIRD_COLUMN, cn.getMobile());
	                 temp.put(FOURTH_COLUMN, cn.getDeposit());
	                 temp.put(FIFTH_COLUMN, cn.getDetection());
	                 temp.put(SIXTH_COLUMN, cn.getAddress());
	             list.add(temp);
	        	
	        	
	            String log = "Id: "+cn.getID()+" ,Name: " + cn.getName() + " ,Phone: " + cn.getPhoneNumber1();
	                // Writing Contacts to log
	        Log.d("Name: ", log);

	        }
	        
	        return list;

		}
		
		
		// Run after an Item (a contact) clicked in List(datagrid)
		@SuppressWarnings("rawtypes")
		public void OnitemClickFunc(HashMap hashMapItem, int position, long id)
		{
			hashMapItem =  (HashMap) ((ListView) findViewById(R.id.listViewContacts)).getItemAtPosition(position);

			String ItemID = hashMapItem.get(FIRST_COLUMN).toString();
			
			if (ItemID!= "کد")
			{
				
				if (FlagForOrderInContacts==false)
				{
				// Launching new Activity on selecting single List Item           
				Intent intent = new Intent(getApplicationContext(), DisplayContactDetails.class);
                       
				// sending data to new activity              
				intent.putExtra(EXTRA_MESSAGE, ItemID);              
				startActivity(intent);
				}
				else // if (FlagForOrderInContacts==true) means when this form loaded from order form
				{
					DatabaseHandler db = new DatabaseHandler(this);
				    
				    db.deleteAllContactsTemp();
				    
				    Contact Selectedcontact = db.getContactById(ItemID);
				    db.addContactTemp(Selectedcontact);
				    
				    Toast.makeText(getApplicationContext(), "مشتری انتخاب شد", Toast.LENGTH_LONG).show();
				    FlagForOrderInContacts = false;
				    finish();
				}
			}
		}
		

}
