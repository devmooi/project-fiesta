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

import com.fiesta.model.vo.Comcategory;
import com.fiesta.model.vo.Custorder;
import com.fiesta.model.vo.Custorderdetail;
import com.fiesta.model.vo.Custrequest;
import com.fiesta.model.vo.Custrequestdetail;
import com.fiesta.util.ServerInfo;

public class CustomerDaoImpl implements CustomerDao {
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
	//고객주문
	public void insertCustOrder(Custorder custorder) throws SQLException {
		Connection conn = null;
		PreparedStatement ps = null;
		
		try{
			conn=  getConnection();
			String query = "INSERT INTO custorder(order_sysdate, order_revdate, order_place, order_budget, order_require, cust_email, service_code, com_code) "
							+ "VALUES(sysdate(), ?, ?, ?, ?, ?, ?, ?)";
			ps = conn.prepareStatement(query);
			System.out.println("PreparedStatement 생성됨...insertCustOrder");
			
			//예약날짜, 예약장소, 예산, 요구사항, 고객이메일, 서비스코드, 컴코드
			ps.setString(1, custorder.getOrderRevdate());
			ps.setString(2, custorder.getOrderPlace());
			ps.setString(3, custorder.getOrderBudget());
			ps.setString(4, custorder.getOrderRequire());
			ps.setString(5, custorder.getCustEmail());//세션의 고객아이디 값 가져오기
			ps.setInt(6, custorder.getServiceCode());
			ps.setInt(7, custorder.getComCode());//기업코드값 가져오기

			System.out.println(ps.executeUpdate()+" row INSERT OK!!");
		}finally{
			closeAll(ps, conn);
		}		
	}

	//VO 수정으로 인한 변경
	//고객이 주문 완료 됐을 때 나오는 의뢰서
	public void insertCustOrderDetail(Custorderdetail custorderdetail) throws SQLException {
		Connection conn = null;
		PreparedStatement ps = null;
		
		//현재시간 출력
		SimpleDateFormat format = new SimpleDateFormat ( "yyyy-MM-dd HH:mm:ss");
		Calendar time = Calendar.getInstance();
		String currTime = format.format(time.getTime());
		//System.out.println(currTime); 확인용
		
		try{
			conn=  getConnection();
			String query = "INSERT INTO custorderdetail(custdetail_totalprice, custdetail_desc, custdetail_completedate, order_code, service_code, com_code, cust_email) "
					+ "VALUES(?,?,?,?,?,?,?)";
			ps = conn.prepareStatement(query);
			System.out.println("PreparedStatement 생성됨...insertCustOrderDetail");
			
			ps.setInt(1, custorderdetail.getCustdetailTotalprice());
			ps.setString(2, custorderdetail.getCustdetailDesc());
			ps.setString(3, currTime);
			ps.setInt(4, custorderdetail.getOrderCode());
			ps.setInt(5, custorderdetail.getServiceCode());
			ps.setInt(6, custorderdetail.getComCode());
			ps.setString(7, custorderdetail.getCustEmail());

			System.out.println(ps.executeUpdate()+" row INSERT OK!!");
		}finally{
			closeAll(ps, conn);
		}		
	}
	
	//VO 수정으로 인한 변경
	//자기가 쓴 의뢰내역 모두보기
	public ArrayList<Custorder> showAllCustOrder(String custEmail) throws SQLException {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		ArrayList<Custorder> list = new ArrayList<>();
		try {
			conn = getConnection();			
			String query = "SELECT c.order_code, c.order_sysdate, c.order_condition, c.cust_email, c.service_code, s.service_name "
					 + "FROM custorder c, service s "
					 + "WHERE c.service_code = s.service_code and c.cust_email=?";
			ps = conn.prepareStatement(query);
			System.out.println("PreparedStatement 생성됨...showAllCustOrder");
			
			ps.setString(1, custEmail);
			rs = ps.executeQuery();

			while(rs.next()) {
				list.add(new Custorder(
									   rs.getInt("c.order_code"),
									   rs.getInt("c.service_code"),
									   rs.getString("s.service_name"),
									   rs.getString("c.order_sysdate"),
									   rs.getString("c.order_condition"),
									   rs.getString("c.cust_email")));
			}
		} finally {
			closeAll(rs, ps, conn);
		}
		return list;
	}
	
