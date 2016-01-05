package com.qq.client.view;

import java.awt.Color;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import com.qq.common.User;

public class MyJDialog extends JDialog  implements ActionListener{
	private JButton jb1;
	private JButton jb2;
	private JTextField jt;
	public String memberId;
	
	
	public MyJDialog(GroupChat frame) {
		// TODO Auto-generated constructor stub
		super(frame,"添加群成员",true);
		JPanel jp=new JPanel();
		jp.setLayout(null);
		
		jb1=new JButton("添加");
		jb1.setBounds(30, 90, 80, 25);
		jb1.addActionListener(this);
		
			
		jb2=new JButton("取消");
		jb2.setBounds(150, 90, 80, 25);
		jb2.addActionListener(this);
		JLabel jl=new JLabel("成员号码：");
		jl.setBounds(20,40,80,30);
		jt=new JTextField();
		jt.setBounds(100, 40, 100, 30);
		jt.setCaretColor(Color.black);
         jp.add(jb1);
         jp.add(jb2);
         jp.add(jl);
         jp.add(jt);
         
         this.add(jp);
		this.setBounds(120, 120, 300, 180);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		// TODO Auto-generated method stub
		if(e.getSource()==(jb1)){
			memberId=jt.getText().trim();
			//System.out.println(groupId);
			GroupChat.groupId(memberId);
		
			this.dispose();
		}
		else if(e.getSource()==(jb2)){
			
			jt.setText("");
			this.dispose();
			
		}
		
	}
	

}
