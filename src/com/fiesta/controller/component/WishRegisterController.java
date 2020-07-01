package com.fiesta.controller.component;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fiesta.controller.Controller;
import com.fiesta.controller.ModelAndView;
import com.fiesta.model.dao.WishDaoImpl;
import com.fiesta.model.vo.Customer;

public class WishRegisterController implements Controller {

	@Override
	public ModelAndView handle(HttpServletRequest request, HttpServletResponse response) throws Exception {
		PrintWriter out = response.getWriter();
		int companycode = Integer.parseInt(request.getParameter("comCode"));
		System.out.println(companycode);
		HttpSession session = request.getSession();
		Customer customer = (Customer) session.getAttribute("customer");
	
		String existResult = WishDaoImpl.getInstance().insertWish(customer.getCustEmail(),companycode);
		
		request.setAttribute("existResult", existResult);
		
		System.out.println(existResult);
		out.print(existResult);
		//response.sendRedirect("ServiceAllShow.do?companycode="+companycode);
		
		return null;
		
	}

}
