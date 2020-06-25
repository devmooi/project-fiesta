package com.fiesta.controller.component;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fiesta.controller.Controller;
import com.fiesta.controller.ModelAndView;
import com.fiesta.model.FiestaDaoImpl;
import com.fiesta.model.dao.RegisterDaoImpl;
import com.fiesta.model.vo.Customer;

public class LoginController implements Controller {

	@Override
	public ModelAndView handle(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String email = request.getParameter("email");
		String pass = request.getParameter("pass");
		String path = "loginResult.jsp";
		
		try {
			Customer customer = RegisterDaoImpl.getInstance().loginCustomer(email, pass);
			HttpSession session = request.getSession();
			
				if(customer != null) {
					session.setAttribute("customer", customer);
				}
		} catch (SQLException e) {
			
		}
		return new ModelAndView(path);
	}

}
