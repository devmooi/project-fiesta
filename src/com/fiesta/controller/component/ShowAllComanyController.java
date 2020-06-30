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
		
		// 추천 업체 출력
		matrix = ReviewDaoImpl.getInstance().getReviewMatrix();
		// 세션 완료되면 출력하기 session.getAttribute(Customer).getEmail()
		recoArr = ReviewDaoImpl.getInstance().getRecoCompany(matrix, "8");
		for(String code : recoArr) {
			String[] temp = code.split("-");
			System.out.println("temp[0]"+temp[0]);
			recoList.add(ReviewDaoImpl.getInstance().showReview(Integer.parseInt(temp[0])));
		}
		for(int[] temp : matrix) {
			System.out.println(Arrays.toString(temp));
		}
		System.out.println(Arrays.toString(recoArr));
		System.out.println(recoList);
		// 전체 업체 바인딩
		request.setAttribute("list", list);
		// 추천 업체 바인딩
		request.setAttribute("recoList", recoList);

		return new ModelAndView("./company/companylist.jsp");
	}

}