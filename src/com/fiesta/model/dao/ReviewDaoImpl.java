package com.fiesta.model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;

import javax.sql.DataSource;

import com.fiesta.model.vo.Company;
import com.fiesta.model.vo.Customer;
import com.fiesta.model.vo.Review;
import com.fiesta.model.vo.Service;
import com.fiesta.util.ServerInfo;

public class ReviewDaoImpl implements ReviewDao{
	private DataSource ds;
	
	private static ReviewDaoImpl dao = new ReviewDaoImpl();
	private ReviewDaoImpl() {
		//ds = DataSourceManager.getInstance().getConnection();
		
		//단위테스트
		try {
			Class.forName(ServerInfo.DRIVER_NAME);
		} catch (ClassNotFoundException e) {
			System.out.println("CustomerDaoImpl :: " + e);
		}
	}
	public static ReviewDaoImpl getInstance() {
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
	
	public boolean isReview(int comCode, int serviceCode) throws SQLException{
		boolean reviewflag = false;
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			conn = getConnection();
			StringBuffer query = new StringBuffer();
			query.append("SELECT review_code ");
			query.append("FROM review ");
			query.append("WHERE com_code = ? ");
			query.append("AND service_code = ? ");
			ps=conn.prepareStatement(query.toString());
			ps.setInt(1, comCode);
			ps.setInt(2, serviceCode);
			rs=ps.executeQuery();
			reviewflag=rs.next();
		}finally {
			closeAll(rs, ps, conn);
		}
		return reviewflag;
	}
	public boolean isAnswer(String reviewCode) throws SQLException{
		boolean reviewflag = false;
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		int cnt = 0;
		
		try {
			conn = getConnection();
			StringBuffer query = new StringBuffer();
			query.append("SELECT review_code ");
			query.append("FROM review ");
			query.append("WHERE review_code Like ? ");
			ps=conn.prepareStatement(query.toString());
			ps.setString(1, reviewCode);
			rs=ps.executeQuery();
			while(rs.next()) {
				cnt++;
			}
		}finally {
			closeAll(rs, ps, conn);
		}
		if(cnt>1) reviewflag = true;
		return reviewflag;
	}
	
	public void insertReview(Review review) throws SQLException {
		Connection conn = null;
		PreparedStatement ps = null;
		
		//System.out.println(review);
		
		try {
			conn=getConnection();
			StringBuffer query = new StringBuffer();
			query.append("INSERT INTO review(");
			query.append("review_code, review_score, review_img, review_desc, review_date, ");
			query.append("cust_email, service_code, com_code) ");
			query.append("VALUES(?,?,?,?,sysdate(),?,?,?) ");
			ps=conn.prepareStatement(query.toString());
			ps.setString(1, review.getReviewCode());
			ps.setInt(2, review.getReviewScore());
			ps.setString(3, review.getReviewImg());
			ps.setString(4, review.getReviewDesc());
			ps.setString(5, review.getCustomer().getCustEmail());
			ps.setInt(6, review.getService().getServiceCode());
			ps.setInt(7, review.getCompany().getComCode());
			System.out.println("리뷰 "+ps.executeUpdate()+"건 추가");
		}finally {
			closeAll(ps, conn);
		}
	}
	
	public void deleteReview(String reviewCode) throws SQLException{
		Connection conn = null;
		PreparedStatement ps = null;
		
		try {
			conn=getConnection();
			StringBuffer query = new StringBuffer();
			query.append("DELETE FROM review ");
			query.append("WHERE review_code = ? ");
			ps=conn.prepareStatement(query.toString());
			ps.setString(1, reviewCode);
			System.out.println("리뷰 "+ps.executeUpdate()+"건이 삭제됐습니다.");
		}finally {
			closeAll(ps, conn);
		}
		
	}
	
	public ArrayList<String> getCustArray() throws SQLException{
		ArrayList<String> custArray = new ArrayList<>();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			conn=getConnection();
			StringBuffer query = new StringBuffer();
			query.append("SELECT DISTINCT cust_email email ");
			query.append("FROM customer ");
			query.append("ORDER BY email ");
			ps=conn.prepareStatement(query.toString());
			//System.out.println(query);
			rs=ps.executeQuery();
			
			int idx=0;
			while(rs.next()) {
				custArray.add(rs.getString("email"));
				idx++;
			}
		}finally {
			closeAll(rs, ps, conn);
		}
		
