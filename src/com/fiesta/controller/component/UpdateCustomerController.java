package com.fiesta.controller.component;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fiesta.controller.Controller;
import com.fiesta.controller.ModelAndView;
import com.fiesta.model.dao.RegisterDaoImpl;
import com.fiesta.model.vo.Customer;

public class UpdateCustomerController implements Controller {

	@Override
	public ModelAndView handle(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		String pass = request.getParameter("pass");
		String tel = request.getParameter("tel");
		String group = request.getParameter("group"); // 이것들이 이제, 변경할 값인거지. 변경된 사항이 있다면.
		
		Customer customer = new Customer(email, name, pass, tel, group);
		try {
			RegisterDaoImpl.getInstance().updateCustomer(customer);
			// 여기서.. 각각 수정의 요청이 들어오는 걸 처리하는 로직 못하나.
		} catch (SQLException e) {
			
		}
		return null; // return할 것은 없고. 이렇게 돌리기만하면 끝 아닌가? 음........
	}

}
