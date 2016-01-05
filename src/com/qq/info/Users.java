package com.qq.info;

import java.util.Date;

public class Users {
	private int UserID;
	private String name;
	private String Password;
	private int Gender;
	private Double Telephonenum;
	private int Birthday;
	private String sign;
	public int getUserID() {
		return UserID;
	}
	public void setUserID(int userID) {
		UserID = userID;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return Password;
	}
	public void setPassword(String password) {
		Password = password;
	}
	public int getGender() {
		return Gender;
	}
	public void setGender(int object) {
		Gender = object;
	}
	public Double getTelephonenum() {
		return Telephonenum;
	}
	public void setTelephonenum(double i) {
		Telephonenum = i;
	}
	public int getBirthday() {
		return Birthday;
	}
	public void setBirthday(int birthday) {
		Birthday = birthday;
	}
	public String getSign() {
		return sign;
	}
	public void setSign(String sign) {
		this.sign = sign;
	}
	
	
}
