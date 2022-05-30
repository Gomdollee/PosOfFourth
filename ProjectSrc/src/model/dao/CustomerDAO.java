package model.dao;

import java.sql.SQLException;

import model.dto.Customer;

public interface CustomerDAO {
	/**
	 * 로그인하기
	 * */
	Customer login(String cusId, String cusPwd)throws SQLException;
}

