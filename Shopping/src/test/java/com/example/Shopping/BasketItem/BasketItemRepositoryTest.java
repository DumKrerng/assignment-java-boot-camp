package com.example.Shopping.BasketItem;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class BasketItemRepositoryTest {

    @Autowired
    private BasketItemRepository m_repository;

    @Test
    void testSave() {
        BasketItemModel model = new BasketItemModel();
        model.setBasketID("BasketID");
        model.setProductID("ProductID");
        model.setQuantity(1);

        BasketItemModel result = m_repository.save(model);

        assertEquals(model.getBasketID(), result.getBasketID());
        assertEquals(model.getProductID(), result.getProductID());
    }
}