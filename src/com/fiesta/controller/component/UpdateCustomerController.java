package com.fiesta.controller.component;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fiesta.controller.Controller;
import com.fiesta.controller.ModelAndView;
import com.fiesta.model.dao.CustomerDaoImpl;
import com.fiesta.model.dao.RegisterDaoImpl;
import com.fiesta.model.vo.Customer;
import com.fiesta.model.vo.Custorder;
import com.fiesta.model.vo.Custorderdetail;
import com.fiesta.model.vo.Custrequest;
import com.fiesta.model.vo.Custrequestdetail;

public class UpdateCustomerController implements Controller {

	@Override
	public ModelAndView handle(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String name = request.getParameter("custName");
		String email = request.getParameter("custEmail");
		String pass = request.getParameter("custPass");
		String tel = request.getParameter("custTel");
		String group = request.getParameter("custGroup"); 
		
		String path = "customerMypage.jsp";
		
		int orderCode = Integer.parseInt((String)request.getParameter("orderCode"));
		int custdetailCode = Integer.parseInt((String)request.getParameter("custdetailCode"));
		int requestCode = Integer.parseInt((String)request.getParameter("requestCode"));
		int detailCode = Integer.parseInt((String)request.getParameter("detailCode"));
		
		Customer customer = new Customer(email, name, pass, tel, group);
		
		try {
			RegisterDaoImpl.getInstance().updateCustomer(customer);
			
			ArrayList<Custorder> orderList = CustomerDaoImpl.getInstance().showAllCustOrder(orderCode);
			ArrayList<Custorderdetail> orderDetailList = CustomerDaoImpl.getInstance().showAllCustOrderDetail(custdetailCode);
			ArrayList<Custrequest> requestList = CustomerDaoImpl.getInstance().showAllCustRequest(requestCode);
			ArrayList<Custrequestdetail> requestDetailList = CustomerDaoImpl.getInstance().showAllCustRequestDetail(detailCode);
		
			request.setAttribute("orderList", orderList);
			request.setAttribute("requestList", requestList);
			request.setAttribute("orderDetailList", orderDetailList);
			request.setAttribute("requestDetailList", requestDetailList);
			
		} catch (SQLException e) {
			
		}

		return new ModelAndView(path);
	}

}