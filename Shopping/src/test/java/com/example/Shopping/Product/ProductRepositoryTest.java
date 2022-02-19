package com.example.Shopping.Product;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class ProductRepositoryTest {

    @Autowired
    private ProductRepository m_repository;

    @BeforeEach
    public void initData(){
        m_repository.save(new ProductModel("ProductCodeX", "ProductNameX"));
        m_repository.save(new ProductModel("CodeX", "NameX"));
    }

    @Test
    @DisplayName("ทดสอบการค้นด้วย SearchValue = \"Product\" แล้ว ต้องพบข้อมูล Product 1 ตัว")
    void TestFindByProductCodeOrProductName_01() {
        String strSearchValue = "Product";
        Optional<List<ProductModel>> result = m_repository.findByProductCodeContainingOrProductNameContaining(strSearchValue, strSearchValue);
        List<ProductModel> lsProducts = result.get();

        assertTrue(result.isPresent());
        assertEquals(1, lsProducts.size());
    }

    @Test
    @DisplayName("ทดสอบการค้นด้วย SearchValue = \"Product\" แล้ว ต้องพบข้อมูล Product 2 ตัว")
    void TestFindByProductCodeOrProductName_02() {
        String strSearchValue = "X";
        Optional<List<ProductModel>> result = m_repository.findByProductCodeContainingOrProductNameContaining(strSearchValue, strSearchValue);
        List<ProductModel> lsProducts = result.get();

        assertTrue(result.isPresent());
        assertEquals(2, lsProducts.size());
    }

    @Test
    @DisplayName("ทดสอบการค้นด้วย SearchValue = \"ProductX\" แล้ว ต้องไม่พบข้อมูล")
    void TestFindByProductCodeOrProductName_03() {
        String strSearchValue = "ProductX";
        Optional<List<ProductModel>> result = m_repository.findByProductCodeContainingOrProductNameContaining(strSearchValue, strSearchValue);
        List<ProductModel> lsProducts = result.get();

        assertTrue(result.isPresent());
        assertEquals(0, lsProducts.size());
    }
}