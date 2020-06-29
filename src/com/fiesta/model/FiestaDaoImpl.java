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
	public void registerCustomer(Customer customer) throws SQLException {
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			conn = getConnection();
			String query = "INSERT INTO customer (cust_name, cust_pass, cust_tel, cust_email, cust_group) VALUES(?,?,?,?,?)";
			ps = conn.prepareStatement(query);
			//System.out.println("ps completed in registerCustomer");
			
			ps.setString(1, customer.getCustName());
			ps.setString(2, customer.getCustPass());
			ps.setString(3, customer.getCustTel());
			ps.setString(4, customer.getCustEmail());
			ps.setString(5, customer.getCustGroup());
			System.out.println(ps.executeUpdate()+" row register success");
		} finally {
			closeAll(ps, conn);
		}
	}

	//VO 수정으로 인한 변경
	public Customer loginCustomer(String custEmail, String pass) throws SQLException {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Customer customer = null;
		try {
			conn = getConnection();
			String query = "SELECT * FROM customer WHERE cust_email=? AND cust_pass=?";
			ps = conn.prepareStatement(query);
			System.out.println("ps completed in loginCustomer");
			ps.setString(1, custEmail);
			ps.setString(2, pass);
			rs = ps.executeQuery();
			if(rs.next()) {
				customer = new Customer(custEmail,
										pass,
										rs.getString("cust_name"),
										rs.getString("cust_tel"),
										rs.getString("cust_group"));
			System.out.println(custEmail+ " login success");
				}
		} finally {
			closeAll(rs, ps, conn);
		}
		return customer; 
	}

	//VO 수정으로 인한 변경
	public void updateCustomer(Customer customer) throws SQLException {
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			conn = getConnection();
			String query = "UPDATE customer SET cust_pass=?, cust_name=?, cust_tel=?, cust_group=? WHERE cust_email=?";
			ps = conn.prepareStatement(query);
			//System.out.println("ps completed in updateCustomer");
			
			ps.setString(1, customer.getCustPass()); 
			ps.setString(2, customer.getCustName());
			ps.setString(3, customer.getCustTel());
			ps.setString(4, customer.getCustGroup());
			ps.setString(5, customer.getCustEmail());
			System.out.println(ps.executeUpdate()+" row update success");
		} finally {
			closeAll(ps, conn);
		}
	}
	
	//VO 수정으로 인한 변경
	public void deleteCustomer(String custEmail, String pass) throws SQLException {
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			conn = getConnection();
			String query = "DELETE FROM customer WHERE cust_email=? AND cust_pass=?";
			ps = conn.prepareStatement(query);
			//System.out.println("ps completed in deleteCustomer");
			
			ps.setString(1, custEmail);
			ps.setString(2, pass);
			System.out.println(ps.executeUpdate()+" row delete success");
		} finally {
			closeAll(ps, conn);
		}
	}

	//VO 수정으로 인한 변경
	public Customer lookupCustomer(String custEmail) throws SQLException {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Customer customer = null;
		try {
			conn = getConnection();
			String query = "SELECT * FROM customer WHERE cust_email=?";
			ps = conn.prepareStatement(query);
			//System.out.println("ps completed in lookupCustomer");
			
			ps.setString(1, custEmail);
			rs = ps.executeQuery();
			if(rs.next()) {
				customer = new Customer(custEmail,
							rs.getString("cust_name"),
							rs.getString("cust_pass"),
							rs.getString("cust_tel"),
							rs.getString("cust_group"));
			//System.out.println(id+ " lookup success");
			}
		} finally {
			closeAll(rs, ps, conn);
		}
		return customer;
	}
	
	public boolean custEmailExist(String custEmail) throws SQLException {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = getConnection();
			String query = "SELECT cust_email FROM customer WHERE cust_email=?";
			ps = conn.prepareStatement(query);
			
			ps.setString(1, custEmail);
			rs = ps.executeQuery();
			return rs.next();
		} finally {
			closeAll(rs, ps, conn);
		}
	}
	
	public boolean comEmailExist(String comEmail) throws SQLException {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = getConnection();
			String query = "SELECT com_email FROM company WHERE com_email=?";
			ps = conn.prepareStatement(query);
			
			ps.setString(1, comEmail);
			rs = ps.executeQuery();
			return rs.next();
		} finally {
			closeAll(rs, ps, conn);
		}
	}
	
	//VO 수정으로 인한 변경
	public void insertCustorder(Custorder custorder) throws SQLException {
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			conn = getConnection();
			String query = "INSERT INTO custorder (order_sysdate, order_revdate, order_place, order_budget, order_require, cust_email, service_code, com_code) VALUES(?,?,?,?,?,?,?,?,?)";
			ps = conn.prepareStatement(query);
			//System.out.println("ps completed in insertCustorder");
			
			ps.setString(1, custorder.getOrderSysdate());
			ps.setString(2, custorder.getOrderRevdate());
			ps.setString(3, custorder.getOrderPlace());
			ps.setString(4, custorder.getOrderBudget());
			ps.setString(5, custorder.getOrderRequire());
			ps.setString(6, custorder.getCustEmail());
			ps.setInt(7, custorder.getServiceCode());
			ps.setInt(8, custorder.getComCode());
			System.out.println(ps.executeUpdate()+" row insert success");
		} finally {
			closeAll(ps, conn);
		}		
	}

	//VO 수정으로 인한 변경
	public void insertOrderdetail(Custrequestdetail orderdetail) throws SQLException {
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			conn = getConnection();
			String query = "INSERT INTO orderdetail (detail_totalprice, detail_desc, detail_condition, request_code, com_code) VALUES(?,?,?,?,?)";
			ps = conn.prepareStatement(query);
			//System.out.println("ps completed in insertOrderdetail");
			
			ps.setInt(1, orderdetail.getDetailTotalprice());
			ps.setString(2, orderdetail.getDetailDesc());
			ps.setString(3, orderdetail.getDetailCondition());
			ps.setInt(4, orderdetail.getRequestCode());
			ps.setInt(5, orderdetail.getComCode());
			System.out.println(ps.executeUpdate()+" row insert success");
		} finally {
			closeAll(ps, conn);
		}				
	}
	
	//VO 수정으로 인한 변경
	public ArrayList<Custorder> showAllCustorder(String custEmail) throws SQLException {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		ArrayList<Custorder> list = new ArrayList<>();
		try {
			conn = getConnection();
			String query = "SELECT * FROM custorder WHERE cust_email=?";
			ps = conn.prepareStatement(query);
			//System.out.println("ps completed in showAllCustorder");
			
			ps.setString(1, custEmail);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				list.add(new Custorder(
									   rs.getInt("order_code"),
									   rs.getString("order_sysdate"),
									   rs.getString("order_revdate"),
									   rs.getString("order_place"),
									   rs.getString("order_budget"),
									   rs.getString("order_require"),
									   rs.getString("order_condition"),
									   custEmail,
									   rs.getInt("service_code"),
									   rs.getInt("com_code")));
			//System.out.println(id+ " showallcustorder success");
			}
		} finally {
			closeAll(rs, ps, conn);
		}
		return list;
	}
	
	
	
	
	//foreign key...?
	public ArrayList<Custrequestdetail> showAllOrderdetail(String requestCode) throws SQLException {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		ArrayList<Custrequestdetail> list = new ArrayList<>();
		try {
			conn = getConnection();
			String query = "SELECT * FROM orderdetail WHERE order_require=?";
			ps = conn.prepareStatement(query);
			System.out.println("ps completed in showAllOrderdetail");
			
			ps.setString(1, requestCode);
			rs = ps.executeQuery();
			/*while(rs.next()) {
				list.add(new Orderdetail(
									   rs.getInt("detail_code"),
									   rs.getInt("detail_totalprice"),
									   rs.getString("detail_desc"),
									   rs.getInt("service_code"),
									   rs.getInt("com_code")
									   id));
			System.out.println(id+ " showallorderdetail success");
			}*/
		} finally {
			closeAll(rs, ps, conn);
		}
		return list;
	}

	
	
	
	
	
	//VO 수정으로 인한 변경
	public void registerCompany(Company company) throws SQLException {
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			conn = getConnection();
			String query = "INSERT INTO company(com_email, com_pass, com_name, com_tel, com_addr, com_img, com_desc, com_count, comCategory_code) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?)";
			ps = conn.prepareStatement(query);
			//System.out.println("ps completed in registerCompany");
			
			ps.setString(1, company.getComEmail());
			ps.setString(2, company.getComPass());
			ps.setString(3, company.getComName());
			ps.setString(4, company.getComTel());
			ps.setString(5, company.getComAddr());
			ps.setString(6, company.getComImg());
			ps.setString(7, company.getComDesc());
			ps.setInt(8, company.getComCount());
			ps.setInt(9, company.getComCategoryCode());
			System.out.println(ps.executeUpdate()+" row register success");
		} finally {
			closeAll(ps, conn);
		}
	}		

	//VO 수정으로 인한 변경
	public Company loginCompany(String comEmail, String pass) throws SQLException {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Company company = null;
		try {
			conn = getConnection();
			String query = "SELECT * FROM company WHERE com_email=? AND com_pass=?";
			ps = conn.prepareStatement(query);
			//System.out.println("ps completed in loginCompany");

			ps.setString(1, comEmail);
			ps.setString(2, pass);
			rs = ps.executeQuery();
			if(rs.next()) {
				company = new Company(
									  rs.getInt("com_code"),
									  comEmail,
									  pass,
									  rs.getString("com_name"),
									  rs.getString("com_tel"),
									  rs.getString("com_addr"),
									  rs.getString("com_img"),
									  rs.getString("com_desc"),
									  rs.getInt("com_count"),
									  rs.getInt("comCategory_code"));  
			//System.out.println(id+ " login success");
				}
		} finally {
			closeAll(rs, ps, conn);
		}
		return company; 
	}

	//VO 수정으로 인한 변경
	public void updateCompany(Company company) throws SQLException {
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			conn = getConnection();
			String query = "UPDATE company SET com_pass=?, com_name=?, com_tel=?, com_addr=?, com_img=?, com_desc=?, comCategory_code=? WHERE com_email=?";
			ps = conn.prepareStatement(query);
			//System.out.println("ps completed in updateCompany");
			
			ps.setString(1, company.getComPass());
			ps.setString(2, company.getComName());
			ps.setString(3, company.getComTel());
			ps.setString(4, company.getComAddr());
			ps.setString(5, company.getComImg());
			ps.setString(6, company.getComDesc());
			ps.setInt(7, company.getComCategoryCode());
			ps.setString(8, company.getComEmail());
			
			System.out.println(ps.executeUpdate()+" row update success");
		} finally {
			closeAll(ps, conn);
		}		
	}

	//VO 수정으로 인한 변경
	public void deleteCompany(String comEmail, String pass) throws SQLException {
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			conn = getConnection();
			String query = "DELETE FROM company WHERE com_id=? AND com_pass=?";
			ps = conn.prepareStatement(query);
			//System.out.println("ps completed in deleteCompany");
			
			ps.setString(1, comEmail);
			ps.setString(2, pass);
			System.out.println(ps.executeUpdate()+" row delete success");
		} finally {
			closeAll(ps, conn);
		}		
	}
	
	
	//VO 변경으로 인한 수정
	public Company lookupCompany(String comEmail) throws SQLException {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Company company = null;
		try {
			conn = getConnection();
			String query = "SELECT * FROM company WHERE com_email=?";
			ps = conn.prepareStatement(query);
			//System.out.println("ps completed in lookupCompany");
			
			ps.setString(1, comEmail);
			rs = ps.executeQuery();
			if(rs.next()) {
				company = new Company(
										  rs.getInt("com_code"),
										  comEmail,
										  rs.getString("com_pass"),
										  rs.getString("com_name"),
										  rs.getString("com_tel"),
										  rs.getString("com_addr"),
										  rs.getString("com_img"),
										  rs.getString("com_desc"),
										  rs.getInt("com_count"),
										  rs.getInt("comCategory_code"));  
			//System.out.println(id+ " lookup success");
			}
		} finally {
			closeAll(rs, ps, conn);
		}
		return company;
	}
	
	
	
	public ArrayList<Review> showAllCompany() throws SQLException {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs =null;
		ArrayList<Review> list = new ArrayList<Review>();

		try {
			conn=getConnection();
			StringBuffer query = new StringBuffer();
			query.append("SELECT c.com_code, c.com_name, c.com_desc, c.com_img, c.com_count, avg(r.review_score), count(r.review_desc) ");
			query.append("FROM company c LEFT OUTER JOIN review r ");
			query.append("ON c.com_code = r.com_code ");
			query.append("GROUP BY c.com_code ");
			query.append("ORDER BY c.com_code DESC");
			ps=conn.prepareStatement(query.toString());
			//System.out.println(query.toString());
			rs=ps.executeQuery();
			while(rs.next()) {
				Review vo = new Review();
				vo.setAvgReviewScore(rs.getFloat("avg(r.review_score)"));
				vo.setCountDesc(rs.getInt("count(r.review_desc)"));
				vo.setCompany(new Company(rs.getInt("com_code"),
						rs.getString("com_name"),
						rs.getString("com_img"),
						rs.getString("com_desc"),
						rs.getInt("com_count")));
				list.add(vo);
			}
		}finally {
			closeAll(rs, ps, conn);
		}

		return list;
	}

	public ArrayList<Review> showAllCompanyByCategory(int category) throws SQLException {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs =null;
		ArrayList<Review> list = new ArrayList<Review>();
		
		try {
			conn=getConnection();
			StringBuffer query = new StringBuffer();
			query.append("SELECT c.com_code, c.com_name, c.com_desc, c.com_img, c.com_count, avg(r.review_score), count(r.review_desc) ");
			query.append("FROM company c LEFT OUTER JOIN review r ");
			query.append("ON c.com_code = r.com_code ");
			query.append("WHERE c.comCategory_code = ? ");
			query.append("GROUP BY c.com_code ");
			query.append("ORDER BY c.com_code DESC ");
			
			ps=conn.prepareStatement(query.toString());
			ps.setInt(1, category);
			
			rs=ps.executeQuery();
			while(rs.next()) {
				Review vo = new Review();
				vo.setAvgReviewScore(rs.getFloat("avg(r.review_score)"));
				vo.setCountDesc(rs.getInt("count(r.review_desc)"));
				vo.setCompany(new Company(rs.getInt("com_code"),
						rs.getString("com_name"),
						rs.getString("com_img"),
						rs.getString("com_desc"),
						rs.getInt("com_count")));
				list.add(vo);
			}
			}finally {
			closeAll(rs, ps, conn);
			}
		
		return list;
	}
	
	//카테고리를 설정 안 하고 검색할 때
	public ArrayList<Review> lookupCompany(String searchBy, String searchContent) throws SQLException {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs =null;
		ArrayList<Review> list = new ArrayList<Review>();
		
		try {
			conn=getConnection();
			StringBuffer query = new StringBuffer();
			query.append("SELECT c.com_code, c.com_name, c.com_desc, c.com_img, c.com_count, avg(r.review_score), count(r.review_desc) ");
			query.append("FROM company c ");
			query.append("LEFT OUTER JOIN review r ");
			query.append("ON c.com_code = r.com_code ");
			query.append("LEFT OUTER JOIN service s ");
			query.append("ON c.com_code = s.com_code ");
			if(searchBy.equals("태그")) {
				query.append("WHERE s.service_tag LIKE ? ");
				query.append("GROUP BY c.com_code ");
				query.append("ORDER BY c.com_code DESC ");
				ps=conn.prepareStatement(query.toString());
				ps.setString(1, "%"+searchContent+"%");
			}else if(searchBy.equals("회사명")) {
				query.append("WHERE c.com_name LIKE ? ");
				query.append("GROUP BY c.com_code ");
				query.append("ORDER BY c.com_code DESC ");
				ps=conn.prepareStatement(query.toString());
				ps.setString(1, "%"+searchContent+"%");
			}else {
				query.append("WHERE (s.service_tag LIKE ? ");
				query.append("OR c.com_name LIKE ?) ");
				query.append("GROUP BY c.com_code ");
				query.append("ORDER BY c.com_code DESC ");
				ps=conn.prepareStatement(query.toString());
				ps.setString(1, "%"+searchContent+"%");
				ps.setString(2, "%"+searchContent+"%");
			}
			rs=ps.executeQuery();
			while(rs.next()) {
				Review vo = new Review();
				vo.setAvgReviewScore(rs.getFloat("avg(r.review_score)"));
				vo.setCountDesc(rs.getInt("count(r.review_desc)"));
				vo.setCompany(new Company(rs.getInt("com_code"),
						rs.getString("com_name"),
						rs.getString("com_img"),
						rs.getString("com_desc"),
						rs.getInt("com_count")));
				list.add(vo);
			}
		}finally {
			closeAll(rs, ps, conn);
		}
		return list;
	}
	
	public ArrayList<Review> lookupCompany(int category, String searchBy, String searchContent) throws SQLException {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs =null;
		ArrayList<Review> list = new ArrayList<Review>();
		
		try {
			conn=getConnection();
			StringBuffer query = new StringBuffer();
			query.append("SELECT c.com_code, c.com_name, c.com_desc, c.com_img, c.com_count, avg(r.review_score), count(r.review_desc) ");
			query.append("FROM company c ");
			query.append("LEFT OUTER JOIN review r ");
			query.append("ON c.com_code = r.com_code ");
			query.append("LEFT OUTER JOIN service s ");
			query.append("ON c.com_code = s.com_code ");
			query.append("WHERE c.comCategory_code = ? ");
			if(searchBy.equals("태그")) {
				query.append("AND s.service_tag LIKE ? ");
				query.append("GROUP BY c.com_code ");
				query.append("ORDER BY c.com_code DESC ");
				ps=conn.prepareStatement(query.toString());
				ps.setString(2, "%"+searchContent+"%");
			}else if(searchBy.equals("회사명")) {
				query.append("AND c.com_name LIKE ? ");
				query.append("GROUP BY c.com_code ");
				query.append("ORDER BY c.com_code DESC ");
				ps=conn.prepareStatement(query.toString());
				ps.setString(2, "%"+searchContent+"%");
			}else {
				query.append("AND (s.service_tag LIKE ? ");
				query.append("OR c.com_name LIKE ?) ");
				query.append("GROUP BY c.com_code ");
				query.append("ORDER BY c.com_code DESC ");
				ps=conn.prepareStatement(query.toString());
				ps.setString(2, "%"+searchContent+"%");
				ps.setString(3, "%"+searchContent+"%");
			}
			ps.setInt(1, category);
			rs=ps.executeQuery();
			while(rs.next()) {
				Review vo = new Review();
				vo.setAvgReviewScore(rs.getFloat("avg(r.review_score)"));
				vo.setCountDesc(rs.getInt("count(r.review_desc)"));
				vo.setCompany(new Company(rs.getInt("com_code"),
						rs.getString("com_name"),
						rs.getString("com_img"),
						rs.getString("com_desc"),
						rs.getInt("com_count")));
				list.add(vo);
			}
		}finally {
			closeAll(rs, ps, conn);
		}
		return list;
	}

	public ArrayList<Review> sortCompany(String sortBy) throws SQLException {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs =null;
		ArrayList<Review> list = new ArrayList<Review>();
		
		try {
			conn=getConnection();
			String query2="";
			if(sortBy.equals("최신순")) {
				query2="GROUP BY c.com_code ORDER BY c.com_code DESC";
			}else if(sortBy.equals("조회순")) {
				query2="GROUP BY c.com_code ORDER BY c.com_count DESC";
			}else if(sortBy.equals("평점순")) {
				query2="GROUP BY c.com_code ORDER BY AVG(r.review_score) DESC";
			}else {
				query2="GROUP BY c.com_code ORDER BY COUNT(r.review_code) DESC";
			}

			StringBuffer query = new StringBuffer();
			query.append("SELECT c.com_code, c.com_name, c.com_desc, c.com_img, c.com_count, avg(r.review_score), count(r.review_desc) ");
			query.append("FROM company c ");
			query.append("LEFT OUTER JOIN review r ");
			query.append("ON c.com_code = r.com_code ");
			query.append("LEFT OUTER JOIN service s ");
			query.append("ON c.com_code = s.com_code ");
			query.append(query2);
			ps=conn.prepareStatement(query.toString());
			//System.out.println(query);
			rs=ps.executeQuery();
			while(rs.next()) {
				Review vo = new Review();
				vo.setAvgReviewScore(rs.getFloat("avg(r.review_score)"));
				vo.setCountDesc(rs.getInt("count(r.review_desc)"));
				vo.setCompany(new Company(rs.getInt("com_code"),
						rs.getString("com_name"),
						rs.getString("com_img"),
						rs.getString("com_desc"),
						rs.getInt("com_count")));
				list.add(vo);
			}
		}finally {
			closeAll(rs, ps, conn);
		}
		
		return list;
	}
	
	public ArrayList<Review> sortCompany(int category, String sortBy) throws SQLException {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs =null;
		ArrayList<Review> list = new ArrayList<Review>();
		
		try {
			conn=getConnection();
			String query2="";
			if(sortBy.equals("최신순")) {
				query2="GROUP BY c.com_code ORDER BY c.com_code DESC";
			}else if(sortBy.equals("조회순")) {
				query2="GROUP BY c.com_code ORDER BY c.com_count DESC";
			}else if(sortBy.equals("평점순")) {
				query2="GROUP BY c.com_code ORDER BY AVG(r.review_score) DESC";
			}else {
				query2="GROUP BY c.com_code ORDER BY COUNT(r.review_code) DESC";
			}

			StringBuffer query = new StringBuffer();
			query.append("SELECT c.com_code, c.com_name, c.com_desc, c.com_img, c.com_count, avg(r.review_score), count(r.review_desc) ");
			query.append("FROM company c ");
			query.append("LEFT OUTER JOIN review r ");
			query.append("ON c.com_code = r.com_code ");
			query.append("LEFT OUTER JOIN service s ");
			query.append("ON c.com_code = s.com_code ");
			query.append("WHERE c.comCategory_code = ? ");
			query.append(query2);
			ps=conn.prepareStatement(query.toString());
			ps.setInt(1, category);
			rs=ps.executeQuery();
			while(rs.next()) {
				Review vo = new Review();
				vo.setAvgReviewScore(rs.getFloat("avg(r.review_score)"));
				vo.setCountDesc(rs.getInt("count(r.review_desc)"));
				vo.setCompany(new Company(rs.getInt("com_code"),
						rs.getString("com_name"),
						rs.getString("com_img"),
						rs.getString("com_desc"),
						rs.getInt("com_count")));
				list.add(vo);
			}
		}finally {
			closeAll(rs, ps, conn);
		}
		
		return list;
	}
	
	public ArrayList<Review> sortCompany(String searchBy, String searchContent, String sortBy) throws SQLException {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs =null;
		ArrayList<Review> list = new ArrayList<Review>();
		
		try {
			conn=getConnection();
			String query2="";
			if(sortBy.equals("최신순")) {
				query2="GROUP BY c.com_code ORDER BY c.com_code DESC";
			}else if(sortBy.equals("조회순")) {
				query2="GROUP BY c.com_code ORDER BY c.com_count DESC";
			}else if(sortBy.equals("평점순")) {
				query2="GROUP BY c.com_code ORDER BY AVG(r.review_score) DESC";
			}else {
				query2="GROUP BY c.com_code ORDER BY COUNT(r.review_code) DESC";
			}

			StringBuffer query = new StringBuffer();
			query.append("SELECT c.com_code, c.com_name, c.com_desc, c.com_img, c.com_count, avg(r.review_score), count(r.review_desc) ");
			query.append("FROM company c ");
			query.append("LEFT OUTER JOIN review r ");
			query.append("ON c.com_code = r.com_code ");
			query.append("LEFT OUTER JOIN service s ");
			query.append("ON c.com_code = s.com_code ");
			if(searchBy.equals("태그")) {
				query.append("AND s.service_tag LIKE ? ");
				query.append(query2);
				ps=conn.prepareStatement(query.toString());
				ps.setString(1, "%"+searchContent+"%");
			}else if(searchBy.equals("회사명")) {
				query.append("AND c.com_name LIKE ? ");
				query.append(query2);
				ps=conn.prepareStatement(query.toString());
				ps.setString(1, "%"+searchContent+"%");
			}else {
				query.append("AND (s.service_tag LIKE ? ");
				query.append("OR c.com_name LIKE ?) ");
				query.append(query2);
				ps=conn.prepareStatement(query.toString());
				ps.setString(1, "%"+searchContent+"%");
				ps.setString(2, "%"+searchContent+"%");
			}
			rs=ps.executeQuery();
			while(rs.next()) {
				Review vo = new Review();
				vo.setAvgReviewScore(rs.getFloat("avg(r.review_score)"));
				vo.setCountDesc(rs.getInt("count(r.review_desc)"));
				vo.setCompany(new Company(rs.getInt("com_code"),
						rs.getString("com_name"),
						rs.getString("com_img"),
						rs.getString("com_desc"),
						rs.getInt("com_count")));
				list.add(vo);
			}
		}finally {
			closeAll(rs, ps, conn);
		}
		
		return list;
	}
	
	public ArrayList<Review> sortCompany(int category, String searchBy, String searchContent, String sortBy) throws SQLException {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs =null;
		ArrayList<Review> list = new ArrayList<Review>();
		
		try {
			conn=getConnection();
			String query2="";
			if(sortBy.equals("최신순")) {
				query2="GROUP BY c.com_code ORDER BY c.com_code DESC";
			}else if(sortBy.equals("조회순")) {
				query2="GROUP BY c.com_code ORDER BY c.com_count DESC";
			}else if(sortBy.equals("평점순")) {
				query2="GROUP BY c.com_code ORDER BY AVG(r.review_score) DESC";
			}else {
				query2="GROUP BY c.com_code ORDER BY COUNT(r.review_code) DESC";
			}

			StringBuffer query = new StringBuffer();
			query.append("SELECT c.com_code, c.com_name, c.com_desc, c.com_img, c.com_count, avg(r.review_score), count(r.review_desc) ");
			query.append("FROM company c ");
			query.append("LEFT OUTER JOIN review r ");
			query.append("ON c.com_code = r.com_code ");
			query.append("LEFT OUTER JOIN service s ");
			query.append("ON c.com_code = s.com_code ");
			query.append("WHERE c.comCategory_code = ? ");
			if(searchBy.equals("태그")) {
				query.append("AND s.service_tag LIKE ? ");
				query.append(query2);
				ps=conn.prepareStatement(query.toString());
				ps.setString(2, "%"+searchContent+"%");
			}else if(searchBy.equals("회사명")) {
				query.append("AND c.com_name LIKE ? ");
				query.append(query2);
				ps=conn.prepareStatement(query.toString());
				ps.setString(2, "%"+searchContent+"%");
			}else {
				query.append("AND (s.service_tag LIKE ? ");
				query.append("OR c.com_name LIKE ? ");
				query.append(query2);
				ps=conn.prepareStatement(query.toString());
				ps.setString(2, "%"+searchContent+"%");
				ps.setString(3, "%"+searchContent+"%");
			}
			ps.setInt(1, category);
			rs=ps.executeQuery();
			while(rs.next()) {
				Review vo = new Review();
				vo.setAvgReviewScore(rs.getFloat("avg(r.review_score)"));
				vo.setCountDesc(rs.getInt("count(r.review_desc)"));
				vo.setCompany(new Company(rs.getInt("com_code"),
						rs.getString("com_name"),
						rs.getString("com_img"),
						rs.getString("com_desc"),
						rs.getInt("com_count")));
				list.add(vo);
			}
		}finally {
			closeAll(rs, ps, conn);
		}
		
		return list;
	}
	
	//VO 변경으로 인한 수정
	public void insertService(Service service) throws SQLException {
		Connection conn = null;
		PreparedStatement ps = null;
		try{
			conn=  getConnection();
			String query = "INSERT INTO service(service_name, service_desc, service_img, service_tag, com_code) VALUES(?,?,?,?,?)";
			ps = conn.prepareStatement(query);
			System.out.println("PreparedStatement 생성됨...insertService");
			
			ps.setString(1, service.getServiceName());
			ps.setString(2, service.getServiceDesc());
			ps.setString(3, service.getServiceImg());
			ps.setString(4, service.getServiceTag());
			ps.setInt(5, service.getComCode());
			
			System.out.println(ps.executeUpdate()+" row INSERT OK!!");
		}finally{
			closeAll(ps, conn);
		}
		
	}

	public void deleteService(int code) throws SQLException {
		Connection conn = null;
		PreparedStatement ps = null;
		try{
			conn=  getConnection();
			String query = "DELETE FROM service WHERE service_code=?";
			ps = conn.prepareStatement(query);
			System.out.println("PreparedStatement 생성됨...insertService");
			
			ps.setInt(1, code);
			
			System.out.println(ps.executeUpdate()+" row delete OK!!");
		}finally{
			closeAll(ps, conn);
		}
		
	}

	public ArrayList<Service> showAllService(String companycode) throws SQLException {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		ArrayList<Service> list = new ArrayList<>();
		try {
			conn = getConnection();
			String query = "SELECT * FROM service WHERE com_code=?";
			ps = conn.prepareStatement(query);
			System.out.println("PreparedStatement....showAllService..");
					
			ps.setString(1, companycode);
			
			rs = ps.executeQuery();
			while(rs.next()) {
				list.add(new Service(rs.getInt("service_code"),
						rs.getString("service_name"),
						rs.getString("service_desc"),
						rs.getString("service_img"),
						rs.getString("service_tag"),
						rs.getInt("com_code")));
			}
		}finally {
			closeAll(rs, ps, conn);
		}
		return list;
	}

	public void insertQuestion(int comCode, String title, String desc, String custEmail) throws SQLException {
		Connection conn = null;
		PreparedStatement ps = null;
		
		//현재시간 출력
		SimpleDateFormat format = new SimpleDateFormat ( "yyyy-MM-dd HH:mm:ss");
		Calendar time = Calendar.getInstance();
		String currTime = format.format(time.getTime());
		//System.out.println(currTime); 확인용
		
		try{
			conn=  getConnection();
			String query = "INSERT INTO question(q_date, q_title, q_desc, cust_email, com_code) VALUES(?,?,?,?,?)";
			ps = conn.prepareStatement(query);
			System.out.println("PreparedStatement 생성됨...insertQuestion");
			
			ps.setString(1, currTime);
			ps.setString(2, title);
			ps.setString(3, desc);
			ps.setString(4, custEmail);	//세션의 고객아이디 값 가져오기
			ps.setInt(5, comCode); //기업코드값 가져오기
			//condition 상태값 default값이 '답변대기'여야 함.
			
			System.out.println(ps.executeUpdate()+" row INSERT OK!!");
		}finally{
			closeAll(ps, conn);
		}
		
	}

	//고객의 질문들보기
	public ArrayList<Question> showAllQuestion(String custEmail) throws SQLException {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		ArrayList<Question> list = new ArrayList<>();
		
		String qDesc= "";
		try {
			conn = getConnection();

			String query = "SELECT q.q_code, q.q_title, q.q_desc, q.q_date, q_condition, c.com_name"
					+ "FROM question q, company c"
					+ "WHERE q.com_code = c.com_code and cust_email=?";
			ps = conn.prepareStatement(query);
			System.out.println("PreparedStatement....showAllQuestion..");
					
			ps.setString(1, custEmail);
			
			rs = ps.executeQuery();
			while(rs.next()) {
				//내용에 문장자르기~~~
				if(rs.getString("q.q_desc").length()>15) {
					qDesc = rs.getString("q.q_desc").substring(0, 15)+"...";
				}else {
					qDesc = rs.getString("q.q_desc");
				}
				Company com = new Company();
				com.setComName("c.com_name");
				list.add(new Question(rs.getInt("q.q_code"), 
									  rs.getString("q.q_title"), 
									  qDesc, 
									  rs.getString("q.q_date"),
									  rs.getString("q.q_condition"),
									  com.getComName()));
			}
		}finally {
			closeAll(rs, ps, conn);
		}
		return list;
	}
	
	//하나조회하는거 추가
