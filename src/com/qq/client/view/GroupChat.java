package com.qq.client.view;

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
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextArea;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeNode;
import javax.swing.tree.TreePath;

import com.qq.client.tools.ManageClientConServerThread;
import com.qq.common.Message;
import com.qq.common.MessageType;
import com.qq.info.Groups;
import com.qq.info.Users;
import com.qq.jdbc.Delete;
import com.qq.jdbc.Inquiry;
import com.qq.jdbc.Insert;

public class GroupChat extends JFrame implements ActionListener, MouseListener {
	JTextArea content;
	static JScrollPane memberPane;
	JButton addRemBtn;
	JButton deleteRemBtn;
	JPanel jp;
	static String ownerId;
	String friend;
	String friendId;
	JLabel close;// 定义关闭标签
	JLabel min;// 定义最小化按钮
	JLabel head, name;// 定义头像标签
	Icon icon, icon2, icon3;// 定义关闭图标
	Icon minIcon1, minIcon2, minIcon3;// 定义最小化图标
	Point pre, dra;
	JTextArea inputText;
	JScrollPane jspsr, jspxx;// 定义滚动面板
	JButton closeButton, sendButton;
	static JTree memberTree;
	newPanel np;// 定义背景面板
	String owner;
	private static DefaultMutableTreeNode memberRoot;
	private static String group;

