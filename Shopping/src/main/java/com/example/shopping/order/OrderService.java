package com.example.shopping.order;

import javax.persistence.*;
import java.text.*;
import java.time.*;
import java.time.format.*;
import com.example.shopping.user.*;
import com.example.shopping.utility.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.context.annotation.*;
import org.springframework.stereotype.*;

@Service
@Scope("singleton")
public class OrderService {

	@Autowired
	private OrderRepository m_repoOrder;

	@Autowired
	private UserRepository m_repoUser;

	private static final String LOCK_ORDERNUMBER = "OrderNumberCount";
	private static final String LOCK_INVOICENUMBER = "InvoiceNumberCount";

	private static Long s_OrderNumberCount = null;
	private static String s_OrderNumberPrefix = null;

	private static Long s_InvoiceNumberCount = null;
	private static String s_InvoiceNumberPrefix = null;

	public static final String FORMAT_ORDERNUMBER_DOCCODE_ORDER = "DD";
	public static final String FORMAT_ORDERNUMBER_DOCCODE_INVOICE = "INV";
	public static final String FORMAT_ORDERNUMBER_YYMM = "yyMM";
	public static final DecimalFormat FORMAT_ORDERNUMBER_NUMBER = new DecimalFormat("0000");

	public static final DateTimeFormatter FORMATTER_YYMM = DateTimeFormatter.ofPattern(FORMAT_ORDERNUMBER_YYMM);

	public OrderService() {}

	public void setRepoMock(OrderRepository p_repoOrder, UserRepository p_repoUser) {
		m_repoOrder = p_repoOrder;
		m_repoUser = p_repoUser;
	}

	public void setOrderNumber(Long p_orderNumberCount, String p_strPrefix) {
		synchronized(LOCK_ORDERNUMBER) {
			s_OrderNumberCount = p_orderNumberCount;
			s_OrderNumberPrefix = p_strPrefix;
		}
	}

	public void setInvoiceNumber(Long p_orderNumberCount, String p_strPrefix) {
		synchronized(LOCK_INVOICENUMBER) {
			s_InvoiceNumberCount = p_orderNumberCount;
			s_InvoiceNumberPrefix = p_strPrefix;
		}
	}

	public OrderModel createOrder(RequestOrder p_order, String p_strUserId) {
		try {
			UserModel user = m_repoUser.getById(p_strUserId);
			OrderModel orderModel = p_order.toOrderModel(p_strUserId, user.getUserFullName());
			orderModel.setOrderNumber(getNextOrderNumber());
			orderModel.setTransactionDate(LocalDateTime.now());
			orderModel = m_repoOrder.save(orderModel);

			return orderModel;

		} catch(EntityNotFoundException exc) {
			throw new NotFoundException(p_strUserId);
		}
	}

	public OrderModel setOrderPaid(String p_orderId) {
		OrderModel orderModel = m_repoOrder.getById(p_orderId);
		orderModel.setStatus(OrderStatus.Paid);
		orderModel.setInvoiceNumber(getNextInvoiceNumber());
		orderModel = m_repoOrder.save(orderModel);

		return orderModel;
	}

	public String getNextOrderNumber() {
		return getNextOrderNumber(m_repoOrder);
	}

	public static String getNextOrderNumber(OrderRepository p_repoOrder) {
		synchronized(LOCK_ORDERNUMBER) {
			String strNextOrderNumber;
			String strYYMM = LocalDateTime.now().format(OrderService.FORMATTER_YYMM);

			if(s_OrderNumberCount == null) {
				s_OrderNumberPrefix = String.format("%s%s", FORMAT_ORDERNUMBER_DOCCODE_ORDER, strYYMM);
				s_OrderNumberCount = p_repoOrder.countByOrderNumberStartingWith(s_OrderNumberPrefix);

			} else {
				String strPrefix = String.format("%s%s", FORMAT_ORDERNUMBER_DOCCODE_ORDER, strYYMM);
				if(strPrefix.compareTo(s_OrderNumberPrefix) != 0) {
					s_OrderNumberPrefix = strPrefix;
					s_OrderNumberCount = p_repoOrder.countByOrderNumberStartingWith(s_OrderNumberPrefix);
				}
			}

			s_OrderNumberCount++;
			strNextOrderNumber = genDOCID(s_OrderNumberPrefix, s_OrderNumberCount);

			return strNextOrderNumber;
		}
	}

	public String getNextInvoiceNumber() {
		return getNextInvoiceNumber(m_repoOrder);
	}

	public static String getNextInvoiceNumber(OrderRepository p_repoOrder) {
		synchronized(LOCK_INVOICENUMBER) {
			String strNextInvoiceNumber;
			String strYYMM = LocalDateTime.now().format(OrderService.FORMATTER_YYMM);

			if(s_InvoiceNumberCount == null) {
				s_InvoiceNumberPrefix = String.format("%s%s", FORMAT_ORDERNUMBER_DOCCODE_INVOICE, strYYMM);
				s_InvoiceNumberCount = p_repoOrder.countByInvoiceNumberStartingWith(s_InvoiceNumberPrefix);

			} else {
				String strPrefix = String.format("%s%s", FORMAT_ORDERNUMBER_DOCCODE_INVOICE, strYYMM);
				if(strPrefix.compareTo(s_InvoiceNumberPrefix) != 0) {
					s_InvoiceNumberPrefix = strPrefix;
					s_InvoiceNumberCount = p_repoOrder.countByInvoiceNumberStartingWith(s_InvoiceNumberPrefix);
				}
			}

			s_InvoiceNumberCount++;
			strNextInvoiceNumber = genDOCID(s_InvoiceNumberPrefix, s_InvoiceNumberCount);

			return strNextInvoiceNumber;
		}
	}

	public static String genDOCID(String p_strPrefix, Long p_lngCount) {
		String strNumber = FORMAT_ORDERNUMBER_NUMBER.format(p_lngCount);
		String strNextOrderNumber = String.format("%s%s", p_strPrefix, strNumber);

		return strNextOrderNumber;
	}
}