/*	public Question showQuestion(int qCode) throws SQLException {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Question question = null;
		
		try {
			conn = getConnection();

			String query = "select q.q_code, q.q_title, q.q_desc, q.q_date, c.com_name "
					+ "from question q,  company c"
					+ "where q.com_code = c.com_code and q.q_code=?";
			ps = conn.prepareStatement(query);
			System.out.println("PreparedStatement....showQuestion..");
					
			ps.setInt(1, qCode);
			
			rs = ps.executeQuery();
			if(rs.next()) {
					question = new Question(qCode, 
							  rs.getString("q_title"),
							  rs.getString("q_desc"),
							  rs.getString("q_date"));
			}
		}finally {
			closeAll(rs, ps, conn);
		}
		return question;
	}*/
	
	//회사입장에서 자기한테 들어온 문의만 보기
	public ArrayList<Question> showAllQuestionByCompany(int comCode) throws SQLException {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		ArrayList<Question> list = new ArrayList<>();
		Question question = new Question();
		
		String qDesc= "";
		try {
			conn = getConnection();

			String query = "SELECT q_code, q_title, q_desc, q_date, q_condition, cust_email FROM question WHERE com_code=?";
			ps = conn.prepareStatement(query);
			System.out.println("PreparedStatement....showAllQuestionByCompany..");
					
			ps.setInt(1, comCode);
			
			rs = ps.executeQuery();
			while(rs.next()) {
				//내용에 문장자르기~~~
				if(rs.getString("q_desc").length()>15) {
					qDesc = rs.getString("q_desc").substring(0, 15)+"...";
				}else {
					qDesc = rs.getString("q_desc");
				}
				/*list.add(new Question(rs.getInt("q_code"), 
									  rs.getString("q_title"), 
									  qDesc, 
									  rs.getString("q_date"),
									  rs.getString("q_condition"),
									  rs.getString("cust_email")));*/
				question.setqCode(rs.getInt("q_code"));
				question.setqTitle(rs.getString("q_title"));
				question.setqDesc(qDesc);
				question.setqDate(rs.getString("q_date"));
				question.setqCondition(rs.getString("q_condition"));
				question.setCustEmail(rs.getString("cust_email"));
				list.add(question);
			}
		}finally {
			closeAll(rs, ps, conn);
		}
		return list;
	}

	public void insertAnswer(int qCode, Answer answer) throws SQLException {
		Connection conn = null;
		PreparedStatement ps = null;
		
		//현재시간 출력
		SimpleDateFormat format = new SimpleDateFormat ( "yyyy-MM-dd HH:mm:ss");
		Calendar time = Calendar.getInstance();
		String currTime = format.format(time.getTime());
		//System.out.println(currTime); 확인용
		
		try{
			conn=  getConnection();
			String query = "INSERT INTO answer(a_date, a_desc, com_code, q_code, cust_email) VALUES(?,?,?,?,?)";
			ps = conn.prepareStatement(query);
			System.out.println("PreparedStatement 생성됨...insertAnswer");
			
			ps.setString(1, currTime);
			ps.setString(2, answer.getaDesc());
			ps.setInt(3, answer.getComCode());
			ps.setInt(4, answer.getqCode());	//세션의 업체코드 가져오기
			ps.setString(5, answer.getCustEmail());
			
			// 답변대기를 답변완료로 바꾸기, 이메일로 보내기
			
			System.out.println(ps.executeUpdate()+" row INSERT OK!!");
		}finally{
			closeAll(ps, conn);
		}
		
	}

	public ArrayList<Answer> showAllAnswer(String id) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	public Answer showAnswer(int qCode) throws SQLException {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Answer answer = null;
		
		try {
			conn = getConnection();
			//select q.q_code, q.q_title, q.q_desc, q.q_date, a.a_code, a.a_desc, a.a_date from question q, answer a where a.q_code = ?;
			String query = "select a.a_desc, a.a_date, c.com_name from question q, answer a, company c where a.q_code = ? and c.com_code = a.com_code";
			ps = conn.prepareStatement(query);
			System.out.println("PreparedStatement....showAnswer..");
					
			ps.setInt(1, qCode);
			
			rs = ps.executeQuery();
			if(rs.next()) {
				Company com = new Company();
				com.setComName(rs.getString("c.com_name"));
				answer = new Answer(rs.getString("a.a_desc"), 
						  rs.getString("a.a_date"), 
						  com.getComName());
			}
		}finally {
			closeAll(rs, ps, conn);
		}
		return answer;
	}
	
	
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

	
	//Wish
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
		//FiestaDaoImpl dao=FiestaDaoImpl.getInstance();
		//dao.insertService(new Service("회사1","설명1","img","버스"));		
		
		//엄체코드를 자동으로 session에서 받고 돌려야할것...  단위테스트 업체코드에 default값 없어서 못함... ㅠㅅㅠ
		
		//dao.deleteService(3);
		//System.out.println(dao.showAllService(1));
		
		//dao.insertQuestion("숙박문의","몇명이서 잘 수 있나요?", "java");
		//dao.insertQuestion("공연문의","이거슨 문장 잘라지는지 테스트하기 위한 문의사항입니담 키키키키킼", "java");
		dao.insertQuestion(1,"버스문의","이거는 답변 없는 문의를 보는 테스트", "encore@gmail.com");
		//System.out.println(dao.showAllQuestion("java"));
		
		//System.out.println(dao.showQuestion(1));
		//System.out.println(dao.showAnswer(1));
		
		//제영 - 단위테스트
		//System.out.println(dao.showAllCompany());
		System.out.println(dao.sortCompany("평점순"));
		
		//의근 - 단위테스트
		// customer register
		// customer = new Customer("park", "박의근", "euigeun", "01088048331", "euigeun@gmail.com", "플레이데이터");
		// FiestaDaoImpl.getInstance().registerCustomer(customer);
		// FiestaDaoImpl.getInstance().registerCustomer(new Customer("lee", "이보람", "0107171293", "boram@gmail.com", "concat", "플레이데이터"));
		
		// customer login
		FiestaDaoImpl.getInstance().loginCustomer("park.eui.geun@gmail.com", "12341234");
		
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