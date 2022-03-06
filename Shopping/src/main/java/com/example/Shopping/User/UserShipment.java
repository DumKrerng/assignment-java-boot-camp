package com.example.Shopping.User;

public class ResponseShipment {

	private String m_strUserEmail;
	private String m_strUserFullName;
	private AddressModel m_address;
	
	public ResponseShipment() {}

	public String getUserEmail() {
		return m_strUserEmail;
	}

	public void setUserEmail(String p_strUserEmail) {
		m_strUserEmail = p_strUserEmail;
	}

	public String getUserFullName() {
		return m_strUserFullName;
	}

	public void setUserFullName(String p_strUserFullName) {
		m_strUserFullName = p_strUserFullName;
	}

	public AddressModel getAddress() {
		return m_address;
	}

	public void setAddress(AddressModel p_address) {
		m_address = p_address;
	}
}
