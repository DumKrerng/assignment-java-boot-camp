package com.example.Shopping.user;

import javax.persistence.Entity;
import javax.persistence.*;
import org.hibernate.annotations.*;

@Entity
public class UserModel {

	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	private String userID;

	private String username;
	private String userEmail;
	private String userFullName;
	private String password;
	private String name;
//	private List<AddressItem> address;

	public void setUserID(String p_strUserID){
		this.userID = p_strUserID;
	}

	public String getUserID(){
		return userID;
	}

	public void setUsername(String p_username){
		this.username = p_username;
	}

	public String getUsername(){
		return username;
	}

	public void setUserEmail(String p_userEmail){
		this.userEmail = p_userEmail;
	}

	public String getUserEmail(){
		return userEmail;
	}

	public void setUserFullName(String p_userFullName){
		this.userFullName = p_userFullName;
	}

	public String getUserFullName(){
		return userFullName;
	}

	public void setPassword(String p_password){
		this.password = p_password;
	}

	public String getPassword(){
		return password;
	}

	public void setName(String p_name){
		this.name = p_name;
	}

	public String getName(){
		return name;
	}

//	public void setAddress(List<AddressItem> address){
//		this.address = address;
//	}
//
//	public List<AddressItem> getAddress(){
//		return address;
//	}
}