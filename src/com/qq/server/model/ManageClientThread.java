package com.qq.server.model;

import java.util.HashMap;
import java.util.Iterator;

import javax.print.attribute.HashAttributeSet;

public class ManageClientThread {
	
	
 public static  HashMap hm=  new HashMap<String,SerConClientThread>();
  
 //向hm中添加一个客户端通信线程，用hashmap存储通讯线程
 public static void addClientThread(String uid,SerConClientThread ct){
	  hm.put(uid,ct);
  }
 public  static SerConClientThread getClientThread(String uid){
	 return (SerConClientThread )hm.get(uid);
	 
 }
 //返回当前在线的人的情况
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
