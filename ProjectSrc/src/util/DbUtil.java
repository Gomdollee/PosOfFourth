package util;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class DbUtil {
	private static Properties proFile = new Properties();
	/**
	 * 로드
	 * */

	static {
		try {
			//외부 properties파일 로딩하기

			proFile.load(new FileInputStream("resources/dbInfo.properties"));
			proFile.load(new FileInputStream("resources/fourth.properties"));

			Class.forName(proFile.getProperty("driverName"));

		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * 확인
	 * */
	public static void main(String[] args) {
		try {
			System.out.println("connect");
			Connection con =DbUtil.getConnection();
			System.out.println("con = " + con);
		}catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("OK");
	}

	public static Properties getProFile() {
		return proFile;
	}

	/**
	 * 연결
	 * */
	public static Connection getConnection() throws SQLException{
		Connection con = DriverManager.getConnection(
			proFile.getProperty("url"),
			proFile.getProperty("userName"),
			proFile.getProperty("userPass"));

		return con;
	}

	/**
	 * 닫기
	 * */
	public static void close(Connection con , Statement st, ResultSet rs) {
		try {
			if(rs != null) rs.close();
			if(st != null) st.close();
			if(con != null) con.close();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
}
