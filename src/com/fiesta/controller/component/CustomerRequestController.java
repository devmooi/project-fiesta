package com.fiesta.controller.component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fiesta.controller.Controller;
import com.fiesta.controller.ModelAndView;
import com.fiesta.model.vo.Customer;

public class CustomerRequestController implements Controller {

	@Override
	public ModelAndView handle(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		HttpSession session = request.getSession();
		String command = request.getParameter("command");
		
		if (command.equals("begin")) { // index랑 연결시켜야하는데 음.. 거기서는 hidden을 쓸 수 없으니까. 음.. 
			String requestFiesta = request.getParameter("requestFiesta");
			request.setAttribute("requestFiesta", requestFiesta);	
			
			String path = "1_requestRevdate.jsp";
			//String path = "/customer/1_requestRevdate.jsp";

			return new ModelAndView(path);
		
		} else if (command.equals("date")) {
			String fromDate = request.getParameter("fromDate");
			String toDate = request.getParameter("toDate");
			
			String requestFiesta = request.getParameter("requestFiesta");
			request.setAttribute("requestFiesta", requestFiesta);	
		
			session.setAttribute("fromDate", fromDate);        // session에 담는 방법은~? 이거다.
			session.setAttribute("toDate", toDate);
			
			System.out.println(session.getAttribute("fromDate")); // session 불러오기 확인 완료.
			System.out.println(session.getAttribute("toDate"));   // 내가 넣은것이 그대로 나오는구나. object넣으면 object 뱉고. String 넣으면 String 뱉고.
	
			String path = "2_requestPlace.jsp";
	
			return new ModelAndView(path);
		
		} else if (command.equals("place")) {
			String postcode = request.getParameter("postcode");
			String address = request.getParameter("address");
			String detailAddress = request.getParameter("detailAddress");
			String extraAddress = request.getParameter("extraAddress");
			
			String requestFiesta = request.getParameter("requestFiesta");
			request.setAttribute("requestFiesta", requestFiesta);	

			session.setAttribute("postcode", postcode);        // session에 담는 방법은~? 이거다.
			session.setAttribute("address", address);
			session.setAttribute("detailAddress", detailAddress);
			session.setAttribute("extraAddress", extraAddress);
			
			System.out.println(session.getAttribute("postcode")); // session 불러오기 확인 완료.
			System.out.println(session.getAttribute("address"));
			System.out.println(session.getAttribute("detailAddress"));
			System.out.println(session.getAttribute("extraAddress"));
			
			String path = "3_requestBudget.jsp";


			return new ModelAndView(path);
			
		} else if (command.equals("budget")) {
			
			String fromBudget = request.getParameter("fromBudget");
			String toBudget = request.getParameter("toBudget");
			
			String requestFiesta = request.getParameter("requestFiesta");
			request.setAttribute("requestFiesta", requestFiesta);	

			session.setAttribute("fromBudget", fromBudget);
			session.setAttribute("toBudget", toBudget);
			
			System.out.println(session.getAttribute("fromBudget"));
			System.out.println(session.getAttribute("toBudget"));
			
			
			String path = "4_requestRequire.jsp";

			return new ModelAndView(path);
			
		} else if (command.equals("require")){
			
			String desc = request.getParameter("desc");
			
			String requestFiesta = request.getParameter("requestFiesta");
			request.setAttribute("requestFiesta", requestFiesta);	
			
			session.setAttribute("desc", desc);        
			System.out.println(session.getAttribute("desc"));
			
			String path = "test_result.jsp";

			return new ModelAndView(path);
		} 
		return null;
	}
}
