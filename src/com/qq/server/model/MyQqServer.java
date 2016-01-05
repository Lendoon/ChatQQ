package com.qq.server.model;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import com.qq.common.Message;
import com.qq.common.MessageType;
import com.qq.common.User;
import com.qq.info.Users;
import com.qq.jdbc.Inquiry;

/*
 * 
 * ����QQ������ �����ڼ������ȴ�ĳ��QQ�ͻ���������
 */
public class MyQqServer {
	static List<Integer> list;
	public MyQqServer() {
			//��9999�˿ڼ���
			ServerSocket ss;
			//�����û��б�
			list = new ArrayList<Integer>();
			try {
				ss = new ServerSocket(9999);
				System.out.println("���Ƿ��������ڼ���.......");
				
				//�������ȴ�����
				while(true){
				Socket s=ss.accept();
				//���տͻ��˷�������Ϣ
				    ObjectInputStream ois=new ObjectInputStream(s.getInputStream());
				
					User u=(User)ois.readObject();
					System.out.println("���������յ����û���"+u.getUserId()+"���룺 "+u.getPasswd());
					Message m=new Message();
					ObjectOutputStream oos=new ObjectOutputStream(s.getOutputStream());
					Inquiry inq=new Inquiry();
					Users users=inq.InqUsers(Integer.valueOf(u.getUserId()));
					String PassWord=users.getPassword().trim();
					int Birthday=users.getBirthday();
					
					//u.getUserId().equals(NameId)||
				     if(Birthday!=0){
				    	 if(list.indexOf(Integer.parseInt(u.getUserId())) != -1 ){
								//�����Ѿ�����
								m.setMesType(MessageType.message_online);
								System.out.println("�Ѿ�����");
								oos.writeObject(m);
								//�ر�����
								s.close();
							}
				    	 else if(String.valueOf(u.getPasswd()).equals(PassWord)){
							//����һ���ɹ���¼����Ϣ��
							m.setMesType("1");
							oos.writeObject(m);
							//����͵���һ���̣߳��ø��߳���ÿͻ��˱���ͨѶ
							SerConClientThread scct=new SerConClientThread(s);
							//���ͻ����̼߳��뵽hashmap�д洢
							ManageClientThread.addClientThread(u.getUserId(),scct);
							//������ÿͻ���ͨѶ���߳�
							scct.start();
							//��ӵ������û�list��
							list.add(Integer.parseInt(u.getUserId()));

							//��֪ͨ���������û�
						//   scct.notifyOther(u.getUserId());
							
						}else if(!String.valueOf(u.getPasswd()).equals(PassWord)){
							//�����������
							m.setMesType("2");
							oos.writeObject(m);
							//�ر�����
							s.close();
						}
						}					
				}
					
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}			
	public static void main(String[] args) {
		new MyQqServer();
	}
	}

