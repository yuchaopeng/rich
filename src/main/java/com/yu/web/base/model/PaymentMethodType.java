package com.yu.web.base.model;

/**
 * 收款类型
 * @author yu
 *
 */
public class PaymentMethodType {
	
	private Long id;
	private String typeName;
	private String decription;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getTypeName() {
		return typeName;
	}
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
	public String getDecription() {
		return decription;
	}
	public void setDecription(String decription) {
		this.decription = decription;
	}
	
	
}
