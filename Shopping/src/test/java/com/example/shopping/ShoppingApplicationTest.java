package com.example.shopping;

import com.example.shopping.basket.*;
import com.example.shopping.order.*;
import com.example.shopping.payment.*;
import com.example.shopping.payment.paymentmethod.*;
import com.example.shopping.product.*;
import com.example.shopping.user.*;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.test.context.*;
import org.springframework.boot.test.web.client.*;
import org.springframework.http.*;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class ShoppingApplicationTest {

	@Autowired
	private TestRestTemplate m_template;

	@Test
	@DisplayName("ทดสอบ Success flow")
	void testSuccessFlow() {
		RequestLogin request = new RequestLogin();
		request.setUsername("DumKrerng");
		request.setPassword("123456");

		ResponseLogin resUser = m_template.postForObject("/api/v1/login", request, ResponseLogin.class);
		UserViewModel user = resUser.getData();

		assertEquals(request.getUsername(), user.getUsername());

		ResponseProductSearch resProduct = m_template.getForObject("/api/v1/search/product/Code", ResponseProductSearch.class);
		List<ProductModel> lsProducts = resProduct.getData();

		assertEquals(2, lsProducts.size());

		String strProductCode = "ProductX";
		ResponseBasket resBasket = m_template.postForObject(
			String.format("/api/v1/basket/product/%s", strProductCode),
			"{}",
			ResponseBasket.class);
		BasketViewModel basket = resBasket.getData();
		List<BasketItemViewModel> Itemmodels = basket.getBasketItems();

		assertEquals(1, Itemmodels.size());

		BasketItemViewModel modelBasketItem = Itemmodels.get(0);

		assertEquals(BasketStatus.OPEN.name(), basket.getBasketStatus());
		assertEquals(10, modelBasketItem.getUnitPrice());
		assertEquals(1, modelBasketItem.getQuantity());

		HttpHeaders headers = new HttpHeaders();
		headers.set("data-userid", user.getUserID());

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

		HttpEntity<RequestOrder> reqOrder = new HttpEntity<>(orderMock, headers);
		ResponseEntity<ResponseOrder> resOrder = m_template.exchange(
			"/api/v1/order",
			HttpMethod.POST,
			reqOrder,
			ResponseOrder.class
		);

		HttpStatus httpstatus = resOrder.getStatusCode();
		ResponseOrder temp = resOrder.getBody();
		OrderViewModel order = temp.getData();

		assertEquals(HttpStatus.OK, httpstatus);
		assertTrue(order.getOrderNumber().startsWith("DD"));
		assertNull(order.getInvoiceNumber());

		HttpEntity reqSetOrderPaid = new HttpEntity(headers);
		resOrder = m_template.exchange(
			String.format("/api/v1/order/paid/%s", order.getOrderId()),
			HttpMethod.POST,
			reqSetOrderPaid,
			ResponseOrder.class
		);

		httpstatus = resOrder.getStatusCode();
		temp = resOrder.getBody();
		order = temp.getData();

		assertEquals(HttpStatus.OK, httpstatus);
		assertTrue(order.getInvoiceNumber().startsWith("INV"));
	}
}
