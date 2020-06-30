package com.fiesta.controller.component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fiesta.controller.Controller;
import com.fiesta.controller.ModelAndView;
import com.fiesta.model.dao.WishDaoImpl;

public class WishRegisterController implements Controller {

	@Override
	public ModelAndView handle(HttpServletRequest request, HttpServletResponse response) throws Exception {
		int companycode = Integer.parseInt(request.getParameter("comCode"));
		System.out.println(companycode);
		String custEmail = "encore@gmail.com"; //세션에서 받아올것
		

		String existResult = WishDaoImpl.getInstance().insertWish(custEmail,companycode);
		
		request.setAttribute("existResult", existResult);
		
		System.out.println(existResult);
			
		return null;
		
	}

}
