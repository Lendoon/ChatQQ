package com.qq.client.view;
/*
 * �����������
 * ��Ϊ�ͻ���Ҫ���ڶ�ȡ��״̬�����Ҫ��������һ���߳�
 */
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.ObjectOutputStream;

import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextArea;

import com.qq.client.tools.ManageClientConServerThread;
import com.qq.common.Message;
import com.qq.common.MessageType;
import com.qq.jdbc.Inquiry;

public class QqChat extends JFrame implements MouseListener{
	JTextArea content;
//	JPanel Jp;
	String Owner;
	String OwnerId;
	String Friend;
	String FriendId;

	JLabel close;//����رձ�ǩ
	JLabel min;//������С����ť
	JLabel head,name;//����ͷ���ǩ
	Icon icon,icon2,icon3;//����ر�ͼ��
	Icon minIcon1,minIcon2,minIcon3;//������С��ͼ��
	Point pre,dra;
	JTextArea inputText;
	JScrollPane jspsr,jspxx;//����������
	JButton closeButton,sendButton;
	newPanel np;//���屳�����
	JButton jb_file;//�ļ���ť
	JButton jb_history;//�����¼��ť
	JFileChooser jfc=new JFileChooser(".");
	
//	public static void main(String[] args) {
//		QqChat qqchat=new QqChat("128001", "128002");
//	}
	
