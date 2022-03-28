package com.farapaidar.firstmenu001;


import static com.farapaidar.firstmenu001.Constant.FIFTH_COLUMN;
import static com.farapaidar.firstmenu001.Constant.FIRST_COLUMN;
import static com.farapaidar.firstmenu001.Constant.FOURTH_COLUMN;
import static com.farapaidar.firstmenu001.Constant.SECOND_COLUMN;
import static com.farapaidar.firstmenu001.Constant.SIXTH_COLUMN;
import static com.farapaidar.firstmenu001.Constant.THIRD_COLUMN;
import static com.farapaidar.firstmenu001.Constant.SEVENTH_COLUMN;
import static com.farapaidar.firstmenu001.Constant.EIGHTH_COLUMN;
import static com.farapaidar.firstmenu001.Constant.NINTH_COLUMN;
import static com.farapaidar.firstmenu001.Constant.TENTH_COLUMN;



import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.DialogFragment;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;

import android.view.Menu;
import android.view.View;

import android.widget.AdapterView;
import android.widget.AdapterView.OnItemLongClickListener;

import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;


public class OrderFirstPage extends Activity {
	
	public static String OperatorGuid;
	public static String ReselerGuid;
	public static String BrancheGuid;
	public static String formType; // sabtSefaresh = 1 && bargashtAzFrosh = 2
	
	
	public static Boolean EditMode = false;
	public static String EditModeguidIteminReportReq;
	
