package model.dao;

import java.sql.SQLException;


import java.util.List;
import model.dto.Revenue;

public interface RevenueDAO {


	int selectTotalRevenue() throws SQLException;

	List<Revenue> selectDailyRevenue() throws SQLException;

	List<Revenue> selectMonthlyRevenue() throws SQLException;

	List<Revenue> selectYearlyReenue() throws SQLException;


}