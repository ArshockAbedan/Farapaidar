package com.farapaidar.firstmenu001;

import java.util.ArrayList;
import java.util.List;
import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHandler extends SQLiteOpenHelper {

	// All Static variables
	// Database Version
	private static final int DATABASE_VERSION = 2;

	// Database Name
	private static final String DATABASE_NAME = "FPDB-Lite.db";

	// /////////////////////////////////////////////////////////////////////////////////////

	// Bultain table name
	private static final String TABLE_BULTAIN = "Bultain";

	// Contacts table name
	private static final String TABLE_CONTACTS = "Customers";

	// Goods table name
	private static final String TABLE_GOODS = "Products";

	// ContactsTemp table name
	private static final String TABLE_CONTACTS_TEMP = "CustomersTemp";

	// goodsTemp table name
	private static final String TABLE_GOODS_TEMP = "ProductsTemp";

	// Orders table name
	private static final String TABLE_ORDERS = "Orders";

	// Factors table name
	private static final String TABLE_FACTORS = "Factors";

	// Operator table name
	private static final String TABLE_OPERATOR = "Operator";

	// DBConnection table name
	private static final String TABLE_DBCONNECTION = "DBConnection";

	// Task table name
	private static final String TABLE_TASK = "Task";

	// Inbox table name
	private static final String TABLE_INBOX = "Inbox";

	// InboxReport table name
	// private static final String TABLE_INBOX_REPORT = "InboxReport";

	// TaskType table name
	private static final String TABLE_TASK_TYPE = "TaskType";

	// PriorityType table name
	private static final String TABLE_PRIORITY_TYPE = "PriorityType";

	// PriceList table name
	private static final String TABLE_PRICELIST = "PriceList";

	// /////////////////////////////////////////////////////////////////////////////////////

	// Common Table Columns names
	private static final String KEY_GUID = "guid";
	private static final String KEY_ID = "id";
	private static final String KEY_NAME = "name";

	// Bultain Table Columns names
	private static final String KEY_TXT = "txt";

	// Contacts Table Columns names
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
	private static final String KEY_GUID_VISITOR = "guid_visitor";
	private static final String KEY_GUID_CUSTOMER_CATEGORY = "guidCustomer_category";

	// Goods Table Columns names
	private static final String KEY_STOCK = "stock";
	private static final String KEY_COUNT_UNIT = "count_unit";
	private static final String KEY_PRICE = "price";
	private static final String KEY_PRICE_TYPE = "price_type";
	private static final String KEY_Q_BOX = "q_box";

	// GoodsTemp Table Extra Columns names
	private static final String KEY_COUNT = "count";
	private static final String KEY_ITEM_TOTAL = "itemtotal";

	// Orders Table Columns names
	private static final String KEY_GUID_REQ = "GuidRequest";
	private static final String KEY_GUID_INVC_REQ = "GuidInvcRequest";
	private static final String KEY_TYPE_INVC_ID = "type_invcid";
	private static final String KEY_GUID_OPERATOR = "guid_operator";
	private static final String KEY_GUID_CUSTOMER = "guid_customer";
	private static final String KEY_NAME_CUSTOMER = "name_customer";
	private static final String KEY_GUID_PROD_SCALE = "guid_prodscale";
	private static final String KEY_NAME_PROD_SCALE = "name_prodscale";
	private static final String KEY_QUANTITY = "quantity";
	private static final String KEY_FEE = "fee";
	private static final String KEY_DATE = "localdate";
	private static final String KEY_TIME = "localtime";
	private static final String KEY_GUID_RESELER = "guid_reseler";
	private static final String KEY_SENDED = "sended";
	private static final String KEY_DATE_NUMBER = "localdate_number";
	private static final String KEY_QUANTITY_INVC = "quantity_invc";

	// Factors Table Columns names
	private static final String KEY_TYPE_INVC_NAME = "type_invc_name";
	private static final String KEY_SYSTEM_ID = "system_id";
	private static final String KEY_GUID_BRANCHE = "guid_branche";
	private static final String KEY_FACTOR_NOTE = "factor_note";

	// Operator Table Columns names
	private static final String KEY_FULL_NAME = "full_name";
	private static final String KEY_USER_NAME = "username";
	private static final String KEY_PASSWORD = "password";
	private static final String KEY_RESELER = "guid_reseler";
	private static final String KEY_BRANCHE = "guid_branche";

	// DBConnection Table Columns names
	private static final String KEY_DataBaseMAIN = "database_main";
	private static final String KEY_DataBaseANDROID = "database_android";
	private static final String KEY_IP_LOCAL = "ip_local";
	private static final String KEY_IP_REMOTE = "ip_remote";

	// Task Table Columns names
	private static final String KEY_TASK_ID = "id";
	private static final String KEY_TASK_GUID = "guid";
	private static final String KEY_TASK_TITLE = "title";
	private static final String KEY_TASK_NOTE = "note";
	private static final String KEY_TASK_TYPE_TASK_ID = "type_task_id";
	private static final String KEY_TASK_TYPE_TASK_NAME = "type_task_name";
	private static final String KEY_TASK_TYPE_PRIORITY_ID = "type_priority_id";
	private static final String KEY_TASK_TYPE_PRIORITY_NAME = "type_priority_name";
	private static final String KEY_TASK_CLOSED = "closed";
	private static final String KEY_TASK_COMMENT = "comment";

	// Inbox Table Columns names
	private static final String KEY_INBOX_GUID = "guid";
	private static final String KEY_INBOX_ID = "id";
	private static final String KEY_INBOX_GUID_TASK = "task_guid";
	private static final String KEY_INBOX_TITLE_TASK = "task_title";
	private static final String KEY_INBOX_GUID_OPERATOR = "operator_guid";
	private static final String KEY_INBOX_NAME_OPERATORE = "operator_name";
	private static final String KEY_INBOX_DATE = "local_date";
	private static final String KEY_INBOX_TIME = "local_time";
	private static final String KEY_INBOX_VISITED = "visited";
	private static final String KEY_INBOX_REFERED = "refered";

	// PriceList Table Columns names
	private static final String KEY_PRICELIST_ID = "id";
	private static final String KEY_PRICELIST_GUID_CUSTOMER_CATEGORY = "guidCustomer_category";
	private static final String KEY_PRICELIST_GUID_PROD_SCALE = "guid_prod_scale";
	private static final String KEY_PRICELIST_GUID_SCALE = "guid_scale";
	private static final String KEY_PRICELIST_NAME_PUBLIC = "name_public";
	private static final String KEY_PRICELIST_TYPE_PRICE_ID = "type_price_id";
	private static final String KEY_PRICELIST_CUSTOMER_CATEGORY = "Customer_category";
	private static final String KEY_PRICELIST_TYPE_PRICE_TITLE = "type_price_title";
	private static final String KEY_PRICELIST_LOCAL_DATE = "type_local_date";
	private static final String KEY_PRICELIST_TYPE_REGISTRATION_ID = "type_registration_id";
	private static final String KEY_PRICELIST_FEE = "fee";

	// ///////////////////////////////////////////////////////////////////////////////////////////////////////

	// Table Create Statements

	// Bultain table create statement
	String CREATE_BULTAIN_TABLE = "CREATE TABLE IF NOT EXISTS " + TABLE_BULTAIN
			+ "(" + KEY_ID + " INTEGER PRIMARY KEY, " + KEY_TXT + " TEXT" + ")";

	// Contacts table create statement
	String CREATE_CONTACTS_TABLE = "CREATE TABLE IF NOT EXISTS "
			+ TABLE_CONTACTS + "(" + KEY_GUID + " TEXT PRIMARY KEY," + KEY_ID
			+ " TEXT," + KEY_NAME + " TEXT," + KEY_REGION_NAME + " TEXT,"
			+ KEY_PH_NO_1 + " TEXT," + KEY_PH_NO_2 + " TEXT," + KEY_PH_NO_3
			+ " TEXT," + KEY_PH_NO_4 + " TEXT," + KEY_ADDRESS + " TEXT,"
			+ KEY_MOBILE + " TEXT," + KEY_FAX + " TEXT," + KEY_DEPOSIT
			+ " TEXT," + KEY_DETECTION + " TEXT," + KEY_GUID_VISITOR + " TEXT,"
			+ KEY_GUID_CUSTOMER_CATEGORY + " TEXT" + ")";

	// Goods table create statement
	String CREATE_GOODS_TABLE = "CREATE TABLE IF NOT EXISTS " + TABLE_GOODS
			+ "(" + KEY_GUID + " TEXT PRIMARY KEY," + KEY_ID + " TEXT,"
			+ KEY_NAME + " TEXT," + KEY_STOCK + " TEXT," + KEY_COUNT_UNIT
			+ " TEXT," + KEY_PRICE + " TEXT," + KEY_PRICE_TYPE + " TEXT,"
			+ KEY_Q_BOX + " TEXT" + ")";

	// ContactsTemp table create statement
	String CREATE_CONTACTSTEMP_TABLE = "CREATE TABLE IF NOT EXISTS "
			+ TABLE_CONTACTS_TEMP + "(" + KEY_GUID + " TEXT PRIMARY KEY,"
			+ KEY_ID + " TEXT," + KEY_NAME + " TEXT," + KEY_REGION_NAME
			+ " TEXT," + KEY_PH_NO_1 + " TEXT," + KEY_PH_NO_2 + " TEXT,"
			+ KEY_PH_NO_3 + " TEXT," + KEY_PH_NO_4 + " TEXT," + KEY_ADDRESS
			+ " TEXT," + KEY_MOBILE + " TEXT," + KEY_FAX + " TEXT,"
			+ KEY_DEPOSIT + " TEXT," + KEY_DETECTION + " TEXT,"
			+ KEY_GUID_VISITOR + " TEXT," + KEY_GUID_CUSTOMER_CATEGORY
			+ " TEXT" + ")";

	// GoodsTemp table create statement
	String CREATE_GOODSTEMP_TABLE = "CREATE TABLE IF NOT EXISTS "
			+ TABLE_GOODS_TEMP + "(" + KEY_GUID + " TEXT PRIMARY KEY," + KEY_ID
			+ " TEXT," + KEY_NAME + " TEXT," + KEY_STOCK + " TEXT,"
			+ KEY_COUNT_UNIT + " TEXT," + KEY_PRICE + " TEXT," + KEY_PRICE_TYPE
			+ " TEXT," + KEY_Q_BOX + " TEXT," + KEY_COUNT + " TEXT,"
			+ KEY_ITEM_TOTAL + " TEXT" + ")";

	// Factors table create statement
	String CREATE_FACTORS_TABLE = "CREATE TABLE IF NOT EXISTS " + TABLE_FACTORS
			+ "(" + KEY_ID + " INTEGER PRIMARY KEY, " + KEY_GUID_INVC_REQ
			+ " TEXT," + KEY_TYPE_INVC_ID + " TEXT," + KEY_TYPE_INVC_NAME
			+ " TEXT," + KEY_GUID_OPERATOR + " TEXT," + KEY_GUID_CUSTOMER
			+ " TEXT," + KEY_NAME_CUSTOMER + " TEXT," + KEY_DATE + " TEXT,"
			+ KEY_TIME + " TEXT," + KEY_SYSTEM_ID + " TEXT, "
			+ KEY_GUID_RESELER + " TEXT, " + KEY_GUID_BRANCHE + " TEXT, "
			+ KEY_FACTOR_NOTE + " TEXT, " + KEY_SENDED + " TEXT,"
			+ KEY_DATE_NUMBER + " INTEGER" + ")";

	// Orders table create statement
	String CREATE_ORDERS_TABLE = "CREATE TABLE IF NOT EXISTS " + TABLE_ORDERS
			+ "(" + KEY_ID + " INTEGER PRIMARY KEY, " + KEY_GUID_REQ + " TEXT,"
			+ KEY_GUID_INVC_REQ + " TEXT," + KEY_TYPE_INVC_ID + " TEXT,"
			+ KEY_GUID_OPERATOR + " TEXT," + KEY_GUID_CUSTOMER + " TEXT,"
			+ KEY_NAME_CUSTOMER + " TEXT," + KEY_GUID_PROD_SCALE + " TEXT,"
			+ KEY_NAME_PROD_SCALE + " TEXT," + KEY_QUANTITY + " TEXT,"
			+ KEY_FEE + " TEXT," + KEY_DATE + " TEXT," + KEY_TIME + " TEXT,"
			+ KEY_GUID_RESELER + " TEXT, " + KEY_SENDED + " TEXT,"
			+ KEY_DATE_NUMBER + " INTEGER," +  KEY_QUANTITY_INVC + " TEXT " + ")";

	// Operator table create statement
	String CREATE_OPERATOR_TABLE = "CREATE TABLE IF NOT EXISTS "
			+ TABLE_OPERATOR + "(" + KEY_GUID + " TEXT PRIMARY KEY,"
			+ KEY_FULL_NAME + " TEXT," + KEY_USER_NAME + " TEXT,"
			+ KEY_PASSWORD + " TEXT," + KEY_RESELER + " TEXT," + KEY_BRANCHE
			+ " TEXT" + ")";

	// DBConnection table create statement

	String CREATE_DBCONNECTION_TABLE = "CREATE TABLE IF NOT EXISTS "
			+ TABLE_DBCONNECTION + "(" + KEY_ID + " TEXT PRIMARY KEY,"
			+ KEY_DataBaseMAIN + " TEXT," + KEY_DataBaseANDROID + " TEXT,"
			+ KEY_USER_NAME + " TEXT," + KEY_PASSWORD + " TEXT," + KEY_IP_LOCAL
			+ " TEXT," + KEY_IP_REMOTE + " TEXT" + ")";

	// Task table create statement
	String CREATE_TASK_TABLE = " CREATE TABLE IF NOT EXISTS " + TABLE_TASK
			+ "(" + KEY_TASK_ID + " INTEGER PRIMARY KEY, " + KEY_TASK_GUID
			+ " TEXT," + KEY_TASK_TITLE + " TEXT," + KEY_TASK_NOTE + " TEXT,"
			+ KEY_TASK_TYPE_TASK_ID + " TEXT," + KEY_TASK_TYPE_TASK_NAME
			+ " TEXT," + KEY_TASK_TYPE_PRIORITY_ID + " TEXT,"
			+ KEY_TASK_TYPE_PRIORITY_NAME + " TEXT," + KEY_TASK_CLOSED
			+ " TEXT," + KEY_TASK_COMMENT + " TEXT" + ")";

	// Inbox table create statement
	String CREATE_INBOX_TABLE = " CREATE TABLE IF NOT EXISTS " + TABLE_INBOX
			+ "(" + KEY_INBOX_ID + " INTEGER PRIMARY KEY, " + KEY_INBOX_GUID
			+ " TEXT," + KEY_INBOX_GUID_TASK + " TEXT," + KEY_INBOX_TITLE_TASK
			+ " TEXT," + KEY_INBOX_GUID_OPERATOR + " TEXT,"
			+ KEY_INBOX_NAME_OPERATORE + " TEXT," + KEY_INBOX_DATE + " TEXT,"
			+ KEY_INBOX_TIME + " TEXT," + KEY_INBOX_VISITED + " TEXT,"
			+ KEY_INBOX_REFERED + " TEXT" + ")";

	// TaskType table create statement
	String CREATE_TASKTYPE_TABLE = " CREATE TABLE IF NOT EXISTS "
			+ TABLE_TASK_TYPE + "(" + KEY_ID + " TEXT PRIMARY KEY," + KEY_NAME
			+ " TEXT" + ")";

	// PriorityType table create statement
	String CREATE_PRIORITYTYPE_TABLE = " CREATE TABLE IF NOT EXISTS "
			+ TABLE_PRIORITY_TYPE + "(" + KEY_ID + " TEXT PRIMARY KEY,"
			+ KEY_NAME + " TEXT" + ")";

	// PriceList table create statement
	String CREATE_PRICELIST_TABLE = "CREATE TABLE IF NOT EXISTS "
			+ TABLE_PRICELIST + "(" + KEY_PRICELIST_ID + " TEXT PRIMARY KEY, "
			+ KEY_PRICELIST_GUID_CUSTOMER_CATEGORY + " TEXT, "
			+ KEY_PRICELIST_GUID_PROD_SCALE + " TEXT, "
			+ KEY_PRICELIST_GUID_SCALE + " TEXT, " + KEY_PRICELIST_NAME_PUBLIC
			+ " TEXT, " + KEY_PRICELIST_TYPE_PRICE_ID + " TEXT, "
			+ KEY_PRICELIST_CUSTOMER_CATEGORY + " TEXT, "
			+ KEY_PRICELIST_TYPE_PRICE_TITLE + " TEXT, "
			+ KEY_PRICELIST_LOCAL_DATE + " TEXT, "
			+ KEY_PRICELIST_TYPE_REGISTRATION_ID + " TEXT, "
			+ KEY_PRICELIST_FEE + " TEXT " + ")";

	// ///////////////////////////////////////////////////////////////////////////////////////////////////////////

	public DatabaseHandler(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	// creating required tables
	@Override
	public void onCreate(SQLiteDatabase db) {

		db.execSQL(CREATE_BULTAIN_TABLE);
		db.execSQL(CREATE_CONTACTS_TABLE);
		db.execSQL(CREATE_GOODS_TABLE);
		db.execSQL(CREATE_CONTACTSTEMP_TABLE);
		db.execSQL(CREATE_GOODSTEMP_TABLE);
		db.execSQL(CREATE_FACTORS_TABLE);
		db.execSQL(CREATE_ORDERS_TABLE);
		db.execSQL(CREATE_OPERATOR_TABLE);
		db.execSQL(CREATE_DBCONNECTION_TABLE);
		db.execSQL(CREATE_TASK_TABLE);
		db.execSQL(CREATE_INBOX_TABLE);
		db.execSQL(CREATE_TASKTYPE_TABLE);
		db.execSQL(CREATE_PRIORITYTYPE_TABLE);
		db.execSQL(CREATE_PRICELIST_TABLE);
	}

	// Upgrading database
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// Drop older table if existed

		if (oldVersion != newVersion) {
			db.execSQL("DROP TABLE IF EXISTS " + TABLE_BULTAIN);
			db.execSQL("DROP TABLE IF EXISTS " + TABLE_CONTACTS);
			db.execSQL("DROP TABLE IF EXISTS " + TABLE_GOODS);
			db.execSQL("DROP TABLE IF EXISTS " + TABLE_CONTACTS_TEMP);
			db.execSQL("DROP TABLE IF EXISTS " + TABLE_GOODS_TEMP);
			db.execSQL("DROP TABLE IF EXISTS " + TABLE_FACTORS);
			db.execSQL("DROP TABLE IF EXISTS " + TABLE_ORDERS);
			db.execSQL("DROP TABLE IF EXISTS " + TABLE_OPERATOR);
			db.execSQL("DROP TABLE IF EXISTS " + TABLE_DBCONNECTION);
			db.execSQL("DROP TABLE IF EXISTS " + TABLE_TASK);
			db.execSQL("DROP TABLE IF EXISTS " + TABLE_INBOX);
			db.execSQL("DROP TABLE IF EXISTS " + TABLE_TASK_TYPE);
			db.execSQL("DROP TABLE IF EXISTS " + TABLE_PRIORITY_TYPE);
			db.execSQL("DROP TABLE IF EXISTS " + TABLE_PRICELIST);

			// Create tables again
			onCreate(db);

		}
	}

	/**
	 * All CRUD(Create, Read, Update, Delete) Operations
	 */

	// ------------------------ "Bultain" table methods ----------------//

	// Adding new Bultain
	void addBultain(Bultain bultain) {
		SQLiteDatabase db = this.getWritableDatabase();

		ContentValues values = new ContentValues();
		// values.put(KEY_ID, null); // bultain's ID
		values.put(KEY_TXT, bultain.getTxt()); // bultain's Txt

		// Inserting Row
		db.insert(TABLE_BULTAIN, null, values);
		db.close(); // Closing database connection
	}

	// Getting All unsended Factors
	@SuppressLint("NewApi")
	public List<Bultain> getAllBultain() {
		List<Bultain> BultainList = new ArrayList<Bultain>();
		// Select All Query
		String selectQuery = "SELECT  * FROM " + TABLE_BULTAIN;

		SQLiteDatabase db = this.getWritableDatabase();
		Cursor cursor = db.rawQuery(selectQuery, null);

		Object C = cursor.getCount();
		if (Integer.parseInt(C.toString()) == 0) {
			return null;
		}

		// looping through all rows and adding to list
		if (cursor.moveToFirst()) {
			do {
				Bultain bultain = new Bultain();

				bultain.setTxt(cursor.getString(1)); // Bultain's Txt

				// Adding Bultain to list
				BultainList.add(bultain);
			} while (cursor.moveToNext());
		}

		// return Bultain list
		return BultainList;
	}

	// Deleting All items of Bultain table
	public void deleteAllBultain() {
		SQLiteDatabase db = this.getWritableDatabase();
		db.delete(TABLE_BULTAIN, null, null);
		db.close();
	}

	// ------------------------ "Contact" table methods ----------------//

	// Adding new contact
	void addContact(Contact contact) {
		SQLiteDatabase db = this.getWritableDatabase();

		ContentValues values = new ContentValues();
		values.put(KEY_GUID, contact.getGuid()); // Contact's Guid
		values.put(KEY_ID, contact.getID()); // Contact's ID
		values.put(KEY_NAME, contact.getName()); // Contact's Name
		values.put(KEY_REGION_NAME, contact.getRegionName()); // Contact's
																// RegionName
		values.put(KEY_PH_NO_1, contact.getPhoneNumber1()); // Contact's
															// PhoneNumber1
		values.put(KEY_PH_NO_2, contact.getPhoneNumber2()); // Contact's
															// getPhoneNumber2
		values.put(KEY_PH_NO_3, contact.getPhoneNumber3()); // Contact's
															// getPhoneNumber3
		values.put(KEY_PH_NO_4, contact.getPhoneNumber4()); // Contact's
															// getPhoneNumber4
		values.put(KEY_ADDRESS, contact.getAddress()); // Contact's Address
		values.put(KEY_MOBILE, contact.getMobile()); // Contact's Mobile
		values.put(KEY_FAX, contact.getFax()); // Contact's Fax
		values.put(KEY_DEPOSIT, contact.getDeposit()); // Contact's Deposit
		values.put(KEY_DETECTION, contact.getDetection()); // Contact's
															// Detection
		values.put(KEY_GUID_VISITOR, contact.getGuidVisitor()); // Contact's
																// GuidVisitor
		values.put(KEY_GUID_CUSTOMER_CATEGORY,
				contact.getGuidCustomerCategory());

		// Inserting Row
		db.insert(TABLE_CONTACTS, null, values);
		db.close(); // Closing database connection
	}

	// Getting single contact By Id
	Contact getContactById(String Id) {
		SQLiteDatabase db = this.getReadableDatabase();

		Cursor cursor = db.query(TABLE_CONTACTS, new String[] { KEY_GUID,
				KEY_ID, KEY_NAME, KEY_REGION_NAME, KEY_PH_NO_1, KEY_PH_NO_2,
				KEY_PH_NO_3, KEY_PH_NO_4, KEY_ADDRESS, KEY_MOBILE, KEY_FAX,
				KEY_DEPOSIT, KEY_DETECTION, KEY_GUID_VISITOR,
				KEY_GUID_CUSTOMER_CATEGORY }, KEY_ID + "=?",
				new String[] { Id }, null, null, null, null);
		if (cursor != null)
			cursor.moveToFirst();

		Contact contact = new Contact(cursor.getString(0), cursor.getString(1),
				cursor.getString(2), cursor.getString(3), cursor.getString(4),
				cursor.getString(5), cursor.getString(6), cursor.getString(7),
				cursor.getString(8), cursor.getString(9), cursor.getString(10),
				cursor.getString(11), cursor.getString(12),
				cursor.getString(13), cursor.getString(14));

		db.close(); // Closing database connection

		// return contact
		return contact;
	}

	// Getting single contact By Guid
	Contact getContactByGuid(String Guid) {
		SQLiteDatabase db = this.getReadableDatabase();

		Cursor cursor = db.query(TABLE_CONTACTS, new String[] { KEY_GUID,
				KEY_ID, KEY_NAME, KEY_REGION_NAME, KEY_PH_NO_1, KEY_PH_NO_2,
				KEY_PH_NO_3, KEY_PH_NO_4, KEY_ADDRESS, KEY_MOBILE, KEY_FAX,
				KEY_DEPOSIT, KEY_DETECTION, KEY_GUID_VISITOR,
				KEY_GUID_CUSTOMER_CATEGORY }, KEY_GUID + "=?",
				new String[] { Guid }, null, null, null, null);
		if (cursor != null)
			cursor.moveToFirst();

		Contact contact = new Contact(cursor.getString(0), cursor.getString(1),
				cursor.getString(2), cursor.getString(3), cursor.getString(4),
				cursor.getString(5), cursor.getString(6), cursor.getString(7),
				cursor.getString(8), cursor.getString(9), cursor.getString(10),
				cursor.getString(11), cursor.getString(12),
				cursor.getString(13), cursor.getString(14));

		db.close(); // Closing database connection

		// return contact
		return contact;
	}

	// Getting All Contacts
	@SuppressLint("NewApi")
	public List<Contact> getAllContacts(String GuidVisitor) {
		List<Contact> contactList = new ArrayList<Contact>();
		// Select All Query
		String selectQuery = "SELECT  * FROM " + TABLE_CONTACTS + " WHERE "
				+ KEY_GUID_VISITOR + " = '" + GuidVisitor + "' ORDER BY "
				+ KEY_NAME;

		SQLiteDatabase db = this.getWritableDatabase();
		Cursor cursor = db.rawQuery(selectQuery, null);

		// looping through all rows and adding to list
		if (cursor.moveToFirst()) {
			do {
				Contact contact = new Contact();
				contact.setGuid(cursor.getString(0));
				contact.setID(cursor.getString(1));
				contact.setName(cursor.getString(2));
				contact.setRegionName(cursor.getString(3));
				contact.setPhoneNumber1(cursor.getString(4));
				contact.setPhoneNumber2(cursor.getString(5));
				contact.setPhoneNumber3(cursor.getString(6));
				contact.setPhoneNumber4(cursor.getString(7));
				contact.setAddress(cursor.getString(8));
				contact.setMobile(cursor.getString(9));
				contact.setFax(cursor.getString(10));
				contact.setDeposit(cursor.getString(11));
				contact.setDetection(cursor.getString(12));
				contact.setGuidVisitor(cursor.getString(13));
				contact.setGuidCustomerCategory(cursor.getString(14));

				ArrayList<String> ArrayofItems = new ArrayList<String>();

				if (!cursor.isNull(1)) {
					ArrayofItems.add(0, cursor.getString(1)); // id
				}
				if (!cursor.isNull(2)) {
					ArrayofItems.add(1, cursor.getString(2)); // name
				}
				if (!cursor.isNull(9)) {
					ArrayofItems.add(2, cursor.getString(9)); // mobile
				}
				if (!cursor.isNull(11)) {
					ArrayofItems.add(3, cursor.getString(11)); // deposit
				}
				if (!cursor.isNull(12)) {
					ArrayofItems.add(4, cursor.getString(12)); // detection
				}

				// Adding contact to list
				contactList.add(contact);
			} while (cursor.moveToNext());
		}

		db.close(); // Closing database connection
		// return contact list
		return contactList;
	}

	// Getting Spesific Contact
	public List<Contact> getSpecificContacts(String Name) {

		List<Contact> contactList = new ArrayList<Contact>();
		// Select All Query
		String selectQuery = "SELECT  * FROM " + TABLE_CONTACTS + "   WHERE  "
				+ KEY_NAME + " LIKE '" + Name.toString() + "%'";

		SQLiteDatabase db = this.getWritableDatabase();
		Cursor cursor = db.rawQuery(selectQuery, null);

		// looping through all rows and adding to list
		if (cursor.moveToFirst()) {
			do {
				Contact contact = new Contact();
				contact.setGuid(cursor.getString(0));
				contact.setID(cursor.getString(1));
				contact.setName(cursor.getString(2));
				contact.setRegionName(cursor.getString(3));
				contact.setPhoneNumber1(cursor.getString(4));
				contact.setPhoneNumber2(cursor.getString(5));
				contact.setPhoneNumber3(cursor.getString(6));
				contact.setPhoneNumber4(cursor.getString(7));
				contact.setAddress(cursor.getString(8));
				contact.setMobile(cursor.getString(9));
				contact.setFax(cursor.getString(10));
				contact.setDeposit(cursor.getString(11));
				contact.setDetection(cursor.getString(12));
				contact.setGuidVisitor(cursor.getString(13));
				contact.setGuidCustomerCategory(cursor.getString(14));

				// Adding contact to list
				contactList.add(contact);
			} while (cursor.moveToNext());
		}

		db.close(); // Closing database connection
		// return contact list
		return contactList;

	}

	public Contact getSpecificContactsByGuid(String Guid) {

		// List<Contact> contactList = new ArrayList<Contact>();
		// Select All Query
		String selectQuery = "SELECT  * FROM " + TABLE_CONTACTS + "   WHERE  "
				+ KEY_GUID + " = '" + Guid.toString() + "'";

		SQLiteDatabase db = this.getWritableDatabase();
		Cursor cursor = db.rawQuery(selectQuery, null);

		Object C = cursor.getCount();
		if (Integer.parseInt(C.toString()) == 0) {
			return null;
		}
		if (cursor != null)
			cursor.moveToFirst();

		Contact contact = new Contact();
		contact.setGuid(cursor.getString(0));
		contact.setID(cursor.getString(1));
		contact.setName(cursor.getString(2));
		contact.setRegionName(cursor.getString(3));
		contact.setPhoneNumber1(cursor.getString(4));
		contact.setPhoneNumber2(cursor.getString(5));
		contact.setPhoneNumber3(cursor.getString(6));
		contact.setPhoneNumber4(cursor.getString(7));
		contact.setAddress(cursor.getString(8));
		contact.setMobile(cursor.getString(9));
		contact.setFax(cursor.getString(10));
		contact.setDeposit(cursor.getString(11));
		contact.setDetection(cursor.getString(12));
		contact.setGuidVisitor(cursor.getString(13));
		contact.setGuidCustomerCategory(cursor.getString(14));

		db.close(); // Closing database connection
		// return contact list
		return contact;

	}

	// Updating single contact
	public int updateContact(Contact contact) {
		SQLiteDatabase db = this.getWritableDatabase();

		ContentValues values = new ContentValues();
		values.put(KEY_ID, contact.getID()); // Contact's id
		values.put(KEY_NAME, contact.getName()); // Contact's Name
		values.put(KEY_REGION_NAME, contact.getRegionName()); // Contact's
																// RegionName
		values.put(KEY_PH_NO_1, contact.getPhoneNumber1()); // Contact's
															// PhoneNumber1
		values.put(KEY_PH_NO_2, contact.getPhoneNumber2()); // Contact's
															// getPhoneNumber2
		values.put(KEY_PH_NO_3, contact.getPhoneNumber3()); // Contact's
															// getPhoneNumber3
		values.put(KEY_PH_NO_4, contact.getPhoneNumber4()); // Contact's
															// getPhoneNumber4
		values.put(KEY_ADDRESS, contact.getAddress()); // Contact's Address
		values.put(KEY_MOBILE, contact.getMobile()); // Contact's Mobile
		values.put(KEY_FAX, contact.getFax()); // Contact's Fax
		values.put(KEY_DEPOSIT, contact.getDeposit()); // Contact's Deposit
		values.put(KEY_DETECTION, contact.getDetection()); // Contact's
															// Detection
		values.put(KEY_GUID_VISITOR, contact.getGuidVisitor()); // Contact's
																// GuidVisitor
		values.put(KEY_GUID_CUSTOMER_CATEGORY,
				contact.getGuidCustomerCategory());

		// updating row
		return db.update(TABLE_CONTACTS, values, KEY_GUID + " = ?",
				new String[] { String.valueOf(contact.getGuid()) });
	}

	// Deleting single contact
	public void deleteContact(Contact contact) {
		SQLiteDatabase db = this.getWritableDatabase();
		db.delete(TABLE_CONTACTS, KEY_GUID + " = ?",
				new String[] { String.valueOf(contact.getGuid()) });
		db.close();
	}

	// Getting contacts Count
	public int getContactsCount() {
		String countQuery = "SELECT  * FROM " + TABLE_CONTACTS;
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor cursor = db.rawQuery(countQuery, null);
		cursor.close();

		db.close(); // Closing database connection
		// return count

		int count = cursor.getCount();
		return count;
	}

	// Deleting All items of Customer table
	public void deleteAllContacts() {
		SQLiteDatabase db = this.getWritableDatabase();
		db.delete(TABLE_CONTACTS, null, null);
		db.close();
	}

	// ------------------------ "Goods" table methods ----------------//

	// Adding new Good
	void addGoods(Goods goods) {
		SQLiteDatabase db = this.getWritableDatabase();

		ContentValues values = new ContentValues();
		values.put(KEY_GUID, goods.getGuid()); // Good's Guid
		values.put(KEY_ID, goods.getID()); // Good's Id
		values.put(KEY_NAME, goods.getGoodName()); // Good's Name
		values.put(KEY_STOCK, goods.getStock()); // Good's Stock
		values.put(KEY_COUNT_UNIT, goods.getCountUnit()); // Good's CountName
		values.put(KEY_PRICE, goods.getPrice()); // Good's Price
		values.put(KEY_PRICE_TYPE, goods.getPriceType()); // Good's PriceType
		values.put(KEY_Q_BOX, goods.getQBox()); // Good's QBox

		// Inserting Row
		db.insert(TABLE_GOODS, null, values);
		db.close(); // Closing database connection
	}

	// Getting single Good By Id
	Goods getGoodsById(String Id) {
		SQLiteDatabase db = this.getReadableDatabase();

		Cursor cursor = db.query(TABLE_GOODS, new String[] { KEY_GUID, KEY_ID,
				KEY_NAME, KEY_STOCK, KEY_COUNT_UNIT, KEY_PRICE, KEY_PRICE_TYPE,
				KEY_Q_BOX }, KEY_ID + "=?", new String[] { Id }, null, null,
				null, null);
		if (cursor != null)
			cursor.moveToFirst();

		Goods goods = new Goods(cursor.getString(0), cursor.getString(1),
				cursor.getString(2), cursor.getString(3), cursor.getString(4),
				cursor.getString(5), cursor.getString(6), cursor.getString(7));
		// return contact
		return goods;
	}

	// Getting All Goods
	@SuppressLint("NewApi")
	public List<Goods> getAllGoods() {
		List<Goods> goodsList = new ArrayList<Goods>();
		// Select All Query
		String selectQuery = "SELECT  * FROM " + TABLE_GOODS + " ORDER BY "
				+ KEY_ID;

		SQLiteDatabase db = this.getWritableDatabase();
		Cursor cursor = db.rawQuery(selectQuery, null);

		// looping through all rows and adding to list
		if (cursor.moveToFirst()) {
			do {
				Goods goods = new Goods();
				goods.setGuid(cursor.getString(0));
				goods.setID(cursor.getString(1));
				goods.setGoodName(cursor.getString(2));
				goods.setStock(cursor.getString(3));
				goods.setCountUnit(cursor.getString(4));
				goods.setPrice(cursor.getString(5));
				goods.setPriceType(cursor.getString(6));
				goods.setQBox(String.valueOf(cursor.getString(7)));

				// Adding Good to list
				goodsList.add(goods);
			} while (cursor.moveToNext());
		}

		// return Good list
		return goodsList;
	}

	// Getting specific Good by good's name
	public List<Goods> getSpecificGood(String GoodName) {

		List<Goods> goodsList = new ArrayList<Goods>();
		// Select All Query
		String selectQuery = "SELECT  * FROM " + TABLE_GOODS + "   WHERE  "
				+ KEY_NAME + " LIKE '" + GoodName.toString() + "%'";

		SQLiteDatabase db = this.getWritableDatabase();
		Cursor cursor = db.rawQuery(selectQuery, null);

		// looping through all rows and adding to list
		if (cursor.moveToFirst()) {
			do {
				Goods goods = new Goods();
				goods.setGuid(cursor.getString(0));
				goods.setID(cursor.getString(1));
				goods.setGoodName(cursor.getString(2));
				goods.setStock(cursor.getString(3));
				goods.setCountUnit(cursor.getString(4));
				goods.setPrice(cursor.getString(5));
				goods.setPriceType(cursor.getString(6));
				goods.setQBox(cursor.getString(7));

				// Adding Good to list
				goodsList.add(goods);
			} while (cursor.moveToNext());
		}

		// return Good list
		return goodsList;
	}

	// Getting single GoodTemp
	Goods getGoodsByGuid(String Guid) {

		String selectQuery = "SELECT  * FROM " + TABLE_GOODS + "   WHERE  "
				+ KEY_GUID + " = '" + Guid.toString() + "'";

		SQLiteDatabase db = this.getWritableDatabase();
		Cursor cursor = db.rawQuery(selectQuery, null);

		Object C = cursor.getCount();
		if (Integer.parseInt(C.toString()) == 0) {
			return null;
		}
		if (cursor != null)
			cursor.moveToFirst();

		Goods goods = new Goods(cursor.getString(0), cursor.getString(1),
				cursor.getString(2), cursor.getString(3), cursor.getString(4),
				cursor.getString(5), cursor.getString(6), cursor.getString(7));
		// return goods
		return goods;
	}

	// Updating single Good
	public int updateGoods(Goods goods) {
		SQLiteDatabase db = this.getWritableDatabase();

		ContentValues values = new ContentValues();
		values.put(KEY_ID, goods.getID()); // Good's id
		values.put(KEY_NAME, goods.getGoodName()); // Good's Name
		values.put(KEY_STOCK, goods.getStock()); // Good's Stock
		values.put(KEY_COUNT_UNIT, goods.getCountUnit()); // Good's CountName
		values.put(KEY_PRICE, goods.getPrice()); // Good's Price
		values.put(KEY_PRICE_TYPE, goods.getPriceType()); // Good's PriceType
		values.put(KEY_Q_BOX, goods.getQBox()); // Good's QBox

		// updating row
		return db.update(TABLE_GOODS, values, KEY_GUID + " = ?",
				new String[] { String.valueOf(goods.getGuid()) });
	}

	// Deleting single Good
	public void deleteGoods(Goods goods) {
		SQLiteDatabase db = this.getWritableDatabase();
		db.delete(TABLE_GOODS, KEY_GUID + " = ?",
				new String[] { String.valueOf(goods.getGuid()) });
		db.close();
	}

	// Getting Goods Count
	public int getGoodsCount() {
		String countQuery = "SELECT  * FROM " + TABLE_GOODS;
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor cursor = db.rawQuery(countQuery, null);
		cursor.close();

		// Object count = cursor.getCount();

		// return count
		return cursor.getCount();
	}

	// Deleting single Good
	public void deleteGoods(String Guid) {
		SQLiteDatabase db = this.getWritableDatabase();
		db.delete(TABLE_GOODS, KEY_GUID + " = ?", new String[] { Guid });
		db.close();
	}

	// Deleting All items of product table
	public void deleteAllGoods() {
		SQLiteDatabase db = this.getWritableDatabase();
		db.delete(TABLE_GOODS, null, null);
		db.close();
	}

	// ------------------------ "ContactTemp" table methods ----------------//

	// Adding new contact
	void addContactTemp(Contact contact) {
		SQLiteDatabase db = this.getWritableDatabase();

		ContentValues values = new ContentValues();
		values.put(KEY_GUID, contact.getGuid()); // Contact's Guid
		values.put(KEY_ID, contact.getID()); // Contact's ID
		values.put(KEY_NAME, contact.getName()); // Contact's Name
		values.put(KEY_REGION_NAME, contact.getRegionName()); // Contact's
																// RegionName
		values.put(KEY_PH_NO_1, contact.getPhoneNumber1()); // Contact's
															// PhoneNumber1
		values.put(KEY_PH_NO_2, contact.getPhoneNumber2()); // Contact's
															// getPhoneNumber2
		values.put(KEY_PH_NO_3, contact.getPhoneNumber3()); // Contact's
															// getPhoneNumber3
		values.put(KEY_PH_NO_4, contact.getPhoneNumber4()); // Contact's
															// getPhoneNumber4
		values.put(KEY_ADDRESS, contact.getAddress()); // Contact's Address
		values.put(KEY_MOBILE, contact.getMobile()); // Contact's Mobile
		values.put(KEY_FAX, contact.getFax()); // Contact's Fax
		values.put(KEY_DEPOSIT, contact.getDeposit()); // Contact's Deposit
		values.put(KEY_DETECTION, contact.getDetection()); // Contact's
															// Detection
		values.put(KEY_GUID_VISITOR, contact.getGuidVisitor());
		values.put(KEY_GUID_CUSTOMER_CATEGORY,
				contact.getGuidCustomerCategory());

		// Inserting Row
		db.insert(TABLE_CONTACTS_TEMP, null, values);
		db.close(); // Closing database connection
	}

	// Getting single contact By Id
	Contact getContactTempById(String Id) {
		SQLiteDatabase db = this.getReadableDatabase();

		Cursor cursor = db.query(TABLE_CONTACTS_TEMP, new String[] { KEY_GUID,
				KEY_ID, KEY_NAME, KEY_REGION_NAME, KEY_PH_NO_1, KEY_PH_NO_2,
				KEY_PH_NO_3, KEY_PH_NO_4, KEY_ADDRESS, KEY_MOBILE, KEY_FAX,
				KEY_DEPOSIT, KEY_DETECTION, KEY_GUID_VISITOR,
				KEY_GUID_CUSTOMER_CATEGORY }, KEY_ID + "=?",
				new String[] { Id }, null, null, null, null);
		if (cursor != null)
			cursor.moveToFirst();

		Contact contact = new Contact(cursor.getString(0), cursor.getString(1),
				cursor.getString(2), cursor.getString(3), cursor.getString(4),
				cursor.getString(5), cursor.getString(6), cursor.getString(7),
				cursor.getString(8), cursor.getString(9), cursor.getString(10),
				cursor.getString(11), cursor.getString(12),
				cursor.getString(13), cursor.getString(14));

		db.close(); // Closing database connection

		// return contact
		return contact;
	}

	// Getting single contact By Guid
	Contact getContactTempByGuid(String Guid) {
		SQLiteDatabase db = this.getReadableDatabase();

		Cursor cursor = db.query(TABLE_CONTACTS_TEMP, new String[] { KEY_GUID,
				KEY_ID, KEY_NAME, KEY_REGION_NAME, KEY_PH_NO_1, KEY_PH_NO_2,
				KEY_PH_NO_3, KEY_PH_NO_4, KEY_ADDRESS, KEY_MOBILE, KEY_FAX,
				KEY_DEPOSIT, KEY_DETECTION, KEY_GUID_VISITOR,
				KEY_GUID_CUSTOMER_CATEGORY }, KEY_GUID + "=?",
				new String[] { Guid }, null, null, null, null);
		if (cursor != null)
			cursor.moveToFirst();

		Contact contact = new Contact(cursor.getString(0), cursor.getString(1),
				cursor.getString(2), cursor.getString(3), cursor.getString(4),
				cursor.getString(5), cursor.getString(6), cursor.getString(7),
				cursor.getString(8), cursor.getString(9), cursor.getString(10),
				cursor.getString(11), cursor.getString(12),
				cursor.getString(13), cursor.getString(14));

		db.close(); // Closing database connection

		// return contact
		return contact;
	}

	// Getting All Contacts
	@SuppressLint("NewApi")
	public List<Contact> getAllContactsTemp() {
		List<Contact> contactList = new ArrayList<Contact>();
		// Select All Query
		String selectQuery = "SELECT  * FROM " + TABLE_CONTACTS_TEMP;

		SQLiteDatabase db = this.getWritableDatabase();
		Cursor cursor = db.rawQuery(selectQuery, null);

		// looping through all rows and adding to list
		if (cursor.moveToFirst()) {
			do {
				Contact contact = new Contact();
				contact.setGuid(cursor.getString(0));
				contact.setID(cursor.getString(1));
				contact.setName(cursor.getString(2));
				contact.setRegionName(cursor.getString(3));
				contact.setPhoneNumber1(cursor.getString(4));
				contact.setPhoneNumber2(cursor.getString(5));
				contact.setPhoneNumber3(cursor.getString(6));
				contact.setPhoneNumber4(cursor.getString(7));
				contact.setAddress(cursor.getString(8));
				contact.setMobile(cursor.getString(9));
				contact.setFax(cursor.getString(10));
				contact.setDeposit(cursor.getString(11));
				contact.setDetection(cursor.getString(12));
				contact.setGuidVisitor(cursor.getString(13));
				contact.setGuidCustomerCategory(cursor.getString(14));

				ArrayList<String> ArrayofItems = new ArrayList<String>();

				if (!cursor.isNull(1)) {
					ArrayofItems.add(0, cursor.getString(1)); // id
				}
				if (!cursor.isNull(2)) {
					ArrayofItems.add(1, cursor.getString(2)); // name
				}
				if (!cursor.isNull(9)) {
					ArrayofItems.add(2, cursor.getString(9)); // mobile
				}
				if (!cursor.isNull(11)) {
					ArrayofItems.add(3, cursor.getString(11)); // deposit
				}
				if (!cursor.isNull(12)) {
					ArrayofItems.add(4, cursor.getString(12)); // detection
				}

				// Adding contact to list
				contactList.add(contact);
			} while (cursor.moveToNext());
		}

		db.close(); // Closing database connection
		// return contact list
		return contactList;
	}

	// Getting All Contacts
	public List<Contact> getSpecificContactsTemp(String Name) {

		List<Contact> contactList = new ArrayList<Contact>();
		// Select All Query
		String selectQuery = "SELECT  * FROM " + TABLE_CONTACTS_TEMP
				+ "   WHERE  " + KEY_NAME + " LIKE '" + Name.toString() + "%'";

		SQLiteDatabase db = this.getWritableDatabase();
		Cursor cursor = db.rawQuery(selectQuery, null);

		// looping through all rows and adding to list
		if (cursor.moveToFirst()) {
			do {
				Contact contact = new Contact();
				contact.setGuid(cursor.getString(0));
				contact.setID(cursor.getString(1));
				contact.setName(cursor.getString(2));
				contact.setRegionName(cursor.getString(3));
				contact.setPhoneNumber1(cursor.getString(4));
				contact.setPhoneNumber2(cursor.getString(5));
				contact.setPhoneNumber3(cursor.getString(6));
				contact.setPhoneNumber4(cursor.getString(7));
				contact.setAddress(cursor.getString(8));
				contact.setMobile(cursor.getString(9));
				contact.setFax(cursor.getString(10));
				contact.setDeposit(cursor.getString(11));
				contact.setDetection(cursor.getString(12));
				contact.setGuidVisitor(cursor.getString(13));
				contact.setGuidCustomerCategory(cursor.getString(14));

				// Adding contact to list
				contactList.add(contact);
			} while (cursor.moveToNext());
		}

		db.close(); // Closing database connection
		// return contact list
		return contactList;

	}

	// Updating single contact
	public int updateContactTemp(Contact contact) {
		SQLiteDatabase db = this.getWritableDatabase();

		ContentValues values = new ContentValues();
		values.put(KEY_ID, contact.getID()); // Contact's id
		values.put(KEY_NAME, contact.getName()); // Contact's Name
		values.put(KEY_REGION_NAME, contact.getRegionName()); // Contact's
																// RegionName
		values.put(KEY_PH_NO_1, contact.getPhoneNumber1()); // Contact's
															// PhoneNumber1
		values.put(KEY_PH_NO_2, contact.getPhoneNumber2()); // Contact's
															// getPhoneNumber2
		values.put(KEY_PH_NO_3, contact.getPhoneNumber3()); // Contact's
															// getPhoneNumber3
		values.put(KEY_PH_NO_4, contact.getPhoneNumber4()); // Contact's
															// getPhoneNumber4
		values.put(KEY_ADDRESS, contact.getAddress()); // Contact's Address
		values.put(KEY_MOBILE, contact.getMobile()); // Contact's Mobile
		values.put(KEY_FAX, contact.getFax()); // Contact's Fax
		values.put(KEY_DEPOSIT, contact.getDeposit()); // Contact's Deposit
		values.put(KEY_DETECTION, contact.getDetection()); // Contact's
															// Detection
		values.put(KEY_GUID_VISITOR, contact.getGuidVisitor());
		values.put(KEY_GUID_CUSTOMER_CATEGORY,
				contact.getGuidCustomerCategory());

		// updating row
		return db.update(TABLE_CONTACTS_TEMP, values, KEY_GUID + " = ?",
				new String[] { String.valueOf(contact.getGuid()) });
	}

	// Deleting single contact
	public void deleteContactTemp(Contact contact) {
		SQLiteDatabase db = this.getWritableDatabase();
		db.delete(TABLE_CONTACTS_TEMP, KEY_GUID + " = ?",
				new String[] { String.valueOf(contact.getGuid()) });
		db.close();
	}

	// Getting contactstemp Count
	public int getContactsTempCount() {
		String countQuery = "SELECT  * FROM " + TABLE_CONTACTS_TEMP;
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor cursor = db.rawQuery(countQuery, null);

		// Closing database connection
		// return count
		Object C = cursor.getCount();
		return Integer.parseInt(C.toString());
	}

	// Deleting All items of Customer table
	public void deleteAllContactsTemp() {
		SQLiteDatabase db = this.getWritableDatabase();
		db.delete(TABLE_CONTACTS_TEMP, null, null);
		db.close();
	}

	// ------------------------ "GoodsTemp" table methods ----------------//

	// Adding new Good
	void addGoodsTemp(GoodsTemp goods) {
		SQLiteDatabase db = this.getWritableDatabase();

		ContentValues values = new ContentValues();
		values.put(KEY_GUID, goods.getGuid()); // Good's Guid
		values.put(KEY_ID, goods.getID()); // Good's Id
		values.put(KEY_NAME, goods.getGoodName()); // Good's Name
		values.put(KEY_STOCK, goods.getStock()); // Good's Stock
		values.put(KEY_COUNT_UNIT, goods.getCountUnit()); // Good's CountName
		values.put(KEY_PRICE, goods.getPrice()); // Good's Price
		values.put(KEY_PRICE_TYPE, goods.getPriceType()); // Good's PriceType
		values.put(KEY_Q_BOX, goods.getQBox()); // Good's Qbox
		values.put(KEY_COUNT, goods.getCount()); // Good's count
		values.put(KEY_ITEM_TOTAL, goods.getItemTotal()); // Good's ItemTotal

		// Inserting Row
		db.insert(TABLE_GOODS_TEMP, null, values);
		db.close(); // Closing database connection
	}

	// Getting single Good By Id
	GoodsTemp getGoodsTempById(String Id) {
		SQLiteDatabase db = this.getReadableDatabase();

		Cursor cursor = db.query(TABLE_GOODS_TEMP, new String[] { KEY_GUID,
				KEY_ID, KEY_NAME, KEY_STOCK, KEY_COUNT_UNIT, KEY_PRICE,
				KEY_PRICE_TYPE, KEY_Q_BOX, KEY_COUNT, KEY_ITEM_TOTAL }, KEY_ID
				+ "=?", new String[] { Id }, null, null, null, null);
		if (cursor != null)
			cursor.moveToFirst();

		GoodsTemp goods = new GoodsTemp(cursor.getString(0),
				cursor.getString(1), cursor.getString(2), cursor.getString(3),
				cursor.getString(4), cursor.getString(5), cursor.getString(6),
				cursor.getString(7), cursor.getString(8), cursor.getString(9));
		// return contact
		return goods;
	}

	// Getting Specific GoodsTemp
	public GoodsTemp getSpecificGoodByGuid(String Guid, String Quantity,
			String fee) {

		// SQLiteDatabase db = this.getReadableDatabase();

		String selectQuery = "SELECT  * FROM " + TABLE_GOODS + "   WHERE  "
				+ KEY_GUID + " = '" + Guid.toString() + "'";

		SQLiteDatabase db = this.getWritableDatabase();
		Cursor cursor = db.rawQuery(selectQuery, null);
		/*
		 * Cursor cursor = db.query(TABLE_GOODS_TEMP, new String[] { KEY_GUID,
		 * KEY_ID, // KEY_NAME, KEY_STOCK, KEY_COUNT_UNIT, KEY_PRICE,
		 * KEY_PRICE_TYPE, KEY_COUNT, KEY_ITEM_TOTAL }, KEY_GUID + "=?", new
		 * String[] { Guid }, null, null, null, null);
		 */

		Object C = cursor.getCount();
		if (Integer.parseInt(C.toString()) == 0) {
			return null;
		}
		if (cursor != null)
			cursor.moveToFirst();

		GoodsTemp goodstemp = new GoodsTemp(cursor.getString(0),
				cursor.getString(1), cursor.getString(2), cursor.getString(3),
				cursor.getString(4), cursor.getString(5), cursor.getString(6),
				cursor.getString(7), Quantity, String.valueOf(Integer
						.parseInt(fee) * Integer.parseInt(Quantity)));
		// return goodtemp
		return goodstemp;

	}

	// Getting single GoodTemp
	Goods getGoodsTempByGuid(String Guid) {
		SQLiteDatabase db = this.getReadableDatabase();

		Cursor cursor = db.query(TABLE_GOODS_TEMP, new String[] { KEY_GUID,
				KEY_ID, KEY_NAME, KEY_STOCK, KEY_COUNT_UNIT, KEY_PRICE,
				KEY_PRICE_TYPE, KEY_Q_BOX }, KEY_GUID + "=?",
				new String[] { Guid }, null, null, null, null);
		if (cursor != null)
			cursor.moveToFirst();

		Goods goods = new Goods(cursor.getString(0), cursor.getString(1),
				cursor.getString(2), cursor.getString(3), cursor.getString(4),
				cursor.getString(5), cursor.getString(6), cursor.getString(7));
		// return contact
		return goods;
	}

	// Getting All Goods
	@SuppressLint("NewApi")
	public List<GoodsTemp> getAllGoodsTemp() {
		List<GoodsTemp> goodsList = new ArrayList<GoodsTemp>();
		// Select All Query
		String selectQuery = "SELECT  * FROM " + TABLE_GOODS_TEMP;

		SQLiteDatabase db = this.getWritableDatabase();
		Cursor cursor = db.rawQuery(selectQuery, null);

		// looping through all rows and adding to list
		if (cursor.moveToFirst()) {
			do {
				GoodsTemp goods = new GoodsTemp();
				goods.setGuid(cursor.getString(0));
				goods.setID(cursor.getString(1));
				goods.setGoodName(cursor.getString(2));
				goods.setStock(cursor.getString(3));
				goods.setCountUnit(cursor.getString(4));
				goods.setPrice(cursor.getString(5));
				goods.setPriceType(cursor.getString(6));
				goods.setQBox(cursor.getString(7));
				goods.setCount(cursor.getString(8));
				goods.setItemTotal(cursor.getString(9));

				// Adding Good to list
				goodsList.add(goods);
			} while (cursor.moveToNext());
		}

		// return Good list
		return goodsList;
	}

	// Getting Specific Goods
	public List<Goods> getSpecificGoodTemp(String GoodName) {

		List<Goods> goodsList = new ArrayList<Goods>();
		// Select All Query
		String selectQuery = "SELECT  * FROM " + TABLE_GOODS_TEMP
				+ "   WHERE  " + KEY_NAME + " LIKE '" + GoodName.toString()
				+ "%'";

		SQLiteDatabase db = this.getWritableDatabase();
		Cursor cursor = db.rawQuery(selectQuery, null);

		// looping through all rows and adding to list
		if (cursor.moveToFirst()) {
			do {
				Goods goods = new Goods();
				goods.setGuid(cursor.getString(0));
				goods.setID(cursor.getString(1));
				goods.setGoodName(cursor.getString(2));
				goods.setStock(cursor.getString(3));
				goods.setCountUnit(cursor.getString(4));
				goods.setPrice(cursor.getString(5));
				goods.setPriceType(cursor.getString(6));
				goods.setQBox(cursor.getString(7));

				// Adding Good to list
				goodsList.add(goods);
			} while (cursor.moveToNext());
		}

		// return Good list
		return goodsList;
	}

	// Getting Specific Goods
	public GoodsTemp getSpecificGoodTempByGuid(String Guid) {

		// SQLiteDatabase db = this.getReadableDatabase();

		String selectQuery = "SELECT  * FROM " + TABLE_GOODS_TEMP
				+ "   WHERE  " + KEY_GUID + " = '" + Guid.toString() + "'";

		SQLiteDatabase db = this.getWritableDatabase();
		Cursor cursor = db.rawQuery(selectQuery, null);
		/*
		 * Cursor cursor = db.query(TABLE_GOODS_TEMP, new String[] { KEY_GUID,
		 * KEY_ID, // KEY_NAME, KEY_STOCK, KEY_COUNT_UNIT, KEY_PRICE,
		 * KEY_PRICE_TYPE, KEY_COUNT, KEY_ITEM_TOTAL }, KEY_GUID + "=?", new
		 * String[] { Guid }, null, null, null, null);
		 */

		Object C = cursor.getCount();
		if (Integer.parseInt(C.toString()) == 0) {
			return null;
		}
		if (cursor != null)
			cursor.moveToFirst();

		GoodsTemp goodstemp = new GoodsTemp(cursor.getString(0),
				cursor.getString(1), cursor.getString(2), cursor.getString(3),
				cursor.getString(4), cursor.getString(5), cursor.getString(6),
				cursor.getString(7), cursor.getString(8), cursor.getString(9));
		// return goodtemp
		return goodstemp;

	}

	// Updating single Good
	public int updateGoodsTemp(Goods goods) {
		SQLiteDatabase db = this.getWritableDatabase();

		ContentValues values = new ContentValues();
		values.put(KEY_ID, goods.getID()); // Good's id
		values.put(KEY_NAME, goods.getGoodName()); // Good's Name
		values.put(KEY_STOCK, goods.getStock()); // Good's Stock
		values.put(KEY_COUNT_UNIT, goods.getCountUnit()); // Good's CountName
		values.put(KEY_PRICE, goods.getPrice()); // Good's Price
		values.put(KEY_PRICE_TYPE, goods.getPriceType()); // Good's PriceType
		values.put(KEY_Q_BOX, goods.getQBox()); // Good's PriceType

		// updating row
		return db.update(TABLE_GOODS_TEMP, values, KEY_GUID + " = ?",
				new String[] { String.valueOf(goods.getGuid()) });
	}

	// Updating single Good By Guid
	public int updateGoodsTempByGuid(String Guid, String Count, String ItemTotal) {
		SQLiteDatabase dbup = this.getWritableDatabase();

		ContentValues values = new ContentValues();
		values.put(KEY_COUNT, Count); // Good's Count
		values.put(KEY_ITEM_TOTAL, ItemTotal); // Good's ItemTotal

		// updating row
		return dbup.update(TABLE_GOODS_TEMP, values, KEY_GUID + " = ?",
				new String[] { Guid });
	}

	// Deleting single Good
	public void deleteGoodsTemp(Goods goods) {
		SQLiteDatabase db = this.getWritableDatabase();
		db.delete(TABLE_GOODS_TEMP, KEY_GUID + " = ?",
				new String[] { String.valueOf(goods.getGuid()) });
		db.close();
	}

	// Getting Goods Count
	public int getGoodsTempCount() {
		String countQuery = "SELECT  * FROM " + TABLE_GOODS_TEMP;
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor cursor = db.rawQuery(countQuery, null);

		// Object count = cursor.getCount();

		// return count
		return cursor.getCount();
	}

	// Getting Goods Count
	public String getGoodsTempSumRiali() {
		String selectQuery = "SELECT  * FROM " + TABLE_GOODS_TEMP;
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor cursorss = db.rawQuery(selectQuery, null);

		int TotalCount = 0;

		// looping through all rows and adding to list
		if (cursorss.getCount() > 0) {
			if (cursorss.moveToFirst()) {
				do {
					TotalCount = TotalCount
							+ Integer.parseInt(cursorss.getString(9));
				} while (cursorss.moveToNext());
			}
		}

		// return count
		return String.valueOf(TotalCount);
	}

	// Getting Goods Count
	public String getGoodsTempSumCount() {
		String selectQuery = "SELECT  * FROM " + TABLE_GOODS_TEMP;
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor cursorss = db.rawQuery(selectQuery, null);

		int TotalCount = 0;

		// looping through all rows and adding to list
		if (cursorss.getCount() > 0) {
			if (cursorss.moveToFirst()) {
				do {
					TotalCount = TotalCount
							+ Integer.parseInt(cursorss.getString(8));
				} while (cursorss.moveToNext());
			}
		}

		// return count
		return String.valueOf(TotalCount);
	}

	// Deleting single Good by Guid
	public void deleteGoodsTempBuGuid(String Guid) {
		SQLiteDatabase db = this.getWritableDatabase();
		db.delete(TABLE_GOODS_TEMP, KEY_GUID + " = ?", new String[] { Guid });
		db.close();
	}

	// Deleting single Good by ID
	public void deleteGoodsTempById(String Id) {
		SQLiteDatabase db = this.getWritableDatabase();
		db.delete(TABLE_GOODS_TEMP, KEY_ID + " = ?", new String[] { Id });
		db.close();
	}

	// Deleting All items of product table
	public void deleteAllGoodsTemp() {
		SQLiteDatabase db = this.getWritableDatabase();
		db.delete(TABLE_GOODS_TEMP, null, null);
		db.close();
	}

	// ------------------------ "Factors" table methods ----------------//
	// Adding new Factor
	void addFactors(Factors factors) {
		SQLiteDatabase db = this.getWritableDatabase();

		ContentValues values = new ContentValues();
		// values.put(KEY_ID, null); // Orders's ID
		values.put(KEY_GUID_INVC_REQ, factors.getGuidInvcRequest()); // factors's
																		// GuidInvcRequest
		values.put(KEY_TYPE_INVC_ID, factors.getTypeInvcId()); // factors's
																// TypeInvcId
		values.put(KEY_TYPE_INVC_NAME, factors.getTypeInvcName()); // factors's
																	// TypeInvcName
		values.put(KEY_GUID_OPERATOR, factors.getGuidOperator()); // factors's
																	// GuidOperator
		values.put(KEY_GUID_CUSTOMER, factors.getGuidCustomer()); // factors's
																	// GuidCustomer
		values.put(KEY_NAME_CUSTOMER, factors.getNameCustomer()); // factors's
																	// NameCustomer
		values.put(KEY_DATE, factors.getLocalDate()); // factors's LocalDate
		values.put(KEY_TIME, factors.getLocalTime()); // factors's LocalTime
		values.put(KEY_SYSTEM_ID, factors.getSystemID()); // factors's SystemID
		values.put(KEY_GUID_RESELER, factors.getGuidReseler()); // factors's
																// GuidReseler
		values.put(KEY_GUID_BRANCHE, factors.getGuidBranche()); // factors's
																// GuidBranche
		values.put(KEY_FACTOR_NOTE, factors.getNote()); // factors's Note
		values.put(KEY_SENDED, factors.getSended()); // factors's Sended
		values.put(KEY_DATE_NUMBER, factors.getLocalDateNumber()); // factors's
																	// LocalDateNumber

		// Inserting Row
		db.insert(TABLE_FACTORS, null, values);
		db.close(); // Closing database connection
	}

	// Getting All unsended Factors
	@SuppressLint("NewApi")
	public List<Factors> getAllFactorsUnsended(String GuidOperator) {
		List<Factors> FactorsList = new ArrayList<Factors>();
		// Select All Query
		String selectQuery = "SELECT  * FROM " + TABLE_FACTORS + "   WHERE  "
				+ KEY_GUID_OPERATOR + " ='" + GuidOperator.toUpperCase()
				+ "' AND " + KEY_SENDED + "='false'";

		SQLiteDatabase db = this.getWritableDatabase();
		Cursor cursor = db.rawQuery(selectQuery, null);

		// looping through all rows and adding to list
		if (cursor.moveToFirst()) {
			do {
				Factors factors = new Factors();

				factors.setGuidInvcRequest(cursor.getString(1)); // factors's
																	// GuidInvcRequest
				factors.setTypeInvcId(cursor.getString(2)); // factors's
															// TypeInvcId
				factors.setTypeInvcName(cursor.getString(3)); // factors's
																// TypeInvcName
				factors.setGuidOperator(cursor.getString(4)); // factors's
																// GuidOperator
				factors.setGuidCustomer(cursor.getString(5)); // factors's
																// GuidCustomer
				factors.setNameCustomer(cursor.getString(6)); // factors's
																// NameCustomer
				factors.setLocalDate(cursor.getString(7)); // factors's
															// LocalDate
				factors.setLocalTime(cursor.getString(8)); // factors's
															// LocalTime
				factors.setSystemID(cursor.getString(9)); // factors's
															// GuidSystemId
				factors.setGuidReseler(cursor.getString(10)); // factorss's
																// GuidReseler
				factors.setGuidBranche(cursor.getString(11)); // factors's
																// GuidBranche
				factors.setNote(cursor.getString(12)); // factors's Note
				factors.setSended(cursor.getString(13)); // factors's Sended
				factors.setLocalDateNumber(cursor.getString(14)); // factors's
																	// LocalDateNumber

				// Adding Factors to list
				FactorsList.add(factors);
			} while (cursor.moveToNext());
		}

		// return factor list
		return FactorsList;
	}

	// Getting Specific Factors with DateStr
	public Factors getSpecificFactorsByDateStr(String DateStr) {

		SQLiteDatabase db = this.getReadableDatabase();

		Cursor cursor = db.query(TABLE_FACTORS,
				new String[] { KEY_ID, KEY_GUID_INVC_REQ, KEY_TYPE_INVC_ID,
						KEY_TYPE_INVC_NAME, KEY_GUID_OPERATOR,
						KEY_GUID_CUSTOMER, KEY_NAME_CUSTOMER, KEY_DATE,
						KEY_TIME, KEY_SYSTEM_ID, KEY_GUID_RESELER,
						KEY_GUID_BRANCHE, KEY_FACTOR_NOTE, KEY_SENDED,
						KEY_DATE_NUMBER }, KEY_DATE + "=?",
				new String[] { DateStr }, null, null, null, null);

		Object C = cursor.getCount();
		if (Integer.parseInt(C.toString()) == 0) {
			Factors factor = new Factors();
			factor.setLocalDateNumber(DateStr);
			return factor;
		}

		if (cursor != null)
			cursor.moveToFirst();

		Factors factor = new Factors(Integer.parseInt(cursor.getString(0)),
				cursor.getString(1), cursor.getString(2), cursor.getString(3),
				cursor.getString(4), cursor.getString(5), cursor.getString(6),
				cursor.getString(7), cursor.getString(8), cursor.getString(9),
				cursor.getString(10), cursor.getString(11),
				cursor.getString(12), cursor.getString(13),
				cursor.getString(14));
		// return factors
		return factor;

	}

	// Getting single Factor By Guid
	Factors getFactorByGuid(String Guid) {
		SQLiteDatabase db = this.getReadableDatabase();

		Cursor cursor = db.query(TABLE_FACTORS,
				new String[] { KEY_ID, KEY_GUID_INVC_REQ, KEY_TYPE_INVC_ID,
						KEY_TYPE_INVC_NAME, KEY_GUID_OPERATOR,
						KEY_GUID_CUSTOMER, KEY_NAME_CUSTOMER, KEY_DATE,
						KEY_TIME, KEY_SYSTEM_ID, KEY_GUID_RESELER,
						KEY_GUID_BRANCHE, KEY_FACTOR_NOTE, KEY_SENDED,
						KEY_DATE_NUMBER }, KEY_GUID_INVC_REQ + "=?",
				new String[] { Guid }, null, null, null, null);
		if (cursor != null)
			cursor.moveToFirst();

		Factors factor = new Factors(Integer.parseInt(cursor.getString(0)),
				cursor.getString(1), cursor.getString(2), cursor.getString(3),
				cursor.getString(4), cursor.getString(5), cursor.getString(6),
				cursor.getString(7), cursor.getString(8), cursor.getString(9),
				cursor.getString(10), cursor.getString(11),
				cursor.getString(12), cursor.getString(13),
				cursor.getString(14));

		db.close(); // Closing database connection

		// return factor
		return factor;
	}

	// Updating single Orders to Sended Mode
	public int updateFactorToSendedMode(Factors factors) {
		SQLiteDatabase db = this.getWritableDatabase();

		ContentValues values = new ContentValues();
		values.put(KEY_SENDED, "true"); // orders sended!

		// updating row
		return db.update(TABLE_FACTORS, values, KEY_GUID_INVC_REQ + " = ?",
				new String[] { String.valueOf(factors.getGuidInvcRequest()) });
	}

	// ///////////////////////////////////////////////////////////

	// Getting Factors with customize condition which is definded by user &
	// press Search Button
	@SuppressLint("NewApi")
	public List<Factors> getFactorsWithSearchCondition(
			String[] SelectCommandString, String GuidOperator) {
		List<Factors> FactorsList = new ArrayList<Factors>();

		String StartDate = SelectCommandString[0];
		String EndDate = SelectCommandString[1];
		String editTxtCustomerName = SelectCommandString[2];
		String spinnerFactorStr = SelectCommandString[3];
		String spinnerSendedStr = SelectCommandString[4];

		String WhereCondition = "";
		if (StartDate.length() > 0) {
			WhereCondition += " AND " + KEY_DATE_NUMBER + " >= "
					+ StartDate.toString();
		}
		if (EndDate.length() > 0) {
			WhereCondition += " AND " + KEY_DATE_NUMBER + " <= "
					+ EndDate.toString();
		}
		if (editTxtCustomerName.length() > 0) {
			WhereCondition += " AND " + KEY_NAME_CUSTOMER + " LIKE '"
					+ editTxtCustomerName.toString() + "%'";
		}
		if (spinnerFactorStr.length() > 0) {
			WhereCondition += " AND " + KEY_TYPE_INVC_ID + " = '"
					+ spinnerFactorStr.toString() + "'";
		}
		if (spinnerSendedStr.length() > 0) {
			WhereCondition += " AND " + KEY_SENDED + " = '"
					+ spinnerSendedStr.toString() + "'";
		}
		// Select Query
		String selectQuery = "SELECT  * FROM " + TABLE_FACTORS + " WHERE "
				+ KEY_ID + " IS NOT NULL " + "   AND  " + KEY_GUID_OPERATOR
				+ " ='" + GuidOperator.toUpperCase() + "' " + WhereCondition;

		SQLiteDatabase db = this.getWritableDatabase();
		Cursor cursor = db.rawQuery(selectQuery, null);

		// looping through all rows and adding to list
		if (cursor.moveToFirst()) {
			do {
				Factors factors = new Factors();
				factors.setID(Integer.parseInt(cursor.getString(0))); // factors's
																		// ID
				factors.setGuidInvcRequest(cursor.getString(1)); // factors's
																	// GuidInvcRequest
				factors.setTypeInvcId(cursor.getString(2)); // factors's
															// TypeInvcId
				factors.setTypeInvcName(cursor.getString(3)); // factors's
																// TypeInvcName
				factors.setGuidOperator(cursor.getString(4)); // factors's
																// GuidOperator
				factors.setGuidCustomer(cursor.getString(5)); // factors's
																// GuidCustomer
				factors.setNameCustomer(cursor.getString(6)); // factors's
																// NameCustomer
				factors.setLocalDate(cursor.getString(7)); // factors's
															// LocalDate
				factors.setLocalTime(cursor.getString(8)); // factors's
															// LocalTime
				factors.setSystemID(cursor.getString(9)); // factors's
															// GuidSystemId
				factors.setGuidReseler(cursor.getString(10)); // factorss's
																// GuidReseler
				factors.setGuidBranche(cursor.getString(11)); // factors's
																// GuidBranche
				factors.setNote(cursor.getString(12)); // factors's Note
				factors.setSended(cursor.getString(13)); // factors's Sended
				factors.setLocalDateNumber(cursor.getString(14)); // factors's
																	// LocalDateNumber

				// Adding factors to list
				FactorsList.add(factors);
			} while (cursor.moveToNext());
		}

		// return factor list
		return FactorsList;
	}

	// Deleting single Factor
	public void deleteFactor(Factors factor) {
		SQLiteDatabase db = this.getWritableDatabase();
		db.delete(TABLE_FACTORS, KEY_GUID_INVC_REQ + " = ?",
				new String[] { String.valueOf(factor.getGuidInvcRequest()) });
		db.close();
	}

	// Deleting single Factor By Guid
	public void deleteFactorByGuid(String Guid) {
		SQLiteDatabase db = this.getWritableDatabase();
		db.delete(TABLE_FACTORS, KEY_GUID_INVC_REQ + " = ?",
				new String[] { Guid });
		db.close();
	}

	// ------------------------ "Orders" table methods ----------------//
	// Adding new Order
	void addOrders(Orders orders) {
		SQLiteDatabase db = this.getWritableDatabase();

		ContentValues values = new ContentValues();
		// values.put(KEY_ID, null); // Orders's ID
		values.put(KEY_GUID_REQ, orders.getGuidRequest()); // Orders's
															// GuidRequest
		values.put(KEY_GUID_INVC_REQ, orders.getGuidInvcRequest()); // Orders's
																	// GuidInvcRequest
		values.put(KEY_TYPE_INVC_ID, orders.getTypeInvcId()); // Orders's
																// TypeInvcId
		values.put(KEY_GUID_OPERATOR, orders.getGuidOperator()); // Orders's
																	// GuidOperator
		values.put(KEY_GUID_CUSTOMER, orders.getGuidCustomer()); // Orders's
																	// GuidCustomer
		values.put(KEY_NAME_CUSTOMER, orders.getNameCustomer()); // Orders's
																	// NameCustomer
		values.put(KEY_GUID_PROD_SCALE, orders.getGuidProdScale()); // Orders's
																	// GuidProdScale
		values.put(KEY_NAME_PROD_SCALE, orders.getNameProdScale()); // Orders's
																	// NameProdScale
		values.put(KEY_QUANTITY, orders.getQuantity()); // Orders's Quantity
		values.put(KEY_FEE, orders.getFee()); // Orders's getFee
		values.put(KEY_DATE, orders.getLocalDate()); // Orders's LocalDate
		values.put(KEY_TIME, orders.getLocalTime()); // Orders's LocalTime
		values.put(KEY_GUID_RESELER, orders.getGuidReseler()); // Orders's
																// GuidReseler
		values.put(KEY_SENDED, orders.getSended()); // Orders's Sended
		values.put(KEY_DATE_NUMBER, orders.getLocalDateNumber()); // Orders's
																	// LocalDateNumber
		
		values.put(KEY_QUANTITY_INVC, orders.getQuantityInvc());

		// Inserting Row
		db.insert(TABLE_ORDERS, null, values);
		db.close(); // Closing database connection
	}

	// Getting All unsended Orders
	@SuppressLint("NewApi")
	public List<Orders> getAllOrdersUnsended(String GuidOperator) {
		List<Orders> OrdersList = new ArrayList<Orders>();
		// Select All Query
		String selectQuery = "SELECT  * FROM " + TABLE_ORDERS + "   WHERE  "
				+ KEY_GUID_OPERATOR + " ='" + GuidOperator.toUpperCase()
				+ "' AND " + KEY_SENDED + "='false'";

		SQLiteDatabase db = this.getWritableDatabase();
		Cursor cursor = db.rawQuery(selectQuery, null);

		// looping through all rows and adding to list
		if (cursor.moveToFirst()) {
			do {
				Orders orders = new Orders();
				orders.setGuidRequest(cursor.getString(1)); // Orders's
															// GuidRequest
				orders.setGuidInvcRequest(cursor.getString(2)); // Orders's
																// GuidInvcRequest
				orders.setTypeInvcId(cursor.getString(3)); // Orders's
															// TypeInvcId
				orders.setGuidOperator(cursor.getString(4)); // Orders's
																// GuidOperator
				orders.setGuidCustomer(cursor.getString(5)); // Orders's
																// GuidCustomer
				orders.setNameCustomer(cursor.getString(6)); // Orders's
																// NameCustomer
				orders.setGuidProdScale(cursor.getString(7)); // Orders's
																// GuidProdScale
				orders.setNameProdScale(cursor.getString(8)); // Orders's
																// NameProdScale
				orders.setQuantity(cursor.getString(9)); // Orders's Quantity
				orders.setFee(cursor.getString(10)); // Orders's getFee
				orders.setLocalDate(cursor.getString(11)); // Orders's LocalDate
				orders.setLocalTime(cursor.getString(12)); // Orders's LocalTime
				orders.setGuidReseler(cursor.getString(13)); // Orders's
																// GuidReseler
				orders.setSended(cursor.getString(14)); // Orders's Sended
				orders.setLocalDateNumber(cursor.getString(15)); // Orders's
																	// LocalDateNumber
				orders.setQuantityInvc(cursor.getString(16));

				// Adding Orders to list
				OrdersList.add(orders);
			} while (cursor.moveToNext());
		}

		// return order list
		return OrdersList;
	}

	// Getting All Orders
	@SuppressLint("NewApi")
	public List<Orders> getAllOrders(String GuidOperator) {
		List<Orders> OrdersList = new ArrayList<Orders>();
		// Select All Query
		String selectQuery = "SELECT  * FROM " + TABLE_ORDERS + "   WHERE  "
				+ KEY_GUID_OPERATOR + " ='" + GuidOperator.toUpperCase() + "'";

		SQLiteDatabase db = this.getWritableDatabase();
		Cursor cursor = db.rawQuery(selectQuery, null);

		// looping through all rows and adding to list
		if (cursor.moveToFirst()) {
			do {
				Orders orders = new Orders();
				orders.setID(Integer.parseInt(cursor.getString(0))); // Orders's
																		// ID
				orders.setGuidRequest(cursor.getString(1)); // Orders's
															// GuidRequest
				orders.setGuidInvcRequest(cursor.getString(2)); // Orders's
																// GuidInvcRequest
				orders.setTypeInvcId(cursor.getString(3)); // Orders's
															// TypeInvcId
				orders.setGuidOperator(cursor.getString(4)); // Orders's
																// GuidOperator
				orders.setGuidCustomer(cursor.getString(5)); // Orders's
																// GuidCustomer
				orders.setNameCustomer(cursor.getString(6)); // Orders's
																// NameCustomer
				orders.setGuidProdScale(cursor.getString(7)); // Orders's
																// GuidProdScale
				orders.setNameProdScale(cursor.getString(8)); // Orders's
																// NameProdScale
				orders.setQuantity(cursor.getString(9)); // Orders's Quantity
				orders.setFee(cursor.getString(10)); // Orders's getFee
				orders.setLocalDate(cursor.getString(11)); // Orders's LocalDate
				orders.setLocalTime(cursor.getString(12)); // Orders's LocalTime
				orders.setGuidReseler(cursor.getString(13)); // Orders's
																// GuidReseler
				orders.setSended(cursor.getString(14)); // Orders's Sended
				orders.setLocalDateNumber(cursor.getString(15)); // Orders's
																	// LocalDateNumber
				orders.setQuantityInvc(cursor.getString(16));

				// Adding Orders to list
				OrdersList.add(orders);
			} while (cursor.moveToNext());
		}

		// return order list
		return OrdersList;
	}

	// Getting Orders with guid request
	@SuppressLint("NewApi")
	public List<Orders> getOrdersWithGuidReq(String GuidReq) {
		List<Orders> OrdersList = new ArrayList<Orders>();

		// Select Query
		String selectQuery = "SELECT  * FROM " + TABLE_ORDERS + " WHERE "
				+ KEY_GUID_INVC_REQ + " ='" + GuidReq + "'";

		SQLiteDatabase db = this.getWritableDatabase();
		Cursor cursor = db.rawQuery(selectQuery, null);

		// looping through all rows and adding to list
		if (cursor.moveToFirst()) {
			do {
				Orders orders = new Orders();
				orders.setID(Integer.parseInt(cursor.getString(0))); // Orders's
																		// ID
				orders.setGuidRequest(cursor.getString(1)); // Orders's
															// GuidRequest
				orders.setGuidInvcRequest(cursor.getString(2)); // Orders's
																// GuidInvcRequest
				orders.setTypeInvcId(cursor.getString(3)); // Orders's
															// TypeInvcId
				orders.setGuidOperator(cursor.getString(4)); // Orders's
																// GuidOperator
				orders.setGuidCustomer(cursor.getString(5)); // Orders's
																// GuidCustomer
				orders.setNameCustomer(cursor.getString(6)); // Orders's
																// NameCustomer
				orders.setGuidProdScale(cursor.getString(7)); // Orders's
																// GuidProdScale
				orders.setNameProdScale(cursor.getString(8)); // Orders's
																// NameProdScale
				orders.setQuantity(cursor.getString(9)); // Orders's Quantity
				orders.setFee(cursor.getString(10)); // Orders's getFee
				orders.setLocalDate(cursor.getString(11)); // Orders's LocalDate
				orders.setLocalTime(cursor.getString(12)); // Orders's LocalTime
				orders.setGuidReseler(cursor.getString(13)); // Orders's
																// GuidReseler
				orders.setSended(cursor.getString(14)); // Orders's Sended
				orders.setLocalDateNumber(cursor.getString(15)); // Orders's
																	// LocalDateNumber
				
				orders.setQuantityInvc(cursor.getString(16));

				// Adding Orders to list
				OrdersList.add(orders);
			} while (cursor.moveToNext());
		}

		// return order list
		return OrdersList;
	}

	// Getting Orders with customize condition which is definded by user & press
	// Search Button
	@SuppressLint("NewApi")
	public List<Orders> getOrdersWithSearchCondition(
			String[] SelectCommandString, String GuidOperator) {
		List<Orders> OrdersList = new ArrayList<Orders>();

		String StartDate = SelectCommandString[0];
		String EndDate = SelectCommandString[1];
		String editTxtCustomerName = SelectCommandString[2];
		String editTxtGoodName = SelectCommandString[3];
		String spinnerFactorStr = SelectCommandString[4];
		String spinnerSendedStr = SelectCommandString[5];

		String WhereCondition = "";
		if (StartDate.length() > 0) {
			WhereCondition += " AND " + KEY_DATE_NUMBER + " >= "
					+ StartDate.toString();
		}
		if (EndDate.length() > 0) {
			WhereCondition += " AND " + KEY_DATE_NUMBER + " <= "
					+ EndDate.toString();
		}
		if (editTxtCustomerName.length() > 0) {
			WhereCondition += " AND " + KEY_NAME_CUSTOMER + " LIKE '"
					+ editTxtCustomerName.toString() + "%'";
		}
		if (editTxtGoodName.length() > 0) {
			WhereCondition += " AND " + KEY_NAME_PROD_SCALE + " LIKE '"
					+ editTxtGoodName.toString() + "%'";
		}
		if (spinnerFactorStr.length() > 0) {
			WhereCondition += " AND " + KEY_TYPE_INVC_ID + " = '"
					+ spinnerFactorStr.toString() + "'";
		}
		if (spinnerSendedStr.length() > 0) {
			WhereCondition += " AND " + KEY_SENDED + " = '"
					+ spinnerSendedStr.toString() + "'";
		}
		// Select Query
		String selectQuery = "SELECT  * FROM " + TABLE_ORDERS + " WHERE "
				+ KEY_ID + " IS NOT NULL " + "   AND  " + KEY_GUID_OPERATOR
				+ " ='" + GuidOperator.toUpperCase() + "' " + WhereCondition;

		SQLiteDatabase db = this.getWritableDatabase();
		Cursor cursor = db.rawQuery(selectQuery, null);

		// looping through all rows and adding to list
		if (cursor.moveToFirst()) {
			do {
				Orders orders = new Orders();
				orders.setID(Integer.parseInt(cursor.getString(0))); // Orders's
																		// ID
				orders.setGuidRequest(cursor.getString(1)); // Orders's
															// GuidRequest
				orders.setGuidInvcRequest(cursor.getString(2)); // Orders's
																// GuidInvcRequest
				orders.setTypeInvcId(cursor.getString(3)); // Orders's
															// TypeInvcId
				orders.setGuidOperator(cursor.getString(4)); // Orders's
																// GuidOperator
				orders.setGuidCustomer(cursor.getString(5)); // Orders's
																// GuidCustomer
				orders.setNameCustomer(cursor.getString(6)); // Orders's
																// NameCustomer
				orders.setGuidProdScale(cursor.getString(7)); // Orders's
																// GuidProdScale
				orders.setNameProdScale(cursor.getString(8)); // Orders's
																// NameProdScale
				orders.setQuantity(cursor.getString(9)); // Orders's Quantity
				orders.setFee(cursor.getString(10)); // Orders's getFee
				orders.setLocalDate(cursor.getString(11)); // Orders's LocalDate
				orders.setLocalTime(cursor.getString(12)); // Orders's LocalTime
				orders.setGuidReseler(cursor.getString(13)); // Orders's
																// GuidReseler
				orders.setSended(cursor.getString(14)); // Orders's Sended
				orders.setLocalDateNumber(cursor.getString(15)); // Orders's
																	// LocalDateNumber
				orders.setQuantityInvc(cursor.getString(16));

				
				// Adding Orders to list
				OrdersList.add(orders);
			} while (cursor.moveToNext());
		}

		// return order list
		return OrdersList;
	}

	// Getting Specific Orders
	public Orders getSpecificOrdersByDateStr(String DateStr, String GuidOperator) {

		// SQLiteDatabase db = this.getReadableDatabase();

		String selectQuery = "SELECT  * FROM " + TABLE_ORDERS + "   WHERE  "
				+ KEY_GUID_OPERATOR + " ='" + GuidOperator.toUpperCase()
				+ "' AND " + KEY_DATE + "='" + DateStr + "'";

		SQLiteDatabase db = this.getWritableDatabase();
		Cursor cursor = db.rawQuery(selectQuery, null);

		/*
		 * Cursor cursor = db.query(TABLE_ORDERS, new String[] { KEY_ID,
		 * KEY_GUID_REQ, KEY_GUID_INVC_REQ, KEY_TYPE_INVC_ID, KEY_GUID_OPERATOR,
		 * KEY_GUID_CUSTOMER, KEY_NAME_CUSTOMER, KEY_GUID_PROD_SCALE,
		 * KEY_NAME_PROD_SCALE, KEY_QUANTITY, KEY_FEE, KEY_DATE, KEY_TIME,
		 * KEY_GUID_RESELER, KEY_SENDED, KEY_DATE_NUMBER }, KEY_DATE + "=?", new
		 * String[] { DateStr }, null, null, null, null);
		 */

		Object C = cursor.getCount();
		if (Integer.parseInt(C.toString()) == 0) {
			Orders orders = new Orders();
			orders.setLocalDateNumber(DateStr);
			return orders;
		}

		if (cursor != null)
			cursor.moveToFirst();

		Orders orders = new Orders(Integer.parseInt(cursor.getString(0)),
				cursor.getString(1), cursor.getString(2), cursor.getString(3),
				cursor.getString(4), cursor.getString(5), cursor.getString(6),
				cursor.getString(7), cursor.getString(8), cursor.getString(9),
				cursor.getString(10), cursor.getString(11),
				cursor.getString(12), cursor.getString(13),
				cursor.getString(14), cursor.getString(15), cursor.getString(16));
		// return orders
		return orders;

	}

	// Updating single Orders to Sended Mode
	public int updateOrderToSendedMode(Orders orders) {
		SQLiteDatabase db = this.getWritableDatabase();

		ContentValues values = new ContentValues();
		values.put(KEY_SENDED, "true"); // orders sended!

		// updating row
		return db.update(TABLE_ORDERS, values, KEY_GUID_REQ + " = ?",
				new String[] { String.valueOf(orders.getGuidRequest()) });
	}

	// Getting Orders Count
	public int getOrdersCount(String GuidOperator) {
		String countQuery = "SELECT  * FROM " + TABLE_ORDERS + "   WHERE  "
				+ KEY_GUID_OPERATOR + " = '" + GuidOperator.toUpperCase()
				+ "' AND " + KEY_SENDED + "='false'";
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor cursor = db.rawQuery(countQuery, null);

		cursor.moveToLast();
		int Orderscount = cursor.getCount();
		cursor.close();
		db.close(); // Closing database connection

		// return count
		return Orderscount;
	}

	// Deleting single Order
	public void deleteOrder(Orders order) {
		SQLiteDatabase db = this.getWritableDatabase();
		db.delete(TABLE_ORDERS, KEY_GUID_REQ + " = ?",
				new String[] { String.valueOf(order.getGuidRequest()) });
		db.close();
	}

	// ------------------------ "Operator" table methods ----------------//
	// Adding new contact
	void addOperator(Operator operator) {
		SQLiteDatabase db = this.getWritableDatabase();

		ContentValues values = new ContentValues();
		values.put(KEY_GUID, operator.getGuid()); // Operator's Guid
		values.put(KEY_FULL_NAME, operator.getFullName()); // Operator'sFullName
		values.put(KEY_USER_NAME, operator.getUserName()); // Operator's
															// UserName
		values.put(KEY_PASSWORD, operator.getPassword()); // Operator's Password
		values.put(KEY_RESELER, operator.getReseler()); // Operator's Reseler
		values.put(KEY_BRANCHE, operator.getBranche()); // Operator's Branche

		// Inserting Row
		db.insert(TABLE_OPERATOR, null, values);
		db.close(); // Closing database connection
	}

	// Getting All Operators
	@SuppressLint("NewApi")
	public List<Operator> getAllOperators() {
		List<Operator> operatorsList = new ArrayList<Operator>();
		// Select All Query
		String selectQuery = "SELECT  * FROM " + TABLE_OPERATOR + " ORDER BY "
				+ KEY_FULL_NAME;

		SQLiteDatabase db = this.getWritableDatabase();
		Cursor cursor = db.rawQuery(selectQuery, null);

		// looping through all rows and adding to list
		if (cursor.moveToFirst()) {
			do {
				Operator operator = new Operator();
				operator.setGuid(cursor.getString(0));
				operator.setFullName(cursor.getString(1));
				operator.setUserName(cursor.getString(2));
				operator.setReseler(cursor.getString(3));
				operator.setBranche(cursor.getString(4));

				// Adding operator to list
				operatorsList.add(operator);
			} while (cursor.moveToNext());
		}

		db.close(); // Closing database connection
		// return operators list
		return operatorsList;
	}

	// Getting Spesific Operator
	public List<Operator> getSpecificOperatorByFullName(String FullName) {

		List<Operator> operatorsList = new ArrayList<Operator>();
		// Select Query
		String selectQuery = "SELECT  * FROM " + TABLE_OPERATOR + "   WHERE  "
				+ KEY_FULL_NAME + " LIKE '" + FullName.toString() + "%'";
		SQLiteDatabase db = this.getWritableDatabase();
		Cursor cursor = db.rawQuery(selectQuery, null);

		// looping through all rows and adding to list
		if (cursor.moveToFirst()) {
			do {
				Operator operator = new Operator();
				operator.setGuid(cursor.getString(0));
				operator.setFullName(cursor.getString(1));
				operator.setUserName(cursor.getString(2));
				operator.setReseler(cursor.getString(3));
				operator.setBranche(cursor.getString(4));

				// Adding operator to list
				operatorsList.add(operator);
			} while (cursor.moveToNext());
		}

		db.close(); // Closing database connection
		// return operators list
		return operatorsList;

	}

	// Getting Specific Operator
	public Operator getSpecificOperator(String UserName, String Password) {

		SQLiteDatabase db = this.getReadableDatabase();

		String selectQuery = "SELECT  * FROM " + TABLE_OPERATOR + "   WHERE  "
				+ KEY_USER_NAME + "='" + UserName.toString() + "' AND "
				+ KEY_PASSWORD + "='" + Password.toString() + "'";

		Cursor cursor = db.rawQuery(selectQuery, null);

		Object C = cursor.getCount();
		if (Integer.parseInt(C.toString()) == 0) {
			return null;
		}

		if (cursor != null)
			cursor.moveToFirst();

		Operator operator = new Operator(cursor.getString(0),
				cursor.getString(1), cursor.getString(2), cursor.getString(3),
				cursor.getString(4), cursor.getString(5));
		// return operator
		return operator;

	}

	// Deleting All items of Operator table
	public void deleteAllOperators() {
		SQLiteDatabase db = this.getWritableDatabase();
		db.delete(TABLE_OPERATOR, null, null);
		db.close();
	}

	// ------------------------ "DBConnection" table methods ----------------//
	// Adding new DBConnection
	void addDBConnection(DBConnection dbconnection) {
		SQLiteDatabase db = this.getWritableDatabase();

		ContentValues values = new ContentValues();
		values.put(KEY_ID, dbconnection.getID()); // dbconnection's DatabaseName
		values.put(KEY_DataBaseMAIN, dbconnection.getDataBaseMain()); // dbconnection's
																		// DatabaseMain
		values.put(KEY_DataBaseANDROID, dbconnection.getDataBaseAndroid()); // dbconnection's
																			// DatabaseAndroid
		values.put(KEY_USER_NAME, dbconnection.getUserName()); // dbconnection's
																// UserName
		values.put(KEY_PASSWORD, dbconnection.getPassword()); // dbconnection's
																// Password
		values.put(KEY_IP_LOCAL, dbconnection.getIPLocal()); // dbconnection's
																// IpLocal
		values.put(KEY_IP_REMOTE, dbconnection.getIPRemote()); // dbconnection's
																// IpRemote

		// Inserting Row
		db.insert(TABLE_DBCONNECTION, null, values);
		db.close(); // Closing database connection
	}

	// Deleting All items of DBConnection table
	public void deleteAllDBConnection() {
		SQLiteDatabase db = this.getWritableDatabase();
		db.delete(TABLE_DBCONNECTION, null, null);
		db.close();
	}

	// Getting Specific DBConnection
	public DBConnection getDBConnection() {

		SQLiteDatabase db = this.getReadableDatabase();

		String selectQuery = "SELECT  * FROM " + TABLE_DBCONNECTION;

		Cursor cursor = db.rawQuery(selectQuery, null);

		Object C = cursor.getCount();
		if (Integer.parseInt(C.toString()) == 0) {
			return null;
		}

		if (cursor != null)
			cursor.moveToFirst();

		DBConnection dbconnection = new DBConnection(cursor.getString(0),
				cursor.getString(1), cursor.getString(2), cursor.getString(3),
				cursor.getString(4), cursor.getString(5), cursor.getString(6));
		// return operator
		return dbconnection;

	}

	// ------------------------ "Task" table methods ----------------//

	// Adding new Task
	void addTasks(Tasks task) {
		SQLiteDatabase db = this.getWritableDatabase();

		ContentValues values = new ContentValues();
		// values.put(KEY_TASK_ID, null);
		values.put(KEY_TASK_GUID, task.getGuid());
		values.put(KEY_TASK_TITLE, task.getTitle());
		values.put(KEY_TASK_NOTE, task.getNote());
		values.put(KEY_TASK_TYPE_TASK_ID, task.getTypeTaskId());
		values.put(KEY_TASK_TYPE_TASK_NAME, task.getTypeTaskName());
		values.put(KEY_TASK_TYPE_PRIORITY_ID, task.getTypePriorityId());
		values.put(KEY_TASK_TYPE_PRIORITY_NAME, task.getTypePriorityName());
		values.put(KEY_TASK_CLOSED, task.getClosed());
		values.put(KEY_TASK_COMMENT, task.getComment());

		// Inserting Row
		db.insert(TABLE_TASK, null, values);
		db.close(); // Closing database connection
	}

	// Getting All Tasks
	@SuppressLint("NewApi")
	public List<Tasks> getAllTasks(String Closed, String Title) {
		List<Tasks> tasksList = new ArrayList<Tasks>();
		// Select All Query
		String selectQuery = "SELECT  * FROM " + TABLE_TASK + " WHERE  "
				+ KEY_TASK_ID + " IS NOT NULL ";

		if (Closed.length() > 0) {
			selectQuery = selectQuery + " AND  " + KEY_TASK_CLOSED + " ='"
					+ Closed + "' ";
		}

		if (Title.length() > 0) {
			selectQuery = selectQuery + " AND  " + KEY_TASK_TITLE + " LIKE '"
					+ Title + "%' ";
		}

		selectQuery = selectQuery + "  ORDER BY " + KEY_TASK_ID;

		SQLiteDatabase db = this.getWritableDatabase();
		Cursor cursor = db.rawQuery(selectQuery, null);

		// looping through all rows and adding to list
		if (cursor.moveToFirst()) {
			do {
				Tasks task = new Tasks();
				task.setID(cursor.getString(0));
				task.setGuid(cursor.getString(1));
				task.setTitle(cursor.getString(2));
				task.setNote(cursor.getString(3));
				task.setTypeTaskId(cursor.getString(4));
				task.setTypeTaskName(cursor.getString(5));
				task.setTypePriorityId(cursor.getString(6));
				task.setTypePriorityName(cursor.getString(7));
				task.setClosed(cursor.getString(8));
				task.setComment(cursor.getString(9));

				// Adding task to list
				tasksList.add(task);
			} while (cursor.moveToNext());
		}

		// return tasks list
		return tasksList;
	}

	public Tasks getSpecificTasksByGuid(String Guid) {

		// List<Contact> contactList = new ArrayList<Contact>();
		// Select All Query
		String selectQuery = "SELECT  * FROM " + TABLE_TASK + "   WHERE  "
				+ KEY_TASK_GUID + " = '" + Guid.toString() + "'";

		SQLiteDatabase db = this.getWritableDatabase();
		Cursor cursor = db.rawQuery(selectQuery, null);

		Object C = cursor.getCount();
		if (Integer.parseInt(C.toString()) == 0) {
			return null;
		}
		if (cursor != null)
			cursor.moveToFirst();

		Tasks task = new Tasks();
		task.setID(cursor.getString(0));
		task.setGuid(cursor.getString(1));
		task.setTitle(cursor.getString(2));
		task.setNote(cursor.getString(3));
		task.setTypeTaskId(cursor.getString(4));
		task.setTypeTaskName(cursor.getString(5));
		task.setTypePriorityId(cursor.getString(6));
		task.setTypePriorityName(cursor.getString(7));
		task.setClosed(cursor.getString(8));
		task.setComment(cursor.getString(9));

		db.close(); // Closing database connection
		// return task
		return task;

	}

	// Updating single Task
	public int updateTasks(Tasks task) {
		SQLiteDatabase db = this.getWritableDatabase();

		ContentValues values = new ContentValues();
		values.put(KEY_TASK_ID, task.getID());
		values.put(KEY_TASK_GUID, task.getGuid());
		values.put(KEY_TASK_TITLE, task.getTitle());
		values.put(KEY_TASK_NOTE, task.getNote());
		values.put(KEY_TASK_TYPE_TASK_ID, task.getTypeTaskId());
		values.put(KEY_TASK_TYPE_TASK_NAME, task.getTypeTaskName());
		values.put(KEY_TASK_TYPE_PRIORITY_ID, task.getTypePriorityId());
		values.put(KEY_TASK_TYPE_PRIORITY_NAME, task.getTypePriorityName());
		values.put(KEY_TASK_CLOSED, task.getClosed());
		values.put(KEY_TASK_COMMENT, task.getComment());

		// updating row
		return db.update(TABLE_TASK, values, KEY_TASK_GUID + " = ?",
				new String[] { String.valueOf(task.getGuid()) });
	}

	// Deleting single Tasks
	public void deleteTasksByGuid(String Guid) {
		SQLiteDatabase db = this.getWritableDatabase();
		db.delete(TABLE_TASK, KEY_TASK_GUID + " = ?",
				new String[] { Guid.toUpperCase() });
		db.close();
	}

	// Deleting All items of Tasks table
	public void deleteAllTasks() {
		SQLiteDatabase db = this.getWritableDatabase();
		db.delete(TABLE_TASK, null, null);
		db.close();
	}

	// ------------------------ "Inbox" table methods ----------------//

	// Adding new Inbox
	void addInbox(Inboxes inbox) {
		SQLiteDatabase db = this.getWritableDatabase();

		ContentValues values = new ContentValues();
		// values.put(KEY_INBOX_ID, null);
		values.put(KEY_INBOX_GUID, inbox.getGuid());
		values.put(KEY_INBOX_GUID_TASK, inbox.getTaskGuid());
		values.put(KEY_INBOX_TITLE_TASK, inbox.getTaskTitle());
		values.put(KEY_INBOX_GUID_OPERATOR, inbox.getOperatorGuid());
		values.put(KEY_INBOX_NAME_OPERATORE, inbox.getOperatorName());
		values.put(KEY_INBOX_DATE, inbox.getLocalDate());
		values.put(KEY_INBOX_TIME, inbox.getLocalTime());
		values.put(KEY_INBOX_VISITED, inbox.getVisited());
		values.put(KEY_INBOX_REFERED, inbox.getRefered());

		// Inserting Row
		db.insert(TABLE_INBOX, null, values);
		db.close(); // Closing database connection
	}

	// Getting All Inbox
	@SuppressLint({ "NewApi", "DefaultLocale" })
	public List<Inboxes> getAllInboxes(String GuidOperator) {
		List<Inboxes> inboxList = new ArrayList<Inboxes>();
		// Select All Query
		String selectQuery = "SELECT  * FROM " + TABLE_INBOX + " WHERE  "
				+ KEY_INBOX_GUID_OPERATOR + " = '" + GuidOperator.toUpperCase()
				+ "'" + "  ORDER BY " + KEY_INBOX_ID;

		SQLiteDatabase db = this.getWritableDatabase();
		Cursor cursor = db.rawQuery(selectQuery, null);

		// looping through all rows and adding to list
		if (cursor.moveToFirst()) {
			do {
				Inboxes inbox = new Inboxes();
				inbox.setID(cursor.getString(0));
				inbox.setGuid(cursor.getString(1));
				inbox.setTaskGuid(cursor.getString(2));
				inbox.setTaskTitle(cursor.getString(3));
				inbox.setOperatorGuid(cursor.getString(4));
				inbox.setOperatorName(cursor.getString(5));
				inbox.setLocalDate(cursor.getString(6));
				inbox.setLocalTime(cursor.getString(7));
				inbox.setVisited(cursor.getString(8));
				inbox.setRefered(cursor.getString(9));

				// Adding inbox to list
				inboxList.add(inbox);
			} while (cursor.moveToNext());
		}

		// return Inbox list
		return inboxList;
	}

	public Inboxes getSpecificInboxByGuid(String Guid) {

		// List<Contact> contactList = new ArrayList<Contact>();
		// Select All Query
		String selectQuery = "SELECT  * FROM " + TABLE_INBOX + "   WHERE  "
				+ KEY_INBOX_GUID + " = '" + Guid.toString() + "'";

		SQLiteDatabase db = this.getWritableDatabase();
		Cursor cursor = db.rawQuery(selectQuery, null);

		Object C = cursor.getCount();
		if (Integer.parseInt(C.toString()) == 0) {
			return null;
		}
		if (cursor != null)
			cursor.moveToFirst();

		Inboxes inbox = new Inboxes();
		inbox.setID(cursor.getString(0));
		inbox.setGuid(cursor.getString(1));
		inbox.setTaskGuid(cursor.getString(2));
		inbox.setTaskTitle(cursor.getString(3));
		inbox.setOperatorGuid(cursor.getString(4));
		inbox.setOperatorName(cursor.getString(5));
		inbox.setLocalDate(cursor.getString(6));
		inbox.setLocalTime(cursor.getString(7));
		inbox.setVisited(cursor.getString(8));
		inbox.setRefered(cursor.getString(9));

		db.close(); // Closing database connection
		// return Inbox
		return inbox;

	}

	// Updating single Inobx
	public int updateInbox(Inboxes inbox) {
		SQLiteDatabase db = this.getWritableDatabase();

		ContentValues values = new ContentValues();
		values.put(KEY_INBOX_ID, inbox.getID());
		values.put(KEY_INBOX_GUID, inbox.getGuid());
		values.put(KEY_INBOX_GUID_TASK, inbox.getTaskGuid());
		values.put(KEY_INBOX_TITLE_TASK, inbox.getTaskTitle());
		values.put(KEY_INBOX_GUID_OPERATOR, inbox.getOperatorGuid());
		values.put(KEY_INBOX_NAME_OPERATORE, inbox.getOperatorName());
		values.put(KEY_INBOX_DATE, inbox.getLocalDate());
		values.put(KEY_INBOX_TIME, inbox.getLocalTime());
		values.put(KEY_INBOX_VISITED, inbox.getVisited());
		values.put(KEY_INBOX_REFERED, inbox.getRefered());

		// updating row
		return db.update(TABLE_INBOX, values, KEY_INBOX_GUID + " = ?",
				new String[] { String.valueOf(inbox.getGuid()) });
	}

	// Deleting All items of Inboxes table
	public void deleteAllInboxes() {
		SQLiteDatabase db = this.getWritableDatabase();
		db.delete(TABLE_INBOX, null, null);
		db.close();
	}

	// ------------------------ "TaskType" table methods ----------------//

	// Adding new TaskType
	void addTaskTypes(TaskTypes taskType) {
		SQLiteDatabase db = this.getWritableDatabase();

		ContentValues values = new ContentValues();
		values.put(KEY_ID, taskType.getID());
		values.put(KEY_NAME, taskType.getName());

		// Inserting Row
		db.insert(TABLE_TASK_TYPE, null, values);
		db.close(); // Closing database connection
	}

	// Getting All TaskTypes
	@SuppressLint("NewApi")
	public List<TaskTypes> getAllTaskTypes() {
		List<TaskTypes> tasktypesList = new ArrayList<TaskTypes>();
		// Select All Query
		String selectQuery = "SELECT  * FROM " + TABLE_TASK_TYPE + " ORDER BY "
				+ KEY_ID;

		SQLiteDatabase db = this.getWritableDatabase();
		Cursor cursor = db.rawQuery(selectQuery, null);

		// looping through all rows and adding to list
		if (cursor.moveToFirst()) {
			do {
				TaskTypes tasktype = new TaskTypes();
				tasktype.setID(cursor.getString(0));
				tasktype.setName(cursor.getString(1));

				// Adding TaskTypes to list
				tasktypesList.add(tasktype);
			} while (cursor.moveToNext());
		}

		// return TaskTypes list
		return tasktypesList;
	}

	// Deleting All items of TaskType table
	public void deleteAllTaskTypes() {
		SQLiteDatabase db = this.getWritableDatabase();
		db.delete(TABLE_TASK_TYPE, null, null);
		db.close();
	}

	// ------------------------ "PriorityType" table methods ----------------//

	// Adding new PriorityType
	void addPriorityTypes(PriorityTypes priorityType) {
		SQLiteDatabase db = this.getWritableDatabase();

		ContentValues values = new ContentValues();
		values.put(KEY_ID, priorityType.getID());
		values.put(KEY_NAME, priorityType.getName());

		// Inserting Row
		db.insert(TABLE_PRIORITY_TYPE, null, values);
		db.close(); // Closing database connection
	}

	// Getting All PriorityTypes
	@SuppressLint("NewApi")
	public List<PriorityTypes> getAllPriorityTypes() {
		List<PriorityTypes> prioritytypesList = new ArrayList<PriorityTypes>();
		// Select All Query
		String selectQuery = "SELECT  * FROM " + TABLE_PRIORITY_TYPE
				+ " ORDER BY " + KEY_ID;

		SQLiteDatabase db = this.getWritableDatabase();
		Cursor cursor = db.rawQuery(selectQuery, null);

		// looping through all rows and adding to list
		if (cursor.moveToFirst()) {
			do {
				PriorityTypes prioritytype = new PriorityTypes();
				prioritytype.setID(cursor.getString(0));
				prioritytype.setName(cursor.getString(1));

				// Adding PriorityTypes to list
				prioritytypesList.add(prioritytype);
			} while (cursor.moveToNext());
		}

		// return PriorityTypes list
		return prioritytypesList;
	}

	// Deleting All items of PriorityType table
	public void deleteAllPriorityTypes() {
		SQLiteDatabase db = this.getWritableDatabase();
		db.delete(TABLE_PRIORITY_TYPE, null, null);
		db.close();
	}

	// ------------------------ "PriceList" table methods ----------------//

	// Adding new PriceList
	void addPriceList(PriceList pricelist) {
		SQLiteDatabase db = this.getWritableDatabase();

		ContentValues values = new ContentValues();
		values.put(KEY_PRICELIST_ID, pricelist.getID());
		values.put(KEY_PRICELIST_GUID_CUSTOMER_CATEGORY,
				pricelist.getGuidCustomerCategory());
		values.put(KEY_PRICELIST_GUID_PROD_SCALE, pricelist.getGuidProdScale());
		values.put(KEY_PRICELIST_GUID_SCALE, pricelist.getGuidScale());
		values.put(KEY_PRICELIST_NAME_PUBLIC, pricelist.getNamePublic());
		values.put(KEY_PRICELIST_TYPE_PRICE_ID, pricelist.getTypePriceID());
		values.put(KEY_PRICELIST_CUSTOMER_CATEGORY,
				pricelist.getCustomerCategory());
		values.put(KEY_PRICELIST_TYPE_PRICE_TITLE,
				pricelist.getTypePriceTitle());
		values.put(KEY_PRICELIST_LOCAL_DATE, pricelist.getLocalDate());
		values.put(KEY_PRICELIST_TYPE_REGISTRATION_ID,
				pricelist.getTypeRegistrationID());
		values.put(KEY_PRICELIST_FEE, pricelist.getFee());

		// Inserting Row
		db.insert(TABLE_PRICELIST, null, values);
		db.close(); // Closing database connection
	}

	public PriceList getSpecificPriceList(String GuidCustomerCategory,
			String GuidProdScale) {

		// Select All Query
		String selectQuery = "SELECT  * FROM " + TABLE_PRICELIST + "  WHERE  "
				+ KEY_PRICELIST_GUID_PROD_SCALE + " = '"
				+ GuidProdScale.toString().toUpperCase() + "' AND "
				+ KEY_PRICELIST_GUID_CUSTOMER_CATEGORY + " = '"
				+ GuidCustomerCategory.toString().toUpperCase() + "' " 
				+ " ORDER BY " + KEY_PRICELIST_ID + " DESC  LIMIT 1";

		SQLiteDatabase db = this.getWritableDatabase();
		Cursor cursor = db.rawQuery(selectQuery, null);

		Object C = cursor.getCount();
		if (Integer.parseInt(C.toString()) == 0) {
			return null;
		}
		if (cursor != null)
			cursor.moveToFirst();

		PriceList pricelist = new PriceList(cursor.getString(0),
				cursor.getString(1), cursor.getString(2), cursor.getString(3),
				cursor.getString(4), cursor.getString(5), cursor.getString(6),
				cursor.getString(7), cursor.getString(8), cursor.getString(9),
				cursor.getString(10));

		db.close(); // Closing database connection
		// return pricelist
		return pricelist;

	}
	
	
	public PriceList getSpecificPriceList(
			String GuidProdScale) {

		// Select All Query
		String selectQuery = "SELECT  * FROM " + TABLE_PRICELIST + "  WHERE  "
				+ KEY_PRICELIST_GUID_PROD_SCALE + " = '"
				+ GuidProdScale.toString().toUpperCase() + "' "
				+ " ORDER BY " + KEY_PRICELIST_ID + " DESC LIMIT 1";

		SQLiteDatabase db = this.getWritableDatabase();
		Cursor cursor = db.rawQuery(selectQuery, null);

		Object Count = cursor.getCount();
		if (Integer.parseInt(Count.toString()) == 0) {
			return null;
		}
		if (cursor != null)
			cursor.moveToFirst();

		PriceList pricelist = new PriceList(cursor.getString(0),
				cursor.getString(1), cursor.getString(2), cursor.getString(3),
				cursor.getString(4), cursor.getString(5), cursor.getString(6),
				cursor.getString(7), cursor.getString(8), cursor.getString(9),
				cursor.getString(10));

		db.close(); // Closing database connection
		// return pricelist
		return pricelist;

	}

	// Deleting All items of pricelist table
	public void deleteAllPriceList() {
		SQLiteDatabase db = this.getWritableDatabase();
		db.delete(TABLE_PRICELIST, null, null);
		db.close();
	}

	// ///////////////////////////// THE END ////////////////////////////
}
