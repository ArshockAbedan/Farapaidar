package com.farapaidar.firstmenu001;

import java.io.File;
import java.io.FileOutputStream;
import java.io.StringWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xmlpull.v1.XmlSerializer;
import android.os.Bundle;
import android.os.Environment;
import android.os.StrictMode;
import android.annotation.SuppressLint;
import android.app.DialogFragment;
import android.content.Context;
import android.util.Log;
import android.util.Xml;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class LocalSendReceiver extends DialogFragment {

	// XML node keys Common of Products + Customers
	static final String KEY_ITEM = "Product"; // parent node
	static final String KEY_GUID = "guid";
	static final String KEY_ID = "id";
	static final String KEY_NAME = "name";

	// XML node keys Products
	static final String KEY_STOCK = "stock";
	static final String KEY_COUNT_UNIT = "count_unit";
	static final String KEY_PRICE = "price";
	static final String KEY_PRICE_TYPE = "price_type";
	static final String KEY_Q_BOX = "q_box";

	// XML node keys Customers
	static final String KEY_REGION_NAME = "region_name";
	static final String KEY_PHON_NUM_1 = "phone_number1";
	static final String KEY_PHON_NUM_2 = "phone_number2";
	static final String KEY_PHON_NUM_3 = "phone_number3";
	static final String KEY_PHON_NUM_4 = "phone_number4";
	static final String KEY_ADDRESS = "address";
	static final String KEY_MOBILE = "mobile";
	static final String KEY_FAX = "fax";
	static final String KEY_DEPOSIT = "deposit";
	static final String KEY_DETECTION = "detection";
	static final String KEY_GUID_VISITOR = "guid_visitor";
	static final String KEY_GUID_CUSTOMER_CATEGORY = "guidCustomer_category";

	static LocalSendReceiver newInstance() {
		return new LocalSendReceiver();
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View v = inflater.inflate(R.layout.activity_local_send_receiver,
				container, false);

		// Set onClickEvent for BtnContacts
		Button btnUpCt = (Button) v.findViewById(R.id.btnContactslocal);
		btnUpCt.setText("بروز رسانی اطلاعات مشتریان");
		btnUpCt.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				v.setEnabled(false);
				Context contaxt = getActivity();
				View vp = (View) v.getParent();
				UpdateContacts(vp, contaxt);
				v.setEnabled(true);
				// v.refreshDrawableState();
			}
		});

		// Set onClickEvent for BtnGoodss
		Button btnUpGd = (Button) v.findViewById(R.id.btnGoodslocal);
		btnUpGd.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				v.setEnabled(false);
				Context contaxt = getActivity();
				View vp = (View) v.getParent();
				UpdateGoods(vp, contaxt);
				v.setEnabled(true);
				// v.refreshDrawableState();
			}
		});

		// Set onClickEvent for BtnPriceList
		Button btnUpPricelist = (Button) v.findViewById(R.id.btnPriceListlocal);
		btnUpPricelist.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				v.setEnabled(false);
				Context contaxt = getActivity();
				View vp = (View) v.getParent();
				UpdatePriceList(vp, contaxt);
				v.setEnabled(true);

			}
		});

		// Set onClickEvent for BtnOrders
		Button btnInsOrders = (Button) v.findViewById(R.id.btnOrderslocal);
		// btnInsOrders.setText("درج اطلاعات ثبت سفارش");
		btnInsOrders.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				v.setEnabled(false);
				Context contaxt = getActivity();
				View vp = (View) v.getParent();
				InsertOrders(vp, contaxt);
				v.setEnabled(true);
				// v.refreshDrawableState();
			}
		});

		return v;
	}

	
	
	
	

	// /////////////////////////////////////////////////////////
	public void UpdateContacts(View v, Context contaxt) {
		// Visibling Buttons
		Button btnUpCt = (Button) v.findViewById(R.id.btnContactslocal);
		btnUpCt.setEnabled(false);
		Button btnUpGd = (Button) v.findViewById(R.id.btnGoodslocal);
		btnUpGd.setEnabled(false);

		// Declare the JDBC objects.
		Connection DbConnContacts = null;
		Statement stmtContacts = null;
		ResultSet resultSetContacts = null;

		try {

			DatabaseHandler db = new DatabaseHandler(this.getActivity());

			DBConnection dbconnection = db.getDBConnection();
			// Establish the connection.
			Class.forName("net.sourceforge.jtds.jdbc.Driver").newInstance();

			// String username = "sa";
			// String password = "prpol1393";

			String username = dbconnection.getUserName();
			String password = dbconnection.getPassword();
			// String username = "Sa";
			// String password = "1";

			if (android.os.Build.VERSION.SDK_INT > 9) {
				StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
						.permitAll().build();
				StrictMode.setThreadPolicy(policy);
			}

			DbConnContacts = DriverManager
					.getConnection("jdbc:jtds:sqlserver://"
							+ dbconnection.getIPLocal() + ":1433/"
							+ dbconnection.getDataBaseMain() + ";user="
							+ username + ";password=" + password + ";");

			// Connection DbConn =
			// DriverManager.getConnection("jdbc:jtds:sqlserver://192.168.1.254:1433/DBSA;user="
			// + username + ";password=" + password
			// +";Network Library=DBMSSOCN;");

			// username = "Sa";
			// password = "1";
			// DbConnContacts =
			// DriverManager.getConnection("jdbc:jtds:sqlserver://192.168.1.254:1433/FPDBLite;user="
			// + username + ";password=" + password +";");

			Log.w("Connection", "open");

			stmtContacts = DbConnContacts.createStatement();
			// ResultSet reset =
			// stmt.executeQuery(" INSERT INTO TEST (id,Title) Values (1,N'عارف');");

			// resultSetContacts =
			// stmtContacts.executeQuery(" SELECT * FROM vw_Apk_Cust WHERE GuidVisitor = '"
			// + MainMenuActivity.OperatorGuid.toLowerCase() + "'");
			resultSetContacts = stmtContacts
					.executeQuery(" SELECT * FROM vw_Apk_Cust  WHERE  GuidVisitor = '" + MainMenuActivity.OperatorGuid.toLowerCase() + "' AND GuidCustomer_Category is NOT NULL ");

			ArrayList<Contact> contacts = new ArrayList<Contact>();

			while (resultSetContacts.next()) {
				Contact ct = new Contact();
				if (resultSetContacts.getString(1) != null
						|| resultSetContacts.getString(1).trim().length() != 0) {
					ct.setGuid(resultSetContacts.getString(1).toString());
				} else {
					ct.setGuid("0");
				}
				if (resultSetContacts.getString(2) != null
						|| resultSetContacts.getString(2).trim().length() != 0) {
					ct.setID(resultSetContacts.getString(2).toString());
				} else {
					ct.setID("0");
				}

				String name = "-";
				try {
					if (resultSetContacts.getString(3).trim().length() != 0) {
						name = resultSetContacts.getString(3).toString();
					}
				} catch (Exception e) {
					// TODO: handle exception
				}
				ct.setName(name);

				String regionName = "-";
				try {
					if (resultSetContacts.getString(4).trim().length() != 0) {
						regionName = resultSetContacts.getString(4).toString();
					}
				} catch (Exception e) {
					// TODO: handle exception
				}
				ct.setRegionName(regionName);

				String phoneNumber1 = "0";
				try {
					if (resultSetContacts.getString(5).trim().length() != 0) {
						phoneNumber1 = resultSetContacts.getString(5)
								.toString();
					}
				} catch (Exception e) {
					// TODO: handle exception
				}
				ct.setPhoneNumber1(phoneNumber1);

				String phoneNumber2 = "0";
				try {
					if (resultSetContacts.getString(6).trim().length() != 0) {
						phoneNumber2 = resultSetContacts.getString(6)
								.toString();
					}
				} catch (Exception e) {
					// TODO: handle exception
				}
				ct.setPhoneNumber2(phoneNumber2);

				String phoneNumber3 = "0";
				try {
					if (resultSetContacts.getString(7).trim().length() != 0) {
						phoneNumber3 = resultSetContacts.getString(7)
								.toString();
					}
				} catch (Exception e) {
					// TODO: handle exception
				}
				ct.setPhoneNumber3(phoneNumber3);

				String phoneNumber4 = "0";
				try {
					if (resultSetContacts.getString(8).trim().length() != 0) {
						phoneNumber4 = resultSetContacts.getString(8)
								.toString();
					}
				} catch (Exception e) {
					// TODO: handle exception
				}
				ct.setPhoneNumber4(phoneNumber4);

				String fax = "-";
				try {
					if (resultSetContacts.getString(9).trim().length() != 0) {
						fax = resultSetContacts.getString(9).toString();
					}
				} catch (Exception e) {
					// TODO: handle exception
				}
				ct.setFax(fax);

				String address = "-";
				try {
					if (resultSetContacts.getString(10).trim().length() != 0) {
						address = resultSetContacts.getString(10).toString();
					}
				} catch (Exception e) {
					// TODO: handle exception
				}
				ct.setAddress(address);

				String mobile = "-";
				try {
					if (resultSetContacts.getString(11).trim().length() != 0) {
						mobile = resultSetContacts.getString(11).toString();
					}
				} catch (Exception e) {
					// TODO: handle exception
				}
				ct.setMobile(mobile);

				if (resultSetContacts.getString(12) != null) {
					// returnedValues[0] = Deposit & returnedValues[1] =
					// Detection
					String[] returnedValues = new String[2];
					returnedValues = SetDetectionFunc(resultSetContacts
							.getString(12).toString());
					ct.setDeposit(returnedValues[0].toString());
					ct.setDetection(returnedValues[1].toString());
				} else {
					ct.setDeposit("-");
				}

				String guidvisitor = "0";
				try {
					if (resultSetContacts.getString(14).trim().length() != 0) {
						guidvisitor = resultSetContacts.getString(14)
								.toString();
					}
				} catch (Exception e) {
					// TODO: handle exception
				}
				ct.setGuidVisitor(guidvisitor);
				
				String guidCustomerCategory = "0";
				try {
					if (resultSetContacts.getString(15).trim().length() != 0) {
						guidCustomerCategory = resultSetContacts.getString(15).toString();
					}
				} catch (Exception e) {
					// TODO: handle exception
				}
				ct.setGuidCustomerCategory(guidCustomerCategory);
				

				contacts.add(ct);
			}

			// deleting exist file
			ReadXMLFile rxf = new ReadXMLFile();
			// rxf.setPath(Environment.getExternalStorageDirectory().toString());
			rxf.setPath("/sdcard/Farapaidar/");
			rxf.setFileName("Customers.xml");
			File file = rxf.ReadFile();
			try {

				if (file != null) {
					file.delete();

				}

			} catch (Exception e) {
				e.printStackTrace();
				Toast.makeText(
						v.getContext(),
						"اختلال در نوشت فایل در حافظه / سیستم قادر به حذف فایل قبلی نیست",
						Toast.LENGTH_LONG);
			}

			// converting data to string prepare for writing to .xml file
			String contactsXmlString = writeXmlContacts(contacts);

			try {
				// writnig customers.xml in SDcard/Farapaidar directory
				if (isExternalStorageReadable() && isExternalStorageWritable()) {
					String stringFilename = "Customers.xml";
					generateFarapaidarProductsOnSD(stringFilename,
							contactsXmlString, contaxt);
				}

			} catch (Exception e) {
				Toast.makeText(v.getContext(), e.toString(), Toast.LENGTH_SHORT)
						.show();
			}

			DbConnContacts.close();
		} catch (Exception e) {
			Log.w("Error connection", "" + e.getMessage());
		}

		// Enabling Buttons
		btnUpCt.setEnabled(true);
		btnUpGd.setEnabled(true);

		// fill Customers table in SQLite by embeded Products.xml file
		FillCustomersInSQLite(v.getContext());

		// Enable ContactFirstPage to load from embeded Customers.xml file
		// ContactFirstPage.flagcontact = false;

	}

	public void UpdateGoods(View v, Context contaxt) {

		// Visibling Buttons
		Button btnUpCt = (Button) v.findViewById(R.id.btnContactslocal);
		btnUpCt.setEnabled(false);
		Button btnUpGd = (Button) v.findViewById(R.id.btnGoodslocal);
		btnUpGd.setEnabled(false);

		// Declare the JDBC objects.
		Connection DbConn = null;
		Statement stmt = null;
		ResultSet resultSet = null;

		try {

			DatabaseHandler db = new DatabaseHandler(contaxt);

			DBConnection dbconnection = db.getDBConnection();
			// Establish the connection.
			Class.forName("net.sourceforge.jtds.jdbc.Driver").newInstance();

			// String username = "sa";
			// String password = "prpol1393";

			String username = dbconnection.getUserName();
			String password = dbconnection.getPassword();
			// String username = "Sa";
			// String password = "1";

			if (android.os.Build.VERSION.SDK_INT > 9) {
				StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
						.permitAll().build();
				StrictMode.setThreadPolicy(policy);
			}

			DbConn = DriverManager.getConnection("jdbc:jtds:sqlserver://"
					+ dbconnection.getIPLocal() + ":1433/"
					+ dbconnection.getDataBaseMain() + ";user=" + username
					+ ";password=" + password + ";");


			Log.w("Connection", "open");

			stmt = DbConn.createStatement();
			// ResultSet reset =
			// stmt.executeQuery(" INSERT INTO TEST (id,Title) Values (1,N'عارف');");

			resultSet = stmt.executeQuery(" SELECT * FROM vw_Apk_Products WHERE  GuidProdScale is NOT NULL AND  VName is NOT NULL AND Len(Code) > 0 ");

			ArrayList<Goods> goods = new ArrayList<Goods>();

			while (resultSet.next()) {
				Goods gd = new Goods();
				if (resultSet.getString(1) != null) {
					gd.setGuid(resultSet.getString(1).toString());
				} else {
					gd.setGuid("");
				}
				if (resultSet.getString(2) != null) {
					gd.setID(resultSet.getString(2).toString());
				} else {
					gd.setID("");
				}
				if (resultSet.getString(3) != null) {
					gd.setGoodName(resultSet.getString(3).toString());
				} else {
					gd.setGoodName("");
				}
				if (resultSet.getString(4) != null) {
					gd.setStock(resultSet.getString(4).toString());
				} else {
					gd.setStock("");
				}
				if (resultSet.getString(5) != null) {
					gd.setCountUnit(resultSet.getString(5).toString());
				} else {
					gd.setCountUnit("");
				}
				if (resultSet.getString(6) != null) {
					gd.setPrice(resultSet.getString(6).toString());
				} else {
					gd.setPrice("");
				}
				if (resultSet.getString(7) != null) {
					gd.setPriceType(resultSet.getString(7).toString());
				} else {
					gd.setPriceType("");
				}
				if (resultSet.getString(8) != null) {
					float qBoxfloat = Float.parseFloat(resultSet.getString(8)
							.toString());
					gd.setQBox(String.valueOf(Math.round(qBoxfloat)));
				} else {
					gd.setQBox("");
				}

				goods.add(gd);
			}

			// deleting exist file
			ReadXMLFile rxf = new ReadXMLFile();
			rxf.setPath(Environment.getExternalStorageDirectory().toString());
			// rxf.setPath("/sdcard/Farapaidar/");
			rxf.setFileName("Products.xml");
			File file = rxf.ReadFile();
			try {

				if (file != null) {
					file.delete();

				}

			} catch (Exception e) {
				e.printStackTrace();
				Toast.makeText(
						v.getContext(),
						"اختلال در نوشت فایل در حافظه / سیستم قادر به حذف فایل قبلی نیست",
						Toast.LENGTH_LONG);
			}

			// converting data to string prepare for writing to .xml file
			String goodsXmlString = writeXmlGoods(goods);

			try {
				// writnig Products.xml in SDcard/Farapaidar directory
				if (isExternalStorageReadable() && isExternalStorageWritable()) {
					String stringFilename = "Products.xml";
					generateFarapaidarProductsOnSD(stringFilename,
							goodsXmlString, contaxt);
				}

			} catch (Exception e) {
				Toast.makeText(v.getContext(), e.toString(), Toast.LENGTH_SHORT)
						.show();
			}

			DbConn.close();
		} catch (Exception e) {
			Toast.makeText(v.getContext(), e.getMessage().toString(),
					Toast.LENGTH_SHORT).show();
			Log.w("Error connection", "" + e.getMessage());
		}

		// Enabling Buttons
		btnUpCt.setEnabled(true);
		btnUpGd.setEnabled(true);

		// fill Products table in SQLite by embeded Products.xml file
		FillProductsInSQLite(v.getContext());

	}


	public void UpdatePriceList(View v, Context contaxt) {

		// Visibling Buttons
		Button btnUpCt = (Button) ((View) v.getParent())
				.findViewById(R.id.btnContactslocal);
		btnUpCt.setEnabled(false);
		Button btnUpGd = (Button) ((View) v.getParent())
				.findViewById(R.id.btnGoodslocal);
		btnUpGd.setEnabled(false);

		// Declare the JDBC objects.
		Connection DbConn = null;
		Statement stmt = null;
		ResultSet resultSet = null;

		try {

			DatabaseHandler db = new DatabaseHandler(contaxt);

			DBConnection dbconnection = db.getDBConnection();
			// Establish the connection.
			Class.forName("net.sourceforge.jtds.jdbc.Driver").newInstance();

			String username = dbconnection.getUserName();
			String password = dbconnection.getPassword();
			// String username = "Sa";
			// String password = "1";

			if (android.os.Build.VERSION.SDK_INT > 9) {
				StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
						.permitAll().build();
				StrictMode.setThreadPolicy(policy);
			}

			DbConn = DriverManager.getConnection("jdbc:jtds:sqlserver://"
					+ dbconnection.getIPLocal() + ":1433/"
					+ dbconnection.getDataBaseMain() + ";user=" + username
					+ ";password=" + password + ";");

			Log.w("Connection", "open");

			stmt = DbConn.createStatement();

			resultSet = stmt
					.executeQuery(" SELECT * FROM vw_Apk_PriceListDetail");

			ArrayList<PriceList> pricelist = new ArrayList<PriceList>();

			while (resultSet.next()) {
				PriceList pl = new PriceList();

				String id = "nullForPricelist";
				try {
					if (resultSet.getString(1).trim().length() != 0) {
						id = resultSet.getString(1).toString();
					}
				} catch (Exception e) {
					// TODO: handle exception
				}
				pl.setId(id);

				String guidCustomerCategory = "nullForPricelist";
				try {
					if (resultSet.getString(2).trim().length() != 0) {
						guidCustomerCategory = resultSet.getString(2)
								.toString();
					}
				} catch (Exception e) {
					// TODO: handle exception
				}
				pl.setGuidCustomerCategory(guidCustomerCategory);

				String guidProdScale = "nullForPricelist";
				try {
					if (resultSet.getString(3).trim().length() != 0) {
						guidProdScale = resultSet.getString(3).toString();
					}
				} catch (Exception e) {
					// TODO: handle exception
				}
				pl.setGuidProdScale(guidProdScale);

				String guidScale = "nullForPricelist";
				try {
					if (resultSet.getString(4).trim().length() != 0) {
						guidScale = resultSet.getString(4).toString();
					}
				} catch (Exception e) {
					// TODO: handle exception
				}
				pl.setGuidScale(guidScale);

				String namePublic = "nullForPricelist";
				try {
					if (resultSet.getString(5).trim().length() != 0) {
						namePublic = resultSet.getString(5).toString();
					}
				} catch (Exception e) {
					// TODO: handle exception
				}
				pl.setNamePublic(namePublic);

				String typePriceID = "nullForPricelist";
				try {
					if (resultSet.getString(6).trim().length() != 0) {
						typePriceID = resultSet.getString(6).toString();
					}
				} catch (Exception e) {
					// TODO: handle exception
				}
				pl.setTypePriceID(typePriceID);

				String customerCategory = "nullForPricelist";
				try {
					if (resultSet.getString(7).trim().length() != 0) {
						customerCategory = resultSet.getString(7).toString();
					}
				} catch (Exception e) {
					// TODO: handle exception
				}
				pl.setCustomerCategory(customerCategory);

				String typePriceTitle = "nullForPricelist";
				try {
					if (resultSet.getString(8).trim().length() != 0) {
						typePriceTitle = resultSet.getString(8).toString();
					}
				} catch (Exception e) {
					// TODO: handle exception
				}
				pl.setTypePriceTitle(typePriceTitle);

				String localDate = "nullForPricelist";
				try {
					if (resultSet.getString(9).trim().length() != 0) {
						localDate = resultSet.getString(9).toString();
					}
				} catch (Exception e) {
					// TODO: handle exception
				}
				pl.setLocalDate(localDate);

				String typeRegistrationID = "nullForPricelist";
				try {
					if (resultSet.getString(10).trim().length() != 0) {
						typeRegistrationID = resultSet.getString(10).toString();
					}
				} catch (Exception e) {
					// TODO: handle exception
				}
				pl.setTypeRegistrationID(typeRegistrationID);
				
				String fee = "nullForPricelist";
				try {
					if (resultSet.getString(11).trim().length() != 0) {
						fee = resultSet.getString(11).toString();
					}
				} catch (Exception e) {
					// TODO: handle exception
				}
				pl.setFee(fee);

				pricelist.add(pl);
			}

			DbConn.close();

			// fill PriceList table in SQLite

			// First delete existing records in PriceList table
			db.deleteAllPriceList();

			// insert new records
			for (PriceList pl : pricelist) {
				db.addPriceList(pl);
			}

		} catch (Exception e) {
			Toast.makeText(v.getContext(), e.getMessage().toString(),
					Toast.LENGTH_SHORT).show();
			Log.w("Error connection", "" + e.getMessage());
		}

		// Enabling Buttons
		btnUpCt.setEnabled(true);
		btnUpGd.setEnabled(true);

	}

	// /////////////////////////////////////////////////////
	public void InsertOrders(View v, Context contaxt) {
		// Visibling Buttons
		Button btnInsOrders = (Button) v.findViewById(R.id.btnOrderslocal);
		btnInsOrders.setEnabled(false);

		// Declare the JDBC objects.
		Connection DbConnFactors = null;
		Statement stmtFactors = null;
		ResultSet resultSetFactors = null;

		Connection DbConnOrders = null;
		Statement stmtOrders = null;
		ResultSet resultSetOrders = null;

		try {

			DatabaseHandler db = new DatabaseHandler(contaxt);

			DBConnection dbconnection = db.getDBConnection();
			// Establish the connection.
			Class.forName("net.sourceforge.jtds.jdbc.Driver").newInstance();

			String username = dbconnection.getUserName();
			String password = dbconnection.getPassword();
			// String username = "Sa";
			// String password = "1";

			if (android.os.Build.VERSION.SDK_INT > 9) {
				StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
						.permitAll().build();
				StrictMode.setThreadPolicy(policy);
			}

			DbConnFactors = DriverManager
					.getConnection("jdbc:jtds:sqlserver://"
							+ dbconnection.getIPLocal() + ":1433/"
							+ dbconnection.getDataBaseMain() + ";user="
							+ username + ";password=" + password + ";");


			String FactorsTableName = "Request";
			String OrdersTableName = "RequestDetail";

			Log.w("Connection", "open");

			// Factors
			List<Factors> FactorsList = db
					.getAllFactorsUnsended(MainMenuActivity.OperatorGuid);
			for (Factors factors : FactorsList) {
				stmtFactors = null;
				resultSetFactors = null;
				stmtFactors = DbConnFactors.createStatement();

				String Query = " INSERT INTO "
						+ FactorsTableName
						+ "( GuidRequest, "
						+ " TypeInvcID, GuidCustomer, LocalDate,"
						+ " LocalTime, SystemID, GuidReseler, Note )"
						+ " Values ( '"
						+ UUID.fromString(factors.getGuidInvcRequest())
						+ "', '" + factors.getTypeInvcId() + "', '"
						//+ "', (Select isnull(Max(id),1)+1 from Request) ,'" + factors.getTypeInvcId() + "', '"
						+ UUID.fromString(factors.getGuidCustomer()) + "', N'"
						+ factors.getLocalDate() + "', N'"
						+ factors.getLocalTime() + "', N'"
						+ factors.getSystemID() + "', '"
						+ UUID.fromString(factors.getGuidReseler()) + "', N'"
						+ factors.getNote() + "' " + ");";
				stmtFactors.execute(Query);

				db.updateFactorToSendedMode(factors);
			}

			DbConnFactors.close();
			// Orders

			DbConnOrders = DriverManager.getConnection("jdbc:jtds:sqlserver://"
					+ dbconnection.getIPLocal() + ":1433/"
					+ dbconnection.getDataBaseMain() + ";user=" + username
					+ ";password=" + password + ";");
			List<Orders> OrdersList = db
					.getAllOrdersUnsended(MainMenuActivity.OperatorGuid);
			for (Orders ords : OrdersList) {
				stmtOrders = null;
				resultSetOrders = null;
				stmtOrders = DbConnOrders.createStatement();

				String Query = " INSERT INTO "
						+ OrdersTableName
						+ "( GuidRequestDetail, GuidRequest, "
						+ " GuidProdScale,"
						+ " Quantity, Fee )"
						+ " Values ( '"
						+ UUID.fromString(ords.getGuidRequest()) + "', '"
						+ UUID.fromString(ords.getGuidInvcRequest()) + "', '"
						//+ " (SELECT isnull(MAX(id),0)+1 FROM RequestDetail) , '"
						+ UUID.fromString(ords.getGuidProdScale()) + "', "
						+ ords.getQuantity()
						+ ", " + ords.getFee() + ");";
				stmtOrders.execute(Query);

				db.updateOrderToSendedMode(ords);
			}

			DbConnOrders.close();
		} catch (Exception e) {
			Log.w("Error connection", "" + e.getMessage());
			Toast.makeText(contaxt, "عدم موفقیت در ارسال گردش عملیات به ستاد",
					Toast.LENGTH_LONG).show();
		}

		// Enabling Buttons
		btnInsOrders.setEnabled(true);

	}

	// a function for converting Goods' Items to <needed string> of its
	// Products.XML file
	private String writeXmlContacts(ArrayList<Contact> contacts) {
		XmlSerializer serializer = Xml.newSerializer();
		StringWriter writer = new StringWriter();
		try {
			serializer.setOutput(writer);
			serializer.startDocument("UTF-8", true);
			serializer.startTag("", "Customers");
			serializer.attribute("", "number", String.valueOf(contacts.size()));
			for (Contact ct : contacts) {
				serializer.startTag("", "Customer");
				serializer.startTag("", "guid");
				serializer.text(ct.getGuid());
				serializer.endTag("", "guid");
				serializer.startTag("", "id");
				serializer.text(ct.getID());
				serializer.endTag("", "id");
				serializer.startTag("", "name");
				serializer.text(ct.getName());
				serializer.endTag("", "name");
				serializer.startTag("", "region_name");
				serializer.text(ct.getRegionName());
				serializer.endTag("", "region_name");
				serializer.startTag("", "phone_number1");
				serializer.text(ct.getPhoneNumber1());
				serializer.endTag("", "phone_number1");
				serializer.startTag("", "phone_number2");
				serializer.text(ct.getPhoneNumber2());
				serializer.endTag("", "phone_number2");
				serializer.startTag("", "phone_number3");
				serializer.text(ct.getPhoneNumber3());
				serializer.endTag("", "phone_number3");
				serializer.startTag("", "phone_number4");
				serializer.text(ct.getPhoneNumber4());
				serializer.endTag("", "phone_number4");
				serializer.startTag("", "address");
				serializer.text(ct.getAddress());
				serializer.endTag("", "address");
				serializer.startTag("", "mobile");
				serializer.text(ct.getMobile());
				serializer.endTag("", "mobile");
				serializer.startTag("", "fax");
				serializer.text(ct.getFax());
				serializer.endTag("", "fax");
				serializer.startTag("", "deposit");
				serializer.text(ct.getDeposit());
				serializer.endTag("", "deposit");
				serializer.startTag("", "detection");
				serializer.text(ct.getDetection());
				serializer.endTag("", "detection");
				serializer.startTag("", "guid_visitor");
				serializer.text(ct.getGuidVisitor());
				serializer.endTag("", "guid_visitor");
				serializer.startTag("", "guidCustomer_category");
				serializer.text(ct.getGuidCustomerCategory());
				serializer.endTag("", "guidCustomer_category");
				serializer.endTag("", "Customer");
			}
			serializer.endTag("", "Customers");
			serializer.endDocument();
			return writer.toString();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	// a function for converting Goods' Items to <needed string> of its
	// Products.XML file
	private String writeXmlGoods(ArrayList<Goods> goods) {
		XmlSerializer serializer = Xml.newSerializer();
		StringWriter writer = new StringWriter();
		try {
			serializer.setOutput(writer);
			serializer.startDocument("UTF-8", true);
			serializer.startTag("", "Products");
			serializer.attribute("", "number", String.valueOf(goods.size()));
			for (Goods gd : goods) {
				serializer.startTag("", "Product");
				serializer.startTag("", "guid");
				serializer.text(gd.getGuid());
				serializer.endTag("", "guid");
				serializer.startTag("", "id");
				serializer.text(gd.getID());
				serializer.endTag("", "id");
				serializer.startTag("", "name");
				serializer.text(gd.getGoodName());
				serializer.endTag("", "name");
				serializer.startTag("", "stock");
				serializer.text(gd.getStock());
				serializer.endTag("", "stock");
				serializer.startTag("", "count_unit");
				serializer.text(gd.getCountUnit());
				serializer.endTag("", "count_unit");
				serializer.startTag("", "price");
				serializer.text(gd.getPrice());
				serializer.endTag("", "price");
				serializer.startTag("", "price_type");
				serializer.text(gd.getPriceType());
				serializer.endTag("", "price_type");
				serializer.startTag("", "q_box");
				serializer.text(gd.getQBox());
				serializer.endTag("", "q_box");
				serializer.endTag("", "Product");
			}
			serializer.endTag("", "Products");
			serializer.endDocument();
			return writer.toString();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/* Checks if external storage is available for read and write */
	public boolean isExternalStorageWritable() {
		String state = Environment.getExternalStorageState();
		if (Environment.MEDIA_MOUNTED.equals(state)) {
			return true;
		}
		return false;
	}

	/* Checks if external storage is available to at least read */
	public boolean isExternalStorageReadable() {
		String state = Environment.getExternalStorageState();
		if (Environment.MEDIA_MOUNTED.equals(state)
				|| Environment.MEDIA_MOUNTED_READ_ONLY.equals(state)) {
			return true;
		}
		return false;
	}

	/* Generating Products.xml */
	@SuppressLint({ "SdCardPath", "WorldReadableFiles" })
	public void generateFarapaidarProductsOnSD(String sFileName, String sBody,
			Context contaxt) {

		try {

			@SuppressWarnings("deprecation")
			FileOutputStream fos = contaxt.openFileOutput(sFileName,
					Context.MODE_PRIVATE |

					Context.MODE_APPEND | Context.MODE_WORLD_READABLE);

			fos.write(sBody.toString().getBytes());

			fos.close();

			File root = new File("/sdcard/", "Farapaidar");
			if (!root.exists()) {
				root.mkdirs();
			}

			File file = new File(root, sFileName);

			FileOutputStream fos2 = new FileOutputStream(file);

			fos2.write(sBody.toString().getBytes());

			fos2.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private void FillProductsInSQLite(Context contaxt) {
		// Reading Customers.xml
		ReadXMLFile readxmlfile = new ReadXMLFile();

		readxmlfile.setPath("/sdcard/Farapaidar/");
		readxmlfile.setFileName("Products.xml");
		File file = readxmlfile.ReadFile();

		NodeList nList = readxmlfile.ParseFile(file, "Product");

		if (nList == null) {
			Toast.makeText(contaxt,
					"ساختار فایل مربوط به موجودی کالا صحیح نیست",
					Toast.LENGTH_LONG).show();
		}

		else {

			Goods[] goods = new Goods[nList.getLength()];

			for (int i = 0; i < nList.getLength(); i++) {

				Node node = nList.item(i);
				if (node.getNodeType() == Node.ELEMENT_NODE) {
					Element element2 = (Element) node;
					Goods gd = new Goods();
					gd.setGuid(getValue(KEY_GUID, element2)); // Guid child
																// value
					gd.setID(getValue(KEY_ID, element2)); // id child value
					gd.setGoodName(getValue(KEY_NAME, element2)); // name child
																	// value
					gd.setStock(getValue(KEY_STOCK, element2)); // stock child
																// value
					gd.setCountUnit(getValue(KEY_COUNT_UNIT, element2)); // count
																			// unit
																			// child
																			// value
					gd.setPrice(getValue(KEY_PRICE, element2)); // price child
																// value
					gd.setPriceType(getValue(KEY_PRICE_TYPE, element2)); // price
																			// type
																			// child
																			// value
					gd.setQBox(getValue(KEY_Q_BOX, element2)); // QBox child
																// value

					goods[i] = gd;

				}

			}

			DatabaseHandler db = new DatabaseHandler(contaxt);
			// Inserting Goods

			// First delete existing records in Products table
			db.deleteAllGoods();

			// Then inserting new records which readed from Products.xml file
			Log.d("Insert: ", "Inserting ..");

			for (int i = 0; i < goods.length; i++) {
				db.addGoods(goods[i]);
			}

		}

	}

	@SuppressLint("SdCardPath")
	private void FillCustomersInSQLite(Context contaxt) {
		// Reading Customers.xml
		ReadXMLFile readxmlfile = new ReadXMLFile();

		readxmlfile.setPath("/sdcard/Farapaidar/");
		readxmlfile.setFileName("Customers.xml");
		File file = readxmlfile.ReadFile();

		NodeList nList = readxmlfile.ParseFile(file, "Customer");

		if (nList == null) {
			Toast.makeText(contaxt,
					"ساختار فایل مربوط به اسامی افراد صحیح نیست",
					Toast.LENGTH_LONG).show();
		} else {

			Contact[] contacts = new Contact[nList.getLength()];
			for (int i = 0; i < nList.getLength(); i++) {

				Node node = nList.item(i);
				if (node.getNodeType() == Node.ELEMENT_NODE) {
					Element element2 = (Element) node;
					Contact ct = new Contact();
					ct.setGuid(getValue(KEY_GUID, element2)); // Guid child
																// value
					ct.setID(getValue(KEY_ID, element2)); // id child value
					ct.setName(getValue(KEY_NAME, element2)); // name child
																// value
					ct.setRegionName(getValue(KEY_REGION_NAME, element2)); // region
																			// name
																			// child
																			// value
					ct.setPhoneNumber1(getValue(KEY_PHON_NUM_1, element2)); // phoneNumber1
																			// child
																			// value
					ct.setPhoneNumber2(getValue(KEY_PHON_NUM_2, element2)); // phoneNumber2
																			// child
																			// value
					ct.setPhoneNumber3(getValue(KEY_PHON_NUM_3, element2)); // phoneNumber3
																			// child
																			// value
					ct.setPhoneNumber4(getValue(KEY_PHON_NUM_4, element2)); // phoneNumber4
																			// child
																			// value
					ct.setAddress(getValue(KEY_ADDRESS, element2)); // address
																	// child
																	// value
					ct.setMobile(getValue(KEY_MOBILE, element2)); // mobile
																	// child
																	// value
					ct.setFax(getValue(KEY_FAX, element2)); // fax child value
					ct.setDeposit(getValue(KEY_DEPOSIT, element2)); // deposit
																	// name
																	// child
																	// value
					ct.setDetection(getValue(KEY_DETECTION, element2)); // detection
																		// name
																		// child
																		// value
					ct.setGuidVisitor(getValue(KEY_GUID_VISITOR, element2)); // GuidVisitor
																				// name
																				// child
																				// value
					
					ct.setGuidCustomerCategory(getValue(
							KEY_GUID_CUSTOMER_CATEGORY, element2)); // GuidCustomerCategory
																	// name
																	// child
																	// value

					contacts[i] = ct;
				}
			}

			// Inserting Contacts

			DatabaseHandler db = new DatabaseHandler(contaxt);

			// First delete existing records in Customers table
			db.deleteAllContacts();

			// Then inserting new records which readed from Customers.xml file
			Log.d("Insert: ", "Inserting ..");

			for (int i = 0; i < contacts.length; i++) {
				db.addContact(contacts[i]);
			}

		}

	}

	// get value of a tag in an Element
	private static String getValue(String tag, Element element) {
		NodeList nodeList = element.getElementsByTagName(tag).item(0)
				.getChildNodes();
		Node node = nodeList.item(0);
		return node.getNodeValue();
	}

	// according Vlaue of Deposit, Detection value will be determined! Detection
	// = {"بد","بس","-"}
	private static String[] SetDetectionFunc(String Deposit) {
		String[] returnedValues = new String[2];
		if (Integer.parseInt(Deposit) > 0) {
			returnedValues[0] = Deposit;
			returnedValues[1] = "بس";
		} else if (Integer.parseInt(Deposit) < 0) {
			returnedValues[0] = String
					.valueOf((Integer.parseInt(Deposit) * -1));
			;
			returnedValues[1] = "بد";
		} else if (Integer.parseInt(Deposit) == 0) {
			returnedValues[0] = Deposit;
			returnedValues[1] = "بی حساب";
		}

		return returnedValues;
	}

}
