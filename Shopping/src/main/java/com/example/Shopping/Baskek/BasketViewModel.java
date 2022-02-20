package com.example.Shopping.Baskek;

import com.example.Shopping.BasketItem.BasketItemModel;

import java.util.ArrayList;
import java.util.List;

public class BasketViewModel {

    private BasketModel m_basket;
    private List<BasketItemModel> m_basketItems;

    public BasketViewModel() {
//        m_basketItems = new ArrayList<>();
    }

    public BasketViewModel(BasketModel p_modelBasket, BasketItemModel p_modelBasketItem) {
        m_basket = p_modelBasket;
        m_basketItems = new ArrayList<>();
        m_basketItems.add(p_modelBasketItem);
    }

    public BasketModel getBasket() {
        return m_basket;
    }

    public void setBasket(BasketModel p_basket) {
        this.m_basket = p_basket;
    }

    public List<BasketItemModel> getBasketItems() {
        return m_basketItems;
    }

    public void setBasketItems(List<BasketItemModel> p_lsBasketItems) {
        this.m_basketItems = p_lsBasketItems;
    }
}
