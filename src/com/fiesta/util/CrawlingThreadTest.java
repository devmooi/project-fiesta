package com.fiesta.util;

import java.util.ArrayList;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.fiesta.model.dao.RegisterDaoImpl;
import com.fiesta.model.vo.Company;

public class CrawlingThreadTest {

	public static void main(String[] args) {
		ClothingCrawling clothing = new ClothingCrawling();
		
		clothing.start();
	}

}

class ClothingCrawling extends Thread {
	
	public void run() {
		String URL = "https://www.google.com/search?q=%EB%8C%80%ED%95%99%EA%B5%90+%EB%8B%A8%EC%B2%B4%EC%98%B7+%EC%97%85%EC%B2%B4+%EB%A6%AC%EC%8A%A4%ED%8A%B8&oq=%EB%8C%80%ED%95%99%EA%B5%90+%EB%8B%A8%EC%B2%B4%EC%98%B7+%EC%97%85%EC%B2%B4+%EB%A6%AC%EC%8A%A4%ED%8A%B8&aqs=chrome..69i64j69i57&sourceid=chrome&ie=UTF-8";
		try {
			Document doc = Jsoup.connect(URL).get();
			Elements elem = doc.select("div[id=\"rso\"]"); 
			ArrayList<String> comNameList = new ArrayList<>();
			ArrayList<String> comDescList = new ArrayList<>();
			
			for(Element e : elem.select("h3[class=\"LC20lb DKV0Md\"]")) {
				String comName = e.text()
						.replace("단체티 주문제작 전문 ", "")
						.replace("NO.1- 탑앤탑 대학교/과티", "")
						.replace("대학과잠바 | No.1 단체 과점퍼 주문제작 쇼핑몰 - ", "")
						.replace("티파나: No.1 단체티 주문 제작 쇼핑몰", "")
						.replace("단체티 주문제작 쇼핑몰 ", "")
						.replace("단체티 주문제작 ", "")
						.replace("-과잠,돕바,단체티 쇼핑몰", "")
						.replace("단체티ㅣ단체복 티셔츠ㅣ단체조끼ㅣ단체바람막이 주문 제작 ...", "")
						.replace("단체복은 ", "")
						.replace("단체티,단체복,단체조끼,단체아이템,친목티 쇼핑몰 - ", "")
						.replace("주문/제작안내 - 단체티 제작전문 ", "")
						.replace(" - 단체복, 단체맨투맨, 단체옷, 단체복 패딩, 과잠바", "");
				if(!comName.equals("")) {
					comNameList.add(comName);
				}
			}

			for(Element e : elem.select("span[class=\"st\"]")) {
				String comDesc = e.text()
						.replace("단체티 주문제작 전문으로 단체복, 과티, 교회티, 브랜드단체티, 단체맨투맨, 단체후드티, 단체조끼등 24시간 빠른 단체티 주문제작 탑앤탑.", "")
						.replace("단체티 주문제작 전문 탑앤탑입니다. ", "")
						.replace("대학과잠바, 과점퍼제작, 과바람막이, 행사바람막이, 과복, 대학교바람막이, 동호회바람막이, 과잠바, 단체복바람막이, 과바막 주문제작 할인까지 가능한 쇼핑몰 - 티파 ...", "")
						.replace("단체복ㅣ단체티ㅣ단체 티셔츠 주문 제작ㅣ단체조끼ㅣ단체바람막이ㅣ트레이닝복ㅣ작업복ㅣ회사복 주문 제작.", "");
				if(!comDesc.equals("")) {
					comDescList.add(comDesc);
				}
			}
			
			int count = 0;
			for(int i=0; i<comNameList.size(); i++) {
				String comName = comNameList.get(i);
				String comDesc = comDescList.get(i);

				System.out.println("실제 들어간 값 : " + ++count);
				
				Company company = new Company();
				company.setComDesc(comDesc);
				company.setComName(comName);
				company.setComCategoryCode(8);
				if(count%3==0) company.setComImg("resource/img/clothing1.jpg");
				else if(count%3==1) company.setComImg("resource/img/clothing2.jpg");
				else if(count%3==2) company.setComImg("resource/img/clothing3.jpg");
				
				RegisterDaoImpl.getInstance().registerCompany(company);
				
				System.out.println("업체명 : " + comName);
				System.out.println("설명 : " + comDesc);
				System.out.println("===================================");
			}
		} catch (Exception e) {
			System.out.println(e);
		}

	}
	
}