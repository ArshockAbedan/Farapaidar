package com.farapaidar.firstmenu001;

import static com.farapaidar.firstmenu001.Constant.FIFTH_COLUMN;
import static com.farapaidar.firstmenu001.Constant.FIRST_COLUMN;
import static com.farapaidar.firstmenu001.Constant.FOURTH_COLUMN;
import static com.farapaidar.firstmenu001.Constant.SECOND_COLUMN;
import static com.farapaidar.firstmenu001.Constant.THIRD_COLUMN;
import static com.farapaidar.firstmenu001.Constant.SIXTH_COLUMN;
import static com.farapaidar.firstmenu001.Constant.SEVENTH_COLUMN;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;



import com.farapaidar.firstmenu001.DatabaseHandler;
import com.farapaidar.firstmenu001.Goods;
import com.farapaidar.firstmenu001.ListviewAdapterGoods;
import com.farapaidar.firstmenu001.R;

import android.os.Bundle;

import android.app.Activity;
import android.util.Log;

import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;
import android.annotation.SuppressLint;
import android.content.Intent;


public class GoodFirstPage extends Activity {
	

	public static boolean FlagForOrderInGoods;
	
	
	  @SuppressWarnings("rawtypes")
		private ArrayList<HashMap> list;
	  
	  
	  public String ii;

	@SuppressWarnings({ "rawtypes" })
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_good_first_page);
		
		DatabaseHandler db = new DatabaseHandler(this);

	        // Reading all Gooods
	        Log.d("Reading: ", "Reading all Goods..");
	        List<Goods> goods = db.getAllGoods();   

	        ListView lview = (ListView) findViewById(R.id.listViewGoods);

	        list =  GridAdapterList(goods);
	        ListviewAdapterGoods adapter = new ListviewAdapterGoods(this, list);
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



	@SuppressWarnings({ "rawtypes", })
	@SuppressLint({ "NewApi", "CutPasteId" })
	public void SearchButtonFunc(View View) {
		
		ListView lview = (ListView) findViewById(R.id.listViewGoods);
		lview.setAdapter(null);		
		
		EditText textEdit = (EditText)  findViewById(R.id.editText1);
		

	    String PersonName = textEdit.getText().toString().replace('+', '%');
	    
	    if (PersonName.isEmpty())// search when textbox is NOT Clear
	    {
	    	
	    	lview = (ListView) findViewById(R.id.listViewGoods);
	        lview.setAdapter(null);	
	        DatabaseHandler db = new DatabaseHandler(this);
	    	 
	    	
	    	List<Goods> goods = db.getAllGoods();    
	    	
	    	list =  GridAdapterList(goods);
	        ListviewAdapterGoods adapter = new ListviewAdapterGoods(this, list);
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
	    
	    List<Goods> goods = db1.getSpecificGood(PersonName.toString());

	    
	    ListView lviewspecific = (ListView) findViewById(R.id.listViewGoods);
	    
	    list =  GridAdapterList(goods);
        ListviewAdapterGoods adapter = new ListviewAdapterGoods(this, list);
        lviewspecific.setAdapter(adapter);
        

        
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
	
	}
	
	
	
	// making header & Contents of grid's Adapter  
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private ArrayList<HashMap> GridAdapterList(List<Goods> goods)
	{
		   list = new ArrayList<HashMap>();
		    
	    
		    // Adding Items
		        for (Goods gd : goods) {        		        	       	 
		        	 
		             HashMap temp = new HashMap();
		                 temp.put(FIRST_COLUMN,gd.getID());
		                 temp.put(SECOND_COLUMN,gd.getGoodName());
		                 temp.put(THIRD_COLUMN, gd.getStock());
		                 temp.put(FOURTH_COLUMN,gd.getCountUnit());
		                 temp.put(FIFTH_COLUMN, gd.getPrice());
		                 temp.put(SIXTH_COLUMN, gd.getQBox());
		                 temp.put(SEVENTH_COLUMN, gd.getGuid().toUpperCase());
		             list.add(temp);
		        }


		        return list;
		
	}
	
	
	// Run after an Item (a good) clicked in List(DataGrid)
			@SuppressWarnings("rawtypes")
			public void OnitemClickFunc(HashMap hashMapItem, int position, long id)
			{
				  hashMapItem =  (HashMap) ((ListView) findViewById(R.id.listViewGoods)).getItemAtPosition(position);

					String ItemName =  hashMapItem.get(SECOND_COLUMN).toString();
					String ItemGUID =  hashMapItem.get(SEVENTH_COLUMN).toString();
					
					if (FlagForOrderInGoods==false)
					{
	        			  Toast.makeText(getApplicationContext(),ItemName, Toast.LENGTH_SHORT).show(); 
					}else // if (FlagForOrderInGoods==true) means when this form loaded from orders form!
	  				{
	  					DatabaseHandler db = new DatabaseHandler(this);
	  				    
	  					Goods Selectedgood = db.getGoodsByGuid(ItemGUID);
	  				    
	  					GoodsTemp Selectedgoodtemp = db.getSpecificGoodTempByGuid(ItemGUID);
	  					
	  					String GuidItem;
	 					String Count;
	 					String ItemTotalValue;
	  					
	  					if (Selectedgoodtemp == null)
	  					{
	  						DisplayOrdersItemDetails.flagForCreateOrUpdate = true;
	  						GuidItem =  Selectedgood.getGuid();
	 						Count =  "1";
	 						ItemTotalValue =  Selectedgood.getPrice();
	  					}
	  					else
	  					{
	  						DisplayOrdersItemDetails.flagForCreateOrUpdate = false;
	  						GuidItem =  Selectedgoodtemp.getGuid();
	 						Count =  Selectedgoodtemp.getCount();
	 						ItemTotalValue =  Selectedgoodtemp.getItemTotal();	
	  					}

						DisplayOrdersItemDetails.GuidItem = GuidItem;
						DisplayOrdersItemDetails.Count = Count;
						DisplayOrdersItemDetails.ItemTotalValue = ItemTotalValue;
		        	   Intent intent = new Intent(getApplicationContext(), DisplayOrdersItemDetails.class); 	
		        	   startActivity(intent);	
	  				    
	  				   
	  				}
			}



			@Override
			protected void onDestroy() {
				// TODO Auto-generated method stub
				super.onDestroy();
				FlagForOrderInGoods = false;
			}

}
