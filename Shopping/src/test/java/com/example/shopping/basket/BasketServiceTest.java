package com.example.shopping.basket;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

import com.example.shopping.product.*;
import com.example.shopping.utility.*;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.test.autoconfigure.orm.jpa.*;

@DataJpaTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class BasketServiceTest {

    @Autowired
    private ProductRepository m_repoProduct;

    @Autowired
    private BasketRepository m_repoBasket;

//    ProductModel product = new ProductModel("ProductX", "ProductX");
//		product.setUnitPrice(10);
//		m_repoProduct.save(product);
//
//    product = new ProductModel("ProductA", "ProductA");
//		product.setUnitPrice(13);
//		m_repoProduct.save(product);
//
//    product = new ProductModel("ProductCodeX", "ProductNameX");
//		product.setUnitPrice(3);
//		m_repoProduct.save(product);
//
//    product = new ProductModel("CodeX", "NameX");
//		product.setUnitPrice(30);
//		m_repoProduct.save(product);
//
//    product = new ProductModel("เสื้อX", "เสื้อX");
//		product.setUnitPrice(30.50);
//		m_repoProduct.save(product);


    @Test
    @DisplayName("ทดสอบ เพิ่ม Product ลงตะกร้า แล้วมี 1 ชิ้นในตะกร้า")
    void testAddProduct_01() {
        String strProductCode = "ProductX";
        BasketService service = new BasketService();
        service.setMockRepository(m_repoProduct, m_repoBasket);
        BasketModel basket = service.addProduct(strProductCode);

        assertEquals(1, basket.getBasketItems().size());
    }

    @Test
    @DisplayName("ทดสอบ เพิ่ม Product ลงตะกร้า แล้วไม่พบ Product ในระบบ")
    void testAddProduct_02() {
        String strProductCode = "ProductZ";
        BasketService service = new BasketService();
        service.setMockRepository(m_repoProduct, m_repoBasket);

        NotFoundException result = assertThrows(NotFoundException.class, () -> {
            service.addProduct(strProductCode);
        });

        assertEquals("ProductZ is not found!", result.getMessage());
    }

    @Test
    @DisplayName("ทดสอบ เพิ่ม Product ลงตะกร้า แล้วไม่พบ Product ในระบบ")
    void testAddProduct_03() {
        String strProductCode = "ProductZ";
        BasketService service = new BasketService();
        service.setMockRepository(m_repoProduct, m_repoBasket);

        NotFoundException result = assertThrows(NotFoundException.class, () -> {
            service.addProduct(strProductCode);
        });

        assertEquals("ProductZ is not found!", result.getMessage());
    }

    @Test
    @DisplayName("ทดสอบ ดึงข้อมูล Basket ที่เป็น Status: OPEN แล้ว พบข้อมูล")
    void testGetBasketIsOpen_01() {
        Optional<ProductModel> optProduct = m_repoProduct.findByProductCode("ProductX");
        ProductModel product = optProduct.get();

        BasketModel mockBasket = new BasketModel();
        BasketItemModel mockBasketItem = new BasketItemModel(mockBasket);
        mockBasketItem.setProductID(product.getProductID());
        mockBasketItem.setQuantity(1);
        mockBasketItem.setUnitPrice(10.50);
        mockBasket.addBasketItem(mockBasketItem);

        mockBasketItem = new BasketItemModel(mockBasket);
        mockBasketItem.setProductID(product.getProductID());
        mockBasketItem.setQuantity(1);
        mockBasketItem.setUnitPrice(5.50);
        mockBasket.addBasketItem(mockBasketItem);

        m_repoBasket.save(mockBasket);

        BasketService service = new BasketService();
        service.setMockRepository(m_repoProduct, m_repoBasket);
        BasketModel basket = service.getBasketIsOpen();

        assertNotNull(basket);

        List<BasketItemModel> basketItems = basket.getBasketItems();
        assertEquals(2, basketItems.size());

        BasketItemModel basketItem = basketItems.get(0);
        assertEquals(10.50, basketItem.getUnitPrice());

        basketItem = basketItems.get(1);
        assertEquals(5.50, basketItem.getUnitPrice());
    }

    @Test
    @DisplayName("ทดสอบ ดึงข้อมูล Basket ที่เป็น Status: OPEN แล้ว ไม่พบข้อมูล")
    void testGetBasketIsOpen_02() {
        Optional<ProductModel> optProduct = m_repoProduct.findByProductCode("ProductX");
        ProductModel product = optProduct.get();

        BasketModel mockBasket = new BasketModel();
        BasketItemModel mockBasketItem = new BasketItemModel(mockBasket);
        mockBasketItem.setProductID(product.getProductID());
        mockBasketItem.setProductLabel(product.getProductLabel());
        mockBasketItem.setQuantity(1);
        mockBasketItem.setUnitPrice(10.50);
        mockBasket.addBasketItem(mockBasketItem);

        mockBasketItem = new BasketItemModel(mockBasket);
        mockBasketItem.setProductID(product.getProductID());
        mockBasketItem.setProductLabel(product.getProductLabel());
        mockBasketItem.setQuantity(1);
        mockBasketItem.setUnitPrice(5.50);
        mockBasket.addBasketItem(mockBasketItem);

        mockBasket.setBasketStatus(BasketStatus.CLOSED.name());
        m_repoBasket.save(mockBasket);

        BasketService service = new BasketService();
        service.setMockRepository(m_repoProduct, m_repoBasket);

        NotFoundException result = assertThrows(NotFoundException.class, () -> {
            service.getBasketIsOpen();
        });

        assertEquals("Basket Open is not found!", result.getMessage());
    }
}