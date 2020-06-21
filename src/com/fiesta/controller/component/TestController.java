package com.fiesta.controller.component;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fiesta.controller.Controller;
import com.fiesta.controller.ModelAndView;
import com.fiesta.model.ItemDaoImpl;
import com.fiesta.model.vo.Item;

public class TestController implements Controller {

	@Override
	public ModelAndView handle(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ArrayList<Item> list = ItemDaoImpl.getInstance().getAllItem();
		request.setAttribute("list", list);
		return new ModelAndView("test/test.jsp");
	}

}