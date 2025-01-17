package com.example.shopping.product;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.test.autoconfigure.orm.jpa.*;

@DataJpaTest
class ProductRepositoryTest {

    @Autowired
    private ProductRepository m_repository;

    @Test
    @DisplayName("ทดสอบการค้นด้วย SearchValue = \"Product\" แล้ว ต้องพบข้อมูล Product 1 ตัว")
    void TestFindByProductCodeOrProductName_01() {
        String strSearchValue = "Product";
        Optional<List<ProductModel>> result = m_repository.findByProductCodeContainingIgnoreCaseOrProductNameContainingIgnoreCase(strSearchValue, strSearchValue);
        List<ProductModel> lsProducts = result.get();

        assertTrue(result.isPresent());
        assertEquals(3, lsProducts.size());
    }

    @Test
    @DisplayName("ทดสอบการค้นด้วย SearchValue = \"X\" แล้ว ต้องพบข้อมูล Product 2 ตัว")
    void TestFindByProductCodeOrProductName_02() {
        String strSearchValue = "X";
        Optional<List<ProductModel>> result = m_repository.findByProductCodeContainingIgnoreCaseOrProductNameContainingIgnoreCase(strSearchValue, strSearchValue);
        List<ProductModel> lsProducts = result.get();

        assertTrue(result.isPresent());
        assertEquals(4, lsProducts.size());
    }

    @Test
    @DisplayName("ทดสอบการค้นด้วย SearchValue = \"ProductX\" แล้ว ต้องไม่พบข้อมูล")
    void TestFindByProductCodeOrProductName_03() {
        String strSearchValue = "ProductX";
        Optional<List<ProductModel>> result = m_repository.findByProductCodeContainingIgnoreCaseOrProductNameContainingIgnoreCase(strSearchValue, strSearchValue);
        List<ProductModel> lsProducts = result.get();

        assertTrue(result.isPresent());
        assertEquals(1, lsProducts.size());
    }

    @Test
    @DisplayName("ทดสอบการค้นด้วย ProductCode = \"CodeX\" แล้ว ต้องพบข้อมูล")
    void TestFindByProductCode_01() {
        String strProductCode = "CodeX";
        Optional<ProductModel> result = m_repository.findByProductCode(strProductCode);

        assertTrue(result.isPresent());
    }

    @Test
    @DisplayName("ทดสอบการค้นด้วย ProductCode = \"รองเท้า\" แล้ว ต้องไม่พบข้อมูล")
    void TestFindByProductCode_02() {
        String strProductCode = "รองเท้า";
        Optional<ProductModel> result = m_repository.findByProductCode(strProductCode);

        assertFalse(result.isPresent());
    }
}