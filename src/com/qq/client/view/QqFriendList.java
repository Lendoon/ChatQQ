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
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeNode;
import javax.swing.tree.TreePath;

import com.qq.client.tools.ManageClientConServerThread;
import com.qq.client.tools.ManageGroup;
import com.qq.client.tools.ManageQqChat;
import com.qq.client.tools.ManageQqGroupChat;
import com.qq.client.view.util.WindowCloseListener;
import com.qq.client.view.util.WindowMinListener;
import com.qq.client.view.util.WindowMoveListener;
import com.qq.common.Message;
import com.qq.common.MessageType;
import com.qq.jdbc.Delete;
import com.qq.jdbc.Inquiry;

/*
 * �ҵĺ����б�
 */

public class QqFriendList extends JFrame {
	private MyPanel flpanel;
	private Image bgSrc;
	private BufferedImage bgImg;
	private JTabbedPane FlTabPanel;
	private JLabel nickname;
	private JTree friendTree;
	private JButton closeBtn;
	private JButton miniBtn;
	private JScrollPane conversation;
	private JScrollPane friend;
	private JScrollPane group;
	private String owner;
	private ArrayList<Integer> friendList;
	private ArrayList<Integer> grouplist;
	private JTree groupTree;
	private JLabel headimg;
	private JButton findBtn;
	private JButton GroupCreate;
	private JButton deletefriends;
	private JButton change;
	private DefaultMutableTreeNode groups;
	private DefaultMutableTreeNode friends;

	public QqFriendList(String string, ArrayList<Integer> list,
			ArrayList<Integer> groupList) {
		this.grouplist = groupList;
		this.owner = string;
		this.friendList = list;
		try {
			initComponent();
		} catch (IOException e) {
			e.printStackTrace();
		}
		addComponent();
	}

	private void initComponent() throws IOException {
		bgSrc = ImageIO.read(new File("./image/QqFriendList/login_bg.png"));
		bgImg = new BufferedImage(400, 200, BufferedImage.TYPE_INT_RGB);
		Graphics g = bgImg.getGraphics();
		g.drawImage(bgSrc, -70, -128, null);
		flpanel = new MyPanel(bgImg);
		flpanel.setPreferredSize(new Dimension(300, 700));
		flpanel.setBounds(0, 0, 300, 500);

		headimg = new JLabel();
		headimg.setIcon(new ImageIcon("./image/QqFriendList/QQ_64.png"));

		// ��С����ť
		miniBtn = new JButton();
		miniBtn.setIcon(new ImageIcon("./image/QqFriendList/mini.png"));
		miniBtn.setBorderPainted(false);
		miniBtn.addActionListener(new WindowMinListener(this));

		// �˳���ť  
		closeBtn = new JButton();
		closeBtn.setIcon(new ImageIcon("./image/QqFriendList/exit.png"));
		closeBtn.setBorderPainted(false);
		closeBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//TODO ����˳������������رո�����  �����رձ���
				Message m=new Message();
				m.setMesType(MessageType.message_offline);
				m.setSender(owner);
				m.setGetter("10086");
				try {
					ObjectOutputStream oos=new ObjectOutputStream
					(ManageClientConServerThread.getClientConserverThread(owner).getS().getOutputStream());
					oos.writeObject(m);	
				} catch (Exception e2) {
					e2.printStackTrace();
				}
				System.exit(0);
				
			}
		});
		
		Inquiry inq = new Inquiry();
		String name = Integer.parseInt(this.owner) + "("
				+ inq.InqUsers(Integer.parseInt(this.owner)).getName().trim()
				+ ")";
		nickname = new JLabel(name);
		nickname.setFont(new Font("����", Font.PLAIN, 16));

		// DefaultMutableTreeNode friendRoot = new DefaultMutableTreeNode("����");
		friends = new DefaultMutableTreeNode("�ҵĺ���");

		for (int i = 0; i < friendList.size(); i++) {

			DefaultMutableTreeNode friendNode = new DefaultMutableTreeNode(
					friendList.get(i));
			friends.add(friendNode);

			System.out.println(friendList.get(i));
		}
		// friendRoot.add(friends);

		friendTree = new JTree(friends);
		// �����Ƿ���ʾ���ڵ��չ���۵�ͼ�꣬
		// friendTree.setShowsRootHandles(false);
		// ���ýڵ��Ƿ�ɼ�
		// friendTree.setRootVisible(false);
