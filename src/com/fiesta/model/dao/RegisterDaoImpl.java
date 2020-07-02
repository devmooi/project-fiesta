package com.fiesta.model.dao;

import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.sql.DataSource;

import com.fiesta.model.vo.Company;
import com.fiesta.model.vo.Customer;
import com.fiesta.util.AES256Util;
import com.fiesta.util.ServerInfo;

public class RegisterDaoImpl implements RegisterDao {
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
	public void updatePassword(String email, String pass, String pick) throws SQLException {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			conn = getConnection();
			if(pick.equals("customer")) {
				String query = "UPDATE customer SET cust_pass=? WHERE cust_email=?";
				ps = conn.prepareStatement(query);
				ps.setString(1, pass);
				ps.setString(2, email);
				System.out.println(ps.executeUpdate()+" row update success");
				
			} else if(pick.equals("company")) {
				String query = "SELECT com_code FROM company WHERE com_email=?";
				ps = conn.prepareStatement(query);
				ps.setString(1, email);
				rs = ps.executeQuery();
				
				String comCode = "";
				if(rs.next()) {
					comCode = rs.getString("com_code");
				}
				
				query = "UPDATE company SET com_pass=? WHERE com_code=?";
				ps = conn.prepareStatement(query);
				ps.setString(1, pass);
				ps.setString(2, comCode);
				System.out.println(ps.executeUpdate() + " row update success");
				
			}

		} finally {
			closeAll(ps, conn);
		}
		
	}
	
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

	public Customer loginCustomer(String custEmail, String pass) throws SQLException, UnsupportedEncodingException, InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Customer customer = null;
		try {
			conn = getConnection();
			String query = "SELECT * FROM customer WHERE cust_email=?";
			ps = conn.prepareStatement(query);
			ps.setString(1, custEmail);
			rs = ps.executeQuery();
			if(rs.next()) {
				//암호화된 패스워드 복호화
				String key = "aes256-password-key";
				AES256Util aes256 = new AES256Util(key);
				String deStr = aes256.decrypt(rs.getString("cust_pass"));

				if(deStr.equals(pass)) {
					customer = new Customer(custEmail,
							rs.getString("cust_name"),
							pass,
							rs.getString("cust_tel"),
							rs.getString("cust_group"));
				}
			}
		} finally {
			closeAll(rs, ps, conn);
		}
		return customer; 
	}

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
			System.out.println(custEmail+ " lookup success");
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
	
	public void registerCompany(Company company) throws SQLException {
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			conn = getConnection();
			String query = "INSERT INTO company (com_email, com_pass, com_name, com_tel, com_addr, com_img, com_desc, comCategory_code) VALUES(?, ?, ?, ?, ?, ?, ?, ?)";
			ps = conn.prepareStatement(query);
			//System.out.println("ps completed in registerCompany");
			
			ps.setString(1, company.getComEmail());
			ps.setString(2, company.getComPass());
			ps.setString(3, company.getComName());
			ps.setString(4, company.getComTel());
			ps.setString(5, company.getComAddr());
			ps.setString(6, company.getComImg());
			ps.setString(7, company.getComDesc());
			ps.setInt(8, company.getComCategoryCode());
			System.out.println(ps.executeUpdate()+" row register success");
		} finally {
			closeAll(ps, conn);
		}
	}		

	public Company loginCompany(String comEmail, String pass) throws SQLException, UnsupportedEncodingException, InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Company company = null;
		try {
			conn = getConnection();
			String query = "SELECT * FROM company WHERE com_email=?";
			ps = conn.prepareStatement(query);
			ps.setString(1, comEmail);
			rs = ps.executeQuery();
			if(rs.next()) {
				//암호화된 패스워드 복호화
				String key = "aes256-password-key";
				AES256Util aes256 = new AES256Util(key);
				String deStr = aes256.decrypt(rs.getString("com_pass"));
				
				if(deStr.equals(pass)) {
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
				}
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

	public void deleteCompany(String comEmail, String pass) throws SQLException {
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			conn = getConnection();
			String query = "DELETE FROM company WHERE com_email=? AND com_pass=?";
			ps = conn.prepareStatement(query);
			//System.out.println("ps completed in deleteCompany");
			
			ps.setString(1, comEmail);
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
