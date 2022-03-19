package com.example.shopping.basket;

import java.util.*;
import com.example.shopping.product.*;
import com.example.shopping.utility.*;
import org.slf4j.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;
import org.springframework.transaction.annotation.*;

@Service
//@Scope("singleton")
public class BasketService {

    private static final Logger log = LoggerFactory.getLogger(BasketService.class);

    @Autowired
    private BasketRepository m_repoBasket;

    @Autowired
    private ProductRepository m_repoProduct;

    public void setMockRepository(ProductRepository p_repProduct, BasketRepository p_repBasket) {
        m_repoProduct = p_repProduct;
        m_repoBasket = p_repBasket;
    }

    @Transactional
    public BasketModel getBasketIsOpen() {
        log.info("Get Basket is Open.");

        Optional<BasketModel> optBasket = m_repoBasket.findByBasketStatus(BasketStatus.OPEN.name());
        if(!optBasket.isPresent()) {
            throw new NotFoundException("Basket Open");
        }

        BasketModel modelBasket = optBasket.get();

        return modelBasket;
    }

    @Transactional
    public BasketModel addProduct(String p_strProductCode) {
        log.info("Add Product, ProductCode: " + p_strProductCode);

        Optional<ProductModel> optProduct = m_repoProduct.findByProductCode(p_strProductCode);
        if(!optProduct.isPresent()) {
            throw new NotFoundException(p_strProductCode);
        }

        ProductModel modelProduct = optProduct.get();
        BasketModel modelBasket;

        try {
            modelBasket = getBasketIsOpen();

        } catch(NotFoundException exc) {
            modelBasket = new BasketModel();
        }

        BasketItemModel modelBasketItem = new BasketItemModel(modelBasket);
        modelBasketItem.setBasket(modelBasket);
        modelBasketItem.setProductID(modelProduct.getProductID());
        modelBasketItem.setProductLabel(modelProduct.getProductLabel());
        modelBasketItem.setUnitPrice(modelProduct.getUnitPrice());
        modelBasketItem.setQuantity(1);
        modelBasket.addBasketItem(modelBasketItem);

        modelBasket = m_repoBasket.save(modelBasket);
        modelBasket = m_repoBasket.getById(modelBasket.getId());

        return modelBasket;
    }
}
