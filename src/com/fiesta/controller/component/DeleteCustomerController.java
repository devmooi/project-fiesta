package com.fiesta.controller.component;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fiesta.controller.Controller;
import com.fiesta.controller.ModelAndView;
import com.fiesta.model.dao.RegisterDaoImpl;

public class DeleteCustomerController implements Controller {

	@Override
	public ModelAndView handle(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String email = request.getParameter("email");
		String pass = request.getParameter("pass");
		
		//HttpSession session = request.getSession();
		//String path = "../index.jsp";
		
		//Customer customer = new Customer();
		//customer.setCustEmail(customer.getCustEmail());
		//customer.setCustPass(customer.getCustPass());
		
			
		try {
			
			//if(session.getAttribute("customer") != null) {
				RegisterDaoImpl.getInstance().deleteCustomer(email, pass);

			//}

		} catch (SQLException e) {
			
		}	
		return null;
		//return new ModelAndView(path);
	}

}
