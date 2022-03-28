package com.farapaidar.fara003simpledb;


import java.util.ArrayList;
import java.util.List;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.*;

@SuppressLint("NewApi")
public class DatabaseHandler extends SQLiteOpenHelper {
	
	 // All Static variables
    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "SalesSystem.db";

    // Contacts table name
    private static final String TABLE_CONTACTS = "Persons";

    // Contacts Table Columns names
    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "name";
    private static final String KEY_REGION_NAME = "region_name";
    private static final String KEY_PH_NO_1 = "phone_number1";
    private static final String KEY_PH_NO_2 = "phone_number2";
    private static final String KEY_PH_NO_3 = "phone_number3";
    private static final String KEY_PH_NO_4 = "phone_number4";
    private static final String KEY_ADDRESS = "address";
    private static final String KEY_MOBILE = "mobile";
    private static final String KEY_FAX = "fax";
    private static final String KEY_DEPOSIT = "deposit";
    private static final String KEY_DETECTION = "detection";


    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_CONTACTS_TABLE = "CREATE TABLE IF NOT EXISTS " + TABLE_CONTACTS + "("
                + KEY_ID + " INTEGER PRIMARY KEY," + KEY_NAME + " TEXT,"
                + KEY_REGION_NAME + " TEXT," + KEY_PH_NO_1 + " TEXT," + KEY_PH_NO_2 + " TEXT," + KEY_PH_NO_3 + " TEXT," + KEY_PH_NO_4 + " TEXT," +
                KEY_ADDRESS + " TEXT," + KEY_MOBILE + " TEXT," + KEY_FAX + " TEXT," + KEY_DEPOSIT + " TEXT," + KEY_DETECTION + " TEXT" + ")";
        
