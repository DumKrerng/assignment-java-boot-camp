package com.example.shopping.basket;

import java.util.*;
import org.springframework.data.jpa.repository.*;

public interface BasketRepository extends JpaRepository<BasketModel, String> {
	@EntityGraph(value = "Basket-With-BasketItem")
	Optional<BasketModel> findByBasketStatus(String p_strBasketStatus);
}
