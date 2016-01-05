package com.qq.client.view;

import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;


public class WindowMoveListener extends MouseAdapter {

	Point mouseInitPoint = null;
	Point mouseMovePoint = null;
	Point frameInitPoint = null;

	QqFriendList frame = null;

	public WindowMoveListener(QqFriendList qqFriendList) {
		super();
		this.frame = qqFriendList;
	}

	@Override
	public void mousePressed(MouseEvent e) {
		mouseInitPoint = e.getPoint();
		frameInitPoint = frame.getLocation();
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		mouseMovePoint = e.getPoint();
		int x = (int) (mouseMovePoint.getX() - mouseInitPoint.getX());
		int y = (int) (mouseMovePoint.getY() - mouseInitPoint.getY());
		frame.setLocation((int) (frameInitPoint.getX() + x),
				(int) frameInitPoint.getY() + y);
		frameInitPoint.x = (int) (frameInitPoint.getX() + x);
		frameInitPoint.y = (int) (frameInitPoint.getY() + y);
	}

}
