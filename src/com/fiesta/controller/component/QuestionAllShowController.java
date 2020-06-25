package com.fiesta.controller.component;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fiesta.controller.Controller;
import com.fiesta.controller.ModelAndView;
import com.fiesta.model.FiestaDaoImpl;
import com.fiesta.model.vo.Question;
import com.fiesta.model.vo.Service;

public class QuestionAllShowController implements Controller {

	@Override
	public ModelAndView handle(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		//세션에서 Id 값 받아온다.
		String path = "";

		ArrayList<Question> list = FiestaDaoImpl.getInstance().showAllQuestion("encore@gmail.com");
		request.setAttribute("list", list);
		
		path = "questionAllShow.jsp";
		

		return new ModelAndView(path);
	}

}
