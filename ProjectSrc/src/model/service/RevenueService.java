package model.service;

import java.sql.SQLException;
import java.util.List;

import model.dao.RevenueDAO;
import model.dao.RevenueDAOImpl;
import model.dto.Revenue;

public interface RevenueService {
	RevenueDAO revenueDao = new RevenueDAOImpl();
	
	
	int selectTotalRevenue() throws SQLException; 
	
	List<Revenue> selectDailyRevenue() throws SQLException;
	
	List<Revenue> selectMonthlyRevenue() throws SQLException; 
	
	List<Revenue> selectYearlyReenue() throws SQLException; 
}
