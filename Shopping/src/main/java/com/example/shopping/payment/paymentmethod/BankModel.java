package com.example.shopping.payment.paymentmethod;

public class BankModel {

	private String bankAccountName;
	private String swiftCode;
	private String bankAccountNumber;

	public void setBankAccountName(String p_bankAccountName){
		this.bankAccountName = p_bankAccountName;
	}

	public String getBankAccountName(){
		return bankAccountName;
	}

	public void setSwiftCode(String p_swiftCode){
		this.swiftCode = p_swiftCode;
	}

	public String getSwiftCode(){
		return swiftCode;
	}

	public void setBankAccountNumber(String p_bankAccountNumber){
		this.bankAccountNumber = p_bankAccountNumber;
	}

	public String getBankAccountNumber(){
		return bankAccountNumber;
	}
}
