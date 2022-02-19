package com.example.Shopping.Product;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<ProductModel, String> {
    Optional<List<ProductModel>> findByProductCodeContainingIgnoreCaseOrProductNameContainingIgnoreCase(String p_strSearchValue1, String p_strSearchValue2);
}
