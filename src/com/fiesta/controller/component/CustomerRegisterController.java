package com.fiesta.controller.component;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fiesta.controller.Controller;
import com.fiesta.controller.ModelAndView;
import com.fiesta.model.FiestaDaoImpl;
import com.fiesta.model.dao.RegisterDaoImpl;
import com.fiesta.model.vo.Company;
import com.fiesta.model.vo.Customer;
import com.fiesta.util.AES256Util;

public class CustomerRegisterController implements Controller {

	@Override
	public ModelAndView handle(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String email = request.getParameter("email");
		String name = request.getParameter("name");
		String pass = request.getParameter("pass");
		String tel = request.getParameter("tel");
		String group = request.getParameter("group");
		
		//암호화
		String key = "aes256-password-key"; 
		AES256Util aes256 = new AES256Util(key);
		pass = aes256.encrypt(pass);
		System.out.println("Encode PWD : " + pass);
		
		//DB 등록
		Customer customer = new Customer(email, name, pass, tel, group);
		try {
			RegisterDaoImpl.getInstance().registerCustomer(customer);
			
		} catch (SQLException e) {
			System.out.println(e);
		}
		
		return new ModelAndView("registerSuccess.jsp", true);
	
	}

}
