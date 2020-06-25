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

import com.fiesta.model.vo.Answer;
import com.fiesta.model.vo.Company;
import com.fiesta.model.vo.Question;
import com.fiesta.util.ServerInfo;

public class QuestionDaoImpl {
	private DataSource ds;
	
	private static QuestionDaoImpl dao = new QuestionDaoImpl();
	private QuestionDaoImpl() {
		//ds = DataSourceManager.getInstance().getConnection();
		
		//단위테스트
		try {
			Class.forName(ServerInfo.DRIVER_NAME);
		} catch (ClassNotFoundException e) {
			System.out.println("QuestionDaoImpl :: " + e);
		}
	}
	public static QuestionDaoImpl getInstance() {
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
	public void insertQuestion(int comCode, String title, String desc, String custEmail) throws SQLException {
		Connection conn = null;
		PreparedStatement ps = null;
		
		//현재시간 출력
		SimpleDateFormat format = new SimpleDateFormat ( "yyyy-MM-dd HH:mm:ss");
		Calendar time = Calendar.getInstance();
		String currTime = format.format(time.getTime());
		//System.out.println(currTime); 확인용
		
		try{
			conn=  getConnection();
			String query = "INSERT INTO question(q_date, q_title, q_desc, cust_email, com_code) VALUES(?,?,?,?,?)";
			ps = conn.prepareStatement(query);
			System.out.println("PreparedStatement 생성됨...insertQuestion");
			
			ps.setString(1, currTime);
			ps.setString(2, title);
			ps.setString(3, desc);
			ps.setString(4, custEmail);	//세션의 고객아이디 값 가져오기
			ps.setInt(5, comCode); //기업코드값 가져오기
			//condition 상태값 default값이 '답변대기'여야 함.
			
			System.out.println(ps.executeUpdate()+" row INSERT OK!!");
		}finally{
			closeAll(ps, conn);
		}
		
	}

	//고객의 질문들보기
	public ArrayList<Question> showAllQuestion(String custEmail) throws SQLException {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		ArrayList<Question> list = new ArrayList<>();
		
		String qDesc= "";
		try {
			conn = getConnection();

			String query = "SELECT q.q_code, q.q_title, q.q_desc, q.q_date, q_condition, c.com_name"
					+ "FROM question q, company c"
					+ "WHERE q.com_code = c.com_code and cust_email=?";
			ps = conn.prepareStatement(query);
			System.out.println("PreparedStatement....showAllQuestion..");
					
			ps.setString(1, custEmail);
			
			rs = ps.executeQuery();
			while(rs.next()) {
				//내용에 문장자르기~~~
				if(rs.getString("q.q_desc").length()>15) {
					qDesc = rs.getString("q.q_desc").substring(0, 15)+"...";
				}else {
					qDesc = rs.getString("q.q_desc");
				}
				Company com = new Company();
				com.setComName("c.com_name");
				list.add(new Question(rs.getInt("q.q_code"), 
									  rs.getString("q.q_title"), 
									  qDesc, 
									  rs.getString("q.q_date"),
									  rs.getString("q.q_condition"),
									  com.getComName()));
			}
		}finally {
			closeAll(rs, ps, conn);
		}
		return list;
	}
	
	//하나조회하는거 추가
	public Question showQuestion(int qCode) throws SQLException {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Question question = null;
		
		try {
			conn = getConnection();

			String query = "select q.q_code, q.q_title, q.q_desc, q.q_date, c.com_name "
					+ "from question q,  company c"
					+ "where q.com_code = c.com_code and q.q_code=?";
			ps = conn.prepareStatement(query);
			System.out.println("PreparedStatement....showQuestion..");
					
			ps.setInt(1, qCode);
			
			rs = ps.executeQuery();
			if(rs.next()) {
					question = new Question(qCode, 
							  rs.getString("q_title"),
							  rs.getString("q_desc"),
							  rs.getString("q_date"));
			}
		}finally {
			closeAll(rs, ps, conn);
		}
		return question;
	}
	
	//회사입장에서 자기한테 들어온 문의만 보기
	public ArrayList<Question> showAllQuestionByCompany(int comCode) throws SQLException {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		ArrayList<Question> list = new ArrayList<>();
		Question question = new Question();
		
		String qDesc= "";
		try {
			conn = getConnection();

			String query = "SELECT q_code, q_title, q_desc, q_date, q_condition, cust_email FROM question WHERE com_code=?";
			ps = conn.prepareStatement(query);
			System.out.println("PreparedStatement....showAllQuestionByCompany..");
					
			ps.setInt(1, comCode);
			
			rs = ps.executeQuery();
			while(rs.next()) {
				//내용에 문장자르기~~~
				if(rs.getString("q_desc").length()>15) {
					qDesc = rs.getString("q_desc").substring(0, 15)+"...";
				}else {
					qDesc = rs.getString("q_desc");
				}
				/*list.add(new Question(rs.getInt("q_code"), 
									  rs.getString("q_title"), 
									  qDesc, 
									  rs.getString("q_date"),
									  rs.getString("q_condition"),
									  rs.getString("cust_email")));*/
				question.setqCode(rs.getInt("q_code"));
				question.setqTitle(rs.getString("q_title"));
				question.setqDesc(qDesc);
				question.setqDate(rs.getString("q_date"));
				question.setqCondition(rs.getString("q_condition"));
				question.setCustEmail(rs.getString("cust_email"));
				list.add(question);
			}
		}finally {
			closeAll(rs, ps, conn);
		}
		return list;
	}

	public void insertAnswer(int qCode, Answer answer) throws SQLException {
		Connection conn = null;
		PreparedStatement ps = null;
		
		//현재시간 출력
		SimpleDateFormat format = new SimpleDateFormat ( "yyyy-MM-dd HH:mm:ss");
		Calendar time = Calendar.getInstance();
		String currTime = format.format(time.getTime());
		//System.out.println(currTime); 확인용
		
		try{
			conn=  getConnection();
			String query = "INSERT INTO answer(a_date, a_desc, com_code, q_code, cust_email) VALUES(?,?,?,?,?)";
			ps = conn.prepareStatement(query);
			System.out.println("PreparedStatement 생성됨...insertAnswer");
			
			ps.setString(1, currTime);
			ps.setString(2, answer.getaDesc());
			ps.setInt(3, answer.getComCode());
			ps.setInt(4, answer.getqCode());	//세션의 업체코드 가져오기
			ps.setString(5, answer.getCustEmail());
			
			// 답변대기를 답변완료로 바꾸기, 이메일로 보내기
			
			System.out.println(ps.executeUpdate()+" row INSERT OK!!");
		}finally{
			closeAll(ps, conn);
		}
		
	}

	public ArrayList<Answer> showAllAnswer(String id) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	public Answer showAnswer(int qCode) throws SQLException {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Answer answer = null;
		
		try {
			conn = getConnection();
			//select q.q_code, q.q_title, q.q_desc, q.q_date, a.a_code, a.a_desc, a.a_date from question q, answer a where a.q_code = ?;
			String query = "select a.a_desc, a.a_date, c.com_name from question q, answer a, company c where a.q_code = ? and c.com_code = a.com_code";
			ps = conn.prepareStatement(query);
			System.out.println("PreparedStatement....showAnswer..");
					
			ps.setInt(1, qCode);
			
			rs = ps.executeQuery();
			if(rs.next()) {
				Company com = new Company();
				com.setComName(rs.getString("c.com_name"));
				answer = new Answer(rs.getString("a.a_desc"), 
						  rs.getString("a.a_date"), 
						  com.getComName());
			}
		}finally {
			closeAll(rs, ps, conn);
		}
		return answer;
	}
	
	//단위테스트
	public static void main(String[] args) throws SQLException {
		QuestionDaoImpl dao = QuestionDaoImpl.getInstance();
		
	}
}
