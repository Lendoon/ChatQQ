package com.qq.info;

import java.util.Date;

public class FriendHistory {
	private int FHistoryID;
	private int SenderID;
	private int ReceiveID;
	private long ChatDate;
	private String Record;
	
	public int getFHistoryID() {
		return FHistoryID;
	}
	public void setFHistoryID(int fHistoryID) {
		FHistoryID = fHistoryID;
	}
	public long getChatDate() {
		return ChatDate;
	}
	public void setChatDate(long chatDate) {
		ChatDate = chatDate;
	}
	public String getRecord() {
		return Record;
	}
	public void setRecord(String record) {
		Record = record;
	}
	public int getSenderID() {
		return SenderID;
	}
	public void setSenderID(int senderID) {
		SenderID = senderID;
	}
	public int getReceiveID() {
		return ReceiveID;
	}
	public void setReceiveID(int receiveID) {
		ReceiveID = receiveID;
	}


}
