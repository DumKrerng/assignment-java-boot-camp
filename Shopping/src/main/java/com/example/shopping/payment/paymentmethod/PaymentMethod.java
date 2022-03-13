package com.example.shopping.payment.paymentmethod;

public enum PaymentMethod {
	CreditCard,
	Bank,
	PayPal,
	LINEPay;

	public String getCode() {
		return name();
	}
}
