package com.qq.client.view;

import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.JTextArea;

public class RightClickMenu extends JTextArea  {
	private JPopupMenu pop = null; // �����˵�
	private JMenuItem copy = null, 
			paste = null,
			cut = null; // �������ܲ˵�
	
	public void JTextAreaMenu() {
			JOptionPane.showMessageDialog(this, "nihao", "����", JOptionPane.INFORMATION_MESSAGE);
			pop = new JPopupMenu();
			pop.add(copy = new JMenuItem("����"));
			pop.add(paste = new JMenuItem("ճ��"));
			pop.add(cut = new JMenuItem("����"));
		this.add(pop);
		}
	
}
