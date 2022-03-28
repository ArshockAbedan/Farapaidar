package com.farapaidar.firstmenu001;


import static com.farapaidar.firstmenu001.Constant.FIRST_COLUMN;
import static com.farapaidar.firstmenu001.Constant.SECOND_COLUMN;
import static com.farapaidar.firstmenu001.Constant.THIRD_COLUMN;
import static com.farapaidar.firstmenu001.Constant.FOURTH_COLUMN;
import static com.farapaidar.firstmenu001.Constant.FIFTH_COLUMN;
import static com.farapaidar.firstmenu001.Constant.SIXTH_COLUMN;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.AdapterView.OnItemSelectedListener;
import android.app.Activity;
import android.app.DialogFragment;

public class ReportReqstFirstPage extends Activity {
	
	
	@SuppressWarnings("rawtypes")
	private ArrayList<HashMap> list;
	
	public String spinnerFactorStringInReq;
	public String spinnerSendedStringInReq;
	
	Spinner spinnerFactorInReq;
	Spinner spinnerSendedInReq;
	
	String[] factorItemsInReq = { "›—Ê‘","»—ê‘  «“ ›—Ê‘","Â„Â"};
	String[] sendedItemsInReq = {"«—”«· ‰‘œÂ","«—”«· ‘œÂ","Â„Â"};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_report_reqst_first_page);
		
		DatabaseHandler db = new DatabaseHandler(this);
		
		
		 // Reading all Orders
		//List<Orders> orders = db.getAllOrders(); 
		
		
		String[] SelectCommandString = new String[5];
		SelectCommandString[0] = "";
		SelectCommandString[1] = "";
		SelectCommandString[2] = "";
		SelectCommandString[3] = "1";
		SelectCommandString[4] = "false";
		
		List<Factors> factors = db.getFactorsWithSearchCondition(SelectCommandString, MainMenuActivity.OperatorGuid);
		ListView lview = (ListView) findViewById(R.id.listViewReportRequest);

        list =  GridAdapterList(factors);
        ListviewAdapterReportRequest adapter = new ListviewAdapterReportRequest(this, list);
        lview.setAdapter(null);
        lview.setAdapter(adapter);
        
        // After Click on a item of list//////////////////////////////////////////////////
        lview.setOnItemLongClickListener(new OnItemLongClickListener() {

        	@SuppressWarnings("rawtypes")
			HashMap hashMapItem = new HashMap();
			@SuppressWarnings("rawtypes")
			@Override
			public boolean onItemLongClick(AdapterView<?> arg0, View arg1,
					int position, long id) {
				// TODO Auto-generated method stub
				 hashMapItem =  (HashMap) ((ListView) findViewById(R.id.listViewReportRequest)).getItemAtPosition(position);
				 String GuidOfItem =  hashMapItem.get(SIXTH_COLUMN).toString();
				 String SendedTypeOfItem =  hashMapItem.get(FOURTH_COLUMN).toString();
				
				MassageInReportReq SendFragment = MassageInReportReq.newInstance();
				SendFragment.guidIteminReportReq = GuidOfItem;
				SendFragment.FactorIsSended = SendedTypeOfItem;
				SendFragment.setStyle(DialogFragment.STYLE_NO_TITLE, 0);
				SendFragment.show(getFragmentManager(), "dialog");
				return true;
			}
		});
        
        
        // Set Factor ComboBox   /////////////////////////////////////////////////////////////////////////////
        spinnerFactorInReq  = (Spinner) findViewById(R.id.spinnerFactorInReq);
        

        ArrayAdapter<String> adapterFactorSpinner = new ArrayAdapter<String>(this,
        		android.R.layout.simple_spinner_dropdown_item, factorItemsInReq);
		
        spinnerFactorInReq.setAdapter(adapterFactorSpinner);
        
        
        spinnerFactorInReq.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parent, View v,
					int position, long id) {
				// TODO Auto-generated method stub
				
				spinnerFactorStringInReq = factorItemsInReq[position];
				
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
				// TODO Auto-generated method stub
				
			}
		});
		
        
        
        // Set Sended ComboBox  /////////////////////////////////////////////////////////////////////////////
        spinnerSendedInReq  = (Spinner) findViewById(R.id.spinnerSendedInReq);
        

        ArrayAdapter<String> adapterSendedSpinnerInReq = new ArrayAdapter<String>(this,
        		android.R.layout.simple_spinner_dropdown_item, sendedItemsInReq);
		
        spinnerSendedInReq.setAdapter(adapterSendedSpinnerInReq);
        
        
        spinnerSendedInReq.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int position, long id) {
				// TODO Auto-generated method stub
				spinnerSendedStringInReq = sendedItemsInReq[position];
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub
				
			}
		});

	}

