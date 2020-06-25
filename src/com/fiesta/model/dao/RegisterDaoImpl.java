package com.fiesta.model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import com.fiesta.model.vo.Company;
import com.fiesta.model.vo.Customer;
import com.fiesta.util.ServerInfo;

public class RegisterDaoImpl {
	private DataSource ds;
	
	private static RegisterDaoImpl dao = new RegisterDaoImpl();
	private RegisterDaoImpl() {
		//ds = DataSourceManager.getInstance().getConnection();
		
		//단위테스트
		try {
			Class.forName(ServerInfo.DRIVER_NAME);
		} catch (ClassNotFoundException e) {
			System.out.println("CustomerDaoImpl :: " + e);
		}
	}
	public static RegisterDaoImpl getInstance() {
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
			String query = "INSERT INTO customer (cust_id, cust_name, cust_pass, cust_tel, cust_email, cust_group) VALUES(?,?,?,?,?,?)";
			ps = conn.prepareStatement(query);
			//System.out.println("ps completed in registerCustomer");
			
			//ps.setString(1, customer.getCustId());
			ps.setString(2, customer.getCustName());
			ps.setString(3, customer.getCustPass());
			ps.setString(4, customer.getCustTel());
			ps.setString(5, customer.getCustEmail());
			ps.setString(6, customer.getCustGroup());
			System.out.println(ps.executeUpdate()+" row register success");
			
		} finally {
			closeAll(ps, conn);
		}
	}
	
	public Customer loginCustomer(String id, String pass) throws SQLException {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Customer customer = null;
		/*try {
			conn = getConnection();
			String query = "SELECT * FROM customer WHERE cust_id=? AND cust_pass=?";
			ps = conn.prepareStatement(query);
			//System.out.println("ps completed in loginCustomer");
			ps.setString(1, id);
			ps.setString(2, pass);
			rs = ps.executeQuery();
			if(rs.next()) {
				customer = new Customer(id,
										pass,
										rs.getString("cust_name"),
										rs.getString("cust_tel"),
										rs.getString("cust_email"),
										rs.getString("cust_group"));
			//System.out.println(id+ " login success");
				}
		} finally {
			closeAll(rs, ps, conn);
		}*/
		return customer; 
	}
	
	public void updateCustomer(Customer customer) throws SQLException {
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			conn = getConnection();
			String query = "UPDATE customer SET cust_pass=?, cust_name=?, cust_tel=?, cust_email=?, cust_group=? WHERE cust_id=?";
			ps = conn.prepareStatement(query);
			//System.out.println("ps completed in updateCustomer");
			
			ps.setString(1, customer.getCustPass()); 
			ps.setString(2, customer.getCustName());
			ps.setString(3, customer.getCustTel());
			ps.setString(4, customer.getCustEmail());
			ps.setString(5, customer.getCustGroup());
			//ps.setString(6, customer.getCustId());
			System.out.println(ps.executeUpdate()+" row update success");
		} finally {
			closeAll(ps, conn);
		}
	}

	public void deleteCustomer(String id, String pass) throws SQLException {
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			conn = getConnection();
			String query = "DELETE FROM customer WHERE cust_id=? AND cust_pass=?";
			ps = conn.prepareStatement(query);
			//System.out.println("ps completed in deleteCustomer");
			
			ps.setString(1, id);
			ps.setString(2, pass);
			System.out.println(ps.executeUpdate()+" row delete success");
		} finally {
			closeAll(ps, conn);
		}
	}
	
	public Customer lookupCustomer(String id) throws SQLException {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Customer customer = null;
		/*try {
			conn = getConnection();
			String query = "SELECT * FROM customer WHERE cust_id=?";
			ps = conn.prepareStatement(query);
			//System.out.println("ps completed in lookupCustomer");
			
			ps.setString(1, id);
			rs = ps.executeQuery();
			if(rs.next()) {
				customer = new Customer(id,
							rs.getString("cust_name"),
							rs.getString("cust_pass"),
							rs.getString("cust_tel"),
							rs.getString("cust_email"),
							rs.getString("cust_group"));
			//System.out.println(id+ " lookup success");
			}
		} finally {
			closeAll(rs, ps, conn);
		}*/
		return customer;
	}
	
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

	public void deleteCompany(String id, String pass) throws SQLException {
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			conn = getConnection();
			String query = "DELETE FROM company WHERE com_id=? AND com_pass=?";
			ps = conn.prepareStatement(query);
			//System.out.println("ps completed in deleteCompany");
			
			ps.setString(1, id);
			ps.setString(2, pass);
			System.out.println(ps.executeUpdate()+" row delete success");
		} finally {
			closeAll(ps, conn);
		}		
	}
	
	//단위테스트
	public static void main(String[] args) throws SQLException {
		RegisterDaoImpl dao = RegisterDaoImpl.getInstance();
		
	}
}
