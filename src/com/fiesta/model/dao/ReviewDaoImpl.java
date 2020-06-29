package com.fiesta.model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.sql.DataSource;

import com.fiesta.model.vo.Company;
import com.fiesta.model.vo.Customer;
import com.fiesta.model.vo.Review;
import com.fiesta.model.vo.Service;
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
	
	public boolean isReview(int comCode, int serviceCode) throws SQLException{
		boolean reviewflag = false;
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			conn = getConnection();
			StringBuffer query = new StringBuffer();
			query.append("SELECT review_code ");
			query.append("FROM review ");
			query.append("WHERE com_code = ? ");
			query.append("AND service_code = ? ");
			ps=conn.prepareStatement(query.toString());
			ps.setInt(1, comCode);
			ps.setInt(2, serviceCode);
			rs=ps.executeQuery();
			reviewflag=rs.next();
		}finally {
			closeAll(rs, ps, conn);
		}
		return reviewflag;
	}
	public boolean isAnswer(String reviewCode) throws SQLException{
		boolean reviewflag = false;
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			conn = getConnection();
			StringBuffer query = new StringBuffer();
			query.append("SELECT review_code ");
			query.append("FROM review ");
			query.append("WHERE review_code Like ? ");
			ps=conn.prepareStatement(query.toString());
			ps.setString(1, reviewCode);
			rs=ps.executeQuery();
			reviewflag=rs.next();
		}finally {
			closeAll(rs, ps, conn);
		}
		return reviewflag;
	}
	//작업 영역
	public void insertReview(Review review) throws SQLException {
		Connection conn = null;
		PreparedStatement ps = null;
		System.out.println(review);
		
		try {
			conn=getConnection();
			StringBuffer query = new StringBuffer();
			query.append("INSERT INTO review(");
			query.append("review_code, review_score, review_img, review_desc, review_date, ");
			query.append("cust_email, service_code, com_code) ");
			query.append("VALUES(?,?,?,?,sysdate(),?,?,?) ");
			ps=conn.prepareStatement(query.toString());
			ps.setString(1, review.getReviewCode());
			ps.setInt(2, review.getReviewScore());
			ps.setString(3, review.getReviewImg());
			ps.setString(4, review.getReviewDesc());
			ps.setString(5, review.getCustomer().getCustEmail());
			ps.setInt(6, review.getService().getServiceCode());
			ps.setInt(7, review.getCompany().getComCode());
			System.out.println("리뷰 "+ps.executeUpdate()+"건 추가");
		}finally {
			closeAll(ps, conn);
		}
	}

	public ArrayList<Review> showAllReview(int comCode) throws SQLException {
		ArrayList<Review> list = new ArrayList<Review>();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			conn=getConnection();
			StringBuffer query = new StringBuffer();
			query.append("SELECT r.review_code, r.review_score, r.review_img, r.review_img, r.review_desc, ");
			query.append("r.review_date, r.cust_email, r.service_code, r.com_code, cust.cust_name ");
			query.append("FROM review r, customer cust ");
			query.append("WHERE r.cust_email=cust.cust_email ");
			query.append("AND r.com_code = ? ");
			ps=conn.prepareStatement(query.toString());
			ps.setInt(1,comCode);
			//System.out.println(query);
			rs=ps.executeQuery();
			
			while(rs.next()) {
				Customer cust = new Customer();
				cust.setCustEmail(rs.getString("r.cust_email"));
				cust.setCustName(rs.getString("cust.cust_name"));
				String reviewCode = rs.getString("r.review_code");
				if(reviewCode.length()==5) {
					list.add(new Review(rs.getString("r.review_code"),
							rs.getInt("r.review_score"),
							rs.getString("r.review_img"),
							rs.getString("r.review_desc"),
							rs.getString("r.review_date"),
							cust,
							new Service(rs.getInt("r.service_code")),
							new Company(rs.getInt("r.com_code")),
							0,
							0));
				}
			}
		}finally {
			closeAll(rs, ps, conn);
		}
		
		return list;
	}
	
	public Review showReview(String reviewCode) throws SQLException{
		Review review = new Review();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			conn=getConnection();
			StringBuffer query = new StringBuffer();
			query.append("SELECT r.review_code, r.review_desc, avg(review_score) avg, count(review_code) count, ");
			query.append("cust.cust_email email, cust.cust_name name, r.com_code comcode, r.service_code servicecode ");
			query.append("FROM review r, customer cust ");
			query.append("WHERE r.review_code LIKE ? ");
			query.append("AND r.cust_email = cust.cust_email ");
			query.append("GROUP BY review_code ");
			ps=conn.prepareStatement(query.toString());
			ps.setString(1, reviewCode);
			rs=ps.executeQuery();
			while(rs.next()) {
				review.setReviewCode(rs.getString("r.review_code"));
				review.setReviewDesc(rs.getString("r.review_desc"));
				review.setAvgReviewScore(rs.getFloat("avg"));
				review.setCountDesc(rs.getInt("count"));
				Customer cust = new Customer();
				cust.setCustEmail(rs.getString("email"));
				cust.setCustName(rs.getString("name"));
				review.setCustomer(cust);
				Company company = new Company();
				company.setComCode(rs.getInt("comcode"));
				review.setCompany(company);
				Service service  = new Service();
				service.setServiceCode(rs.getInt("servicecode"));
				review.setService(service);
			}
		}finally {
			closeAll(rs, ps, conn);
		}
		
		return review;
	}
	
	public Review showReview(int comCode) throws SQLException{
		Review review = new Review();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			conn=getConnection();
			StringBuffer query = new StringBuffer();
			query.append("SELECT r.review_code, r.review_desc, avg(review_score) avg, count(review_code) count, ");
			query.append("cust.cust_email email, cust.cust_name name, r.com_code comcode, r.service_code servicecode ");
			query.append("FROM review r, customer cust ");
			query.append("WHERE r.com_code = ? ");
			query.append("AND r.cust_email = cust.cust_email ");
			query.append("GROUP BY review_code ");
			ps=conn.prepareStatement(query.toString());
			ps.setInt(1, comCode);
			rs=ps.executeQuery();
			while(rs.next()) {
				review.setReviewCode(rs.getString("r.review_code"));
				review.setReviewDesc(rs.getString("r.review_desc"));
				review.setAvgReviewScore(rs.getFloat("avg"));
				review.setCountDesc(rs.getInt("count"));
				Customer cust = new Customer();
				cust.setCustEmail(rs.getString("email"));
				cust.setCustName(rs.getString("name"));
				review.setCustomer(cust);
				Company company = new Company();
				company.setComCode(rs.getInt("comcode"));
				review.setCompany(company);
				Service service  = new Service();
				service.setServiceCode(rs.getInt("servicecode"));
				review.setService(service);
			}
		}finally {
			closeAll(rs, ps, conn);
		}
		
		return review;
	}
	
	public ArrayList<Service> showService(int companycode) throws SQLException{
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		ArrayList<Service> list = new ArrayList<Service>();
		
		try {
			conn=getConnection();
			StringBuffer query = new StringBuffer();
			query.append("SELECT c.com_code, c.com_name, s.service_code, s.service_name ");
			query.append("FROM company c, service s ");
			query.append("WHERE c.com_code = s.com_code ");
			query.append("AND c.com_code = ? ");
			ps=conn.prepareStatement(query.toString());
			ps.setInt(1, companycode);
			//System.out.println(query);
			rs=ps.executeQuery();
			while(rs.next()) {
				list.add(new Service(rs.getInt("s.service_code"),
									rs.getString("s.service_name"),
						new Company(rs.getInt("c.com_code"),
								rs.getString("c.com_name"))));
			}
		}finally {
			closeAll(rs, ps, conn);
		}
		return list;
	}
	
	//단위테스트
	public static void main(String[] args) throws SQLException {
		ReviewDaoImpl dao = ReviewDaoImpl.getInstance();
		//System.out.println(dao.showService(1));
		//System.out.println(dao.isReview(1, 1));
		//System.out.println(dao.showAllReview(1).size());
		System.out.println(dao.showReview("1-1-1"));
	}
}
