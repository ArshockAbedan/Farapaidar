package com.farapaidar.firstmenu001;

public class Factors {
	
	
	 //private variables
	 private int _id;
	 private String _GuidInvcRequest;
	 private String _type_invcid;
	 private String _type_invc_name;
	 private String _guid_operator;	  
	 private String _guid_customer;
	 private String _name_customer;		  		  
	 private String _localdate;	  
	 private String _localtime;
	 private String _system_id;
	 private String _guid_reseler;
	 private String _guid_branche;
	 private String _note;
	 private String _sended;
	 private String _localdate_number;
    
    
	// Empty constructor
    public Factors()
    {
   	 
    }
      
    
    public Factors(int ID, String GuidInvcRequest, String TypeInvcId, String TypeInvcName,
   		 String GuidOperator, String GuidCustomer, String NameCustomer, String LocalDate, String LocalTime,
   		 String SystemID, String GuidReseler, String GuidBranche, String Note, String Sended, String LocalDateNumber)
    {
   	 this._id = ID;
   	 this._GuidInvcRequest = GuidInvcRequest;
   	 this._type_invcid = TypeInvcId;
     this._type_invc_name = TypeInvcName;
   	 this._guid_operator = 	GuidOperator;  
   	 this._guid_customer = 	GuidCustomer;
   	 this._name_customer = NameCustomer;		  
   	 this._localdate = LocalDate;	  
   	 this._localtime = LocalTime;
   	 this._system_id = SystemID;
   	 this._guid_reseler = GuidReseler;
   	 this._guid_branche = GuidBranche;
   	 this._note = Note;
   	 this._sended = Sended;
   	 this._localdate_number = LocalDateNumber;
    }
    

    //////////////////////////////////////////////////////////////////////////
   
    //getting ID
    public int getID()
    {
   	 return this._id;
    }


    //setting ID
    public void setID(int ID)
    {
   	 this._id = ID;
    }


    //////////////////////////////////////
    
  //getting GuidInvcRequest
    public String getGuidInvcRequest()
    {
   	 return this._GuidInvcRequest;
    }


    //setting GuidInvcRequest
    public void setGuidInvcRequest(String GuidInvcRequest)
    {
   	 this._GuidInvcRequest = GuidInvcRequest;
    }

    //////////////////////////////////////
    
  //getting TypeInvcId
    public String getTypeInvcId()
    {
   	 return this._type_invcid;
    }


    //setting TypeInvcId
    public void setTypeInvcId(String TypeInvcId)
    {
   	 this._type_invcid = TypeInvcId;
    }
    
    //////////////////////////////////////
    
    
  //getting TypeInvcName
    public String getTypeInvcName()
    {
   	 return this._type_invc_name;
    }


    //setting TypeInvcName
    public void setTypeInvcName(String TypeInvcName)
    {
   	 this._type_invc_name = TypeInvcName;
    }
    
    //////////////////////////////////////
    
  //getting GuidOperator
    public String getGuidOperator()
    {
   	 return this._guid_operator;
    }


    //setting GuidOperator
    public void setGuidOperator(String GuidOperator)
    {
   	 this._guid_operator = GuidOperator;
    }
    
    //////////////////////////////////////
    
  //getting GuidCustomer
    public String getGuidCustomer()
    {
   	 return this._guid_customer;
    }


    //setting GuidCustomer
    public void setGuidCustomer(String GuidCustomer)
    {
   	 this._guid_customer = GuidCustomer;
    }
    
    //////////////////////////////////////
    
  //getting NameCustomer
    public String getNameCustomer()
    {
   	 return this._name_customer;
    }


    //setting NameCustomer
    public void setNameCustomer(String NameCustomer)
    {
   	 this._name_customer = NameCustomer;
    }
    
    //////////////////////////////////////
    
  //getting LocalDate
    public String getLocalDate()
    {
   	 return this._localdate;
    }


    //setting LocalDate
    public void setLocalDate(String LocalDate)
    {
   	 this._localdate = LocalDate;
    }
    
    //////////////////////////////////////
    
  //getting LocalTime
    public String getLocalTime()
    {
   	 return this._localtime;
    }


    //setting LocalTime
    public void setLocalTime(String LocalTime)
    {
   	 this._localtime = LocalTime;
    }
    
    //////////////////////////////////////
    
  //getting SystemID
    public String getSystemID()
    {
   	 return this._system_id;
    }


    //setting SystemID
    public void setSystemID(String SystemID)
    {
   	 this._system_id = SystemID;
    }
    
    //////////////////////////////////////

  //getting GuidReseler
    public String getGuidReseler()
    {
   	 return this._guid_reseler;
    }


    //setting GuidReseler
    public void setGuidReseler(String GuidReseler)
    {
   	 this._guid_reseler = GuidReseler;
    }
    
    //////////////////////////////////////
    
    //getting GuidBranche
    public String getGuidBranche()
    {
   	 return this._guid_branche;
    }


    //setting GuidBranche
    public void setGuidBranche(String GuidBranche)
    {
   	 this._guid_branche = GuidBranche;
    }
    
    //////////////////////////////////////
    
    //getting Note
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
    
  //getting Sended
    public String getSended()
    {
   	 return this._sended;
    }


    //setting Sended
    public void setSended(String Sended)
    {
   	 this._sended = Sended;
    }
    
    //////////////////////////////////////

  //getting LocalDateNumber
    public String getLocalDateNumber()
    {
   	 return this._localdate_number;
    }


    //setting LocalDateNumber
    public void setLocalDateNumber(String LocalDateNumber)
    {
   	 this._localdate_number = LocalDateNumber;
    }
    
    //////////////////////////////////////
    

}
