package com.example.shopping.payment;

import com.example.shopping.payment.paymentmethod.*;
import org.hibernate.annotations.*;
import javax.persistence.*;
import javax.persistence.Entity;

@Entity
public class PaymentDetail {

	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	private String id;

	private String orderId;

	private String m_paymentmethod;

	private String creditCardExpire;
	private String creditCardName;
	private String creditCardNumber;

	private String bankAccountName;
	private String bankSwiftCode;
	private String bankAccountNumber;

	private String m_paypalUserEmail;

	private String m_linepayAccountNumber;


	public PaymentDetail() {}

	public PaymentDetail(PaymentMethod p_paymentmethod) {
		m_paymentmethod = p_paymentmethod.getCode();
	}

	public String getPaymentMethod() {
		return m_paymentmethod;
	}

	public void setPaymentMethod(String p_paymentmethod) {
		m_paymentmethod = p_paymentmethod;
	}



}
