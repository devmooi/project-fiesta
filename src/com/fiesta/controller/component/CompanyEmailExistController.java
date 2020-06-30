package com.fiesta.controller.component;

import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fiesta.controller.Controller;
import com.fiesta.controller.ModelAndView;
import com.fiesta.model.dao.RegisterDaoImpl;

public class CompanyEmailExistController implements Controller {

	@Override
	public ModelAndView handle(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String email = request.getParameter("comEmail");

		boolean flag = false;
		PrintWriter out = response.getWriter();
		try {
			flag = RegisterDaoImpl.getInstance().comEmailExist(email);
			out.print(flag);
		} catch (SQLException e) {
			
		}
		return null;
	}

}
