package com.farapaidar.fara006readfiles;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;


import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;


import android.os.Bundle;
import android.os.Environment;
import android.app.Activity;
import android.view.Menu;
import android.widget.TextView;

public class MainActivity extends Activity {
	
	
	
	 // XML node keys
    static final String KEY_ITEM = "Product"; // parent node
    static final String KEY_NAME = "name";
    static final String KEY_COUNT_UNIT = "count_unit";
    static final String KEY_STOCK = "stock";
    static final String KEY_PRICE_TYPE = "price_type";
	
 

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		StringBuilder text = new StringBuilder();
	    try {
	    File sdcard = Environment.getExternalStorageDirectory();
	    String sdcardpath = sdcard.getAbsolutePath();
	    sdcardpath += "/Farapaidar";
	    File file = new File(sdcardpath,"Products.xml");
	   System.out.println("exception");

	        BufferedReader br = new BufferedReader(new FileReader(file));  
	        String line;   
	        while ((line = br.readLine()) != null) {
	                    text.append(line);
	                    System.out.println("text : "+text+" : end");
	                    text.append('\n');
	                    } 
	        br.close();
	        }
	    catch (IOException e) {
	        e.printStackTrace();
	        System.out.println("hello");           

	    }

	     
	    XMLParser parser = new XMLParser();
	    String xml = text.toString(); // getting XML
	    Document doc = parser.getDomElement(xml); // getting DOM element
	     
	    NodeList nl = doc.getElementsByTagName(KEY_ITEM);
	     
	    Goods[] goods = new  Goods[nl.getLength()];
	    
	    
	    // looping through all item nodes <item>      
	    for (int i = 0; i < nl.getLength(); i++) {
	    	Element e = (Element) nl.item(i);
	    	
	    	Goods gd = new  Goods();
	    	gd._id = i+1;
	    	gd._good_name = parser.getValue(e, KEY_NAME); // name child value
	    	gd._count_unit = parser.getValue(e, KEY_COUNT_UNIT); // cost child value
	    	gd._stock = parser.getValue(e, KEY_STOCK); // description child value
	    	gd._price_type = parser.getValue(e, KEY_PRICE_TYPE); // description child value 

	    	goods[i] = gd;
	    }

	    TextView tv = (TextView)findViewById(R.id.txt);  

	    tv.setText(goods.toString()); ////Set the text to text view.
	  }


	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
