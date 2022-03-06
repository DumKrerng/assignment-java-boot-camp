package com.example.shopping.product;

import javax.persistence.Entity;
import javax.persistence.*;
import org.hibernate.annotations.*;

@Entity
public class ProductModel {

	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	private String productID;

	private String productCode;
	private double unitDiscount;
	private double unitPrice;
	private double productRating;
	private String productName;
	private String productDetail;
	private String productGallery;
	private String productTitle;

	public ProductModel() {}

	public ProductModel(String p_strProductCodeX, String p_strProductNameX) {
		this.productCode = p_strProductCodeX;
		this.productName = p_strProductNameX;
	}

	public String getProductID(){
		return productID;
	}

	public void setUnitDiscount(double p_dubUnitDiscount){
		this.unitDiscount = p_dubUnitDiscount;
	}

	public double getUnitDiscount(){
		return unitDiscount;
	}

	public void setUnitPrice(double p_dubUnitPrice){
		this.unitPrice = p_dubUnitPrice;
	}

	public double getUnitPrice(){
		return unitPrice;
	}

	public void setProductRating(double p_dubProductRating){
		this.productRating = p_dubProductRating;
	}

	public double getProductRating(){
		return productRating;
	}

	public void setProductName(String p_strProductName){
		this.productName = p_strProductName;
	}

	public String getProductName(){
		return productName;
	}

	public void setProductDetail(String p_strProductDetail){
		this.productDetail = p_strProductDetail;
	}

	public String getProductDetail(){
		return productDetail;
	}

	public void setProductCode(String p_strProductCode){
		this.productCode = p_strProductCode;
	}

	public String getProductCode(){
		return productCode;
	}

	public void setProductGallery(String p_strProductGallery){
		this.productGallery = p_strProductGallery;
	}

	public String getProductGallery(){
		return productGallery;
	}

	public void setProductTitle(String p_strProductTitle){
		this.productTitle = p_strProductTitle;
	}

	public String getProductTitle(){
		return productTitle;
	}

	public String getProductLabel() {
		return String.format("%s-%s", productCode, productName);
	}
}
