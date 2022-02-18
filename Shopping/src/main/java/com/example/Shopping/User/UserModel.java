package com.example.Shopping.User;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.List;

@Entity
public class UserModel {

	@Id
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

	public void setUsername(String username){
		this.username = username;
	}

	public String getUsername(){
		return username;
	}

	public void setUserEmail(String userEmail){
		this.userEmail = userEmail;
	}

	public String getUserEmail(){
		return userEmail;
	}

	public void setUserFullName(String userFullName){
		this.userFullName = userFullName;
	}

	public String getUserFullName(){
		return userFullName;
	}

	public void setPassword(String password){
		this.password = password;
	}

	public String getPassword(){
		return password;
	}

	public void setName(String name){
		this.name = name;
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