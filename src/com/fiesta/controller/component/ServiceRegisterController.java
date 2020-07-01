package com.fiesta.controller.component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fiesta.controller.Controller;
import com.fiesta.controller.ModelAndView;
import com.fiesta.model.dao.CompanyDaoImpl;
import com.fiesta.model.vo.Service;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

public class ServiceRegisterController implements Controller {

	@Override
	public ModelAndView handle(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//int comCode = 1; //세션에서 회사코드 받아올것

		// String savePath = "D:/Projects/workspace/projectName/WebContent/file_upload";
		String savePath = request.getSession().getServletContext().getRealPath("/resource/file_upload");
		System.out.println(savePath);
		
		// 파일 크기 15MB로 제한
		int sizeLimit = 1024*1024*15;
		
		MultipartRequest multi = new MultipartRequest(request, savePath, sizeLimit, "utf-8", new DefaultFileRenamePolicy());
		
		int companycode = Integer.parseInt(multi.getParameter("companycode"));
		String serviceName = multi.getParameter("serviceName");
		String serviceDesc = multi.getParameter("serviceDesc");
		String serviceImg = multi.getFilesystemName("serviceImg");	//파일이미지
		String serviceTag = multi.getParameter("serviceTag");
		
		String m_fileFullPath = savePath + "/" + serviceImg;
		
		
		/*int companycode = Integer.parseInt(request.getParameter("companycode"));
		String serviceName = request.getParameter("serviceName");
		String serviceDesc = request.getParameter("serviceDesc");
		String serviceImg = request.getParameter("serviceImg");
		String serviceTag = request.getParameter("serviceTag");
		
		String path = "";*/

		Service service  = new Service(serviceName, serviceDesc, "resource/file_upload/" + serviceImg, serviceTag, companycode);

		CompanyDaoImpl.getInstance().insertService(service);

		response.sendRedirect("ServiceAllShow.do?companycode="+companycode);

		//return new ModelAndView("ServiceAllShow.do?companycode="+companycode);
		return null;
	}

}