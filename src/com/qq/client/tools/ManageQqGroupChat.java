package com.qq.client.tools;

import java.util.HashMap;

import com.qq.client.view.GroupChat;
import com.qq.client.view.QqChat;

public class ManageQqGroupChat {

		public   static HashMap hm=new HashMap<String, GroupChat>();
		//加入
		public static void addQqGroupChat(String groupid,GroupChat groupchat){
			hm.put(groupid,groupchat);
			
		}
		//取出
		public static GroupChat getQqGroupChat(String groupid){
			return (GroupChat)hm.get(groupid);
		}
}
