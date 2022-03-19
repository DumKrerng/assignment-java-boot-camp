package com.example.shopping.payment.paymentmethod;

public class CreditCardModel{
	private String creditCardExpire;
	private String creditCardName;
	private String creditCardNumber;

	public void setCreditCardExpire(String creditCardExpire){
		this.creditCardExpire = creditCardExpire;
	}

	public String getCreditCardExpire(){
		return creditCardExpire;
	}

	public void setCreditCardName(String creditCardName){
		this.creditCardName = creditCardName;
	}

	public String getCreditCardName(){
		return creditCardName;
	}

	public void setCreditCardNumber(String creditCardNumber){
		this.creditCardNumber = creditCardNumber;
	}

	public String getCreditCardNumber(){
		return creditCardNumber;
	}
}
