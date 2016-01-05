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
 * 这是QQ服务器 ，他在监听，等待某个QQ客户端来连接
 */
public class MyQqServer {
	static List<Integer> list;
	public MyQqServer() {
			//在9999端口监听
			ServerSocket ss;
			//在线用户列表
			list = new ArrayList<Integer>();
			try {
				ss = new ServerSocket(9999);
				System.out.println("我是服务器，在监听.......");
				
				//阻塞，等待连接
				while(true){
				Socket s=ss.accept();
				//接收客户端发来的信息
				    ObjectInputStream ois=new ObjectInputStream(s.getInputStream());
				
					User u=(User)ois.readObject();
					System.out.println("服务器接收到的用户名"+u.getUserId()+"密码： "+u.getPasswd());
					Message m=new Message();
					ObjectOutputStream oos=new ObjectOutputStream(s.getOutputStream());
					Inquiry inq=new Inquiry();
					Users users=inq.InqUsers(Integer.valueOf(u.getUserId()));
					String PassWord=users.getPassword().trim();
					int Birthday=users.getBirthday();
					
					//u.getUserId().equals(NameId)||
				     if(Birthday!=0){
				    	 if(list.indexOf(Integer.parseInt(u.getUserId())) != -1 ){
								//返回已经在线
								m.setMesType(MessageType.message_online);
								System.out.println("已经在线");
								oos.writeObject(m);
								//关闭连接
								s.close();
							}
				    	 else if(String.valueOf(u.getPasswd()).equals(PassWord)){
							//返回一个成功登录的信息报
							m.setMesType("1");
							oos.writeObject(m);
							//这里就单开一个线程，让该线程与该客户端保持通讯
							SerConClientThread scct=new SerConClientThread(s);
							//将客户端线程加入到hashmap中存储
							ManageClientThread.addClientThread(u.getUserId(),scct);
							//启动与该客户端通讯的线程
							scct.start();
							//添加到在线用户list中
							list.add(Integer.parseInt(u.getUserId()));

							//并通知其他在线用户
						//   scct.notifyOther(u.getUserId());
							
						}else if(!String.valueOf(u.getPasswd()).equals(PassWord)){
							//返回密码错误
							m.setMesType("2");
							oos.writeObject(m);
							//关闭连接
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

