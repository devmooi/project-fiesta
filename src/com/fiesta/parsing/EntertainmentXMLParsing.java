package com.fiesta.parsing;

import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class EntertainmentXMLParsing {

	public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException {
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
				String siteWhlAddr = getTagValue("siteWhlAddr", eElement);
				String rdnWhlAddr = getTagValue("rdnWhlAddr", eElement);
				String rdnPostNo = getTagValue("rdnPostNo", eElement);
				String bplcNm = getTagValue("bplcNm", eElement);
				String culPhyedCobNm = getTagValue("culPhyedCobNm", eElement); //전부 다 대중문화예술기획업
				String culWrkrSeNm = getTagValue("culWrkrSeNm", eElement); //전부 다 유통관련업
				String mnfacTreArtclCn = getTagValue("mnfacTreArtclCn", eElement);

				System.out.println("소재지전화 :: " + siteTel);
				System.out.println("소재지전체주소 :: " + siteWhlAddr);
				System.out.println("도로명전체주소 :: " + rdnWhlAddr);
				System.out.println("도로명우편번호 :: " + rdnPostNo);
				System.out.println("사업장명 :: " + bplcNm);
				System.out.println("문화체육업종명 :: " + culPhyedCobNm);
				System.out.println("문화사업자구분명 :: " + culWrkrSeNm);
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
