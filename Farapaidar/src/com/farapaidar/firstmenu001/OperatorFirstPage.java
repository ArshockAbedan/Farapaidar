package com.farapaidar.firstmenu001;


import static com.farapaidar.firstmenu001.Constant.FIRST_COLUMN;
import static com.farapaidar.firstmenu001.Constant.SECOND_COLUMN;
import static com.farapaidar.firstmenu001.Constant.THIRD_COLUMN;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;

@SuppressLint("DefaultLocale")
public class OperatorFirstPage extends Activity {
	
	public static String GuidTask = "";
	public static String TitleTask = "";
	
	public static String GuidInbox = "";
	
	 public static boolean FlagForTaskOrInbox;  // True=Task  & False=Inbox
	 
	 @SuppressWarnings("rawtypes")
		private ArrayList<HashMap> list;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_operator_first_page);
		
		DatabaseHandler db = new DatabaseHandler(this);

        // Reading all Operator
        Log.d("Reading: ", "Reading all Operator...");
        List<Operator> operators = db.getAllOperators(); 
        
        
        ListView lview = (ListView) findViewById(R.id.listViewOperators);
        
        list =  GridAdapterList(operators);
        
        ListviewAdapterOperator adapter = new ListviewAdapterOperator(this, list);
        lview.setAdapter(adapter);
        
        // listening to single list item on click
        lview.setOnItemClickListener(new OnItemClickListener() {
        	
        	@SuppressWarnings("rawtypes")
			HashMap hashMapItem = new HashMap();
        	
          @Override
		public void onItemClick(AdapterView<?> parent, View view,
              int position, long id) {
        	  OnitemClickFunc(hashMapItem, position, id);       	  
          }     

        });
	}

