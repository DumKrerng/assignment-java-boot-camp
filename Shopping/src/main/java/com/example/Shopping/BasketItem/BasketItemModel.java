package com.example.Shopping.BasketItem;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class BasketItemModel {

	@Id
	@GeneratedValue
	private int basketItemID;

	private String basketID;
	private String productID;
	private double unitPrice;
	private int quantity;

	public BasketItemModel() {}

	public void setUnitPrice(double p_dubUnitPrice){
		this.unitPrice = p_dubUnitPrice;
	}

	public double getUnitPrice(){
		return unitPrice;
	}

	public void setQuantity(int p_intQuantity){
		this.quantity = p_intQuantity;
	}

	public int getQuantity(){
		return quantity;
	}

	public void setProductID(String p_strProductID){
		this.productID = p_strProductID;
	}

	public String getProductID(){
		return productID;
	}

	public void setBasketID(String p_strBasketID){
		this.basketID = p_strBasketID;
	}

	public String getBasketID(){
		return basketID;
	}

	public void setBasketItemID(int p_intBasketItemID){
		this.basketItemID = p_intBasketItemID;
	}

	public int getBasketItemID(){
		return basketItemID;
	}
}
