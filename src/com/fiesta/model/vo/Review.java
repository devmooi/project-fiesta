package com.fiesta.model.vo;

import java.util.ArrayList;

public class Review {
	String reviewCode;
	int reviewScore;
	String reviewImg;
	String reviewDesc;
	String reviewDate;
	Customer customer;
	Service service;
	Company company;
	float avgReviewScore;
	int countDesc;
	ArrayList<Review> answerlist = new ArrayList<Review>();
	
	public Review() {}
	public Review(float avgReviewScore, int countDesc, Company company) {
		this.avgReviewScore = avgReviewScore;
		this.countDesc = countDesc;
		this.company = company;
	}
	
	public Review(String reviewCode, int reviewScore, String reviewImg, String reviewDesc, Customer customer,
			Service service, Company company) {
		super();
		this.reviewCode=reviewCode;
		this.reviewScore = reviewScore;
		this.reviewImg = reviewImg;
		this.reviewDesc = reviewDesc;
		this.customer = customer;
		this.service = service;
		this.company = company;
	}
	
	public Review(String reviewCode, int reviewScore, String reviewImg, String reviewDesc, String reviewDate,
			Customer customer, Service service, Company company, float avgReviewScore, int countDesc) {
		super();
		this.reviewCode = reviewCode;
		this.reviewScore = reviewScore;
		this.reviewImg = reviewImg;
		this.reviewDesc = reviewDesc;
		this.reviewDate = reviewDate;
		this.customer = customer;
		this.service = service;
		this.company = company;
		this.avgReviewScore = avgReviewScore;
		this.countDesc = countDesc;
	}
	public Review(String reviewCode, int reviewScore, String reviewImg, String reviewDesc, String reviewDate,
			Customer customer, Service service, Company company, float avgReviewScore, int countDesc,
			ArrayList<Review> answerlist) {
		super();
		this.reviewCode = reviewCode;
		this.reviewScore = reviewScore;
		this.reviewImg = reviewImg;
		this.reviewDesc = reviewDesc;
		this.reviewDate = reviewDate;
		this.customer = customer;
		this.service = service;
		this.company = company;
		this.avgReviewScore = avgReviewScore;
		this.countDesc = countDesc;
		this.answerlist = answerlist;
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
	public float getAvgReviewScore() {
		return avgReviewScore;
	}
	public void setAvgReviewScore(float avgReviewScore) {
		this.avgReviewScore = avgReviewScore;
	}
	public int getCountDesc() {
		return countDesc;
	}
	public void setCountDesc(int countDesc) {
		this.countDesc = countDesc;
	}
	public ArrayList<Review> getAnswerlist() {
		return answerlist;
	}
	public void setAnswerlist(ArrayList<Review> answerlist) {
		this.answerlist = answerlist;
	}
	@Override
	public String toString() {
		return "Review [reviewCode=" + reviewCode + ", reviewScore=" + reviewScore + ", reviewImg=" + reviewImg
				+ ", reviewDesc=" + reviewDesc + ", reviewDate=" + reviewDate + ", customer=" + customer + ", service="
				+ service + ", company=" + company + ", avgReviewScore=" + avgReviewScore + ", countDesc=" + countDesc
				+ ", answerlist=" + answerlist + "]";
	}
	
	
}