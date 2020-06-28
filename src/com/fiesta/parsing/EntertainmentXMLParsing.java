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

public class EntertainmentXMLParsing {

	public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException, SQLException {
		String url = "WebContent/resource/data/03_08_02_P_대중문화예술기획업_1592853097711.xml";

		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
		Document doc = dBuilder.parse(url);

		//ROOT TAG
		doc.getDocumentElement().normalize();
		System.out.println("Root element: " + doc.getDocumentElement().getNodeName());

		//Parsing TAG
		NodeList nList = doc.getElementsByTagName("row");
		System.out.println("파싱할 리스트 수 : " + nList.getLength());

		for(int temp=0; temp<nList.getLength();temp++) {
			Node nNode = nList.item(temp);
			if(nNode.getNodeType() == Node.ELEMENT_NODE) {
				Element eElement = (Element)nNode;

				String siteTel = getTagValue("siteTel", eElement);
				String rdnWhlAddr = getTagValue("rdnWhlAddr", eElement);
				String bplcNm = getTagValue("bplcNm", eElement);
				String mnfacTreArtclCn = getTagValue("mnfacTreArtclCn", eElement);
				
				Company company = new Company();
				company.setComTel(siteTel);
				company.setComAddr(rdnWhlAddr);
				company.setComCategoryCode(1);
				company.setComDesc(mnfacTreArtclCn);
				if(temp%3==0) company.setComImg("resource/img/entertainment1.jpg");
				else if(temp%3==1) company.setComImg("resource/img/entertainment2.jpg");
				else if(temp%3==2) company.setComImg("resource/img/entertainment3.jpg");
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
	
	private static String getTagValue(String tag, Element eElement) {
		NodeList nList = eElement.getElementsByTagName(tag).item(0).getChildNodes();

		Node nValue = (Node)nList.item(0);
		if(nValue==null) return null;
		return nValue.getNodeValue();
	}

}
