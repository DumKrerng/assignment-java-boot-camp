package com.example.Shopping.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class ProductController {

    @Autowired
    private ProductRepository m_repository;

    @GetMapping("/api/v1/search/product/{p_strSearchValue}")
    public ResponseProductSearch search(@PathVariable String p_strSearchValue) {
        Optional<List<ProductModel>> result = m_repository.findByProductCodeContainingIgnoreCaseOrProductNameContainingIgnoreCase(p_strSearchValue, p_strSearchValue);
        ResponseProductSearch response = new ResponseProductSearch();
        response.setData(result.get());

        return response;
    }
}
