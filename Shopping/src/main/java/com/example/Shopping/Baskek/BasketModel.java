package com.example.Shopping.Baskek;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class BasketModel {

	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	private String basketID;

	private String basketStatus;

	public BasketModel() {}

	public void setBasketStatus(String basketStatus){
		this.basketStatus = basketStatus;
	}

	public String getBasketStatus(){
		return basketStatus;
	}

	public void setBasketID(String basketID){
		this.basketID = basketID;
	}

	public String getBasketID(){
		return basketID;
	}
}
