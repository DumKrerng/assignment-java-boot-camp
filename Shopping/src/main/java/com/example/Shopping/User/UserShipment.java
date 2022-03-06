package com.example.Shopping.User;

public class UserShipment {

	private UserModel m_user;
	private AddressModel m_address;
	
	public UserShipment() {}

	public UserModel getUser() {
		return m_user;
	}

	public void setUser(UserModel p_user) {
		m_user = p_user;
	}

	public AddressModel getAddress() {
		return m_address;
	}

	public void setAddress(AddressModel p_address) {
		m_address = p_address;
	}
}
