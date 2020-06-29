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
		int companycode = Integer.parseInt(request.getParameter("companycode"));	//나중에 세션에서 받아오는게 더 나을지두...
		//int comCode = 1; //세션에서 받아오기
		

		String path = "";

		QuestionDaoImpl.getInstance().insertAnswer(qCode, aDesc, companycode);
		
		//이메일보내기
		
		//request.setAttribute("aDesc", aDesc);
		//path = "ServiceAllShow.do";
		
		response.sendRedirect("ServiceAllShow.do?companycode="+companycode);

		//return new ModelAndView(path);
		return null;
	}

}
