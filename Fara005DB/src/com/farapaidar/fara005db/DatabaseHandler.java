package com.farapaidar.fara005db;

import java.util.ArrayList;
import java.util.List;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.farapaidar.fara005db.Goods;

public class DatabaseHandler  extends SQLiteOpenHelper {
	 // All Static variables
   // Database Version
   private static final int DATABASE_VERSION = 1;

   // Database Name
   private static final String DATABASE_NAME = "SalesSystem.db";

   // Contacts table name
   private static final String TABLE_GOODS = "Goods";

   // Contacts Table Columns names
   private static final String KEY_ID = "id";
   private static final String KEY_NAME = "name";
   private static final String KEY_COUNT_UNIT = "count_unit";
   private static final String KEY_STOCK = "stock";
   private static final String KEY_PRICE_TYPE = "price_type";
   

   public DatabaseHandler(Context context) {
       super(context, DATABASE_NAME, null, DATABASE_VERSION);
   }

   // Creating Tables
   @Override
   public void onCreate(SQLiteDatabase db) {
       String CREATE_GOODS_TABLE = "CREATE TABLE IF NOT EXISTS " + TABLE_GOODS + "("
               + KEY_ID + " INTEGER PRIMARY KEY," + KEY_NAME + " TEXT,"
               + KEY_COUNT_UNIT + " TEXT," + KEY_STOCK + " TEXT," + KEY_PRICE_TYPE + " TEXT" + ")";
       
       db.execSQL(CREATE_GOODS_TABLE);
   }

   // Upgrading database
   @Override
   public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
       // Drop older table if existed
       db.execSQL("DROP TABLE IF EXISTS " + TABLE_GOODS);

       // Create tables again
       onCreate(db);
   }

   /**
    * All CRUD(Create, Read, Update, Delete) Operations
    */

   // Adding new contact
   void addGoods(Goods goods) {
       SQLiteDatabase db = this.getWritableDatabase();

       ContentValues values = new ContentValues();
       values.put(KEY_NAME, goods.getGoodName()); // Good's Name
       values.put(KEY_COUNT_UNIT, goods.getCountUnit()); // Good's CountName
       values.put(KEY_STOCK, goods.getStock()); // Good's Stock
       values.put(KEY_PRICE_TYPE, goods.getPriceType()); // Good's PriceType
       
       // Inserting Row
       db.insert(TABLE_GOODS, null, values);
       db.close(); // Closing database connection
   }

   // Getting single contact
   Goods getGoods(int id) {
       SQLiteDatabase db = this.getReadableDatabase();

       Cursor cursor = db.query(TABLE_GOODS, new String[] { KEY_ID,
               KEY_NAME, KEY_COUNT_UNIT, KEY_STOCK, KEY_PRICE_TYPE }, KEY_ID + "=?",
               new String[] { String.valueOf(id) }, null, null, null, null);
       if (cursor != null)
           cursor.moveToFirst();

       Goods goods = new Goods(Integer.parseInt(cursor.getString(0)),
               cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4));
       // return contact
       return goods;
   }

   // Getting All Contacts
   @SuppressLint("NewApi")
	public List<Goods> getAllGoods() {
       List<Goods> goodsList = new ArrayList<Goods>();
       // Select All Query
       String selectQuery = "SELECT  * FROM " + TABLE_GOODS;

       SQLiteDatabase db = this.getWritableDatabase();
       Cursor cursor = db.rawQuery(selectQuery, null);

       // looping through all rows and adding to list
       if (cursor.moveToFirst()) {
           do {
               Goods goods = new Goods();
               goods.setID(Integer.parseInt(cursor.getString(0)));
               goods.setGoodName(cursor.getString(1));
               goods.setCountUnit(cursor.getString(2));
               goods.setStock(cursor.getString(3));
               goods.setPriceType(cursor.getString(4));   

               // Adding contact to list
               goodsList.add(goods);
           } while (cursor.moveToNext());
       }

       // return contact list
       return goodsList;
   }

  
   
   // Getting All Contacts
public List<Goods> getSpecificGood(String GoodName) {
   	
	 
	 
       List<Goods> goodsList = new ArrayList<Goods>();
       // Select All Query
       String selectQuery = "SELECT  * FROM " + TABLE_GOODS + "   WHERE  "+ KEY_NAME+ " LIKE '" + GoodName.toString()+ "%'";

       SQLiteDatabase db = this.getWritableDatabase();
       Cursor cursor = db.rawQuery(selectQuery, null);

       
       // looping through all rows and adding to list
       if (cursor.moveToFirst()) {
           do {
           	   Goods goods = new Goods();
                  goods.setID(Integer.parseInt(cursor.getString(0)));
                  goods.setGoodName(cursor.getString(1));
                  goods.setCountUnit(cursor.getString(2));
                  goods.setStock(cursor.getString(3));
                  goods.setPriceType(cursor.getString(4));   

                  // Adding contact to list
                  goodsList.add(goods);
              } while (cursor.moveToNext());
          }

          // return contact list
          return goodsList;
      }


  
   // Updating single contact
   public int updateGoods(Goods goods) {
       SQLiteDatabase db = this.getWritableDatabase();

       ContentValues values = new ContentValues();
       values.put(KEY_NAME, goods.getGoodName()); // Good's Name
       values.put(KEY_COUNT_UNIT, goods.getCountUnit()); // Good's CountName
       values.put(KEY_STOCK, goods.getStock()); // Good's Stock
       values.put(KEY_PRICE_TYPE, goods.getPriceType()); // Good's PriceType


       // updating row
       return db.update(TABLE_GOODS, values, KEY_ID + " = ?",
               new String[] { String.valueOf(goods.getID()) });
   }
   
   

   // Deleting single contact
   public void deleteGoods(Goods goods) {
       SQLiteDatabase db = this.getWritableDatabase();
       db.delete(TABLE_GOODS, KEY_ID + " = ?",
               new String[] { String.valueOf(goods.getID()) });
       db.close();
   }


   // Getting contacts Count
   public int getGoodsCount() {
       String countQuery = "SELECT  * FROM " + TABLE_GOODS;
       SQLiteDatabase db = this.getReadableDatabase();
       Cursor cursor = db.rawQuery(countQuery, null);
       cursor.close();

       // return count
       return cursor.getCount();
   }

}
