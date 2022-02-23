package com.example.Shopping.Baskek;

import javax.persistence.Entity;
import javax.persistence.*;
import java.util.*;
import com.example.Shopping.BasketItem.*;
import org.hibernate.annotations.*;

@Entity
public class BasketModel {

	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	private String basketID;

	private String basketStatus;

	@OneToMany(mappedBy = "basket", fetch = FetchType.EAGER)
//	@OneToMany(mappedBy = "basket", fetch = FetchType.LAZY) //Default
	private List<BasketItemModel> basketItems;

	public BasketModel() {
		this.basketItems = new ArrayList<>();
	}

	public void setBasketStatus(String p_strBasketStatus){
		this.basketStatus = p_strBasketStatus;
	}

	public String getBasketStatus(){
		return basketStatus;
	}

	public void setBasketID(String p_strBasketID){
		this.basketID = p_strBasketID;
	}

	public String getBasketID(){
		return basketID;
	}

	public void setBasketItemModels(List<BasketItemModel> p_lsBasketItems) {
		this.basketItems = p_lsBasketItems;
	}

	public void addBasketItem(BasketItemModel p_basketItemModel) {
		this.basketItems.add(p_basketItemModel);
	}

	public List<BasketItemModel> getBasketItems() {
		return basketItems;
	}

	/*
	*
    @Column(name = "last_name")
    private String lastName;

    @OneToMany
    private Set<Role> roles;
	*
	* */
}
