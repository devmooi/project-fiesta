package com.fiesta.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.fiesta.model.vo.Wish;

public interface WishDao {
	public Connection getConnection() throws SQLException;
	public void closeAll(PreparedStatement ps, Connection conn) throws SQLException;
	public void closeAll(ResultSet rs, PreparedStatement ps, Connection conn) throws SQLException;
	
	public String insertWish(String custEmail, int comCode) throws SQLException;
	public void deleteWish(int wishCode, String custEmail) throws SQLException;
	public boolean wishExist(int comCode) throws SQLException;
	public ArrayList<Wish> showAllWish(String custEmail) throws SQLException;
	
}



