package com.fiesta.controller.component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fiesta.controller.Controller;
import com.fiesta.controller.ModelAndView;
import com.fiesta.model.dao.CustomerDaoImpl;

public class OrderCancelController implements Controller {

	@Override
	public ModelAndView handle(HttpServletRequest request, HttpServletResponse response) throws Exception {
		int orderCode = Integer.parseInt(request.getParameter("orderCode"));
		System.out.println(orderCode);
		CustomerDaoImpl.getInstance().changeOrderCondition(orderCode, "주문취소");
		
		
		return null;
	}

}
