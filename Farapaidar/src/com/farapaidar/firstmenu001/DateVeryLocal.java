package com.farapaidar.firstmenu001;

public class DateVeryLocal {
	
	
	//private variables
	  private String _jalali_date;
	  private String _grigorian_date;
	 

	   
	    public DateVeryLocal(){

	    }

	    

	    public DateVeryLocal(String JalaliDate, String GrigorianDate){
	    	
	    	this._jalali_date = JalaliDate;
	    	this._grigorian_date = GrigorianDate;
	    }
	    
	    
	    // getting JalaliDate
	    public String getJalaliDate()
	    {
	    	return this._jalali_date;
	    }
	    // set JalaliDate
	    public void setJalaliDate(String JalaliDate)
	    {
	    	this._jalali_date = JalaliDate;
	    }
	    
	    
	    

	    // getting GrigorianDate
	    public String getGrigorianDate()
	    {
	    	return this._grigorian_date;
	    }
	    // set GrigorianDate
	    public void setGrigorianDate(String GrigorianDate)
	    {
	    	this._grigorian_date = GrigorianDate;
	    }
}
