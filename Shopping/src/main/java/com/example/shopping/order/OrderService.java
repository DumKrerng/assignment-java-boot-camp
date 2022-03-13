package com.example.shopping.order;

import org.springframework.beans.factory.annotation.*;
import org.springframework.context.annotation.*;
import org.springframework.stereotype.*;

@Service
@Scope("singleton")
public class OrderService {

	@Autowired
	private OrderRepository m_repoOrder;

	public void setRepoMock(OrderRepository p_repoOrder) {
		m_repoOrder = p_repoOrder;
	}

	public OrderModel createOrder(OrderDetail p_order, String p_strUserId) {
		OrderModel orderModel = p_order.toOrderModel(p_strUserId);
		orderModel = m_repoOrder.save(orderModel);

		return orderModel;
	}
}
