package com.example.shopping.basket;

import java.util.*;

public class BasketItemViewModel {
	private double unitPrice;
	private int quantity;
	private String productID;
	private String productLabel;

	public BasketItemViewModel() {}

	public BasketItemViewModel(BasketItemModel p_basketItemModel) {
		productID = p_basketItemModel.getProductID();
		productLabel = p_basketItemModel.getProductLabel();
		unitPrice = p_basketItemModel.getUnitPrice();
		quantity = p_basketItemModel.getQuantity();
	}

	public void setUnitPrice(double p_unitPrice){
		this.unitPrice = p_unitPrice;
	}

	public double getUnitPrice(){
		return unitPrice;
	}

	public void setQuantity(int p_quantity){
		this.quantity = p_quantity;
	}

	public int getQuantity(){
		return quantity;
	}

	public void setProductID(String p_productID){
		this.productID = p_productID;
	}

	public String getProductID(){
		return productID;
	}

	public void setProductLabel(String p_productLabel){
		this.productLabel = p_productLabel;
	}

	public String getProductLabel(){
		return productLabel;
	}

	public static List<BasketItemViewModel> transform(List<BasketItemModel> p_items) {
		List<BasketItemViewModel> lsBasketItemViewModels = new ArrayList<>();

		for(BasketItemModel itemmodel : p_items) {
			BasketItemViewModel viewmodel = new BasketItemViewModel(itemmodel);
			lsBasketItemViewModels.add(viewmodel);
		}

		return lsBasketItemViewModels;
	}
}
