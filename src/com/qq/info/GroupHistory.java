package com.qq.info;

import java.util.Date;

public class GroupHistory {
	private int GroupID;
	private long ChatDate;
	private String Record;
	private int TalkerID;
	public int getGroupID() {
		return GroupID;
	}
	public void setGroupID(int groupID) {
		GroupID = groupID;
	}
	public long getChatDate() {
		return ChatDate;
	}
	public void setChatDate(int chatDate) {
		ChatDate = chatDate;
	}
	public String getRecord() {
		return Record;
	}
	public void setRecord(String record) {
		Record = record;
	}
	public int getTalkerID() {
		return TalkerID;
	}
	public void setTalkerID(int talkerID) {
		TalkerID = talkerID;
	}
	
}
