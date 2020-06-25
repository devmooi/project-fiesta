package com.fiesta.model.vo;

public class Review {
	String reviewCode;
	int reviewScore;
	String reviewImg;
	String reviewDesc;
	String reviewDate;
	Customer customer;
	Service service;
	Company company;
	
	public Review() {}
	public Review(int reviewScore, String reviewDesc, Company company) {
		this.reviewScore = reviewScore;
		this.reviewDesc = reviewDesc;
		this.company = company;
	}
	public Review(String reviewCode, int reviewScore, String reviewImg, String reviewDesc, String reviewDate,
			Customer customer, Service service, Company company) {
		this.reviewCode = reviewCode;
		this.reviewScore = reviewScore;
		this.reviewImg = reviewImg;
		this.reviewDesc = reviewDesc;
		this.reviewDate = reviewDate;
		this.customer = customer;
		this.service = service;
		this.company = company;
	}
	
	public String getReviewCode() {
		return reviewCode;
	}
	public void setReviewCode(String reviewCode) {
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
	public String getReviewDate() {
		return reviewDate;
	}
	public void setReviewDate(String reviewDate) {
		this.reviewDate = reviewDate;
	}
	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	public Service getService() {
		return service;
	}
	public void setService(Service service) {
		this.service = service;
	}
	public Company getCompany() {
		return company;
	}
	public void setCompany(Company company) {
		this.company = company;
	}
	
	@Override
	public String toString() {
		return "Review [reviewCode=" + reviewCode + ", reviewScore=" + reviewScore + ", reviewImg=" + reviewImg
				+ ", reviewDesc=" + reviewDesc + ", reviewDate=" + reviewDate + ", customer=" + customer + ", service="
				+ service + ", company=" + company + "]";
	}
	
}