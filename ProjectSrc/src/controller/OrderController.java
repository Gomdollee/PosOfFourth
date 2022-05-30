package controller;

import java.sql.SQLException;
import java.util.List;

import model.dto.Orders;
import model.service.OrderService;
import model.service.OrderServiceImpl;
import view.EndView;
import view.FailView;

public class OrderController {
	
	private static OrderService orderService = new OrderServiceImpl();

	public static void orderInsert(Orders order) {
		try {
			orderService.orderInsert(order);
		} catch(SQLException e) {
			FailView.errorMessage(e.getMessage());
		}
	}
	
	public static void orderTotal(String cusId) {
		try {
			List<Orders> orders = orderService.orderTotal(cusId);
			for (Orders order : orders) {
				EndView.printOrderTotal(order);
			}
		} catch(SQLException e) {
			FailView.errorMessage(e.getMessage());
		}
	}
}
