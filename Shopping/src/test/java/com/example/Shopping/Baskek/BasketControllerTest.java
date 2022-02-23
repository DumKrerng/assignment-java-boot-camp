package com.example.Shopping.Baskek;

import com.example.Shopping.BasketItem.BasketItemModel;
import com.example.Shopping.Product.ProductModel;
import com.example.Shopping.Product.ProductRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class BasketControllerTest {

    @Autowired
    private TestRestTemplate m_template;

    @Autowired
    private ProductRepository m_repoProduct;

    @BeforeAll
    public void setup() {
        ProductModel product = new ProductModel("ProductX", "ProductX");
        m_repoProduct.save(product);
    }

    @Test
    @DisplayName("ทดสอบ เพิ่ม Product: ProductX ลงในตะกร้า แล้วมี Product ในตะกร้า 1 ตัว")
    @Transactional
    void testAddProduct_01() {
        String strProductCode = "ProductX";
        ResponseBasket response = m_template.postForObject(
                String.format("/api/v1/basket/product/%s", strProductCode),
                "{}",
                ResponseBasket.class);
        BasketViewModel viewmodel = response.getData();
        BasketModel basket = viewmodel.getBasket();

        assertEquals(1, basket.getBasketItems().size());

        BasketModel modelBasket = viewmodel.getBasket();
        BasketItemModel modelBasketItem = basket.getBasketItems().get(0);
        String strProductID = modelBasketItem.getProductID();
        ProductModel modelProduct = m_repoProduct.getById(strProductID);

        assertEquals(BasketStatus.OPEN.name(), modelBasket.getBasketStatus());
        assertEquals(strProductCode, modelProduct.getProductCode());
    }

    @Test
    @DisplayName("ทดสอบ เพิ่ม Product: ProductZ ลงในตะกร้า แล้ว Response: HttpStatus.NOT_FOUND")
    @Transactional
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
}