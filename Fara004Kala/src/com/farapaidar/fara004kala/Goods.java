package com.farapaidar.fara004kala;

public class Goods {
	
	
	 //private variables
    int _id;
    String _good_name;
    String _count_unit;
    String _stock;
    String _price_type;
   

    // Empty constructor
    public Goods(){

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
    
    
    // complete constructor with id
    public Goods(int id, String goodName, String countUnit, String stock, String priceType){
        this._id = id;
        this._good_name = goodName;
        this._count_unit = countUnit;
        this._stock = stock;
        this._price_type = priceType;         
    }

    // complete constructor without id
    public Goods(String goodName, String countUnit, String stock, String priceType){
    	this._good_name = goodName;
    	 this._count_unit = countUnit;
         this._stock = stock;
         this._price_type = priceType;  
        
    }
    
//////////////////////////////////////////////////////////////////////////    
    
// getting ID
public int getID(){
return this._id;
}

// setting id
public void setID(int id){
this._id = id;
}
//////////////////////////////////////
// getting goodName
public String getGoodName(){
return this._good_name;
}

// setting goodName
public void setGoodName(String goodName){
this._good_name = goodName;
}

/////////////////////////////////////// /////////////////////////////////////    

// getting countUnit
public String getCountUnit(){
    return this._count_unit;
}

// setting countUnit
public void setCountUnit(String countUnit){
    this._count_unit = countUnit;
}
//////////////////////////////////////
// getting stock
public String getStock(){
    return this._stock;
}

// setting stock
public void setStock(String stock){
    this._stock = stock;
}

/////////////////////////////////////// //////////////////////////////////////
//getting stock
public String getPriceType(){
return this._price_type;
}

//setting stock
public void setPriceType(String priceType){
this._price_type = priceType;
}

/////////////////////////////////////// 
    

}
