package com.fiesta.controller.component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fiesta.controller.Controller;
import com.fiesta.controller.ModelAndView;

public class LogoutController implements Controller {

	public ModelAndView handle(HttpServletRequest request, HttpServletResponse response) throws Exception {

		HttpSession session = request.getSession();
		
		if(session.getAttribute("customer") != null) {
			session.invalidate();
		} else if(session.getAttribute("company") != null) {
			session.invalidate();
		}
		
		return new ModelAndView("index.jsp", true);
	}
}
