package com.qq.client.view;
/*
 * 好友聊天界面
 * 因为客户端要处于读取的状态，因此要把它做成一个线程
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

	JLabel close;//定义关闭标签
	JLabel min;//定义最小化按钮
	JLabel head,name;//定义头像标签
	Icon icon,icon2,icon3;//定义关闭图标
	Icon minIcon1,minIcon2,minIcon3;//定义最小化图标
	Point pre,dra;
	JTextArea inputText;
	JScrollPane jspsr,jspxx;//定义滚动面板
	JButton closeButton,sendButton;
	newPanel np;//定义背景面板
	JButton jb_file;//文件按钮
	JButton jb_history;//聊天记录按钮
	JFileChooser jfc=new JFileChooser(".");
	
//	public static void main(String[] args) {
//		QqChat qqchat=new QqChat("128001", "128002");
//	}
	
	public QqChat(String ownerId,String friend){
	    super("JFrame Testing");
		np=new newPanel();//实例化内部类面板对象
		np.setLayout(null);//设置np面板的布局
		FriendId = friend;
		OwnerId = ownerId;
		
		/*最小化标签按钮*/
		minIcon1=new ImageIcon("./image/HomeScreen/min1.png");
		minIcon2=new ImageIcon("./image/HomeScreen/.png");
		minIcon3=new ImageIcon("./image/HomeScreen/min3.png");
		min=new JLabel();
		min.setBounds(533, 0, 28,18);
		min.setIcon(minIcon1);
		min.addMouseListener(this);
		np.add(min);
		
		/*close标签按钮*/
		icon=new ImageIcon("./image/HomeScreen/btn_close_normal.gif");//close标签图案
		icon2=new ImageIcon("./image/HomeScreen/btn_close_highlight.png");//close标签图案
		icon3=new ImageIcon("./image/HomeScreen/btn_close_down.png");//close标签图案
		close=new JLabel();
		close.setBounds(561,0,39,18);
		close.setIcon(icon);
		np.add(close);//将close加入到np面板
		
		/*设置网名*/
		Inquiry inq = new Inquiry();
		Friend = inq.InqUsers(Integer.parseInt(friend)).getName().trim();
		Owner = inq.InqUsers(Integer.parseInt(ownerId)).getName().trim();
		name=new JLabel("<HTML>"+Friend+ "("+ friend +")"+"</HTML>");
		name.setOpaque(false);
		name.setFont(new Font("仿宋",Font.PLAIN,20));
		name.setBounds(30,30,250,50);
		np.add(name);
		//创建消息显示、输入面板
		JPanel np1=new JPanel();
		np1.setLayout(null);
		/*创建消息框*/
		content=new JTextArea();
		content.setBorder(BorderFactory.createLineBorder(Color.gray,0));
		jspxx=new JScrollPane(content);
		jspxx.setBorder(BorderFactory.createEmptyBorder(0, 0, 0,0));
		content.setEditable(false);
		content.setOpaque(false);
		content.setLineWrap(true);
		
		/*创建输入框*/
		inputText=new JTextArea();
		inputText.setOpaque(false);
		inputText.setLineWrap(true);
		inputText.setFont(new Font("仿宋",Font.PLAIN,15));
		jspsr=new JScrollPane(inputText);
		jspsr.setBorder(BorderFactory.createEmptyBorder(0, 0, 0,0));
		
		//设置分割面板
		np1.setBounds(0,100,450,350);
		JSplitPane jsp=new JSplitPane(JSplitPane.VERTICAL_SPLIT,jspxx,jspsr);
		jsp.setPreferredSize(new Dimension(450,350));
		jsp.setBounds(0,-1,450,350);
		jsp.setContinuousLayout(true);
		jsp.setDividerLocation(230);
		jsp.setDividerSize(20);
		np1.add(jsp);
		np.add(np1);
		
		//关闭、发送按钮
		closeButton=new JButton("关闭(C)");
		closeButton.setMnemonic('C');
		closeButton.setBounds(260,460,80,25);
		closeButton.setBorder(BorderFactory.createLineBorder(Color.cyan));
		closeButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		np.add(closeButton);
		sendButton=new JButton("发送(s)");
		sendButton.setMnemonic('S');
		sendButton.setBounds(370,460,80,25);
		sendButton.setBorder(BorderFactory.createLineBorder(Color.cyan));
		final String o_id = ownerId;      //自己的id号码
		final String f_id = friend;       //要聊天的好友的id号码
		sendButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				//如果用户点击了发送按钮
				Message m=new Message();
				m.setMesType(MessageType.message_comm_mes);
				m.setSender(o_id);
				m.setGetter(f_id);
				m.setCon(inputText.getText());   //得到发送的消息
				m.setSendTime(new java.util.Date().toString());  //得到发送消息的时间
				//发送到服务器
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
		
		//对close注册监听
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
		jb_file=new JButton("发送文件");
		jb_file.setBounds(475 ,100 , 86, 30);
		jb_file.addMouseListener(this);
		this.add(jb_file);
		jb_history=new JButton("聊天记录");
		jb_history.setBounds(475 ,140 , 86, 30);
		jb_history.addMouseListener(this);
		this.add(jb_history);
		
		this.add(np);//将面板对象加入到容器里
		this.setUndecorated(true);//设置窗体为未加修饰的
		this.setSize(600,500);
		this.setLocation((Toolkit.getDefaultToolkit().getScreenSize().width-600)/2,(Toolkit.getDefaultToolkit().getScreenSize().height-400)/2);//设置窗体相对位置
		this.setVisible(true);//窗体可视
		this.setDefaultCloseOperation(3);//设置窗体关闭方式
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
			jfc.showDialog(this,"选择文件");
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
	
	//写一个方法，让它显示消息
	public void showMessage(Message m){
		String info;
		if(m.getSender().equals(FriendId)  )
			info= Friend +" ： "+m.getCon()+"\r\n";
		else
			info= Owner +" ： "+m.getCon()+"\r\n";
     	this.content.append(info);
	}
}
