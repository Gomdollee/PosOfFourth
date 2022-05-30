package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.dto.Customer;
import util.DbUtil;

public class CustomerDAOImpl implements CustomerDAO {

	/**
	 * 로그인 하기
	 * */
	@Override
	public Customer login(String cusId, String cusPwd) throws SQLException {
		Connection con=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		Customer customer = null; //리턴타입 customer

		try {
			con= DbUtil.getConnection();
			ps = con.prepareStatement("select * from Customer where CUS_ID=? and CUS_PWD=?");
			ps.setString(1, cusId);
			ps.setString(2, cusPwd);

			rs = ps.executeQuery();


			if(rs.next()) {
				customer = new Customer(rs.getString(1), rs.getString(2));
				//System.out.println(customer); 출력
			}

		}finally {
			DbUtil.close(con, ps, rs);
		}

		return customer;
	}


}

