package com.fiesta.controller.component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fiesta.controller.Controller;
import com.fiesta.controller.ModelAndView;
import com.fiesta.model.dao.CompanyDaoImpl;
import com.fiesta.model.dao.CustomerDaoImpl;
import com.fiesta.model.dao.QuestionDaoImpl;
import com.fiesta.model.vo.Company;
import com.fiesta.model.vo.Custorderdetail;

public class OrderApproveController implements Controller {

	@Override
	public ModelAndView handle(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		HttpSession session = request.getSession();
		Company company = (Company) session.getAttribute("company");
		String comEmail = company.getComEmail();		//세션에서 받아오기
		int companycode = company.getComCode();

		int orderCode = Integer.parseInt(request.getParameter("orderCode"));

		int serviceCode = Integer.parseInt(request.getParameter("serviceCode"));
		//String serviceName = request.getParameter("serviceName");
		String custEmail = request.getParameter("custEmail");

		int totalPrice = Integer.parseInt(request.getParameter("totalPrice"));
		String finalDesc = request.getParameter("finalDesc");

		
		String path = "";
		
		Custorderdetail custorderdetail = new Custorderdetail(totalPrice,finalDesc,orderCode,serviceCode,companycode,custEmail);
		
		CustomerDaoImpl.getInstance().insertCustOrderDetail(custorderdetail);
		
		//주문상태바꾸기
		CustomerDaoImpl.getInstance().changeOrderCondition(orderCode, "주문승인완료");
		
		//이메일보내기
		
		//response.sendRedirect("index.jsp");

		//return new ModelAndView(path);
		return new ModelAndView("index.jsp");
	}

}
