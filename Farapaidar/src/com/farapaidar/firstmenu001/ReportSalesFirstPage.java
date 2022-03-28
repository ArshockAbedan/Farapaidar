package com.farapaidar.firstmenu001;

import static com.farapaidar.firstmenu001.Constant.FIFTH_COLUMN;
import static com.farapaidar.firstmenu001.Constant.FIRST_COLUMN;
import static com.farapaidar.firstmenu001.Constant.FOURTH_COLUMN;
import static com.farapaidar.firstmenu001.Constant.SECOND_COLUMN;
import static com.farapaidar.firstmenu001.Constant.SIXTH_COLUMN;
import static com.farapaidar.firstmenu001.Constant.THIRD_COLUMN;
import static com.farapaidar.firstmenu001.Constant.SEVENTH_COLUMN;
import static com.farapaidar.firstmenu001.Constant.EIGHTH_COLUMN;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;

public class ReportSalesFirstPage extends Activity {
	
	@SuppressWarnings("rawtypes")
	private ArrayList<HashMap> list;
	
	public String spinnerFactorString;
	public String spinnerSendedString;
	
	Spinner spinnerFactor;
	Spinner spinnerSended;
	
	String[] factorItems = { "›—Ê‘","»—ê‘  «“ ›—Ê‘","Â„Â"};
	String[] sendedItems = {"«—”«· ‰‘œÂ","«—”«· ‘œÂ","Â„Â"};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_report_sales_first_page);
		
		DatabaseHandler db = new DatabaseHandler(this);
		
		
		 // Reading all Orders
		//List<Orders> orders = db.getAllOrders(); 
		
		
		String[] SelectCommandString = new String[6];
		SelectCommandString[0] = "";
		SelectCommandString[1] = "";
		SelectCommandString[2] = "";
		SelectCommandString[3] = "";
		SelectCommandString[4] = "1";
		SelectCommandString[5] = "false";
		
		List<Orders> orders = db.getOrdersWithSearchCondition(SelectCommandString,MainMenuActivity.OperatorGuid);
          
        
        ListView lview = (ListView) findViewById(R.id.listViewReportSales);

        list =  GridAdapterList(orders);
        ListviewAdapterReportSales adapter = new ListviewAdapterReportSales(this, list);
        lview.setAdapter(null);
        lview.setAdapter(adapter);
        
        
        // Set Factor ComboBox   /////////////////////////////////////////////////////////////////////////////
        spinnerFactor  = (Spinner) findViewById(R.id.spinnerFactor);
        

        ArrayAdapter<String> adapterFactorSpinner = new ArrayAdapter<String>(this,
        		android.R.layout.simple_spinner_dropdown_item, factorItems);
		
        spinnerFactor.setAdapter(adapterFactorSpinner);
        
        
        spinnerFactor.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parent, View v,
					int position, long id) {
				// TODO Auto-generated method stub
				
				spinnerFactorString = factorItems[position];
				
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
				// TODO Auto-generated method stub
				
			}
		});
		
        
        
        // Set Sended ComboBox  /////////////////////////////////////////////////////////////////////////////
        spinnerSended  = (Spinner) findViewById(R.id.spinnerSended);
        

        ArrayAdapter<String> adapterSendedSpinner = new ArrayAdapter<String>(this,
        		android.R.layout.simple_spinner_dropdown_item, sendedItems);
		
        spinnerSended.setAdapter(adapterSendedSpinner);
        
        
        spinnerSended.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parent, View v,
					int position, long id) {
				// TODO Auto-generated method stub
				spinnerSendedString = sendedItems[position];
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
				// TODO Auto-generated method stub
				
			}
		});

        /////////////////////////////////////////////////////////////////////////////
	}

	
	
	// making header & Contents of grid's Adapter  
		@SuppressWarnings({ "rawtypes", "unchecked" })
		private ArrayList<HashMap> GridAdapterList(List<Orders> orders)
		{
			   list = new ArrayList<HashMap>();
			    
		    
			    // Adding Items
			        for (Orders ords : orders) {        		        	       	 
			        	 
			             HashMap temp = new HashMap();
			                 temp.put(FIRST_COLUMN,ords.getLocalDate());
			                 temp.put(SECOND_COLUMN,String.valueOf(ords.getID()));
			                 temp.put(THIRD_COLUMN, ords.getNameCustomer());
			                 temp.put(FOURTH_COLUMN,ords.getNameProdScale());
			                 temp.put(FIFTH_COLUMN, ords.getQuantity());
			                 temp.put(SIXTH_COLUMN, ords.getFee());
			                 temp.put(SEVENTH_COLUMN, String.valueOf((Integer.parseInt(ords.getQuantity()))*(Integer.parseInt(ords.getFee()))));
			                 temp.put(EIGHTH_COLUMN, ords.getTypeInvcId());
			             list.add(temp);
			        }


			        return list;
			
		}
		
		
		// after press Ã” ÃÊ button
		public void Search(View view)
		{
			
			DatabaseHandler db = new DatabaseHandler(this);
			
			
			EditText editTxtStartTarikh = (EditText) findViewById(R.id.editTxtStartTarikh1);
			EditText editTxtEndTarikh = (EditText) findViewById(R.id.editTxtEndTarikh);

			EditText editTxtCustomerName = (EditText) findViewById(R.id.editTxtCustomerName);
			EditText editTxtGoodName = (EditText) findViewById(R.id.editTxtGoodName);
			
			
			Orders StartDateOrds = new Orders();
			Orders EndDateOrds = new Orders();
			if (editTxtStartTarikh.getText().toString().length()==10 && editTxtEndTarikh.getText().toString().length()==10 )
			{
				StartDateOrds = db.getSpecificOrdersByDateStr(editTxtStartTarikh.getText().toString(),MainMenuActivity.OperatorGuid);
				EndDateOrds = db.getSpecificOrdersByDateStr(editTxtEndTarikh.getText().toString(),MainMenuActivity.OperatorGuid);
			}else{
				StartDateOrds.setLocalDateNumber("");
				EndDateOrds.setLocalDateNumber("");
			}

			
			String[] SelectCommandString = new String[6];
		    SelectCommandString[0] = StartDateOrds.getLocalDateNumber();
			SelectCommandString[1] = EndDateOrds.getLocalDateNumber();
			SelectCommandString[2] = editTxtCustomerName.getText().toString();
			SelectCommandString[3] = editTxtGoodName.getText().toString();
			SelectCommandString[4] = spinnerFactorString;
			SelectCommandString[5] = spinnerSendedString;
			
			
			 String[] WhereCondition  = MakeSelectCommandWhereString(SelectCommandString);
			 
			 List<Orders> orders = db.getOrdersWithSearchCondition(WhereCondition,MainMenuActivity.OperatorGuid);
			 
	         ListView lview = (ListView) findViewById(R.id.listViewReportSales);	        
	         list =  GridAdapterList(orders);	        
	         ListviewAdapterReportSales adapter = new ListviewAdapterReportSales(this, list);	
	         //lview.setCacheColorHint(Color.TRANSPARENT);
	         lview.setAdapter(adapter);
			
		}
		
		
		// a function for making SelectCommandString according to two editbox& two Spinner
		private String[] MakeSelectCommandWhereString(String[] SelectCommandString)
		{
			String[] WhereCondition = new String[6];
			
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
			
			if (SelectCommandString[3].toString().length()>0)
			{
				WhereCondition[3] = SelectCommandString[3].replace("+", "%");
			}
			else
			{
				WhereCondition[3] = "";
			}
			
			
			if (SelectCommandString[4] == "›—Ê‘" )
			{
				WhereCondition[4] = "1";
			}
			else if (SelectCommandString[4] == "»—ê‘  «“ ›—Ê‘" )
			{
				WhereCondition[4] = "2";
			}
			else{
				WhereCondition[4] = "";
			}
			
			
			if (SelectCommandString[5] == "«—”«· ‰‘œÂ" )
			{
				WhereCondition[5] = "false";
			}
			else if (SelectCommandString[5] == "«—”«· ‘œÂ" )
			{
				WhereCondition[5] = "true";
			}else{
				WhereCondition[5] = "";
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
