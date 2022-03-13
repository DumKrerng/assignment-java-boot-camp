package com.example.shopping.payment.paymentmethod;

public enum PaymentMethod {
	CreditCard,
	Bank,
	PayPal,
	LINEPay;

	public String getCode() {
		return name();
	}

	public static PaymentMethod getPaymentMethod(String p_name) {
		switch(p_name) {
			case "CreditCard":
				return CreditCard;

			case "Bank":
				return Bank;

			case "PayPal":
				return PayPal;

			case "LINEPay":
				return LINEPay;
		}

		return null;
	}
}
