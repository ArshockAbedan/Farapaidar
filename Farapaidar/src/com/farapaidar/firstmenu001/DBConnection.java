package com.farapaidar.firstmenu001;

import java.sql.Connection;
import java.sql.DriverManager;

import android.os.StrictMode;
import android.util.Log;

public class DBConnection {
	
	
	//private variables
	private String _id;
	private String _dataBaseMain;
	private String _dataBaseAndroid;
	private String _username;
	private String _password;
	private String _iplocal;
	private String _ipremote;
			
	



    // Empty constructor
    public DBConnection() 
    {

    }
    
    public DBConnection(String ID, String DataBaseMain, String DataBaseAndroid, 
    		String UserName, String Password,String IPLocal, String IPRemote) 
    {
    	this._id = ID;
    	this._dataBaseMain = DataBaseMain;
    	this._dataBaseAndroid = DataBaseAndroid;
    	this._username = UserName;
    	this._password = Password;
    	this._iplocal = IPLocal;
    	this._ipremote = IPRemote;
    	
    }

/*
    public DBConnection(String DataBaseMain, String DataBaseAndroid,  String UserName,
    		String Password,String IPLocal, String IPRemote) 
    {
    	this._dataBaseMain = DataBaseMain;
    	this._dataBaseAndroid = DataBaseAndroid;
    	this._username = UserName;
    	this._password = Password;
    	this._iplocal = IPLocal;
    	this._ipremote = IPRemote;
    	
    }
    */
    
////////////////////////////////////////////////////////////////////////////////////////////////////////////////

 
    public String getID(){
    return this._id;
    }

    public void setID(String id){
    this._id = id;
    }
    
    
    public String getDataBaseMain()
    {
    	return this._dataBaseMain;
    }
       
    public void setDataBaseMain(String DataBaseMain)
    {
    	 this._dataBaseMain = DataBaseMain;
    }
    
    public String getDataBaseAndroid()
    {
    	return this._dataBaseAndroid;
    }
       
    public void setDataBaseAndroid(String DataBaseAndroid)
    {
    	 this._dataBaseAndroid = DataBaseAndroid;
    }
    
    

    public String getUserName()
    {
    	return this._username;
    }
       
    public void setUserName(String UserName)
    {
    	 this._username = UserName;
    }
    
    

    public String getPassword()
    {
    	return this._password;
    }
       
    public void setPassword(String Password)
    {
    	 this._password = Password;
    }
    
    
    
    public String getIPLocal()
    {
    	return this._iplocal;
    }
       
    public void setIPLocal(String IPLocal)
    {
    	 this._iplocal = IPLocal;
    }
    
    

    public String getIPRemote()
    {
    	return this._ipremote;
    }
       
    public void setIPRemote(String IPRemote)
    {
    	 this._ipremote = IPRemote;
    }
    
////////////////////////////////////////////////////////////////////////////////////////////////////////////////    
  
    


}