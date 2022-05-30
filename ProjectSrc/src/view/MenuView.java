package view;

import java.util.List;
import java.util.Scanner;

import controller.CustomerController;
import controller.GoodsController;
import controller.IBController;
import controller.OrderController;
import model.dto.Goods;
import model.dto.Ib;
import model.dto.IbLine;
import model.dto.OrderLine;
import model.dto.Orders;
import session.Session;
import session.SessionSet;

public class MenuView {
	private static Scanner sc = new Scanner(System.in);

	/**
	 * 1. 상품구매 or 2.관리자
	 * 여기서 1번 누르면 로그인 기능 오픈 시킨다
	 * 상품구매 - 로그인 - 아이스크림 목록 - 아이스크림 선택
	 */
	public static void menu() { // 1.상품구매, 2.관리자 (printFirstMenu호출)
		while (true) {
			MenuView.printFirstMenu();
			int menu = Integer.parseInt(sc.nextLine());
			switch (menu) {
				case 1:
					MenuView.login();// 로그인창으로...
					System.out.println();
					System.out.println();
					break;

				case 2:
					MenuView.printAdminMenu();// 관리자로...
					break;

				case 3:
					return;
			}
		}

	}

	public static void printFirstMenu() {
		System.out.println();
		System.out.println("         【  Pos of Fourth  】     ");
		System.out.println();
		System.out.println("――――――――――――――――――――――――――――――――――――――――");
		System.out.println("    1. 상품구매(로그인)  |  2. 관리자   ");
		System.out.println("――――――――――――――――――――――――――――――――――――――――");
		System.out.print("메뉴를 선택하세요 》 ");
	}

	public static void printSecondMenu(String cusId) {
		while (true) {
			SessionSet ss = SessionSet.getInstance();
			//System.out.println(ss.getSet()); //Set객체
			System.out.println(" ▷ 현재 " + cusId + "님 로그인 중 입니다. ◁ ");
			System.out.println();
			System.out.println("――――――――――――――――――――――――――――――――――――――――");
			System.out.println("1. 아이스크림 구매하기  |  2. 뒤로가기  ");
			System.out.println("――――――――――――――――――――――――――――――――――――――――");
			System.out.print("메뉴를 선택하세요 》 ");
			int menu = Integer.parseInt(sc.nextLine());

			while (true) {
				switch (menu) {
					case 1:
						MenuView.printInputOrder(cusId);// 아이스크림 종류별 목록...
						break;
					case 2:
						logout(cusId);
						System.out.println();
						MenuView.menu();// 뒤로가기...(이전메뉴 호출)
						//MenuView.logout(null);// 로그인창으로...
						break;
				}
			}
		}
	}

	/**
	 * 로그인 메뉴
	 * 휴대폰 번호 입력 - db에 저장 후 - 다음 메뉴 출력
	 */
	public static void login() {
		System.out.println();
		System.out.println("  【 Pos of Fourth 회원 로그인 】  ");
		System.out.print(" ☎ 휴대폰 번호 : ");
		String cusId = sc.nextLine();
		System.out.print(" ☞ 비밀번호 : ");
		String cusPwd = sc.nextLine();

		CustomerController.login(cusId, cusPwd);
		// CustomerController에서 printSecondMenu() 호출시키는것으로 수정하기
	}

	/**
	 * 로그아웃
	 * db에 저장된 번호 삭제...?
	 */
	public static void logout(String cusId) {
		Session session = new Session(cusId);

		SessionSet ss = SessionSet.getInstance();
		ss.remove(session);
	}

