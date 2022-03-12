package com.example.shopping.basket;

import java.util.List;

public class BasketViewModel {
	private String basketStatus;
	private List<BasketItemViewModel> basketItems;
	private String basketID;

	public BasketViewModel() {}

	public BasketViewModel(BasketModel p_basket) {
		basketID = p_basket.getId();
		basketStatus = p_basket.getBasketStatus();
		basketItems = BasketItemViewModel.transform(p_basket.getBasketItems());
	}

	public void setBasketStatus(String p_basketStatus){
		this.basketStatus = p_basketStatus;
	}

	public String getBasketStatus(){
		return basketStatus;
	}

	public void setBasketItems(List<BasketItemViewModel> p_lsBasketItems){
		this.basketItems = p_lsBasketItems;
	}

	public List<BasketItemViewModel> getBasketItems(){
		return basketItems;
	}

	public void setBasketID(String p_basketID){
		this.basketID = p_basketID;
	}

	public String getBasketID(){
		return basketID;
	}
}