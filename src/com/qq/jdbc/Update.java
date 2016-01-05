package com.qq.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class Update {
	
	public boolean Users_password(int Userid, String password) throws Exception {

		Connection conn = JdbcUtil.getConnection();
//		conn.setAutoCommit(false);

		String sql = "update Users set Password =? where Userid = ?";

		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, password);
		ps.setInt(2, Userid);
		int change = ps.executeUpdate();
		boolean result = false;
		if(change==1)
			result = true;

		conn.commit();
		ps.close();
		conn.close();
		return result;
	}
	
	public boolean Users_Name(int Userid, String Name) throws Exception {

		Connection conn = JdbcUtil.getConnection();

		String sql = "update Users set Name =? where Userid = ?";

		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, Name);
		ps.setInt(2, Userid);
		int change = ps.executeUpdate();
		boolean result = false;
		if(change==1)
			result = true;

		conn.commit();
		ps.close();
		conn.close();
		return result;
	}
	
	public boolean Users_Age(int Userid, int age) throws Exception {

		Connection conn = JdbcUtil.getConnection();

		String sql = "update Users set Age =? where Userid = ?";

		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setInt(1, age);
		ps.setInt(2, Userid);
		int change = ps.executeUpdate();
		boolean result = false;
		if(change==1)
			result = true;
		conn.commit();
		ps.close();
		conn.close();
		return result;
	}
	
	public boolean Users_Telephonenum(int Userid, int Telephonenum) throws Exception {

		Connection conn = JdbcUtil.getConnection();

		String sql = "update Users set Telephonenum =? where Userid = ?";

		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setInt(1, Telephonenum);
		ps.setInt(2, Userid);
		
		int change = ps.executeUpdate();
		boolean result = false;
		if(change==1)
			result = true;
		
		conn.commit();
		ps.close();
		conn.close();
		return result;
	}
	
	public boolean Users_Email(int Userid, String Email) throws Exception {

		Connection conn = JdbcUtil.getConnection();

		String sql = "update Users set Email =? where Userid = ?";

		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, Email);
		ps.setInt(2, Userid);
		
		int change = ps.executeUpdate();
		boolean result = false;
		if(change==1)
			result = true;
		
		conn.commit();
		ps.close();
		conn.close();
		return result;
	}
	
	public boolean Users_Birthday(int Userid, int Birthday) throws Exception {

		Connection conn = JdbcUtil.getConnection();

		String sql = "update Users set Birthday =? where Userid = ?";

		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setInt(1, Birthday);
		ps.setInt(2, Userid);
		
		int change = ps.executeUpdate();
		boolean result = false;
		if(change==1)
			result = true;
		
		conn.commit();
		ps.close();
		conn.close();
		return result;
	}
	
	public boolean Users_Gender(int Userid, int Gender) throws Exception {

		Connection conn = JdbcUtil.getConnection();

		String sql = "update Users set Gender =? where Userid = ?";

		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setInt(1, Gender);
		ps.setInt(2, Userid);
		
		int change = ps.executeUpdate();
		boolean result = false;
		if(change==1)
			result = true;
		
		conn.commit();
		ps.close();
		conn.close();
		return result;
	}
	
	public boolean Users_Isonline(int Userid, int Isonline) throws Exception {

		Connection conn = JdbcUtil.getConnection();

		String sql = "update Users set Isonline =? where Userid = ?";

		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setInt(1, Isonline);
		ps.setInt(2, Userid);
		
		int change = ps.executeUpdate();
		boolean result = false;
		if(change==1)
			result = true;
		
		conn.commit();
		ps.close();
		conn.close();
		return result;
	}
	
	public boolean Groups_Adminid(int GroupID, int Adminid) throws Exception {

		Connection conn = JdbcUtil.getConnection();

		String sql = "update Groups set Adminid =? where GroupID = ?";

		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setInt(1, Adminid);
		ps.setInt(2, GroupID);
		
		int change = ps.executeUpdate();
		boolean result = false;
		if(change==1)
			result = true;
		
		conn.commit();
		ps.close();
		conn.close();
		return result;
	}
	
	public boolean Groups_Name(int GroupID, String Name) throws Exception {

		Connection conn = JdbcUtil.getConnection();

		String sql = "update Groups set Name =? where GroupID = ?";

		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, Name);
		ps.setInt(2, GroupID);
		
		int change = ps.executeUpdate();
		boolean result = false;
		if(change==1)
			result = true;
		
		conn.commit();
		ps.close();
		conn.close();
		return result;
	}
	
	public boolean Friendlist_Name(int UserID, int FriendID) throws Exception {

		Connection conn = JdbcUtil.getConnection();

		String sql = "update Friendlist set FriendID =? where UserID = ?";

		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setInt(1, FriendID);
		ps.setInt(2, UserID);
		
		int change = ps.executeUpdate();
		boolean result = false;
		if(change==1)
			result = true;
		
		conn.commit();
		ps.close();
		conn.close();
		return result;
	}
	
	public boolean Friendlist_FHistoryID(int UserID, int FHistoryID) throws Exception {

		Connection conn = JdbcUtil.getConnection();

		String sql = "update Friendlist set FHistoryID =? where UserID = ?";

		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setInt(1, FHistoryID);
		ps.setInt(2, UserID);
		
		int change = ps.executeUpdate();
		boolean result = false;
		if(change==1)
			result = true;
		
		conn.commit();
		ps.close();
		conn.close();
		return result;
	}
	
	public boolean Friendlist_DepartmentID(int UserID, int DepartmentID) throws Exception {

		Connection conn = JdbcUtil.getConnection();

		String sql = "update Friendlist set DepartmentID =? where UserID = ?";

		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setInt(1, DepartmentID);
		ps.setInt(2, UserID);
		
		int change = ps.executeUpdate();
		boolean result = false;
		if(change==1)
			result = true;
		
		conn.commit();
		ps.close();
		conn.close();
		return result;
	}
	
	public boolean Department_DepartmentName(int DepartmentID, String DepartmentName) throws Exception {

		Connection conn = JdbcUtil.getConnection();

		String sql = "update Department set DepartmentName =? where DepartmentID = ?";

		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, DepartmentName);
		ps.setInt(2, DepartmentID);
		
		int change = ps.executeUpdate();
		boolean result = false;
		if(change==1)
			result = true;
		
		conn.commit();
		ps.close();
		conn.close();
		return result;
	}
	
	public boolean FriendHistory_ChatDate(int FHistoryID, int ChatDate) throws Exception {

		Connection conn = JdbcUtil.getConnection();

		String sql = "update FriendHistory set ChatDate =? where FHistoryID = ?";

		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setInt(1, ChatDate);
		ps.setInt(2, FHistoryID);
		
		int change = ps.executeUpdate();
		boolean result = false;
		if(change==1)
			result = true;
		
		conn.commit();
		ps.close();
		conn.close();
		return result;
	}
	
	public boolean FriendHistory_DepartmentName(int FHistoryID, String Record) throws Exception {

		Connection conn = JdbcUtil.getConnection();

		String sql = "update FriendHistory set Record =? where FHistoryID = ?";

		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, Record);
		ps.setInt(2, FHistoryID);
		
		int change = ps.executeUpdate();
		boolean result = false;
		if(change==1)
			result = true;
		
		conn.commit();
		ps.close();
		conn.close();
		return result;
	}
	
	
	public boolean GroupHistory_ChatDate(int GroupID, int ChatDate) throws Exception {

		Connection conn = JdbcUtil.getConnection();

		String sql = "update GroupHistory set ChatDate =? where GroupID = ?";

		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setInt(1, ChatDate);
		ps.setInt(2, GroupID);
		
		int change = ps.executeUpdate();
		boolean result = false;
		if(change==1)
			result = true;
		
		conn.commit();
		ps.close();
		conn.close();
		return result;
	}
	
	public boolean GroupHistory_DepartmentName(int GroupID, String Record) throws Exception {

		Connection conn = JdbcUtil.getConnection();

		String sql = "update GroupHistory set Record =? where GroupID = ?";

		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, Record);
		ps.setInt(2, GroupID);
		
		int change = ps.executeUpdate();
		boolean result = false;
		if(change==1)
			result = true;
		
		conn.commit();
		ps.close();
		conn.close();
		return result;
	}
	
	public boolean GroupHistory_TalkerID(int GroupID, int TalkerID) throws Exception {

		Connection conn = JdbcUtil.getConnection();

		String sql = "update GroupHistory set TalkerID =? where GroupID = ?";

		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setInt(1, TalkerID);
		ps.setInt(2, GroupID);
		
		int change = ps.executeUpdate();
		boolean result = false;
		if(change==1)
			result = true;
		
		conn.commit();
		ps.close();
		conn.close();
		return result;
	}

//	public static void main(String[] args) throws Exception {
//		Update u = new Update();
//		u.Department_DepartmentName(1, "ÎÒµÄºÃÓÑ");
//		System.out.println(u.Groups_Adminid(100011, 128001));
//	}
	
}
