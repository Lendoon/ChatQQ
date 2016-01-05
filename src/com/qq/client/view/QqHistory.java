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
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.ListIterator;
import java.util.Map;

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

import com.qq.jdbc.Inquiry;

public class QqHistory extends JFrame implements MouseListener {
	JTextArea content;
	JPanel jp;
	String Owner;
	int OwnerId;
	String Friend;
	int FriendId;
	Inquiry inq;//���ݿ��ѯ��

	JLabel close;// ����رձ�ǩ
	JLabel min;// ������С����ť
	JLabel head, name;// ����ͷ���ǩ
	Icon icon, icon2, icon3;// ����ر�ͼ��
	Icon minIcon1, minIcon2, minIcon3;// ������С��ͼ��
	Point pre, dra;
	JTextArea inputText;
	JScrollPane jspsr, jspxx;// ����������
	JButton closeButton;
	newPanel np;// ���屳�����
	JButton jb_file;// �ļ���ť
	JButton jb_history;// �����¼��ť
	JFileChooser jfc = new JFileChooser(".");

	public QqHistory(String ownerId,String friend) throws Exception {
		super("JFrame Testing");
		np = new newPanel();// ʵ�����ڲ���������
		np.setLayout(null);// ����np���Ĳ���
		FriendId = Integer.parseInt(friend);
		OwnerId = Integer.parseInt(ownerId);
		/* ��С����ǩ��ť */
		minIcon1 = new ImageIcon("./image/HomeScreen/min1.png");
		minIcon2 = new ImageIcon("./image/HomeScreen/.png");
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
		inq = new Inquiry();
		Owner = inq.InqUsers(Integer.parseInt(ownerId)).getName().trim();
		Friend = inq.InqUsers(Integer.parseInt(friend)).getName().trim();
		name = new JLabel("<HTML>" + "��" + Friend + "("+ friend +")" + "�������¼" + "</HTML>");
		name.setOpaque(false);
		name.setFont(new Font("����", Font.PLAIN, 20));
		name.setBounds(30, 30, 250, 50);
		np.add(name);
		// ������Ϣ��ʾ���������
		JPanel np1 = new JPanel();
		np1.setLayout(null);
		/* ������Ϣ�� */
		content = new JTextArea();
		content.setBorder(BorderFactory.createLineBorder(Color.gray, 0));
		jspxx = new JScrollPane(content);
		jspxx.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
		content.setEditable(false);
		content.setOpaque(false);
		content.setLineWrap(true);

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

		// �رհ�ť
		closeButton = new JButton("�ر�(C)");
		closeButton.setMnemonic('C');
		closeButton.setBounds(370,460,80,25);
		closeButton.setBorder(BorderFactory.createLineBorder(Color.cyan));
		closeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		np.add(closeButton);
		
		//��ȡ����ʾ�����¼
		showMessage();

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

		this.add(np);// ����������뵽������
		this.setUndecorated(true);// ���ô���Ϊδ�����ε�
		this.setSize(600, 500);
		this.setLocation(
				(Toolkit.getDefaultToolkit().getScreenSize().width - 600) / 2,
				(Toolkit.getDefaultToolkit().getScreenSize().height - 400) / 2);// ���ô������λ��
		this.setVisible(true);// �������
		this.setDefaultCloseOperation(3);// ���ô���رշ�ʽ

	}

	public void mouseEntered(MouseEvent e) {
	}

	public void mouseExited(MouseEvent e) {
	}

	public void mouseClicked(MouseEvent e) {
		if (e.getSource() == min) {
			this.setExtendedState(ICONIFIED);
		} else if (e.getSource() == close) {
			dispose();
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
	public void showMessage() throws Exception {
		//�����ݿ��ȡ�����¼
		LinkedHashMap<String,String> link1 = inq.InqFriendhistory(OwnerId, FriendId);
		LinkedHashMap<String,String> link2 = inq.InqFriendhistory(FriendId, OwnerId);
		//��Iterator�������¼
		ListIterator<Map.Entry<String,String>> iter1=new ArrayList<Map.Entry<String,String>>(link1.entrySet()).listIterator();
		ListIterator<Map.Entry<String,String>> iter2=new ArrayList<Map.Entry<String,String>>(link2.entrySet()).listIterator();
		
		String st1,st2;
		String output;
		
		Map.Entry<String, String> entry1=iter1.next();
		Map.Entry<String, String> entry2=iter2.next();
		st1 = entry1.getKey();
		st2 = entry2.getKey();
		while(st1!=st2 ) {
			if(Long.parseLong(st1)<Long.parseLong(st2)  ){//|| !iter2.hasNext()
				output =  Owner + " �� " + entry1.getValue() + "\n";
				this.content.append(output);
				if(iter1.hasNext()){
					entry1=iter1.next();
					st1 = entry1.getKey();
				}
				else
					st1 = "99999999999999";
			}else if(Long.parseLong(st1)>Long.parseLong(st2)){// || !iter1.hasNext()
				output =  Friend + " �� " + entry2.getValue() + "\n";
				this.content.append(output);
				if(iter2.hasNext()){
					entry2=iter2.next();
					st2 = entry2.getKey();
				}
				else
					st2 = "99999999999999";
			}
		}
//		Map.Entry<String, String> entry;
//		while(iter2.hasNext()) {
//			entry=iter2.next();
//			System.out.println(entry.getKey()+":"+entry.getValue());
//		}

	}
	
	
}
