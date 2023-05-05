package com.product.Responce;

import java.util.Date;
import java.util.List;

public class BaseResponce <T>{
	
	private int statusCode;
	private Date date=new Date();
    private T body;
    private String massege;
	public int getStatusCode() {
		return statusCode;
	}
	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}
	public T getBody() {
		return body;
	}
	public void setBody(T body) {
		this.body = body;
	}
	public String getMassege() {
		return massege;
	}
	public void setMassege(String massege) {
		this.massege = massege;
	}
	public BaseResponce(int statusCode, T body, String massege) {
		this.statusCode = statusCode;
		this.body = body;
		this.massege = massege;
	}
	
	public BaseResponce() {}
    

}
