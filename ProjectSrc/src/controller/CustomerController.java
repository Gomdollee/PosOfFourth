package controller;

import model.dto.Customer;
import model.service.CustomerService;
import model.service.CustomerServiceImpl;
import view.FailView;
import view.MenuView;

public class CustomerController {

	static CustomerService customerService = new CustomerServiceImpl();
	/**
	 * 로그인
	 * */
	public static void login(String cusId, String cusPwd) {
		try {
			Customer customer = customerService.login(cusId, cusPwd);
			//MenuView.printUserMenu();
			MenuView.printSecondMenu(cusId);
			//MenuView.menu();
		}catch (Exception e) {
			//e.printStackTrace();
			FailView.errorMessage(e.getMessage());

		}
	}

}
