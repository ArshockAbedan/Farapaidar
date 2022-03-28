package com.farapaidar.firstmenu001;

public class Inboxes {


	 //private variables
	 private String _guid;
	 private String _id;
	 private String _task_guid;
	 private String _task_title;
	 private String _operator_guid;	  
	 private String _operator_name;
	 private String _local_date;
	 private String _local_time;
	 private String _visited;
	 private String _refered;
	 
    
    
	// Empty constructor
    public Inboxes()
    {
   	 
    }
    
    // Main constructor
    public Inboxes(String Guid, String ID, String TaskGuid, 
    		String TaskTitle, String OperatorGuid, String OperatorName,
    		String LocalDate, String LocalTime, String Visited, String Refered)
    {
    	this._guid = Guid;
    	this._id = ID;
    	this._task_guid = TaskGuid;
    	this._task_title = TaskTitle;
    	this._operator_guid = OperatorGuid;	  
    	this._operator_name = OperatorName;
    	this._local_date = LocalDate;
    	this._local_time = LocalTime;
    	this._visited = Visited;
    	this._refered = Refered;
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
    
    //getting TaskGuid
    public String getTaskGuid()
    {
   	 return this._task_guid;
    }


    //setting TaskGuid
    public void setTaskGuid(String TaskGuid)
    {
   	 this._task_guid = TaskGuid;
    }

    //////////////////////////////////////
    
  //getting TaskTitle
    public String getTaskTitle()
    {
   	 return this._task_title;
    }


    //setting TaskTitle
    public void setTaskTitle(String TaskTitle)
    {
   	 this._task_title = TaskTitle;
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
    
    //getting LocalDate
    public String getLocalDate()
    {
   	 return this._local_date;
    }


    //setting LocalDate
    public void setLocalDate(String LocalDate)
    {
   	 this._local_date = LocalDate;
    }

    //////////////////////////////////////
    
    //getting LocalTime
    public String getLocalTime()
    {
   	 return this._local_time;
    }


    //setting LocalTime
    public void setLocalTime(String LocalTime)
    {
   	 this._local_time = LocalTime;
    }

    //////////////////////////////////////
    
    //getting Visited
    public String getVisited()
    {
   	 return this._visited;
    }


    //setting Visited
    public void setVisited(String Visited)
    {
   	 this._visited = Visited;
    }

    //////////////////////////////////////
    
    //getting Refered
    public String getRefered()
    {
   	 return this._refered;
    }


    //setting Refered
    public void setRefered(String Refered)
    {
   	 this._refered = Refered;
    }
    
}
