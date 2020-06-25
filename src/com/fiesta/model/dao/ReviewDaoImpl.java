package com.fiesta.model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.sql.DataSource;

import com.fiesta.model.vo.Review;
import com.fiesta.util.ServerInfo;

public class ReviewDaoImpl {
	private DataSource ds;
	
	private static ReviewDaoImpl dao = new ReviewDaoImpl();
	private ReviewDaoImpl() {
		//ds = DataSourceManager.getInstance().getConnection();
		
		//단위테스트
		try {
			Class.forName(ServerInfo.DRIVER_NAME);
		} catch (ClassNotFoundException e) {
			System.out.println("CustomerDaoImpl :: " + e);
		}
	}
	public static ReviewDaoImpl getInstance() {
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
	public void insertReview(Review review) throws SQLException {
		Connection conn = null;
		PreparedStatement ps = null;
		
		try {
			
		}finally {
			closeAll(ps, conn);
		}
	}

	public ArrayList<Review> showAllReview(String id) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}
	
	//단위테스트
	public static void main(String[] args) throws SQLException {
		ReviewDaoImpl dao = ReviewDaoImpl.getInstance();
		
	}
}
