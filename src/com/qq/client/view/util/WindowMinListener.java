package com.qq.client.view.util;

import java.awt.AWTException;
import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.awt.SystemTray;
import java.awt.TrayIcon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class WindowMinListener extends JFrame implements ActionListener{
	JFrame frame = null;
	 private JPanel pane = null;
	 private TrayIcon trayIcon = null; // 托盘图标
	 private SystemTray tray = null; // 本操作系统托盘的实例
	public WindowMinListener(JFrame frame) {
		super();
		this.frame = frame;
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		frame.setVisible(false);
		this.tray();
	     }
	 public  void   tray(){
	       
	        tray = SystemTray.getSystemTray(); // 获得本操作系统托盘的实例
	        ImageIcon icon = new ImageIcon("./image/systemtray/qq.gif"); // 将要显示到托盘中的图标
	        PopupMenu pop = new PopupMenu(); // 构造一个右键弹出式菜单
	        MenuItem show = new MenuItem("QQ");
	        MenuItem exit = new MenuItem("退出");
	        MenuItem author = new MenuItem("Author");
	        /**
	         * TrayIcon有三个构造
	         * TrayIcon(Image image) 用“图标”来构造
	         * TrayIcon(Image image, String tooltip) 用“图标”和“ToolTip”构造
	         * TrayIcon(Image image, String tooltip, PopupMenu popup) 用“图标”，“ToolTip”，“弹出菜单”来构造一个托盘图标
	         */
	        trayIcon = new TrayIcon(icon.getImage(), "qq托盘", pop);
	        // 点击本按钮后窗口被关闭，托盘图标被添加到系统的托盘中
	       
	                    try {
							tray.add(trayIcon);
						} catch (AWTException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						} // 将托盘图标添加到系统的托盘实例中
	                //    frame.setVisible(false); // 使窗口不可视
	              
	        /**
	         * 添加鼠标监听器，当鼠标在托盘图标上双击时，默认显示窗口
	         */
	        trayIcon.addMouseListener(new MouseAdapter() {
	            public void mouseClicked(MouseEvent e) {
	                if(e.getClickCount()==2){ // 鼠标双击
	                    tray.remove(trayIcon); // 从系统的托盘实例中移除托盘图标
	                  
	                    frame.setVisible(true); // 显示窗口
	                }
	            }
	        });
	        show.addActionListener(new ActionListener() { // 点击“显示窗口”菜单后将窗口显示出来
	            public void actionPerformed(ActionEvent e) {
	                tray.remove(trayIcon); // 从系统的托盘实例中移除托盘图标
	                frame.setVisible(true);
	            }
	        });
	        exit.addActionListener(new ActionListener() { // 点击“退出演示”菜单后退出程序
	            public void actionPerformed(ActionEvent e) {
	                System.exit(0); // 退出程序
	            }
	        });
	        author.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent e) {
	                showMessage();
	            }
	        });
	        pop.add(show);
	        pop.add(exit);
	        pop.add(author);
	        
	    }
	    /**
	     * 显示信息
	     */
	    private void showMessage(){
	        JOptionPane.showMessageDialog(this,
	                new JLabel("<html>作者：qq项目组<br>"),
	                       "刘效坤", JOptionPane.INFORMATION_MESSAGE);
	    }
}