        db.execSQL(CREATE_CONTACTS_TABLE);
    }

    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CONTACTS);

        // Create tables again
        onCreate(db);
    }

    /**
     * All CRUD(Create, Read, Update, Delete) Operations
     */

    // Adding new contact
    void addContact(Contact contact) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAME, contact.getName()); // Contact's Name
        values.put(KEY_REGION_NAME, contact.getRegionName()); // Contact's RegionName
        values.put(KEY_PH_NO_1, contact.getPhoneNumber1()); // Contact's PhoneNumber1
        values.put(KEY_PH_NO_2, contact.getPhoneNumber2()); // Contact's getPhoneNumber2
        values.put(KEY_PH_NO_3, contact.getPhoneNumber3()); // Contact's getPhoneNumber3
        values.put(KEY_PH_NO_4, contact.getPhoneNumber4()); // Contact's getPhoneNumber4
        values.put(KEY_ADDRESS, contact.getAddress()); // Contact's Address
        values.put(KEY_MOBILE, contact.getMobile()); // Contact's Mobile
        values.put(KEY_FAX, contact.getFax()); // Contact's Fax 
        values.put(KEY_DEPOSIT, contact.getDeposit()); // Contact's Deposit
        values.put(KEY_DETECTION, contact.getDetection()); // Contact's Detection
        
        // Inserting Row
        db.insert(TABLE_CONTACTS, null, values);
        db.close(); // Closing database connection
    }

    // Getting single contact
    Contact getContact(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_CONTACTS, new String[] { KEY_ID,
                KEY_NAME, KEY_REGION_NAME, KEY_PH_NO_1, KEY_PH_NO_2, KEY_PH_NO_3, KEY_PH_NO_4, KEY_ADDRESS, 
                KEY_MOBILE, KEY_FAX, KEY_DEPOSIT, KEY_DETECTION }, KEY_ID + "=?",
                new String[] { String.valueOf(id) }, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        Contact contact = new Contact(Integer.parseInt(cursor.getString(0)),
                cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4)
                , cursor.getString(5), cursor.getString(6), cursor.getString(7), cursor.getString(8)
                , cursor.getString(9), cursor.getString(10), cursor.getString(11));
        // return contact
        return contact;
    }

    // Getting All Contacts
    @SuppressLint("NewApi")
	public List<Contact> getAllContacts() {
        List<Contact> contactList = new ArrayList<Contact>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_CONTACTS;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Contact contact = new Contact();
                contact.setID(Integer.parseInt(cursor.getString(0)));
                contact.setName(cursor.getString(1));
                contact.setRegionName(cursor.getString(2));
                contact.setPhoneNumber1(cursor.getString(3));
                contact.setPhoneNumber2(cursor.getString(4));
                contact.setPhoneNumber3(cursor.getString(5));
                contact.setPhoneNumber4(cursor.getString(6));
                contact.setAddress(cursor.getString(7));
                contact.setMobile(cursor.getString(8));
                contact.setFax(cursor.getString(9));
                contact.setDeposit(cursor.getString(10));
                contact.setDetection(cursor.getString(11));
                
                
                

               ArrayList<String> ArrayofItems = new ArrayList<String>();
               
               if (!cursor.isNull(0)){
               ArrayofItems.add(0, cursor.getString(0));     // id
               }
               if (!cursor.isNull(1)){
               ArrayofItems.add(1,cursor.getString(1));     // name
               }
               if (!cursor.isNull(8)){
               ArrayofItems.add(2,cursor.getString(8));    // mobile
               }
               if (!cursor.isNull(10)){
               ArrayofItems.add(3,cursor.getString(10));  // deposit
               }
               if (!cursor.isNull(11)){
               ArrayofItems.add(4,cursor.getString(11)); //  detection
               }
                
                //String name = cursor.getString(0) +") "+ cursor.getString(1) +"\t"+ cursor.getString(8) +"\t"+ cursor.getString(10)+"\t"+ cursor.getString(11);
                FirstPage.ArrayofName.add(ArrayofItems);
                // Adding contact to list
                contactList.add(contact);
            } while (cursor.moveToNext());
        }

        // return contact list
        return contactList;
    }

   
    
    // Getting All Contacts
 public List<Contact> getSpecificContacts(String Name) {
    	
	 
	 
        List<Contact> contactList = new ArrayList<Contact>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_CONTACTS + "   WHERE  "+ KEY_NAME+ " LIKE '" + Name.toString()+ "%'";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        
        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
            	Contact contact = new Contact();
            	contact.setID(Integer.parseInt(cursor.getString(0)));
                contact.setName(cursor.getString(1));
                contact.setRegionName(cursor.getString(2));
                contact.setPhoneNumber1(cursor.getString(3));
                contact.setPhoneNumber2(cursor.getString(4));
                contact.setPhoneNumber3(cursor.getString(5));
                contact.setPhoneNumber4(cursor.getString(6));
                contact.setAddress(cursor.getString(7));
                contact.setMobile(cursor.getString(8));
                contact.setFax(cursor.getString(9));
                contact.setDeposit(cursor.getString(10));
                contact.setDetection(cursor.getString(11));

                
                
                String name = cursor.getString(0) +") "+ cursor.getString(1) +"\t"+ cursor.getString(8) +"\t"+ cursor.getString(10)+"\t"+ cursor.getString(11);
                FirstPage.ArrayofName2.add(name);
                
                
                // Adding contact to list
                contactList.add(contact);
            } while (cursor.moveToNext());
        }

        // return contact list
        return contactList;
        
        
    	
    	
    }

   
    // Updating single contact
    public int updateContact(Contact contact) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAME, contact.getName());
        values.put(KEY_REGION_NAME, contact.getRegionName()); // Contact's RegionName
        values.put(KEY_PH_NO_1, contact.getPhoneNumber1()); // Contact's PhoneNumber1
        values.put(KEY_PH_NO_2, contact.getPhoneNumber2()); // Contact's getPhoneNumber2
        values.put(KEY_PH_NO_3, contact.getPhoneNumber3()); // Contact's getPhoneNumber3
        values.put(KEY_PH_NO_4, contact.getPhoneNumber4()); // Contact's getPhoneNumber4
        values.put(KEY_ADDRESS, contact.getAddress()); // Contact's Address
        values.put(KEY_MOBILE, contact.getMobile()); // Contact's Mobile
        values.put(KEY_FAX, contact.getFax()); // Contact's Fax 
        values.put(KEY_DEPOSIT, contact.getDeposit()); // Contact's Deposit
        values.put(KEY_DETECTION, contact.getDetection()); // Contact's Detection

        // updating row
        return db.update(TABLE_CONTACTS, values, KEY_ID + " = ?",
                new String[] { String.valueOf(contact.getID()) });
    }

    // Deleting single contact
    public void deleteContact(Contact contact) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_CONTACTS, KEY_ID + " = ?",
                new String[] { String.valueOf(contact.getID()) });
        db.close();
    }


    // Getting contacts Count
    public int getContactsCount() {
        String countQuery = "SELECT  * FROM " + TABLE_CONTACTS;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        cursor.close();

        // return count
        return cursor.getCount();
    }

}
