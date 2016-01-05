package com.qq.server.model;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import com.qq.client.tools.Automaticwindow;
import com.qq.client.tools.ManageClientConServerThread;
import com.qq.client.tools.ManageQqChat;
import com.qq.client.tools.ManageQqGroupChat;
import com.qq.client.tools.QqFriendListManage;
import com.qq.client.view.GroupChat;
import com.qq.client.view.QqChat;
import com.qq.client.view.QqFriendList;
import com.qq.common.Group;
import com.qq.common.Message;
import com.qq.common.MessageType;
import com.qq.info.FriendHistory;
import com.qq.jdbc.*;

/*
 * 功能：是服务器和某个客户端的通信线程
 */
public class SerConClientThread extends Thread {
	Socket s;

	public SerConClientThread(Socket s) {
		// 把服务器和该客户端的连接赋给s

		this.s = s;
	}

	public void run() {

		while (true) {
			// 这里该线程就可以接受客户端的信息
			ObjectInputStream ois;
			try {
				ois = new ObjectInputStream(s.getInputStream());
				Message m = (Message) ois.readObject();
				if (m.getGetter() != null) {
					System.out.println(m.getSender() + m.getSendTime() + "给"
							+ m.getGetter() + "说: " + m.getCon());
					// 对从客户端取得的消息进行类型判断，然后做相应的处理

					/*-----------------------------------------------*/
					if (m.getMesType().equals(MessageType.message_comm_mes)) {
						// 生成数据类传给服务器
						FriendHistory fh = new FriendHistory();
						fh.setFHistoryID(new Inquiry().FriendHistoryMaxID() + 1);

						SimpleDateFormat df = new SimpleDateFormat(
								"yyyyMMddHHmmss");// 设置日期格式
						fh.setChatDate(Long.parseLong(df.format(new Date())));// 保存为年月日时分秒14位数字

						fh.setSenderID(Integer.parseInt(m.getSender()));
						fh.setReceiveID(Integer.parseInt(m.getGetter()));
						fh.setRecord(m.getCon());
						// 写入数据库
						Insert temp = new Insert();
						temp.save(fh);

						// 一会完成转发任务
						// 取得接收人的通信线程
						if (MyQqServer.list.indexOf(Integer.parseInt(m
								.getGetter())) != -1) {
							// 在线则直接发送
							SerConClientThread sc = ManageClientThread
									.getClientThread(m.getGetter());
							ObjectOutputStream oos = new ObjectOutputStream(
									sc.s.getOutputStream());
							oos.writeObject(m);
						} else {
							// 不在线则储存到服务器
							System.out.println("对方不在线");
							// 修改时间格式
							m.setSendTime(df.format(new Date()));
							Insert ins = new Insert();
							ins.save(m);
						}
					}
					if (m.getMesType().equals(
							MessageType.message_updata_friendlist)) {
						if (MyQqServer.list.indexOf(Integer.parseInt(m
								.getGetter())) != -1) {
							SerConClientThread sc = ManageClientThread
									.getClientThread(m.getGetter());
							ObjectOutputStream oos = new ObjectOutputStream(
									sc.s.getOutputStream());
							oos.writeObject(m);
							System.out.println("通知客户端刷新好友列表");
						}

					}

					if (m.getMesType().equals(
							MessageType.message_updata_grouplist)) {
						if (MyQqServer.list.indexOf(Integer.parseInt(m
								.getGetter())) != -1) {
							SerConClientThread sc = ManageClientThread
									.getClientThread(m.getGetter());
							ObjectOutputStream oos = new ObjectOutputStream(
									sc.s.getOutputStream());
							oos.writeObject(m);
							System.out.println("通知客户端刷新群组列表");
						}
					}

					if (m.getMesType().equals(MessageType.message_offline)) {
						// 下线请求
						int index = MyQqServer.list.indexOf(Integer.parseInt(m
								.getSender()));
						System.out.println("index:" + index);
						MyQqServer.list.remove(index);
					}

					if (m.getMesType().equals(MessageType.message_group_chat)) {
						// 这里是群聊
						Inquiry inq = new Inquiry();
						ArrayList<Integer> Grouplist = new ArrayList<Integer>();
						Grouplist = inq.InqGroupMemberlist(Integer.valueOf(m
								.getGetter()));
						for (int i = 0; i < Grouplist.size(); i++) {
							System.out.println(Grouplist.size());
							System.out.println(Grouplist.get(i));
							SerConClientThread sc = ManageClientThread
									.getClientThread(String.valueOf(Grouplist
											.get(i)));
							ObjectOutputStream oos = new ObjectOutputStream(
									sc.s.getOutputStream());
							//m.setSender(String.valueOf(Grouplist.get(i)));// TO`DO
							System.out
									.println(String.valueOf(Grouplist.get(i)));
							oos.writeObject(m);
						}
					}
				}

			} catch (IOException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (NumberFormatException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}
}