	/**
	 * 이번달 인기상품 + 종류선택, 코드 , 개수 입력
	 */
	public static void printInputOrder(String cusId) {
		// 여기 상단에 인기상품을 띄운다..?
		//GoodsController.goodsSelectByTop(); // 상위품목 호출
		// 인기 상품 기준.... 판매 갯수!
		//EndView.printMessage(Map<Integer, String> map);
		while (true) {
			System.out.println();
			System.out.println(" 【 PosOfFourth의 아이스크림 전문점에 오신걸 환영합니다 ^_^ 】 ");
			System.out.println();
			System.out.println(
				"―――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――");
			System.out.println("1.콘 아이스크림 |  2. 막대 아이스크림  |  3.컵 아이스크림  | 4. 쭈쭈바  | 5. 인기 상품 | 6. 나가기 ");
			System.out.println(
				"―――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――");
			System.out.print("원하는 아이스크림 종류를 선택하세요 》");
			int menu = Integer.parseInt(sc.nextLine());
			switch (menu) {

				case 1: // 콘 아이스크림
					GoodsController.goodsSelectByType("C");// 콘 아이스크림 상품조회
					MenuView.printselectOrders(cusId);
					//printInputOrdersub();
					break;

				case 2: // 막대 아이스크림
					GoodsController.goodsSelectByType("B");// 막대 아이스크림 상품조회
					MenuView.printselectOrders(cusId);
					//printInputOrdersub();
					break;

				case 3: // 컵 아이스크림
					GoodsController.goodsSelectByType("P");// 컵 아이스크림 상품조회
					MenuView.printselectOrders(cusId);
					// printInputOrdersub();
					break;

				case 4: // 쭈쭈바
					GoodsController.goodsSelectByType("J");// 쭈쭈바 아이스크림 상품조회
					MenuView.printselectOrders(cusId);
					// printInputOrdersub();
					break;

				case 5:
					GoodsController.goodsSelectByTop(); //인기상품 top5 조회
					MenuView.printselectOrders(cusId);
					//printInputOrdersub();
					break;

				case 6: // 뒤로가기
					MenuView.menu();// 뒤로가기...(이전메뉴 호출)
					break;

			}
			if (menu == 6) {
				break;
			}
		}

	}

	////////////////////////////////////////////////////////////////////////////////
	/*
	 * 구매기능 - 1. 주문하기 2. 주문상세 등록하기 3. 재고량 감소시키기 4. 결제하기
	 */
	public static void printselectOrders(String cusId) {
		//원하는 상품을 구매하기 위해서 선택한다.
		System.out.println();
		System.out.println(" 【  Pos of Fourth  】 ");
		System.out.println("―――――――――――――――――――――――――――――――――――――――――――――――――――――――――");
		System.out.println(" 1. 주문하기 | 2. 아이스크림 메뉴 조회하기  |  3.나가기 ");
		System.out.println("―――――――――――――――――――――――――――――――――――――――――――――――――――――――――");
		System.out.print("아이스크림 주문은 1번을 눌러주세요 》 ");
		int menu = Integer.parseInt(sc.nextLine());
		//while(true) {
		switch (menu) {
			case 1:
				MenuView.printInsertOrders(cusId);
				break;
			case 2:
				MenuView.printInputOrder(cusId);
				break;
			case 3:
				MenuView.menu();// 뒤로가기...(이전메뉴 호출)
				break;

		}
		//}
	}

	/*
	 * 주문기능
	 */

	public static void printInsertOrders(String cusId) {
		Orders orders = new Orders(null, cusId, null); //sysdate도 null로 해야하는 것일까?
		while (true) {
			//상품 구매는 아이스크림 이름과 수량을 입력 받는다.
			System.out.println(" 【  Pos of Fourth  】 ");
			System.out.println("―――――――――――――――――――――――――――――――――――――――――――――");
			System.out.print("아이스크림 상품코드 입력하기(품목 등록을 중단하시려면 \"END\"를 입력해주세요)  》 ");
			String goodsId = sc.nextLine();
			if (goodsId.toLowerCase().equals("end")) {
				break;
			}
			System.out.print("아이스크림 수 입력하기  》 ");
			int orderQty = Integer.parseInt(sc.nextLine());
			System.out.println("―――――――――――――――――――――――――――――――――――――――――――――");
			Goods goods = GoodsController.goodsSelectById(goodsId);
			OrderLine orderLine = new OrderLine(null, null, goodsId, goods.getGoodsPrice(), orderQty);
			orders.getOrderLineList().add(orderLine);
		}
		OrderController.orderInsert(orders);
		EndView.printOrderTotal(orders);
	}

	public static void printOrderTotal() {

	}

	////////////////////////////////////////////////////////////////////////////////

	

