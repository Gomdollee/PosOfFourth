package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;
import java.util.ArrayList;
import java.util.List;
import model.dto.Revenue;

import util.DbUtil;

public class RevenueDAOImpl implements RevenueDAO {

	private Properties proFile = DbUtil.getProFile();

	@Override
	public int selectTotalRevenue() throws SQLException {
		Connection con=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		String sql=proFile.getProperty("revenue.selectTotalRevenue");
		int result = 0;
		try {
			con = DbUtil.getConnection();
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			if(rs.next()) {

				result = rs.getInt(1);

			}

		}finally {
			DbUtil.close(con, ps, rs);
		}
		return result;
	}


	@Override
	public List<Revenue> selectDailyRevenue() throws SQLException {

		Connection con=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		String sql=proFile.getProperty("revenue.selectDailyRevenue");
		List<Revenue> list = new ArrayList<>();
		try {
			con = DbUtil.getConnection();
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()) {
				Revenue dto = new Revenue(rs.getString(1), rs.getInt(2));
				list.add(dto);
			}

		}finally {
			DbUtil.close(con, ps, rs);
		}
		return list;
	}

	@Override
	public List<Revenue> selectMonthlyRevenue() throws SQLException {

		Connection con=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		String sql=proFile.getProperty("revenue.selectMonthlyRevenue");
		List<Revenue> list = new ArrayList<>();
		try {
			con = DbUtil.getConnection();
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()) {
				Revenue dto = new Revenue(rs.getString(1), rs.getInt(2));
				list.add(dto);
			}

		}finally {
			DbUtil.close(con, ps, rs);
		}
		return list;
	}

	@Override
	public List<Revenue> selectYearlyReenue() throws SQLException {

		Connection con=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		String sql=proFile.getProperty("revenue.selectYearlyReenuee");
		List<Revenue> list = new ArrayList<>();
		try {
			con = DbUtil.getConnection();
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()) {
				Revenue dto = new Revenue(rs.getString(1), rs.getInt(2));
				list.add(dto);
			}

		}finally {
			DbUtil.close(con, ps, rs);
		}
		return list;
	}



}

 