	//고객이 주문한 서비스 자세히 보기
	public Custorder showCustOrder(int custorderCode) throws SQLException {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Custorder custOrder = null;
		try {
			conn = getConnection();
			String query = "SELECT order_code, order_sysdate, order_revdate, order_place, order_budget, order_require, order_condition, cust_email, service_code "
						 + "FROM custorder WHERE order_code=?";
			ps = conn.prepareStatement(query);
			System.out.println("PreparedStatement 생성됨...showCustOrder");
			
			ps.setInt(1, custorderCode);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				custOrder = new Custorder(
									   rs.getInt("order_code"),
									   rs.getString("order_sysdate"),
									   rs.getString("order_revdate"),
									   rs.getString("order_place"),
									   rs.getString("order_budget"),
									   rs.getString("order_require"),
									   rs.getString("order_condition"),
									   rs.getString("cust_email"),
									   rs.getInt("service_code"));
			}
		} finally {
			closeAll(rs, ps, conn);
		}
		return custOrder;
	}
	
	//회사가 보는 의뢰내역
	public ArrayList<Custorder> showAllCustOrderByCompany(int comcode) throws SQLException {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		ArrayList<Custorder> list = new ArrayList<>();
		try {
			conn = getConnection();

			String query = "SELECT c.order_code, c.order_sysdate, c.order_condition, c.cust_email, c.service_code, s.service_name "
						 + "FROM custorder c, service s "
						 + "WHERE c.service_code = s.service_code and c.com_code=?";
			ps = conn.prepareStatement(query);
			System.out.println("PreparedStatement 생성됨...showAllCustOrderByCompany");
			
			ps.setInt(1, comcode);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				list.add(new Custorder(
									   rs.getInt("c.order_code"),
									   rs.getInt("c.service_code"),
									   rs.getString("s.service_name"),
									   rs.getString("c.order_sysdate"),
									   rs.getString("c.order_condition"),
									   rs.getString("c.cust_email")));
			}
		} finally {
			closeAll(rs, ps, conn);
		}
		return list;
	}
	
	//승인된 최종내역들 다보기 고객이
	public ArrayList<Custorderdetail> showAllCustOrderDetail(String custEmail) throws SQLException {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		ArrayList<Custorderdetail> list = new ArrayList<>();
		try {
			conn = getConnection();
			String query = "SELECT * "
							+ "FROM custorderdetail WHERE cust_email=?";
			ps = conn.prepareStatement(query);
			System.out.println("PreparedStatement 생성됨...showAllCustOrderDetail");
			
			ps.setString(1, custEmail);
			rs = ps.executeQuery();
			while(rs.next()) {
				list.add(new Custorderdetail(
										   rs.getInt("custdetail_code"),
										   rs.getInt("custdetail_totalprice"),
										   rs.getString("custdetail_desc"),
										   rs.getString("custdetail_completedate"),
										   rs.getInt("order_code"),
										   rs.getInt("service_code"),
										   rs.getInt("com_code"),
										   rs.getString("cust_email")));
			}
		} finally {
			closeAll(rs, ps, conn);
		}
		return list;
	}
	
	//승인된 최종내역들 다보기 회사가
	public ArrayList<Custorderdetail> showAllCustOrderDetailByCompany(int comCode) throws SQLException {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		ArrayList<Custorderdetail> list = new ArrayList<>();
		try {
			conn = getConnection();
			String query = "SELECT * "
							+ "FROM custorderdetail WHERE com_code=?";
			ps = conn.prepareStatement(query);
			System.out.println("PreparedStatement 생성됨...showAllCustOrderDetailByCompany");

			ps.setInt(1, comCode);
			rs = ps.executeQuery();
			while(rs.next()) {
				list.add(new Custorderdetail(
									   rs.getInt("custdetail_code"),
									   rs.getInt("custdetail_totalprice"),
									   rs.getString("custdetail_desc"),
									   rs.getString("custdetail_completedate"),
									   rs.getInt("order_code"),
									   rs.getInt("service_code"),
									   rs.getInt("com_code"),
									   rs.getString("cust_email")));
			}
		} finally {
			closeAll(rs, ps, conn);
		}
		return list;
	}
	
	//답변상태 바꾸기 답변대기를 답변완료로
		public void changeOrderCondition(int orderCode, String condition) throws SQLException{
			Connection conn = null;
			PreparedStatement ps = null;
			
			try{
				conn=  getConnection();
				String query = "Update custorder set order_condition=? WHERE order_code=?";
				ps = conn.prepareStatement(query);
				System.out.println("PreparedStatement 생성됨...changeOrderCondition");
				
				ps.setString(1, condition);
				ps.setInt(2, orderCode);  
				
				// 답변대기를 답변완료로 바꾸기, 이메일로 보내기
				
				System.out.println(ps.executeUpdate()+" row update OK!!");
			}finally{
				closeAll(ps, conn);
			}
		}
	
