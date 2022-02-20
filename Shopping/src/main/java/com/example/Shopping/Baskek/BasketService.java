package com.example.Shopping.Baskek;

import com.example.Shopping.BasketItem.BasketItemModel;
import com.example.Shopping.BasketItem.BasketItemRepository;
import com.example.Shopping.Product.ProductModel;
import com.example.Shopping.Product.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Scope("singleton")
public class BasketService {

    @Autowired
    private BasketRepository m_repoBasket;

    @Autowired
    private BasketItemRepository m_repoBasketItem;

    @Autowired
    private ProductRepository m_repoProduct;

    public void setMockRepository(ProductRepository p_repProduct, BasketRepository p_repBasket, BasketItemRepository p_repBasketItem) {
        m_repoProduct = p_repProduct;
        m_repoBasket = p_repBasket;
        m_repoBasketItem = p_repBasketItem;
    }

    public BasketViewModel addProduct(String p_strProductCode) {
        Optional<ProductModel> optProduct = m_repoProduct.findByProductCode(p_strProductCode);

        if(!optProduct.isPresent()) {
            return null;
        }

        ProductModel modelProduct = optProduct.get();

        BasketModel modelBasket = new BasketModel();
        modelBasket.setBasketStatus(BasketStatus.OPEN.name());
        modelBasket = m_repoBasket.save(modelBasket);

        BasketItemModel modelBasketItem = new BasketItemModel();
        modelBasketItem.setBasketID(modelBasket.getBasketID());
        modelBasketItem.setProductID(modelProduct.getProductID());
        modelBasketItem.setUnitPrice(modelProduct.getUnitPrice());
        modelBasketItem.setQuantity(1);
        modelBasketItem = m_repoBasketItem.save(modelBasketItem);

        return new BasketViewModel(modelBasket, modelBasketItem);
    }
}
