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
	JLabel close;// ����رձ�ǩ
	JLabel min;// ������С����ť
	JLabel head, name;// ����ͷ���ǩ
	Icon icon, icon2, icon3;// ����ر�ͼ��
	Icon minIcon1, minIcon2, minIcon3;// ������С��ͼ��
	Point pre, dra;
	JTextArea inputText;
	JScrollPane jspsr, jspxx;// ����������
	JButton closeButton, sendButton;
	static JTree memberTree;
	newPanel np;// ���屳�����
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
		
		addRemBtn = new JButton("����"); // ���Ⱥ��Ա
		addRemBtn.setBounds(465, 461, 60, 30);
		addRemBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				new MyJDialog(GroupChat.this).setVisible(true);
			}
		});
		deleteRemBtn = new JButton("�߳�");
		deleteRemBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				DefaultMutableTreeNode selectNode = (DefaultMutableTreeNode) memberTree
						.getLastSelectedPathComponent();
				if (selectNode == null || !selectNode.isLeaf()) {
					JOptionPane.showMessageDialog(memberPane, "����ѡ��Ҫ�߳��ĳ�Ա",
							"��ʾ", JOptionPane.INFORMATION_MESSAGE);
					return;
				}
				int flag = JOptionPane
						.showConfirmDialog(memberPane, "ȷ���߳��ó�Ա��");
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

		np = new newPanel();// ʵ�����ڲ���������
		np.setLayout(null);// ����np���Ĳ���
		/* ��С����ǩ��ť */
		minIcon1 = new ImageIcon("./image/HomeScreen/min1.png");
		minIcon2 = new ImageIcon("./image/HomeScreen/min2.png");
		minIcon3 = new ImageIcon("./image/HomeScreen/min3.png");
		min = new JLabel();
		min.setBounds(533, 0, 28, 18);
		min.setIcon(minIcon1);
		min.addMouseListener(this);
		np.add(min);
		/* close��ǩ��ť */
		icon = new ImageIcon("./image/HomeScreen/btn_close_normal.gif");// close��ǩͼ��
		icon2 = new ImageIcon("./image/HomeScreen/btn_close_highlight.png");// close��ǩͼ��
		icon3 = new ImageIcon("./image/HomeScreen/btn_close_down.png");// close��ǩͼ��
		close = new JLabel();
		close.setBounds(561, 0, 39, 18);
		close.setIcon(icon);
		np.add(close);// ��close���뵽np���
		/* �������� */
		Inquiry inq=new Inquiry();
		Groups groupinfo=inq.InqGroups(Integer.valueOf(group));
		
		name = new JLabel(groupinfo.getName().trim()+"("+group+")");
		name.setOpaque(false);
		name.setFont(new Font("����", Font.PLAIN, 20));
		name.setBounds(80, 30, 250, 50);
		np.add(name);
		// ������Ϣ��ʾ���������
		JPanel np1 = new JPanel();
		np1.setLayout(null);
		/* ������Ϣ�� */
		content = new JTextArea();
		content.setBorder(BorderFactory.createLineBorder(Color.gray, 0));
		jspxx = new JScrollPane(content);
		jspxx.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
		content.setOpaque(false);
		content.setLineWrap(true);
		/* ��������� */
		inputText = new JTextArea();
		inputText.setOpaque(false);
		inputText.setLineWrap(true);
		inputText.setFont(new Font("����", Font.PLAIN, 15));
		jspsr = new JScrollPane(inputText);
		jspsr.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
		// ���÷ָ����
		np1.setBounds(0, 100, 450, 350);
		JSplitPane jsp = new JSplitPane(JSplitPane.VERTICAL_SPLIT, jspxx, jspsr);
		jsp.setPreferredSize(new Dimension(450, 350));
		jsp.setBounds(0, -1, 450, 350);
		jsp.setContinuousLayout(true);
		jsp.setDividerLocation(230);
		jsp.setDividerSize(20);
		np1.add(jsp);
		np.add(np1);
		// �رա����Ͱ�ť
		closeButton = new JButton("�ر�(C)");
		closeButton.setMnemonic('C');
		closeButton.setBounds(260, 460, 80, 25);
		closeButton.setBorder(BorderFactory.createLineBorder(Color.cyan));
		closeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});

		memberRoot = new DefaultMutableTreeNode("Ⱥ��Ա");
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
		sendButton = new JButton("����(s)");
		sendButton.setMnemonic('S');
		sendButton.setBounds(370, 460, 80, 25);
		sendButton.setBorder(BorderFactory.createLineBorder(Color.cyan));
		final String o_id = ownerId;
		final String f_id = group;
		sendButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// ����û�����˷��Ͱ�ť
				Message m = new Message();
				m.setMesType(MessageType.message_group_chat);
				m.setSender(o_id);
				m.setGetter(f_id);
				m.setCon(inputText.getText());
				m.setSendTime(new java.util.Date().toString());
				// ���͵�������
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

		// ��closeע�����
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
		this.add(np);// ����������뵽������
		this.setUndecorated(true);// ���ô���Ϊδ�����ε�
		this.setSize(600, 500);
		this.setLocation(
				(Toolkit.getDefaultToolkit().getScreenSize().width - 600) / 2,
				(Toolkit.getDefaultToolkit().getScreenSize().height - 400) / 2);// ���ô������λ��
		this.setVisible(true);// �������
		this.setDefaultCloseOperation(3);// ���ô���رշ�ʽ
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

			JOptionPane.showMessageDialog(memberPane, "��ӳɹ�", "��ʾ",
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
			//���͵�������
			try {
				ObjectOutputStream oos=new ObjectOutputStream
				(ManageClientConServerThread.getClientConserverThread(ownerId).getS().getOutputStream());
				oos.writeObject(m);	
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		
		} else {
			JOptionPane.showMessageDialog(memberPane, "���ʧ�ܣ��޴˺���", "��ʾ",
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

	// дһ��������������ʾ��Ϣ
	public void showMessage(Message m) {
		Inquiry inq=new Inquiry();
		Users userinfo=inq.InqUsers(Integer.valueOf(m.getSender()));
		String info = userinfo.getName().trim() + "˵��" + m.getCon()
				+ "\r\n";
		this.content.append(info);
	}

	@Override
	public void actionPerformed(ActionEvent e) {

	}

}
