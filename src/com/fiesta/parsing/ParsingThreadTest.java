package com.fiesta.parsing;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.fiesta.model.dao.RegisterDaoImpl;
import com.fiesta.model.vo.Company;

public class ParsingThreadTest {

	public static void main(String[] args) {
		EntertainmentXMLParsing entertainment = new EntertainmentXMLParsing();
		HotelXMLParsing hotel = new HotelXMLParsing();
		BusXMLParsing bus = new BusXMLParsing();
		
		entertainment.start();
		hotel.start();
		bus.start();
	}

}

class EntertainmentXMLParsing extends Thread {
	
	public void run() {
		String url = "WebContent/resource/data/03_08_02_P_대중문화예술기획업_1592853097711.xml";

		try {
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(url);
			
			//ROOT TAG
			doc.getDocumentElement().normalize();
			System.out.println("Root element: " + doc.getDocumentElement().getNodeName());

			//Parsing TAG
			NodeList nList = doc.getElementsByTagName("row");
			System.out.println("파싱할 리스트 수 : " + nList.getLength());

			int count = 0;
			for(int temp=0; temp<nList.getLength();temp++) {
				Node nNode = nList.item(temp);
				if(nNode.getNodeType() == Node.ELEMENT_NODE) {
					Element eElement = (Element)nNode;

					String siteTel = getTagValue("siteTel", eElement);
					String rdnWhlAddr = getTagValue("rdnWhlAddr", eElement);
					String bplcNm = getTagValue("bplcNm", eElement);
					String mnfacTreArtclCn = getTagValue("mnfacTreArtclCn", eElement);
					
					if(siteTel!=null && rdnWhlAddr!=null && bplcNm!=null && mnfacTreArtclCn!=null) {
						System.out.println("실제 들어간 값 : " + ++count);
						Company company = new Company();
						company.setComTel(siteTel);
						company.setComAddr(rdnWhlAddr);
						company.setComCategoryCode(1);
						company.setComDesc(mnfacTreArtclCn);
						if(count%3==0) company.setComImg("resource/img/entertainment1.jpg");
						else if(count%3==1) company.setComImg("resource/img/entertainment2.jpg");
						else if(count%3==2) company.setComImg("resource/img/entertainment3.jpg");
						company.setComName(bplcNm);
						
						RegisterDaoImpl.getInstance().registerCompany(company);

						System.out.println("소재지전화 :: " + siteTel);
						System.out.println("도로명전체주소 :: " + rdnWhlAddr);
						System.out.println("사업장명 :: " + bplcNm);
						System.out.println("제작취급품목내용 :: " + mnfacTreArtclCn);
						System.out.println("==========================================");
					}
				}
			}
			
		} catch (Exception e) {
			System.out.println(e);
		} 
	}
	
	private static String getTagValue(String tag, Element eElement) {
		NodeList nList = eElement.getElementsByTagName(tag).item(0).getChildNodes();

		Node nValue = (Node)nList.item(0);
		if(nValue==null) return null;
		return nValue.getNodeValue();
	}
}

class HotelXMLParsing extends Thread {
	
	public void run() {
		String url = "WebContent/resource/data/전국민박펜션업소표준데이터.xml";

		try {
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(url);
			
			//ROOT TAG
			doc.getDocumentElement().normalize();
			System.out.println("Root element: " + doc.getDocumentElement().getNodeName());

			//Parsing TAG
			NodeList nList = doc.getElementsByTagName("record");
			System.out.println("파싱할 리스트 수 : " + nList.getLength());

			int count = 0;
			for(int temp=0; temp<nList.getLength();temp++) {
				Node nNode = nList.item(temp);
				if(nNode.getNodeType() == Node.ELEMENT_NODE) {
					Element eElement = (Element)nNode;

					String comTel = getTagValue("전화번호", eElement);
					String comAddr = getTagValue("소재지도로명주소", eElement);
					String comDesc = getTagValue("주변관광정보", eElement);
					String comName = getTagValue("업소명", eElement);
					
					if(comTel!=null && comAddr!=null && comDesc!=null && comName!=null) {
						System.out.println("실제 들어간 값 : " + ++count);
						Company company = new Company();
						company.setComTel(comTel);
						company.setComAddr(comAddr);
						company.setComDesc(comDesc);
						company.setComName(comName);
						company.setComCategoryCode(2);
						if(count%3==0) company.setComImg("resource/img/hotel1.jpg");
						else if(count%3==1) company.setComImg("resource/img/hotel2.jpg");
						else if(count%3==2) company.setComImg("resource/img/hotel3.jpg");
						
						RegisterDaoImpl.getInstance().registerCompany(company);

						System.out.println("전화번호 : " + comTel);
						System.out.println("소재지도로명주소 : " + comAddr);
						System.out.println("주변관광정보 : " + comDesc);
						System.out.println("업소명 : " + comName);
						System.out.println("=====================================");
					}
				}
			}
			
		} catch (Exception e) {
			System.out.println(e);
		} 
	}
	
	private static String getTagValue(String tag, Element eElement) {
		NodeList nList = eElement.getElementsByTagName(tag).item(0).getChildNodes();

		Node nValue = (Node)nList.item(0);
		if(nValue==null) return null;
		return nValue.getNodeValue();
	}
}

class BusXMLParsing extends Thread {
	
	public void run() {
		String url = "WebContent/resource/data/전국렌터카업체정보표준데이터.xml";

		try {
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(url);
			
			//ROOT TAG
			doc.getDocumentElement().normalize();
			System.out.println("Root element: " + doc.getDocumentElement().getNodeName());

			//Parsing TAG
			NodeList nList = doc.getElementsByTagName("record");
			System.out.println("파싱할 리스트 수 : " + nList.getLength());

			int count = 0;
			for(int temp=0; temp<nList.getLength();temp++) {
				Node nNode = nList.item(temp);
				if(nNode.getNodeType() == Node.ELEMENT_NODE) {
					Element eElement = (Element)nNode;

					String comTel = getTagValue("전화번호", eElement);
					String comAddr = getTagValue("차고지도로명주소", eElement);
					String comDesc = getTagValue("자동차총보유대수", eElement);
					String comName = getTagValue("업체명", eElement);
					
					if(comTel!=null && comAddr!=null && comDesc!="0" && comName!=null) {
						System.out.println("실제 들어간 값 : " + ++count);
						Company company = new Company();
						company.setComTel(comTel);
						company.setComAddr(comAddr);
						company.setComDesc("총보유대수 : " + comDesc);
						company.setComName(comName);
						company.setComCategoryCode(4);
						if(count%3==0) company.setComImg("resource/img/bus1.jpg");
						else if(count%3==1) company.setComImg("resource/img/bus2.jpg");
						else if(count%3==2) company.setComImg("resource/img/bus3.jpg");
						
						RegisterDaoImpl.getInstance().registerCompany(company);

						System.out.println("전화번호 : " + comTel);
						System.out.println("차고지도로명주소 : " + comAddr);
						System.out.println("자동차총보유대수 : " + comDesc);
						System.out.println("업체명 : " + comName);
						System.out.println("=====================================");
					}
				}
			}
			
		} catch (Exception e) {
			System.out.println(e);
		} 
	}
	
	private static String getTagValue(String tag, Element eElement) {
		NodeList nList = eElement.getElementsByTagName(tag).item(0).getChildNodes();

		Node nValue = (Node)nList.item(0);
		if(nValue==null) return null;
		return nValue.getNodeValue();
	}
}