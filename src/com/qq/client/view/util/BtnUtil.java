package com.qq.client.view.util;

import javax.swing.ImageIcon;
import javax.swing.JButton;

public class BtnUtil {
	
	 public static JButton getBtnClose() {
		 JButton btnClose = new JButton();
		 
		 btnClose.setIcon(new ImageIcon("./image/QqClientLogin/exit.png"));
//		 btnClose.setRolloverIcon(new ImageIcon());
//		 btnClose.setPressedIcon(new ImageIcon());
		 btnClose.setBorderPainted(false);
		 btnClose.setToolTipText("�ر�");

		return btnClose;
	}
	 
	 public static JButton getBtnMini() {
		 JButton btnMini = new JButton();
		 
		 btnMini.setIcon(new ImageIcon("./image/QqClientLogin/mini.png"));
//		 btnClose.setRolloverIcon(new ImageIcon());
//		 btnClose.setPressedIcon(new ImageIcon());
		 btnMini.setBorderPainted(false);
		 btnMini.setToolTipText("��С��");

		return btnMini;
	}
	 
}
