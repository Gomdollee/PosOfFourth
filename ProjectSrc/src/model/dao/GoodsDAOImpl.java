package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import model.dto.Goods;
import model.dto.GoodsTop;
import util.DbUtil;

public class GoodsDAOImpl implements GoodsDAO {

	private Properties proFile = DbUtil.getProFile();

	// select * from goods order by goods_price
	public List<Goods> goodsSelectAll() throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<Goods> list = new ArrayList<Goods>();
		String sql = proFile.getProperty("goods.SelectAll");

		try {

			con = DbUtil.getConnection();
			ps = con.prepareStatement(sql);

			rs = ps.executeQuery();

			while (rs.next()) {
				Goods dto = new Goods(rs.getString(1), rs.getString(2), rs.getInt(3), rs.getInt(4));

				list.add(dto);

			}

		} finally {
			DbUtil.close(con, ps, rs);
		}

		return list;
	}

	//	SELECT * FROM (SELECT GOODS.GOODS_ID,GOODS.GOODS_NAME,SUM(ORDER_LINE.ORDER_QTY)FROM ORDER_LINE
	//	JOIN GOODS ON(ORDER_LINE.GOODS_ID = GOODS.GOODS_ID) GROUP BY ORDER_LINE.GOODS_ID, GOODS.GOODS_ID, GOODS.GOODS_NAME
	//	ORDER BY SUM(ORDER_LINE.ORDER_QTY) DESC) WHERE ROWNUM <= 5
	@Override
	public List<GoodsTop> goodsSelectByTop() throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<GoodsTop> list = new ArrayList<GoodsTop>();
		String sql = proFile.getProperty("goods.SelectByTop");
		try {
			con = DbUtil.getConnection();
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {
				GoodsTop goods = new GoodsTop(rs.getString(1), rs.getString(2), rs.getInt(3));
				list.add(goods);
			}
		} finally {
			DbUtil.close(con, ps, rs);
		}
		return list;
	}

	//SELECT * FROM GOODS where goods_id like ?
	@Override
	public List<Goods> goodsSelectByType(String type) throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<Goods> list = new ArrayList<Goods>();
		String sql = proFile.getProperty("goods.SelectByType");

		try {
			con = DbUtil.getConnection();
			ps = con.prepareStatement(sql);
			ps.setString(1, "GC_" + type + "%");
			rs = ps.executeQuery();
			while (rs.next()) {
				Goods goods = new Goods(rs.getString(1), rs.getString(2), rs.getInt(3), rs.getInt(4));
				list.add(goods);
			}
		} finally {
			DbUtil.close(con, ps, rs);
		}
		return list;
	}

	/**
	 GoodsID를 이용해 검색
	 select goods_id from goods??
	 * */
	@Override
	public Goods goodsSelectById(String goodsId) throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Goods goods = null;

		String sql = proFile.getProperty("goods.SelectById");
		try {
			con = DbUtil.getConnection();
			ps = con.prepareStatement(sql);
			ps.setString(1, goodsId);
			rs = ps.executeQuery();
			if (rs.next()) {
				goods = new Goods(rs.getString(1), rs.getString(2), rs.getInt(3), rs.getInt(4));
			}
		} finally {
			DbUtil.close(con, ps, rs);
		}

		return goods;
	}

	//insert into goods (goods_id , goods_name, stock_qty, goods_price) values (board_seq.nextval,?,?,?,?)
	public int goodsInsert(Goods goods) throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		int result = 0;
		String sql = proFile.getProperty("goods.insert");

		try {
			con = DbUtil.getConnection();
			ps = con.prepareStatement(sql);

			ps.setString(1, goods.getGoodsId());
			ps.setString(2, goods.getGoodsName());
			ps.setInt(3, goods.getStockQty());
			ps.setInt(4, goods.getGoodsPrice());

			result = ps.executeUpdate();

		} finally {
			DbUtil.close(con, ps, null);
		}
		return result;
	}

	//update goods set stock_qty = stock_qty + ? where goods_id = ?
	public int goodsUpdate(String goodsId, int stockQty) throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		Goods goods = null;
		int result = 0;
		String sql = proFile.getProperty("goods.updateByGoods");
		try {
			con = DbUtil.getConnection();
			ps = con.prepareStatement(sql);
			ps.setInt(1, stockQty);
			ps.setString(2, goodsId);
			result = ps.executeUpdate();
		} finally {
			DbUtil.close(con, ps, null);
		}

		return result;

	}

	//delete from goods where goods_id = ?
	public int goodsDelete(String goodsId) throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		int result = 0;
		String sql = proFile.getProperty("goods.DeleteById");

		try {
			con = DbUtil.getConnection();
			ps = con.prepareStatement(sql);
			ps.setString(1, goodsId);
			result = ps.executeUpdate();

		} finally {
			DbUtil.close(con, ps, null);
		}
		return result;
	}
}

