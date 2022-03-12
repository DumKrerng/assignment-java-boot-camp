package com.example.shopping.user;

public class UserShipment {

	private UserViewModel m_user;
	private AddressViewModel m_address;
	
	public UserShipment() {}

	public UserViewModel getUser() {
		return m_user;
	}

	public void setUser(UserViewModel p_user) {
		m_user = p_user;
	}

	public AddressViewModel getAddress() {
		return m_address;
	}

	public void setAddress(AddressViewModel p_address) {
		m_address = p_address;
	}
}
