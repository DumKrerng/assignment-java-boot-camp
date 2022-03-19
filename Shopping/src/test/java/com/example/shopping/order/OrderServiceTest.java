package com.example.shopping.order;

import java.time.*;

import static org.junit.jupiter.api.Assertions.*;

import com.example.shopping.*;
import com.example.shopping.payment.*;
import com.example.shopping.payment.paymentmethod.*;
import com.example.shopping.user.*;
import com.example.shopping.utility.*;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.test.autoconfigure.orm.jpa.*;

@DataJpaTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class OrderServiceTest {

	@Autowired
	private OrderRepository m_repoOrder;

	@Autowired
	private UserRepository m_repoUser;

	@Test
	@DisplayName("ทดสอบการสร้าง Order แส้วสำเร็จ")
	public void testCreateOrder_01() {
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

		OrderService service = new OrderService();
		service.setRepoMock(m_repoOrder, m_repoUser);
		service.setOrderNumber(null, null);
		OrderModel result = service.createOrder(orderMock, ShoppingApplication.UserId_ForTesting);

		LocalDateTime timenow = LocalDateTime.now();
		String strYYMM = timenow.format(OrderService.FORMATTER_YYMM);
		String strPrefix = String.format("%s%s", OrderService.FORMAT_ORDERNUMBER_DOCCODE_ORDER, strYYMM);
		String strExpectedOrderNumber = OrderService.genDOCID(strPrefix, 1L);

		assertEquals(strExpectedOrderNumber, result.getOrderNumber());
		assertEquals("10800", result.getAddressShipping().getPostCode());
		assertEquals("087792XXXX", result.getAddressInvoice().getPhoneNumber());
		assertEquals(PaymentMethod.CreditCard.getCode(), result.getPayment().getPaymentMethod());
		assertEquals("1234567890123456", result.getPayment().getCreditCardNumber());
		assertEquals(ShoppingApplication.UserFullName_ForTesting, result.getPayer());

		assertNull(result.getPayment().getBankModel());

		m_repoOrder.deleteById(result.getId());
	}

	@Test
	@DisplayName("ทดสอบการสร้าง Order แล้วมี Exception: NotFoundException")
	public void testCreateOrder_02() {
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

		OrderService service = new OrderService();
		service.setRepoMock(m_repoOrder, m_repoUser);
		service.setOrderNumber(null, null);

		NotFoundException error = assertThrows(NotFoundException.class, () -> {
			service.createOrder(orderMock, "XXX");
		});

		assertEquals(error.getMessage(), "XXX is not found!");
	}

	@Test
	@DisplayName("ทดสอบการ SetOrderPaid")
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

		RequestOrder orderMock = new RequestOrder();
		orderMock.setInvoiceDetail(addressInvoiceMock);
		orderMock.setShippingDetail(addressShippingMock);
		orderMock.setPaymentDetail(paymentMock);

		OrderService service = new OrderService();
		service.setRepoMock(m_repoOrder, m_repoUser);
		OrderModel order = service.createOrder(orderMock, ShoppingApplication.UserId_ForTesting);
		order = service.setOrderPaid(order.getId());

		LocalDateTime timenow = LocalDateTime.now();
		String strYYMM = timenow.format(OrderService.FORMATTER_YYMM);
		String strPrefix = String.format("%s%s", OrderService.FORMAT_ORDERNUMBER_DOCCODE_INVOICE, strYYMM);
		String strExpectedInvoiceNumber = OrderService.genDOCID(strPrefix, 1L);

		assertEquals(OrderStatus.Paid, order.getOrderStatus());
		assertEquals(strExpectedInvoiceNumber, order.getInvoiceNumber());
		assertEquals("087792XXXX", order.getAddressInvoice().getPhoneNumber());
		assertEquals("1234567890123456", order.getPayment().getCreditCardNumber());
		assertEquals(ShoppingApplication.UserFullName_ForTesting, order.getPayer());

		assertNull(order.getPayment().getBankModel());
	}

	@Test
	@DisplayName("ทดสอบ getNextOrderNumber ที่ OrderNumberCount = null")
	public void testGetNextOrderNumber_01() {
		OrderService service = new OrderService();
		service.setRepoMock(m_repoOrder, m_repoUser);
		service.setOrderNumber(null, null);
		String result = service.getNextOrderNumber();

		LocalDateTime timenow = LocalDateTime.now();
		String strYYMM = timenow.format(OrderService.FORMATTER_YYMM);

		String strPrefix = String.format("%s%s", OrderService.FORMAT_ORDERNUMBER_DOCCODE_ORDER, strYYMM);
		String strExpected = OrderService.genDOCID(strPrefix, 1L);
		assertEquals(strExpected, result);
	}

	@Test
	@DisplayName("ทดสอบ getNextOrderNumber ที่ OrderNumberCount = 1")
	public void testGetNextOrderNumber_02() {
		LocalDateTime timenow = LocalDateTime.now();
		String strYYMM = timenow.format(OrderService.FORMATTER_YYMM);
		String strPrefix = String.format("%s%s", OrderService.FORMAT_ORDERNUMBER_DOCCODE_ORDER, strYYMM);

		OrderService service = new OrderService();
		service.setRepoMock(m_repoOrder, m_repoUser);
		service.setOrderNumber(1L, strPrefix);
		String result = service.getNextOrderNumber();

		String strExpected = OrderService.genDOCID(strPrefix, 2L);
		assertEquals(strExpected, result);
	}

	@Test
	@DisplayName("ทดสอบ getNextOrderNumber ที่ OrderNumberCount = 2, OrderNumberPrefix = \"DD0001\"")
	public void testGetNextOrderNumber_03() {
		LocalDateTime timenow = LocalDateTime.now();
		String strYYMM = timenow.format(OrderService.FORMATTER_YYMM);
		String strPrefix = String.format("%s%s", OrderService.FORMAT_ORDERNUMBER_DOCCODE_ORDER, strYYMM);

		OrderService service = new OrderService();
		service.setRepoMock(m_repoOrder, m_repoUser);
		service.setOrderNumber(2L, "DD0001");

		String result = service.getNextOrderNumber();
		String strExpected = OrderService.genDOCID(strPrefix, 1L);
		assertEquals(strExpected, result);

		result = service.getNextOrderNumber();
		strExpected = OrderService.genDOCID(strPrefix, 2L);
		assertEquals(strExpected, result);
	}

	@Test
	@DisplayName("ทดสอบ getNextInvoiceNumber ที่ InvoiceNumberCount = 1")
	public void testGetNextInvoiceNumber_02() {
		LocalDateTime timenow = LocalDateTime.now();
		String strYYMM = timenow.format(OrderService.FORMATTER_YYMM);
		String strPrefix = String.format("%s%s", OrderService.FORMAT_ORDERNUMBER_DOCCODE_INVOICE, strYYMM);

		OrderService service = new OrderService();
		service.setRepoMock(m_repoOrder, m_repoUser);
		service.setInvoiceNumber(1L, strPrefix);
		String result = service.getNextInvoiceNumber();

		String strExpected = OrderService.genDOCID(strPrefix, 2L);
		assertEquals(strExpected, result);
	}

	@Test
	@DisplayName("ทดสอบ getNextInvoiceNumber ที่ InvoiceNumberCount = 2, InvoiceNumberPrefix = \"INV0001\"")
	public void testGetNextInvoiceNumber_03() {
		LocalDateTime timenow = LocalDateTime.now();
		String strYYMM = timenow.format(OrderService.FORMATTER_YYMM);
		String strPrefix = String.format("%s%s", OrderService.FORMAT_ORDERNUMBER_DOCCODE_INVOICE, strYYMM);

		OrderService service = new OrderService();
		service.setRepoMock(m_repoOrder, m_repoUser);
		service.setInvoiceNumber(2L, "INV0001");

		String result = service.getNextInvoiceNumber();
		String strExpected = OrderService.genDOCID(strPrefix, 1L);
		assertEquals(strExpected, result);

		result = service.getNextInvoiceNumber();
		strExpected = OrderService.genDOCID(strPrefix, 2L);
		assertEquals(strExpected, result);
	}
}
