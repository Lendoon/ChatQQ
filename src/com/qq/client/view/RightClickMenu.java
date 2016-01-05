package com.qq.client.view;

import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.JTextArea;

public class RightClickMenu extends JTextArea  {
	private JPopupMenu pop = null; // 弹出菜单
	private JMenuItem copy = null, 
			paste = null,
			cut = null; // 三个功能菜单
	
	public void JTextAreaMenu() {
			JOptionPane.showMessageDialog(this, "nihao", "警告", JOptionPane.INFORMATION_MESSAGE);
			pop = new JPopupMenu();
			pop.add(copy = new JMenuItem("复制"));
			pop.add(paste = new JMenuItem("粘贴"));
			pop.add(cut = new JMenuItem("剪切"));
		this.add(pop);
		}
	
}
