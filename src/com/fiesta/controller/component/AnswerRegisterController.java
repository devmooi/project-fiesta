package com.fiesta.controller.component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fiesta.controller.Controller;
import com.fiesta.controller.ModelAndView;
import com.fiesta.model.dao.QuestionDaoImpl;

public class AnswerRegisterController implements Controller {

	@Override
	public ModelAndView handle(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		int qCode = Integer.parseInt(request.getParameter("qCode"));
		System.out.println(qCode);
		String aDesc = request.getParameter("aDesc");
		System.out.println(aDesc);
		int comCode = 1; //세션에서 받아오기
		

		String path = "";

		QuestionDaoImpl.getInstance().insertAnswer(qCode, aDesc, comCode);
		
		request.setAttribute("aDesc", aDesc);
		
		path = "questionView.do";
			

		return new ModelAndView(path);
	}

}
