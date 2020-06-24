package com.fiesta.model.vo;

public class Service {
	int serviceCode;
	String serviceName;
	String serviceDesc;
	String serviceImg;
	String serviceTag;
	int comCode;
	
	public Service() {}
	public Service(int serviceCode, String serviceName, String serviceDesc, String serviceImg, String serviceTag,
			int comCode) {
		this.serviceCode = serviceCode;
		this.serviceName = serviceName;
		this.serviceDesc = serviceDesc;
		this.serviceImg = serviceImg;
		this.serviceTag = serviceTag;
		this.comCode = comCode;
	}
	public Service(String serviceName, String serviceDesc, String serviceImg, String serviceTag, int comCode) {
		this.serviceName = serviceName;
		this.serviceDesc = serviceDesc;
		this.serviceImg = serviceImg;
		this.serviceTag = serviceTag;
		this.comCode = comCode;
	}
	public Service(String serviceName, String serviceDesc, String serviceImg, String serviceTag) {
		this.serviceName = serviceName;
		this.serviceDesc = serviceDesc;
		this.serviceImg = serviceImg;
		this.serviceTag = serviceTag;
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
	
	public int getComCode() {
		return comCode;
	}
	public void setComCode(int comCode) {
		this.comCode = comCode;
	}
	
	@Override
	public String toString() {
		return "Service [serviceCode=" + serviceCode + ", serviceName=" + serviceName + ", serviceDesc=" + serviceDesc
				+ ", serviceImg=" + serviceImg + ", serviceTag=" + serviceTag  + ", comCode=" + comCode + "]";
	}
}