	/**
	 * 관리자기능 - 1.매출조회 2.거래처관리 3.상품관리 4.뒤로가기
	 */
	public static void printAdminMenu() {
		while (true) {
			System.out.println();
			System.out.println("  【 Pos of Fourth 관리자 모드 】  ");
			System.out.println();
			System.out.println("――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――");
			System.out.println("1. 매출조회 |  2. 발주하기  |  3. 상품관리  | 4. 뒤로가기");
			System.out.println("――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――");
			System.out.print("메뉴 번호를 선택하세요 》");
			int menu = Integer.parseInt(sc.nextLine());
			//while (true) {
			switch (menu) {

				case 1: // 매출조회
					EndView.printAllRevenue();
					break;
                	/*RevenueController rc = new RevenueController();
                    rc.getRevenue();// 년,월,일*/

				case 2: // 거래처관리
					DealerAdminMenu();// -1.거래처 조회 -2.발주
					break;

				case 3: // 상품관리
					GoodsAdminMenu();//// 1. 상품 등록 2. 상품 수정 3. 상품 삭제 4. 재고현황
					break;

				case 4: // 뒤로가기
					MenuView.printFirstMenu();// 뒤로가기...(이전메뉴 호출)
					return;
			}
		}

	}

	/**
	 * 관리자기능 2번 눌렀을때 - 거래처 관리
	 */
	public static void DealerAdminMenu() {
		while (true) {
			System.out.println();
			IBController.dealerSelect(); //거래처 조회
			System.out.println();
			System.out.println("―――――――――――――――――――――――――――――――――――");
			System.out.println("    1.발주하기  |  2.뒤로가기   ");
			System.out.println("―――――――――――――――――――――――――――――――――――");
			System.out.print("메뉴 번호를 선택하세요 》");
			int menu = Integer.parseInt(sc.nextLine());
			switch (menu) {
				case 1: // 발주하기 //무한 반복 해결해야함
					System.out.print("발주할 거래처 코드 입력: ");
					String dealerId = sc.nextLine();
					IBController.skuSelectByDealerId(dealerId);
					printInputIb(dealerId);
					break;
				case 2:
					break;
			}
			if (menu == 2) {
				break;
			}
		}
		printAdminMenu();
	}

	/**
	 * 발주하기
	 * */
	public static void printInputIb(String dealerId) {
		Ib ib = new Ib(null, dealerId, null);
		while (true) {
			System.out.print("발주할 상품 코드(발주 품목 등록을 중단하시려면 \"END\"를 입력해주세요) : ");
			String goodsId = sc.nextLine();
			if (goodsId.toLowerCase().equals("end")) {
				break;
			}
			System.out.print("발주할 수량 : ");
			int ibQty = Integer.parseInt(sc.nextLine());
			IbLine ibLine = new IbLine(null, null, goodsId, ibQty, 0);
			ib.getIbLineList().add(ibLine);
		}
		IBController.ibInsert(ib); //발주
		EndView.printIb(ib);
	}

	/**
	 * 상품관리
	 */
	public static void GoodsAdminMenu() {
		String goodsId = null;
		String goodsName = null;
		int stockQty = 0;
		int goodsPrice = 0;
		while (true) {
			System.out.println("  【 Pos of Fourth 상품 관리 】  ");
			System.out.println();
			System.out.println("―――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――");
			System.out.println("1. 상품 등록 | 2. 재고 현황 | 3. 뒤로가기");
			System.out.println("―――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――");
			System.out.print("번호를 선택하세요 》");
			int menu = Integer.parseInt(sc.nextLine());

			switch (menu) {
				case 1: // 상품 등록
					System.out.print("등록할 상품코드를 입력해주세요 : ");
					goodsId = sc.nextLine();

					System.out.print("등록할 상품이름을 입력해주세요 : ");
					goodsName = sc.nextLine();

					System.out.print("등록할 상품의 수량을 입력해주세요 : ");
					stockQty = Integer.parseInt(sc.nextLine());

					System.out.print("등록할 상품의 가격을 입력해주세요 : ");
					goodsPrice = Integer.parseInt(sc.nextLine());

					Goods goods = new Goods(goodsId, goodsName, stockQty, goodsPrice);
					GoodsController.goodsInsert(goods);
					break;

				case 2: // 재고현황
					GoodsController.goodsSelectAll();
					break;

				case 3:
					break;
			}

			if (menu == 3) {
				break;
			}
		}
	}

}
