package com.fiesta.controller.component;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fiesta.controller.Controller;
import com.fiesta.controller.ModelAndView;
import com.fiesta.model.dao.RegisterDaoImpl;
import com.fiesta.model.vo.Company;

public class CompanyRegisterController implements Controller {

	@Override
	public ModelAndView handle(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String email = request.getParameter("email");
		String name = request.getParameter("name");
		String pass = request.getParameter("pass");
		String tel = request.getParameter("tel");
		String addr = request.getParameter("addr");
		String img = request.getParameter("img");
		String desc = request.getParameter("desc");
		int categoryCode = Integer.parseInt((String)request.getParameter("categoryCode"));



		String path = "../index.jsp";
		
		Company company = new Company(email, pass, name, tel, addr, img, desc, categoryCode);
		try {
			RegisterDaoImpl.getInstance().registerCompany(company);
			
		} catch (SQLException e) {
			
		}
		return new ModelAndView(path);
	
	}
}

