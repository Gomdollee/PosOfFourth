package view;

import java.time.LocalDate;

public class StartView {

	public static void main(String[] args) {
		LocalDate now = LocalDate.now(); //현재 날짜
		System.out.println();
		System.out.println("         ——————"+now+"——————   "); //현재 날짜 출력
		System.out.println();
		MenuView.menu();
	}

}
