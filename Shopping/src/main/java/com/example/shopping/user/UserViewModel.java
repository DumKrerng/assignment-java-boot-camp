package com.example.shopping.user;

public class UserViewModel {

	private String username;
	private String userID;
	private String userEmail;
	private String userFullName;
	private String name;

	public UserViewModel() {}

	public UserViewModel(UserModel p_user) {
		username = p_user.getUsername();
		userID = p_user.getUserID();
		userEmail = p_user.getUserEmail();
		userFullName = p_user.getUserFullName();
		name = p_user.getName();
	}

	public void setUsername(String p_username){
		this.username = p_username;
	}

	public String getUsername(){
		return username;
	}

	public void setUserID(String p_userID){
		this.userID = p_userID;
	}

	public String getUserID(){
		return userID;
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

	public void setName(String p_name){
		this.name = p_name;
	}

	public String getName(){
		return name;
	}
}
