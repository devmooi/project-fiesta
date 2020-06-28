package com.fiesta.controller.component;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fiesta.controller.Controller;
import com.fiesta.controller.ModelAndView;
import com.fiesta.model.dao.WishDaoImpl;
import com.fiesta.model.vo.Wish;

public class WishListController implements Controller {

	@Override
	public ModelAndView handle(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String custEmail = "encore@gmail.com"; //세션에서 받아올것
		String path = "";

		//세션에서 Id 값 받아온다.
		ArrayList<Wish> wishlist = WishDaoImpl.getInstance().showAllWish(custEmail);
		request.setAttribute("wishlist", wishlist);
		
		path = "../register/wishList.jsp";
		

		return new ModelAndView(path);
	}

}
