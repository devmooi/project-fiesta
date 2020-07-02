package com.fiesta.model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import javax.sql.DataSource;

import com.fiesta.model.vo.Answer;
import com.fiesta.model.vo.Company;
import com.fiesta.model.vo.Question;
import com.fiesta.model.vo.Wish;
import com.fiesta.util.ServerInfo;

public class WishDaoImpl implements WishDao {
	private DataSource ds;
	
	private static WishDaoImpl dao = new WishDaoImpl();
	private WishDaoImpl() {
		//ds = DataSourceManager.getInstance().getConnection();
		
		//단위테스트
		try {
			Class.forName(ServerInfo.DRIVER_NAME);
		} catch (ClassNotFoundException e) {
			System.out.println("QuestionDaoImpl :: " + e);
		}
	}
	public static WishDaoImpl getInstance() {
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
	//Wish
	public String insertWish(String custEmail, int comCode) throws SQLException {
		Connection conn = null;
		PreparedStatement ps = null;
		String result = "false";
		
		try{
			conn=  getConnection();
			String query = "INSERT INTO wish(cust_email, com_code) VALUES(?,?)";
			ps = conn.prepareStatement(query);
			System.out.println("PreparedStatement 생성됨...insertWish");
			
			ps.setString(1, custEmail);	//세션의 고객아이디 값 가져오기
			ps.setInt(2, comCode); //기업코드값 가져오기
			
			if(wishExist(comCode)==false) {
				System.out.println(ps.executeUpdate()+" row INSERT OK!!");
				result="true";
			}else {
				result="false";
			}
			
			return result;
			
		}finally{
			closeAll(ps, conn);
		}
		
	}
	
	public boolean wishExist(int comCode) throws SQLException {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = getConnection();
			String query = "SELECT com_code FROM wish WHERE com_code=?";
			ps = conn.prepareStatement(query);
			System.out.println("PreparedStatement 생성됨...wishExist");
			
			ps.setInt(1, comCode);
			rs = ps.executeQuery();
			return rs.next();
		} finally {
			closeAll(rs, ps, conn);
		}
	}

	public ArrayList<Wish> showAllWish(String custEmail) throws SQLException {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		ArrayList<Wish> list = new ArrayList<>();
		
		try {
			conn = getConnection();
			
			String query = "SELECT w.wish_code, c.com_name, c.com_desc FROM wish w, company c WHERE w.com_code = c.com_code and cust_email=?";
			ps = conn.prepareStatement(query);
			System.out.println("PreparedStatement....showAllWish..");
					
			ps.setString(1, custEmail);
			
			rs = ps.executeQuery();
			
			while(rs.next()) {
				//Company com = new Company();
				//com.setComName("c.com_name");
				//com.setComDesc("c.com_desc");
				list.add(new Wish(rs.getInt("w.wish_code"),
										custEmail,
									  rs.getString("c.com_name"),  
									  rs.getString("c.com_desc")));
			}
			System.out.println("show All list ok");
		}finally {
			closeAll(rs, ps, conn);
		} 
		return list;
	}

	public void deleteWish(int wishCode, String custEmail) throws SQLException {
		Connection conn = null;
		PreparedStatement ps = null;
		try{
			conn=  getConnection();
			String query = "DELETE FROM wish WHERE wish_code=? and cust_email=?";
			ps = conn.prepareStatement(query);
			System.out.println("PreparedStatement 생성됨...deleteWish");
			
			ps.setInt(1, wishCode);
			ps.setString(2, custEmail);
			
			System.out.println(ps.executeUpdate()+" row delete OK!!");
		}finally{
			closeAll(ps, conn);
		}
		
	}
	
	//단위테스트
	public static void main(String[] args) throws SQLException {
		WishDaoImpl dao = WishDaoImpl.getInstance();
		
		//dao.insertWish("encore@gmail.com", 5);
		//dao.deleteWish(4);
		System.out.println(dao.showAllWish("encore@gmail.com"));
		

	}
}
