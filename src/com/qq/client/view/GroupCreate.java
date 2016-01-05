package com.qq.client.view;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreePath;

import org.omg.CORBA.PUBLIC_MEMBER;

import com.qq.client.tools.ManageClientConServerThread;
import com.qq.client.tools.ManageQqChat;
import com.qq.client.tools.QqFriendListManage;
import com.qq.client.view.QqFriendList.MyPanel;
import com.qq.common.Group;
import com.qq.common.Message;
import com.qq.common.MessageType;
import com.qq.common.User;
import com.qq.info.Groups;
import com.qq.jdbc.Inquiry;
import com.qq.jdbc.Insert;

public class GroupCreate extends JFrame implements ActionListener{

	
	private BufferedImage bgImg;
	private Image bgSrc;
	private MyPanel panel;
	private JLabel jl;
	private JLabel jl1;
	private String owner;
	private String[] finder;
	private JButton jb1;
	private JButton jb2;
	private  int GroupId=0;
	private JLabel jlname;
	private JTextField jtef;
	

 public GroupCreate(String owner){
	   createGroup();
		this.owner = owner;
		try {
			this.CreateGroupFrameInit();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
 
 //获得群id号码
  public void createGroup(){
	  Inquiry inq=new Inquiry();
	 Group g=new Group();
	  try {
		GroupId =inq.GroupMaxID()+1;
		g.setGroupId(GroupId);
System.out.println(GroupId);
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	  
  }
 
	//初始化群创建窗体
	private void CreateGroupFrameInit() throws IOException {
		this.setLayout(null);
        jl=new JLabel();
        jl.setText(String.valueOf(GroupId));
 System.out.println(GroupId);
        jlname=new JLabel("群昵称");
        jlname.setBounds(50, 200, 100, 40);
        jtef=new JTextField();
        jtef.setBounds(150, 200, 250, 40);
        jb1=new JButton("确定");
        jb1.addActionListener(this);
        jb1.setBounds(50, 260, 100, 40);
        jb2=new JButton("取消");
        jb2.setBounds(150, 260, 100, 40);
		bgImg = new BufferedImage(750, 450, BufferedImage.TYPE_INT_RGB);
		bgSrc = ImageIO.read(new File("./image/CreateGroup/GroupCreate.jpg"));
		Graphics g = bgImg.getGraphics();
		g.drawImage(bgSrc, -100, 0, null);
		this.add(jl);
		this.add(jb1);
		this.add(jb2);
		this.add(jtef);
		this.add(jlname);
		jl.setBounds(10, 10, 120, 50);
		panel = new MyPanel(bgImg);
		panel.setPreferredSize(new Dimension(750, 450));
		panel.setLayout(null);
		this.add(panel);
		panel.setBounds(0, 0, 740, 335);
		this.setBounds(500, 200, 640, 335);
		this.setVisible(true);
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

     private void saved(){
    	 Insert ins=new Insert();
    	 Groups gr=new Groups();
    	 gr.setGroupID(GroupId);
    	 gr.setAdminid(Integer.valueOf(owner));
    	 gr.setName(jtef.getText().trim());
         ins.save(gr);
         ins.saveGroupList(GroupId, Integer.valueOf(owner));
    	 
     }
    
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
           if(e.getSource()==jb1){
        	   
        	   saved();
        QqFriendListManage.getQqFriendList(owner).updata(0,String.valueOf(GroupId));
        	   this.dispose();
           }else if(e.getSource()==jb2){
        	   this.dispose();
           }
	}


	
}
