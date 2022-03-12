package com.example.shopping.basket;

import org.springframework.beans.factory.annotation.*;
import org.springframework.web.bind.annotation.*;

@RestController
public class BasketController {

    @Autowired
    private BasketService m_service;

    @PostMapping("/api/v1/basket/product/{p_strProductCode}")
    public ResponseBasket addProduct(@PathVariable String p_strProductCode) {
        BasketModel basket = m_service.addProduct(p_strProductCode);
        ResponseBasket response = new ResponseBasket();
        response.setData(new BasketViewModel(basket));

        return response;
    }

    @GetMapping("/api/v1/basket")
    public ResponseBasket getBasketDetail() {
        BasketModel basket = m_service.getBasketIsOpen();
        ResponseBasket response = new ResponseBasket();
        response.setData(new BasketViewModel(basket));

        return response;
    }
}
