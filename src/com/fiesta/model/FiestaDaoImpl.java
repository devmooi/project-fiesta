package com.fiesta.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.sql.DataSource;

import com.fiesta.model.vo.Answer;
import com.fiesta.model.vo.Comcategory;
import com.fiesta.model.vo.Company;
import com.fiesta.model.vo.Customer;
import com.fiesta.model.vo.Custorder;
import com.fiesta.model.vo.Orderdetail;
import com.fiesta.model.vo.Question;
import com.fiesta.model.vo.Review;
import com.fiesta.model.vo.Service;
import com.fiesta.model.vo.Wish;
import com.fiesta.util.ServerInfo;

public class FiestaDaoImpl implements FiestaDao{
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
		// TODO Auto-generated method stub
		
	}

	public Customer loginCustomer(String id, String pass) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	public void updateCustomer(Customer customer) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	public void deleteCustomer(String id, String pass) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	public Customer lookupCustomer(String id) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	public void insertCustorder(Custorder custorder) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	public void insertOrderdetail(Orderdetail orderdetail) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	public ArrayList<Custorder> showAllCustorder(String id) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	public ArrayList<Orderdetail> showAllOrderdetail(String id) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	public void registerCompany(Company company) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	public Company loginCompany(String id, String pass) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	public void updateCompany(Company company) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	public void deleteCompany(String id, String pass) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	public Company lookupCompany(String id) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	public ArrayList<Company> showAllCompany() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	public ArrayList<Company> showAllCompanyByCategory(Comcategory category) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	public void insertService(Service service) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	public void deleteService(String code) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	public ArrayList<Service> showAllService(String companycode) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	public void insertQuestion(Question question) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	public ArrayList<Question> showAllQuestion(String id) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	public void insertAnswer(Answer answer) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	public ArrayList<Answer> showAllAnswer(String id) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	public void insertReview(Review review) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	public ArrayList<Review> showAllReview(String id) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

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
		
	}
}
