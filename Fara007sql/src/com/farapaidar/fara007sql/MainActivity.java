package com.farapaidar.fara007sql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import net.sourceforge.jtds.jdbc.Driver;

import android.os.Bundle;
import android.os.StrictMode;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;
import android.widget.TextView;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		
		
		
		//String connectionUrl = "jdbc:jtds:sqlserver://192.168.1.254:1433;" +
	           //  "databaseName=DBSA;integratedSecurity=true";

	  // Declare the JDBC objects.
	  Connection DbConn = null;
	  Statement stmt = null;
	  ResultSet resultSet = null;

	  try {
	     // Establish the connection.
		  
		  Class.forName("net.sourceforge.jtds.jdbc.Driver").newInstance();
		  
		  String username = "Sa";
          String password = "1";
          
          if (android.os.Build.VERSION.SDK_INT > 9) {
        	  StrictMode.ThreadPolicy policy = 
        	          new StrictMode.ThreadPolicy.Builder().permitAll().build();
        	  StrictMode.setThreadPolicy(policy);
        	  }
      // Connection DbConn = DriverManager.getConnection("jdbc:jtds:sqlserver://192.168.1.254:1433/DBSA;user=" + username + ";password=" + password +";Network Library=DBMSSOCN;");
           DbConn = DriverManager.getConnection("jdbc:jtds:sqlserver://192.168.1.254:1433/DBSA;user=" + username + ";password=" + password +";");

      
       
       Log.w("Connection","open");

       stmt = DbConn.createStatement();
      //ResultSet reset = stmt.executeQuery(" INSERT INTO TEST (id,Title) Values (1,N'ÚÇÑÝ');");
      
       resultSet =  stmt.executeQuery(" SELECT COUNT(*) FROM Products");


      TextView tv =(TextView) findViewById(R.id.Txt);
      tv.setText(resultSet.getString(0));

      DbConn.close();
	  } catch (Exception e)
      {
      Log.w("Error connection","" + e.getMessage());
      }
	}
		 /* 
	     con = DriverManager.getConnection(connectionUrl, userName, password); 

	     // Create and execute an SQL statement that returns some data.
	     String SQL = "SELECT * FROM AccGroup;";
	     stmt = con.createStatement();
	     rs = stmt.executeQuery(SQL);

	     // Iterate through the data in the result set and display it.
	     while (rs.next()) {
	    	 
	    	 String s = rs.getString(1) + " " + rs.getString(2)  + " " + rs.getString(3) + " " + rs.getString(4);
	        System.out.println(rs.getString(1) + " " + rs.getString(2));
	     }
	     
	  }

	  // Handle any errors that may have occurred.
	  catch (Exception e) {
	     e.printStackTrace();
	  }
	  finally {
	     if (rs != null) try { rs.close(); } catch(Exception e) {}
	     if (stmt != null) try { stmt.close(); } catch(Exception e) {}
	     if (con != null) try { con.close(); } catch(Exception e) {}
	  }
		
		
	}
*/
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
