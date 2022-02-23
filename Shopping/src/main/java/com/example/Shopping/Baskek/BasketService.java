package com.example.Shopping.Baskek;

import java.util.*;
import com.example.Shopping.BasketItem.*;
import com.example.Shopping.Product.*;
import com.example.Shopping.utility.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.context.annotation.*;
import org.springframework.stereotype.*;
import org.springframework.transaction.annotation.*;

@Service
@Scope("singleton")
public class BasketService {

    @Autowired
    private BasketRepository m_repoBasket;

//    @Autowired
//    private BasketItemRepository m_repoBasketItem;

    @Autowired
    private ProductRepository m_repoProduct;

//    public void setMockRepository(ProductRepository p_repProduct, BasketRepository p_repBasket, BasketItemRepository p_repBasketItem) {
    public void setMockRepository(ProductRepository p_repProduct, BasketRepository p_repBasket) {
        m_repoProduct = p_repProduct;
        m_repoBasket = p_repBasket;
//        m_repoBasketItem = p_repBasketItem;
    }

    @Transactional
    public BasketModel addProduct(String p_strProductCode) {
        Optional<ProductModel> optProduct = m_repoProduct.findByProductCode(p_strProductCode);

        if(!optProduct.isPresent()) {
            throw new NotFoundException(p_strProductCode);
        }

        ProductModel modelProduct = optProduct.get();

        BasketModel modelBasket = new BasketModel();
        modelBasket.setBasketStatus(BasketStatus.OPEN.name());

        BasketItemModel modelBasketItem = new BasketItemModel();
        modelBasketItem.setBasket(modelBasket);
//        modelBasketItem.setBasketID(modelBasket.getBasketID());
        modelBasketItem.setProductID(modelProduct.getProductID());
        modelBasketItem.setUnitPrice(modelProduct.getUnitPrice());
        modelBasketItem.setQuantity(12);
        modelBasket.addBasketItem(modelBasketItem);

        modelBasket = m_repoBasket.save(modelBasket);
        modelBasket = m_repoBasket.getById(modelBasket.getBasketID());

        return modelBasket;
    }
}
