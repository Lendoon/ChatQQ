package com.qq.server.view;

import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import com.qq.server.model.MyQqServer;

/*
 * 
 * 这是服务器端的控制界面， 可以启动服务器，关闭服务器，
 * 可以管理和监控用户
 * 
 */
public class MyServerFrame extends JFrame implements  ActionListener{
	JPanel jp1服务面板;
	JButton jb1启动服务;
	public static void main(String[] args) {
		MyServerFrame mysf=new MyServerFrame();
	}
	public MyServerFrame(){
		jp1服务面板=new JPanel();
		
		jb1启动服务=new JButton("启动服务器");
		jb1启动服务.addActionListener(this);
		
		jp1服务面板.add(jb1启动服务);
		this.add(jp1服务面板);
			
		this.setSize(300,300);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==jb1启动服务);
		new MyQqServer();
	}

}
