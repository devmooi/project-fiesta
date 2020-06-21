package com.fiesta.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.sql.DataSource;

import com.fiesta.model.vo.Item;
import com.fiesta.util.DataSourceManager;
import com.fiesta.util.ServerInfo;

public class ItemDaoImpl {
	private DataSource ds;

	private static ItemDaoImpl dao = new ItemDaoImpl();
	private ItemDaoImpl() {
		//ds = DataSourceManager.getInstance().getConnection();

		//단위테스트
		try {
			Class.forName(ServerInfo.DRIVER_NAME);
		} catch (ClassNotFoundException e) {
			System.out.println("ItemDao :: " + e);
		}
	}
	public static ItemDaoImpl getInstance() {
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

	//비즈니스 로직 :: 주석에 이름 표기해주세요!
	public ArrayList<Item> getAllItem() throws SQLException{
		ArrayList<Item> list = new ArrayList<>();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			conn = getConnection();
			String query = "SELECT * FROM item";
			ps = conn.prepareStatement(query);
			rs = ps.executeQuery();
			while(rs.next()) {
				list.add(new Item(
						rs.getInt(1), 
						rs.getString(2), 
						rs.getInt(3), 
						rs.getString(4), 
						rs.getString(5), 
						rs.getInt(6)));
			}
		} finally {
			closeAll(rs, ps, conn);
		}

		return list;
	}

	//단위테스트
	public static void main(String[] args) throws SQLException {
		ArrayList<Item> list = ItemDaoImpl.getInstance().getAllItem();
		for(Item l : list) {
			System.out.println(l);
		}
	}
}