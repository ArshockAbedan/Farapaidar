package com.farapaidar.fara003simpledb;


import static com.farapaidar.fara003simpledb.Constant.FIRST_COLUMN;
import static com.farapaidar.fara003simpledb.Constant.SECOND_COLUMN;
import static com.farapaidar.fara003simpledb.Constant.THIRD_COLUMN;
import static com.farapaidar.fara003simpledb.Constant.FOURTH_COLUMN;
import static com.farapaidar.fara003simpledb.Constant.FIFTH_COLUMN;



import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;




import android.os.Bundle;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
//import android.app.ListActivity;
//import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.view.Menu;
import android.view.View;

import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;


import android.widget.EditText;

import android.widget.ListView;



public class FirstPage extends Activity {
	
	//private GridView gridView;
    public static ArrayList<ArrayList<String>> ArrayofName = new ArrayList<ArrayList<String>>();
    public static ArrayList<String> ArrayofName2 = new ArrayList<String>();
    public final static String EXTRA_MESSAGE = "com.farapaidar.fara003simpledb.MESSAGE";
    
    
    @SuppressWarnings("rawtypes")
	private ArrayList<HashMap> list;

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_first_page);
		
		ArrayofName.clear();
		ArrayofName2.clear();
	        DatabaseHandler db = new DatabaseHandler(this);

	        /**
	         * CRUD Operations
	         * */
	        // Inserting Contacts
	        Log.d("Insert: ", "Inserting ..");
	        
	        db.addContact(new Contact("Aref", "Shomal", "3228345", "1245","1","2","Rasht","09111434981","23546","14500","rt"));
	        db.addContact(new Contact("Ahmad", "Gharb", "25697", "148","3","1574","Lahijan","09113367449","58974","12000","t"));
	        
	        

	        db.addContact(new Contact("عارف عابدجوی", "شمال", "255", "1245","1","2","Rasht","09111434981","23546","12,185,102,100","rt"));
	        db.addContact(new Contact("آراد حسن پور خمامی اصل", "غرب", "25697", "148","3","1574","Lahijan","09113367449","58974","7,541,120,122","t"));
	        db.addContact(new Contact("عارف عابدجوی", "شمال", "255", "1245","1","2","Rasht","09111434981","23546","12,185,102,100","rt"));
	        db.addContact(new Contact("آراد حسن پور خمامی اصل", "غرب", "25697", "148","3","1574","Lahijan","09113367449","58974","7,541,120,122","t"));
	        db.addContact(new Contact("عارف عابدجوی", "شمال", "255", "1245","1","2","Rasht","09111434981","23546","12,185,102,100","rt"));
	        db.addContact(new Contact("آراد حسن پور خمامی اصل", "غرب", "25697", "148","3","1574","Lahijan","09113367449","58974","7,541,120,122","t"));
	        
	        
	        db.addContact(new Contact("اصغر اکبرنیا" , "جنوب" , "1124" , "145","78","5454","بندر","09116085522","1458","152402","rt"));

	        
	        // Reading all contacts
	        Log.d("Reading: ", "Reading all contacts..");
	        List<Contact> contacts = db.getAllContacts();      
	        
	        
	        ListView lview = (ListView) findViewById(R.id.listView1);
	        //list.clear();

	        list = new ArrayList<HashMap>();
	        // Setting Headers
		    HashMap tempH = new HashMap();
		    tempH.put(FIRST_COLUMN,"کد");
		    tempH.put(SECOND_COLUMN,"عنوان");
		    tempH.put(THIRD_COLUMN, "موبایل");
		    tempH.put(FOURTH_COLUMN, "مانده حساب");
		    tempH.put(FIFTH_COLUMN, "تشخیص");
	    list.add(tempH);

	    
	    // Adding Items
	        for (Contact cn : contacts) {        		        	       	 
	        	 
	             HashMap temp = new HashMap();
	                 temp.put(FIRST_COLUMN,cn.getID());
	                 temp.put(SECOND_COLUMN,cn.getName());
	                 temp.put(THIRD_COLUMN, cn.getMobile());
	                 temp.put(FOURTH_COLUMN, cn.getDeposit());
	                 temp.put(FIFTH_COLUMN, cn.getDetection());
	             list.add(temp);
	        	
	        	
	            String log = "Id: "+cn.getID()+" ,Name: " + cn.getName() + " ,Phone: " + cn.getPhoneNumber1();
	                // Writing Contacts to log
	        Log.d("Name: ", log);

	        }


	        
	        listviewAdapter adapter = new listviewAdapter(this, list);
	        lview.setAdapter(adapter);
	        
	       // db.getAllContacts();

	       // gridView = (GridView) findViewById(R.id.gridView1);
	      //  gridView.setAdapter(null);
	        
