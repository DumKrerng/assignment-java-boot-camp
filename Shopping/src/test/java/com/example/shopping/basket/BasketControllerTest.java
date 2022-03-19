package com.example.shopping.basket;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.test.context.*;
import org.springframework.boot.test.web.client.*;
import org.springframework.http.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class BasketControllerTest {

    @Autowired
    private TestRestTemplate m_template;

    @Autowired
    private BasketRepository m_repoBasket;

    @BeforeEach
    void deleteAllBasket() {
        m_repoBasket.deleteAll();
    }

    @Test
    @DisplayName("ทดสอบ เพิ่ม Product: ProductX ลงในตะกร้า แล้วมี Product ในตะกร้า 1 ตัว")
    void testAddProduct_01() {
        String strProductCode = "ProductX";
        ResponseBasket response = m_template.postForObject(
                String.format("/api/v1/basket/product/%s", strProductCode),
                "{}",
                ResponseBasket.class);
        BasketViewModel viewmodel = response.getData();
        List<BasketItemViewModel> Itemmodels = viewmodel.getBasketItems();

        assertEquals(1, Itemmodels.size());

        BasketItemViewModel modelBasketItem = Itemmodels.get(0);

        assertEquals(BasketStatus.OPEN.name(), viewmodel.getBasketStatus());
        assertEquals(10, modelBasketItem.getUnitPrice());
        assertEquals(1, modelBasketItem.getQuantity());
    }

    @Test
    @DisplayName("ทดสอบ เพิ่ม Product: ProductZ ลงในตะกร้า แล้ว Response: HttpStatus.NOT_FOUND")
    void testAddProduct_02() {
        String strProductCode = "ProductZ";
        ResponseEntity<ResponseBasket> response = m_template.postForEntity(
                String.format("/api/v1/basket/product/%s", strProductCode),
                "{}",
                ResponseBasket.class);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());

        ResponseBasket body = response.getBody();
        assertEquals(String.format("%s is not found!", strProductCode), body.getMessage());
        assertNull(body.getData());
    }

    @Test
    @DisplayName("ทดสอบ ดึงข้อมูล Basket Open แล้ว พบข้อมูล")
    void testGetBasketDetail_01() {
        BasketModel mockBasket = new BasketModel();
        BasketItemModel mockBasketItem = new BasketItemModel(mockBasket);
        mockBasketItem.setProductID("ProductID-1");
        mockBasketItem.setQuantity(1);
        mockBasketItem.setUnitPrice(10.50);
        mockBasket.addBasketItem(mockBasketItem);

        mockBasketItem = new BasketItemModel(mockBasket);
        mockBasketItem.setProductID("ProductID-2");
        mockBasketItem.setQuantity(1);
        mockBasketItem.setUnitPrice(5.50);
        mockBasket.addBasketItem(mockBasketItem);

        m_repoBasket.save(mockBasket);

        ResponseEntity<ResponseBasket> response = m_template.getForEntity("/api/v1/basket", ResponseBasket.class);

        assertEquals(HttpStatus.OK, response.getStatusCode(), response.getBody().getMessage());

        ResponseBasket body = response.getBody();
        BasketViewModel view = body.getData();
        assertEquals(BasketStatus.OPEN.name(), view.getBasketStatus());

        List<BasketItemViewModel> basketItems = view.getBasketItems();
        assertEquals(2, basketItems.size());

        BasketItemViewModel basketItem = basketItems.get(0);
        assertEquals(10.50, basketItem.getUnitPrice());

        basketItem = basketItems.get(1);
        assertEquals(5.50, basketItem.getUnitPrice());
    }
}