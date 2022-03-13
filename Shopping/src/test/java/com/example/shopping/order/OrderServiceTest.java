package com.example.shopping.order;

import com.example.shopping.payment.*;
import com.example.shopping.payment.paymentmethod.*;
import com.example.shopping.user.*;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.test.autoconfigure.orm.jpa.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@DataJpaTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class OrderServiceTest {

	@Autowired
	private OrderRepository m_repoOrder;

	@Test
	@DisplayName("ทดสอบการสร้าง Order")
	public void testCreateOrder() {
		AddressModel addressShippingMock = new AddressModel();
		addressShippingMock.setPostCode("10800");
		addressShippingMock.setPhoneNumber("087792XXXX");

		AddressModel addressInvoiceMock = new AddressModel();
		addressInvoiceMock.setPostCode("10800");
		addressInvoiceMock.setPhoneNumber("087792XXXX");

		CreditCardModel creditCardMock = new CreditCardModel();
		creditCardMock.setCreditCardNumber("1234567890123456");
		creditCardMock.setCreditCardName("CreditCardName");
		creditCardMock.setCreditCardExpire("12/25");

		PaymentDetail paymentMock = new PaymentDetail();
		paymentMock.setMethod(PaymentMethod.CreditCard);
		paymentMock.setCreditCard(creditCardMock);

		OrderDetail orderMock = new OrderDetail();
		orderMock.setAddressInvoice(addressInvoiceMock);
		orderMock.setAddressShipping(addressShippingMock);
		orderMock.setPayment(paymentMock);

		String userId = "TestID";

		OrderService service = new OrderService();
		service.setRepoMock(m_repoOrder);
		OrderModel result = service.createOrder(orderMock, userId);

		assertEquals("10800", result.getAddressShipping().getPostCode());
		assertEquals("087792XXXX", result.getAddressInvoice().getPhoneNumber());
		assertEquals(PaymentMethod.CreditCard.getCode(), result.getPayment().getPaymentMethod());
		assertEquals("1234567890123456", result.getPayment().getCreditCardNumber());
		assertEquals("TestID", result.getUserId());

		assertNull(result.getPayment().getBankModel());

		m_repoOrder.deleteById(result.getId());
	}

	@Test
	@DisplayName("ทดสอบการสร้าง SetOrderPaid")
	public void testSetOrderPaid() {
		AddressModel addressShippingMock = new AddressModel();
		addressShippingMock.setPostCode("10800");
		addressShippingMock.setPhoneNumber("087792XXXX");

		AddressModel addressInvoiceMock = new AddressModel();
		addressInvoiceMock.setPostCode("10800");
		addressInvoiceMock.setPhoneNumber("087792XXXX");

		CreditCardModel creditCardMock = new CreditCardModel();
		creditCardMock.setCreditCardNumber("1234567890123456");
		creditCardMock.setCreditCardName("CreditCardName");
		creditCardMock.setCreditCardExpire("12/25");

		PaymentDetail paymentMock = new PaymentDetail();
		paymentMock.setMethod(PaymentMethod.CreditCard);
		paymentMock.setCreditCard(creditCardMock);

		OrderDetail orderMock = new OrderDetail();
		orderMock.setAddressInvoice(addressInvoiceMock);
		orderMock.setAddressShipping(addressShippingMock);
		orderMock.setPayment(paymentMock);

		String userId = "TestID";

		OrderService service = new OrderService();
		service.setRepoMock(m_repoOrder);
		OrderModel order = service.createOrder(orderMock, userId);
		order = service.setOrderPaid(order.getId());

		assertEquals(OrderStatus.Paid, order.getOrderStatus());
		assertEquals("087792XXXX", order.getAddressInvoice().getPhoneNumber());
		assertEquals("1234567890123456", order.getPayment().getCreditCardNumber());
		assertEquals("TestID", order.getUserId());

		assertNull(order.getPayment().getBankModel());
	}
}
