package com.example.shopping.order;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.*;
import java.time.*;
import com.example.shopping.payment.*;
import com.example.shopping.user.*;
import org.hibernate.annotations.*;

@Entity
public class OrderModel {

	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	private String id;

	private String userId;
	private String payer;
	private String orderNumber;
	private String invoiceNumber;
	private LocalDateTime transactionDate;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "address_shipping_id")
	private AddressModel addressShipping;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "address_invoice_id")
	private AddressModel addressInvoice;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "payment_id")
	private PaymentModel payment;

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

	public PaymentModel getPayment() {
		return payment;
	}

	public void setPayment(PaymentModel p_payment) {
		payment = p_payment;
	}

	public String getStatus() {
		return status;
	}

	public OrderStatus getOrderStatus() {
		return OrderStatus.getOrderStatus(status);
	}

	public void setStatus(String p_status) {
		status = p_status;
	}

	public void setStatus(OrderStatus p_status) {
		status = p_status.name();
	}

	public String getOrderNumber() {
		return orderNumber;
	}

	public void setOrderNumber(String p_orderNumber) {
		orderNumber = p_orderNumber;
	}

	public String getInvoiceNumber() {
		return invoiceNumber;
	}

	public void setInvoiceNumber(String p_invoiceNumber) {
		invoiceNumber = p_invoiceNumber;
	}

	public LocalDateTime getTransactionDate() {
		return transactionDate;
	}

	public void setTransactionDate(LocalDateTime p_transactionDate) {
		transactionDate = p_transactionDate;
	}

	public String getPayer() {
		return payer;
	}

	public void setPayer(String p_payer) {
		payer = p_payer;
	}
}
