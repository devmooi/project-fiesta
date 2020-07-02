package com.fiesta.controller.component;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fiesta.controller.Controller;
import com.fiesta.controller.ModelAndView;
import com.fiesta.model.dao.CompanyDaoImpl;
import com.fiesta.model.dao.CustomerDaoImpl;
import com.fiesta.model.vo.Company;
import com.fiesta.model.vo.Custorder;
import com.fiesta.model.vo.Custorderdetail;
import com.fiesta.model.vo.Custrequest;
import com.fiesta.model.vo.Custrequestdetail;
import com.fiesta.model.vo.Question;
import com.fiesta.model.vo.Service;

public class CompanyMypageController implements Controller {

	@Override
	public ModelAndView handle(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		Company company = (Company) session.getAttribute("company");
		String comEmail = company.getComEmail();
		
		int companycode = CompanyDaoImpl.getInstance().lookupCompany(comEmail).getComCode();
		
		//회사가 받은 고객리스트
		ArrayList<Custorder> custOrderList = CustomerDaoImpl.getInstance().showAllCustOrderByCompany(companycode); //얘는 간단히 보는거
		ArrayList<Custorder> custOrderDetailList = new ArrayList<>();  //얘는 자세히 보는거
		
		//주문한 서비스
		ArrayList<Service> custOrderService = new ArrayList<>();
				
		//고객리스트 자세히 보기
		for(Custorder c : custOrderList) {
			custOrderDetailList.add(CustomerDaoImpl.getInstance().showCustOrder(c.getOrderCode()));
			custOrderService.add(CompanyDaoImpl.getInstance().showService(c.getServiceCode()));
		}
		
		//최종주문승인된 서비스
		ArrayList<Custorderdetail> custOrderFinalDetail = CustomerDaoImpl.getInstance().showAllCustOrderDetailByCompany(companycode);
			
		//바인딩
		request.setAttribute("custOrderList", custOrderList);
		request.setAttribute("custOrderDetailList", custOrderDetailList);
		//주문한 서비스 바인딩
		request.setAttribute("custOrderService", custOrderService);
		
		//최종주문 승인된 것들 바인딩
		request.setAttribute("custOrderFinalDetail", custOrderFinalDetail);
		
		////////////////////////////////////////////////////////////////////////////
		//의뢰내역
		ArrayList<Custrequest> custRequestList = CustomerDaoImpl.getInstance().showAllCustRequestByCompany(Integer.toString(company.getComCategoryCode()));
		ArrayList<Custrequest> custRequestDetailList = new ArrayList<>();
		
		for(Custrequest c : custRequestList) {
			custRequestDetailList.add(CustomerDaoImpl.getInstance().showCustRequest(c.getRequestCode()));
		}
		
		ArrayList<Custrequestdetail> custRequestFinalDetail = CustomerDaoImpl.getInstance().showAllCustRequestDetailByCompany(companycode);
		
		request.setAttribute("custRequestList", custRequestList);
		request.setAttribute("custRequestDetailList", custRequestDetailList);
		request.setAttribute("custRequestFinalDetail", custRequestFinalDetail);
		
		
		String path = "register/companyMypage.jsp";

		return new ModelAndView(path);
		
		
	}

}
