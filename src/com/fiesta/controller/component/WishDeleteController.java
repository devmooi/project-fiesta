package com.fiesta.controller.component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fiesta.controller.Controller;
import com.fiesta.controller.ModelAndView;
import com.fiesta.model.dao.WishDaoImpl;

public class WishDeleteController implements Controller {

	@Override
	public ModelAndView handle(HttpServletRequest request, HttpServletResponse response) throws Exception {
		int wishCode = Integer.parseInt(request.getParameter("wishCode"));
		String custEmail = request.getParameter("custEmail"); //세션에서 받아오기
		
		String path = "";

		WishDaoImpl.getInstance().deleteWish(wishCode,custEmail);
		
		//path = "wishList.do";  
		
		response.sendRedirect("customerMypage.do?custEmail="+custEmail);
		
		return new ModelAndView(path);
	}

}
