package com.example.Shopping.Baskek;

import javax.persistence.Entity;
import javax.persistence.*;
import java.util.*;
import com.example.Shopping.BasketItem.*;
import org.hibernate.annotations.*;

//@NamedEntityGraph(
//	name = "Basket-With-BasketItem",
//	attributeNodes = {
//		@NamedAttributeNode("id"),
//		@NamedAttributeNode("basketStatus"),
//		@NamedAttributeNode("basketItems")
//	}
//)

@Entity
public class BasketModel {

	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	private String id;

	private String basketStatus;

	@OneToMany(mappedBy = "basket", fetch = FetchType.EAGER)
//	@OneToMany(mappedBy = "basket", fetch = FetchType.LAZY) //Default
	private List<BasketItemModel> basketItems;

	public BasketModel() {
		this.basketItems = new ArrayList<>();
		this.basketStatus = BasketStatus.OPEN.name();
	}

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

	public void addBasketItem(BasketItemModel p_basketItemModel) {
		this.basketItems.add(p_basketItemModel);
	}

	public List<BasketItemModel> getBasketItems() {
		return basketItems;
	}
}
