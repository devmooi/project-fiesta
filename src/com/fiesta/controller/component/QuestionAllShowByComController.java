package com.fiesta.controller.component;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fiesta.controller.Controller;
import com.fiesta.controller.ModelAndView;
import com.fiesta.model.dao.QuestionDaoImpl;
import com.fiesta.model.vo.Question;

public class QuestionAllShowByComController implements Controller {

	@Override
	public ModelAndView handle(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String path = "";

		//세션에서 업체코드값 받아온다. 지금은 임시데이터 1
		ArrayList<Question> list = QuestionDaoImpl.getInstance().showAllQuestionByCompany(1);
		request.setAttribute("list", list);
		
		path = "questionAllShow.jsp";
		

		return new ModelAndView(path);
	}

}
