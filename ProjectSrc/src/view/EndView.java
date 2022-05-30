package view;

import java.io.PrintStream;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.List;
import java.util.Map;

import controller.OrderController;
import controller.RevenueController;
import model.dto.Dealer;
import model.dto.Goods;
import model.dto.Ib;
import model.dto.IbLine;
import model.dto.OrderLine;
import model.dto.Orders;
import model.dto.Revenue;
import model.dto.Sku;

public class EndView {

	OrderController orderController = new OrderController();
	// 현재 시간
	static LocalTime now = LocalTime.now();
	static SimpleDateFormat format1 =new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	/**
	 * 전체 상품 출력
	 * */
	public static void printGoodsList(List<Goods> list) {
		System.out.println("---------------------------------------------------------------------");
		System.out.println("  상품코드  |         이름          |    가격(원)    |    재고수량   ");
		System.out.println("---------------------------------------------------------------------");
		for (Goods goods : list) {
			System.out.println(String.format("%10s|%25s|%10d|%10d", goods.getGoodsId(), goods.getGoodsName(), goods.getGoodsPrice(), goods.getStockQty()));
		}
		System.out.println("---------------------------------------------------------------------");
	}

	/**
	 * 고객 영수증 출력
	 * 	주문번호:
	 구매일: YYYY-MM-DD HH:MM:SS
	 주문자: 01012345678
	 * */
	public static void printOrderTotal(Orders orders) { //orders //List<Orders> orderList
		System.out.println("————————————————————————————————————————————————————————————————————");
		System.out.println(" ▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒구매 영수증▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒ ");
		System.out.println("————————————————————————————————————————————————————————————————————");
		//System.out.println("주문번호 : " + Orders.getOrderId());
		//System.out.println("구매일   : " + format1.format(now) ); //현재 날짜, 시간 출력
		//System.out.println("주문자   : " + Orders.getCusId()); //입력받은 휴대폰 번호
		System.out.println("---------------------------------------------------------------------");
		System.out.println("   상품코드  |    가격(원)   |    선택수량   ");
		System.out.println("---------------------------------------------------------------------");
		for (OrderLine orderLine : orders.getOrderLineList()) {
			System.out.println(String.format("%10s | %10d | %10d", orderLine.getGoodsId(), orderLine.getGoodsPrice(), orderLine.getOrderQty()));
		}
		System.out.println("---------------------------------------------------------------------");
		System.out.println();

	}

	/**
	 * 전체 거래처 출력
	 * */
	public static void printDealerList(List<Dealer> dealerList) {
		System.out.println("---------------------------------------------------------------------");
		System.out.println("                                거래처 조회                              ");
		System.out.println("---------------------------------------------------------------------");
		System.out.println("  거래처 코드  |    취급 종류    |    거래처 이름    |         주소       |    전화번호");
		System.out.println("---------------------------------------------------------------------");

		for(Dealer dealer : dealerList) {
			System.out.println(dealer);
		}
		System.out.println();
	}

	/**
	 * 거래처별 sku 상품 출력
	 * */
	public static void printSkuListByDealerId(List<Sku> skuList) {
		System.out.println("---------------------------------------------------------------------");
		System.out.println("                              발주  가능 목록                           ");
		System.out.println("---------------------------------------------------------------------");
		System.out.println("취급 상품 코드 |    거래처 코드    |         취급 상품 이름         |      가격");
		System.out.println("---------------------------------------------------------------------");
		for(Sku sku : skuList) {
			System.out.println(sku);
		}
		System.out.println();

	}

	/**
	 * 발주 내역 출력
	 */
	public static void printIb(Ib ib) {
		System.out.println();
		System.out.println("————————————————————————————————————————————————————————————————————");
		System.out.println("                        이 거래처의  발주 내역                     ");
		System.out.println("————————————————————————————————————————————————————————————————————");
		System.out.println("---------------------------------------------------------------------");
		System.out.println("           상품 코드              |             발주 수량             ");
		System.out.println("---------------------------------------------------------------------");
		for (IbLine ibLine : ib.getIbLineList()) {
			System.out.println(String.format("%10s              | %25d", ibLine.getGoodsId(), ibLine.getIbQty()));
		}
		System.out.println("---------------------------------------------------------------------");
		System.out.println();
	}

	

	/**
	 * 전체 매출 출력
	 */
	public static void printAllRevenue() {
		RevenueController rc = new RevenueController();
		rc.getRevenue();// 년,월,일
	}



	/**
	 * 많이 팔린 상위 5개 출력
	 * */
	public static void printMessage(Map<Integer, String> map) {
		System.out.println(map);
	}


	/**
	 * 메세지 출력
	 * */
	public static void printMessage(String message) {
		System.out.println(message);
	}


	/**
	 * 장바구니 보여주기
	 * */
	public static void printViewCart(String id, Map<Goods, Integer> cart) {
		// TODO Auto-generated method stub

	}



}

