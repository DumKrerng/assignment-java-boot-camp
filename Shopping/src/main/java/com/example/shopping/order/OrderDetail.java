package com.example.shopping.order;

import com.example.shopping.payment.*;
import com.example.shopping.user.*;

public class OrderDetail {

	private AddressModel addressShipping;
	private AddressModel addressInvoice;

	private PaymentDetail payment;

	public AddressModel getAddressShipping() {
		return addressShipping;
	}

	public void setAddressShipping(AddressModel p_addressShipping) {
		addressShipping = p_addressShipping;
	}

	public AddressModel getAddressInvoice() {
		return addressInvoice;
	}

	public void setAddressInvoice(AddressModel p_addressInvoice) {
		addressInvoice = p_addressInvoice;
	}

	public PaymentDetail getPayment() {
		return payment;
	}

	public void setPayment(PaymentDetail p_payment) {
		payment = p_payment;
	}

	public OrderModel toOrderModel(String p_userId) {
		OrderModel order = new OrderModel();
		order.setUserId(p_userId);
		order.setStatus(OrderStatus.Created);

		order.setAddressShipping(addressShipping);
		order.setAddressInvoice(addressInvoice);

		order.setPayment(payment.toPaymentModel());

		return order;
	}
}
