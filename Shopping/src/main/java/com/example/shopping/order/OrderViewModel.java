package com.example.shopping.order;

import java.time.*;

public class OrderViewModel {

	private String payer;
	private String orderNumber;
	private String invoiceNumber;
	private LocalDateTime transactionDate;

	public OrderViewModel() {}

	public OrderViewModel(OrderModel p_order) {
		payer = p_order.getPayer();
		orderNumber = p_order.getOrderNumber();
		invoiceNumber = p_order.getInvoiceNumber();
		transactionDate = p_order.getTransactionDate();
	}

	public void setPayer(String p_payer){
		this.payer = p_payer;
	}

	public String getPayer(){
		return payer;
	}

	public void setOrderNumber(String p_orderNumber){
		this.orderNumber = p_orderNumber;
	}

	public String getOrderNumber(){
		return orderNumber;
	}

	public void setInvoiceNumber(String p_invoiceNumber){
		this.invoiceNumber = p_invoiceNumber;
	}

	public String getInvoiceNumber(){
		return invoiceNumber;
	}

	public void setTransactionDate(LocalDateTime p_transactionDate){
		this.transactionDate = p_transactionDate;
	}

	public LocalDateTime getTransactionDate(){
		return transactionDate;
	}
}
