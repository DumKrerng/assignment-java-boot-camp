package com.example.Shopping.Basket;

import java.util.*;
import org.springframework.data.jpa.repository.*;

public interface BasketRepository extends JpaRepository<BasketModel, String> {
	@EntityGraph(value = "Basket-With-BasketItem")
	Optional<BasketModel> findByBasketStatus(String p_strBasketStatus);
}
