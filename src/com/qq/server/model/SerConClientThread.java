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
 * ���ܣ��Ƿ�������ĳ���ͻ��˵�ͨ���߳�
 */
public class SerConClientThread extends Thread {
	Socket s;

	public SerConClientThread(Socket s) {
		// �ѷ������͸ÿͻ��˵����Ӹ���s

		this.s = s;
	}

	public void run() {

		while (true) {
			// ������߳̾Ϳ��Խ��ܿͻ��˵���Ϣ
			ObjectInputStream ois;
			try {
				ois = new ObjectInputStream(s.getInputStream());
				Message m = (Message) ois.readObject();
				if (m.getGetter() != null) {
					System.out.println(m.getSender() + m.getSendTime() + "��"
							+ m.getGetter() + "˵: " + m.getCon());
					// �Դӿͻ���ȡ�õ���Ϣ���������жϣ�Ȼ������Ӧ�Ĵ���

					/*-----------------------------------------------*/
					if (m.getMesType().equals(MessageType.message_comm_mes)) {
						// ���������ഫ��������
						FriendHistory fh = new FriendHistory();
						fh.setFHistoryID(new Inquiry().FriendHistoryMaxID() + 1);

						SimpleDateFormat df = new SimpleDateFormat(
								"yyyyMMddHHmmss");// �������ڸ�ʽ
						fh.setChatDate(Long.parseLong(df.format(new Date())));// ����Ϊ������ʱ����14λ����

						fh.setSenderID(Integer.parseInt(m.getSender()));
						fh.setReceiveID(Integer.parseInt(m.getGetter()));
						fh.setRecord(m.getCon());
						// д�����ݿ�
						Insert temp = new Insert();
						temp.save(fh);

						// һ�����ת������
						// ȡ�ý����˵�ͨ���߳�
						if (MyQqServer.list.indexOf(Integer.parseInt(m
								.getGetter())) != -1) {
							// ������ֱ�ӷ���
							SerConClientThread sc = ManageClientThread
									.getClientThread(m.getGetter());
							ObjectOutputStream oos = new ObjectOutputStream(
									sc.s.getOutputStream());
							oos.writeObject(m);
						} else {
							// �������򴢴浽������
							System.out.println("�Է�������");
							// �޸�ʱ���ʽ
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
							System.out.println("֪ͨ�ͻ���ˢ�º����б�");
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
							System.out.println("֪ͨ�ͻ���ˢ��Ⱥ���б�");
						}
					}

					if (m.getMesType().equals(MessageType.message_offline)) {
						// ��������
						int index = MyQqServer.list.indexOf(Integer.parseInt(m
								.getSender()));
						System.out.println("index:" + index);
						MyQqServer.list.remove(index);
					}

					if (m.getMesType().equals(MessageType.message_group_chat)) {
						// ������Ⱥ��
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
