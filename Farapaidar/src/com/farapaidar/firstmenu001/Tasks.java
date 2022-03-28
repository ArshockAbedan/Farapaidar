package com.farapaidar.firstmenu001;

public class Tasks {
	
	 //private variables
	 private String _guid;
	 private String _id;
	 private String _title;
	 private String _note;
	 private String _type_task_id;	  
	 private String _type_task_name;
	 private String _type_priority_id;
	 private String _type_priority_name;
	 private String _closed;
	 private String _comment;
	 
	// Empty constructor
    public Tasks()
    {
   	 
    }
    
    // constructor
    public Tasks(String Guid, String ID, String Title, String Note, String TypeTaskId, String TypeTaskName,
    		String TypePriorityId, String TypePriorityName, String Closed, String Comment)
    {
    	this._guid = Guid;
    	this._id = ID;
    	this._title = Title;
    	this._note = Note;
    	this._type_task_id = TypeTaskId;
    	this._type_task_name = TypeTaskName;
    	this._type_priority_id = TypePriorityId;
    	this._type_priority_name = TypePriorityName;
    	this._closed = Closed;   
    	this._comment = Comment;
    }
    
    ////////////////////////////////////////////////////////
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
    
    // getting title
    public String getTitle()
    {
     return this._title;
    }
    
    //setting Title
    public void setTitle(String Title)
    {
    	this._title = Title;
    }

    //////////////////////////////////////
    
 // getting Note
    public String getNote()
    {
     return this._note;
    }
    
    //setting Note
    public void setNote(String Note)
    {
    	this._note = Note;
    }

    //////////////////////////////////////
    
    // getting TypeTaskId
    public String getTypeTaskId()
    {
     return this._type_task_id;
    }
    
    //setting TypeTaskId
    public void setTypeTaskId(String TypeTaskId)
    {
    	this._type_task_id = TypeTaskId;
    }

    //////////////////////////////////////
    
    // getting TypeTaskName
    public String getTypeTaskName()
    {
     return this._type_task_name;
    }
    
    //setting TypeTaskName
    public void setTypeTaskName(String TypeTaskName)
    {
    	this._type_task_name = TypeTaskName;
    }

    //////////////////////////////////////
    
 // getting TypePriorityId
    public String getTypePriorityId()
    {
     return this._type_priority_id;
    }
    
    //setting TypePriorityId
    public void setTypePriorityId(String TypePriorityId)
    {
    	this._type_priority_id = TypePriorityId;
    }

    //////////////////////////////////////
    
    // getting TypePriorityName
    public String getTypePriorityName()
    {
     return this._type_priority_name;
    }
    
    //setting TypePriorityName
    public void setTypePriorityName(String TypePriorityName)
    {
    	this._type_priority_name = TypePriorityName;
    }

    //////////////////////////////////////
 // getting Closed
    public String getClosed()
    {
     return this._closed;
    }
    
    //setting Closed
    public void setClosed(String Closed)
    {
    	this._closed = Closed;
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
