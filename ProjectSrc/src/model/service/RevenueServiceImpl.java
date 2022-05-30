package model.service;

import java.sql.SQLException;
import java.util.List;

import model.dao.RevenueDAO;
import model.dao.RevenueDAOImpl;
import model.dto.Revenue;

public class RevenueServiceImpl implements RevenueService {
	
	private RevenueDAO revenueDao = new RevenueDAOImpl();

	@Override
	public int selectTotalRevenue() throws SQLException {
		return revenueDao.selectTotalRevenue();
	}

	@Override
	public List<Revenue> selectDailyRevenue() throws SQLException {
	    return revenueDao.selectDailyRevenue();
	}

	@Override
	public List<Revenue> selectMonthlyRevenue() throws SQLException {
		return revenueDao.selectMonthlyRevenue();
	}

	@Override
	public List<Revenue> selectYearlyReenue() throws SQLException {
		return revenueDao.selectYearlyReenue();
	}
	

}
