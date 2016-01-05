package com.qq.common;

public class Message implements java.io.Serializable{
    private String mesType;
    private String sender;
    private String getter;
    private String con;
    private String sendTime;
    private String getter2;
    

	public String getSender() {
		return sender;
	}

	public void setSender(String sender) {
		this.sender = sender;
	}

	public String getGetter() {
		return getter;
	}

	public void setGetter(String getter) {
		this.getter = getter;
	}

	public String getCon() {
		return con;
	}

	public void setCon(String con) {
		this.con = con;
	}

	public String getSendTime() {
		return sendTime;
	}

	public void setSendTime(String sendTime) {
		this.sendTime = sendTime;
	}

	public String getMesType() {
		return mesType;
	}

	public void setMesType(String mesType) {
		this.mesType = mesType;
	}

	public String getGetter2() {
		return getter2;
	}

	public void setGetter2(String getter2) {
		this.getter2 = getter2;
	}
    
}
