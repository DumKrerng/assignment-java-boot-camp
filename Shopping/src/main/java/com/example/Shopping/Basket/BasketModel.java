package com.example.Shopping.Basket;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.*;
import java.util.*;
import org.hibernate.annotations.*;

@NamedEntityGraph(
	name = "Basket-With-BasketItem",
	attributeNodes = {
		@NamedAttributeNode("id"),
		@NamedAttributeNode("basketStatus"),
		@NamedAttributeNode("basketItems"),
	}
)

@Entity
public class BasketModel {

	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	private String id;

	private String basketStatus = BasketStatus.OPEN.name();

	//	@OneToMany(mappedBy = "basket", fetch = FetchType.LAZY) //Default
	@OneToMany(mappedBy = "basket", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private List<BasketItemModel> basketItems = new ArrayList<>();

	public BasketModel() {}

	public void setBasketStatus(String p_strBasketStatus){
		this.basketStatus = p_strBasketStatus;
	}

	public String getBasketStatus(){
		return basketStatus;
	}

	public void setId(String p_strID){
		this.id = p_strID;
	}

	public String getId(){
		return id;
	}

	public void setBasketItems(List<BasketItemModel> p_lsBasketItems) {
		this.basketItems = p_lsBasketItems;
	}

	public void addBasketItem(BasketItemModel p_basketItems) {
		this.basketItems.add(p_basketItems);
	}

	public List<BasketItemModel> getBasketItems() {
		return basketItems;
	}
}
