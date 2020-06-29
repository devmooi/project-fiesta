package com.fiesta.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.fiesta.model.vo.*;

public interface FiestaDao {
	Connection getConnection() throws SQLException;
	void closeAll(PreparedStatement ps, Connection conn) throws SQLException;
	void closeAll(ResultSet rs, PreparedStatement ps, Connection conn) throws SQLException;
	
	//의근
	//회원 등록
	void registerCustomer(Customer customer) throws SQLException;
	//회원 로그인
	Customer loginCustomer(String custEmail, String pass) throws SQLException; 
	//회원 정보 수정
	void updateCustomer(Customer customer) throws SQLException;
	//회원 탈퇴
	void deleteCustomer(String custEmail, String pass) throws SQLException;
	//회원 정보 조회
	Customer lookupCustomer(String custEmail) throws SQLException;
	
	//고객 의뢰
	void insertCustOrder(Custorder custorder) throws SQLException;
	//고객 의뢰 확정
	void insertCustOrderDetail(Custrequestdetail orderdetail) throws SQLException;
	//고객 의뢰한 모든 내용 조회
	ArrayList<Custorder> showAllCustOrder(String custEmail) throws SQLException;
	//고객 의뢰 확정 내용 조회
	ArrayList<Custrequestdetail> showAllOrderDetail(String requestCode) throws SQLException; 
	
	//회사 등록
	void registerCompany(Company company) throws SQLException;
	//회사 로그인
	Company loginCompany(String comEmail, String pass) throws SQLException;
	//회사 정보 수정
	void updateCompany(Company company) throws SQLException;
	//회사 탈퇴
	void deleteCompany(String comEmail, String pass) throws SQLException;
	
	//제영
	//회사 정보 조회
	Company lookupCompany(String id) throws SQLException;
	//모든 회사 조회
	ArrayList<Company> showAllCompany() throws SQLException;
	//카테고리별 회사 조회
	ArrayList<Company> showAllCompanyByCategory(Comcategory category) throws SQLException; 
	
	//하경
	//서비스 등록
	void insertService(Service service) throws SQLException;
	//서비스 삭제
	void deleteService(int code) throws SQLException;
	//서비스 회사별 모든 조회
	ArrayList<Service> showAllService(int companycode) throws SQLException;
	
	//하경
	//고객 문의
	void insertQuestion(Question question) throws SQLException;
	//고객 문의 내역
	ArrayList<Question> showAllQuestion(String id) throws SQLException;
	//회사 답변
	void insertAnswer(Answer answer) throws SQLException;
	//회사 답변 내역
	ArrayList<Answer> showAllAnswer(String id) throws SQLException;
	
	//제영
	//고객 리뷰 작성
	void insertReview(Review review) throws SQLException;
	//고객 리뷰 확인
	ArrayList<Review> showAllReview(String id) throws SQLException;
	
	//하경
	//고객 찜
	void insertWish(Wish wish) throws SQLException;
	//고객 찜 확인
	ArrayList<Wish> showAllWish(String id) throws SQLException;
	//고객 찜 삭제
	void deleteWish(Wish wish) throws SQLException;
	
}
