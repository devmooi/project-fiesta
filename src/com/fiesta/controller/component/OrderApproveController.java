package com.fiesta.controller.component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fiesta.controller.Controller;
import com.fiesta.controller.ModelAndView;
import com.fiesta.model.dao.CompanyDaoImpl;
import com.fiesta.model.dao.CustomerDaoImpl;
import com.fiesta.model.dao.QuestionDaoImpl;
import com.fiesta.model.vo.Custorderdetail;

public class OrderApproveController implements Controller {

	@Override
	public ModelAndView handle(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String comEmail = "play@gmail.com";		//세션에서 받아오기
		int companycode = CompanyDaoImpl.getInstance().lookupCompany(comEmail).getComCode();

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
		
		response.sendRedirect("companyMypage.do?");

		//return new ModelAndView(path);
		return null;
	}

}
