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
import java.util.Calendar;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.qq.client.tools.ManageClientConServerThread;
import com.qq.client.tools.QqFriendListManage;
import com.qq.common.Message;
import com.qq.common.MessageType;
import com.qq.info.Groups;
import com.qq.info.Users;
import com.qq.jdbc.Inquiry;
import com.qq.jdbc.Insert;

public class FindFrame extends JFrame {
	private JLabel findHead;
	private BufferedImage bgImg;
	private Image bgSrc;
	private MyPanel panel;
	private JTextField textField;
	private JButton findFriend;
	private JButton findGroup;
	private String owner;
	private String[] finder;
	private boolean answer;
	private JButton addBtn;
	private JButton cancleBtn;

	public FindFrame(String owner) {
		this.owner = owner;
		this.answer = false;
		try {
			FindFrameInit(answer);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public FindFrame(String owner, String[] finder) {
		this.finder = finder;
		this.owner = owner;
		this.answer = true;
		try {
			FindFrameInit(answer);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// 初始化搜索好友窗体
	private void FindFrameInit(boolean answer) throws IOException {

		findFriend = new JButton(new ImageIcon(
				"./image/FindFrame/findfriend_hover.png"));
		findGroup = new JButton(new ImageIcon(
				"./image/FindFrame/findgroup_hover.png"));

		this.setLayout(null);

		bgImg = new BufferedImage(640, 335, BufferedImage.TYPE_INT_RGB);
		bgSrc = ImageIO.read(new File("./image/FindFrame/answer.jpg"));
		Graphics g = bgImg.getGraphics();
		g.drawImage(bgSrc, -100, 0, null);
		panel = new MyPanel(bgImg);
		panel.setPreferredSize(new Dimension(640, 335));

		textField = new JTextField(10);
		textField.setFont(new Font("楷体", HEIGHT, 22));

		findFriend = new JButton("findFriend", new ImageIcon(
				"./image/FindFrame/findfriend_hover.png"));
		findFriend.addActionListener(listener);
		findGroup = new JButton("findGroup", new ImageIcon(
				"./image/FindFrame/findgroup_hover.png"));
		findGroup.addActionListener(listener);

		findHead = new JLabel("查找");
		findHead.setFont(new Font("宋体", HEIGHT, 28));

		panel.setLayout(null);
		if (!answer) {
			panel.add(findHead);
			findHead.setBounds(45, 40, 70, 25);
			panel.add(textField);
			textField.setBounds(50, 90, 251, 50);
			panel.add(findFriend);
			findFriend.setBounds(50, 150, 250, 51);
			panel.add(findGroup);
			findGroup.setBounds(50, 210, 250, 51);
		} else {
			JLabel finderID;
			JLabel finderName;
			JLabel friendGen;
			JLabel groupAdmin;
			JLabel friendAge;

			if (finder[1].equals("null")) {
				JOptionPane.showMessageDialog(null, "找不到对象", "查找结果",
						JOptionPane.INFORMATION_MESSAGE);
				return;
			}
			if (finder.length == 4) {
				finderID = new JLabel("账号: " + finder[0]);
				finderName = new JLabel("昵称：" + finder[1]);

				if (finder[2] == "1") {
					friendGen = new JLabel("性别：男");
				} else {
					friendGen = new JLabel("性别：女");
				}
				int age = 0;
				if (!finder[1].equals("null")) {
					age = Calendar.getInstance().get(Calendar.YEAR)
							- Integer.valueOf(finder[3].substring(0, 4));
				}
				friendAge = new JLabel("年龄：" + age);
				addBtn = new JButton("添加好友");
				addBtn.addActionListener(addListener);

				panel.add(friendGen);
				friendGen.setFont(new Font("楷体", HEIGHT, 22));
				friendGen.setBounds(50, 155, 250, 40);
				panel.add(friendAge);
				friendAge.setFont(new Font("楷体", HEIGHT, 22));
				friendAge.setBounds(50, 210, 250, 40);
			} else {
				finderID = new JLabel("群号: " + finder[0]);
				finderName = new JLabel("群名：" + finder[1]);
				groupAdmin = new JLabel("群主：" + finder[2]);
				panel.add(groupAdmin);
				groupAdmin.setFont(new Font("楷体", HEIGHT, 22));
				groupAdmin.setBounds(50, 160, 250, 40);

				addBtn = new JButton("加入群组");
				addBtn.addActionListener(addListener);
			}

			panel.add(finderID);
			finderID.setFont(new Font("楷体", HEIGHT, 22));
			finderID.setBounds(50, 100, 250, 40);
			panel.add(finderName);
			finderName.setFont(new Font("楷体", HEIGHT, 22));
			finderName.setBounds(50, 45, 250, 40);
			panel.add(addBtn);
			addBtn.setBounds(66, 250, 110, 50);
			cancleBtn = new JButton("取消");
			cancleBtn.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
				}
			});
			panel.add(cancleBtn);
			cancleBtn.setBounds(190, 250, 110, 50);
		}
		this.add(panel);
		panel.setBounds(0, 0, 740, 335);
		this.setBounds(500, 200, 640, 335);
		this.setVisible(true);
	}

	ActionListener addListener = new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			int AddType;
			if (e.getActionCommand().equals("添加好友")) {
				AddType = 1;
			} else {
				AddType = 0;
			}
			insertData(AddType);
		}

	};

	private void insertData(int addType) {
		Inquiry inquiry = new Inquiry();
		Insert insert = new Insert();
		if (addType == 1) {
			ArrayList<Integer> list = inquiry.InqFriendlist(Integer
					.valueOf(owner));
			if (list.contains(Integer.valueOf(finder[0]))
					|| Integer.valueOf(finder[0]) == Integer.valueOf(owner)) {
				JOptionPane.showMessageDialog(null, "好友已存在，请不要重复添加！");
				return;
			}
			insert.save(Integer.valueOf(this.owner), Integer.valueOf(finder[0]));
			insert.save(Integer.valueOf(finder[0]), Integer.valueOf(this.owner));
		} else {
			ArrayList<Integer> list = inquiry.InqGrouplist(Integer
					.valueOf(finder[0]));
			if (list.contains(Integer.valueOf(finder[0]))
					|| Integer.valueOf(finder[0]) == Integer.valueOf(owner)) {
				JOptionPane.showMessageDialog(null, "群已存在，请不要重复添加！");
				return;
			}
			insert.saveGroupList(Integer.valueOf(finder[0]),
					Integer.valueOf(this.owner));
		}
		JOptionPane.showMessageDialog(null, "添加完成", "操作",
				JOptionPane.INFORMATION_MESSAGE);
		// 刷新本地好友列表
		QqFriendListManage.getQqFriendList(owner).updata(addType, finder[0]);
		// 通知对方刷新好友列表
		Message m = new Message();
		m.setSender(owner);
		m.setGetter(finder[0]);
		m.setCon(String.valueOf(addType));
		m.setMesType(MessageType.message_updata_friendlist);
		try {
			ObjectOutputStream oos = new ObjectOutputStream(
					ManageClientConServerThread.getClientConserverThread(owner).getS().getOutputStream());
			oos.writeObject(m);
		} catch (Exception e2) {
			e2.printStackTrace();
		}
	}

	ActionListener listener = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {

			String findID = textField.getText();
			Inquiry inquiry = new Inquiry();

			String info;
			if (e.getActionCommand().equals("findFriend")) {
				Users finder = inquiry.InqUsers(Integer.valueOf(findID));
				info = finder.getUserID() + "/" + finder.getName() + "/"
						+ finder.getGender() + "/" + finder.getBirthday();
			} else {
				Groups finder = inquiry.InqGroups(Integer.valueOf(findID));
				info = finder.getGroupID() + "/" + finder.getName() + "/"
						+ finder.getAdminid();
			}
			find(info);
		}

	};

	private void find(String info) {
		String[] finderMsg = info.split("/");
		new FindFrame(this.owner, finderMsg);
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
}
