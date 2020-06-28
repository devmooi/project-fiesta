package com.fiesta.parsing;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ReadCsv {

	public static void main(String[] args) {
		BufferedReader br = null;
		
		try {
			br = new BufferedReader(new InputStreamReader(new FileInputStream("WebContent/resource/data/예금보험공사_부보금융회사_목록_20200331.csv"),"utf-8"));
			String line = br.readLine();

			while((line = br.readLine()) != null) {
				List<String> tmpList = new ArrayList<>();
				String array[] = line.split(",");
				
				tmpList = Arrays.asList(array);
				
				String comName = tmpList.get(1);
				String comAddr = tmpList.get(2);
				String comDesc = tmpList.get(0);
				String comTel = tmpList.get(3);
				
				System.out.println("업체명 : " + comName);
				System.out.println("주소 : " + comAddr);
				System.out.println("설명 : " + comDesc);
				System.out.println("전화번호 : " + comTel);
				System.out.println("===================================");
				
			}
		} catch(Exception e) {
			System.out.println(e);
		} finally {
			try {
				if(br!=null) br.close();
			} catch (IOException e) {
				System.out.println(e);
			}
		}
		//System.out.println(ret.get(1).get(1).replace("\"", ""));
		//System.out.println(ret.get(1).get(2));
	}

}
