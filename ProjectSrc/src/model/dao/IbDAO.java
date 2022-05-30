package model.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import model.dto.Dealer;
import model.dto.Ib;
import model.dto.IbLine;
import model.dto.OrderLine;
import model.dto.Orders;
import model.dto.Sku;

public interface IbDAO {
	/**
	 * 전체 거래처 조회
	 * */
	List<Dealer> dealerSelect() throws SQLException;

	/**
	 *거래처 코드로 sku 상품 검색하기
	 * */
	List<Sku> skuSelectByDealerId(String dealerId) throws SQLException;

	/**
	 * 발주
	 * */
	int ibInsert(Ib ib) throws SQLException;


	/**
	 * 발주한 물품 Goods에 추가 (-> stock 추가시키기) + 발주 상세 등록하기
	 * */
	int[] ibLineUpdate(Connection con, Ib ib) throws SQLException;

	/**
	 * 발주 내역 보기
	 * */
	List<Ib> selectIbByDealerId(String dealerId) throws SQLException;

	/**
	 * 발주 상세 내역
	 * */
	List<IbLine> ibTotal(String ibId) throws SQLException;


}
