package com.qq.control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JOptionPane;

import com.qq.client.view.QqRegister;
import com.qq.info.Users;
import com.qq.jdbc.Inquiry;
import com.qq.jdbc.Insert;

public class Register extends QqRegister{
	
	public Register(){
		addEvent();
		setVisible(true);
		
		
	}
	public void addEvent(){
		
		btnRegister.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Boolean b=true;
				// TODO Auto-generated method stub
				  if(txtName.getText().equals("")){
                	  JOptionPane.showMessageDialog(null, "用户名不能为空","错误",JOptionPane.ERROR_MESSAGE);
                	  b=false;
				  }
                  else if (new String (pwd.getPassword()).equals("")){
                	  JOptionPane.showMessageDialog(null, "密码不能为空","错误",JOptionPane.ERROR_MESSAGE);
                	   b=false;
                  }
               else if(!new String (pwd.getPassword()).equals (new String(pwdRe.getPassword()))){
                	  JOptionPane.showMessageDialog(null, "两次密码不同，请重新输入","错误",JOptionPane.ERROR_MESSAGE);
                	   b=false;
               }
                	  else if (txtphone.getText().equals("")){
                	  JOptionPane.showMessageDialog(null, "请填写手机号码","错误",JOptionPane.ERROR_MESSAGE);
                	   b=false;
                	  }else if(txtbirthday.getText().trim().length()!=8){
                		  System.out.println(txtbirthday.getText().trim().length());
                		  
                	  JOptionPane.showMessageDialog(null, "请填写8位数生日","错误",JOptionPane.ERROR_MESSAGE);
                	   b=false;
                	  }
                  else if(b==true){
                	  TestRegister();
                  }
			}
		});
		
		
		
		

}
	public void TestRegister(){
		Inquiry inq = new Inquiry();
		int id = 0;
		try {
			id = inq.UserMaxID()+1;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		Users users=new Users();
		users.setUserID(id);
		users.setName(txtName.getText());
		users.setPassword(new String(pwd.getPassword()));
		int  Gender= comGender.getSelectedIndex();
		users.setGender(Gender);
		String strphone=txtphone.getText();
		users.setTelephonenum(Double.valueOf(strphone));
		String strBirthday=txtbirthday.getText();
		users.setBirthday(Integer.parseInt(strBirthday));
		users.setSign(txtSignat.getText());
		Insert insert=new Insert();
		insert.save(users);
		JOptionPane.showMessageDialog(this, "注册账号："+id, "恭喜你，注册成功", JOptionPane.INFORMATION_MESSAGE);
		dispose();
		
		
	}
}