	public QqChat(String ownerId,String friend){
	    super("JFrame Testing");
		np=new newPanel();//ʵ�����ڲ���������
		np.setLayout(null);//����np���Ĳ���
		FriendId = friend;
		OwnerId = ownerId;
		
		/*��С����ǩ��ť*/
		minIcon1=new ImageIcon("./image/HomeScreen/min1.png");
		minIcon2=new ImageIcon("./image/HomeScreen/.png");
		minIcon3=new ImageIcon("./image/HomeScreen/min3.png");
		min=new JLabel();
		min.setBounds(533, 0, 28,18);
		min.setIcon(minIcon1);
		min.addMouseListener(this);
		np.add(min);
		
		/*close��ǩ��ť*/
		icon=new ImageIcon("./image/HomeScreen/btn_close_normal.gif");//close��ǩͼ��
		icon2=new ImageIcon("./image/HomeScreen/btn_close_highlight.png");//close��ǩͼ��
		icon3=new ImageIcon("./image/HomeScreen/btn_close_down.png");//close��ǩͼ��
		close=new JLabel();
		close.setBounds(561,0,39,18);
		close.setIcon(icon);
		np.add(close);//��close���뵽np���
		
		/*��������*/
		Inquiry inq = new Inquiry();
		Friend = inq.InqUsers(Integer.parseInt(friend)).getName().trim();
		Owner = inq.InqUsers(Integer.parseInt(ownerId)).getName().trim();
		name=new JLabel("<HTML>"+Friend+ "("+ friend +")"+"</HTML>");
		name.setOpaque(false);
		name.setFont(new Font("����",Font.PLAIN,20));
		name.setBounds(30,30,250,50);
		np.add(name);
		//������Ϣ��ʾ���������
		JPanel np1=new JPanel();
		np1.setLayout(null);
		/*������Ϣ��*/
		content=new JTextArea();
		content.setBorder(BorderFactory.createLineBorder(Color.gray,0));
		jspxx=new JScrollPane(content);
		jspxx.setBorder(BorderFactory.createEmptyBorder(0, 0, 0,0));
		content.setEditable(false);
		content.setOpaque(false);
		content.setLineWrap(true);
		
		/*���������*/
		inputText=new JTextArea();
		inputText.setOpaque(false);
		inputText.setLineWrap(true);
		inputText.setFont(new Font("����",Font.PLAIN,15));
		jspsr=new JScrollPane(inputText);
		jspsr.setBorder(BorderFactory.createEmptyBorder(0, 0, 0,0));
		
		//���÷ָ����
		np1.setBounds(0,100,450,350);
		JSplitPane jsp=new JSplitPane(JSplitPane.VERTICAL_SPLIT,jspxx,jspsr);
		jsp.setPreferredSize(new Dimension(450,350));
		jsp.setBounds(0,-1,450,350);
		jsp.setContinuousLayout(true);
		jsp.setDividerLocation(230);
		jsp.setDividerSize(20);
		np1.add(jsp);
		np.add(np1);
		
		//�رա����Ͱ�ť
		closeButton=new JButton("�ر�(C)");
		closeButton.setMnemonic('C');
		closeButton.setBounds(260,460,80,25);
		closeButton.setBorder(BorderFactory.createLineBorder(Color.cyan));
		closeButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		np.add(closeButton);
		sendButton=new JButton("����(s)");
		sendButton.setMnemonic('S');
		sendButton.setBounds(370,460,80,25);
		sendButton.setBorder(BorderFactory.createLineBorder(Color.cyan));
		final String o_id = ownerId;      //�Լ���id����
		final String f_id = friend;       //Ҫ����ĺ��ѵ�id����
		sendButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				//����û�����˷��Ͱ�ť
				Message m=new Message();
				m.setMesType(MessageType.message_comm_mes);
				m.setSender(o_id);
				m.setGetter(f_id);
				m.setCon(inputText.getText());   //�õ����͵���Ϣ
				m.setSendTime(new java.util.Date().toString());  //�õ�������Ϣ��ʱ��
				//���͵�������
				showMessage(m);
				try {
					ObjectOutputStream oos=new ObjectOutputStream
					(ManageClientConServerThread.getClientConserverThread(o_id).getS().getOutputStream());
					oos.writeObject(m);	
					inputText.setText("");
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}
		});
		np.add(sendButton);
		
		//��closeע�����
		close.addMouseListener(this);
		np.addMouseListener(new MouseAdapter(){
			public void mousePressed(MouseEvent e){
				pre=new Point(e.getX(),e.getY());
			}
		});
		np.addMouseMotionListener(new MouseAdapter(){
			public void mouseDragged(MouseEvent e){
				dra=new Point(getLocation().x+e.getX()-pre.x,getLocation().y+e.getY()-pre.y);
				setLocation(dra);
			}
		});
		np.setBorder(BorderFactory.createLineBorder(Color.black));
		jb_file=new JButton("�����ļ�");
		jb_file.setBounds(475 ,100 , 86, 30);
		jb_file.addMouseListener(this);
		this.add(jb_file);
		jb_history=new JButton("�����¼");
		jb_history.setBounds(475 ,140 , 86, 30);
		jb_history.addMouseListener(this);
		this.add(jb_history);
		
		this.add(np);//����������뵽������
		this.setUndecorated(true);//���ô���Ϊδ�����ε�
		this.setSize(600,500);
		this.setLocation((Toolkit.getDefaultToolkit().getScreenSize().width-600)/2,(Toolkit.getDefaultToolkit().getScreenSize().height-400)/2);//���ô������λ��
		this.setVisible(true);//�������
		this.setDefaultCloseOperation(3);//���ô���رշ�ʽ
	}
         
	public void mouseEntered(MouseEvent e){
	}
	public void mouseExited(MouseEvent e){
	}
	public void mouseClicked(MouseEvent e){
		if(e.getSource()==min){
			this.setExtendedState(ICONIFIED);
		}
		else if(e.getSource()==close){
			dispose();
		}
		else if(e.getSource()==jb_file){
			jfc.showDialog(this,"ѡ���ļ�");
		}
		else if(e.getSource()==jb_history){
			try {
				new QqHistory(OwnerId,FriendId);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
	}
	public void mousePressed(MouseEvent e){
	}
	public void mouseReleased(MouseEvent e){
	}
	
	class newPanel extends JPanel{
		public newPanel(){}
		public void paintComponent(Graphics g){
			ImageIcon image=new ImageIcon("./image/HomeScreen/background.jpg");
			g.drawImage(image.getImage(),0,0,getSize().width,getSize().height,this);
		}
	}
	
	//дһ��������������ʾ��Ϣ
	public void showMessage(Message m){
		String info;
		if(m.getSender().equals(FriendId)  )
			info= Friend +" �� "+m.getCon()+"\r\n";
		else
			info= Owner +" �� "+m.getCon()+"\r\n";
     	this.content.append(info);
	}
}
