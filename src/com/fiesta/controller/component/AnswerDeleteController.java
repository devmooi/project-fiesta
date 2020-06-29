package com.fiesta.controller.component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fiesta.controller.Controller;
import com.fiesta.controller.ModelAndView;
import com.fiesta.model.dao.QuestionDaoImpl;

public class AnswerDeleteController implements Controller {

	@Override
	public ModelAndView handle(HttpServletRequest request, HttpServletResponse response) throws Exception {
		int companycode = 1; //나중에 세션에서 회사코드값 받아올것
		int answerqCode = Integer.parseInt(request.getParameter("answerqCode"));
		
		//String path = "";

		QuestionDaoImpl.getInstance().deleteAnswer(answerqCode);
		
		//path = "ServiceAllShow.do?companycode="+companycode;  
		
		response.sendRedirect("ServiceAllShow.do?companycode="+companycode);
		
		//return new ModelAndView(path);
		return null;
	}

}
