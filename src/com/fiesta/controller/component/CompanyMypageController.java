package com.fiesta.controller.component;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fiesta.controller.Controller;
import com.fiesta.controller.ModelAndView;
import com.fiesta.model.dao.CompanyDaoImpl;
import com.fiesta.model.dao.CustomerDaoImpl;
import com.fiesta.model.vo.Custorder;
import com.fiesta.model.vo.Question;
import com.fiesta.model.vo.Service;

public class CompanyMypageController implements Controller {

	@Override
	public ModelAndView handle(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String comEmail = "play@gmail.com";
		
		int companycode = CompanyDaoImpl.getInstance().lookupCompany(comEmail).getComCode();
		
		//회사가 받은 고객리스트
		ArrayList<Custorder> custOrderList = CustomerDaoImpl.getInstance().showAllCustOrderByCompany(companycode);
		ArrayList<Custorder> custOrderDetailList = new ArrayList<>();
		
		ArrayList<Service> custOrderService = new ArrayList<>();
		
		//고객리스트 자세히 보기
		for(Custorder c : custOrderList) {
			custOrderDetailList.add(CustomerDaoImpl.getInstance().showCustOrder(c.getOrderCode()));
			custOrderService.add(CompanyDaoImpl.getInstance().showService(c.getServiceCode()));
		}
			
		//바인딩
		request.setAttribute("custOrderList", custOrderList);
		request.setAttribute("custOrderDetailList", custOrderDetailList);
		
		request.setAttribute("custOrderService", custOrderService);
		
		
		String path = "companyMypage.jsp";

		return new ModelAndView(path);
		
		
	}

}
