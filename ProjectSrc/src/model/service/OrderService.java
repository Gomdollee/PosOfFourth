package model.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import model.dao.OrderDAO;
import model.dao.OrderDAOImpl;
import model.dto.OrderLine;
import model.dto.Orders;

public interface OrderService {
	OrderDAO orderDao = new OrderDAOImpl();

	/**
	 * 주문하기
	 */
	public void orderInsert(Orders order) throws SQLException;

	/**
	 * 주문 내역보기
	 */
	public List<Orders> orderTotal(String cusId) throws SQLException;
}
