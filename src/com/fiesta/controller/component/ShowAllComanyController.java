package com.fiesta.controller.component;

import java.util.ArrayList;
import java.util.Arrays;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fiesta.controller.Controller;
import com.fiesta.controller.ModelAndView;
import com.fiesta.model.FiestaDaoImpl;
import com.fiesta.model.dao.CompanyDaoImpl;
import com.fiesta.model.dao.ReviewDaoImpl;
import com.fiesta.model.vo.Company;
import com.fiesta.model.vo.Customer;
import com.fiesta.model.vo.Review;

public class ShowAllComanyController implements Controller{
	public ShowAllComanyController() {};
	@Override
	public ModelAndView handle(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		ArrayList<Review> list = new ArrayList<>();
		ArrayList<Review> recoList = new ArrayList<>();
		String[] recoArr = new String[2];
		int[][] matrix = {};
		
		// 전체 업체 출력
		list = CompanyDaoImpl.getInstance().showAllCompany();
		//System.out.println(1);
		
		// 추천 업체 출력
		Customer customer = (Customer) session.getAttribute("customer");
		String[] temp= {};
		if(customer!=null) {
			matrix = ReviewDaoImpl.getInstance().getReviewMatrix();
			recoArr = ReviewDaoImpl.getInstance().getRecoCompany(matrix, customer.getCustEmail());
			if(recoArr!=null) {
				for(int i=0;i<recoArr.length;i++) {
					if(recoArr[i]==null) continue;
					if(recoArr[i].equals("리뷰없음")) {
						recoList = null;
						break;
					} else {
						temp = recoArr[i].split("-");
						recoList.add(ReviewDaoImpl.getInstance().showReview(Integer.parseInt(temp[0])));
					}
				}
			}

			//System.out.println(Arrays.toString(recoArr));
			System.out.println("recoList : "+recoList);
		}
		
		//System.out.println(2);
		// 전체 업체 바인딩
		request.setAttribute("list", list);
		// 추천 업체 바인딩
		request.setAttribute("recoList", recoList);

		return new ModelAndView("./company/companylist.jsp");
	}

}