package com.farapaidar.firstmenu001;

public class TaskTypes {
	
	//private variables
	 private String _id;  
	 private String _name;

  
  
	// Empty constructor
  public TaskTypes()
  {
 	 
  }
  
  // Main constructor
  public TaskTypes(String ID,String Name)
  {
  	this._id = ID;  
  	this._name = Name;
  }
  
  
  ////////////////////////////////////////////////////////////////////////////
    
  //getting ID
    public String getID()
    {
   	 return this._id;
    }


    //setting ID
    public void setID(String ID)
    {
   	 this._id = ID;
    }

    //////////////////////////////////////
    
    //getting Name
    public String getName()
    {
   	 return this._name;
    }


    //setting Name
    public void setName(String Name)
    {
   	 this._name = Name;
    }
  
}