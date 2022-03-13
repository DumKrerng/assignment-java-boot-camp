package com.example.shopping.order;

import com.example.shopping.payment.*;
import com.example.shopping.user.*;
import org.hibernate.annotations.*;
import javax.persistence.*;
import javax.persistence.Entity;

@Entity
public class OrderModel {

	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	private String id;

	private String userId;

	@OneToOne
	@JoinColumn(name = "address_shipping_id")
	private AddressModel addressShipping;

	@OneToOne
	@JoinColumn(name = "address_invoice_id")
	private AddressModel addressInvoice;

	@OneToOne
	@JoinColumn(name = "payment_id")
	private PaymentDetail payment;

	private String status = OrderStatus.Created.name();

	public OrderModel() {}

	public String getId() {
		return id;
	}

	public void setId(String p_id) {
		id = p_id;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String p_userId) {
		userId = p_userId;
	}

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

	public String getStatus() {
		return status;
	}

	public void setStatus(String p_status) {
		status = p_status;
	}

	public void setStatus(OrderStatus p_status) {
		status = p_status.name();
	}
}