//////////////////////////////////////////////////////////////////
	//고객요청
	public void insertCustRequest(Custrequest custrequest, String custEmail) throws SQLException {
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			conn = getConnection();
			String query = "INSERT INTO custrequest (request_sysdate, request_revdate, request_place, request_budget, request_require, request_fiesta, cust_email) " 
			                + "VALUES(sysdate(), ?, ?, ?, ?, ?, ?)";
			//예약날짜, 예약장소, 예산, 요구사항, 카테고리, 고객이메일
			
			ps = conn.prepareStatement(query);
			System.out.println("ps completed in insertCustrequest");
			
			ps.setString(1, custrequest.getRequestRevdate());
			ps.setString(2, custrequest.getRequestPlace());
			ps.setString(3, custrequest.getRequestBudget());
			ps.setString(4, custrequest.getRequestRequire());
			ps.setString(5, custrequest.getRequestFiesta());
			ps.setString(6, custEmail);
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
	
	public ArrayList<Custrequest> showAllCustRequest(String custEmail) throws SQLException {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		ArrayList<Custrequest> list = new ArrayList<>();
		try {
			conn = getConnection();
			String query = "SELECT request_code, request_sysdate, request_revdate, request_place, request_budget, request_require, "
					+ "comCategory_code, comCategory_desc from custrequest as cu, comcategory as co "
					+ "WHERE cu.request_fiesta = co.comCategory_code AND cust_email=?";
			ps = conn.prepareStatement(query);
			System.out.println("ps completed in showAllCustrequest");
			
			ps.setString(1, custEmail);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				Custrequest custrequest = new Custrequest();
				custrequest.setComcategory(new Comcategory(rs.getInt("comCategory_code"), rs.getString("comCategory_desc")));
				custrequest.setCustEmail(custEmail);
				custrequest.setRequestCode(rs.getInt("request_code"));
				custrequest.setRequestBudget(rs.getString("request_budget"));
				custrequest.setRequestPlace(rs.getString("request_place"));
				custrequest.setRequestRequire(rs.getString("request_require"));
				custrequest.setRequestRevdate(rs.getString("request_revdate"));
				custrequest.setRequestSysdate(rs.getString("request_sysdate"));
				list.add(custrequest);
			System.out.println(custEmail+ " showallcustrequest success");
			}
		} finally {
			closeAll(rs, ps, conn);
		}
		return list;
	}
	
	//회사가 볼때는 업체 카테고리에 해당하는 걸 가져오기
	public ArrayList<Custrequest> showAllCustRequestByCompany(String comCategoryCode) throws SQLException {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		ArrayList<Custrequest> list = new ArrayList<>();
		try {
			conn = getConnection();
			String query = "SELECT * FROM custrequest WHERE request_fiesta=?";
			ps = conn.prepareStatement(query);
			System.out.println("ps completed in showAllCustrequest");
			
			ps.setString(1, comCategoryCode);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				Custrequest custrequest = new Custrequest();
				custrequest.setCustEmail(rs.getString("cust_email"));
				custrequest.setRequestCode(rs.getInt("request_code"));
				custrequest.setRequestBudget(rs.getString("request_budget"));
				custrequest.setRequestPlace(rs.getString("request_place"));
				custrequest.setRequestRequire(rs.getString("request_require"));
				custrequest.setRequestRevdate(rs.getString("request_revdate"));
				custrequest.setRequestSysdate(rs.getString("request_sysdate"));
				list.add(custrequest);
			System.out.println(" showallcustrequest success");
			}
		} finally {
			closeAll(rs, ps, conn);
		}
		return list;
	}
	
	public ArrayList<Custrequestdetail> showAllCustRequestDetail(String custEmail) throws SQLException {
			Connection conn = null;
			PreparedStatement ps = null;
			ResultSet rs = null;
			ArrayList<Custrequestdetail> list = new ArrayList<>();
			try {
				conn = getConnection();
				String query = "SELECT d.detail_totalprice, d.detail_desc, d.detail_condition, d.detail_completedate "
							+ "FROM custrequestdetail d, custrequest r "
							+ "WHERE d.request_code = r.request_code "
							+ "AND cust_email=?";
				ps = conn.prepareStatement(query);
				System.out.println("ps completed in showAllRequestdetail");
				
				ps.setString(1, custEmail);
				rs = ps.executeQuery();
				while(rs.next()) {
					list.add(new Custrequestdetail(
												   rs.getInt("d.detail_totalprice"),
												   rs.getString("d.detail_desc"),
												   rs.getString("d.detail_condition"),
												   rs.getString("d.detail_completedate")));
				System.out.println(custEmail+ " showallrequestdetail success");
				}
			} finally {
				closeAll(rs, ps, conn);
			}
			return list;
		}
	

	
	public Custrequest showCustRequest(int requestCode) throws SQLException {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Custrequest custRequest = null;

		try {
			conn = getConnection();
			String query = "SELECT request_code, request_sysdate, request_revdate, request_place, request_budget, request_require, request_fiesta, cust_email "
						 + "FROM custrequest WHERE request_code=?";
			ps = conn.prepareStatement(query);
			System.out.println("PreparedStatement 생성됨...showCustOrder");
			
			ps.setInt(1, requestCode);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				custRequest = new Custrequest(rs.getInt("request_code"),
											rs.getString("request_sysdate"),
											rs.getString("request_revdate"),
											rs.getString("request_place"),
											rs.getString("request_budget"),
											rs.getString("request_require"),
											rs.getString("request_fiesta"),
											rs.getString("cust_email"));
			}
		} finally {
			closeAll(rs, ps, conn);
		}
		return custRequest;
	}
	
	//이부분도 추가! 잘 확인하기
	public ArrayList<Custrequestdetail> showAllCustRequestDetailByCompany(int companycode) {
		// TODO Auto-generated method stub
		return null;
	}
	
	//이부분도 추가! 잘 확인하기 .. 여긴 그냥 삭제하면 됨~~ 회사가 걸린 것도 없고 그냥 고객이 취소하는 거라
	public void changeRequestCondition(int requestCode) throws SQLException {
		Connection conn = null;
		PreparedStatement ps = null;
		
		try{
			conn=  getConnection();
			String query = "DELETE FROM custrequest WHERE request_code=?";
			ps = conn.prepareStatement(query);
			System.out.println("PreparedStatement 생성됨...changeOrderCondition");
			
			ps.setInt(1, requestCode);
			System.out.println(ps.executeUpdate()+" row update OK!!");
		}finally{
			closeAll(ps, conn);
		}
		
	}
	
	//단위테스트
	public static void main(String[] args) throws SQLException {
		CustomerDaoImpl dao = CustomerDaoImpl.getInstance();
		//dao.insertCustOrder(new Custorder("1119-02-17","1212","홍천","10000","빨리","encore@gmail.com",1,1));
		//dao.insertCustRequest(new Custrequest("1119-02-17","1212","홍천","10000","빨리","부가","encore@gmail.com"));
		//dao.insertCustOrderDetail(new Custorderdetail(1000, "상세", 1, 25, 1, "encore@gmail.com"));
		//dao.insertCustOrderDetail(new Custorderdetail(1000, "상세", "19191010", 3, 1, 1, "encore@gmail.com"));
		//dao.insertCustRequestDetail(new Custrequestdetail(1000, "상세", "진행", "19191010", 1, 1));
		//dao.showAllCustOrder(3);
		//dao.showAllCustRequest(1);
		// dao.showAllCustOrder("encore@gmail.com");
		//dao.showAllCustRequest("encore@gmail.com");
		// dao.showAllCustRequestDetail("encore@gmail.com");
		//dao.showAllCustRequestDetail("encore@gmail.com");
		
		
	}
	
	
	
	
}