//////////////////////////////////////////////////////////////////////////

	
	
	
	@SuppressWarnings({ "rawtypes"})
	@SuppressLint({ "NewApi", "CutPasteId" })
	public void SearchButtonFunc(View View) {
		
		ListView lview = (ListView) findViewById(R.id.listViewOperators);
		lview.setAdapter(null);		
		
		EditText editTxtInOperatorFirstPage = (EditText)  findViewById(R.id.editTxtInOperatorFirstPage);
		

	    String VisitorName = editTxtInOperatorFirstPage.getText().toString();
	    
	    if (VisitorName.isEmpty())// search when textbox is NOT Clear
	    {
	    	
			DatabaseHandler db = new DatabaseHandler(this);

	        // Reading all Operator
	        Log.d("Reading: ", "Reading all Operator...");
	        List<Operator> operators = db.getAllOperators(); 
	        
	        
	        //ListView lview = (ListView) findViewById(R.id.listViewOperators);
	        
	        list =  GridAdapterList(operators);
	        
	        ListviewAdapterOperator adapter = new ListviewAdapterOperator(this, list);
	        lview.setAdapter(adapter);
	        
	        // listening to single list item on click
	        lview.setOnItemClickListener(new OnItemClickListener() {
	        	
	        	
				HashMap hashMapItem = new HashMap();
	        	
	          @Override
			public void onItemClick(AdapterView<?> parent, View view,
	              int position, long id) {
	        	  OnitemClickFunc(hashMapItem, position, id);       	  
	          }     

	        });
	        
	    } 
	    else{// search when textbox is NOT Clear
	    	

	    	DatabaseHandler db1 = new DatabaseHandler(this);
	    
	    	List<Operator> operators = db1.getSpecificOperatorByFullName(VisitorName.toString());

	    
	    ListView lviewspecific = (ListView) findViewById(R.id.listViewOperators);
	    
	    list =  GridAdapterList(operators);
        
	    ListviewAdapterOperator adapter = new ListviewAdapterOperator(this, list);
	    lviewspecific.setAdapter(adapter);
        
        // listening to single list item on click
	    lviewspecific.setOnItemClickListener(new OnItemClickListener() {
        	HashMap hashMapItem = new HashMap();
        	
          public void onItemClick(AdapterView<?> parent, View view,
              int position, long id) {
        	  OnitemClickFunc(hashMapItem,  position,id);
        	  }
          });
	    }

	}


	
	
	///////////////////////////////////////////////////////////////////////
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private ArrayList<HashMap> GridAdapterList(List<Operator> operators)
	{
		   list = new ArrayList<HashMap>();

		    // Setting Headers
		    HashMap tempH = new HashMap();
		    tempH.put(FIRST_COLUMN,"کد");
		    tempH.put(SECOND_COLUMN,"نام و نام خانوادگی");
		    tempH.put(THIRD_COLUMN,"");
	    list.add(tempH);

		   int i = 1;
		    // Adding Items
		        for (Operator op : operators) {        		        	       	 
		        	 
		             HashMap temp = new HashMap();
		                 temp.put(FIRST_COLUMN,String.valueOf(i));
		                 i = i+1;
		                 temp.put(SECOND_COLUMN,op.getFullName());
		                 temp.put(THIRD_COLUMN,op.getGuid());
		             list.add(temp);
		        }
		        
		        return list;
	}

	
	// Run after an Item (a contact) clicked in List(datagrid)
	@SuppressWarnings("rawtypes")
	public void OnitemClickFunc(HashMap hashMapItem, int position, long id)
	{
		hashMapItem =  (HashMap) ((ListView) findViewById(R.id.listViewOperators)).getItemAtPosition(position);

		String ItemID = hashMapItem.get(FIRST_COLUMN).toString();
		String ItemName = hashMapItem.get(SECOND_COLUMN).toString();
		String ItemGuid = hashMapItem.get(THIRD_COLUMN).toString();
		
		if (ItemID!= "کد")
		{
			
			if (FlagForTaskOrInbox==true) // loaded from TaskFirstPage
			{			
				AddRecordToInboxTable(ItemGuid,  ItemName,  GuidTask, TitleTask);

				Intent intent = new Intent(getApplicationContext(), TaskFirstPage.class);
				startActivity(intent);		
				Toast.makeText(getApplicationContext(), "کار به " + ItemName.toString() + " ارجاع شد", Toast.LENGTH_SHORT).show();		    
				finish();
			}
			else if (FlagForTaskOrInbox==false) // loaded from Cartable
			{
				UpdateRecordInInboxTable(GuidInbox);
				AddRecordToInboxTableAfterRefered(ItemGuid,  ItemName, GuidInbox);

				Intent intent = new Intent(getApplicationContext(), CartableActivity.class);
				startActivity(intent);		
				Toast.makeText(getApplicationContext(), "کار به " + ItemName.toString() + " ارجاع شد", Toast.LENGTH_SHORT).show();		    
				finish();
			}
		}
	}
	

	// if this form opened by Task list this func will be runned!
	@SuppressLint({ "SimpleDateFormat" })
	public void AddRecordToInboxTable(String OperatorGuid, String OperatorName, String GuidTask, String TitleTask)
	{
	
		DatabaseHandler db = new DatabaseHandler(this);
		
		Inboxes inbox = new Inboxes();
		
		UUID guidInbox = UUID.randomUUID();
		inbox.setGuid(guidInbox.toString().toUpperCase());
		
		inbox.setTaskGuid(GuidTask);
		inbox.setTaskTitle(TitleTask);
		
		inbox.setOperatorGuid(OperatorGuid.toUpperCase());
		inbox.setOperatorName(OperatorName);
		
		/////   PersianCalendar.jar ///////			    		  
		  
		  //Date Farsi
		  com.ghasemkiani.util.SimplePersianCalendar c =
					new com.ghasemkiani.util.SimplePersianCalendar();   			
			com.ghasemkiani.util.DateFields t = c.getDateFields();				    			
				
			
			String DayCorr = CorrectDateOneDigit(String.valueOf(t.getDay()));
			String Month = String.valueOf(t.getMonth()+1);
			String MonthCorr = CorrectDateOneDigit(Month);
			
			
			String CurrentDate = String.valueOf(t.getYear()) +"/" 
			+  MonthCorr + "/"+ DayCorr;
			//////	 PersianCalendar.jar ///////
	
		
			/////  time /////
			Calendar cal = Calendar.getInstance();
			SimpleDateFormat sdf = new SimpleDateFormat("h:mm:ssa");
			String Time =  sdf.format(cal.getTime());
			String timestr = Time;
			/////  time /////
			
			
			inbox.setLocalDate(CurrentDate);
			inbox.setLocalTime(timestr);
			
			inbox.setVisited("0");
			inbox.setRefered("0");
			
			db.addInbox(inbox);
			
			Tasks task = db.getSpecificTasksByGuid(GuidTask.toUpperCase());
		
			task.setClosed("1");
			
			db.updateTasks(task);
	}
	

	// if this form opened by Inbox list this func will be runned! // this func update existed record to refered one
		private void UpdateRecordInInboxTable(String guidInbox) {
			
			DatabaseHandler db = new DatabaseHandler(this);
			
			Inboxes inbox = db.getSpecificInboxByGuid(guidInbox.toUpperCase());
			
			inbox.setRefered("1");
			
			db.updateInbox(inbox);
			
		}


		// if this form opened by Inbox list this func will be runned after "UpdateRecordInInboxTable" Fuction.
		// this func write new record in Inbox table after updating that record's crosspanding existed record
		@SuppressLint({ "SimpleDateFormat" })
		public void AddRecordToInboxTableAfterRefered(String OperatorGuid, String OperatorName, String GuidInbox)
		{
		
			DatabaseHandler db = new DatabaseHandler(this);
			
			Inboxes existedInbox = db.getSpecificInboxByGuid(GuidInbox);
			
			Inboxes inbox = new Inboxes();
			
			UUID guidInbox = UUID.randomUUID();
			inbox.setGuid(guidInbox.toString().toUpperCase());
			
			inbox.setTaskGuid(existedInbox.getTaskGuid().toUpperCase());
			inbox.setTaskTitle(existedInbox.getTaskTitle());
			
			inbox.setOperatorGuid(OperatorGuid.toUpperCase());
			inbox.setOperatorName(OperatorName);
			
			/////   PersianCalendar.jar ///////			    		  
			  
			  //Date Farsi
			  com.ghasemkiani.util.SimplePersianCalendar c =
						new com.ghasemkiani.util.SimplePersianCalendar();   			
				com.ghasemkiani.util.DateFields t = c.getDateFields();				    			
					
				
				String DayCorr = CorrectDateOneDigit(String.valueOf(t.getDay()));
				String Month = String.valueOf(t.getMonth()+1);
				String MonthCorr = CorrectDateOneDigit(Month);
				
				
				String CurrentDate = String.valueOf(t.getYear()) +"/" 
				+  MonthCorr + "/"+ DayCorr;
				//////	 PersianCalendar.jar ///////
		
			
				/////  time /////
				Calendar cal = Calendar.getInstance();
				SimpleDateFormat sdf = new SimpleDateFormat("h:mm:ssa");
				String Time =  sdf.format(cal.getTime());
				String timestr = Time;
				/////  time /////
				
				
				inbox.setLocalDate(CurrentDate);
				inbox.setLocalTime(timestr);
				
				inbox.setVisited("0");
				inbox.setRefered("0");
				
				db.addInbox(inbox);
				
				//Tasks task = db.getSpecificTasksByGuid(GuidTask.toUpperCase());
			
				//task.setClosed("1");
				
				//db.updateTasks(task);
		}
		
		
	
	// a function for Converting one digit Day/Month to 2 digits
	private String CorrectDateOneDigit(String Digit)
	{
		String CorrectedDigit;
		if (Digit.length() == 1)
		{
			CorrectedDigit = '0' + Digit;
		}else
		{
			CorrectedDigit = Digit;
		}

		return CorrectedDigit;
		
	}
	
}
