package com.example.shopping.order;

import com.example.shopping.*;
import com.example.shopping.payment.*;
import com.example.shopping.payment.paymentmethod.*;
import com.example.shopping.user.*;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.test.context.*;
import org.springframework.boot.test.web.client.*;
import org.springframework.http.*;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class OrderControllerTest {

	@Autowired
	private TestRestTemplate m_template;

	@Test
	@DisplayName("ทดสอบการสร้าง Order สำเร็จ")
	void TestCreateOrder_01() {
		HttpHeaders headers = new HttpHeaders();
		headers.set("data-userid", ShoppingApplication.UserId_ForTesting);

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

		RequestOrder orderMock = new RequestOrder();
		orderMock.setInvoiceDetail(addressInvoiceMock);
		orderMock.setShippingDetail(addressShippingMock);
		orderMock.setPaymentDetail(paymentMock);

		HttpEntity<RequestOrder> request = new HttpEntity<>(orderMock, headers);
		ResponseEntity<ResponseOrder> response = m_template.exchange(
			"/api/v1/order",
			HttpMethod.POST,
			request,
			ResponseOrder.class
		);

		HttpStatus httpstatus = response.getStatusCode();
		ResponseOrder body = response.getBody();
		OrderViewModel order = body.getData();

		assertEquals(HttpStatus.OK, httpstatus);
		assertEquals(order.getPayer(), ShoppingApplication.UserFullName_ForTesting);
		assertTrue(order.getOrderNumber().startsWith("DD"));
		assertNull(order.getInvoiceNumber());
	}

	@Test
	@DisplayName("ทดสอบการสร้าง Order ไม่สำเร็จ")
	void TestCreateOrder_02() {
		HttpHeaders headers = new HttpHeaders();
		headers.set("data-userid", "XXX");

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

		RequestOrder orderMock = new RequestOrder();
		orderMock.setInvoiceDetail(addressInvoiceMock);
		orderMock.setShippingDetail(addressShippingMock);
		orderMock.setPaymentDetail(paymentMock);

		HttpEntity<RequestOrder> request = new HttpEntity<>(orderMock, headers);
		ResponseEntity<ResponseOrder> response = m_template.exchange(
			"/api/v1/order",
			HttpMethod.POST,
			request,
			ResponseOrder.class
		);

		HttpStatus httpstatus = response.getStatusCode();
		ResponseOrder body = response.getBody();
		OrderViewModel order = body.getData();

		assertEquals(HttpStatus.NOT_FOUND, httpstatus);
		assertEquals(body.getMessage(), "XXX is not found!");
		assertNull(order);
	}
}
