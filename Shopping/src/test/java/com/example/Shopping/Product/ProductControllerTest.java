package com.example.Shopping.Product;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class ProductControllerTest {

    @Autowired
    private TestRestTemplate m_template;

    @Autowired
    private ProductRepository m_repository;

//    @BeforeAll
//    public void setup() {
//        m_repository.save(new ProductModel("ProductCodeX", "ProductNameX"));
//        m_repository.save(new ProductModel("CodeX", "NameX"));
//        m_repository.save(new ProductModel("เสื้อX", "เสื้อX"));
//    }

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
    @DisplayName("ทดสอบ ค้นหา Product ด้วย \"Code\" แล้ว พบข้อมูล 2 ตัว")
    void TestSearch_01() {
        ResponseProductSearch response = m_template.getForObject("/api/v1/search/product/Code", ResponseProductSearch.class);
        List<ProductModel> lsProducts = response.getData();

        assertEquals(2, lsProducts.size());
    }

    @Test
    @DisplayName("ทดสอบ ค้นหา Product ด้วย \"Product\" แล้ว พบข้อมูล 1 ตัว")
    void TestSearch_02() {
        ResponseProductSearch response = m_template.getForObject("/api/v1/search/product/Product", ResponseProductSearch.class);
        List<ProductModel> lsProducts = response.getData();

        assertEquals(3, lsProducts.size());
    }

    @Test
    @DisplayName("ทดสอบ ค้นหา Product ด้วย \"product\" แล้ว พบข้อมูล 1 ตัว")
    void TestSearch_03() {
        ResponseProductSearch response = m_template.getForObject("/api/v1/search/product/product", ResponseProductSearch.class);
        List<ProductModel> lsProducts = response.getData();

        assertEquals(3, lsProducts.size());
    }

    @Test
    @DisplayName("ทดสอบ ค้นหา Product ด้วย \"รองเท้า\" แล้ว ไม่พบข้อมูล")
    void TestSearch_04() {
        ResponseProductSearch response = m_template.getForObject("/api/v1/search/product/รองเท้า", ResponseProductSearch.class);
        List<ProductModel> lsProducts = response.getData();

        assertEquals(0, lsProducts.size());
    }

    @Test
    @DisplayName("ทดสอบ ค้นหา Product ด้วย \"เสื้อx\" แล้ว พบข้อมูล 1 ตัว")
    void TestSearch_05() {
        ResponseProductSearch response = m_template.getForObject("/api/v1/search/product/เสื้อx", ResponseProductSearch.class);
        List<ProductModel> lsProducts = response.getData();

        assertEquals(1, lsProducts.size());
    }
}