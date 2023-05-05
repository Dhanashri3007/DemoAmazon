package com.product.model;



import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;


@Entity
public class Product {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int pId;
	private String brandName;
	private String pName;
	private double pPrice;
	private String pdiscription;
	private int stars;
	
	@OneToOne(cascade = CascadeType.ALL)
	public ProductImage imageData;
	

	
	
	public ProductImage getImageData() {
		return imageData;
	}
	public void setImageData(ProductImage imageData) {
		this.imageData = imageData;
	}
	public String getBrandName() {
		return brandName;
	}
	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}
	public int getpId() {
		return pId;
	}
	public void setpId(int pId) {
		this.pId = pId;
	}
	public String getpName() {
		return pName;
	}
	public void setpName(String pName) {
		this.pName = pName;
	}
	public double getpPrice() {
		return pPrice;
	}
	public String getPdiscription() {
		return pdiscription;
	}
	public void setPdiscription(String pdiscription) {
		this.pdiscription = pdiscription;
	}
	public int getStars() {
		return stars;
	}
	public void setStars(int stars) {
		this.stars = stars;
	}
	public void setpPrice(double pPrice) {
		this.pPrice = pPrice;
	}
	
	

}
