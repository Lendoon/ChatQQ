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
 * ���Ƿ������˵Ŀ��ƽ��棬 �����������������رշ�������
 * ���Թ���ͼ���û�
 * 
 */
public class MyServerFrame extends JFrame implements  ActionListener{
	JPanel jp1�������;
	JButton jb1��������;
	public static void main(String[] args) {
		MyServerFrame mysf=new MyServerFrame();
	}
	public MyServerFrame(){
		jp1�������=new JPanel();
		
		jb1��������=new JButton("����������");
		jb1��������.addActionListener(this);
		
		jp1�������.add(jb1��������);
		this.add(jp1�������);
			
		this.setSize(300,300);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==jb1��������);
		new MyQqServer();
	}

}
