package com.qq.client.tools;

import java.io.ObjectInputStream;
import java.net.Socket;
import java.util.ArrayList;

import com.qq.client.view.GroupChat;
import com.qq.client.view.QqChat;
import com.qq.client.view.QqFriendList;
import com.qq.common.Message;
import com.qq.common.MessageType;
import com.qq.common.User;
import com.qq.jdbc.Delete;
import com.qq.jdbc.Inquiry;

/*
 * ���ǿͻ��˺ͷ������˱���ͨѶ���߳�
 */
public class ClientConServerThread extends Thread {
	public Socket getS() {
		return s;
	}

	public void setS(Socket s) {
		this.s = s;
	}

	private Socket s;

	// ���캯��
	public ClientConServerThread(Socket s) {
		this.s = s;

	}

	public void run() {
		while (true) {
			// ��ͣ�Ķ�ȡ�ӷ������˷�������Ϣ
			try {
				ObjectInputStream ois = new ObjectInputStream(
						s.getInputStream());
				Message m = (Message) ois.readObject();
				// System.out.println("��ȡ���ӷ���������Ϣ"+m.getSender()+"��"+m.getGetter()+"����"+m.getCon());
				if (m.getMesType().equals(MessageType.message_comm_mes)) {
					// �Ѵӷ�������õ���Ϣ����ʾ������ʾ���������
					//QqChat qqChat=new QqChat(m.getGetter(), m.getSender());
					  QqChat qqChat = ManageQqChat.getQqChat(m.getGetter() + " "
								+ m.getSender());
					
						if(qqChat!=null){
					// ��ʾ	
							qqChat.showMessage(m);
					
					}else{
						QqChat qqChat1=new QqChat(m.getGetter(), m.getSender());
						ManageQqChat.addQqChat( m.getGetter()+ " " + m.getSender(), qqChat1);
						qqChat1.showMessage(m);
							
					}
				} else if (m.getMesType().equals(MessageType.message_group_chat)) {
					GroupChat gc=ManageQqGroupChat.getQqGroupChat(m.getGetter());
					   if(gc!=null){
						   gc.showMessage(m);
					    
					    
					   }
						else {						

							  GroupChat groupchat1 =new GroupChat(m.getSender(),m.getGetter());
							  System.out.println("^^^^^^"+m.getSender());
							
							 ManageQqGroupChat.addQqGroupChat(m.getGetter(), groupchat1);				
							     groupchat1.showMessage(m);
							
						     }
						
				} else if(m.getMesType().equals(MessageType.message_updata_friendlist)){
					//ˢ�º����б�
					QqFriendList list = QqFriendListManage.getQqFriendList(m.getGetter());
				    list.updata(Integer.parseInt(m.getCon()) , m.getSender());
				    //ɾ�����ݿ��¼
				    Delete del = new Delete();
				    del.DelFriend(Integer.parseInt(m.getGetter()), Integer.parseInt(m.getSender()));
					
				} else if(m.getMesType().equals(MessageType.message_updata_grouplist)){
					//ˢ��Ⱥ�б�
					QqFriendList list = QqFriendListManage.getQqFriendList(m.getGetter());
					String[] str = m.getCon().split("/");
					  list.updata(Integer.parseInt(str[0]) , str[1]);
					
				}

				

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

}
