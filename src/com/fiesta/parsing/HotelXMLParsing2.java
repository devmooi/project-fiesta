package com.fiesta.parsing;

import java.io.IOException;
import java.sql.SQLException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.fiesta.model.dao.RegisterDaoImpl;
import com.fiesta.model.vo.Company;

public class HotelXMLParsing2 {

	public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException, SQLException {
		String url = "WebContent/resource/data/전국렌터카업체정보표준데이터.xml";

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
				
				if(comDesc != null) {
					Company company = new Company();
					company.setComTel(comTel);
					company.setComAddr(comAddr);
					company.setComDesc("총보유대수 : " + comDesc);
					company.setComName(comName);
					company.setComCategoryCode(4);
					if(temp%3==0) company.setComImg("resource/img/bus1.jpg");
					else if(temp%3==1) company.setComImg("resource/img/bus2.jpg");
					else if(temp%3==2) company.setComImg("resource/img/bus3.jpg");
					RegisterDaoImpl.getInstance().registerCompany(company);
					
					System.out.println("전화번호 : " + comTel);
					System.out.println("차고지도로명주소 : " + comAddr);
					System.out.println("자동차총보유대수 : " + comDesc);
					System.out.println("업체명 : " + comName);
					System.out.println(++count);
					System.out.println("=====================================");
				}
			}
		}
	}
	
	private static String getTagValue(String tag, Element eElement) {
		NodeList nList = eElement.getElementsByTagName(tag).item(0).getChildNodes();

		Node nValue = (Node)nList.item(0);
		if(nValue==null) return null;
		return nValue.getNodeValue();
	}

}
