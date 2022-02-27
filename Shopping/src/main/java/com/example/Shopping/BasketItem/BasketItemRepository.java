package com.example.Shopping.BasketItem;

import java.util.*;
import org.springframework.data.jpa.repository.*;

public interface BasketItemRepository extends JpaRepository<BasketItemModel, Integer> {
	Optional<List<BasketItemModel>> findByBasketId(String p_strBasketId);
}
