package com.farapaidar.firstmenu001;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import android.os.Bundle;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends Activity {
	
	//XML node keys
    static final String KEY_DataBaseMAIN = "database_main";
    static final String KEY_DataBaseANDROID = "database_android";
	//static final String KEY_USER_NAME = "username"; // repeated below
	//static final String KEY_PASSWORD = "password"; // repeated below
	static final String KEY_IP_LOCAL = "ip_local";
	static final String KEY_IP_REMOTE = "ip_remote";
	
	 private static final String KEY_GUID = "guid";
	 private static final String KEY_FULL_NAME = "full_name";
     private static final String KEY_USER_NAME = "username";
     private static final String KEY_PASSWORD = "password";
     private static final String KEY_RESELER = "guid_reseler";
     private static final String KEY_BRANCHE = "guid_branche";	
     

	@SuppressLint("SdCardPath")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		
		//  Aref time lock
		try{
			 Calendar c = Calendar.getInstance();
			 SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		     String formattedDate = df.format(c.getTime());
	
    		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        	Date date1 = (Date) sdf.parse(formattedDate);
        	Date date2 = (Date) sdf.parse("2015-12-05");
        	Date date3 = (Date) sdf.parse("2014-04-05");
 
        	System.out.println(sdf.format(date1));
        	System.out.println(sdf.format(date2));
        	
 
        	if(date1.compareTo(date2)>0 && date3.compareTo(date1)>0 ){
        		/*finish(); */
        		System.out.println("Date1 is after Date2");
        	}else if(date1.compareTo(date2)<0){
        		System.out.println("Date1 is before Date2");
        	}else if(date1.compareTo(date2)==0){
        		System.out.println("Date1 is equal to Date2");
        	}else{
        		System.out.println("How to get here?");
        	}
 
    	}catch(Exception ex){
    		ex.printStackTrace();
    	}
		
		
		DatabaseHandler db = new DatabaseHandler(this);
		int CurrentworkedDBVersion = 2 ;
		db.onUpgrade(db.getReadableDatabase(), CurrentworkedDBVersion, db.getWritableDatabase().getVersion());
		
		this.setTitle("احراز هویت");		
		
		//Reading DBConnection.xml
		ReadXMLFile readxmlfile = new ReadXMLFile();			
	
		readxmlfile.setPath("/sdcard/Farapaidar/");						
	
		readxmlfile.setFileName("DBConnection.xml");			
	
		File fileDBConnection = readxmlfile.ReadFile();

		if (fileDBConnection == null)			
		{							
			Toast.makeText(this, "فایل مربوط  ه اطلاعات سرور در مسیر موجود نیست", Toast.LENGTH_LONG).show();					
		}else					
		{										
			NodeList nList = readxmlfile.ParseFile(fileDBConnection,"DBConnection");					
			if (nList == null)										
			{											
				Toast.makeText(this, "فایل مربوط  به اطلاعات سرور در مسیر موجود نیست", Toast.LENGTH_LONG).show();									
			}else								
			{						
				DBConnection[] dbconnections = new  DBConnection[nList.getLength()];		
					
				for (int i=0; i<nList.getLength(); i++) {

						Node node = nList.item(i);
						if (node.getNodeType() == Node.ELEMENT_NODE) {
						Element element2 = (Element) node;
						DBConnection dbcn = new  DBConnection();
						dbcn.setID(String.valueOf(i+1)); 
						dbcn.setDataBaseMain(getValue(KEY_DataBaseMAIN,element2)); // database_main child value
						dbcn.setDataBaseAndroid(getValue(KEY_DataBaseANDROID,element2)); // database_Android child value
						dbcn.setUserName(getValue(KEY_USER_NAME,element2)); // username child value
						dbcn.setPassword(getValue(KEY_PASSWORD,element2)); // password child value
						dbcn.setIPLocal(getValue(KEY_IP_LOCAL,element2)); //  ip_local child value
						dbcn.setIPRemote(getValue(KEY_IP_REMOTE,element2)); // ip_remote child value

				    	
				    	dbconnections[i] = dbcn;
						}
						}
						
						
						// Inserting Contacts
					    
					    // First delete existing records in DBConnection table
					    db.deleteAllDBConnection();
					    
					   // Then inserting new records which readed from DBConnection.xml file
					    Log.d("Insert: ", "Inserting ..");
					    
					    for (int i = 0; i < dbconnections.length; i++) {
					    	db.addDBConnection(dbconnections[i]);
					    } 
		}
	
	}
		

			
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		
		
		DatabaseHandler db = new DatabaseHandler(this);
	
		
		// Reading Operators.xml
		ReadXMLFile readxmlfile = new ReadXMLFile();
		//String sd_Path = getResources().getString(R.string._sdcard_farapaidar_);
		readxmlfile.setPath("/sdcard/Farapaidar/");
		readxmlfile.setFileName("Operators.xml");
		File fileOperators = readxmlfile.ReadFile();
		
		NodeList nList = readxmlfile.ParseFile(fileOperators,"Operator");
		if (nList == null)
		{
			Toast.makeText(this, "فایل مربوط  به  اپراتورها در مسیر موجود نیست", Toast.LENGTH_LONG).show();
			ShowReadOpertorsFromServerFragment();
		}else
		{
			Operator[] operators = new  Operator[nList.getLength()];
			
			for (int i=0; i<nList.getLength(); i++) {

			Node node = nList.item(i);
			if (node.getNodeType() == Node.ELEMENT_NODE) {
			Element element2 = (Element) node;
			Operator op = new  Operator();
			op.setGuid(getValue(KEY_GUID,element2)); // Guid child value
			op.setFullName(getValue(KEY_FULL_NAME,element2)); // id child value
			op.setUserName(getValue(KEY_USER_NAME,element2)); // username child value
			op.setPassword(getValue(KEY_PASSWORD,element2)); // password child value
			op.setReseler(getValue(KEY_RESELER,element2)); // reseler guid child value
			op.setBranche(getValue(KEY_BRANCHE,element2)); // Branch guid child value
	    	
	    	
	    	operators[i] = op;
			}
			}
			
			// Inserting Operators
		    // First delete existing records in Operators table
		    db.deleteAllOperators();
		    
		         // Then inserting new records which readed from Operators.xml file
		    Log.d("Insert: ", "Inserting ..");
		    
		    for (int i = 0; i < operators.length; i++) {
		    	db.addOperator(operators[i]);
		    }  
			
			
		}
		
		
	}

	private void ShowReadOpertorsFromServerFragment() {
		Intent intent = new Intent(getApplicationContext(), FragmentOfLoadOperatorFromServer.class);
		startActivity(intent);
	}

	
	
	public void EnterApp(View view)
	{
		EditText editTextUserName = (EditText) findViewById(R.id.editTextUserName);
		String UserName = editTextUserName.getText().toString();
		EditText editTextPass = (EditText) findViewById(R.id.editTextPass);
		String PassWord = editTextPass.getText().toString();
		
		
		DatabaseHandler db = new DatabaseHandler(this);
		Operator operator = db.getSpecificOperator(UserName, PassWord);
	    if (operator==null)			
	    {
	    	Toast.makeText(this, "نام کاربری و یا رمزعبور صحیح نیست", Toast.LENGTH_LONG).show();	
	    	editTextUserName.setText("");
	    	editTextPass.setText("");
	    }
	    else
	    {
	    	// Showing Bultain Activity
	    	if ( db.getAllBultain() != null)
			{
				Intent intent = new Intent(getApplicationContext(), ShowBultain.class);
				ShowBultain.OperatorGuid = operator.getGuid();
				ShowBultain.ReselerGuid = operator.getReseler();
				ShowBultain.BrancheGuid = operator.getBranche();
				ShowBultain.OperatorFullName = operator.getFullName();
				startActivity(intent);
				
			}else
			{
				Intent intent = new Intent(getApplicationContext(), MainMenuActivity.class); 
				MainMenuActivity.OperatorGuid = operator.getGuid();
				MainMenuActivity.ReselerGuid = operator.getReseler();
				MainMenuActivity.BrancheGuid = operator.getBranche();
				MainMenuActivity.OperatorFullName = operator.getFullName();
				startActivity(intent);
			}
	    }
		
	}
	
	
	// get value of a tag in an Element
		private static String getValue(String tag, Element element) {  
			NodeList nodeList = element.getElementsByTagName(tag).item(0).getChildNodes();  
			Node node = nodeList.item(0);  
			return node.getNodeValue();		
			}

}
