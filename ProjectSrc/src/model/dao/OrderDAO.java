package model.dao;

import java.sql.Connection;

import java.sql.SQLException;
import java.util.List;

import model.dto.OrderLine;
import model.dto.Orders;

public interface OrderDAO {
	/**
	 * 주문하기
	 * */
	int orderInsert(Orders order) throws SQLException;

	/**
	 * 주문상세 등록하기
	 * */
	int[] orderLineInsert(Connection con, Orders order) throws SQLException;

	/**
	 * 재고량 감소시키기
	 * */
	int[] decrementStock(Connection con, List<OrderLine> orderLineList) throws SQLException;

	/**
	 * 결제하기(주문내역보기)
	 * */
	List<Orders> orderTotal(String cusId) throws SQLException;

	/**
	 * 주문 상세 조회하기
	 */
	List<OrderLine> selectOrderLine(String orderId) throws SQLException;
}