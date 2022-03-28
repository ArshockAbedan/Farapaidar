package com.farapaidar.firstmenu001;

import java.io.File;
import java.io.FileOutputStream;
import java.io.StringWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import org.xmlpull.v1.XmlSerializer;

import android.os.Bundle;
import android.os.Environment;
import android.os.StrictMode;
import android.util.Log;
import android.util.Xml;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;

public class FragmentOfLoadOperatorFromServer extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.fragment_of_load_operator_from_server);
		
		this.setTitle("فراپایدار");
		
		
		Button btnCancel = (Button) findViewById(R.id.btnCancelInFragmentOfLoadOperatorsFromServer);
		btnCancel.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				EndthisProgram();
				
			}
		});
		
		
		
		Button btnOk = (Button) findViewById(R.id.btnOKInFragmentOfLoadOperatorsFromServer);
		btnOk.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ArrayList<Operator> operatorList = ReadOperatorsFromServer();
				MakeOperatorXMLFile(operatorList);
				Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
				startActivity(intent);
			}

			
		});
		
		
	
	}

	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		super.onBackPressed();
		EndthisProgram();
	}

	private void EndthisProgram()
	{
		Intent homeIntent = new Intent(Intent.ACTION_MAIN);
	    homeIntent.addCategory( Intent.CATEGORY_HOME );
	    homeIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);  
	    startActivity(homeIntent);
	    finish();
	}
	
	
	private ArrayList<Operator> ReadOperatorsFromServer() {
		// TODO Auto-generated method stub
		
		 ArrayList<Operator> operatorList = new ArrayList<Operator>();
		
		 
		 // Declare the JDBC objects.
		  Connection DbConn = null;
		  Statement stmt = null;
		  ResultSet resultSet = null;
		  try {  
			  
			  DatabaseHandler db = new DatabaseHandler(this);
			  
				 DBConnection dbconnection =  db.getDBConnection();
			     // Establish the connection.
				  Class.forName("net.sourceforge.jtds.jdbc.Driver").newInstance();
		          
				  String username = dbconnection.getUserName();
				  String password = dbconnection.getPassword();
		          
		          if (android.os.Build.VERSION.SDK_INT > 9) {
		        	  StrictMode.ThreadPolicy policy = 
		        	          new StrictMode.ThreadPolicy.Builder().permitAll().build();
		        	  StrictMode.setThreadPolicy(policy);
		        	  }
		          DriverManager.setLoginTimeout(60);
		          
		          DbConn = DriverManager.getConnection("jdbc:jtds:sqlserver://"+ dbconnection.getIPLocal() +":1433/"+ dbconnection.getDataBaseMain() +";user=" + username + ";password=" + password +";");

		      
		          Log.w("Connection","open");
		          
		        //Tasks ////////////////////////////////////////////////////////////////

			       stmt = DbConn.createStatement();
			       
			      String OperatorsTableName = "vwVisitors";
			   
			       resultSet =  stmt.executeQuery(" SELECT GuidVisitors, AccountTitle, Username, " +
			       		"Pass, GuidBranche, GuidVisitors FROM " + OperatorsTableName + " WHERE Username <>'' AND Pass <>'' ");


			      // ArrayList<Operator> operatorList = new ArrayList<Operator>();
			       
			       while (resultSet.next()) {
			    	   Operator oprt = new Operator();
			    	   if ( resultSet.getString(1) != null)
			    	   {
			    		   oprt.setGuid(resultSet.getString(1).toString().toUpperCase());
			    	   }else
			    	   {
			    		   oprt.setGuid("");
			    	   }
			    	   if ( resultSet.getString(2) != null)
			    	   {
			    		   oprt.setFullName(resultSet.getString(2).toString());
			    	   }else
			    	   {
			    		   oprt.setFullName("");
			    	   }
			    	   if ( resultSet.getString(3) != null)
			    	   {
			    		   oprt.setUserName(resultSet.getString(3).toString());
			    	   }else
			    	   {
			    		   oprt.setUserName("");
			    	   }
			    	   if ( resultSet.getString(4) != null)
			    	   {
			    		   oprt.setPassword(resultSet.getString(4).toString());
			    	   }else
			    	   {
			    		   oprt.setPassword("");
			    	   }
			    	   if ( resultSet.getString(5) != null)
			    	   {
			    		   oprt.setBranche(resultSet.getString(5).toString().toUpperCase());
			    	   }else
			    	   {
			    		   oprt.setBranche("NullForBranche");
			    	   }
			    	   if ( resultSet.getString(6) != null)
			    	   {
			    		   oprt.setReseler(resultSet.getString(6).toString().toUpperCase());
			    	   }else
			    	   {
			    		   oprt.setReseler("");
			    	   }

			    	   operatorList.add(oprt);   
			    	}
			       
			       db.deleteAllOperators();
			       
			       for (Operator operator : operatorList) 
			       {			    	   
			    	   db.addOperator(operator);				
			       }
			       
			       DbConn.close();
		 
		  } catch (Exception e)
		  {
			  Toast.makeText(getApplicationContext(),e.getMessage().toString(), Toast.LENGTH_SHORT).show();
			  Log.w("Error connection","" + e.getMessage());
	      }
			       
			   return operatorList;
		
	}

	private void MakeOperatorXMLFile(ArrayList<Operator> operatorList)
	{
		 //converting data to string prepare for writing to .xml file
	       String operatorsXmlString = writeXmlOperators(operatorList);
	       
	       try {
	    	   // writnig Operators.xml in SDcard/Farapaidar directory
				if (isExternalStorageReadable() && isExternalStorageWritable() )
				{
					String stringFilename = "Operators.xml";
					generateFarapaidarOperatorOnSD(stringFilename,operatorsXmlString,this);
				}

	       } catch (Exception e) {
	    	   Toast.makeText(this,e.toString(), Toast.LENGTH_SHORT).show();
	       }

	}
	
	
	//a function for converting Operators' Items to <needed string> of its Operators.XML file
			private String writeXmlOperators(ArrayList<Operator> operators)
			{ 
			XmlSerializer serializer = Xml.newSerializer(); 
			StringWriter writer = new StringWriter(); 
			try 
			{ 
			serializer.setOutput(writer); 
			serializer.startDocument("UTF-8", true); 
			serializer.startTag("", "Operators"); 
			serializer.attribute("", "number", String.valueOf(operators.size())); 
			for (Operator op: operators)
			{ 
			serializer.startTag("", "Operator"); 
			serializer.startTag("", "guid"); 
			serializer.text(op.getGuid()); 
			serializer.endTag("", "guid");
			serializer.startTag("", "full_name"); 
			serializer.text(op.getFullName()); 
			serializer.endTag("", "full_name");
			serializer.startTag("", "username"); 
			serializer.text(op.getUserName()); 
			serializer.endTag("", "username");
			serializer.startTag("", "password"); 
			serializer.text(op.getPassword()); 
			serializer.endTag("", "password");
			serializer.startTag("", "guid_reseler"); 
			serializer.text(op.getReseler()); 
			serializer.endTag("", "guid_reseler");
			serializer.startTag("", "guid_branche"); 
			serializer.text(op.getBranche()); 
			serializer.endTag("", "guid_branche");
			serializer.endTag("", "Operator"); 
			} 
			serializer.endTag("", "Operators"); 
			serializer.endDocument(); 
			return writer.toString(); 
			} 
			catch (Exception e) 
			{ 
			throw new RuntimeException(e); 
			}
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
			
			
			
			
			
			
			/* Generating Operators.xml */
			@SuppressLint({ "SdCardPath", "WorldReadableFiles" })
			public void generateFarapaidarOperatorOnSD(String sFileName, String sBody, Context contaxt){

				try {

					@SuppressWarnings("deprecation")
					FileOutputStream fos = contaxt.openFileOutput(sFileName, Context.MODE_PRIVATE |

				            Context.MODE_APPEND | Context.MODE_WORLD_READABLE);

				    fos.write(sBody.toString().getBytes());

				    fos.close();

				    File root = new File("/sdcard/","Farapaidar");
					        if (!root.exists()) {
					            root.mkdirs();
					       }

				    File file = new File(root, sFileName);

				    FileOutputStream fos2 = new FileOutputStream(file);

				    fos2.write(sBody.toString().getBytes());

				    fos2.close();
				     
				} 
				catch (Exception e) 
				{				   
					e.printStackTrace();    
				}
					    
					  
			}


}

