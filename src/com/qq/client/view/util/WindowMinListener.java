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
	 private TrayIcon trayIcon = null; // ����ͼ��
	 private SystemTray tray = null; // ������ϵͳ���̵�ʵ��
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
	       
	        tray = SystemTray.getSystemTray(); // ��ñ�����ϵͳ���̵�ʵ��
	        ImageIcon icon = new ImageIcon("./image/systemtray/qq.gif"); // ��Ҫ��ʾ�������е�ͼ��
	        PopupMenu pop = new PopupMenu(); // ����һ���Ҽ�����ʽ�˵�
	        MenuItem show = new MenuItem("QQ");
	        MenuItem exit = new MenuItem("�˳�");
	        MenuItem author = new MenuItem("Author");
	        /**
	         * TrayIcon����������
	         * TrayIcon(Image image) �á�ͼ�ꡱ������
	         * TrayIcon(Image image, String tooltip) �á�ͼ�ꡱ�͡�ToolTip������
	         * TrayIcon(Image image, String tooltip, PopupMenu popup) �á�ͼ�ꡱ����ToolTip�����������˵���������һ������ͼ��
	         */
	        trayIcon = new TrayIcon(icon.getImage(), "qq����", pop);
	        // �������ť�󴰿ڱ��رգ�����ͼ�걻��ӵ�ϵͳ��������
	       
	                    try {
							tray.add(trayIcon);
						} catch (AWTException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						} // ������ͼ����ӵ�ϵͳ������ʵ����
	                //    frame.setVisible(false); // ʹ���ڲ�����
	              
	        /**
	         * ������������������������ͼ����˫��ʱ��Ĭ����ʾ����
	         */
	        trayIcon.addMouseListener(new MouseAdapter() {
	            public void mouseClicked(MouseEvent e) {
	                if(e.getClickCount()==2){ // ���˫��
	                    tray.remove(trayIcon); // ��ϵͳ������ʵ�����Ƴ�����ͼ��
	                  
	                    frame.setVisible(true); // ��ʾ����
	                }
	            }
	        });
	        show.addActionListener(new ActionListener() { // �������ʾ���ڡ��˵��󽫴�����ʾ����
	            public void actionPerformed(ActionEvent e) {
	                tray.remove(trayIcon); // ��ϵͳ������ʵ�����Ƴ�����ͼ��
	                frame.setVisible(true);
	            }
	        });
	        exit.addActionListener(new ActionListener() { // ������˳���ʾ���˵����˳�����
	            public void actionPerformed(ActionEvent e) {
	                System.exit(0); // �˳�����
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
	     * ��ʾ��Ϣ
	     */
	    private void showMessage(){
	        JOptionPane.showMessageDialog(this,
	                new JLabel("<html>���ߣ�qq��Ŀ��<br>"),
	                       "��Ч��", JOptionPane.INFORMATION_MESSAGE);
	    }
}
