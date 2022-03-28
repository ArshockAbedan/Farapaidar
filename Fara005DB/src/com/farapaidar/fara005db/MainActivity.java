package com.farapaidar.fara005db;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.farapaidar.fara005db.DatabaseHandler;
import com.farapaidar.fara005db.Goods;



import android.os.Bundle;
import android.os.Environment;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.Menu;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

@SuppressLint("ShowToast")
public class MainActivity extends Activity {

	String appPath = "";

	@SuppressLint("ShowToast")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	
		
		  DatabaseHandler db = new DatabaseHandler(this);

	        /**
	         * CRUD Operations
	         * */
	        // Inserting Contacts
	        Log.d("Insert: ", "Inserting ..");
	        
	       
	         
	        db.addGoods(new Goods("stone","kilogram","420","3"));

	        db.addGoods(new Goods("سیمان","کیلو","500","2"));
	        db.addGoods(new Goods("میله گرد","شاخه","200","3"));
		
		
		
		db.addGoods(new Goods("stone","kilogram","420","3"));
		
		
		List<Goods> GoodsList = db.getAllGoods();  
		
		 ArrayList<String> list = new ArrayList<String>();
		
		 for (Goods  gd : GoodsList) {        		        	       	 
			
             
           list.add(gd.getGoodName().toString());
          
        }
		 
		 
		 
			if (isExternalStorageReadable() && isExternalStorageWritable() )
			{
				//String stringfileContent = "Hello world!";
				String stringFilename = "Hello.txt";
				generateFarapaidarOnSD(stringFilename,list.toString());
			}
		
		
		 ListView lv = (ListView) findViewById(R.id.listView1);
	  ListAdapter listAdapter = new ArrayAdapter<String>(this, R.layout.simplerow, list);
		
		lv.setAdapter(listAdapter);
		
		
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	
	
	
	/* Checks if external storage is available for read and write */
	public boolean isExternalStorageWritable() {
	    String state = Environment.getExternalStorageState();
	    if (Environment.MEDIA_MOUNTED.equals(state)) {
	        return true;
	    }
	    return false;
	}

	/* Checks if external storage is available to at least read */
	public boolean isExternalStorageReadable() {
	    String state = Environment.getExternalStorageState();
	    if (Environment.MEDIA_MOUNTED.equals(state) ||
	        Environment.MEDIA_MOUNTED_READ_ONLY.equals(state)) {
	        return true;
	    }
	    return false;
	}
	
	public void generateFarapaidarOnSD(String sFileName, String sBody){
	 /*
		try
	    {
	        File root = new File(Environment.getExternalStorageDirectory(), "Farapaidar");
	        if (!root.exists()) {
	            root.mkdirs();
	        }
	        
	        File gpxfile = new File(root, sFileName);
	        FileWriter writer = new FileWriter(gpxfile);
	        writer.append(sBody);
	        writer.flush();
	        writer.close();
	     
	        Toast.makeText(this, "Saved", Toast.LENGTH_SHORT).show();
	    }
	    catch(IOException e)
	    {
	         e.printStackTrace();
	    }
	     
	    */
		
		


try {

   // FileOutputStream fos = openFileOutput(sFileName, Context.MODE_PRIVATE | Context.MODE_APPEND | Context.MODE_WORLD_READABLE);
    

    FileOutputStream fos = openFileOutput(sFileName, Context.MODE_PRIVATE |

            Context.MODE_APPEND);

    fos.write(sBody.toString().getBytes());

    fos.close();

 

    String storageState = Environment.getExternalStorageState();

    if (storageState.equals(Environment.MEDIA_MOUNTED)) {
    	
    	
    	 File root = new File(Environment.getExternalStorageDirectory(), "Farapaidar");
	        if (!root.exists()) {
	            root.mkdirs();
	        }

        File file = new File(root, sFileName);

        FileOutputStream fos2 = new FileOutputStream(file);

        fos2.write(sBody.toString().getBytes());

        fos2.close();

    }

} catch (Exception e) {

    e.printStackTrace();

}
	    
	   } 

}
