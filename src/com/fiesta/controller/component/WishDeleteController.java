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
		
		String path = "";

		WishDaoImpl.getInstance().deleteWish(wishCode);
		
		path = "wishList.do";  
		
		return new ModelAndView(path);
	}

}
