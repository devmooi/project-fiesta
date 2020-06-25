package com.fiesta.model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.sql.DataSource;

import com.fiesta.model.vo.Custorder;
import com.fiesta.model.vo.Orderdetail;
import com.fiesta.util.ServerInfo;

public class CustomerDaoImpl {
	private DataSource ds;
	
	private static CustomerDaoImpl dao = new CustomerDaoImpl();
	private CustomerDaoImpl() {
		//ds = DataSourceManager.getInstance().getConnection();
		
		//단위테스트
		try {
			Class.forName(ServerInfo.DRIVER_NAME);
		} catch (ClassNotFoundException e) {
			System.out.println("CustomerDaoImpl :: " + e);
		}
	}
	public static CustomerDaoImpl getInstance() {
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
	public void insertCustorder(Custorder custorder) throws SQLException {
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			conn = getConnection();
			String query = "INSERT INTO custorder (order_code, order_sysdate, order_revdate, order_place, order_budget, order_require, order_condition, cust_id) VALUES(?,?,?,?,?,?,?,?)";
			ps = conn.prepareStatement(query);
			//System.out.println("ps completed in insertCustorder");
			
			ps.setInt(1, custorder.getOrderCode());
			ps.setString(2, custorder.getOrderSysdate());
			ps.setString(3, custorder.getOrderRevdate());
			ps.setString(4, custorder.getOrderPlace());
			ps.setString(5, custorder.getOrderBudget());
			ps.setString(6, custorder.getOrderRequire());
			//ps.setString(7, custorder.getOrderCondition());
			//ps.setString(8, custorder.getCustId());
			System.out.println(ps.executeUpdate()+" row insert success");
		} finally {
			closeAll(ps, conn);
		}		
	}
	
	public void insertOrderdetail(Orderdetail orderdetail) throws SQLException {
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
	
	public ArrayList<Orderdetail> showAllOrderdetail(String id) throws SQLException {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		ArrayList<Orderdetail> list = new ArrayList<>();
		try {
			conn = getConnection();
			String query = "SELECT * FROM orderdetail WHERE order_code=?";
			ps = conn.prepareStatement(query);
			System.out.println("ps completed in showAllOrderdetail");
			
			ps.setString(1, id);
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
	
	//단위테스트
	public static void main(String[] args) throws SQLException {
		CustomerDaoImpl dao = CustomerDaoImpl.getInstance();
		
	}
}
