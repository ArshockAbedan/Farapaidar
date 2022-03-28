package com.farapaidar.firstmenu001;

public class Contact {

	// private variables
	private String _guid;
	private String _id;
	private String _name;
	private String _region_name;
	private String _phone_number1;
	private String _phone_number2;
	private String _phone_number3;
	private String _phone_number4;
	private String _address;
	private String _mobile;
	private String _fax;
	private String _deposit;
	private String _detection;
	private String _guid_visitor;
	private String _guidCustomer_category;

	public Contact() {

	}

	// basic constructor with Guid
	public Contact(String guid, String id, String name, String phone_number1) {
		this._guid = guid;
		this._id = id;
		this._name = name;
		this._phone_number1 = phone_number1;
	}

	// basic constructor without Guid
	public Contact(String id, String name, String phone_number1) {
		this._id = id;
		this._name = name;
		this._phone_number1 = phone_number1;
	}

	// complete constructor with id
	public Contact(String guid, String id, String name, String region_name,
			String phone_number1, String phone_number2, String phone_number3,
			String phone_number4, String address, String mobile, String fax,
			String deposit, String detection, String GuidVisitor, String GuidCustomerCategory) {
		this._guid = guid;
		this._id = id;
		this._name = name;
		this._region_name = region_name;
		this._phone_number1 = phone_number1;
		this._phone_number2 = phone_number2;
		this._phone_number3 = phone_number3;
		this._phone_number4 = phone_number4;
		this._address = address;
		this._mobile = mobile;
		this._fax = fax;
		this._deposit = deposit;
		this._detection = detection;
		this._guid_visitor = GuidVisitor;
		this._guidCustomer_category = GuidCustomerCategory;
	}

	/*
	// complete constructor without guid
	public Contact(String id, String name, String region_name,
			String phone_number1, String phone_number2, String phone_number3,
			String phone_number4, String address, String mobile, String fax,
			String deposit, String detection, String GuidVisitor, String GuidCustomerCategory) {
		this._id = id;
		this._name = name;
		this._region_name = region_name;
		this._phone_number1 = phone_number1;
		this._phone_number2 = phone_number2;
		this._phone_number3 = phone_number3;
		this._phone_number4 = phone_number4;
		this._address = address;
		this._mobile = mobile;
		this._fax = fax;
		this._deposit = deposit;
		this._detection = detection;
		this._guid_visitor = GuidVisitor;
		this._guidCustomer_category = GuidCustomerCategory;
	}
*/
	// ///////////////////////////////////
	// ////////////////////////////////////////////////////////////////////////

	// getting guid
	public String getGuid() {
		return this._guid;
	}

	// setting guid
	public void setGuid(String guid) {
		this._guid = guid;
	}

	// ////////////////////////////////////

	// getting ID

	public String getID() {
		return this._id;
	}

	// setting id
	public void setID(String id) {
		this._id = id;
	}

	// ////////////////////////////////////

	// getting name
	public String getName() {
		return this._name;
	}

	// setting name
	public void setName(String name) {
		this._name = name;
	}

	// /////////////////////////////////////

	// getting region_name
	public String getRegionName() {
		return this._region_name;
	}

	// setting name
	public void setRegionName(String region_name) {
		this._region_name = region_name;
	}

	// /////////////////////////////////////

	// getting phone number1
	public String getPhoneNumber1() {
		return this._phone_number1;
	}

	// setting phone number1
	public void setPhoneNumber1(String phone_number1) {
		this._phone_number1 = phone_number1;
	}

	// /////////////////////////////////////

	// getting phone number2
	public String getPhoneNumber2() {
		return this._phone_number2;
	}

	// setting phone number2
	public void setPhoneNumber2(String phone_number2) {
		this._phone_number2 = phone_number2;
	}

	// /////////////////////////////////////

	// getting phone number3
	public String getPhoneNumber3() {
		return this._phone_number3;
	}

	// setting phone number3
	public void setPhoneNumber3(String phone_number3) {
		this._phone_number3 = phone_number3;
	}

	// /////////////////////////////////////

	// getting phone number4
	public String getPhoneNumber4() {
		return this._phone_number4;
	}

	// setting phone number4
	public void setPhoneNumber4(String phone_number4) {
		this._phone_number4 = phone_number4;
	}

	// /////////////////////////////////////

	// getting address
	public String getAddress() {
		return this._address;
	}

	// setting address
	public void setAddress(String address) {
		this._address = address;
	}

	// /////////////////////////////////////

	// getting mobile
	public String getMobile() {
		return this._mobile;
	}

	// setting mobile
	public void setMobile(String mobile) {
		this._mobile = mobile;
	}

	// /////////////////////////////////////

	// getting fax
	public String getFax() {
		return this._fax;
	}

	// setting fax
	public void setFax(String fax) {
		this._fax = fax;
	}

	// /////////////////////////////////////

	// getting deposit
	public String getDeposit() {
		return this._deposit;
	}

	// setting deposit
	public void setDeposit(String deposit) {
		this._deposit = deposit;
	}

	// /////////////////////////////////////

	// getting _detection
	public String getDetection() {
		return this._detection;
	}

	// setting detection
	public void setDetection(String detection) {
		this._detection = detection;
	}

	// /////////////////////////////////////

	// getting GuidVisitor
	public String getGuidVisitor() {
		return this._guid_visitor;
	}

	// setting GuidVisitor
	public void setGuidVisitor(String GuidVisitor) {
		this._guid_visitor = GuidVisitor;
	}

	// //////////////////////////////////////

		public String getGuidCustomerCategory() {
			return this._guidCustomer_category;
		}

		public void setGuidCustomerCategory(String GuidCustomerCategory) {
			this._guidCustomer_category = GuidCustomerCategory;
		}

		// //////////////////////////////////////

}