@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		DatabaseHandler db = new DatabaseHandler(this);
		
		
		EditText editTxtStartTarikh = (EditText) findViewById(R.id.editTxtStartTarikh1InReq);
		EditText editTxtEndTarikh = (EditText) findViewById(R.id.editTxtEndTarikhInReq);

		EditText editTxtCustomerName = (EditText) findViewById(R.id.editTxtCustomerNameInReq);
		
		
		spinnerFactorInReq  = (Spinner) findViewById(R.id.spinnerFactorInReq);
		spinnerFactorStringInReq = spinnerFactorInReq.getSelectedItem().toString();
		spinnerSendedInReq  = (Spinner) findViewById(R.id.spinnerSendedInReq);
		spinnerSendedStringInReq = spinnerSendedInReq.getSelectedItem().toString();
		
		
		Factors StartDateFacts = new Factors();
		Factors EndDateFacts = new Factors();
		if (editTxtStartTarikh.getText().toString().length()==10 && editTxtEndTarikh.getText().toString().length()==10 )
		{
			StartDateFacts = db.getSpecificFactorsByDateStr(editTxtStartTarikh.getText().toString());
			EndDateFacts = db.getSpecificFactorsByDateStr(editTxtEndTarikh.getText().toString());
		}else{
			StartDateFacts.setLocalDateNumber("");
			EndDateFacts.setLocalDateNumber("");
		}


		
		String[] SelectCommandString = new String[5];
	    SelectCommandString[0] = StartDateFacts.getLocalDateNumber();
		SelectCommandString[1] = EndDateFacts.getLocalDateNumber();
		SelectCommandString[2] = editTxtCustomerName.getText().toString();
		SelectCommandString[3] = spinnerFactorStringInReq;
		SelectCommandString[4] = spinnerSendedStringInReq;
		
		
		 String[] WhereCondition  = MakeSelectCommandWhereString(SelectCommandString);
		 
		 List<Factors> factors = db.getFactorsWithSearchCondition(WhereCondition, MainMenuActivity.OperatorGuid);
		 
         ListView lview = (ListView) findViewById(R.id.listViewReportRequest);	        
         list =  GridAdapterList(factors);	        
         ListviewAdapterReportRequest adapter = new ListviewAdapterReportRequest(this, list);	
         lview.setAdapter(adapter);
	}

			///////////////////////////////////////////////////////////////
	// making header & Contents of grid's Adapter  
			@SuppressWarnings({ "rawtypes", "unchecked" })
			private ArrayList<HashMap> GridAdapterList(List<Factors> factors)
			{
				   list = new ArrayList<HashMap>();
				    
			    
				    // Adding Items
				        for (Factors facts : factors) {        		        	       	 
				        	 
				             HashMap temp = new HashMap();
				                 temp.put(FIRST_COLUMN,facts.getLocalDate());
				                 temp.put(SECOND_COLUMN,String.valueOf(facts.getID()));
				                 temp.put(THIRD_COLUMN, facts.getNameCustomer());
				                 temp.put(FOURTH_COLUMN, facts.getSended());
				                 temp.put(FIFTH_COLUMN, facts.getTypeInvcId());
				                 temp.put(SIXTH_COLUMN, facts.getGuidInvcRequest());
				             list.add(temp);
				        }


				        return list;
				
			}
			

			// after press Ã” ÃÊ button
			public void Search(View view)
			{
				
				DatabaseHandler db = new DatabaseHandler(this);
				
				
				EditText editTxtStartTarikh = (EditText) findViewById(R.id.editTxtStartTarikh1InReq);
				EditText editTxtEndTarikh = (EditText) findViewById(R.id.editTxtEndTarikhInReq);

				EditText editTxtCustomerName = (EditText) findViewById(R.id.editTxtCustomerNameInReq);
				
				
				Factors StartDateFacts = new Factors();
				Factors EndDateFacts = new Factors();
				if (editTxtStartTarikh.getText().toString().length()==10 && editTxtEndTarikh.getText().toString().length()==10 )
				{
					StartDateFacts = db.getSpecificFactorsByDateStr(editTxtStartTarikh.getText().toString());
					EndDateFacts = db.getSpecificFactorsByDateStr(editTxtEndTarikh.getText().toString());
				}else{
					StartDateFacts.setLocalDateNumber("");
					EndDateFacts.setLocalDateNumber("");
				}


				
				String[] SelectCommandString = new String[5];
			    SelectCommandString[0] = StartDateFacts.getLocalDateNumber();
				SelectCommandString[1] = EndDateFacts.getLocalDateNumber();
				SelectCommandString[2] = editTxtCustomerName.getText().toString();
				SelectCommandString[3] = spinnerFactorStringInReq;
				SelectCommandString[4] = spinnerSendedStringInReq;
				
				
				 String[] WhereCondition  = MakeSelectCommandWhereString(SelectCommandString);
				 
				 List<Factors> factors = db.getFactorsWithSearchCondition(WhereCondition, MainMenuActivity.OperatorGuid);
				 
		         ListView lview = (ListView) findViewById(R.id.listViewReportRequest);	        
		         list =  GridAdapterList(factors);	        
		         ListviewAdapterReportRequest adapter = new ListviewAdapterReportRequest(this, list);	
		         lview.setAdapter(adapter);
				
			}
			
			
			// a function for making SelectCommandString according to two editbox& two Spinner
			private String[] MakeSelectCommandWhereString(String[] SelectCommandString)
			{
				String[] WhereCondition = new String[5];
				
				//WhereCondition[0] = SelectCommandString[0];
				
				if (SelectCommandString[0].toString().length()==8)
				{
					WhereCondition[0] = SelectCommandString[0];
				}
				else
				{
					WhereCondition[0] = ConvDateToDateNum(SelectCommandString[0]);
				}
				
				//WhereCondition[1] = SelectCommandString[1];
				if (SelectCommandString[1].toString().length()==8)
				{
					WhereCondition[1] = SelectCommandString[1];
				}
				else
				{
					WhereCondition[1] = ConvDateToDateNum(SelectCommandString[1]);
				}
				
				if (SelectCommandString[2].toString().length()>0)
				{
					WhereCondition[2] = SelectCommandString[2].replace("+", "%");
				}
				else
				{
					WhereCondition[2] = "";
				}
				if (SelectCommandString[3] == "›—Ê‘" )
				{
					WhereCondition[3] = "1";
				}
				else if (SelectCommandString[3] == "»—ê‘  «“ ›—Ê‘" )
				{
					WhereCondition[3] = "2";
				}
				else{
					WhereCondition[3] = "";
				}
				
				
				if (SelectCommandString[4] == "«—”«· ‰‘œÂ" )
				{
					WhereCondition[4] = "false";
				}
				else if (SelectCommandString[4] == "«—”«· ‘œÂ" )
				{
					WhereCondition[4] = "true";
				}else{
					WhereCondition[4] = "";
				}
				
				return WhereCondition;
			}
			
			//Convert Date to DateNumber
			private String ConvDateToDateNum(String DateStr)
			{
				if (DateStr.equalsIgnoreCase(""))
				{
					return DateStr;
				}

				String s = DateStr;
				char arr[]=s.toCharArray();
				
				String strArray = Character.toString(arr[0])+ Character.toString(arr[1])+ Character.toString(arr[2])+
						Character.toString(arr[3])+ Character.toString(arr[5])+ Character.toString(arr[6])+
						Character.toString(arr[8])+ Character.toString(arr[9]);
				return strArray;
			
			}
	}
