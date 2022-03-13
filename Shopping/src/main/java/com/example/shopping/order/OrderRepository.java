package com.example.shopping.order;

import java.util.*;
import org.springframework.data.jpa.repository.*;

public interface OrderRepository extends JpaRepository<OrderModel, String> {
	Optional<List<OrderModel>> findByUserIdAndStatus(String p_strUserId, String p_strStatus);
}
