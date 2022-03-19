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

	private static String USERID = "";

	private String getUserId() {
		if(USERID.length() <= 0) {
			Optional<UserModel> optUser = m_repoUser.findByUsernameIs("DumKrerng");
			USERID = optUser.get().getUserID();
		}

		return USERID;
	}

	@Test
	@DisplayName("ทดสอบการค้นด้วย UserId, Status = \"Paid\" แล้ว ต้องพบข้อมูล")
	void TestFindByUserIdAndStatus_01() {
		String strStatus = OrderStatus.Paid.name();
		Optional<List<OrderModel>> result = m_repoOrder.findByUserIdAndStatus(getUserId(), strStatus);
		List<OrderModel> lsOrders = result.get();

		assertTrue(result.isPresent());
		assertEquals(1, lsOrders.size());
	}

	@Test
	@DisplayName("ทดสอบการค้นด้วย UserId, Status = \"Created\" แล้ว ต้องพบไม่พบข้อมูล")
	void TestFindByUserIdAndStatus_02() {
		String strStatus = OrderStatus.Created.name();
		Optional<List<OrderModel>> result = m_repoOrder.findByUserIdAndStatus(getUserId(), strStatus);
		List<OrderModel> lsOrders = result.get();

		assertTrue(result.isPresent());
		assertEquals(0, lsOrders.size());
	}

	@Test
	@DisplayName("ทดสอบ CountByOrderNumberStartingWith Prefix = \"DD0001\" แล้ว ต้องได้ 1")
	void TestCountByOrderNumberStartingWith_01() {
		String prefix = "DD0001";
		Long result = m_repoOrder.countByOrderNumberStartingWith(prefix);

		assertEquals(1, result);
	}

	@Test
	@DisplayName("ทดสอบ CountByOrderNumberStartingWith Prefix = \"DD0101\" แล้ว ต้องได้ 0")
	void TestCountByOrderNumberStartingWith_02() {
		String prefix = "DD0101";
		Long result = m_repoOrder.countByOrderNumberStartingWith(prefix);

		assertEquals(0, result);
	}

	@Test
	@DisplayName("ทดสอบ CountByInvoiceNumberStartingWith Prefix = \"INV0001\" แล้ว ต้องได้ 1")
	void TestCountByInvoiceNumberStartingWith_01() {
		String prefix = "INV0001";
		Long result = m_repoOrder.countByInvoiceNumberStartingWith(prefix);

		assertEquals(1, result);
	}

	@Test
	@DisplayName("ทดสอบ CountByInvoiceNumberStartingWith Prefix = \"INV0101\" แล้ว ต้องได้ 0")
	void TestCountByInvoiceNumberStartingWith_02() {
		String prefix = "INV0101";
		Long result = m_repoOrder.countByInvoiceNumberStartingWith(prefix);

		assertEquals(0, result);
	}
}
