package com.example.shopping.payment;

import com.example.shopping.payment.paymentmethod.*;
import org.hibernate.annotations.*;
import org.jetbrains.annotations.*;
import javax.persistence.*;
import javax.persistence.Entity;

@Entity
public class PaymentModel {

	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	private String id;

	private String orderId;

	private String paymentmethod;

	private String creditCardExpire;
	private String creditCardName;
	private String creditCardNumber;

	private String bankAccountName;
	private String bankSwiftCode;
	private String bankAccountNumber;

	private String paypalUserEmail;

	private String linepayAccountNumber;

	public PaymentModel() {}

	public String getId() {
		return id;
	}

	public void setId(String p_id) {
		id = p_id;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String p_orderId) {
		orderId = p_orderId;
	}

	public String getPaymentMethod() {
		return paymentmethod;
	}

	public void setPaymentMethod(String p_paymentmethod) {
		paymentmethod = p_paymentmethod;
	}

	public String getCreditCardExpire() {
		return creditCardExpire;
	}

	public void setCreditCardExpire(String p_creditCardExpire) {
		creditCardExpire = p_creditCardExpire;
	}

	public String getCreditCardName() {
		return creditCardName;
	}

	public void setCreditCardName(String p_creditCardName) {
		creditCardName = p_creditCardName;
	}

	public String getCreditCardNumber() {
		return creditCardNumber;
	}

	public void setCreditCardNumber(String p_creditCardNumber) {
		creditCardNumber = p_creditCardNumber;
	}

	public String getBankAccountName() {
		return bankAccountName;
	}

	public void setBankAccountName(String p_bankAccountName) {
		bankAccountName = p_bankAccountName;
	}

	public String getBankSwiftCode() {
		return bankSwiftCode;
	}

	public void setBankSwiftCode(String p_bankSwiftCode) {
		bankSwiftCode = p_bankSwiftCode;
	}

	public String getBankAccountNumber() {
		return bankAccountNumber;
	}

	public void setBankAccountNumber(String p_bankAccountNumber) {
		bankAccountNumber = p_bankAccountNumber;
	}

	public String getPaypalUserEmail() {
		return paypalUserEmail;
	}

	public void setPaypalUserEmail(String p_paypalUserEmail) {
		paypalUserEmail = p_paypalUserEmail;
	}

	public String getLinePayAccountNumber() {
		return linepayAccountNumber;
	}

	public void setLinePayAccountNumber(String p_linepayAccountNumber) {
		linepayAccountNumber = p_linepayAccountNumber;
	}

	public @Nullable CreditCardModel getCreditCard() {
		if(paymentmethod.equals(PaymentMethod.CreditCard.getCode())) {
			CreditCardModel creditcard = new CreditCardModel();
			creditcard.setCreditCardNumber(creditCardNumber);
			creditcard.setCreditCardName(creditCardName);
			creditcard.setCreditCardExpire(creditCardExpire);

			return creditcard;
		}

		return null;
	}

	public @Nullable BankModel getBankModel() {
		if(paymentmethod.equals(PaymentMethod.Bank.getCode())) {
			BankModel bank = new BankModel();
			bank.setSwiftCode(bankSwiftCode);
			bank.setBankAccountNumber(bankAccountNumber);
			bank.setBankAccountName(bankAccountName);

			return bank;
		}

		return null;
	}

	public @Nullable PayPalModel getPayPalModel() {
		if(paymentmethod.equals(PaymentMethod.PayPal.getCode())) {
			PayPalModel paypal = new PayPalModel();
			paypal.setUserEmail(paypalUserEmail);

			return paypal;
		}

		return null;
	}

	public @Nullable LINEPayModel getLINEPayModel() {
		if(paymentmethod.equals(PaymentMethod.LINEPay.getCode())) {
			LINEPayModel linepay = new LINEPayModel();
			linepay.setAccountNumber(linepayAccountNumber);

			return linepay;
		}

		return null;
	}

	public PaymentDetail toPaymentDetail() {
		PaymentDetail detail = new PaymentDetail();
		detail.setOrderId(orderId);
		detail.setMethod(PaymentMethod.getPaymentMethod(paymentmethod));

		CreditCardModel creditcard = new CreditCardModel();
		creditcard.setCreditCardNumber(creditCardNumber);
		creditcard.setCreditCardName(creditCardName);
		creditcard.setCreditCardExpire(creditCardExpire);
		detail.setCreditCard(creditcard);

		BankModel bank = new BankModel();
		bank.setSwiftCode(bankSwiftCode);
		bank.setBankAccountNumber(bankAccountNumber);
		bank.setBankAccountName(bankAccountName);
		detail.setBank(bank);

		PayPalModel paypal = new PayPalModel();
		paypal.setUserEmail(paypalUserEmail);
		detail.setPaypal(paypal);

		LINEPayModel linepay = new LINEPayModel();
		linepay.setAccountNumber(linepayAccountNumber);
		detail.setLinePay(linepay);

		return detail;
	}
}
