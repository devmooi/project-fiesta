package com.fiesta.controller.component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fiesta.controller.Controller;
import com.fiesta.controller.ModelAndView;
import com.fiesta.model.dao.CompanyDaoImpl;
import com.fiesta.model.dao.CustomerDaoImpl;
import com.fiesta.model.dao.QuestionDaoImpl;
import com.fiesta.model.vo.Customer;
import com.fiesta.model.vo.Custorder;
import com.fiesta.model.vo.Custrequest;
import com.fiesta.model.vo.Service;

public class CustomerOrderFromController implements Controller {

	@Override
	public ModelAndView handle(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		HttpSession session = request.getSession();
		String command = request.getParameter("command");
		System.out.println("여기 실행 : " + command);
		if(command.equals("date")) {
			String fromDate = request.getParameter("fromDate");
			String toDate = request.getParameter("toDate");
			
			String companycode = request.getParameter("companycode");
			String serviceCode = request.getParameter("serviceCode");
			
			request.setAttribute("companycode", companycode);
			request.setAttribute("serviceCode", serviceCode);
			
			String requestDate = "";
			if(fromDate.equals(toDate)) {
				requestDate = fromDate;
			} else {
				requestDate = fromDate + " ~ " + toDate;
			}
			
			session.setAttribute("requestDate", requestDate);        // session에 담는 방법은~? 이거다.
			return new ModelAndView("customer/2_requestPlace.jsp");
		} else if (command.equals("place")) {
			String[] addr = request.getParameterValues("addr");
			String address = addr[0];
			String detailAddress = addr[1];
			
			String requestAddr = address + " " + detailAddress;
			
			String companycode = request.getParameter("companycode");
			String serviceCode = request.getParameter("serviceCode");
			
			request.setAttribute("companycode", companycode);
			request.setAttribute("serviceCode", serviceCode);	

			session.setAttribute("requestAddr", requestAddr);
			return new ModelAndView("customer/3_requestBudget.jsp");
		
		} else if (command.equals("budget")) {
			String fromBudget = request.getParameter("fromBudget");
			String toBudget = request.getParameter("toBudget");
			
			String companycode = request.getParameter("companycode");
			String serviceCode = request.getParameter("serviceCode");
			
			request.setAttribute("companycode", companycode);
			request.setAttribute("serviceCode", serviceCode);	
			
			String requestBudget = fromBudget + " ~ " + toBudget + "만원대";

			session.setAttribute("requestBudget", requestBudget);
			return new ModelAndView("customer/4_requestRequire.jsp");
			
		} else if (command.equals("require")){
			String orderRevdate = (String) session.getAttribute("requestDate");
			String orderPlace = (String) session.getAttribute("requestAddr");
			String orderBudget = (String) session.getAttribute("requestBudget");
			String orderRequire = request.getParameter("desc");
			
			int companycode = Integer.parseInt(request.getParameter("companycode"));
			int serviceCode = Integer.parseInt(request.getParameter("serviceCode"));
			
			Customer customer = (Customer) session.getAttribute("customer");
			
			Custorder custorder = new Custorder(orderRevdate, orderPlace, orderBudget, orderRequire, customer.getCustEmail(), serviceCode, companycode);
			
			//비즈니스 로직
			CustomerDaoImpl.getInstance().insertCustOrder(custorder);
			Service service = CompanyDaoImpl.getInstance().showService(serviceCode);
			
			request.setAttribute("custorder", custorder);
			request.setAttribute("service", service);
			
			return new ModelAndView("index.jsp");

		} 
		return null;
		
	}

}
