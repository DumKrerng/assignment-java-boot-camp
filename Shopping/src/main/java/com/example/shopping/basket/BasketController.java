package com.example.shopping.basket;

import org.slf4j.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.web.bind.annotation.*;

@RestController
public class BasketController {

    private static final Logger log = LoggerFactory.getLogger(BasketController.class);

    @Autowired
    private BasketService m_service;

    @PostMapping("/api/v1/basket/product/{p_strProductCode}")
    public ResponseBasket addProduct(@PathVariable String p_strProductCode) {
        log.info("API Called: addProduct, ProductCode: " + p_strProductCode);

        BasketModel basket = m_service.addProduct(p_strProductCode);
        ResponseBasket response = new ResponseBasket();
        response.setData(new BasketViewModel(basket));

        return response;
    }

    @GetMapping("/api/v1/basket")
    public ResponseBasket getBasketDetail() {
        log.info("API Called: getBasketDetail");

        BasketModel basket = m_service.getBasketIsOpen();
        ResponseBasket response = new ResponseBasket();
        response.setData(new BasketViewModel(basket));

        return response;
    }
}