	// public static void main(String[] args) {
	// GroupChat qqchat=new GroupChat("1", "2");
	// }
	public GroupChat(String ownerId, final String group) {
		super("JFrame Testing");
		this.ownerId = ownerId;
		this.group = group;
		
		addRemBtn = new JButton("邀请"); // 添加群成员
		addRemBtn.setBounds(465, 461, 60, 30);
		addRemBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				new MyJDialog(GroupChat.this).setVisible(true);
			}
		});
		deleteRemBtn = new JButton("踢出");
		deleteRemBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				DefaultMutableTreeNode selectNode = (DefaultMutableTreeNode) memberTree
						.getLastSelectedPathComponent();
				if (selectNode == null || !selectNode.isLeaf()) {
					JOptionPane.showMessageDialog(memberPane, "请先选择要踢出的成员",
							"提示", JOptionPane.INFORMATION_MESSAGE);
					return;
				}
				int flag = JOptionPane
						.showConfirmDialog(memberPane, "确认踢出该成员？");
				if (flag == 0) {
					DefaultTreeModel model = (DefaultTreeModel) memberTree
							.getModel();
					model.removeNodeFromParent(selectNode);
					Delete de = new Delete();
					try {
						de.DelGroupMenber(Integer.valueOf(group), Integer.valueOf(selectNode.toString()));
					} catch (NumberFormatException e1) {
						e1.printStackTrace();
					} catch (Exception e1) {
						e1.printStackTrace();
					}
				}

			}
		});
		deleteRemBtn.setBounds(531, 461, 60, 30);

		np = new newPanel();// 实例化内部类面板对象
		np.setLayout(null);// 设置np面板的布局
		/* 最小化标签按钮 */
		minIcon1 = new ImageIcon("./image/HomeScreen/min1.png");
		minIcon2 = new ImageIcon("./image/HomeScreen/min2.png");
		minIcon3 = new ImageIcon("./image/HomeScreen/min3.png");
		min = new JLabel();
		min.setBounds(533, 0, 28, 18);
		min.setIcon(minIcon1);
		min.addMouseListener(this);
		np.add(min);
		/* close标签按钮 */
		icon = new ImageIcon("./image/HomeScreen/btn_close_normal.gif");// close标签图案
		icon2 = new ImageIcon("./image/HomeScreen/btn_close_highlight.png");// close标签图案
		icon3 = new ImageIcon("./image/HomeScreen/btn_close_down.png");// close标签图案
		close = new JLabel();
		close.setBounds(561, 0, 39, 18);
		close.setIcon(icon);
		np.add(close);// 将close加入到np面板
		/* 设置网名 */
		Inquiry inq=new Inquiry();
		Groups groupinfo=inq.InqGroups(Integer.valueOf(group));
		
		name = new JLabel(groupinfo.getName().trim()+"("+group+")");
		name.setOpaque(false);
		name.setFont(new Font("仿宋", Font.PLAIN, 20));
		name.setBounds(80, 30, 250, 50);
		np.add(name);
		// 创建消息显示、输入面板
		JPanel np1 = new JPanel();
		np1.setLayout(null);
		/* 创建消息框 */
		content = new JTextArea();
		content.setBorder(BorderFactory.createLineBorder(Color.gray, 0));
		jspxx = new JScrollPane(content);
		jspxx.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
		content.setOpaque(false);
		content.setLineWrap(true);
		/* 创建输入框 */
		inputText = new JTextArea();
		inputText.setOpaque(false);
		inputText.setLineWrap(true);
		inputText.setFont(new Font("仿宋", Font.PLAIN, 15));
		jspsr = new JScrollPane(inputText);
		jspsr.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
		// 设置分割面板
		np1.setBounds(0, 100, 450, 350);
		JSplitPane jsp = new JSplitPane(JSplitPane.VERTICAL_SPLIT, jspxx, jspsr);
		jsp.setPreferredSize(new Dimension(450, 350));
		jsp.setBounds(0, -1, 450, 350);
		jsp.setContinuousLayout(true);
		jsp.setDividerLocation(230);
		jsp.setDividerSize(20);
		np1.add(jsp);
		np.add(np1);
		// 关闭、发送按钮
		closeButton = new JButton("关闭(C)");
		closeButton.setMnemonic('C');
		closeButton.setBounds(260, 460, 80, 25);
		closeButton.setBorder(BorderFactory.createLineBorder(Color.cyan));
		closeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});

		memberRoot = new DefaultMutableTreeNode("群成员");
		Inquiry inquiry = new Inquiry();
		ArrayList<Integer> memberList = inquiry.InqGroupMemberlist(Integer
				.valueOf(group));
		for (Integer i : memberList) {
			memberRoot.add(new DefaultMutableTreeNode(i));
		}
		memberTree = new JTree(memberRoot);
		memberPane = new JScrollPane(memberTree);
		memberPane.setBounds(455, 255, 140, 195);

		np.add(closeButton);
		sendButton = new JButton("发送(s)");
		sendButton.setMnemonic('S');
		sendButton.setBounds(370, 460, 80, 25);
		sendButton.setBorder(BorderFactory.createLineBorder(Color.cyan));
		final String o_id = ownerId;
		final String f_id = group;
		sendButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// 如果用户点击了发送按钮
				Message m = new Message();
				m.setMesType(MessageType.message_group_chat);
				m.setSender(o_id);
				m.setGetter(f_id);
				m.setCon(inputText.getText());
				m.setSendTime(new java.util.Date().toString());
				// 发送到服务器
				try {
					ObjectOutputStream oos = new ObjectOutputStream(
							ManageClientConServerThread
									.getClientConserverThread(o_id).getS()
									.getOutputStream());
					oos.writeObject(m);
					inputText.setText("");
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}
		});
		np.add(sendButton);

		// 对close注册监听
		close.addMouseListener(this);
		np.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				pre = new Point(e.getX(), e.getY());
			}
		});
		np.addMouseMotionListener(new MouseAdapter() {
			public void mouseDragged(MouseEvent e) {
				dra = new Point(getLocation().x + e.getX() - pre.x,
						getLocation().y + e.getY() - pre.y);
				setLocation(dra);
			}
		});
		np.setBorder(BorderFactory.createLineBorder(Color.black));
		this.add(memberPane);
		this.add(addRemBtn);
		this.add(deleteRemBtn);
		this.add(np);// 将面板对象加入到容器里
		this.setUndecorated(true);// 设置窗体为未加修饰的
		this.setSize(600, 500);
		this.setLocation(
				(Toolkit.getDefaultToolkit().getScreenSize().width - 600) / 2,
				(Toolkit.getDefaultToolkit().getScreenSize().height - 400) / 2);// 设置窗体相对位置
		this.setVisible(true);// 窗体可视
		this.setDefaultCloseOperation(3);// 设置窗体关闭方式
	}

	public static void groupId(String memberId) {
		memberId = memberId;
		// System.out.println(groupId);

		Inquiry inq = new Inquiry();
		Users users = inq.InqUsers(Integer.valueOf(memberId));
		int birthday = users.getBirthday();
		if (birthday != 0) {
			Insert ins = new Insert();
			ins.saveGroupList(Integer.valueOf(group), Integer.valueOf(memberId));

			JOptionPane.showMessageDialog(memberPane, "添加成功", "提示",
					JOptionPane.INFORMATION_MESSAGE);
			
			DefaultMutableTreeNode newNode = new DefaultMutableTreeNode(
					memberId);

			memberRoot.add(newNode);
			DefaultTreeModel model = (DefaultTreeModel) memberTree.getModel();
			TreeNode[] nodes = model.getPathToRoot(newNode);
			TreePath path = new TreePath(nodes);
			memberTree.scrollPathToVisible(path);
			memberTree.updateUI();
			
			Message m=new Message();
			m.setSender(ownerId);
			m.setGetter(memberId);
			m.setCon("2/"+group);
			m.setMesType(MessageType.message_updata_grouplist);
			//发送到服务器
			try {
				ObjectOutputStream oos=new ObjectOutputStream
				(ManageClientConServerThread.getClientConserverThread(ownerId).getS().getOutputStream());
				oos.writeObject(m);	
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		
		} else {
			JOptionPane.showMessageDialog(memberPane, "添加失败，无此好友", "提示",
					JOptionPane.INFORMATION_MESSAGE);
		}

	}

	public void mouseEntered(MouseEvent e) {
	}

	public void mouseExited(MouseEvent e) {
	}

	public void mouseClicked(MouseEvent e) {

		if (e.getSource() == min) {
			this.setExtendedState(ICONIFIED);
		}
		if (e.getSource() == close) {
			this.dispose();
		}
	}

	public void mousePressed(MouseEvent e) {
	}

	public void mouseReleased(MouseEvent e) {

	}

	class newPanel extends JPanel {
		public newPanel() {
		}

		public void paintComponent(Graphics g) {
			ImageIcon image = new ImageIcon("./image/HomeScreen/background.jpg");
			g.drawImage(image.getImage(), 0, 0, getSize().width,
					getSize().height, this);
		}
	}

	// 写一个方法，让它显示消息
	public void showMessage(Message m) {
		Inquiry inq=new Inquiry();
		Users userinfo=inq.InqUsers(Integer.valueOf(m.getSender()));
		String info = userinfo.getName().trim() + "说：" + m.getCon()
				+ "\r\n";
		this.content.append(info);
	}

	@Override
	public void actionPerformed(ActionEvent e) {

	}

}
