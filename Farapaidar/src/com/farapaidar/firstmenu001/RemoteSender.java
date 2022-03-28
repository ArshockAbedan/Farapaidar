package com.farapaidar.firstmenu001;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;
import java.util.UUID;
import android.app.DialogFragment;
import android.content.Context;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class RemoteSender extends DialogFragment {

	static RemoteSender newInstance() {
        return new RemoteSender();
    }


	 @Override
	  public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
		
		
		 View v = inflater.inflate(R.layout.activity_remote_sender, container, false);
		
		// Set onClickEvent for BtnOrders
		Button btnInsOrders = (Button) v.findViewById(R.id.btnOrders);
		//btnInsOrders.setText("درج اطلاعات ثبت سفارش");
		btnInsOrders.setOnClickListener( new OnClickListener() {
			
			@Override
			public void onClick(View v) {

				v.setEnabled(false);
				 Context contaxt = getActivity();
				 View vp = (View) v.getParent();
				 InsertOrders(vp,contaxt);
				 v.setEnabled(true);
				 //v.refreshDrawableState();
			}  
        });
		
		 return v;
		}
	

	
   @SuppressWarnings("unused")
public void	InsertOrders(View v,Context contaxt)
   {
	   // Visibling Buttons
	   Button btnInsOrders = (Button) v.findViewById(R.id.btnOrders);
	   btnInsOrders.setEnabled(false);

	   
	// Declare the JDBC objects.
		  Connection DbConnFactors = null;
		  Statement stmtFactors = null;
		  ResultSet resultSetFactors = null;
		  
		  Connection DbConnOrders = null;
		  Statement stmtOrders = null;
		  ResultSet resultSetOrders = null;

		  try {
			  
			  DatabaseHandler db = new DatabaseHandler(contaxt);
				 
			  DBConnection dbconnection =  db.getDBConnection();
			     // Establish the connection.
				  Class.forName("net.sourceforge.jtds.jdbc.Driver").newInstance();
				  
				 
		          
				  String username = dbconnection.getUserName();
				  String password = dbconnection.getPassword();
		          //String username = "Sa";
		          //String password = "1";
		          
		          if (android.os.Build.VERSION.SDK_INT > 9) {
		        	  StrictMode.ThreadPolicy policy = 
		        	          new StrictMode.ThreadPolicy.Builder().permitAll().build();
		        	  StrictMode.setThreadPolicy(policy);
		        	  }
		          
		          DbConnFactors = DriverManager.getConnection("jdbc:jtds:sqlserver://"+ dbconnection.getIPRemote() +":1433/"
		          + dbconnection.getDataBaseMain() +";user=" + username + ";password=" + password +";");
		      
		         
		          // Connection DbConn = DriverManager.getConnection("jdbc:jtds:sqlserver://192.168.1.254:1433/DBSA;user=" + username + ";password=" + password +";Network Library=DBMSSOCN;");
		          //DbConnOrders = DriverManager.getConnection("jdbc:jtds:sqlserver://192.168.1.254:1433/FPDBLite;user=" + username + ";password=" + password +";");

		      
		          String FactorsTableName = "Request";
		          String OrdersTableName = "RequestDetail";
		      
		       
		       Log.w("Connection","open");

		       //Factors
		       List<Factors> FactorsList = db.getAllFactorsUnsended(MainMenuActivity.OperatorGuid);
		       for (Factors factors : FactorsList) 
		       {
		    	   stmtFactors = null ;
		    	   resultSetFactors = null;
		    	   stmtFactors = DbConnFactors.createStatement();

		    	   String Query = " INSERT INTO "
							+ FactorsTableName
							+ "( GuidRequest, "
							+ " TypeInvcID, GuidCustomer, LocalDate,"
							+ " LocalTime, SystemID, GuidReseler, Note )"
							+ " Values ( '"
							+ UUID.fromString(factors.getGuidInvcRequest())
							+ "', '" + factors.getTypeInvcId() + "', '"
							//+ "', (Select isnull(Max(id),1)+1 from Request) ,'" + factors.getTypeInvcId() + "', '"
							+ UUID.fromString(factors.getGuidCustomer()) + "', N'"
							+ factors.getLocalDate() + "', N'"
							+ factors.getLocalTime() + "', N'"
							+ factors.getSystemID() + "', '"
							+ UUID.fromString(factors.getGuidReseler()) + "', N'"
							+ factors.getNote() + "' " + ");";
					stmtFactors.execute(Query);
		    	   
		    	   db.updateFactorToSendedMode(factors);
		       }
		       
		       DbConnFactors.close();
		       // Orders
		       
		       
		       DbConnOrders = DriverManager.getConnection("jdbc:jtds:sqlserver:/"+ dbconnection.getIPRemote() +":1433/"
		       + dbconnection.getDataBaseMain() +";user=" + username + ";password=" + password +";");
		       
		       List<Orders> OrdersList = db.getAllOrdersUnsended(MainMenuActivity.OperatorGuid);
		       for (Orders ords : OrdersList) 
		       {
		    	   stmtOrders = null ;
		    	   resultSetOrders = null;
		    	   stmtOrders = DbConnOrders.createStatement();
		    	   
		    	   String Query = " INSERT INTO "
							+ OrdersTableName
							+ "( GuidRequestDetail, GuidRequest, "
							+ " GuidProdScale,"
							+ " Quantity, Fee )"
							+ " Values ( '"
							+ UUID.fromString(ords.getGuidRequest()) + "', '"
							+ UUID.fromString(ords.getGuidInvcRequest()) + "', '"
							//+ " (SELECT isnull(MAX(id),0)+1 FROM RequestDetail) , '"
							+ UUID.fromString(ords.getGuidProdScale()) + "', "
							+ ords.getQuantity()
							+ ", " + ords.getFee() + ");";
					stmtOrders.execute(Query);
		    	   
		    	   db.updateOrderToSendedMode(ords);
		       }




		       DbConnOrders.close();
			  } catch (Exception e)
		      {
		      Log.w("Error connection","" + e.getMessage());
		      Toast.makeText(contaxt,"عدم موفقیت در ارسال گردش عملیات به ستاد", Toast.LENGTH_LONG).show();
		      }
		  
		// Enabling Buttons  
		  btnInsOrders.setEnabled(true);
		  		  
   }
   

}

