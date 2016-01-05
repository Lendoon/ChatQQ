package com.qq.client.tools;

import java.util.HashMap;

/*
 * 这是一个管理客户端和服务器保持通讯的线程类
 */
public class ManageClientConServerThread {
   private  static   HashMap hm=new HashMap<String,ClientConServerThread>();
	//把创建好的ClientConServerThread 放入hm
	public static void addClientConServerThread (String qqId,ClientConServerThread ccst ){
		hm.put(qqId ,ccst);	
	}
	
    //可以通过qqId取得该线程
	public static ClientConServerThread getClientConserverThread(String qqId){
		return (ClientConServerThread)hm.get(qqId);
		
	}

}
