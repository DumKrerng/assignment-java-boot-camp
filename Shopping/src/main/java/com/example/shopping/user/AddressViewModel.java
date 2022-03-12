package com.example.shopping.user;

public class AddressViewModel {

	private String subDistrict;
	private String addressDetail;
	private String phoneNumber;
	private String postcode;
	private String district;
	private String province;

	public AddressViewModel() {}

	public AddressViewModel(AddressModel p_address) {
		addressDetail = p_address.getAddressDetail();
		subDistrict = p_address.getSubDistrict();
		district = p_address.getDistrict();
		province = p_address.getProvince();
		postcode = p_address.getPostcode();
		phoneNumber = p_address.getPhoneNumber();
	}

	public void setSubDistrict(String p_subDistrict){
		this.subDistrict = p_subDistrict;
	}

	public String getSubDistrict(){
		return subDistrict;
	}

	public void setAddressDetail(String p_addressDetail){
		this.addressDetail = p_addressDetail;
	}

	public String getAddressDetail(){
		return addressDetail;
	}

	public void setPhoneNumber(String p_phoneNumber){
		this.phoneNumber = p_phoneNumber;
	}

	public String getPhoneNumber(){
		return phoneNumber;
	}

	public void setPostcode(String p_postcode){
		this.postcode = p_postcode;
	}

	public String getPostcode(){
		return postcode;
	}

	public void setDistrict(String p_district){
		this.district = p_district;
	}

	public String getDistrict(){
		return district;
	}

	public void setProvince(String p_province){
		this.province = p_province;
	}

	public String getProvince(){
		return province;
	}
}
