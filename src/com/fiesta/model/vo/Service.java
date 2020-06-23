package com.fiesta.model.vo;

public class Service {
	int serviceCode;
	String serviceName;
	String serviceDesc;
	String serviceImg;
	String serviceTag;
	int serviceCount;
	int comCode;
	public Service() {
		// TODO Auto-generated constructor stub
	}
	public Service(int serviceCode, String serviceName, String serviceDesc, String serviceImg, String serviceTag,
			int serviceCount, int comCode) {
		this.serviceCode = serviceCode;
		this.serviceName = serviceName;
		this.serviceDesc = serviceDesc;
		this.serviceImg = serviceImg;
		this.serviceTag = serviceTag;
		this.serviceCount = serviceCount;
		this.comCode = comCode;
	}
	public int getServiceCode() {
		return serviceCode;
	}
	public void setServiceCode(int serviceCode) {
		this.serviceCode = serviceCode;
	}
	public String getServiceName() {
		return serviceName;
	}
	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}
	public String getServiceDesc() {
		return serviceDesc;
	}
	public void setServiceDesc(String serviceDesc) {
		this.serviceDesc = serviceDesc;
	}
	public String getServiceImg() {
		return serviceImg;
	}
	public void setServiceImg(String serviceImg) {
		this.serviceImg = serviceImg;
	}
	public String getServiceTag() {
		return serviceTag;
	}
	public void setServiceTag(String serviceTag) {
		this.serviceTag = serviceTag;
	}
	public int getServiceCount() {
		return serviceCount;
	}
	public void setServiceCount(int serviceCount) {
		this.serviceCount = serviceCount;
	}
	public int getComCode() {
		return comCode;
	}
	public void setComCode(int comCode) {
		this.comCode = comCode;
	}
	@Override
	public String toString() {
		return "Service [serviceCode=" + serviceCode + ", serviceName=" + serviceName + ", serviceDesc=" + serviceDesc
				+ ", serviceImg=" + serviceImg + ", serviceTag=" + serviceTag + ", serviceCount=" + serviceCount
				+ ", comCode=" + comCode + "]";
	}
	
	
}