/*
	        ArrayAdapter<ArrayList<String>> adapter = new ArrayAdapter<ArrayList<String>>(this,
	                android.R.layout.simple_list_item_1, ArrayofName);

	        gridView.setAdapter(adapter);
*/        
	        
	        
	        
	        
	        // listening to single list item on click
	        lview.setOnItemClickListener(new OnItemClickListener() {
	        	
	        	HashMap hashMapItem = new HashMap();
	        	
	          public void onItemClick(AdapterView<?> parent, View view,
	              int position, long id) {
	        	  
	        	  hashMapItem =  (HashMap) ((ListView) findViewById(R.id.listView1)).getItemAtPosition(position);

					String ItemNumber =  hashMapItem.get(FIRST_COLUMN).toString();
	               
	              // Launching new Activity on selecting single List Item
	              Intent intent = new Intent(getApplicationContext(), DisplayContactDetails.class);
	              
	              // sending data to new activity
	              intent.putExtra(EXTRA_MESSAGE, ItemNumber); 
	             // startActivity(intent);     
	          }
	        });

	/*	
		SQLiteDatabase db = openOrCreateDatabase("DBPersons.db",0, null);
		db.execSQL("CREATE TABLE IF NOT EXISTS DbPersons(FirstName VARCHAR, LastName VARCHAR, Age INT(3));" );
		db.execSQL("INSERT INTO DbPersons VALUES ('Aref', 'Abedjooy', 12);");
		
		Toast.makeText(this, "DB Created!", Toast.LENGTH_LONG).show();
		db.close();
		*/
		
	}

	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.first_page, menu);
		return true;
	}
	
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@SuppressLint({ "NewApi", "CutPasteId" })
	public void SearchButtonFunc(View View) {
		
		ListView lview = (ListView) findViewById(R.id.listView1);
		lview.setAdapter(null);		
		
		EditText textEdit = (EditText)  findViewById(R.id.editText1);
		

	    String PersonName = textEdit.getText().toString();
	    
	    if (PersonName.isEmpty())// search when textbox is NOT Clear
	    {
	    	
	    	lview = (ListView) findViewById(R.id.listView1);
	        lview.setAdapter(null);	
	    	DatabaseHandler db = new DatabaseHandler(this);
	    	  List<Contact> contacts = db.getAllContacts();      

		        list = new ArrayList<HashMap>();    
		        
		        // Setting Headers
			    HashMap tempH = new HashMap();
			    tempH.put(FIRST_COLUMN,"کد");
			    tempH.put(SECOND_COLUMN,"عنوان");
			    tempH.put(THIRD_COLUMN, "موبایل");
			    tempH.put(FOURTH_COLUMN, "مانده حساب");
			    tempH.put(FIFTH_COLUMN, "تشخیص");
		    list.add(tempH);

		    
		    // Adding Items 
		        for (Contact cn : contacts) {        		        	       	 
		        	 
		             HashMap temp = new HashMap();
		                 temp.put(FIRST_COLUMN,cn.getID());
		                 temp.put(SECOND_COLUMN,cn.getName());
		                 temp.put(THIRD_COLUMN, cn.getMobile());
		                 temp.put(FOURTH_COLUMN, cn.getDeposit());
		                 temp.put(FIFTH_COLUMN, cn.getDetection());
		             list.add(temp);
		        }


		        
		        listviewAdapter adapter = new listviewAdapter(this, list);
		        lview.setAdapter(adapter);
		     // listening to single list item on click
		        lview.setOnItemClickListener(new OnItemClickListener() {
		        	
		        	HashMap hashMapItem = new HashMap();
		        	
		          public void onItemClick(AdapterView<?> parent, View view,
		              int position, long id) {
		        	  
		        	  hashMapItem =  (HashMap) ((ListView) findViewById(R.id.listView1)).getItemAtPosition(position);

						String ItemNumber =  hashMapItem.get(FIRST_COLUMN).toString();
		               
		              // Launching new Activity on selecting single List Item
		              Intent intent = new Intent(getApplicationContext(), DisplayContactDetails.class);
		              
		              // sending data to new activity
		              intent.putExtra(EXTRA_MESSAGE, ItemNumber); 
		              startActivity(intent);     
		          }
		        });
		    
	        
	        
/*
	        ArrayAdapter<ArrayList<String>> adapter = new ArrayAdapter<ArrayList<String>>(this,
	                android.R.layout.simple_list_item_1, ArrayofName);

	        gridView.setAdapter(adapter);

	        gridView.setOnItemClickListener(new OnItemClickListener() {
	            public void onItemClick(AdapterView<?> parent, View v,
	                int position, long id) {
	            	
	               Toast.makeText(getApplicationContext(),
	                ((TextView) v).getText(), Toast.LENGTH_SHORT).show();
	            }
	        });
	        
*/   
		
		
	    } 
	    else{// search when textbox is NOT Clear
	    	
	    	ArrayofName2.clear();
	    DatabaseHandler db1 = new DatabaseHandler(this);
	    
	    List<Contact> contacts = db1.getSpecificContacts(PersonName.toString());

	    
	    ListView lviewspecific = (ListView) findViewById(R.id.listView1);
	    
	    list = new ArrayList<HashMap>();
	    
	    
	    // Setting Headers
	    HashMap tempH = new HashMap();
	    tempH.put(FIRST_COLUMN,"کد");
	    tempH.put(SECOND_COLUMN,"عنوان");
	    tempH.put(THIRD_COLUMN, "موبایل");
	    tempH.put(FOURTH_COLUMN, "مانده حساب");
	    tempH.put(FIFTH_COLUMN, "تشخیص");
    list.add(tempH);

    
    // Adding Items
        for (Contact cn : contacts) {        	   	       	 
        	 
             HashMap temp = new HashMap();
                 temp.put(FIRST_COLUMN,cn.getID());
                 temp.put(SECOND_COLUMN,cn.getName());
                 temp.put(THIRD_COLUMN, cn.getMobile());
                 temp.put(FOURTH_COLUMN, cn.getDeposit());
                 temp.put(FIFTH_COLUMN, cn.getDetection());
             list.add(temp);
        	
        	
            String log = "Id: "+cn.getID()+" ,Name: " + cn.getName() + " ,Phone: " + cn.getPhoneNumber1();
                // Writing Contacts to log
        Log.d("Name: ", log);

        }


        
        listviewAdapter adapter = new listviewAdapter(this, list);
        lviewspecific.setAdapter(adapter);
	    
	    /*
        lview.setOnItemClickListener(new OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v,
                int position, long id) {
               Toast.makeText(getApplicationContext(),
                ((TextView) v).getText(), Toast.LENGTH_SHORT).show();
            }
        });
        */
        
     // listening to single list item on click
        lview.setOnItemClickListener(new OnItemClickListener() {
        	
        	HashMap hashMapItem = new HashMap();
        	
          public void onItemClick(AdapterView<?> parent, View view,
              int position, long id) {
        	  
        	  hashMapItem =  (HashMap) ((ListView) findViewById(R.id.listView1)).getItemAtPosition(position);

				String ItemNumber =  hashMapItem.get(FIRST_COLUMN).toString();
               
              // Launching new Activity on selecting single List Item
              Intent intent = new Intent(getApplicationContext(), DisplayContactDetails.class);
              
              // sending data to new activity
              intent.putExtra(EXTRA_MESSAGE, ItemNumber); 
              startActivity(intent);     
          }
        });
	    
	    /*
       
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, ArrayofName2 );
       
        gridView.setAdapter(adapter);

        gridView.setOnItemClickListener(new OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v,
                int position, long id) {
               Toast.makeText(getApplicationContext(),
                ((TextView) v).getText(), Toast.LENGTH_SHORT).show();
            }
        });
        
        */
	}
	    
	    
	    
	}


	// After click on an item in Grid, item's details will be shown in next page 
	public void SendContact(long id, List<Contact> contacts){
		Intent intent = new Intent(this,DisplayContactDetails.class);
		intent.putExtra(EXTRA_MESSAGE, id);
	    startActivity(intent);
		
	}
	


}
