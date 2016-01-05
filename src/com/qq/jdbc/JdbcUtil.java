package com.qq.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JdbcUtil {

	private static String username = "qq";
	private static String password = "123456";
	private static String url = "jdbc:oracle:thin:@192.168.8.22:1521:orcl";
	private static String driver = "oracle.jdbc.driver.OracleDriver";
	
	public static void freeAll(Connection conn,Statement st,ResultSet rs){
		try {
			if(conn != null){
				conn.close();
			}
			
			if(st != null){
				st.close();
			}
			
			if(rs != null){
				rs.close();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void freeAll(Connection conn,Statement st){
		freeAll(conn, st, null);
	}
	
	public static Connection getConnection(){
		
		Connection conn = null;
		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(url, username, password);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return conn;
	}
	
	public static void main(String[] args) {
		Connection conn = JdbcUtil.getConnection();
		System.out.println("数据库连接成功!");
	}
	
}
