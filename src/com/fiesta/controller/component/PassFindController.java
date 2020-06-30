package com.fiesta.controller.component;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fiesta.controller.Controller;
import com.fiesta.controller.ModelAndView;
import com.fiesta.model.dao.RegisterDaoImpl;
import com.fiesta.util.AES256Util;
import com.fiesta.util.Mail;
import com.fiesta.util.RandomPassword;

public class PassFindController implements Controller {

	@Override
	public ModelAndView handle(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String pick = request.getParameter("pick");
		String email = request.getParameter("email");
		
		boolean flag = false;
		PrintWriter out = response.getWriter();
		
		//임시 비밀번호 메일 전송
		String randomPass = RandomPassword.randomPassword(8);
		Mail.sendMail(email, randomPass);
		
		//암호화
		String key = "aes256-password-key"; 
		AES256Util aes256 = new AES256Util(key);
		randomPass = aes256.encrypt(randomPass);
		System.out.println("Encode PWD : " + randomPass);
		
		//DB 저장
		RegisterDaoImpl.getInstance().updatePassword(email, randomPass, pick);
		
		flag = true;
		out.print(flag);
		
		return null;
	}

}
