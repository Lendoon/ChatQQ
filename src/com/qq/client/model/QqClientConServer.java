package com.qq.client.model;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

import com.qq.client.tools.ClientConServerThread;
import com.qq.client.tools.ManageClientConServerThread;
import com.qq.common.Message;
import com.qq.common.MessageType;
import com.qq.common.User;

/*
 * 客户端连接服务器的后台
 */

   public class QqClientConServer {
     public   Socket s;
	   //发送第一次请求
	   
    public int SendLoginInfoToServer(Object o){
	    int b = 2;//1:密码正确，2：密码错误 3：已经在线
		try {
		    s = new Socket("127.0.0.1",9999);
			ObjectOutputStream oos=new ObjectOutputStream(s.getOutputStream());
			oos.writeObject(o);
			ObjectInputStream ois=new ObjectInputStream(s.getInputStream());		
			Message ms = (Message)ois.readObject();
			//这里就是验证用户登录的地方		
		if(ms.getMesType().equals("1")){
			//就创建一个该qq号和服务器端保持通讯连接的线程
			ClientConServerThread ccst=new ClientConServerThread(s);
			//启动该通讯线程
			ccst.start();
			ManageClientConServerThread.addClientConServerThread(((User)o).getUserId(), ccst);
			b = 1;
		}else if(ms.getMesType().equals(MessageType.message_online))
			b = 3;
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	            return b;
	
}
    public void SendInfoToServer(Object o){
    	
    }
}
