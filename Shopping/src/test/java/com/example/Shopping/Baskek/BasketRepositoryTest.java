package com.example.Shopping.Baskek;

import com.example.Shopping.BasketItem.*;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.test.autoconfigure.orm.jpa.*;
import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
public class BasketRepositoryTest {

	@Autowired
	private BasketRepository m_repository;

	@Test
	@DisplayName("ทดสอบการสร้าง Basket ที่มี BasketItem 2 รายการ")
	public void testAddProduct() {
		BasketModel mockBasket = new BasketModel();
		BasketItemModel mockBasketItem = new BasketItemModel();
		mockBasketItem.setProductID("ProductID-1");
		mockBasketItem.setQuantity(1);
		mockBasketItem.setUnitPrice(10.50);
		mockBasket.addBasketItem(mockBasketItem);

		mockBasketItem = new BasketItemModel();
		mockBasketItem.setProductID("ProductID-2");
		mockBasketItem.setQuantity(1);
		mockBasketItem.setUnitPrice(5.50);
		mockBasket.addBasketItem(mockBasketItem);

		m_repository.save(mockBasket);

		Optional<BasketModel> optBasket = m_repository.findByBasketStatus(BasketStatus.OPEN.name());
		BasketModel basket = optBasket.get();
		List<BasketItemModel> lsBasketItems = basket.getBasketItems();

		assertEquals(2, lsBasketItems.size());

		BasketItemModel basketItem = lsBasketItems.get(0);
		assertEquals("ProductID-1", basketItem.getProductID());
		assertEquals(10.5, basketItem.getUnitPrice());

		basketItem = lsBasketItems.get(1);
		assertEquals("ProductID-2", basketItem.getProductID());
		assertEquals(5.5, basketItem.getUnitPrice());
	}
}
