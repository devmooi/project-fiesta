package com.fiesta.controller.component;

import java.util.ArrayList;

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
			
			String requestFiesta = request.getParameter("requestFiesta");
			request.setAttribute("requestFiesta", requestFiesta);
			
			String requestDate = "";
			if(fromDate.equals(toDate)) {
				requestDate = fromDate;
			} else {
				requestDate = fromDate + " ~ " + toDate;
			}

			session.setAttribute("requestDate", requestDate);        // session에 담는 방법은~? 이거다.
			return new ModelAndView("2_requestPlace.jsp");
		
		} else if (command.equals("place")) {
			String[] addr = request.getParameterValues("addr");
			String address = addr[0];
			String detailAddress = addr[1];
			
			String requestAddr = address + " " + detailAddress;
			
			String requestFiesta = request.getParameter("requestFiesta");
			request.setAttribute("requestFiesta", requestFiesta);	

			session.setAttribute("requestAddr", requestAddr);
			return new ModelAndView("3_requestBudget.jsp");
			
		} else if (command.equals("budget")) {
			String fromBudget = request.getParameter("fromBudget");
			String toBudget = request.getParameter("toBudget");
			
			String requestFiesta = request.getParameter("requestFiesta");
			request.setAttribute("requestFiesta", requestFiesta);
			
			String requestBudget = fromBudget + " ~ " + toBudget + "만원대";

			session.setAttribute("requestBudget", requestBudget);
			return new ModelAndView("4_requestRequire.jsp");
			
		} else if (command.equals("require")){
			String requestRequire = request.getParameter("desc");
			String requestFiesta = request.getParameter("requestFiesta");	
			String requestDate = (String) session.getAttribute("requestDate");
			String requestAddr = (String) session.getAttribute("requestAddr");
			String requestBudget = (String) session.getAttribute("requestBudget");
			
			Customer customer = (Customer) session.getAttribute("customer");
			
			//비즈니스 로직
			CustomerDaoImpl.getInstance().insertCustRequest(new Custrequest(requestDate, requestAddr, requestBudget, requestRequire, requestFiesta), customer.getCustEmail());
			return new ModelAndView("../index.jsp");
		} 
		return null;
	}
}
