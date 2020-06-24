package com.fiesta.model.vo;

public class Orderdetail {
	int detailCode;
	int detailTotalprice;
	String detailDesc;
	String detailCondition;
	int serviceCode;
	int comCode;
	int orderCode;
	
	public Orderdetail() {}
	public Orderdetail(int detailCode, int detailTotalprice, String detailDesc, String detailCondition, int serviceCode,
			int comCode, int orderCode) {
		super();
		this.detailCode = detailCode;
		this.detailTotalprice = detailTotalprice;
		this.detailDesc = detailDesc;
		this.detailCondition = detailCondition;
		this.serviceCode = serviceCode;
		this.comCode = comCode;
		this.orderCode = orderCode;
	}

	public int getDetailCode() {
		return detailCode;
	}
	public void setDetailCode(int detailCode) {
		this.detailCode = detailCode;
	}
	public int getDetailTotalprice() {
		return detailTotalprice;
	}
	public void setDetailTotalprice(int detailTotalprice) {
		this.detailTotalprice = detailTotalprice;
	}
	public String getDetailDesc() {
		return detailDesc;
	}
	public void setDetailDesc(String detailDesc) {
		this.detailDesc = detailDesc;
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
	public int getOrderCode() {
		return orderCode;
	}
	public void setOrderCode(int orderCode) {
		this.orderCode = orderCode;
	}
	
	@Override
	public String toString() {
		return "Orderdetail [detailCode=" + detailCode + ", detailTotalprice=" + detailTotalprice + ", detailDesc="
				+ detailDesc + ", serviceCode=" + serviceCode + ", comCode=" + comCode + ", orderCode=" + orderCode
				+ "]";
	}
}
