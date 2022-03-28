package com.farapaidar.firstmenu001;

public class Bultain {
	
	
	//private variables
		 private int _id;
		 private String _txt;
		 
		 
		 // Empty Constructor
		 public Bultain()
		 {
			 
		 }
		 
		 
		 public Bultain(int ID, String Txt)
		 {
			 this._id = ID;
			 this._txt = Txt;
		 }
		 
		 
		 /////////////////////////////////////
		 public int getId()
		 {
			 return this._id;
		 }
		 
		 public void setID(int ID)
		 {
			 this._id = ID;
		 }
		 
		 ////////////////////////////////////
		 
		 public String getTxt()
		 {
			 return this._txt;
		 }
		 
		 public void setTxt(String Txt)
		 {
			 this._txt = Txt;
		 }

}
