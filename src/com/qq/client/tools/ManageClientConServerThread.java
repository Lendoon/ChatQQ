package com.qq.client.tools;

import java.util.HashMap;

/*
 * ����һ�������ͻ��˺ͷ���������ͨѶ���߳���
 */
public class ManageClientConServerThread {
   private  static   HashMap hm=new HashMap<String,ClientConServerThread>();
	//�Ѵ����õ�ClientConServerThread ����hm
	public static void addClientConServerThread (String qqId,ClientConServerThread ccst ){
		hm.put(qqId ,ccst);	
	}
	
    //����ͨ��qqIdȡ�ø��߳�
	public static ClientConServerThread getClientConserverThread(String qqId){
		return (ClientConServerThread)hm.get(qqId);
		
	}

}