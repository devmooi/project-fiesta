package com.fiesta.controller.component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fiesta.controller.Controller;
import com.fiesta.controller.ModelAndView;
import com.fiesta.model.dao.WishDaoImpl;
import com.fiesta.model.vo.Customer;

public class WishDeleteController implements Controller {

	@Override
	public ModelAndView handle(HttpServletRequest request, HttpServletResponse response) throws Exception {
		int wishCode = Integer.parseInt(request.getParameter("wishCode"));
		HttpSession session = request.getSession();
		Customer customer = (Customer) session.getAttribute("customer");
		String custEmail = customer.getCustEmail();
		
		String path = "";

		WishDaoImpl.getInstance().deleteWish(wishCode,custEmail);
		
		//path = "wishList.do";  
		
		response.sendRedirect("customerMypage.do?custEmail="+custEmail);
		
		return new ModelAndView(path);
	}

}
