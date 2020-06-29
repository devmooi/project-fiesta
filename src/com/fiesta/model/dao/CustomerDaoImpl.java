package com.fiesta.model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.sql.DataSource;

import com.fiesta.model.vo.Custorder;
import com.fiesta.model.vo.Custorderdetail;
import com.fiesta.model.vo.Custrequest;
import com.fiesta.model.vo.Custrequestdetail;
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
	//VO 수정으로 인한 변경
	public void insertCustOrder(Custorder custorder) throws SQLException {
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			conn = getConnection();
			String query = "INSERT INTO custorder (order_sysdate, order_revdate, order_place, order_budget, order_require, cust_email, service_code, com_code) VALUES(?,?,?,?,?,?,?,?)";
			ps = conn.prepareStatement(query);
			System.out.println("ps completed in insertCustorder");
			
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
	public void insertCustOrderDetail(Custorderdetail custorderdetail) throws SQLException {
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			conn = getConnection();
			String query = "INSERT INTO custorderdetail (custdetail_totalprice, custdetail_desc, custdetail_completedate, order_code, service_code, com_code, cust_email) VALUES(?,?,?,?,?,?,?)";
			ps = conn.prepareStatement(query);
			System.out.println("ps completed in insertOrderdetail");
			
			ps.setInt(1, custorderdetail.getCustdetailTotalprice());
			ps.setString(2, custorderdetail.getCustdetailDesc());
			ps.setString(3, custorderdetail.getCustdetailCompletedate());
			ps.setInt(4, custorderdetail.getOrderCode());
			ps.setInt(5, custorderdetail.getServiceCode());
			ps.setInt(6, custorderdetail.getComCode());
			ps.setString(7, custorderdetail.getCustEmail());
			System.out.println(ps.executeUpdate()+" row insert success");
		} finally {
			closeAll(ps, conn);
		}				
	}
	
	//VO 수정으로 인한 변경
	public ArrayList<Custorder> showAllCustOrder(int orderCode) throws SQLException {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		ArrayList<Custorder> orderlist = new ArrayList<>();
		try {
			conn = getConnection();
			String query = "SELECT * FROM custorder WHERE order_code=?";
			ps = conn.prepareStatement(query);
			System.out.println("ps completed in showAllCustorder");
			
			ps.setInt(1, orderCode);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				orderlist.add(new Custorder(
									   orderCode,
									   rs.getString("order_sysdate"),
									   rs.getString("order_revdate"),
									   rs.getString("order_place"),
									   rs.getString("order_budget"),
									   rs.getString("order_require"),
									   rs.getString("order_condition"),
									   rs.getString("cust_email"),
									   rs.getInt("service_code"),
									   rs.getInt("com_code")));
			System.out.println(orderCode+ " showallcustorder success");
			}
		} finally {
			closeAll(rs, ps, conn);
		}
		return orderlist;
	}
	
	
	
	
	//foreign key...?
	public ArrayList<Custorderdetail> showAllCustOrderDetail(int custdetailCode) throws SQLException {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		ArrayList<Custorderdetail> list = new ArrayList<>();
		try {
			conn = getConnection();
			String query = "SELECT * FROM custorderdetail WHERE custdetail_code=?";
			ps = conn.prepareStatement(query);
			System.out.println("ps completed in showAllOrderdetail");
			
			ps.setInt(1, custdetailCode);
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
	
	
	public void insertCustRequest(Custrequest custrequest) throws SQLException {
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			conn = getConnection();
			String query = "INSERT INTO custrequest (request_sysdate, request_revdate, request_place, request_budget, request_require, request_fiesta, cust_email) VALUES(?,?,?,?,?,?,?)";
			ps = conn.prepareStatement(query);
			System.out.println("ps completed in insertCustrequest");
			
			ps.setString(1, custrequest.getRequestSysdate());
			ps.setString(2, custrequest.getRequestRevdate());
			ps.setString(3, custrequest.getRequestPlace());
			ps.setString(4, custrequest.getRequestBudget());
			ps.setString(5, custrequest.getRequestRequire());
			ps.setString(6, custrequest.getRequestFiesta());
			ps.setString(7, custrequest.getCustEmail());
			System.out.println(ps.executeUpdate()+" row insert success");
		} finally {
			closeAll(ps, conn);
		}		
	}
	
	public void insertCustRequestDetail(Custrequestdetail custrequestdetail) throws SQLException {
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			conn = getConnection();
			String query = "INSERT INTO custrequestdetail (detail_totalprice, detail_desc, detail_condition, detail_completedate, request_code, com_code) VALUES(?,?,?,?,?,?)";
			ps = conn.prepareStatement(query);
			System.out.println("ps completed in insertCustRequestDetail");
				
			ps.setInt(1, custrequestdetail.getDetailTotalprice());
			ps.setString(2, custrequestdetail.getDetailDesc());
			ps.setString(3, custrequestdetail.getDetailCondition());
			ps.setString(4, custrequestdetail.getDetailCompletedate());
			ps.setInt(5, custrequestdetail.getRequestCode());
			ps.setInt(6, custrequestdetail.getComCode());
			System.out.println(ps.executeUpdate()+" row insert success");
			} finally {
				closeAll(ps, conn);
			}				
		}
	
	public ArrayList<Custrequest> showAllCustRequest(int requestCode) throws SQLException {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		ArrayList<Custrequest> requestlist = new ArrayList<>();
		try {
			conn = getConnection();
			String query = "SELECT * FROM custrequest WHERE request_code=?";
			ps = conn.prepareStatement(query);
			System.out.println("ps completed in showAllCustrequest");
			
			ps.setInt(1, requestCode);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				requestlist.add(new Custrequest(
									   requestCode,
									   rs.getString("request_sysdate"),
									   rs.getString("request_revdate"),
									   rs.getString("request_place"),
									   rs.getString("request_budget"),
									   rs.getString("request_require"),
									   rs.getString("request_fiesta"),
									   rs.getString("cust_email")));
			System.out.println(requestCode+ " showallcustrequest success");
			}
		} finally {
			closeAll(rs, ps, conn);
		}
		return requestlist;
	}
	
	//foreign key...?
		public ArrayList<Custrequestdetail> showAllCustRequestDetail(int detailCode) throws SQLException {
			Connection conn = null;
			PreparedStatement ps = null;
			ResultSet rs = null;
			ArrayList<Custrequestdetail> list = new ArrayList<>();
			try {
				conn = getConnection();
				String query = "SELECT * FROM custrequestdetail WHERE detail_code=?";
				ps = conn.prepareStatement(query);
				System.out.println("ps completed in showAllRequestdetail");
				
				ps.setInt(1, detailCode);
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
		//dao.insertCustOrder(new Custorder("1119-02-17","1212","홍천","10000","빨리","encore@gmail.com",1,1));
		//dao.insertCustRequest(new Custrequest("1119-02-17","1212","홍천","10000","빨리","부가","encore@gmail.com"));
		//dao.insertCustOrderDetail(new Custorderdetail(1000, "상세", "19191010", 3, 1, 1, "encore@gmail.com"));
		//dao.insertCustRequestDetail(new Custrequestdetail(1000, "상세", "진행", "19191010", 1, 1));
		//dao.showAllCustOrder(3);
		dao.showAllCustRequest(1);
	}
}
