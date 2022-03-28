package com.farapaidar.firstmenu001;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;

public class WorkflowActivity extends Activity {
	
	public static Context contaxt;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_workflow);
		
		contaxt = getApplicationContext();
		
		
		Button  btnDefineWorkInWorkflow = (Button) findViewById(R.id.btnDefineWorkInWorkflow);
		btnDefineWorkInWorkflow.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(getApplicationContext(), DefineWorkActivity.class);
				DefineWorkActivity.FlagForCreateOrUpdate = true;
				startActivity(intent);
				
				
			}
		});
		
		
		Button btnTasksInWorkflow = (Button) findViewById(R.id.btnTasksInWorkflow);
		btnTasksInWorkflow.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(getApplicationContext(), TaskFirstPage.class); 
				startActivity(intent);
			}
		});
		
		
		Button  btnCartableInWorkflow = (Button) findViewById(R.id.btnCartableInWorkflow);
		btnCartableInWorkflow.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(getApplicationContext(), CartableActivity.class); 
				startActivity(intent);
				
			}
		});
		
		
		
		Button btnReceivedInWorkflow = (Button) findViewById(R.id.btnReceivedInWorkflow);
		btnReceivedInWorkflow.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				// Declare the JDBC objects.
				  Connection DbConn = null;
				  Statement stmt = null;
				  ResultSet resultSet = null;
				  try {
					  
					  
					  
					  DatabaseHandler db = new DatabaseHandler(contaxt);
					  
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
				          
				          DbConn = DriverManager.getConnection("jdbc:jtds:sqlserver://"+ dbconnection.getIPRemote() +":1433/"+ dbconnection.getDataBaseAndroid() +";user=" + username + ";password=" + password +";");

				      
				          Log.w("Connection","open");
				          
				        //Tasks ////////////////////////////////////////////////////////////////

					       stmt = DbConn.createStatement();
					   
					       resultSet =  stmt.executeQuery(" SELECT * FROM Task");


					       ArrayList<Tasks> taskList = new ArrayList<Tasks>();
					       
					       while (resultSet.next()) {
					    	   Tasks tsk = new Tasks();
					    	   if ( resultSet.getString(1) != null)
					    	   {
					    		   tsk.setGuid(resultSet.getString(1).toString());
					    	   }else
					    	   {
					    		   tsk.setGuid("");
					    	   }
					    	   if ( resultSet.getString(2) != null)
					    	   {
					    		   tsk.setID(resultSet.getString(2).toString());
					    	   }else
					    	   {
					    		   tsk.setID("");
					    	   }
					    	   if ( resultSet.getString(3) != null)
					    	   {
					    		   tsk.setTitle(resultSet.getString(3).toString());
					    	   }else
					    	   {
					    		   tsk.setTitle("");
					    	   }
					    	   if ( resultSet.getString(4) != null)
					    	   {
					    		   tsk.setNote(resultSet.getString(4).toString());
					    	   }else
					    	   {
					    		   tsk.setNote("");
					    	   }
					    	   if ( resultSet.getString(5) != null)
					    	   {
					    		   tsk.setTypeTaskId(resultSet.getString(5).toString());
					    	   }else
					    	   {
					    		   tsk.setTypeTaskId("");
					    	   }
					    	   if ( resultSet.getString(6) != null)
					    	   {
					    		   tsk.setTypeTaskName(resultSet.getString(6).toString());
					    	   }else
					    	   {
					    		   tsk.setTypeTaskName("");
					    	   }
					    	   if ( resultSet.getString(7) != null)
					    	   {
					    		   tsk.setTypePriorityId(resultSet.getString(7).toString());
					    	   }else
					    	   {
					    		   tsk.setTypePriorityId("");
					    	   }
					    	   if ( resultSet.getString(8) != null)
					    	   {
					    		   tsk.setTypePriorityName(resultSet.getString(8).toString());
					    	   }else
					    	   {
					    		   tsk.setTypePriorityName("");
					    	   }
					    	   if ( resultSet.getString(9) != null)
					    	   {
					    		  /* if (resultSet.getString(9).toString().equalsIgnoreCase("0"))
					    		   {
					    			   tsk.setClosed("False");
					    		   } 
					    		   else if (resultSet.getString(9).toString().equalsIgnoreCase("1"))
					    		   {
					    			   tsk.setClosed("True");   
					    		   }*/
					    		   
					    		   tsk.setClosed(resultSet.getString(9).toString());
					    	   }else
					    	   {
					    		   tsk.setClosed("");
					    	   }
					    	   if ( resultSet.getString(10) != null)
					    	   {
					    		   tsk.setComment(resultSet.getString(10).toString());
					    	   }else
					    	   {
					    		   tsk.setComment("");
					    	   }
					    	   
					    	   
					    	   taskList.add(tsk);   
					    	}
					       
					       db.deleteAllTasks();
					       
					       for (Tasks task : taskList) {
					    	   
					    	   db.addTasks(task);
						} 
					       
					       
					     //Inbox ////////////////////////////////////////////////////////////////

					       stmt = DbConn.createStatement();
					   
					       resultSet =  stmt.executeQuery(" SELECT * FROM Inbox");


					       ArrayList<Inboxes> inboxesList = new ArrayList<Inboxes>();
					       
					       while (resultSet.next()) {
					    	   Inboxes inbx = new Inboxes();
					    	   if ( resultSet.getString(1) != null)
					    	   {
					    		   inbx.setGuid(resultSet.getString(1).toString());
					    	   }else
					    	   {
					    		   inbx.setGuid("");
					    	   }
					    	   if ( resultSet.getString(2) != null)
					    	   {
					    		   inbx.setID(resultSet.getString(2).toString());
					    	   }else
					    	   {
					    		   inbx.setID("");
					    	   }
					    	   if ( resultSet.getString(3) != null)
					    	   {
					    		   inbx.setTaskGuid(resultSet.getString(3).toString());
					    	   }else
					    	   {
					    		   inbx.setTaskGuid("");
					    	   }
					    	   if ( resultSet.getString(4) != null)
					    	   {
					    		   inbx.setTaskTitle(resultSet.getString(4).toString());
					    	   }else
					    	   {
					    		   inbx.setTaskTitle("");
					    	   }
					    	   if ( resultSet.getString(5) != null)
					    	   {
					    		   inbx.setOperatorGuid(resultSet.getString(5).toString());
					    	   }else
					    	   {
					    		   inbx.setOperatorGuid("");
					    	   }
					    	   if ( resultSet.getString(6) != null)
					    	   {
					    		   inbx.setOperatorName(resultSet.getString(6).toString());
					    	   }else
					    	   {
					    		   inbx.setOperatorName("");
					    	   }
					    	   if ( resultSet.getString(7) != null)
					    	   {
					    		   inbx.setLocalDate(resultSet.getString(7).toString());
					    	   }else
					    	   {
					    		   inbx.setLocalDate("");
					    	   }
					    	   if ( resultSet.getString(8) != null)
					    	   {
					    		   inbx.setLocalTime(resultSet.getString(8).toString());
					    	   }else
					    	   {
					    		   inbx.setLocalTime("");
					    	   }
					    	   if ( resultSet.getString(9) != null)
					    	   {
					    		   inbx.setVisited(resultSet.getString(9).toString());
					    	   }else
					    	   {
					    		   inbx.setVisited("");
					    	   }
					    	   if ( resultSet.getString(10) != null)
					    	   {
					    		   inbx.setRefered(resultSet.getString(10).toString());
					    	   }else
					    	   {
					    		   inbx.setRefered("");
					    	   }
					    	   
					    	   inboxesList.add(inbx);   
					    	}
					       
					       db.deleteAllTaskTypes();
					       
					       for (Inboxes inbox : inboxesList) {
					    	   
					    	   db.addInbox(inbox);
						} 
					       
					       
					       DbConn.close();
				          
				        //task type ////////////////////////////////////////////////////////////////

					       DbConn = DriverManager.getConnection("jdbc:jtds:sqlserver://"
					       + dbconnection.getIPRemote() +":1433/"+ dbconnection.getDataBaseMain() 
					       +";user=" + username + ";password=" + password +";");

					       
				       stmt = DbConn.createStatement();
				   
				       resultSet =  stmt.executeQuery(" SELECT * FROM Type_Task");


				       ArrayList<TaskTypes> tasktypeList = new ArrayList<TaskTypes>();
				       
				       while (resultSet.next()) {
				    	   TaskTypes tT = new TaskTypes();
				    	   if ( resultSet.getString(1) != null)
				    	   {
				    		   tT.setID(resultSet.getString(1).toString());
				    	   }else
				    	   {
				    		   tT.setID("");
				    	   }
				    	   if ( resultSet.getString(2) != null)
				    	   {
				    		   tT.setName(resultSet.getString(2).toString());
				    	   }else
				    	   {
				    		   tT.setName("");
				    	   }
				    	   
				    	   tasktypeList.add(tT);   
				    	}
				       
				       db.deleteAllTaskTypes();
				       
				       for (TaskTypes taskType : tasktypeList) {
				    	   
				    	   db.addTaskTypes(taskType);
					} 
				       
				       
				       
				     //Priority type ////////////////////////////////////////////////////////////////
				       stmt = null;
				       
				       stmt = DbConn.createStatement();
				       
				       resultSet = null;
					   
				       resultSet =  stmt.executeQuery(" SELECT * FROM Type_Priority");


				       ArrayList<PriorityTypes> prioritytypeList = new ArrayList<PriorityTypes>();
				       
				       while (resultSet.next()) {
				    	   PriorityTypes pT = new PriorityTypes();
				    	   if ( resultSet.getString(1) != null)
				    	   {
				    		   pT.setID(resultSet.getString(1).toString());
				    	   }else
				    	   {
				    		   pT.setID("");
				    	   }
				    	   if ( resultSet.getString(2) != null)
				    	   {
				    		   pT.setName(resultSet.getString(2).toString());
				    	   }else
				    	   {
				    		   pT.setName("");
				    	   }
				    	   
				    	   prioritytypeList.add(pT);   
				    	}

				       
				       db.deleteAllPriorityTypes();
				       
				       
				       for (PriorityTypes priorityType : prioritytypeList) {
						
				    	   db.addPriorityTypes(priorityType);
					}
				       

				      DbConn.close();
					  } catch (Exception e)
				      {
						  Toast.makeText(v.getContext(),e.getMessage().toString(), Toast.LENGTH_SHORT).show();
				      Log.w("Error connection","" + e.getMessage());
				      }
				
				
			}
		});
		
		
	}

	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		super.onBackPressed();
		
		Intent intent = new Intent(this, MainMenuActivity.class);
		intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		startActivity(intent);
	}

	

}
