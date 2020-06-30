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
		
		String path = "";
		
		Customer customer = new Customer(email, name, pass, tel, group);
				
		try {
			RegisterDaoImpl.getInstance().updateCustomer(customer);
			
			path = "customerMypage.jsp";
			
		} catch (SQLException e) {
			
		}

		return new ModelAndView(path);
	}

}