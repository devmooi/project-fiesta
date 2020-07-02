package com.fiesta.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.fiesta.model.vo.Review;

public interface ReviewDao {
	public Connection getConnection() throws SQLException;
	public void closeAll(PreparedStatement ps, Connection conn) throws SQLException;
	public void closeAll(ResultSet rs, PreparedStatement ps, Connection conn) throws SQLException;
	
	public boolean isReview(int comCode, int serviceCode) throws SQLException;
	public boolean isAnswer(String reviewCode) throws SQLException;
	
	public void insertReview(Review review) throws SQLException;
	public void deleteReview(String reviewCode) throws SQLException;
	public ArrayList<Review> showAllReviewByCompany(int comCode) throws SQLException;
	public ArrayList<Review> showAllReviewByCustomer(String email) throws SQLException;
	public ArrayList<Review> showAllAnswerByCustomer(String email) throws SQLException;
	public ArrayList<Review> showAllAnswerByCompany(int comCode) throws SQLException;
	public Review showReview(String reviewCode) throws SQLException;
	public Review showReview(int comCode) throws SQLException;
	public int lastReviewCode(int comCode) throws SQLException;
	
	//추천 알고리즘
	public ArrayList<String> getCustArray() throws SQLException;
	public ArrayList<String> getServiceArray() throws SQLException;
	public int[] countRowAndCol() throws SQLException;
	public int[][] getReviewMatrix() throws SQLException;
	public String[] getRecoCompany(int[][] matrix, String who) throws SQLException;
	
}



