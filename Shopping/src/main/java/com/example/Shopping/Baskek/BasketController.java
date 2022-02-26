package com.example.Shopping.Baskek;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BasketController {

    @Autowired
    private BasketService m_service;

    @PostMapping("/api/v1/basket/product/{p_strProductCode}")
    public ResponseBasket addProduct(@PathVariable String p_strProductCode) {
        BasketViewModel result = m_service.addProduct(p_strProductCode);
        ResponseBasket response = new ResponseBasket();
        response.setData(result);

        return response;
    }
}
