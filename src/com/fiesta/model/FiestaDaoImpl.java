package com.fiesta.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import javax.sql.DataSource;

import com.fiesta.model.vo.*;
import com.fiesta.util.ServerInfo;

public class FiestaDaoImpl {
	private DataSource ds;
	
	private static FiestaDaoImpl dao = new FiestaDaoImpl();
	private FiestaDaoImpl() {
		//ds = DataSourceManager.getInstance().getConnection();

		//단위테스트
		try {
			Class.forName(ServerInfo.DRIVER_NAME);
		} catch (ClassNotFoundException e) {
			System.out.println("ItemDao :: " + e);
		}
	}
	public static FiestaDaoImpl getInstance() {
		return dao;
	}
	
	public Connection getConnection() throws SQLException {
		//return ds.getConnection();

		//단위테스트
		return DriverManager.getConnection(ServerInfo.URL, ServerInfo.USER, ServerInfo.PASS);
	}
	public void closeAll(PreparedStatement ps, Connection conn) throws SQLException {
		if(ps!=null) ps.close();
		if(conn!=null) conn.close();
	}
	public void closeAll(ResultSet rs, PreparedStatement ps, Connection conn) throws SQLException {
		if(rs!=null) rs.close();
		closeAll(ps, conn);
	}
	
	
	//작업 영역
	public void insertWish(Wish wish) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	public ArrayList<Wish> showAllWish(String id) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	public void deleteWish(Wish wish) throws SQLException {
		// TODO Auto-generated method stub
		
	}
	
	//단위테스트
	public static void main(String[] args) throws SQLException {
		//하경 - 단위테스트
		FiestaDaoImpl dao=FiestaDaoImpl.getInstance();
		//dao.insertService(new Service("회사1","설명1","img","버스"));		
		
		//엄체코드를 자동으로 session에서 받고 돌려야할것...  단위테스트 업체코드에 default값 없어서 못함... ㅠㅅㅠ
		
		//dao.deleteService(3);
		//System.out.println(dao.showAllService(1));
		
		//dao.insertQuestion("숙박문의","몇명이서 잘 수 있나요?", "java");
		//dao.insertQuestion("공연문의","이거슨 문장 잘라지는지 테스트하기 위한 문의사항입니담 키키키키킼", "java");
		//dao.insertQuestion("버스문의","이거는 답변 없는 문의를 보는 테스트", "encore@gmail.com");
		//System.out.println(dao.showAllQuestion("java"));
		
		//System.out.println(dao.showQuestion(1));
		//System.out.println(dao.showAnswer(1));
		
		//제영 - 단위테스트
		//System.out.println(dao.showAllCompany());
		
		
		//의근 - 단위테스트
		// customer register
		// customer = new Customer("park", "박의근", "euigeun", "01088048331", "euigeun@gmail.com", "플레이데이터");
		// FiestaDaoImpl.getInstance().registerCustomer(customer);
		// FiestaDaoImpl.getInstance().registerCustomer(new Customer("lee", "이보람", "0107171293", "boram@gmail.com", "concat", "플레이데이터"));
		
		// customer login
		// FiestaDaoImpl.getInstance().loginCustomer("java", "euigeun");
		
		// customer update
		// Customer customer = new Customer("java", "이보람", "euigeun", "01088048331", "euigeun@gmail.com", "플레이데이터");
		// FiestaDaoImpl.getInstance().updateCustomer(customer);

		// customer delete
		// FiestaDaoImpl.getInstance().deleteCustomer("java", "euigeun");
		
		// customer lookup
		// FiestaDaoImpl.getInstance().lookupCustomer("java");
		
		// company register
		// FiestaDaoImpl.getInstance().registerCompany(new Company(24, "euigeun", "LGtop", "LG", "031498202", "남부", "-", "가전제품~~", 3));
		
		// company login
		// FiestaDaoImpl.getInstance().loginCompany("house1", "2222");
		
		// company update
		// FiestaDaoImpl.getInstance().updateCompany(new Company(22, "boram", "LGbottom", "LG", "031498202", "남부", "-", "가전제품~~", 3));
		
		// company delete
		// FiestaDaoImpl.getInstance().deleteCompany("LGbottom", "boram");
		
		// company lookup
		// FiestaDaoImpl.getInstance().lookupCompany("house1");
		
		// order insertcustorder 
		// FiestaDaoImpl.getInstance().insertCustorder(new Custorder(11, "200619", "200710", "남부", 1000000, "우천시취소", "진행중", "java"));
	
		// order showAllCustorder
		// FiestaDaoImpl.getInstance().showAllCustorder("java");
		
		// orderdetail insertorderdetail
		// FiestaDaoImpl.getInstance().insertOrderdetail(new Orderdetail(11, 1000000, "우천시취소", 1, 1, 11));
	}
}