package com.fiesta.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.fiesta.model.vo.Answer;
import com.fiesta.model.vo.Question;

public interface QuestionDao {
	public Connection getConnection() throws SQLException;
	public void closeAll(PreparedStatement ps, Connection conn) throws SQLException;
	public void closeAll(ResultSet rs, PreparedStatement ps, Connection conn) throws SQLException;
	
	public void insertQuestion(int comCode, String title, String desc, String custEmail) throws SQLException;
	public void deleteQuestion(int qCode) throws SQLException;
	public ArrayList<Question> showAllQuestion(String custEmail) throws SQLException;
	public Question showQuestion(int qCode) throws SQLException;
	public ArrayList<Question> showAllQuestionByCompany(int comCode) throws SQLException;
	
	public void insertAnswer(int qCode, String aDesc, int comCode) throws SQLException;
	public void deleteAnswer(int answerqCode) throws SQLException;
	public Answer showAnswer(int qCode) throws SQLException;
	public void changeQnaCondition(int qCode, String condition) throws SQLException;
	
	
}


