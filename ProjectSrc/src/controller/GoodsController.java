package controller;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import model.dto.Goods;
import model.dto.GoodsTop;
import model.service.GoodsService;
import model.service.GoodsServiceImpl;
import view.EndView;
import view.FailView;

public class GoodsController {

	static GoodsService goodsService = new GoodsServiceImpl();

	//전체상품검색
	public static void goodsSelectAll() {
		try {
			List<Goods> list = goodsService.goodsSelectAll();
			EndView.printGoodsList(list);
		} catch (Exception e) {
			//e.printStackTrace();
			FailView.errorMessage(e.getMessage());
		}

	}

	//가장 많이 팔린 상위 5개
	public static void goodsSelectByTop() {
		try {
			List<GoodsTop> list = goodsService.goodsSelectByTop();
			for (GoodsTop goods : list) {
				System.out.println(goods);
			}
			// EndView.printMessage(map);
		} catch (Exception e) {
			//e.printStackTrace();
			FailView.errorMessage(e.getMessage());
		}

	}

	public static Goods goodsSelectById(String goodsId) {
		try {
			Goods goods = goodsService.goodsSelectById(goodsId);
			return goods;
		} catch (Exception e) {
			FailView.errorMessage(e.getMessage());
			return null;
		}
	}

	//종류별 검색
	public static void goodsSelectByType(String type) {
		try {
			List<Goods> list = goodsService.goodsSelectByType(type);
			EndView.printGoodsList(list);
		} catch (Exception e) {
			//e.printStackTrace();
			FailView.errorMessage(e.getMessage());
		}
	}


	//상품등록
	public static void goodsInsert(Goods goods) {
		try {
			goodsService.goodsInsert(goods);
			EndView.printMessage("상품이 등록되었습니다");
		} catch (SQLException e) {
			//e.printStackTrace();
			FailView.errorMessage(e.getMessage());
		}

	}

	//상품수정
	public static void goodsUpdate(String goodsId, int stockQty) {
		try {
			goodsService.goodsUpdate(goodsId, stockQty);
			EndView.printMessage("상품이 수정되었습니다");
		} catch (SQLException e) {
			//e.printStackTrace();
			FailView.errorMessage(e.getMessage());
		}
	}

	//상품삭제
	public static void goodsDelete(String goodsId) {
		try {
			goodsService.goodsDelete(goodsId);
			EndView.printMessage("상품이 삭제되었습니다");
		} catch (SQLException e) {
			//e.printStackTrace();
			FailView.errorMessage(e.getMessage());;
		}

	}

}

