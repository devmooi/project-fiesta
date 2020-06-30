package com.fiesta.controller.component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fiesta.controller.Controller;
import com.fiesta.controller.ModelAndView;
import com.fiesta.model.dao.QuestionDaoImpl;

public class QuestionDeleteController implements Controller {

	@Override
	public ModelAndView handle(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String custEmail = "encore@gmail.com"; //나중에 세션에서 회사코드값 받아올것
		int qCode = Integer.parseInt(request.getParameter("qCode"));
		
		//String path = "";

		QuestionDaoImpl.getInstance().deleteQuestion(qCode);
		
		//path = "ServiceAllShow.do?companycode="+companycode;  
		
		//questionAllShow.do?custEmail=encore@gmail.com
		//response.sendRedirect("questionAllShow.do?custEmail="+custEmail);
		response.sendRedirect("customerMypage.do?custEmail="+custEmail);
		
		//return new ModelAndView(path);
		return null;
	}

}
