package com.example.shopping.order;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.example.shopping.user.*;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.test.autoconfigure.orm.jpa.*;

@DataJpaTest
public class OrderRepositoryTest {

	@Autowired
	private OrderRepository m_repoOrder;

	@Autowired
	private UserRepository m_repoUser;

	@Test
	@DisplayName("ทดสอบการค้นด้วย UserId = \"TestID\", Status = \"Paid\" แล้ว ต้องพบข้อมูล")
	void TestFindByUserIdAndStatus_01() {
		String userId = "TestID";

		String strStatus = OrderStatus.Paid.name();
		Optional<List<OrderModel>> result = m_repoOrder.findByUserIdAndStatus(userId, strStatus);
		List<OrderModel> lsOrders = result.get();

		assertTrue(result.isPresent());
		assertEquals(1, lsOrders.size());
	}
	@Test
	@DisplayName("ทดสอบการค้นด้วย UserName = \"TestID\", Status = \"Created\" แล้ว ต้องพบไม่พบข้อมูล")
	void TestFindByUserIdAndStatus_02() {
		String userId = "TestID";

		String strStatus = OrderStatus.Created.name();
		Optional<List<OrderModel>> result = m_repoOrder.findByUserIdAndStatus(userId, strStatus);
		List<OrderModel> lsOrders = result.get();

		assertTrue(result.isPresent());
		assertEquals(0, lsOrders.size());
	}
}
