package com.farapaidar.firstmenu001;

public class InboxReports {
	
	 //private variables
	 private String _guid;
	 private String _id;
	 private String _operator_guid;	  
	 private String _operator_name;
	 private String _comment;

   
   
	// Empty constructor
   public InboxReports()
   {
  	 
   }
   
   // Main constructor
   public InboxReports(String Guid, String ID, String OperatorGuid, String OperatorName,
   		String Comment)
   {
   	this._guid = Guid;
   	this._id = ID;
   	this._operator_guid = OperatorGuid;	  
   	this._operator_name = OperatorName;
   	this._comment = Comment;
   }
   
   
   ////////////////////////////////////////////////////////////////////////////
   
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
     
     //getting OperatorGuid
     public String getOperatorGuid()
     {
    	 return this._operator_guid;
     }


     //setting OperatorGuid
     public void setOperatorGuid(String OperatorGuid)
     {
    	 this._operator_guid = OperatorGuid;
     }

     //////////////////////////////////////
     
   //getting OperatorName
     public String getOperatorName()
     {
    	 return this._operator_name;
     }


     //setting OperatorName
     public void setOperatorName(String OperatorName)
     {
    	 this._operator_name = OperatorName;
     }

     //////////////////////////////////////
     
     // getting Comment
     public String getComment()
     {
    	 return this._comment;
     }
     
     
     // setting Comment
     public void setComment(String Comment)
     {
    	 this._comment = Comment;
     }
   

}
