package com.example.shopping.order;

import com.example.shopping.payment.*;
import com.example.shopping.user.*;

public class RequestOrder {

	private AddressModel shippingDetail;
	private AddressModel invoiceDetail;

	private PaymentDetail paymentDetail;

	public AddressModel getShippingDetail() {
		return shippingDetail;
	}

	public void setShippingDetail(AddressModel p_shippingDetail) {
		shippingDetail = p_shippingDetail;
	}

	public AddressModel getInvoiceDetail() {
		return invoiceDetail;
	}

	public void setInvoiceDetail(AddressModel p_invoiceDetail) {
		invoiceDetail = p_invoiceDetail;
	}

	public PaymentDetail getPaymentDetail() {
		return paymentDetail;
	}

	public void setPaymentDetail(PaymentDetail p_paymentDetail) {
		paymentDetail = p_paymentDetail;
	}

	public OrderModel toOrderModel(String p_userId, String p_userLabel) {
		OrderModel order = new OrderModel();
		order.setUserId(p_userId);
		order.setPayer(p_userLabel);
		order.setStatus(OrderStatus.Created);

		order.setAddressShipping(shippingDetail);
		order.setAddressInvoice(invoiceDetail);

		order.setPayment(paymentDetail.toPaymentModel());

		return order;
	}
}
