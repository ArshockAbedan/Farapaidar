package com.farapaidar.fara004kala;



import static com.farapaidar.fara004kala.Constant.FIFTH_COLUMN;
import static com.farapaidar.fara004kala.Constant.FIRST_COLUMN;
import static com.farapaidar.fara004kala.Constant.FOURTH_COLUMN;
import static com.farapaidar.fara004kala.Constant.SECOND_COLUMN;
import static com.farapaidar.fara004kala.Constant.THIRD_COLUMN;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


import com.farapaidar.fara004kala.Goods;
import com.farapaidar.fara004kala.DatabaseHandler;
import com.farapaidar.fara004kala.R;
import com.farapaidar.fara004kala.ListviewAdapter;

import android.os.Bundle;
import android.os.Environment;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.TextView;
import android.widget.Toast;

public class MainKalaActivity extends Activity {
	
	
	
	  @SuppressWarnings("rawtypes")
		private ArrayList<HashMap> list;

	@SuppressLint("SdCardPath")
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main_kala);
		
	     DatabaseHandler db = new DatabaseHandler(this);

	        /**
	         * CRUD Operations
	         * */
	        // Inserting Contacts
	        Log.d("Insert: ", "Inserting ..");
	        
	       
	         
	        db.addGoods(new Goods("stone","kilogram","420","3"));

	        db.addGoods(new Goods("سیمان","کیلو","500","2"));
	        db.addGoods(new Goods("میله گرد","شاخه","200","3"));
	       

	        
	        // Reading all contacts
	        Log.d("Reading: ", "Reading all contacts..");
	        List<Goods> goods = db.getAllGoods();      
	        
	        
	        ListView lview = (ListView) findViewById(R.id.listView1);
	        //list.clear();

	        list = new ArrayList<HashMap>();
	        // Setting Headers
		    HashMap tempH = new HashMap();
		    tempH.put(FIRST_COLUMN,"کد");
		    tempH.put(SECOND_COLUMN,"عنوان");
		    tempH.put(THIRD_COLUMN, "واحد شمارش");
		    tempH.put(FOURTH_COLUMN, "موجودی");
		    tempH.put(FIFTH_COLUMN, "نحوه فروش");
	    list.add(tempH);

	    
	    // Adding Items
	        for (Goods gd : goods) {        		        	       	 
	        	 
	             HashMap temp = new HashMap();
	                 temp.put(FIRST_COLUMN,gd.getID());
	                 temp.put(SECOND_COLUMN,gd.getGoodName());
	                 temp.put(THIRD_COLUMN, gd.getCountUnit());
	                 temp.put(FOURTH_COLUMN, gd.getStock());
	                 temp.put(FIFTH_COLUMN, gd.getPriceType());
	             list.add(temp);
	        }
	        
	        
	        try {
	        	
	        	
	        	FileOutputStream fos = openFileOutput("list.txt",  Context.MODE_APPEND | Context.MODE_WORLD_READABLE);
	        	String s = "aref";
	            fos.write(s.toString().getBytes());
	            fos.close();
				/*File myFile = new File("/mysdfile001");
				myFile.createNewFile();
				FileOutputStream fOut = new FileOutputStream(myFile);
				OutputStreamWriter myOutWriter = 
										new OutputStreamWriter(fOut);
				myOutWriter.append(list.toString());
				myOutWriter.close();
				fOut.close();
				*/
	            
	            
	            
	            String storageState = Environment.getExternalStorageState();
	            if (storageState.equals(Environment.MEDIA_MOUNTED)) {
	                File file = new File(getExternalFilesDir(null),
	                        "list.txt");
	                FileOutputStream fos2 = new FileOutputStream(file);
	                fos.write(s.toString().getBytes());
	                fos2.close();
			} 
	            }catch (Exception e) {
				Toast.makeText(getBaseContext(), e.getMessage(),
						5).show();
			}
       
	        
	        ListviewAdapter adapter = new ListviewAdapter(this, list);
	        lview.setAdapter(adapter);
	        
	        // listening to single list item on click
	        lview.setOnItemClickListener(new OnItemClickListener() {
	        	 HashMap c = new HashMap();

			public void onItemClick(AdapterView<?> parent, View view,
	              int position, long id) {

				// c =  (HashMap) ((ListView) findViewById(R.id.listView1)).getItemAtPosition(position);
				 c =  (HashMap) ((ListView) view).getItemAtPosition(position);
				 
				Object v =  c.get("Second");
	        	  
	        	  Toast.makeText(getApplicationContext(),
	  	                v.toString(), Toast.LENGTH_SHORT).show();

	          }
	        });

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main_kala, menu);
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
	    	 
	    	
	    	List<Goods> goods = db.getAllGoods();    
	    	
		    list = new ArrayList<HashMap>();    
		        
		    // Setting Headers
		    HashMap tempH = new HashMap();
		    tempH.put(FIRST_COLUMN,"کد");
		    tempH.put(SECOND_COLUMN,"عنوان");
		    tempH.put(THIRD_COLUMN, "واحد شمارش");
		    tempH.put(FOURTH_COLUMN, "موجودی");
		    tempH.put(FIFTH_COLUMN, "تیپ قیمتی");
	    list.add(tempH);

		    
	    
	    // Adding Items
	        for (Goods gd : goods) {        		        	       	 
	        	 
	             HashMap temp = new HashMap();
	                 temp.put(FIRST_COLUMN,gd.getID());
	                 temp.put(SECOND_COLUMN,gd.getGoodName());
	                 temp.put(THIRD_COLUMN, gd.getCountUnit());
	                 temp.put(FOURTH_COLUMN, gd.getStock());
	                 temp.put(FIFTH_COLUMN, gd.getPriceType());
	             list.add(temp);
	        }
	        
	        ListviewAdapter adapter = new ListviewAdapter(this, list);
	        lview.setAdapter(adapter);
    
	        
	        // listening to single list item on click
	        lview.setOnItemClickListener(new OnItemClickListener() {
	          public void onItemClick(AdapterView<?> parent, View view,
	              int position, long id) {
	        	  
	        	  Toast.makeText(getApplicationContext(),
	  	                ((TextView) view).getText(), Toast.LENGTH_SHORT).show();

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
	    	
	    DatabaseHandler db1 = new DatabaseHandler(this);
	    
	    List<Goods> goods = db1.getSpecificGood(PersonName.toString());

	    
	    ListView lviewspecific = (ListView) findViewById(R.id.listView1);
	    
	    list = new ArrayList<HashMap>();
	    
	    
	    // Setting Headers
	    HashMap tempH = new HashMap();
	    tempH.put(FIRST_COLUMN,"کد");
	    tempH.put(SECOND_COLUMN,"عنوان");
	    tempH.put(THIRD_COLUMN, "واحد شمارش");
	    tempH.put(FOURTH_COLUMN, "موجودی");
	    tempH.put(FIFTH_COLUMN, "نحوه فروش");
    list.add(tempH);

    
    
    // Adding Items
        for (Goods gd : goods) {        		        	       	 
        	 
             HashMap temp = new HashMap();
                 temp.put(FIRST_COLUMN,gd.getID());
                 temp.put(SECOND_COLUMN,gd.getGoodName());
                 temp.put(THIRD_COLUMN, gd.getCountUnit());
                 temp.put(FOURTH_COLUMN, gd.getStock());
                 temp.put(FIFTH_COLUMN, gd.getPriceType());
             list.add(temp);
        }


        
        ListviewAdapter adapter = new ListviewAdapter(this, list);
        lviewspecific.setAdapter(adapter);
        

        
        // listening to single list item on click
        lview.setOnItemClickListener(new OnItemClickListener() {
          public void onItemClick(AdapterView<?> parent, View view,
              int position, long id) {
        	  
        	  Toast.makeText(getApplicationContext(),
  	                ((TextView) view).getText(), Toast.LENGTH_SHORT).show();

          }
        });
	
	    }   
	
	}


}
