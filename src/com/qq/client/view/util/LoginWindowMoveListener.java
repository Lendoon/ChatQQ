package com.qq.client.view.util;

import java.awt.Frame;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import com.qq.client.view.QqClientLogin;


public class LoginWindowMoveListener extends MouseAdapter {

	Point mouseInitPoint = null;
	Point mouseMovePoint = null;
	Point frameInitPoint = null;

	Frame frame = null;

	public LoginWindowMoveListener(QqClientLogin login) {
		super();
		this.frame = login;
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
