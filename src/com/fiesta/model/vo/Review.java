package com.fiesta.model.vo;

public class Review {
	String reviewCode;
	int reviewScore;
	String reviewImg;
	String reviewDesc;
	Service service;
	Company company;
	Customer customer;
	
	public Review() {}
	public Review(int reviewScore, String reviewDesc, Company company) {
		super();
		this.reviewScore = reviewScore;
		this.reviewDesc = reviewDesc;
		this.company = company;
	}
	public Review(String reviewCode, int reviewScore, String reviewImg, String reviewDesc, Service service,
			Company company, Customer customer) {
		this.reviewCode = reviewCode;
		this.reviewScore = reviewScore;
		this.reviewImg = reviewImg;
		this.reviewDesc = reviewDesc;
		this.service = service;
		this.company = company;
		this.customer = customer;
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
	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	
	@Override
	public String toString() {
		return "Review [reviewCode=" + reviewCode + ", reviewScore=" + reviewScore + ", reviewImg=" + reviewImg
				+ ", reviewDesc=" + reviewDesc + ", service=" + service + ", company=" + company + ", customer="
				+ customer + "]";
	}
}
