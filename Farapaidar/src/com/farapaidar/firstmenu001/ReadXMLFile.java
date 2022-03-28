package com.farapaidar.firstmenu001;


import java.io.File;


import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import android.annotation.SuppressLint;

@SuppressLint("SdCardPath")
public class ReadXMLFile {
	
	//private variables
	
	private String _path = null;
	private String _filename = null;

	
	// empty Constructor
	public ReadXMLFile()
	{
		
	}
	
	public ReadXMLFile(String FileName)
	{
		this._path = "/sdcard/Farapaidar/";
		this._filename = FileName;
		
	}
	
	
	public ReadXMLFile(String Path,String FileName)
	{
		this._path = Path;
		this._filename = FileName;
		
	}
	
//////////////////////////////////////////////////////
	
	public String getPath()
	{
		return this._path;	
	}
	
	public void setPath(String Path)
	{
		this._path = Path;
	}
	
	
	public String getFileName()
	{
		return this._filename;	
	}
	
	public void setFileName(String FileName)
	{
		this._filename = FileName;
	}
	
//////////////////////////////////////////////////////
	
	public File ReadFile()
	{		
		File file = null;

	    try 
	    {	    	
	    	file = new File(this._path,this._filename);	    	   
	    }
	    catch (Exception e) 
	    {
	        e.printStackTrace();
	        System.out.println("فایل مربوط به جدول اسامی افراد در مسیر موجود نیست");
	        return null;
	    }
	    
	    return file;
	}
	
	public NodeList ParseFile(File file,String TagName)
	{	 
	    //Parsing Readed .XML File
	    NodeList nList = null;
	    
	        try 
	        {

	        	DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
				DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();

				Document doc = dBuilder.parse(file);
				Element element=doc.getDocumentElement();
				element.normalize();

				nList = doc.getElementsByTagName(TagName);	
	        } 
	        catch (Exception e) 
	        {
	        	e.printStackTrace();
	        	return null;
			}
  
	    return nList;
	}

}


