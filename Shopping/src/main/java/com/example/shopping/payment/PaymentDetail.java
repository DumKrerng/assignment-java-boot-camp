package com.example.shopping.payment;

import com.example.shopping.payment.paymentmethod.*;

public class PaymentDetail {

	private String m_orderId;

	private PaymentMethod m_method;

	private CreditCardModel m_creditcard;
	private BankModel m_bank;
	private PayPalModel m_paypal;
	private LINEPayModel m_linepay;

	public String getOrderId() {
		return m_orderId;
	}

	public void setOrderId(String p_orderId) {
		m_orderId = p_orderId;
	}

	public PaymentMethod getMethod() {
		return m_method;
	}

	public void setMethod(PaymentMethod p_method) {
		m_method = p_method;
	}

	public CreditCardModel getCreditCard() {
		return m_creditcard;
	}

	public void setCreditCard(CreditCardModel p_creditcard) {
		m_creditcard = p_creditcard;
	}

	public BankModel getBank() {
		return m_bank;
	}

	public void setBank(BankModel p_bank) {
		m_bank = p_bank;
	}

	public PayPalModel getPaypal() {
		return m_paypal;
	}

	public void setPaypal(PayPalModel p_paypal) {
		m_paypal = p_paypal;
	}

	public LINEPayModel getLinePay() {
		return m_linepay;
	}

	public void setLinePay(LINEPayModel p_linepay) {
		m_linepay = p_linepay;
	}

	public PaymentModel toPaymentModel() {
		PaymentModel payment = new PaymentModel();
		payment.setPaymentMethod(m_method.getCode());

		if(PaymentMethod.CreditCard == m_method) {
			payment.setCreditCardNumber(m_creditcard.getCreditCardNumber());
			payment.setCreditCardName(m_creditcard.getCreditCardName());
			payment.setCreditCardExpire(m_creditcard.getCreditCardExpire());

		} else if(PaymentMethod.Bank == m_method) {
			payment.setBankSwiftCode(m_bank.getSwiftCode());
			payment.setBankAccountNumber(m_bank.getBankAccountNumber());
			payment.setBankAccountName(m_bank.getBankAccountName());

		} else if(PaymentMethod.PayPal == m_method) {
			payment.setPaypalUserEmail(m_paypal.getUserEmail());

		} else if(PaymentMethod.LINEPay == m_method) {
			payment.setLinePayAccountNumber(m_linepay.getAccountNumber());
		}

		return payment;
	}
}
