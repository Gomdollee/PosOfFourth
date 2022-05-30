package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.dto.Goods;
import model.dto.OrderLine;
import model.dto.Orders;
import util.DbUtil;

public class OrderDAOImpl implements OrderDAO {

	GoodsDAO goodsDao = new GoodsDAOImpl();

	/**
	 * 주문하기
	 * */
	@Override
	public int orderInsert(Orders order) throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		String sql = "INSERT INTO ORDERS(ORDER_ID, CUS_ID, ORDER_DATE)" +
			"VALUES('JB ' || ORDERS_SEQ.NEXTVAL, ?, sysdate)";
		int result = 0;
		try {
			con = DbUtil.getConnection();
			con.setAutoCommit(false);
			ps = con.prepareStatement(sql);
			ps.setString(1, order.getCusId());
			result = ps.executeUpdate();
			if (result == 0) {
				con.rollback();
				throw new SQLException("주문 실패...");
			} else {
				int re[] = orderLineInsert(con, order);
				for (int i : re) {
					if (i != 1) {
						con.rollback();
						throw new SQLException("주문 할 수 없습니다...");
					}
				}
				decrementStock(con, order.getOrderLineList());
				con.commit();
			}
		} finally {
			con.commit();
			DbUtil.close(con, ps, null);
		}
		return result;
	}

	/**
	 * 주문상세 등록하기
	 * */
	@Override
	public int[] orderLineInsert(Connection con, Orders order) throws SQLException {
		PreparedStatement ps = null;
		String sql = "insert into order_line values ('JS ' || ORDER_LINE_SEQ.nextval, 'JB ' || ORDERS_SEQ.currval, ?, ?, ?)";
		int result[] = null;
		try {
			ps = con.prepareStatement(sql);
			for (OrderLine orderLine : order.getOrderLineList()) {
				Goods goods = goodsDao.goodsSelectById(orderLine.getGoodsId());
				ps.setString(1, goods.getGoodsId());
				ps.setInt(2, goods.getGoodsPrice());
				ps.setInt(3, orderLine.getOrderQty());
				ps.addBatch();
				ps.clearParameters();
			}
			result = ps.executeBatch();
		} finally {
			DbUtil.close(null, ps, null);
		}
		return result;
	}

	/**
	 * 재고량 감소시키기
	 * */
	public int[] decrementStock(Connection con, List<OrderLine> orderLineList) throws SQLException {
		PreparedStatement ps = null;
		String sql = "update goods set stock_qty = stock_qty-? where goods_id=?";
		int result[] = null;
		try {
			ps = con.prepareStatement(sql);
			for (OrderLine orderLine : orderLineList) {
				ps.setInt(1, orderLine.getOrderQty());
				ps.setString(2, orderLine.getGoodsId());
				ps.addBatch();
				ps.clearParameters();
			}
			result = ps.executeBatch();
		} finally {
			DbUtil.close(con, ps, null);
		}
		return result;
	}

	/**
	 * 결제하기(주문내역보기)
	 * */
	public List<Orders> orderTotal(String cusId) throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "select * from orders where cus_id=?";
		List<Orders> orders = new ArrayList<>();
		try {
			con = DbUtil.getConnection();
			ps = con.prepareStatement(sql);
			ps.setString(1, cusId);
			rs = ps.executeQuery();
			while (rs.next()) {
				Orders order = new Orders(rs.getString(1), rs.getString(2), rs.getString(3));
				List<OrderLine> orderLines = selectOrderLine(order.getOrderId());
				order.setOrderLineList(orderLines);
				orders.add(order);
			}
		} finally {
			DbUtil.close(con, ps, rs);
		}
		return orders;
	}

	/**
	 * 주문 번호에 해당하는 주문 상세 가져오기
	 */
	public List<OrderLine> selectOrderLine(String orderId) throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "select * from order_line where order_id=?";
		List<OrderLine> orderLines = new ArrayList<>();
		try {
			con = DbUtil.getConnection();
			ps = con.prepareStatement(sql);
			ps.setString(1, orderId);
			rs = ps.executeQuery();
			while (rs.next()) {
				OrderLine orderLine = new OrderLine(rs.getString(1), rs.getString(2), rs.getString(3), rs.getInt(4),
					rs.getInt(5));
				orderLines.add(orderLine);
			}
		} finally {
			DbUtil.close(con, ps, rs);
		}
		return orderLines;
	}
}