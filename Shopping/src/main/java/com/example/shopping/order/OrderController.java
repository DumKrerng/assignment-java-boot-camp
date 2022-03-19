package com.example.shopping.order;

import org.springframework.beans.factory.annotation.*;
import org.springframework.web.bind.annotation.*;

@RestController
public class OrderController {

	@Autowired
	private OrderService m_service;

	@PostMapping("/api/v1/order")
	public ResponseOrder createOrder(@RequestBody RequestOrder p_body, @RequestHeader("data-userid") String p_strUserID) {
		OrderModel orderModel = m_service.createOrder(p_body, p_strUserID);
		OrderViewModel view = new OrderViewModel(orderModel);
		ResponseOrder response = new ResponseOrder();
		response.setData(view);

		return response;
	}

	@PostMapping("/api/v1/order/paid/{p_orderId}")
	public ResponseOrder setOrderPaid(@PathVariable String p_orderId, @RequestHeader("data-userid") String p_strUserId) {
		OrderModel orderModel = m_service.setOrderPaid(p_orderId, p_strUserId);
		OrderViewModel view = new OrderViewModel(orderModel);
		ResponseOrder response = new ResponseOrder();
		response.setData(view);

		return response;
	}
}
