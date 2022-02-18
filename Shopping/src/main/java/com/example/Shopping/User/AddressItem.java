package com.example.Shopping.User;

public class AddressItem {
	private String subDistrict;
	private String addressDetail;
	private String phoneNumber;
	private String postcode;
	private String district;
	private String province;

	public void setSubDistrict(String subDistrict){
		this.subDistrict = subDistrict;
	}

	public String getSubDistrict(){
		return subDistrict;
	}

	public void setAddressDetail(String addressDetail){
		this.addressDetail = addressDetail;
	}

	public String getAddressDetail(){
		return addressDetail;
	}

	public void setPhoneNumber(String phoneNumber){
		this.phoneNumber = phoneNumber;
	}

	public String getPhoneNumber(){
		return phoneNumber;
	}

	public void setPostcode(String postcode){
		this.postcode = postcode;
	}

	public String getPostcode(){
		return postcode;
	}

	public void setDistrict(String district){
		this.district = district;
	}

	public String getDistrict(){
		return district;
	}

	public void setProvince(String province){
		this.province = province;
	}

	public String getProvince(){
		return province;
	}
}
