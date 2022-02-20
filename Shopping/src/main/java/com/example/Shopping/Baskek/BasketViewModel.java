package com.example.Shopping.Baskek;

import com.example.Shopping.BasketItem.BasketItemModel;

import java.util.ArrayList;
import java.util.List;

public class BasketViewModel {

    private BasketModel m_basket;
    private List<BasketItemModel> m_lsBasketItems;

    public BasketViewModel(BasketModel p_modelBasket, BasketItemModel p_modelBasketItem) {
        m_basket = p_modelBasket;
        m_lsBasketItems = new ArrayList<>();
        m_lsBasketItems.add(p_modelBasketItem);
    }

    public BasketModel getBasket() {
        return m_basket;
    }

    public void setM_basket(BasketModel p_basket) {
        this.m_basket = p_basket;
    }

    public List<BasketItemModel> getBasketItems() {
        return m_lsBasketItems;
    }

    public void setM_lsBasketItems(List<BasketItemModel> p_lsBasketItems) {
        this.m_lsBasketItems = p_lsBasketItems;
    }
}
