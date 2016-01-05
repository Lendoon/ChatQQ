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
 * �ͻ������ӷ������ĺ�̨
 */

   public class QqClientConServer {
     public   Socket s;
	   //���͵�һ������
	   
    public int SendLoginInfoToServer(Object o){
	    int b = 2;//1:������ȷ��2��������� 3���Ѿ�����
		try {
		    s = new Socket("127.0.0.1",9999);
			ObjectOutputStream oos=new ObjectOutputStream(s.getOutputStream());
			oos.writeObject(o);
			ObjectInputStream ois=new ObjectInputStream(s.getInputStream());		
			Message ms = (Message)ois.readObject();
			//���������֤�û���¼�ĵط�		
		if(ms.getMesType().equals("1")){
			//�ʹ���һ����qq�źͷ������˱���ͨѶ���ӵ��߳�
			ClientConServerThread ccst=new ClientConServerThread(s);
			//������ͨѶ�߳�
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
