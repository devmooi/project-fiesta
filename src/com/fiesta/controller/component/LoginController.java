package com.fiesta.controller.component;

import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fiesta.controller.Controller;
import com.fiesta.controller.ModelAndView;
import com.fiesta.model.dao.RegisterDaoImpl;
import com.fiesta.model.vo.Company;
import com.fiesta.model.vo.Customer;

public class LoginController implements Controller {

	@Override
	public ModelAndView handle(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String email = request.getParameter("email");
		String pass = request.getParameter("pass");
		String pick = request.getParameter("pick");
		
		boolean flag = false;
		PrintWriter out = response.getWriter();

		try {
			if(pick.equals("customer")) { 
				Customer customer = RegisterDaoImpl.getInstance().loginCustomer(email, pass);
				HttpSession session = request.getSession();

				if(customer != null) {
					session.setAttribute("customer", customer);
					flag = true;
				}
			} else if (pick.equals("company")) {
				Company company = RegisterDaoImpl.getInstance().loginCompany(email, pass);
				HttpSession session = request.getSession();

					if(company != null) {
						session.setAttribute("company", company);
						flag = true;
					}
			}
			out.print(flag);
			
		} catch (SQLException e) {
			
		}
		return null;
	}

}
