package com.fiesta.model.dao;

import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import com.fiesta.model.vo.Company;
import com.fiesta.model.vo.Customer;

public interface RegisterDao {
	public Connection getConnection() throws SQLException;
	public void closeAll(PreparedStatement ps, Connection conn) throws SQLException;
	public void closeAll(ResultSet rs, PreparedStatement ps, Connection conn) throws SQLException;
	
	//Login
	public Customer loginCustomer(String custEmail, String pass) throws SQLException, UnsupportedEncodingException, 
	InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidAlgorithmParameterException, 
	IllegalBlockSizeException, BadPaddingException;
	public Company loginCompany(String comEmail, String pass) throws SQLException, UnsupportedEncodingException, 
	InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidAlgorithmParameterException, 
	IllegalBlockSizeException, BadPaddingException;
	public void updatePassword(String email, String pass, String pick) throws SQLException;
	
	//Customer
	public void registerCustomer(Customer customer) throws SQLException;
	public void updateCustomer(Customer customer) throws SQLException;
	public void deleteCustomer(String custEmail, String pass) throws SQLException;
	public Customer lookupCustomer(String custEmail) throws SQLException;
	public boolean custEmailExist(String custEmail) throws SQLException;
	
	//Company
	public void registerCompany(Company company) throws SQLException;
	public void updateCompany(Company company) throws SQLException;
	public void deleteCompany(String comEmail, String pass) throws SQLException;
	public boolean comEmailExist(String comEmail) throws SQLException;
}