	public static String Note;
	
		
	 @SuppressWarnings("rawtypes")
		private ArrayList<HashMap> list;
	private static HashMap hashMapItemS;
	 private static View viewS;
	 private static int positionS;
	 private static long idS;
	 private static Activity activityS;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_order_first_page);
		
		
		
		/// delete content of NoteFragment
				Note = ""; 
				
				DatabaseHandler db = new DatabaseHandler(this);
				db.deleteAllContactsTemp();
				db.deleteAllGoodsTemp();
		
		
		////// edit Mode = Means start this form from ReportReq //////////////
		if (EditMode == true)
		{			
			
			List<Orders> orders = db.getOrdersWithGuidReq(EditModeguidIteminReportReq);
		
			Factors fact = db.getFactorByGuid(EditModeguidIteminReportReq);
			Note =  fact.getNote();

			 formType = fact.getTypeInvcId();
			
			 OperatorGuid = fact.getGuidOperator();
			 ReselerGuid = fact.getGuidReseler();
			 BrancheGuid = fact.getGuidBranche();
			
			
			Orders orderscontainCustomerGuid = orders.get(0);
			Contact customer = db.getContactByGuid(orderscontainCustomerGuid.getGuidCustomer());
			
			db.addContactTemp(customer);
			
			for (Orders ords : orders) {
				GoodsTemp gdtmp =  db.getSpecificGoodByGuid(ords.getGuidProdScale(), ords.getQuantity(),
						ords.getFee());
				db.addGoodsTemp(gdtmp);
			}
			
		}
		
		
		/////////////////////////////
		if (formType.equalsIgnoreCase("1"))
		{

		    this.setTitle("ثبت سفارش");	
		}
		else if (formType.equalsIgnoreCase("2"))
		{
			this.setTitle("برگشت از فروش");
		}
		
		
		  List<GoodsTemp> goods = db.getAllGoodsTemp();   

	        ListView lview = (ListView) findViewById(R.id.listViewOrder);
	        
	        

	        list =  GridAdapterList(goods);


				 ListviewAdapterOrders adapter = new ListviewAdapterOrders(this, list); 
				 lview.setAdapter(adapter);
		
		
		 // listening to single list item on click
        lview.setOnItemClickListener(new OnItemClickListener() {

        	HashMap hashMapItem = new HashMap();
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
		              int position, long id) {
				// TODO Auto-generated method stub
				
				
				
				 hashMapItem =  (HashMap) ((ListView) findViewById(R.id.listViewOrder)).getItemAtPosition(position);

					String GuidItem =  hashMapItem.get(EIGHTH_COLUMN).toString();
					String Count =  hashMapItem.get(SIXTH_COLUMN).toString();
					String ItemTotalValue =  hashMapItem.get(SEVENTH_COLUMN).toString();
					
					DisplayOrdersItemDetails.GuidItem = GuidItem;
					DisplayOrdersItemDetails.Count = Count;
					DisplayOrdersItemDetails.ItemTotalValue = ItemTotalValue;

	        	   Intent intent = new Intent(getApplicationContext(), DisplayOrdersItemDetails.class); 	
	        	   startActivity(intent);	
	        	   
				
				
			}
		});
        
        
        lview.setOnItemLongClickListener(new OnItemLongClickListener() {

        	@SuppressWarnings("rawtypes")
			HashMap hashMapItem = new HashMap();
        	
			public boolean onItemLongClick(AdapterView<?> parent, View view,
					int position, long id) {
				OnitemLongClickFunc(hashMapItem, view,  position,  id);

				return true;	
			}
        	
		});
	}

	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		super.onBackPressed();
		if (EditMode == true)
		{
			EditMode = false;
			Intent intent = new Intent(getApplicationContext(), MainMenuActivity.class); 
			startActivity(intent);
		}
				
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.order_first_page, menu);
		return true;
	}
	
	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
	}

	@Override
	protected void onRestart() {
		// TODO Auto-generated method stub
		super.onRestart();
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		
		DatabaseHandler db = new DatabaseHandler(this);
		
		
		 if (formType.equalsIgnoreCase("1") && db.getGoodsTempCount() > 0)
		 {
			 Button btnAddContact = (Button) findViewById(R.id.btnaddcontact);
			 btnAddContact.setEnabled(false);
		 }
		
		// for Contacts
		List<Contact> contacts =  db.getAllContactsTemp();
		
		TextView txtcontactName = (TextView) findViewById(R.id.txtcontactName);
		TextView txtcontactCode = (TextView) findViewById(R.id.txtcontactCode);
		
		  for (Contact cn : contacts) {    			  
			  txtcontactName.setText(cn.getName().toString());
			  txtcontactCode.setText(cn.getID().toString());			  
		  }
		  
		  
		// for Goods		 
	        List<GoodsTemp> goods = db.getAllGoodsTemp();   

	        ListView lview = (ListView) findViewById(R.id.listViewOrder);
	        
	        

	        list =  GridAdapterList(goods);


				 ListviewAdapterOrders adapter = new ListviewAdapterOrders(this, list); 
				 lview.setAdapter(adapter);

	        
	     // listening to single list item on click
	        lview.setOnItemClickListener(new OnItemClickListener() {

	        	HashMap hashMapItem = new HashMap();
				@Override
				public void onItemClick(AdapterView<?> parent, View view,
			              int position, long id) {
					// TODO Auto-generated method stub
					 hashMapItem =  (HashMap) ((ListView) findViewById(R.id.listViewOrder)).getItemAtPosition(position);

						String GuidItem =  hashMapItem.get(EIGHTH_COLUMN).toString();
						String Count =  hashMapItem.get(SIXTH_COLUMN).toString();
						String ItemTotalValue =  hashMapItem.get(SEVENTH_COLUMN).toString();
						
						DisplayOrdersItemDetails.GuidItem = GuidItem;
						DisplayOrdersItemDetails.Count = Count;
						DisplayOrdersItemDetails.ItemTotalValue = ItemTotalValue;

		        	   Intent intent = new Intent(getApplicationContext(), DisplayOrdersItemDetails.class); 	
		        	   startActivity(intent);	
					
				}
			});
	        
	        
	        lview.setOnItemLongClickListener(new OnItemLongClickListener() {

	        	@SuppressWarnings("rawtypes")
				HashMap hashMapItem = new HashMap();
	        	
				public boolean onItemLongClick(AdapterView<?> parent, View view,
						int position, long id) {
					OnitemLongClickFunc(hashMapItem, view,  position,  id);

					return true;	
				}
	        	
			});
	        
	        
	        // For TotalRiali TextView
	        Typeface tf = Typeface.createFromAsset(getAssets(),
            		"fonts/BYekan.ttf");
			String SumRialiCountOfGoods = db.getGoodsTempSumRiali();
			TextView txtTotalValue = (TextView) findViewById(R.id.txtTotalValue);

			int SeparatorLocation = 0;
			SeparatorLocation =  SumRialiCountOfGoods.length() % 3;
			
			if (SeparatorLocation == 0)
			{
				SeparatorLocation =3;
			}

			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < SumRialiCountOfGoods.length(); i++) 
			{
				sb.append(SumRialiCountOfGoods.charAt(i));
				if (i==SeparatorLocation-1)
				{
					if (i!=SumRialiCountOfGoods.length()-1)
					{
					sb.append(',');
					SeparatorLocation+=3;
					}
				}
			}
			
			String txtTotalValueString = sb.toString();
			
			if (txtTotalValueString=="0")
			{
				txtTotalValueString="";
			}
			
			txtTotalValue.setText(txtTotalValueString.toString());
			txtTotalValue.setTypeface(tf);
			
			
			// For TotalCount TextView
			String SumCountOfGoods = db.getGoodsTempSumCount();
			TextView txtContTotalValue = (TextView) findViewById(R.id.txtContTotalValue);
			
			txtContTotalValue.setText(SumCountOfGoods);
			txtContTotalValue.setTypeface(tf);
						
						 
						
			
			
	       

	}

	@Override
	protected void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
	}

	@Override
	protected void onStop() {
		// TODO Auto-generated method stub
		super.onStop();
	}

	
	
	public void OpenContact(View view)
	{
		ContactFirstPage.FlagForOrderInContacts = true;
		Intent intentContact = new Intent(this, ContactFirstPage.class); 
		startActivity(intentContact);
		
		/*FragmentContact newFragment = FragmentContact.newInstance();
		newFragment.setStyle(DialogFragment.STYLE_NO_TITLE, 0);
	    newFragment.show(getFragmentManager(), "dialog");
	    */
		
	}
	
	
	public void OpenGoods(View view)
	{
		DatabaseHandler db = new DatabaseHandler(getApplicationContext());
		int ContactCount = db.getContactsTempCount();
		
		if (ContactCount==0)
		{
			 Toast.makeText(getApplicationContext(), "نام مشتری وارد نشده است", Toast.LENGTH_SHORT).show();
		}
		else
		{
		GoodFirstPage.FlagForOrderInGoods = true;
		Intent intentGoods = new Intent(this, GoodFirstPage.class); 
		startActivity(intentGoods);
		}
	
	}
	
	
	
	
	// making header & Contents of grid's Adapter  
		@SuppressWarnings({ "rawtypes", "unchecked" })
		private ArrayList<HashMap> GridAdapterList(List<GoodsTemp> goods)
		{
			   list = new ArrayList<HashMap>();		    
			    // Adding Items
			        for (GoodsTemp gd : goods) {        		        	       	 
			        	 
			             HashMap temp = new HashMap();
		                 
			                 temp.put(FIRST_COLUMN,gd.getID());
			                 temp.put(SECOND_COLUMN,gd.getGoodName());
			                 temp.put(THIRD_COLUMN, gd.getPrice());
			                 temp.put(FOURTH_COLUMN, gd.getPriceType());
			                 temp.put(FIFTH_COLUMN, gd.getCountUnit());
			                 temp.put(SIXTH_COLUMN,gd.getCount());
			                 temp.put(SEVENTH_COLUMN, gd.getItemTotal());
			                 temp.put(EIGHTH_COLUMN, gd.getGuid());
			                 
			                 int numb = Integer.parseInt(gd.getCount());
							 long qLong = numb;
							 long box = 0 ;
			                 
			                 long QBOX = Integer.parseInt(gd.getQBox());
							 
							  if (QBOX == 0)
							  {
								  qLong = numb;
								  box = 0;
								  
							  }else
							  {
								  while(qLong >= QBOX )
									 {
										 qLong = qLong - QBOX;
										 box ++ ;
									 }
							  }
							
							  temp.put(NINTH_COLUMN, String.valueOf(qLong));
				              temp.put(TENTH_COLUMN, String.valueOf(box));
			                 
			             list.add(temp);
			        }
			        return list;			
		}
		
		
		
		// Run after an Item (a good) clicked in List(DataGrid)
					@SuppressWarnings("rawtypes")
					public void OnitemClickFunc(HashMap hashMapItem, int position, long id)
					{
						  hashMapItem =  (HashMap) ((ListView) findViewById(R.id.listViewOrder)).getItemAtPosition(position);

							
							String ItemID =  hashMapItem.get(FIRST_COLUMN).toString();
							String ItemName =  hashMapItem.get(SECOND_COLUMN).toString();
	
							DatabaseHandler db = new DatabaseHandler(this);
							
							db.deleteGoodsTempById(ItemID);
							
							 List<GoodsTemp> goods = db.getAllGoodsTemp();   						        
							 ListView lview = (ListView) findViewById(R.id.listViewOrder);						        
							 list =  GridAdapterList(goods);						        
							 //ListviewAdapterOrders adapter = new ListviewAdapterOrders(this, list);    

						
							 ListviewAdapterOrders adapter = new ListviewAdapterOrders(this, list); 
							 lview.setAdapter(adapter);
								    
							
		  				    
							 String toastmessage = "کالای " +  ItemName + " " + " از لیست حذف شد"; 
							 Toast.makeText(getApplicationContext(), toastmessage, Toast.LENGTH_SHORT).show();
			  				  
			  				
					}
					
					
					
					// Run after an Item (a good) clicked&pressed in List(DataGrid)
					@SuppressWarnings("rawtypes")
					public void OnitemLongClickFunc(HashMap hashMapItem, View view, int position, long id)
					{
						
						hashMapItem =  (HashMap) ((ListView) findViewById(R.id.listViewOrder)).getItemAtPosition(position);
						String ItemName =  hashMapItem.get(SECOND_COLUMN).toString();
						
						Builder builder = new AlertDialog.Builder(view.getContext());
						String msg = "آیا میخواهید کالای " + ItemName + " حذف شود؟";
					    builder.setMessage(msg);
					    builder.setCancelable(true);
					    builder.setPositiveButton("نه مخالفم", new CancelOnClickListener());
					    builder.setNegativeButton("بله موافقم", new OkOnClickListener());
					    AlertDialog dialog = builder.create();
					    dialog.show(); 
						
					     hashMapItemS = hashMapItem;
						 viewS = view;
						 positionS = position;
						 idS = id;
						 activityS = this;

					}
					
					private final class CancelOnClickListener implements
					  DialogInterface.OnClickListener {
					  public void onClick(DialogInterface dialog, int which) {
					   // Toast.makeText(getApplicationContext(), "Activity will continue", Toast.LENGTH_LONG).show();
					  }
					}

					private final class OkOnClickListener implements
					  DialogInterface.OnClickListener {
					  public void onClick(DialogInterface dialog, int which) {

							String ItemID =  hashMapItemS.get(FIRST_COLUMN).toString();
							String ItemName =  hashMapItemS.get(SECOND_COLUMN).toString();
	
							DatabaseHandler db = new DatabaseHandler(getApplicationContext());
							
							db.deleteGoodsTempById(ItemID);
							
							 List<GoodsTemp> goods = db.getAllGoodsTemp();   						        
							 ListView lview = (ListView) findViewById(R.id.listViewOrder);						        
							 list =  GridAdapterList(goods);
							 
							 //ListviewAdapterOrders adapter = new ListviewAdapterOrders(activityS, list);    
							
								 ListviewAdapterOrders adapter = new ListviewAdapterOrders(activityS, list); 
								 lview.setAdapter(adapter);
		  				    
							 String toastmessage = "کالای " +  ItemName + " " + " از لیست حذف شد"; 
							 Toast.makeText(getApplicationContext(), toastmessage, Toast.LENGTH_SHORT).show();
						  
						  
						  dialog.dismiss();
					  }
					} 
					
					
					
					public void RegisterOrder(View View)
					{
						DatabaseHandler db = new DatabaseHandler(this);
						
						// when We are in Edit mode, befor saving, First existed record in ordersTable
						// & FactorsTable should be deleted.
						if (EditMode == true)
						{
							List<Orders> orders = db.getOrdersWithGuidReq(EditModeguidIteminReportReq);
							
							Orders orderscontainFactorGuid = orders.get(0);	
							db.deleteFactorByGuid(orderscontainFactorGuid.getGuidInvcRequest());
							
							for (Orders ords : orders) {
								db.deleteOrder(ords);
							}
							
							
							
						}
						
						
						
						int ContactCount = db.getContactsTempCount();
						String SumCountOfGoods = db.getGoodsTempSumCount();
						
						if (ContactCount==0)
						{
							 Toast.makeText(getApplicationContext(), "نام مشتری وارد نشده است", Toast.LENGTH_SHORT).show();
						}
						else if(Integer.parseInt(SumCountOfGoods) == 0)
						{
							Toast.makeText(getApplicationContext(), "کالا وارد نشده است", Toast.LENGTH_SHORT).show();
						}
						else
						{
							
							UUID GuidInvcRequestuuid = UUID.randomUUID();
					        String GuidInvcRequest = GuidInvcRequestuuid.toString();
					        
					        //Add a Factor
					        Factors factors = new Factors();
					        factors.setGuidInvcRequest(GuidInvcRequest);
					        String TypeInvcNameStr = formType.toString();
					        factors.setTypeInvcId(TypeInvcNameStr);

					        if (Integer.parseInt(TypeInvcNameStr) == 1)
					        { 
					        	 factors.setTypeInvcName("فروش");
					        	
					        }else  if (Integer.parseInt(TypeInvcNameStr) == 2)
					        {
					        	factors.setTypeInvcName("برگشت از فروش");
					        }
					        
					        factors.setGuidOperator(OperatorGuid);
					        
					        List<Contact> contacts =  db.getAllContactsTemp();
				    		  for (Contact cn : contacts) 
				    		  {    			  
				    			  factors.setGuidCustomer(cn.getGuid());	
				    			  factors.setNameCustomer(cn.getName());
				    		  }
					        
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

				    			String CurrentDateNumber = String.valueOf(t.getYear())  
				    					+  MonthCorr + DayCorr;

				    			//////	 PersianCalendar.jar ///////	
				    			
				    			/////  time /////
				    			Calendar cal = Calendar.getInstance();
				    			SimpleDateFormat sdf = new SimpleDateFormat("h:mm:ssa");
				    			String Time =  sdf.format(cal.getTime());
				    			String timestr = Time;
				    		
				    			/////  time /////
		
				    			factors.setLocalDate(CurrentDate);
								  
				    			factors.setLocalTime(timestr);
				    			
				    			factors.setSystemID("9");
				    						    		 				    						    		 
				    			factors.setGuidReseler(ReselerGuid);
				    			
				    			factors.setGuidBranche(BrancheGuid);	
				    			
				    			factors.setNote(Note);
				    						    		
				    			factors.setSended("false");  
				    			
				    			factors.setLocalDateNumber(CurrentDateNumber);
				    						    		
				    			db.addFactors(factors);
					        
					        // Adding Items of a registerd factor
							List<GoodsTemp> goods = db.getAllGoodsTemp();							
					        for (GoodsTemp gd : goods) {        		        	       	 
					        	 
					        	Orders orders = new Orders();

					    		  UUID GuidRequestuuid = UUID.randomUUID();
							      String GuidRequest = GuidRequestuuid.toString();
					    		  orders.setGuidRequest(GuidRequest);
					    		  
					    		  orders.setGuidInvcRequest(GuidInvcRequest);
					    		  
					    		  String TypeInvcId = formType.toString();
					    		  orders.setTypeInvcId(TypeInvcId);
					    		  
					    		  orders.setGuidOperator(OperatorGuid);
					    		  
					    		  //List<Contact> contacts =  db.getAllContactsTemp();
					    		  for (Contact cn : contacts) 
					    		  {    			  
					    			  orders.setGuidCustomer(cn.getGuid());	
					    			  orders.setNameCustomer(cn.getName());
					    		  }
					    		  
					    		  orders.setGuidProdScale(gd.getGuid());
					    		  orders.setNameProdScale(gd.getGoodName());
					    		  
					    		  orders.setQuantity(gd.getCount());
					    		  
					    		  orders.setFee(gd.getPrice());
	
			    		  
								  orders.setLocalDate(CurrentDate);
	
								  orders.setLocalTime(timestr);
					    		 
					    		  orders.setGuidReseler(ReselerGuid);
					    		  
					    		  orders.setSended("false");  
					    		  
					    		  orders.setLocalDateNumber(CurrentDateNumber);
					    		  
					    		  orders.setQuantityInvc("0");
					    		  
					    		  db.addOrders(orders);
					        }
					        
							Toast.makeText(getApplicationContext(), "سفارش ثبت شد", Toast.LENGTH_SHORT).show();
							if (EditMode == true)
							{
								EditMode = false;
								Intent intent = new Intent(getApplicationContext(), MainMenuActivity.class); 
								startActivity(intent);
							}
							else{
								finish();
							}
							
						}
						
						
					
					
					}
					
					/*
					public void New(View view)
					{
						DatabaseHandler db = new DatabaseHandler(this);
						db.deleteAllContactsTemp();
						db.deleteAllGoodsTemp();
						
						 ListView lview = (ListView) findViewById(R.id.listViewOrder);						        
						 lview.setAdapter(null);
						 lview.refreshDrawableState();
						 
						 TextView txtcontactName = (TextView) findViewById(R.id.txtcontactName);
					     TextView txtcontactCode = (TextView) findViewById(R.id.txtcontactCode);
					     
					     txtcontactName.setText("");
					     txtcontactCode.setText("");
				
					}
					*/
					
					public void NoteOfOrder(View view)
					{
						NoteOfOrder note = NoteOfOrder.newInstance();
						note.notefragment = Note;
						note.setStyle(DialogFragment.STYLE_NO_TITLE, 0);
						note.show(getFragmentManager(), "dialog");
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
