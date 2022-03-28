package com.farapaidar.firstmenu001;

public class GoodsTemp {
	
	   //private variables
	private String _guid;
	private String _id;   
	private String _good_name;
	private String _stock;
	private String _count_unit;
	private String _price;
	private String _price_type;
	private String _q_box;
	private String _count;
    private String _itemtotal;
	  

	   // Empty constructor
	   public GoodsTemp(){

	   }
	   
	   /*
	// basic constructor with id
	   public Goods(int id, String GoodName, String phone_number1){
	       this._id = id;
	       this._good_name = GoodName;
	       this._phone_number1 = phone_number1;
	   }

	   // basic constructor without id
	   public Contact(String GoodName, String phone_number1){
	       this._good_name = GoodName;
	       this._phone_number1 = phone_number1;
	   }
	 
	   
	   */
	   
	   
	   // complete constructor with Guid
	   public GoodsTemp( String Guid, String id, String goodName, String stock,
			   String countUnit, String price, String priceType, 
			   String qBox, String count,String itemTotal){
	       
		   this._guid = Guid;
		   this._id = id;
	       this._good_name = goodName;
	       this._stock = stock;
	       this._count_unit = countUnit;
	       this._price = price;
	       this._price_type = priceType; 
	       this._q_box = qBox;
	       this._count = count;
	       this._itemtotal = itemTotal;
	   }

	   // complete constructor without Guid
	   public GoodsTemp(String id, String goodName, String stock, String countUnit,
			   String price, String priceType,
			   String qBox, String count, String itemTotal){
	   	
		   this._id = id;
		   this._good_name = goodName;   	
		   this._stock = stock;   	
		   this._count_unit = countUnit;    
		   this._price = price;    
		   this._price_type = priceType;
		   this._q_box = qBox;
		   this._count = count;
		   this._itemtotal = itemTotal;
	       
	   }
	   
//////////////////////////////////////////////////////////////////////////	      
	 //getting guid
	   public String getGuid(){
	   return this._guid;
	   }

	   //setting guid
	   public void setGuid(String guid){
	   this._guid = guid;
	   }

	   //////////////////////////////////////
	   
	//getting ID
	public String getID(){
	return this._id;
	}

	//setting id
	public void setID(String id){
	this._id = id;
	}
	//////////////////////////////////////
	//getting goodName
	public String getGoodName(){
	return this._good_name;
	}

	//setting goodName
	public void setGoodName(String goodName){
	this._good_name = goodName;
	}
	//////////////////////////////////////
	//getting stock
	public String getStock(){
	return this._stock;
	}

	//setting stock
	public void setStock(String stock){
	this._stock = stock;
	}

	/////////////////////////////////////// /////////////////////////////////////    

	//getting countUnit
	public String getCountUnit(){
	   return this._count_unit;
	}

	//setting countUnit
	public void setCountUnit(String countUnit){
	   this._count_unit = countUnit;
	}
	//////////////////////////////////////
	//getting Price
	public String getPrice(){
	   return this._price;
	}

	//setting Price
	public void setPrice(String price){
	   this._price = price;
	}

	/////////////////////////////////////// //////////////////////////////////////
	//getting priceType
	public String getPriceType(){
	return this._price_type;
	}

	//setting priceType
	public void setPriceType(String priceType){
	this._price_type = priceType;
	}
	
/////////////////////////////////////// //////////////////////////////////////

//getting qBox
public String getQBox()
{
return this._q_box;
}

//  setting qBox
public void setQBox(String QBox)
{
this._q_box = QBox;
}

/////////////////////////////////////// //////////////////////////////////////
	
	
	
//getting Count
public String getCount(){
return this._count;
}

//setting Count
public void setCount(String count){
this._count = count;
}

/////////////////////////////////////// //////////////////////////////////////
//getting ItemTotal
public String getItemTotal(){
return this._itemtotal;
}

//setting Count
public void setItemTotal(String ItemTotal){
this._itemtotal = ItemTotal;
}

/////////////////////////////////////// 
	   

	}

