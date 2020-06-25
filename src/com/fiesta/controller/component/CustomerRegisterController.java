package com.fiesta.controller.component;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fiesta.controller.Controller;
import com.fiesta.controller.ModelAndView;
import com.fiesta.model.FiestaDaoImpl;
import com.fiesta.model.vo.Customer;

public class CustomerRegisterController implements Controller {

	@Override
	public ModelAndView handle(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String email = request.getParameter("email");
		String name = request.getParameter("name");
		String pass = request.getParameter("pass");
		String tel = request.getParameter("tel");
		String group = request.getParameter("group");

		String path = "../index.jsp";
		
		Customer customer = new Customer(email, name, pass, tel, group);
		try {
			FiestaDaoImpl.getInstance().registerCustomer(customer);
			
		} catch (SQLException e) {
			
		}
		return new ModelAndView(path);
	
	}

}
