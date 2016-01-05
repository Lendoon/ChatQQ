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
 * 这是客户端和服务器端保持通讯的线程
 */
public class ClientConServerThread extends Thread {
	public Socket getS() {
		return s;
	}

	public void setS(Socket s) {
		this.s = s;
	}

	private Socket s;

	// 构造函数
	public ClientConServerThread(Socket s) {
		this.s = s;

	}

	public void run() {
		while (true) {
			// 不停的读取从服务器端发来的消息
			try {
				ObjectInputStream ois = new ObjectInputStream(
						s.getInputStream());
				Message m = (Message) ois.readObject();
				// System.out.println("读取到从服务发来的消息"+m.getSender()+"给"+m.getGetter()+"内容"+m.getCon());
				if (m.getMesType().equals(MessageType.message_comm_mes)) {
					// 把从服务器获得的消息，显示到该显示的聊天界面
					//QqChat qqChat=new QqChat(m.getGetter(), m.getSender());
					  QqChat qqChat = ManageQqChat.getQqChat(m.getGetter() + " "
								+ m.getSender());
					
						if(qqChat!=null){
					// 显示	
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
					//刷新好友列表
					QqFriendList list = QqFriendListManage.getQqFriendList(m.getGetter());
				    list.updata(Integer.parseInt(m.getCon()) , m.getSender());
				    //删除数据库记录
				    Delete del = new Delete();
				    del.DelFriend(Integer.parseInt(m.getGetter()), Integer.parseInt(m.getSender()));
					
				} else if(m.getMesType().equals(MessageType.message_updata_grouplist)){
					//刷新群列表
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
