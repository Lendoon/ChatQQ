package com.qq.client.view.util;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
public class WindowCloseListener implements ActionListener{
	JFrame frame = null;
	public WindowCloseListener(JFrame frame) {
		super();
		this.frame = frame;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
			System.exit(0);

	}
}
