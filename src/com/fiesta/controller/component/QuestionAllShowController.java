package com.fiesta.controller.component;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fiesta.controller.Controller;
import com.fiesta.controller.ModelAndView;
import com.fiesta.model.FiestaDaoImpl;
import com.fiesta.model.dao.QuestionDaoImpl;
import com.fiesta.model.vo.Question;
import com.fiesta.model.vo.Service;

public class QuestionAllShowController implements Controller {

	@Override
	public ModelAndView handle(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String custEmail = request.getParameter("custEmail");
		String path = "";

		//세션에서 Id 값 받아온다.
		ArrayList<Question> list = QuestionDaoImpl.getInstance().showAllQuestion(custEmail);
		request.setAttribute("list", list);
		
		path = "questionAllShow.jsp";
		

		return new ModelAndView(path);
	}

}
