package com.fiesta.controller.component;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.fiesta.controller.Controller;
import com.fiesta.controller.ModelAndView;

public class XMLParsingTestController implements Controller {

	@Override
	public ModelAndView handle(HttpServletRequest request, HttpServletResponse response) throws Exception {
		return null;
	}

	//단위테스트
	public static void main(String[] args) throws SQLException, ParserConfigurationException, SAXException, IOException {
		String url = "http://localhost:8888/fiesta_data.xml";

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

				String rowNum = getTagValue("rowNum", eElement);
				String opnSvcNm = getTagValue("opnSvcNm", eElement);
				String opnSvcId = getTagValue("opnSvcId", eElement);
				String opnSfTeamCode = getTagValue("opnSfTeamCode", eElement);
				String mgtNo = getTagValue("mgtNo", eElement);
				String apvPermYmd = getTagValue("apvPermYmd", eElement);
				String trdCode = getTagValue("trdCode", eElement); //전부 다 01
				String trdCodeNm = getTagValue("trdCodeNm", eElement); //전부 다 영업/정상
				String trdStateGbn = getTagValue("trdStateGbn", eElement); //전부 다 13
				String trdStateNm = getTagValue("trdStateNm", eElement); //전부 다 영업중
				String siteTel = getTagValue("siteTel", eElement);
				String siteWhlAddr = getTagValue("siteWhlAddr", eElement);
				String rdnWhlAddr = getTagValue("rdnWhlAddr", eElement);
				String rdnPostNo = getTagValue("rdnPostNo", eElement);
				String bplcNm = getTagValue("bplcNm", eElement);
				String lastModTs = getTagValue("lastModTs", eElement);
				String updateGbn = getTagValue("updateGbn", eElement);
				String updateDt = getTagValue("updateDt", eElement);
				String x = getTagValue("x", eElement);
				String y = getTagValue("y", eElement);
				String culPhyedCobNm = getTagValue("culPhyedCobNm", eElement); //전부 다 대중문화예술기획업
				String culWrkrSeNm = getTagValue("culWrkrSeNm", eElement); //전부 다 유통관련업
				String mnfacTreArtclCn = getTagValue("mnfacTreArtclCn", eElement);
				String facilAr = getTagValue("facilAr", eElement);

				System.out.println("번호 :: " + rowNum);
				System.out.println("개방서비스명 :: " + opnSvcNm);
				System.out.println("개방서비스ID :: " + opnSvcId);
				System.out.println("개방자치단체코드 :: " + opnSfTeamCode);
				System.out.println("관리번호 :: " + mgtNo);
				System.out.println("인허가일자 :: " + apvPermYmd);
				System.out.println("영업상태구분코드 :: " + trdCode);
				System.out.println("영업상태명 :: " + trdCodeNm);
				System.out.println("상세영업상태코드 :: " + trdStateGbn);
				System.out.println("상세영업상태명 :: " + trdStateNm);
				System.out.println("소재지전화 :: " + siteTel);
				System.out.println("소재지전체주소 :: " + siteWhlAddr);
				System.out.println("도로명전체주소 :: " + rdnWhlAddr);
				System.out.println("도로명우편번호 :: " + rdnPostNo);
				System.out.println("사업장명 :: " + bplcNm);
				System.out.println("최종수정시점 :: " + lastModTs);
				System.out.println("데이터갱신구분 :: " + updateGbn);
				System.out.println("데이터갱신일자 :: " + updateDt);
				System.out.println("좌표정보(X) :: " + x);
				System.out.println("좌표정보(Y) :: " + y);
				System.out.println("문화체육업종명 :: " + culPhyedCobNm);
				System.out.println("문화사업자구분명 :: " + culWrkrSeNm);
				System.out.println("제작취급품목내용 :: " + mnfacTreArtclCn);
				System.out.println("시설면적 :: " + facilAr);
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