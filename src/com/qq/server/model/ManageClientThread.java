package com.qq.server.model;

import java.util.HashMap;
import java.util.Iterator;

import javax.print.attribute.HashAttributeSet;

public class ManageClientThread {
	
	
 public static  HashMap hm=  new HashMap<String,SerConClientThread>();
  
 //��hm�����һ���ͻ���ͨ���̣߳���hashmap�洢ͨѶ�߳�
 public static void addClientThread(String uid,SerConClientThread ct){
	  hm.put(uid,ct);
  }
 public  static SerConClientThread getClientThread(String uid){
	 return (SerConClientThread )hm.get(uid);
	 
 }
 //���ص�ǰ���ߵ��˵����
// public static String getAllOnLineUserid(){
//	 Iterator it=hm.keySet().iterator();
//	 String res="";
//	 while(it.hasNext()){
//		 res+=it.next().toString()+" ";		 
//	 }
//	 return res;
//	 
// }
}
