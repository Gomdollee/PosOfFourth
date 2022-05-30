package model.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import model.dao.OrderDAO;
import model.dao.OrderDAOImpl;
import model.dto.OrderLine;
import model.dto.Orders;

public class OrderServiceImpl implements OrderService {
	OrderDAO orderDao = new OrderDAOImpl();

	public void orderInsert(Orders order) throws SQLException {
		orderDao.orderInsert(order);
	}

	public List<Orders> orderTotal(String cusId) throws SQLException {
		List<Orders> orders = orderDao.orderTotal(cusId);
		if (orders.isEmpty()) {
			throw new SQLException(cusId + "의 주문내역이 없습니다");
		}
		return orders;
	}
}
