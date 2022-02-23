package com.example.Shopping.Baskek;

import com.example.Shopping.BasketItem.BasketItemRepository;
import com.example.Shopping.Product.ProductModel;
import com.example.Shopping.Product.ProductRepository;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class BasketServiceTest {

    @Autowired
    private ProductRepository m_repProduct;

    @Autowired
    private BasketRepository m_repBasket;

    @Autowired
    private BasketItemRepository m_repBasketItem;

    @BeforeAll
    public void setup() {
        ProductModel product = new ProductModel("ProductX", "ProductX");
        m_repProduct.save(product);
    }

    @Test
    @DisplayName("ทดสอบ เพิ่ม Product ลงตะกร้า แล้วมี 1 ชิ้นในตะกร้า")
    void testAddProduct_01() {
        String strProductCode = "ProductX";
        BasketService service = new BasketService();
//        service.setMockRepository(m_repProduct, m_repBasket, m_repBasketItem);
        service.setMockRepository(m_repProduct, m_repBasket);
        BasketModel basket = service.addProduct(strProductCode);

        assertEquals(1, basket.getBasketItems().size());
    }

    @Test
    @DisplayName("ทดสอบ เพิ่ม Product ลงตะกร้า แล้วไม่พบ Product ในระบบ")
    void testAddProduct_02() {
        String strProductCode = "ProductZ";
        BasketService service = new BasketService();
//        service.setMockRepository(m_repProduct, m_repBasket, m_repBasketItem);
        service.setMockRepository(m_repProduct, m_repBasket);
        BasketModel viewBasket = service.addProduct(strProductCode);

        assertNull(viewBasket);
    }
}