		return custArray;
	}
	
	public ArrayList<String> getServiceArray() throws SQLException{
		ArrayList<String> custArray = new ArrayList<>();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			conn=getConnection();
			StringBuffer query = new StringBuffer();
			query.append("SELECT DISTINCT concat(com_code,'-',service_code) code ");
			query.append("FROM service ");
			query.append("ORDER BY code ");
			ps=conn.prepareStatement(query.toString());
			//System.out.println(query);
			rs=ps.executeQuery();
			
			int idx=0;
			while(rs.next()) {
				custArray.add(rs.getString("code"));
				idx++;
			}
		}finally {
			closeAll(rs, ps, conn);
		}
		
		return custArray;
	}
	
	public int[] countRowAndCol() throws SQLException{
		int[] arr = new int[2];
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			conn=getConnection();
			StringBuffer query = new StringBuffer();
			query.append("SELECT count(distinct concat(com_code,'-',service_code)) scount, count(DISTINCT cust_email) ccount ");
			query.append("FROM customer, service ");
			ps=conn.prepareStatement(query.toString());
			//System.out.println(query);
			rs=ps.executeQuery();
			if(rs.next()) {
				arr[0]=rs.getInt("scount");
				arr[1]=rs.getInt("ccount");
			}
			
		}finally {
			closeAll(rs, ps, conn);
		}
		
		return arr;
	}
	
	public int[][] getReviewMatrix() throws SQLException {
		int[][] mat = {};
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			conn=getConnection();
			StringBuffer query = new StringBuffer();
			query.append("SELECT concat(com_code,'-',service_code) code, review_score score, cust_email email, ");
			query.append("review_code ");
			query.append("FROM review ");
			query.append("ORDER BY review_code, email ");
			ps=conn.prepareStatement(query.toString());
			//System.out.println(query);
			rs=ps.executeQuery();
			
			int[] arr = countRowAndCol();
			mat = new int[arr[0]][arr[1]];//행 = 고객, 열 = 서비스
			//System.out.println("행렬 수 : ["+arr[0]+","+arr[1]+"]");
			ArrayList<String> custArray= getCustArray();
			ArrayList<String> serviceArray = getServiceArray();
			int i=0;
			int j=0;
			//System.out.println("cust : "+custArray);
			//System.out.println("service : "+serviceArray);
			while(rs.next()) {
				if(rs.getString("review_code").split("-").length==3) {
					//System.out.println("이메일 : "+rs.getString("email"));
					//System.out.println("코드 : "+rs.getString("code"));
					i = custArray.indexOf(rs.getString("email"));
					j = serviceArray.indexOf(rs.getString("code"));
					//
					//System.out.println("(i,j) = ("+i+","+j+")");
					mat[j][i]=rs.getInt("score");
				}
			}
			System.out.println("matrix :: ");
			for(int[] arr2 : mat) {
				System.out.println(java.util.Arrays.toString(arr2));
			}
		}finally {
			closeAll(rs, ps, conn);
		}
		
		return mat;
	}
	
	public String[] getRecoCompany(int[][] matrix, String who) throws SQLException {
		//matrix[s][c]
		String[] recommandService = new String[5];
		float[] corr = new float[matrix[0].length];
		ArrayList<String> custArray = getCustArray();
		ArrayList<String> serviceArray = getServiceArray();
		
		//System.out.println("고객 배열 : "+ custArray);
		//System.out.println("who : "+ who);
		
		int custlocation = custArray.indexOf(who);
		
		float sumU = 0; // A 포함 리뷰 점수의 편차제곱 합 집합
		float[] avgU = new float[matrix[0].length];// A 포함 리뷰 점수의 평균 집합
		float[] stdU = new float[matrix[0].length];// A 포함 리뷰 점수의 표준편차
		float[] covAU = new float[matrix[0].length];// A와 리뷰 점수의 공변량
		
		//sumA, avgA
		for(int i=0;i<matrix[0].length;i++) {
			sumU=0;
			int cnt=0;
			for(int j=0;j<matrix.length;j++) {
				//System.out.println("수 : "+matrix[j][i]);
				sumU+= matrix[j][i];
				if(matrix[j][i]!=0) cnt++;
				if(j==matrix.length-1) {
					if(cnt==0) {
						System.out.println("리뷰내역이 없습니다.");
						recommandService[0]="리뷰없음";
						return  recommandService;
					}
					//System.out.println("sumU, "+custArray.get(i)+": " +sumU);
					//System.out.println(cnt);
					avgU[i] = (float) (Math.round(sumU/(cnt)*10000)/10000.0);
					//System.out.println("avg : "+avgU[i]);
					//System.out.println("sumU : "+sumU);
					//System.out.println("avgU : "+avgU[i]);
					
				}	
			}
		}
		//System.out.println("평균 : "+Arrays.toString(avgU));
		//stdU
		for(int i=0;i<matrix[0].length;i++) {
			float sumDU = 0;
			for(int j=0;j<matrix.length;j++) {
				if(matrix[j][i]!=0) sumDU += (float) Math.pow((float)(matrix[j][i]-avgU[i]), 2);
				if(j==matrix.length-1) {
					stdU[i] = (float) Math.sqrt((float)sumDU);
					if(stdU[i]==0) {
						stdU[i]=(float) 0.1;
					}
					
				}
			}
			
		}
		//System.out.println("표준편차*n : "+Arrays.toString(stdU));
		
		//covAU :: 공분산
		
		//System.out.println("고객 인덱스 : "+custlocation);
		for(int i=0;i<matrix[0].length;i++) {
			if(i!=custlocation) {
				float sumCov = 0;
				for(int j=0;j<matrix.length;j++) {
					if(matrix[j][custlocation]!=0&&matrix[j][i]!=0) {
						//System.out.println(matrix[j][i]+"||"+matrix[j][custlocation]);
						sumCov += (float) ((float)matrix[j][custlocation]-(float)avgU[custlocation])*((float)matrix[j][i]-(float)avgU[i]);
					}
					if(j==matrix.length-1) {
						//System.out.println(sumCov);
						covAU[i] = (float) sumCov;
					}
				}
			}
		}
		//System.out.println("공분산 : "+Arrays.toString(covAU));
		
		//특정인과 그외 사람들 간 corr :: 유사도
		for(int i=0;i<covAU.length;i++) {
			if(i!=custlocation) {
				corr[i] = (float) (Math.round(((covAU[i])/(stdU[custlocation]*stdU[i]))*10000)/10000.0);
			}
		}
		System.out.println("유사도"+Arrays.toString(corr));
		
		// 모든 서비스에 대한 기대 점수
		Float[] expscoreArr = new Float[matrix.length];
		float sumRSbyCorr=0;
		float sumCorr=0;
		for(int i=0; i<matrix.length;i++) {
			for(int j=0;j<matrix[0].length;j++) {
				//System.out.println("matrix["+i+"]["+j+"] : "+matrix[i][j]+", corr["+j+"] : "+corr[j]);
				sumRSbyCorr+=(matrix[i][j]*corr[j]); 
				sumCorr+= corr[j];
			}
			expscoreArr[i]=(float) ((float) Math.round((sumRSbyCorr/sumCorr)*10000)/10000.0);
		}
		//System.out.println("서비스 어레이 : "+serviceArray);
		System.out.println("고객 어레이 : "+custArray);
		//System.out.println("기대점수 : "+Arrays.toString(expscoreArr)+", 개수 : "+expscoreArr.length);
		HashMap<Float, String> scoreAndService = new HashMap<>();
		for(int i=0;i<expscoreArr.length;i++) {
			scoreAndService.put(expscoreArr[i],serviceArray.get(i));
		}
		//System.out.println(scoreAndService);
		Arrays.sort(expscoreArr, Collections.reverseOrder());
		System.out.println("기대점수 내림차순: "+Arrays.toString(expscoreArr));
		String[] rececommandArr = {};
		String[] temp = {};
		for(int i=0;i<5;i++) {
			// 같은 업체에서 다른 서비스를 제공할 경우 제외
			rececommandArr = scoreAndService.get(expscoreArr[i]).split("-");
			if(i>0) {
				if(recommandService[i-1]==null) continue;
				temp = recommandService[i-1].split("-");
				if(temp[0].equals(rececommandArr[0])) continue;
				recommandService[i]=scoreAndService.get(expscoreArr[i]);
			}else {
				recommandService[i]=scoreAndService.get(expscoreArr[i]);
			}
		}
		
		//서비스 별 평균 리뷰 점수
		System.out.println(Arrays.toString(recommandService));
		return recommandService;
	}
	
	
	//리뷰 불러오는 곳
	public ArrayList<Review> showAllReviewByCompany(int comCode) throws SQLException {
		ArrayList<Review> list = new ArrayList<Review>();
		ArrayList<Review> answerlist = new ArrayList<Review>();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			conn=getConnection();
			StringBuffer query = new StringBuffer();
			query.append("SELECT r.review_code, r.review_score, r.review_img, r.review_img, r.review_desc, ");
			query.append("r.review_date, r.cust_email, r.service_code, r.com_code, cust.cust_name ");
			query.append("FROM review r, customer cust ");
			query.append("WHERE r.cust_email=cust.cust_email ");
			query.append("AND r.com_code = ? ");
			query.append("ORDER BY review_date DESC ");
			ps=conn.prepareStatement(query.toString());
			System.out.println("PreparedStatement....showAllReviewByCompany");
			ps.setInt(1,comCode);
			System.out.println(query);
			rs=ps.executeQuery();
			
			answerlist=showAllAnswerByCompany(comCode);
			
			while(rs.next()) {
				if(rs.getString("r.review_code").split("-").length==3) {
					Customer cust = new Customer();
					cust.setCustEmail(rs.getString("r.cust_email"));
					cust.setCustName(rs.getString("cust.cust_name"));
					Service service = new Service();
					service.setServiceCode(rs.getInt("r.service_code"));
					Company company = new Company();
					company.setComCode(rs.getInt("r.com_code"));
					Review review = new Review();
					review.setReviewCode(rs.getString("r.review_code"));
					review.setReviewScore(rs.getInt("r.review_score"));
					review.setReviewImg(rs.getString("r.review_img"));
					review.setReviewDesc(rs.getString("r.review_desc"));
					review.setReviewDate(rs.getString("r.review_date"));
					review.setCompany(company);
					review.setService(service);
					review.setCustomer(cust);
					System.out.println("answerlist : "+ answerlist.size());
					if(answerlist.size()>0) {
						//System.out.println(1);
						for(Review answer : answerlist) {
							String[] arr = answer.getReviewCode().split("-");
							String answerCode = arr[0]+"-"+arr[1]+"-"+arr[2];
							//System.out.println("answerCode : "+answerCode);
							//System.out.println("review : "+rs.getString("r.review_code"));
							if(answerCode.equals(rs.getString("r.review_code"))) {
								//System.out.println("찾음");
								review.setAnswerlist(answerlist);
								list.add(review);
							}else {
								list.add(review);
							}
						}
						return list;
					}
					//System.out.println(2);
					list.add(review);
					//System.out.println("list : "+list);
				}
			}
		}finally {
			closeAll(rs, ps, conn);
		}
		
		return list;
	}
	
	public ArrayList<Review> showAllReviewByCustomer(String email) throws SQLException {
		ArrayList<Review> list = new ArrayList<Review>();
		ArrayList<Review> answerlist = new ArrayList<Review>();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			conn=getConnection();
			StringBuffer query = new StringBuffer();
			query.append("SELECT r.review_code, r.review_score, r.review_img, r.review_img, r.review_desc, ");
			query.append("r.review_date, r.cust_email, r.service_code, r.com_code, cust.cust_name ");
			query.append("FROM review r, customer cust ");
			query.append("WHERE r.cust_email=cust.cust_email ");
			query.append("AND r.cust_email = ? ");
			query.append("ORDER BY review_date DESC ");
			ps=conn.prepareStatement(query.toString());
			System.out.println("PreparedStatement....showAllReviewByCustomer");
			ps.setString(1,email);
			System.out.println(query);
			rs=ps.executeQuery();
			
			answerlist=showAllAnswerByCustomer(email);
			System.out.println("answerlist : "+ answerlist);
			while(rs.next()) {
				if(rs.getString("r.review_code").split("-").length==3) {
					Customer cust = new Customer();
					cust.setCustEmail(rs.getString("r.cust_email"));
					cust.setCustName(rs.getString("cust.cust_name"));
					Service service = new Service();
					service.setServiceCode(rs.getInt("r.service_code"));
					Company company = new Company();
					company.setComCode(rs.getInt("r.com_code"));
					Review review = new Review();
					review.setReviewCode(rs.getString("r.review_code"));
					review.setReviewScore(rs.getInt("r.review_score"));
					review.setReviewImg(rs.getString("r.review_img"));
					review.setReviewDesc(rs.getString("r.review_desc"));
					review.setReviewDate(rs.getString("r.review_date"));
					review.setCompany(company);
					review.setService(service);
					review.setCustomer(cust);
					if(answerlist.size()>0) {
						//System.out.println(1);
						for(Review answer : answerlist) {
							String[] arr = answer.getReviewCode().split("-");
							String answerCode = arr[0]+"-"+arr[1]+"-"+arr[2];
							//System.out.println("answerCode : "+answerCode);
							//System.out.println("review : "+rs.getString("r.review_code"));
							if(answerCode.equals(rs.getString("r.review_code"))) {
								//System.out.println("찾음");
								review.setAnswerlist(answerlist);
								list.add(review);
							}else {
								list.add(review);
							}
						}
						return list;
					}
					//System.out.println(2);
					list.add(review);
					//System.out.println("list : "+list);
				}
			}
		}finally {
			closeAll(rs, ps, conn);
		}
		
		return list;
	}

	
	public ArrayList<Review> showAllAnswerByCustomer(String email) throws SQLException {
		ArrayList<Review> answerlist = new ArrayList<Review>();
		Review answer = null;
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			conn=getConnection();
			StringBuffer query = new StringBuffer();
			query.append("SELECT review_code, review_desc, review_img, review_date, com_code ");
			query.append("FROM review ");
			query.append("WHERE cust_email = ? ");
			ps=conn.prepareStatement(query.toString());
			System.out.println("PreparedStatement....showAllAnswerByCustomer");
			ps.setString(1,email);
			System.out.println(query);
			rs=ps.executeQuery();
			
			while(rs.next()) {
				if(rs.getString("review_code").split("-").length==4) {
					answer = new Review();
					answer.setReviewCode(rs.getString("review_code"));
					answer.setReviewImg(rs.getString("review_img"));
					answer.setReviewDesc(rs.getString("review_desc"));
					answer.setReviewDate(rs.getString("review_date"));
					Company company = new Company();
					company.setComCode(rs.getInt("com_code"));
					answer.setCompany(company);
					answerlist.add(answer);
				}
			}
		}finally {
			closeAll(rs, ps, conn);
		}
		
		return answerlist;
	}
	
	public ArrayList<Review> showAllAnswerByCompany(int comCode) throws SQLException {
		ArrayList<Review> answerlist = new ArrayList<Review>();
		Review answer = null;
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			conn=getConnection();
			StringBuffer query = new StringBuffer();
			query.append("SELECT review_code, review_desc, review_img, review_date, com_code ");
			query.append("FROM review ");
			query.append("WHERE com_code = ? ");
			ps=conn.prepareStatement(query.toString());
			System.out.println("PreparedStatement....showAllAnswerByCompany");
			ps.setInt(1,comCode);
			System.out.println(query);
			rs=ps.executeQuery();
			
			while(rs.next()) {
				if(rs.getString("review_code").split("-").length==4) {
					answer = new Review();
					answer.setReviewCode(rs.getString("review_code"));
					answer.setReviewImg(rs.getString("review_img"));
					answer.setReviewDesc(rs.getString("review_desc"));
					answer.setReviewDate(rs.getString("review_date"));
					Company company = new Company();
					company.setComCode(rs.getInt("com_code"));
					answer.setCompany(company);
					answerlist.add(answer);
				}
			}
		}finally {
			closeAll(rs, ps, conn);
		}
		
		return answerlist;
	}
	
	public Review showReview(String reviewCode) throws SQLException{
		Review review = new Review();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			conn=getConnection();
			StringBuffer query = new StringBuffer();
			query.append("SELECT r.review_code, r.review_desc, avg(review_score) avg, count(review_code) count, ");
			query.append("cust.cust_email email, cust.cust_name name, r.com_code comcode, r.service_code servicecode ");
			query.append("FROM review r, customer cust ");
			query.append("WHERE r.review_code LIKE ? ");
			query.append("AND r.cust_email = cust.cust_email ");
			query.append("GROUP BY review_code ");
			ps=conn.prepareStatement(query.toString());
			System.out.println("PreparedStatement....showReview");
			ps.setString(1, reviewCode);
			System.out.println(query);
			rs=ps.executeQuery();
			while(rs.next()) {
				review.setReviewCode(rs.getString("r.review_code"));
				review.setReviewDesc(rs.getString("r.review_desc"));
				review.setAvgReviewScore(rs.getFloat("avg"));
				review.setCountDesc(rs.getInt("count"));
				Customer cust = new Customer();
				cust.setCustEmail(rs.getString("email"));
				cust.setCustName(rs.getString("name"));
				review.setCustomer(cust);
				Company company = new Company();
				company.setComCode(rs.getInt("comcode"));
				review.setCompany(company);
				Service service  = new Service();
				service.setServiceCode(rs.getInt("servicecode"));
				review.setService(service);
				System.out.println("review : "+review);
			}
		}finally {
			closeAll(rs, ps, conn);
		}
		
		return review;
	}
	
	public int lastReviewCode(int comCode) throws SQLException{
		int lastReviewCode = 0;
		String[] arr = new String[3];
		ArrayList<Integer> list = new ArrayList<>();
		int idx=0;
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			conn=getConnection();
			StringBuffer query = new StringBuffer();
			query.append("SELECT review_code ");
			query.append("FROM review ");
			query.append("WHERE com_code = ? ");
			ps=conn.prepareStatement(query.toString());
			System.out.println(query);
			ps.setInt(1, comCode);
			rs=ps.executeQuery();
			while(rs.next()) {
				System.out.println(rs.getString("review_code"));
				arr = rs.getString("review_code").split("-");
				System.out.println(Arrays.toString(arr));
				list.add(Integer.parseInt(arr[2]));
				System.out.println(list);
				idx++;
			}
		}finally {
			closeAll(rs, ps, conn);
		}
		Collections.sort(list);
		lastReviewCode = list.get(list.size()-1);
		return lastReviewCode;
	}
	
	public Review showReview(int comCode) throws SQLException{
		Review review = new Review();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			conn=getConnection();
			StringBuffer query = new StringBuffer();
			query.append("SELECT r.review_code, r.review_desc, avg(review_score) avg, count(review_code) count, ");
			query.append("cust.cust_email email, cust.cust_name name, r.com_code comcode, r.service_code servicecode, ");
			query.append("c.com_count, c.com_img, c.com_desc, c.com_name ");
			query.append("FROM review r JOIN customer cust ");
			query.append("ON r.cust_email = cust.cust_email ");
			query.append("JOIN company c ON r.com_code=c.com_code ");
			query.append("WHERE r.com_code = ? ");
			query.append("GROUP BY r.com_code ");
			ps=conn.prepareStatement(query.toString());
			System.out.println(query);
			ps.setInt(1, comCode);
			rs=ps.executeQuery();
			while(rs.next()) {
				review.setReviewCode(rs.getString("r.review_code"));
				review.setReviewDesc(rs.getString("r.review_desc"));
				review.setAvgReviewScore(rs.getFloat("avg"));
				review.setCountDesc(rs.getInt("count"));
				Customer cust = new Customer();
				cust.setCustEmail(rs.getString("email"));
				cust.setCustName(rs.getString("name"));
				review.setCustomer(cust);
				Company company = new Company();
				company.setComCode(rs.getInt("comcode"));
				company.setComCount(rs.getInt("c.com_count"));
				company.setComName(rs.getString("c.com_name"));
				company.setComImg(rs.getString("c.com_img"));
				company.setComDesc(rs.getString("c.com_desc"));
				review.setCompany(company);
				Service service  = new Service();
				service.setServiceCode(rs.getInt("servicecode"));
				review.setService(service);
			}
		}finally {
			closeAll(rs, ps, conn);
		}
		System.out.println(review);
		return review;
	}
	
	//단위테스트
	public static void main(String[] args) throws SQLException {
		ReviewDaoImpl dao = ReviewDaoImpl.getInstance();
		RegisterDaoImpl rdao =  RegisterDaoImpl.getInstance();
		CompanyDaoImpl cdao = CompanyDaoImpl.getInstance();
		//System.out.println(dao.showService(1));
		//System.out.println(dao.isReview(1, 1));
		//System.out.println(dao.showAllReviewByCompany(18).size());
		//System.out.println(dao.showReview(19));
		//System.out.println(dao.showAllReviewByCustomer("wpdud001@gmail.com"));
		//dao.deleteReview("10-10-102");
		//System.out.println(dao.lastReviewCode(1));
		System.out.println(dao.showAllReviewByCompany(1));
		//System.out.println(dao.showAllAnswerByCustomer("wpdud001@gmail.com"));
		//알고리즘 테스트 케이스 추가
		/*int num=1;
		int comCode =1;
		
		String reviewCode = "";
		String reviewDesc = "123";
		int reviewScore=0;
		String email ="1";
		Service service = new Service();
		Company company = new Company();
		Customer cust = new Customer(email, "123", "1234", "1111", "d");
		Review review = new Review();
		int cnt=1;
		for(int i=0;i<10;i++) {
			service.setComCode(comCode);
			cust.setCustEmail(email);
			rdao.registerCustomer(cust);
			cdao.insertService(service);
			comCode++;
			email=String.valueOf(Integer.parseInt(email)+1);
			System.out.println("email : "+email);
			System.out.println(cnt+"회 :: 서비스, 고객");
			cnt++;
		}
		comCode=1;
		int serviceCode= 1;
		for(int i=0;i<10;i++) {
			email ="1";
			for(int j=0;j<10;j++) {
				reviewCode = comCode+"-"+serviceCode+"-"+num;
				reviewScore = (int) Math.floor((Math.random()*6));
				cust.setCustEmail(email);
				service.setServiceCode(serviceCode);
				company.setComCode(comCode);
				review = new Review(reviewCode, reviewScore, null, reviewDesc, cust, service, company);
				dao.insertReview(review);
				email=String.valueOf(Integer.parseInt(email)+1);
				num++;
			}
			comCode++;serviceCode++;
			System.out.println(cnt+"회 ::");
			cnt++;
		}*/
		
		//알고리즘 테스트 케이스 추가2
		/*int num=1;
		int comCode =1;
		
		String reviewCode = "";
		String reviewDesc = "123";
		int reviewScore=0;
		int emailNum = 1;
		String email ="encore"+emailNum+"@gmail.com";
		Service service = new Service();
		Company company = new Company();
		Customer cust = new Customer(email, "강제영", "witmals99", "010-5043-5765", "엔코어 학생단체");
		Review review = new Review();
		int cnt=1;
		for(int i=0;i<10;i++) {
			service.setComCode(comCode);
			cust.setCustEmail(email);
			rdao.registerCustomer(cust);
			cdao.insertService(service);
			comCode++;
			emailNum++;
			email ="encore"+emailNum+"@gmail.com";
			System.out.println("email : "+email);
			System.out.println(cnt+"회 :: 서비스, 고객");
			cnt++;
		}
		comCode=1;
		int serviceCode= 1;
		emailNum = 1;
		email ="encore"+emailNum+"@gmail.com";
		for(int i=0;i<10;i++) {
			for(int j=0;j<10;j++) {
				reviewCode = comCode+"-"+serviceCode+"-"+num;
				reviewScore = (int) Math.floor((Math.random()*6));
				cust.setCustEmail(email);
				service.setServiceCode(serviceCode);
				company.setComCode(comCode);
				review = new Review(reviewCode, reviewScore, null, reviewDesc, cust, service, company);
				dao.insertReview(review);
				//email=String.valueOf(Integer.parseInt(email)+1);
				num++;
			}
			comCode++;serviceCode++;
			emailNum++;
			email ="encore"+emailNum+"@gmail.com";
			System.out.println(cnt+"회 ::");
			cnt++;
		}*/
		
		//리뷰 단독으로 등록하기
		/*int comCode=1;
		int serviceCode= 1;
		int num =101;
		int cnt=1;
		String email ="wpdud001@gmail.com";
		String reviewDesc ="123";
		Service service = new Service();
		Company company = new Company();
		Customer cust = new Customer(email, "강제영", "witmals99", "010-5043-5765", "엔코어 학생단체");
		Review review = new Review();
		for(int i=0;i<10;i++) {
			String reviewCode = comCode+"-"+serviceCode+"-"+num;
			int reviewScore = (int) Math.floor((Math.random()*6));
			cust.setCustEmail(email);
			service.setServiceCode(serviceCode);
			company.setComCode(comCode);
			review = new Review(reviewCode, reviewScore, null, reviewDesc, cust, service, company);
			dao.insertReview(review);
			//email=String.valueOf(Integer.parseInt(email)+1);
			comCode++;serviceCode++;
			System.out.println(cnt+"회 ::");
			cnt++;
			num++;
		}*/
		//matrix 생성
		/*int[][] mat = dao.getReviewMatrix();
		System.out.println("matrix :: ");
		for(int[] arr : mat) {
			System.out.println(java.util.Arrays.toString(arr));
		}*/
		// 추천 알고리즘 실행
		System.out.println(Arrays.toString(dao.getRecoCompany(dao.getReviewMatrix(), "kim@gmail.com")));
		
	}
}
