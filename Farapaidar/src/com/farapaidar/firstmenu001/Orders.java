package com.farapaidar.firstmenu001;

public class Orders {
	
	 //private variables
	 private int _id;
	 private String _GuidRequest;
	 private String _GuidInvcRequest;
	 private String _type_invcid;
	 private String _guid_operator;	  
	 private String _guid_customer;
	 private String _name_customer;
	 private String _guid_prodscale;
	 private String _name_prodscale;
	 private String _quantity;		  
	 private String _fee;		  		  
	 private String _localdate;	  
	 private String _localtime;		  
	 private String _guid_reseler;
	 private String _sended;
	 private String _localdate_number;
	 private String _quantity_invc;
     
     
	// Empty constructor
     public Orders()
     {
    	 
     }
       
     
     public Orders(int ID, String GuidRequest, String GuidInvcRequest, String TypeInvcId,
    		 String GuidOperator, String GuidCustomer, String NameCustomer, String GuidProdScale,
    		 String NameProdScale,String Quantity, String Fee, String LocalDate, String LocalTime,
    		 String GuidReseler, String Sended, String LocalDateNumber, String QuantityInvc )
     {
    	 this._id = ID;
    	 this._GuidRequest = GuidRequest;
    	 this._GuidInvcRequest = GuidInvcRequest;
    	 this._type_invcid = TypeInvcId;
    	 this._guid_operator = 	GuidOperator;  
    	 this._guid_customer = 	GuidCustomer;
    	 this._name_customer = NameCustomer;
    	 this._guid_prodscale = 	GuidProdScale;	
    	 this._name_prodscale = NameProdScale;
    	 this._quantity = Quantity;		  
    	 this._fee = Fee;	  		  
    	 this._localdate = LocalDate;	  
    	 this._localtime = LocalTime;		      	 
    	 this._guid_reseler = GuidReseler;
    	 this._sended = Sended;
    	 this._localdate_number = LocalDateNumber;
    	 this._quantity_invc = QuantityInvc;
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
     
     //getting GuidRequest
     public String getGuidRequest()
     {
    	 return this._GuidRequest;
     }


     //setting GuidRequest
     public void setGuidRequest(String GuidRequest)
     {
    	 this._GuidRequest = GuidRequest;
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

     
     //getting GuidProdScale
     public String getGuidProdScale()
     {
    	 return this._guid_prodscale;
     }


     //setting GuidProdScale
     public void setGuidProdScale(String GuidProdScale)
     {
    	 this._guid_prodscale = GuidProdScale;
     }
     
     //////////////////////////////////////
     
     
   //getting NameProdScale
     public String getNameProdScale()
     {
    	 return this._name_prodscale;
     }


     //setting NameProdScale
     public void setNameProdScale(String NameProdScale)
     {
    	 this._name_prodscale = NameProdScale;
     }
     
     //////////////////////////////////////
     
     //getting Quantity
     public String getQuantity()
     {
    	 return this._quantity;
     }


     //setting Quantity
     public void setQuantity(String Quantity)
     {
    	 this._quantity = Quantity;
     }
     
     //////////////////////////////////////
     
     //getting Fee
     public String getFee()
     {
    	 return this._fee;
     }


     //setting Fee
     public void setFee(String Fee)
     {
    	 this._fee = Fee;
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

     
     //getting QuantityInvc
     public String getQuantityInvc()
     {
    	 return this._quantity_invc;
     }


     //setting QuantityInvc
     public void setQuantityInvc(String QuantityInvc)
     {
    	 this._quantity_invc = QuantityInvc;
     }
     

}
