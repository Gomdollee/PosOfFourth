package model.service;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import exception.NotFoundException;
import model.dao.GoodsDAO;
import model.dao.GoodsDAOImpl;
import model.dto.Goods;
import model.dto.GoodsTop;

public class GoodsServiceImpl implements GoodsService {
	
	private GoodsDAO goodsDAO = new GoodsDAOImpl();

	@Override
	public List<Goods> goodsSelectAll() throws SQLException {
		List<Goods> list = goodsDAO.goodsSelectAll();
		if(list.size() == 0 || list.isEmpty()) {
			throw new SQLException("상품의 정보가 없어 검색할 수 없습니다");			
		}
		return list;
	}

	@Override
	public List<GoodsTop> goodsSelectByTop() throws SQLException {
		List<GoodsTop> list = goodsDAO.goodsSelectByTop();
		if(list.size() == 0 || list.isEmpty()) {
        	throw new SQLException("상위 5개 상품검색할 수 없습니다");
        }
		return list;
	}

	@Override
	public Goods goodsSelectById(String goodsId) throws SQLException {
		Goods goods = goodsDAO.goodsSelectById(goodsId);
		return goods;
	}
	
	@Override
	public List<Goods> goodsSelectByType(String type) throws SQLException {
        List<Goods> list = goodsDAO.goodsSelectByType(type);
        if(list.size() == 0 || list.isEmpty()) {
        	throw new SQLException("상품의 종류를 검색할 수 없습니다");
        }
		return list;
	}

	@Override
	public void goodsInsert(Goods goods) throws SQLException {
	 int result = goodsDAO.goodsInsert(goods);
	 if(result == 0) throw new SQLException("상품이 등록되지 않았습니다");
		
	}

	@Override
	public void goodsUpdate(String goodsId, int qty) throws SQLException {
		int result = goodsDao.goodsUpdate(goodsId, qty);
		if(result == 0) throw new SQLException("수량이 수정되지 않았습니다");
		
	}

	@Override
	public void goodsDelete(String goodsId) throws SQLException {
		int result = goodsDAO.goodsDelete(goodsId);
		if(result == 0) throw new SQLException("상품이 삭제되지 않았습니다");
			
	}





}

