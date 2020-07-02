package com.fiesta.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.fiesta.model.vo.Custorder;
import com.fiesta.model.vo.Custorderdetail;
import com.fiesta.model.vo.Custrequest;
import com.fiesta.model.vo.Custrequestdetail;

public interface CustomerDao {
	public Connection getConnection() throws SQLException;
	public void closeAll(PreparedStatement ps, Connection conn) throws SQLException;
	public void closeAll(ResultSet rs, PreparedStatement ps, Connection conn) throws SQLException;
	
	public void insertCustOrder(Custorder custorder) throws SQLException;
	public ArrayList<Custorder> showAllCustOrder(String custEmail) throws SQLException;
	public Custorder showCustOrder(int custorderCode) throws SQLException;
	public ArrayList<Custorder> showAllCustOrderByCompany(int comcode) throws SQLException;
	public ArrayList<Custorderdetail> showAllCustOrderDetail(String custEmail) throws SQLException;
	public ArrayList<Custorderdetail> showAllCustOrderDetailByCompany(int comCode) throws SQLException;
	public void changeOrderCondition(int orderCode, String condition) throws SQLException;
	
	public void insertCustRequest(Custrequest custrequest, String custEmail) throws SQLException;
	public void insertCustRequestDetail(Custrequestdetail custrequestdetail) throws SQLException;
	public ArrayList<Custrequest> showAllCustRequest(String custEmail) throws SQLException;
	public ArrayList<Custrequestdetail> showAllCustRequestDetail(String custEmail) throws SQLException;
	
}
