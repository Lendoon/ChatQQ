package com.qq.client.tools;

import java.util.HashMap;

import com.qq.client.view.GroupChat;
import com.qq.client.view.QqChat;

public class ManageQqGroupChat {

		public   static HashMap hm=new HashMap<String, GroupChat>();
		//����
		public static void addQqGroupChat(String groupid,GroupChat groupchat){
			hm.put(groupid,groupchat);
			
		}
		//ȡ��
		public static GroupChat getQqGroupChat(String groupid){
			return (GroupChat)hm.get(groupid);
		}
}
