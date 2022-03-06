package com.example.Shopping.basket;

import java.util.*;

public class BasketViewModel {

    private BasketModel m_basket;

    public BasketViewModel() {}

    public BasketViewModel(BasketModel p_modelBasket) {
        m_basket = p_modelBasket;
        List<BasketItemModel> lsBasketItems = p_modelBasket.getBasketItems();

        for(BasketItemModel item : lsBasketItems) {
            item.setBasket(null);
        }
    }

    public BasketModel getBasket() {
        return m_basket;
    }

    public void setBasket(BasketModel p_basket) {
        this.m_basket = p_basket;
    }
}
