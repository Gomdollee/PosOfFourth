package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.dto.Goods;
import model.dto.Sku;
import util.DbUtil;

public class SkuDAOImpl implements SkuDAO {

	/**
	 * Id로 조회
	 */
	public Sku selectSkuById(String skuId) throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Sku sku= null;

		String sql = "select * from sku where sku_id = ?";
		try {
			con = DbUtil.getConnection();
			ps = con.prepareStatement(sql);
			ps.setString(1, skuId);
			rs = ps.executeQuery();
			if (rs.next()) {
				sku = new Sku(rs.getString(1), rs.getString(2), rs.getString(3), rs.getInt(4));
			}
		} finally {
			DbUtil.close(con, ps, rs);
		}

		return sku;
	}
}
