package com.fiesta.controller.component;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fiesta.controller.Controller;
import com.fiesta.controller.ModelAndView;
import com.fiesta.model.dao.RegisterDaoImpl;
import com.fiesta.model.vo.Company;

public class UpdateCompanyController implements Controller {

	@Override
	public ModelAndView handle(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String name = request.getParameter("comName");
		String email = request.getParameter("comEmail");
		String pass = request.getParameter("comPass");
		String tel = request.getParameter("comTel");
		String addr = request.getParameter("comAddr"); 
		String img = request.getParameter("comImg"); 
		String desc = request.getParameter("comDesc"); 
		int categoryCode = Integer.parseInt((String)request.getParameter("categoryCode"));

		Company company = new Company(email, pass, name, tel, addr, img, desc, categoryCode);
		try {
			RegisterDaoImpl.getInstance().updateCompany(company);
		} catch (SQLException e) {
			
		}
		return null; 
	}

}
