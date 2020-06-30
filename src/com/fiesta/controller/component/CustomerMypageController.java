package com.fiesta.controller.component;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fiesta.controller.Controller;
import com.fiesta.controller.ModelAndView;
import com.fiesta.model.dao.CustomerDaoImpl;
import com.fiesta.model.vo.Customer;
import com.fiesta.model.vo.Custorder;
import com.fiesta.model.vo.Custorderdetail;
import com.fiesta.model.vo.Custrequest;
import com.fiesta.model.vo.Custrequestdetail;

public class CustomerMypageController implements Controller {

	@Override
	public ModelAndView handle(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		HttpSession session = request.getSession();

		Customer customer = (Customer) session.getAttribute("customer"); // eclispe가 시키는대로 casting만 해주면 됐..!
		String email = customer.getCustEmail();
		
		String path = "customerMypage.jsp";

		ArrayList<Custorder> orderList = new ArrayList<>();
		ArrayList<Custorderdetail> orderDetailList = new ArrayList<>();
		ArrayList<Custrequest> requestList = new ArrayList<>();
		ArrayList<Custrequestdetail> requestDetailList = new ArrayList<>();
		
		try {
			orderList = CustomerDaoImpl.getInstance().showAllCustOrder(email);
			orderDetailList = CustomerDaoImpl.getInstance().showAllCustOrderDetail(email);
			requestList = CustomerDaoImpl.getInstance().showAllCustRequest(email);
			requestDetailList = CustomerDaoImpl.getInstance().showAllCustRequestDetail(email);
			
		} catch (SQLException e) {
				
		}
		request.setAttribute("orderList", orderList);
		request.setAttribute("requestList", requestList);
		request.setAttribute("orderDetailList", orderDetailList);
		request.setAttribute("requestDetailList", requestDetailList);		

		return new ModelAndView(path);
	}

}
