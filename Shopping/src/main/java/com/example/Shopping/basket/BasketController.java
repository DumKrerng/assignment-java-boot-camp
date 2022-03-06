package com.example.Shopping.basket;

import org.springframework.beans.factory.annotation.*;
import org.springframework.web.bind.annotation.*;

@RestController
public class BasketController {

    @Autowired
    private BasketService m_service;

    @PostMapping("/api/v1/basket/product/{p_strProductCode}")
    public ResponseBasket addProduct(@PathVariable String p_strProductCode) {
        BasketModel result = m_service.addProduct(p_strProductCode);
        ResponseBasket response = new ResponseBasket();
        response.setData(new BasketViewModel(result));

        return response;
    }

    @GetMapping("/api/v1/basket")
    public ResponseBasket getBasketDetail() {
        BasketModel result = m_service.getBasketIsOpen();
        ResponseBasket response = new ResponseBasket();
        response.setData(new BasketViewModel(result));

        return response;
    }
}
