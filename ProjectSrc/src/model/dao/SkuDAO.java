package model.dao;

import java.sql.SQLException;

import model.dto.Sku;

public interface SkuDAO {

	/**
	 *  Id로 조회
	 */
	Sku selectSkuById(String skuId) throws SQLException;
}
