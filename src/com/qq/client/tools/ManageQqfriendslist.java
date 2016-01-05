package com.qq.client.tools;

import java.util.HashMap;

import com.qq.client.view.QqChat;
import com.qq.client.view.QqFriendList;

public class ManageQqfriendslist {
	private static HashMap hm=new HashMap<String, QqFriendList>();
	//加入
	public static void addQqFriendlist(String ownerid,QqFriendList friendlist){
		hm.put(ownerid,friendlist);
		
	}
	//取出
	public static QqFriendList getQqFriendlist(String ownerid){
		return (QqFriendList)hm.get(ownerid);
	}

}
