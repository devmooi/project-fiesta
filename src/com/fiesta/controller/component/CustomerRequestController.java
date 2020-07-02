package com.fiesta.controller.component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fiesta.controller.Controller;
import com.fiesta.controller.ModelAndView;
import com.fiesta.model.dao.CustomerDaoImpl;
import com.fiesta.model.vo.Customer;
import com.fiesta.model.vo.Custrequest;

public class CustomerRequestController implements Controller {

	@Override
	public ModelAndView handle(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		HttpSession session = request.getSession();
		String command = request.getParameter("command");
		
		if (command.equals("begin")) { // index랑 연결시켜야하는데 음.. 거기서는 hidden을 쓸 수 없으니까. 음.. 
			String requestFiesta = request.getParameter("requestFiesta");
			request.setAttribute("requestFiesta", requestFiesta);	
			return new ModelAndView("1_requestRevdate.jsp");
		
		} else if (command.equals("date")) {
			String fromDate = request.getParameter("fromDate");
			String toDate = request.getParameter("toDate");
			
			//String requestFiesta = request.getAttribute(name)
		
			session.setAttribute("fromDate", fromDate);        // session에 담는 방법은~? 이거다.
			session.setAttribute("toDate", toDate);
			
			System.out.println(session.getAttribute("fromDate")); // session 불러오기 확인 완료.
			System.out.println(session.getAttribute("toDate"));   // 내가 넣은것이 그대로 나오는구나. object넣으면 object 뱉고. String 넣으면 String 뱉고.
	
			return new ModelAndView("2_requestPlace.jsp");
		
		} else if (command.equals("place")) {
			//address = request.getParameter("address");
			//detailAddress = request.getParameter("detailAddress");

			//session.setAttribute("address", address);
			//session.setAttribute("detailAddress", detailAddress);
			
			System.out.println(session.getAttribute("address"));
			System.out.println(session.getAttribute("detailAddress"));
			
			return new ModelAndView("3_requestBudget.jsp");
			
		} else if (command.equals("budget")) {
			
			//fromBudget = request.getParameter("fromBudget");
			//toBudget = request.getParameter("toBudget");

			//session.setAttribute("fromBudget", fromBudget);
			//session.setAttribute("toBudget", toBudget);
			
			System.out.println(session.getAttribute("fromBudget"));
			System.out.println(session.getAttribute("toBudget"));
			
			return new ModelAndView("4_requestRequire.jsp");
			
		} else if (command.equals("require")){
		
			//System.out.println("requestFiesta : " + requestFiesta);
			//System.out.println("fromDate : " + fromDate);
			//System.out.println("toDate" + toDate);
			//System.out.println("address" + address);
			//System.out.println("detailAddress" + detailAddress);
			//System.out.println("fromBudget" + fromBudget);
			//System.out.println("toBudget" + toBudget);
			
			String desc = request.getParameter("desc");
			
			session.setAttribute("desc", desc);        
			System.out.println(session.getAttribute("desc"));
			
			Customer customer = (Customer) session.getAttribute("customer");
			String requestRevdate = "";
			//if(fromDate.equals(toDate)) {
			//	requestRevdate = fromDate;
			//} else {
			//	requestRevdate = fromDate + " - " + toDate;
			//}
			//String requestPlace = address + " " + detailAddress;
			//String requestBudget = fromBudget + " ~ " + toBudget + " 만원대";
			
			
			
			//비즈니스 로직 추가
			//CustomerDaoImpl.getInstance().insertCustRequest(new Custrequest(requestRevdate, requestPlace, requestBudget, desc, requestFiesta), customer.getCustEmail());
		
			return new ModelAndView("../register/customerMypage.jsp");
		} 
		return null;
	}
}
