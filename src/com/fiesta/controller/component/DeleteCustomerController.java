package com.fiesta.controller.component;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fiesta.controller.Controller;
import com.fiesta.controller.ModelAndView;
import com.fiesta.model.dao.RegisterDaoImpl;

public class DeleteCustomerController implements Controller {

	@Override
	public ModelAndView handle(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		String email = session.getId(email);
		String path = "../index.jsp";
		
		try {
			if(session.getAttribute("customer") != null) {
				RegisterDaoImpl.getInstance().deleteCustomer(email, pass);

			}

		} catch (SQLException e) {
			
		}	
		//return null;
		return new ModelAndView(path);
	}

}
