package com.farapaidar.firstmenu001;

public class PriceList {

	private String _id;
	private String _guidCustomer_category;
	private String _guid_prod_scale;
	private String _guid_scale;
	private String _name_public;
	private String _type_price_id;
	private String _Customer_category;
	private String _type_price_title;
	private String _local_date;
	private String _type_registration_id;
	private String _fee;

	public PriceList() {
    
	}

	public PriceList(String ID, String GuidCustomerCategory,
			String GuidProdScale, String GuidScale, String NamePublic,
			String TypePriceID, String CustomerCategory, String TypePriceTitle,
			String LocalDate, String TypeRegistrationID, String Fee) {
		this._id = ID;
		this._guidCustomer_category = GuidCustomerCategory;
		this._guid_prod_scale = GuidProdScale;
		this._guid_scale = GuidScale;
		this._name_public = NamePublic;
		this._type_price_id = TypePriceID;
		this._Customer_category = CustomerCategory;
		this._type_price_title = TypePriceTitle;
		this._local_date = LocalDate;
		this._type_registration_id = TypeRegistrationID;
		this._fee = Fee;
	}

	// ///////////////////////////////////////////////////////////////////////////////

	public String getID() {
		return this._id;
	}

	public void setId(String ID) {
		this._id = ID;
	}

	// //////////////////////////////////////

	public String getGuidCustomerCategory() {
		return this._guidCustomer_category;
	}

	public void setGuidCustomerCategory(String GuidCustomerCategory) {
		this._guidCustomer_category = GuidCustomerCategory;
	}

	// //////////////////////////////////////

	public String getGuidProdScale() {
		return this._guid_prod_scale;
	}

	public void setGuidProdScale(String GuidProdScale) {
		this._guid_prod_scale = GuidProdScale;
	}

	// //////////////////////////////////////

	public String getGuidScale() {
		return this._guid_scale;
	}

	public void setGuidScale(String GuidScale) {
		this._guid_scale = GuidScale;
	}

	// //////////////////////////////////////

	public String getNamePublic() {
		return this._name_public;
	}

	public void setNamePublic(String NamePublic) {
		this._name_public = NamePublic;
	}

	// //////////////////////////////////////

	public String getTypePriceID() {
		return this._type_price_id;
	}

	public void setTypePriceID(String TypePriceID) {
		this._type_price_id = TypePriceID;
	}

	// //////////////////////////////////////

	public String getCustomerCategory() {
		return this._Customer_category;
	}

	public void setCustomerCategory(String CustomerCategory) {
		this._Customer_category = CustomerCategory;
	}

	// //////////////////////////////////////

	public String getTypePriceTitle() {
		return this._type_price_title;
	}

	public void setTypePriceTitle(String TypePriceTitle) {
		this._type_price_title = TypePriceTitle;
	}

	// //////////////////////////////////////

	public String getLocalDate() {
		return this._local_date;
	}

	public void setLocalDate(String LocalDate) {
		this._local_date = LocalDate;
	}

	// //////////////////////////////////////

	public String getTypeRegistrationID() {
		return this._type_registration_id;
	}

	public void setTypeRegistrationID(String TypeRegistrationID) {
		this._type_registration_id = TypeRegistrationID;
	}
	
	// //////////////////////////////////////
	public String getFee() {
		return this._fee;
	}

	public void setFee(String Fee) {
		this._fee = Fee;
	}

	

	// //////////////////////////////////////////////////////////////////////////////

}
