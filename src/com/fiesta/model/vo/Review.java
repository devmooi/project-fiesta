package com.fiesta.model.vo;

public class Review {
	int reviewCode;
	int reviewScore;
	String reviewImg;
	String reviewDesc;
	int serviceCode;
	int comCode;
	String custId;
	public Review() {
		// TODO Auto-generated constructor stub
	}
	public Review(int reviewCode, int reviewScore, String reviewImg, String reviewDesc, int serviceCode, int comCode,
			String custId) {
		this.reviewCode = reviewCode;
		this.reviewScore = reviewScore;
		this.reviewImg = reviewImg;
		this.reviewDesc = reviewDesc;
		this.serviceCode = serviceCode;
		this.comCode = comCode;
		this.custId = custId;
	}
	public int getReviewCode() {
		return reviewCode;
	}
	public void setReviewCode(int reviewCode) {
		this.reviewCode = reviewCode;
	}
	public int getReviewScore() {
		return reviewScore;
	}
	public void setReviewScore(int reviewScore) {
		this.reviewScore = reviewScore;
	}
	public String getReviewImg() {
		return reviewImg;
	}
	public void setReviewImg(String reviewImg) {
		this.reviewImg = reviewImg;
	}
	public String getReviewDesc() {
		return reviewDesc;
	}
	public void setReviewDesc(String reviewDesc) {
		this.reviewDesc = reviewDesc;
	}
	public int getServiceCode() {
		return serviceCode;
	}
	public void setServiceCode(int serviceCode) {
		this.serviceCode = serviceCode;
	}
	public int getComCode() {
		return comCode;
	}
	public void setComCode(int comCode) {
		this.comCode = comCode;
	}
	public String getCustId() {
		return custId;
	}
	public void setCustId(String custId) {
		this.custId = custId;
	}
	@Override
	public String toString() {
		return "Review [reviewCode=" + reviewCode + ", reviewScore=" + reviewScore + ", reviewImg=" + reviewImg
				+ ", reviewDesc=" + reviewDesc + ", serviceCode=" + serviceCode + ", comCode=" + comCode + ", custId="
				+ custId + "]";
	}
	
	
}
