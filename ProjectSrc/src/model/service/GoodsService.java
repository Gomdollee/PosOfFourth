package model.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import exception.NotFoundException;
import model.dao.GoodsDAO;
import model.dao.GoodsDAOImpl;
import model.dto.Goods;
import model.dto.GoodsTop;

public interface GoodsService {
	GoodsDAO goodsDao = new GoodsDAOImpl();

	public List<Goods> goodsSelectAll() throws SQLException;

	public List<GoodsTop> goodsSelectByTop() throws SQLException;

	public Goods goodsSelectById(String goodsId) throws SQLException;

	public List<Goods> goodsSelectByType(String type) throws SQLException;

	public void goodsInsert(Goods goods) throws SQLException;

	public void goodsUpdate(String goodsId, int qty) throws SQLException;

	public void goodsDelete(String goodId) throws SQLException;

}
