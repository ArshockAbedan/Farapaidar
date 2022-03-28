package com.farapaidar.firstmenu001;

public class Operator {
	
	 //private variables
	private  String _guid;
	private  String _full_name;
    private  String _username;
    private  String _password;
    private  String _reseler;
    private  String _branche;
    
    
 // Empty constructor
    public Operator()
    {
   	 
    }
    
    
//  constructor with Fields
    public Operator(String Guid, String FullName, String UserName, String Password,
    		String Reseler, String Branche)
    {
    	this._guid = Guid;
    	this._full_name = FullName;
    	this._username = UserName;
    	this._password = Password;
    	this._reseler = Reseler;
    	this._branche = Branche;
    }
    
    
    //////////////////////////////////////////////////////////////////////////
    
    //getting Guid
    public String getGuid()
    {
   	 return this._guid;
    }


    //setting Guid
    public void setGuid(String Guid)
    {
   	 this._guid = Guid;
    }

    //////////////////////////////////////
    
  //getting FullName
    public String getFullName()
    {
   	 return this._full_name;
    }


    //setting FullName
    public void setFullName(String FullName)
    {
   	 this._full_name = FullName;
    }

    //////////////////////////////////////
    
  //getting UserName
    public String getUserName()
    {
   	 return this._username;
    }


    //setting UserName
    public void setUserName(String UserName)
    {
   	 this._username = UserName;
    }

    //////////////////////////////////////
    
  //getting Password
    public String getPassword()
    {
   	 return this._password;
    }


    //setting Password
    public void setPassword(String Password)
    {
   	 this._password = Password;
    }

    //////////////////////////////////////
    
  //getting Reseler
    public String getReseler()
    {
   	 return this._reseler;
    }


    //setting Reseler
    public void setReseler(String Reseler)
    {
   	 this._reseler = Reseler;
    }

    //////////////////////////////////////
    
  //getting Branche
    public String getBranche()
    {
   	 return this._branche;
    }


    //setting Branche
    public void setBranche(String Branche)
    {
   	 this._branche = Branche;
    }

    //////////////////////////////////////
    
    

}
