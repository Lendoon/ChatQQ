package com.qq.client.tools;

import java.util.HashMap;

import com.qq.client.view.QqFriendList;

public class ManageQqFriendList {
  static HashMap hm=new HashMap<String, QqFriendList>();
  public static void addQqFriendList(String qqid,QqFriendList qqFriendList){
	  hm.put(qqid,qqFriendList);
	  
  }
  public static QqFriendList getQqFriendList(String qqid){
	  return (QqFriendList)hm.get(qqid);
  }
}
