package controller;

import java.sql.SQLException;
import java.util.List;

import model.dto.Revenue;
import model.service.RevenueService;
import model.service.RevenueServiceImpl;
import view.FailView;

public class RevenueController {

	private static RevenueService revenueService = new RevenueServiceImpl();

	public static void getRevenue() {
		try {
			int total = revenueService.selectTotalRevenue();
			List<Revenue> dailyTotal = revenueService.selectDailyRevenue();
			List<Revenue> monthlyTotal = revenueService.selectMonthlyRevenue();
			List<Revenue> yearlyTotal = revenueService.selectYearlyReenue();
			//~~~.~~~(total, dailyTotal, monthlyTotal, yearlyTotal);
			System.out.println("         = 전체 매출 =       ");
			System.out.println("      ["+total+"원 입니다]      ");
			System.out.println();
			System.out.println("         = 일 매출 =       ");
			System.out.println(dailyTotal);
			System.out.println();
			System.out.println("         = 월 매출 =       ");
			System.out.println(monthlyTotal);
			System.out.println();
			System.out.println("         = 연 매출 =       ");
			System.out.println(yearlyTotal);

		} catch (SQLException e) {
			FailView.errorMessage("");
		} catch (Exception e) {

		}

	}

}
