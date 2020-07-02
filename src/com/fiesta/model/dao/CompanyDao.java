package com.fiesta.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.fiesta.model.vo.Comcategory;
import com.fiesta.model.vo.Company;
import com.fiesta.model.vo.Review;
import com.fiesta.model.vo.Service;

public interface CompanyDao {

	public Connection getConnection() throws SQLException;
	public void closeAll(PreparedStatement ps, Connection conn) throws SQLException;
	public void closeAll(ResultSet rs, PreparedStatement ps, Connection conn) throws SQLException;
	
	public ArrayList<Comcategory> showAllComcategory() throws SQLException;
	public ArrayList<Company> showMainCompanyByCategory(int comCategory) throws SQLException;
	public Company detailViewCompany(int comCode) throws SQLException;
	public ArrayList<Review> showAllCompany() throws SQLException;
	public ArrayList<Review> showAllCompanyByCategory(int category) throws SQLException;
	public Company lookupCompany(String comEmail) throws SQLException;
	public ArrayList<Review> lookupCompany(String searchBy, String searchContent) throws SQLException;
	public ArrayList<Review> lookupCompany(int category, String searchBy, String searchContent) throws SQLException;
	
	public ArrayList<Review> sortCompany(String sortBy) throws SQLException;
	public ArrayList<Review> sortCompany(int category, String sortBy) throws SQLException;
	public ArrayList<Review> sortCompany(String searchBy, String searchContent, String sortBy) throws SQLException;
	public ArrayList<Review> sortCompany(int category, String searchBy, String searchContent, String sortBy) throws SQLException;
	
	public void insertService(Service service) throws SQLException;
	public void deleteService(int code) throws SQLException;
	public ArrayList<Service> showAllService(int companycode) throws SQLException;
	public Service showService(int servicecode) throws SQLException;
	public ArrayList<Service> showAllServiceByCompany(int companycode) throws SQLException;
	
	public void plusCount(int comCode) throws SQLException;
}
