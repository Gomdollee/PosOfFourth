package model.dao;

import java.sql.SQLException;
import java.util.List;

import model.dto.Goods;
import model.dto.GoodsTop;

public interface GoodsDAO {
	/**
	 전체검색
	 * */
	List<Goods> goodsSelectAll() throws SQLException;

	/**
	 상위 top5검색
	 * */
	List<GoodsTop> goodsSelectByTop() throws SQLException;

	/**
	 종류별 검색
	 * */
	List<Goods> goodsSelectByType(String type) throws SQLException;

	/**
	 GoodsID를 이용해 검색
	 * */
	Goods goodsSelectById(String goodsId) throws SQLException;

	/**
	 상품등록
	 * */
	int goodsInsert(Goods goods) throws SQLException;

	/**
	 상품수정
	 * */
	int goodsUpdate(String goodsId, int stockQty) throws SQLException;

	/**
	 상품삭제
	 * */
	int goodsDelete(String goodsId) throws SQLException;

}