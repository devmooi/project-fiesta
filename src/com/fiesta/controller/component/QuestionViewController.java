package com.fiesta.controller.component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fiesta.controller.Controller;
import com.fiesta.controller.ModelAndView;
import com.fiesta.model.FiestaDaoImpl;
import com.fiesta.model.vo.Answer;
import com.fiesta.model.vo.Question;

public class QuestionViewController implements Controller {

	@Override
	public ModelAndView handle(HttpServletRequest request, HttpServletResponse response) throws Exception {
		int qCode = Integer.parseInt(request.getParameter("qCode"));
		
		String path = "";

		Question qDetail = FiestaDaoImpl.getInstance().showQuestion(qCode);
		
		Answer aDetail = FiestaDaoImpl.getInstance().showAnswer(qCode);
		
		request.setAttribute("qDetail", qDetail);
		
		if(aDetail != null) {
			request.setAttribute("aDetail", aDetail);
		}
		
		path = "questionView.jsp";
			

		return new ModelAndView(path);
	}

}
