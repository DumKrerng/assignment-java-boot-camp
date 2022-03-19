package com.example.shopping.payment.paymentmethod;

public class PayPalModel {

	private String m_strUserEmail;

	public PayPalModel() {}

	public String getUserEmail() {
		return m_strUserEmail;
	}

	public void setUserEmail(String p_strUserEmail) {
		m_strUserEmail = p_strUserEmail;
	}
}