//		javax.swing.SwingUtilities.updateComponentTreeUI(this);
		friendTree.addMouseListener(mouse);

		groups = new DefaultMutableTreeNode("�ҵ�Ⱥ��");
		for (int i = 0; i < grouplist.size(); i++) {
			DefaultMutableTreeNode groupNode = new DefaultMutableTreeNode(
					grouplist.get(i));
			groups.add(groupNode);

		}
		// groupRoot.add(groups);
		groupTree = new JTree(groups);
		groupTree.setShowsRootHandles(false);
		// groupTree.setRootVisible(false);
		// ��Ⱥ������Ӽ���
		groupTree.addMouseListener(mouse1);
		friend = new JScrollPane(friendTree);
		group = new JScrollPane(groupTree);
		FlTabPanel = new JTabbedPane();
		FlTabPanel.add("�Ự", conversation);
		FlTabPanel.add("�ҵĺ���", friend);
		FlTabPanel.add("Ⱥ��", group);
		findBtn = new JButton("����");

		findBtn.setIcon(new ImageIcon("./image/QqFriendList/Finger_Setting.png"));
		findBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				new FindFrame(owner);
			}
		});
		// ����Ⱥ��ť
		GroupCreate = new JButton("����Ⱥ");
		GroupCreate.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				new GroupCreate(owner);
			}
		});

		// ɾ�����Ѻ�Ⱥ�鰴ť
		deletefriends = new JButton("ɾ��");
		deletefriends.addActionListener(new ActionListener() {

			@Override // TODO 
			public void actionPerformed(ActionEvent e) {
				DefaultMutableTreeNode selectNode2 = (DefaultMutableTreeNode) friendTree.getLastSelectedPathComponent();
				Delete del = new Delete();
				if (selectNode2 == null || !selectNode2.isLeaf()) {
					JOptionPane.showMessageDialog(null, "��ѡ��Ҫɾ���ĺ���");
					return;
				}
				int deletefriend = Integer.valueOf(selectNode2.toString());
				try {
					del.DelFriend(Integer.valueOf(owner), deletefriend);
				} catch (NumberFormatException e1) {
					e1.printStackTrace();
				} catch (Exception e1) {
					e1.printStackTrace();
				}
				//ɾ�����غ��ѽ������
				updata(3, selectNode2.toString());
				//ˢ�¶Է������б�
				Message m=new Message();
				m.setSender(owner);
				m.setGetter(selectNode2.toString());
				m.setCon("5");
				m.setMesType(MessageType.message_updata_friendlist);
				try {
					ObjectOutputStream oos=new ObjectOutputStream
					(ManageClientConServerThread.getClientConserverThread(owner).getS().getOutputStream());
					oos.writeObject(m);	
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}
		});

		change = new JButton("����");
	}

	private void addComponent() {

		// ��Ӵ����ƶ�����
		WindowMoveListener listener = new WindowMoveListener(this);
		this.addMouseListener(listener);
		this.addMouseMotionListener(listener);
		flpanel.setLayout(null);
		flpanel.add(miniBtn);
		miniBtn.setBounds(240, 0, 30, 27);
		flpanel.add(closeBtn);
		closeBtn.setBounds(270, 0, 30, 27);
		flpanel.add(FlTabPanel);
		FlTabPanel.setBounds(0, 130, 300, 545);
		flpanel.add(headimg);
		headimg.setBounds(20, 50, 65, 65);
		flpanel.add(nickname);
		nickname.setBounds(100, 40, 200, 50);
		flpanel.add(findBtn);
		findBtn.setBounds(0, 675, 85, 25);
		flpanel.add(GroupCreate);
		GroupCreate.setBounds(85, 675, 75, 25);
		flpanel.add(deletefriends);
		deletefriends.setBounds(160, 675, 70, 25);
		flpanel.add(change);
		change.setBounds(230, 675, 70, 25);
		this.setUndecorated(true);
		this.add(flpanel);
		this.setSize(300, 700);
		this.setLocation(850, 10);
		this.setVisible(true);
	}

	MouseAdapter mouse = new MouseAdapter() {
		public void mouseClicked(MouseEvent e) {
			// ��Ӧ�û�˫�����¼����õ����ѵı��
			if (e.getClickCount() == 2) {
				// TreePath selectNode = friendTree.getPathForLocation(e.getX(),
				// e.getY());
				// �õ��ú��ѵı��
				Object selectNode1 = friendTree.getLastSelectedPathComponent();
				DefaultMutableTreeNode selectNode = (DefaultMutableTreeNode) friendTree
						.getLastSelectedPathComponent();
				// if (selectNode.getPathCount() == 3) {
				if (selectNode.isLeaf()) {
					String selectFriend = selectNode1.toString();

					QqChat qqChat = new QqChat(owner, selectFriend);

					// �����������뵽������
					ManageQqChat.addQqChat(owner + " " + selectFriend, qqChat);
				}
			}
		}
	};
	MouseAdapter mouse1 = new MouseAdapter() {
		public void mouseClicked(MouseEvent e) {
			TreePath tp = groupTree.getPathForLocation(e.getX(), e.getY());

			// ��ȡȺ����
			Object selectnode = groupTree.getLastSelectedPathComponent();
			if (e.getClickCount() == 2) {
				if (tp.getPathCount() == 2) {
					String selectgroup = selectnode.toString();
					GroupChat groupchat = new GroupChat(owner, selectgroup);
					ManageQqGroupChat.addQqGroupChat(selectgroup, groupchat);
					ManageGroup.groupmap.put(owner, groupchat);
				}
			}

		};
	};

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

	public void updata(int type, String string) {
		DefaultMutableTreeNode selectNode;
		JTree tree;
		DefaultTreeModel model;
		if (type == 3 || type == 4 || type==5 ) {
			if (type == 3) {
				selectNode = (DefaultMutableTreeNode) friendTree
						.getLastSelectedPathComponent();
				tree = friendTree;
			} else if( type==5 ){
				// TODO selectNode�ĳ�Ҫ��ɾ���Ľ��
				selectNode = null;
System.out.println("getChildCount:"+friends.getChildCount());
				//��ȡ�����б����ĵ�һ�����
				DefaultMutableTreeNode temp = (DefaultMutableTreeNode) friends.getFirstChild();
				//���������б������Ƿ�Ϊ��ɾ���ĺ���
				while(temp!=null){
System.out.println(string+"���Ա�����"+temp.getUserObject().toString());
					if( temp.getUserObject().toString().equals(string)){
						System.out.println("ѡ����");
						selectNode = temp;
						break;
					}else
						temp = temp.getNextLeaf();
				}
				tree = friendTree;
			}
			else {
				selectNode = (DefaultMutableTreeNode) groupTree
						.getLastSelectedPathComponent();
				tree = groupTree;
			}
			if (selectNode == null || !selectNode.isLeaf()) {
				JOptionPane.showMessageDialog(this, "��ѡ��Ҫɾ���ĺ��ѻ�Ⱥ");
				return;
			}
			model = (DefaultTreeModel) tree.getModel();
			model.removeNodeFromParent(selectNode);
		} else {
			if (type == 1) {
				selectNode = friends;
				tree = friendTree;
			} else {
				selectNode = groups;
				tree = groupTree;
			}
			DefaultMutableTreeNode newNode = new DefaultMutableTreeNode(string);

			selectNode.add(newNode);
			model = (DefaultTreeModel) tree.getModel();
			TreeNode[] nodes = model.getPathToRoot(newNode);
			TreePath path = new TreePath(nodes);
			tree.scrollPathToVisible(path);
		}
		tree.updateUI();
	}
	public static void main(String[] args) {
		ArrayList<Integer> list = new ArrayList<Integer>();
		ArrayList<Integer> groupList = new ArrayList<Integer>();
		list.add(12580);
		list.add(12589);
		groupList.add(123);
		new QqFriendList("test", list, groupList);
		
	}
}
