package com.qq.client.view;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

import com.qq.client.model.QqClientUser;
import com.qq.client.tools.ManageQqChat;
import com.qq.client.tools.QqFriendListManage;
import com.qq.client.view.util.BtnUtil;
import com.qq.client.view.util.LoginWindowMoveListener;
import com.qq.client.view.util.WindowCloseListener;
import com.qq.client.view.util.WindowMinListener;
import com.qq.common.Message;
import com.qq.common.User;
import com.qq.control.Register;
import com.qq.jdbc.Delete;
import com.qq.jdbc.Inquiry;

public class QqClientLogin extends JFrame implements MouseListener{
	private JTextField jtfAccount = null; // �˻������
	private JPasswordField pwdfPassword = null;// ���������
	private MyPanel loginPanel;
	private BufferedImage bgImage;
	private Image bgSrc;
	private JLabel headImg;
	private JLabel loginImg;  //��¼
	private JButton miniBtn;
	private JButton closeBtn;
	private JLabel register;  //ע��
	 private  User u ;

	public QqClientLogin() {

		bgImage = new BufferedImage(500, 282, BufferedImage.TYPE_INT_RGB);
		Graphics gBg = bgImage.getGraphics();
		try {
			bgSrc = ImageIO.read(new File("./image/QqClientLogin/login_bg.png"));
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		gBg.drawImage(bgSrc, 0, 0, null);
		loginPanel = new MyPanel(bgImage);
		loginPanel.setPreferredSize(new Dimension(500, 282));
		// loginPanel.setOpaque(false);

		headImg = new JLabel();
		headImg.setIcon(new ImageIcon("./image/QqClientLogin/dafult.png"));

		loginImg = new JLabel();
		loginImg.setIcon(new ImageIcon("./image/QqClientLogin/in.png"));
		loginImg.addMouseListener(this);
			
		// logIn.setOpaque(false);

		// ��С����ť
		miniBtn = BtnUtil.getBtnMini();
		miniBtn.addActionListener(new WindowMinListener(this));

		// �˳���ť
		closeBtn = BtnUtil.getBtnClose();
		closeBtn.addActionListener(new WindowCloseListener(this));

		jtfAccount = new JTextField();
		//jtfAccount.setText(" �˺�");
		jtfAccount.setBorder(new LineBorder(Color.GRAY, 1));
		jtfAccount.setFont(new Font("��������", Font.PLAIN, 14));
		jtfAccount.setForeground(Color.blue);
		jtfAccount.setEditable(true);
		jtfAccount.setEnabled(true);

		pwdfPassword = new JPasswordField();
		//pwdfPassword.setText("����  ");
		pwdfPassword.requestFocus(true);
		pwdfPassword.setBorder(new LineBorder(Color.GRAY, 1));
		pwdfPassword.setForeground(Color.blue);
		pwdfPassword.setEchoChar('��');

		register = new JLabel("ע���˺�");
		register.setFont(new Font("��������", Font.PLAIN, 16));
		register.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseClicked(MouseEvent e) {
        	new Register();
        	}
		});

		loginPanel.setLayout(null);
		loginPanel.add(miniBtn);
		miniBtn.setBounds(440, 0, 30, 27);
		loginPanel.add(closeBtn);
		closeBtn.setBounds(470, 0, 30, 27);
		loginPanel.add(headImg);
		headImg.setBounds(47, 91, 100, 100);
		loginPanel.add(jtfAccount);
		jtfAccount.setBounds(160, 101, 210, 40);
		loginPanel.add(pwdfPassword);
		pwdfPassword.setBounds(160, 141, 210, 40);
		loginPanel.add(loginImg);
		loginImg.setBounds(380, 111, 62, 62);
		loginPanel.add(register);
		// register.setBounds(230, 275, 20, 5);
		register.setBounds(230, 240, 100, 20);

		this.setUndecorated(true);
		this.add(loginPanel);
		this.pack();
		this.setLocation(450, 230);

		// this.setLocation(JFrameUtil.getScreenCenterPoint(this));//λ�þ���

		// this.initSystemTray();//����ϵͳ����

		// ��Ӵ����ƶ�����
		LoginWindowMoveListener listener = new LoginWindowMoveListener(this);
		this.addMouseListener(listener);
		this.addMouseMotionListener(listener);

		this.setVisible(true);
	}

	
	public ArrayList<Integer> groupcreate(){
   	 
    	//��ȡȺ�б�
    	     Inquiry inq=new Inquiry();
    	 
			ArrayList<Integer> grouplist=new ArrayList<Integer>();
			grouplist=inq.InqGrouplist(Integer.valueOf(u.getUserId()));
    	   return grouplist ;
    	 
     }
	
	
	class MyPanel extends JPanel {
		private BufferedImage image;

		MyPanel(BufferedImage image) {
			this.image = image;
		}

		@Override
		protected void paintComponent(Graphics g) {
			super.paintComponent(g);

			g.drawImage(image, 0, 0, null);
		}
	}
	public static void main(String[] args) {
		new QqClientLogin();
	}


	@Override
	public void mouseClicked(MouseEvent e) {
		// ����û������¼
				if (e.getSource() ==loginImg ) {
					QqClientUser qqClientUser = new QqClientUser();
					u=new User();
					u.setUserId(jtfAccount.getText().trim());
					u.setPasswd(new String(pwdfPassword.getPassword()));
					if (qqClientUser.checkUser(u) == 1 ) {
						// ��ȡ�����б�
						Inquiry inq = new Inquiry();
						ArrayList<Integer> list = new ArrayList<Integer>();
						list = inq.InqFriendlist(Integer.parseInt(u.getUserId()));
						
						// ���Ǻ��Ѵ��������
						QqFriendList qqList = new QqFriendList(u.getUserId(), list,this.groupcreate());
						QqFriendListManage.addQqFriendList(u.getUserId(), qqList);

						// ��������б���棬ͬʱ�رյ�½����
						this.dispose();
						
						//��ȡ������Ϣ  TODO
						while(inq.OffLineMsg(Integer.parseInt(u.getUserId())).getSender()!=null){
							System.out.println("��������Ϣ");
							Message m = inq.OffLineMsg(Integer.parseInt(u.getUserId()));
							
						//��ʾ��Ϣ
						QqChat qqChat = ManageQqChat.getQqChat(m.getGetter() + " "
								+ m.getSender());
						if(qqChat!=null){
								qqChat.showMessage(m);
						}else{
							QqChat qqChat1=new QqChat(m.getGetter(), m.getSender());
							ManageQqChat.addQqChat( m.getGetter()+ " " + m.getSender(), qqChat1);
							qqChat1.showMessage(m);
						}
						
						//ɾ��������¼
						Delete del = new Delete();
						try {
							int a = del.OffMsg(Integer.parseInt(m.getGetter()),m.getSendTime());
							System.out.println("ɾ�����ִ�н����"+a);
						} catch (NumberFormatException e1) {
							e1.printStackTrace();
						} catch (Exception e1) {
							e1.printStackTrace();
						}
						}
						

					} else if(qqClientUser.checkUser(u) == 2){
						JOptionPane.showMessageDialog(this, "�û������������");
					}
					else if(qqClientUser.checkUser(u) == 3){
						JOptionPane.showMessageDialog(this, "���û��Ѿ�����");
					}
				}
		
	}


	@Override
	public void mousePressed(MouseEvent e) {
	}


	@Override
	public void mouseReleased(MouseEvent e) {
	}


	@Override
	public void mouseEntered(MouseEvent e) {
	}


	@Override
	public void mouseExited(MouseEvent e) {
	}
}
