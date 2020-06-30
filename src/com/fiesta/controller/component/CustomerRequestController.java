package com.fiesta.controller.component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fiesta.controller.Controller;
import com.fiesta.controller.ModelAndView;

public class CustomerRequestController implements Controller {

	@Override
	public ModelAndView handle(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		if (request.getParameter("fromDate") != null) {
		String fromDate = request.getParameter("fromDate");
		String toDate = request.getParameter("toDate");
		
		//System.out.println(fromDate);
		//System.out.println(toDate);
		
		HttpSession session = request.getSession();
		session.setAttribute("fromDate", fromDate);        // session에 담는 방법은~? 이거다.
		session.setAttribute("toDate", toDate);
		
		//System.out.println(session.getAttribute("fromDate")); // session 불러오기 확인 완료.
		//System.out.println(session.getAttribute("toDate"));   // 내가 넣은것이 그대로 나오는구나. object넣으면 object 뱉고. String 넣으면 String 뱉고.
		
		String path = "2_requestPlace.jsp";

		return new ModelAndView(path);
		
		} else if (request.getParameter("postcode") != null) {
			String postcode = request.getParameter("postcode");
			String addressItem = request.getParameter("addressItem");
			String address = request.getParameter("address");
			String detailAddress = request.getParameter("detailAddress");
			String extraAddress = request.getParameter("extraAddress");
			
			HttpSession session = request.getSession();
			session.setAttribute("postcode", postcode);        // session에 담는 방법은~? 이거다.
			session.setAttribute("addressItem", addressItem);
			session.setAttribute("address", address);
			session.setAttribute("detailAddress", detailAddress);
			session.setAttribute("extraAddress", extraAddress);
			
			String path = "3_requestBudget.jsp";

			return new ModelAndView(path);
			
		} /*else if ("form의 이름 == budgetFrm"){
			
			String path = "4_requestRequire.jsp";

			return new ModelAndView(path);
			
		} else if ("form의 이름 == finalFrm"){
			
			tring path = "어디로? 업체로?";

			return new ModelAndView(path);
			
		}*/
		
		return null;
	}

}
