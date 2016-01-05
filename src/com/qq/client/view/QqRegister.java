package com.qq.client.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class QqRegister extends JFrame{
	public static void main(String[] args) {
		new QqRegister();
	}
	
	public JPanel contentPane;
	public JTextField txtName;
    public JButton btnRegister;
    public    JPasswordField pwd;
    public JPasswordField pwdRe;
    public JComboBox comGender;
   
    
     public JTextField txtbirthday;
     public JTextField txtphone;
     public JTextArea txtSignat ;
	public QqRegister (){
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(592, 553);
		
		 contentPane = new JPanel() {  
            @Override  
            protected void paintComponent(Graphics g) {  
                ImageIcon icon = new ImageIcon("./image/QqRegister/registerBGimg.jpg");  
                Image img = icon.getImage();  
                g.drawImage(img, 0, 0, icon.getIconWidth(), icon.getIconHeight(), icon.getImageObserver());  
             
            }  
        };  
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);
		
		JLabel jlUserName=new JLabel("ÓÃ»§Ãû");
		jlUserName.setFont(new Font("Î¢ÈíÑÅºÚ",Font.PLAIN,18));
		jlUserName.setBounds(38, 45, 54, 24);
		contentPane.add(jlUserName);
		
		JLabel jlpwd =new JLabel("ÃÜÂë");
		jlpwd.setFont(new Font("Î¢ÈíÑÅºÚ",Font.PLAIN,18));
		jlpwd.setBounds(38, 96, 54, 30);
		contentPane.add(jlpwd);
		
		JLabel jlpwd2=new JLabel("ÖØ¸´ÃÜÂë");
		jlpwd2.setFont(new Font("Î¢ÈíÑÅºÚ",Font.PLAIN,18));
		jlpwd2.setBounds(22, 138, 79, 35);
		contentPane.add(jlpwd2);
		
		JLabel lbEmail = new JLabel("ÊÖ»ú");
		lbEmail.setFont(new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 18));
		lbEmail.setBounds(33, 234, 59, 28);
		contentPane.add(lbEmail);
		
		 JLabel lbBirthday = new JLabel("ÉúÈÕ");
		lbBirthday.setFont(new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 18));
		lbBirthday.setBounds(38, 285, 46, 24);
		contentPane.add(lbBirthday);
		
		JLabel lbSignature = new JLabel("¸öÐÔÇ©Ãû");
		lbSignature.setFont(new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 18));
		lbSignature.setBounds(22, 332, 79, 28);
		contentPane.add(lbSignature);
		
		btnRegister = new JButton("Ãâ·Ñ×¢²á");
		btnRegister.setForeground(new Color(255, 255, 255));
		btnRegister.setBackground(new Color(0, 100, 0));
		btnRegister.setFont(new Font("Î¢ÈíÑÅºÚ", Font.BOLD, 28));
		btnRegister.setBounds(57, 431, 161, 60);
		contentPane.add(btnRegister);
				
		JButton btnCancel = new JButton("¹Ø±Õ");
		btnCancel.setForeground(new Color(255, 250, 250));
		btnCancel.setBackground(new Color(106, 90, 205));
		btnCancel.setFont(new Font("Î¢ÈíÑÅºÚ", Font.BOLD, 28));
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				dispose();
			}
		});
		btnCancel.setBounds(248, 431, 100, 60);
		contentPane.add(btnCancel);
		
		txtName=new JTextField();
		txtName.setBounds(111, 40, 217, 35);
		contentPane.add(txtName);
		txtName.setColumns(10);
		
		pwd = new JPasswordField();
		pwd.setBounds(111, 92, 217, 35);
		contentPane.add(pwd);
		
	     pwdRe = new JPasswordField();
		pwdRe.setBounds(113, 138, 215, 35);
		contentPane.add(pwdRe);
		
		 txtphone = new JTextField();
		 txtphone.setColumns(10);
		 txtphone.setBounds(111, 231, 217, 35);
		contentPane.add(txtphone);
		
	    comGender = new JComboBox();
		comGender.setModel(new DefaultComboBoxModel(new String[] {"ÄÐ", "Å®"}));
		comGender.setBounds(111, 185, 59, 35);
		contentPane.add(comGender);
		
		JLabel lbGender = new JLabel("ÐÔ±ð");
		lbGender.setFont(new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 18));
		lbGender.setBounds(38, 190, 46, 24);
		contentPane.add(lbGender);
		
		 txtSignat = new JTextArea();
		txtSignat.setBounds(111, 328, 217, 75);
		contentPane.add(txtSignat);
		
	     txtbirthday = new JTextField();
		txtbirthday.setBounds(111, 281, 217, 35);
		contentPane.add(txtbirthday);
		txtbirthday.setColumns(10);
	
		JComboBox comboBoxHeadImage = new JComboBox();
		comboBoxHeadImage.setBounds(375, 193, 138, 117);
		ImageIcon icon=null;
		try {
			icon = new ImageIcon(ImageIO.read(new File("./image/QqClientLogin/dafult.png")));
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		comboBoxHeadImage.addItem(icon);
		contentPane.add(comboBoxHeadImage);
		
		JLabel lblHead = new JLabel("Í·ÏñÑ¡Ôñ");
		lblHead.setFont(new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 18));
		lblHead.setBounds(375, 139, 100, 32);
		contentPane.add(lblHead);
	 
	}
}
