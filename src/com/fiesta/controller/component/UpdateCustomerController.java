package com.fiesta.controller.component;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fiesta.controller.Controller;
import com.fiesta.controller.ModelAndView;
import com.fiesta.model.dao.RegisterDaoImpl;
import com.fiesta.model.vo.Customer;

public class UpdateCustomerController implements Controller {

	@Override
	public ModelAndView handle(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String name = request.getParameter("custName");
		String email = request.getParameter("custEmail");
		String pass = request.getParameter("custPass");
		String tel = request.getParameter("custTel");
		String group = request.getParameter("custGroup"); 
		
		Customer customer = new Customer(email, name, pass, tel, group);
		try {
			RegisterDaoImpl.getInstance().updateCustomer(customer);
		} catch (SQLException e) {
			
		}
		return null; 
	}

}
