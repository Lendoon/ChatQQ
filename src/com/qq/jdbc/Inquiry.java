package com.qq.jdbc;
//
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;

import com.qq.common.Message;
import com.qq.info.Department;
import com.qq.info.Groups;
import com.qq.info.Users;

public class Inquiry {
	//查询用户信息
	public Users InqUsers(int userid) {
		Connection conn = JdbcUtil.getConnection();
		String sql = "select * from Users where Userid = " +userid;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Users user = new Users();
		try {
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()){
				user.setUserID(rs.getInt(1));
				user.setName(rs.getString(2));
				user.setPassword(rs.getString(3));
				user.setGender(rs.getInt(4));
				user.setTelephonenum(rs.getDouble(5));
				user.setBirthday(rs.getInt(6));
				user.setSign(rs.getString(7));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			JdbcUtil.freeAll(conn, ps);
		}
		return user;
	}
	
	//查询群信息
	public Groups InqGroups(int groupid) {
		Connection conn = JdbcUtil.getConnection();
		String sql = "select * from Groups where Groupid = " +groupid;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Groups groups = new Groups();
		try {
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()){
				groups.setGroupID(rs.getInt(1));
				groups.setAdminid(rs.getInt(2));
				groups.setName(rs.getString(3));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			JdbcUtil.freeAll(conn, ps);
		}
		return groups;
	}
	
	//查询好友列表
	public ArrayList<Integer> InqFriendlist(int Userid) {
		Connection conn = JdbcUtil.getConnection();
		String sql = "select * from Friendlist where Userid = " +Userid;
		PreparedStatement ps = null;
		ResultSet rs = null;
		ArrayList<Integer> list = new ArrayList<Integer>();
		try {
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()){
				list.add(rs.getInt(2));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			JdbcUtil.freeAll(conn, ps);
		}
		return list;
	}
	
	//查询所加的群
	public ArrayList<Integer> InqGrouplist(int GroupID) {
		Connection conn = JdbcUtil.getConnection();
		String sql = "select * from GroupList where masterID = " +GroupID;
		PreparedStatement ps = null;
		ResultSet rs = null;
		ArrayList<Integer> list = new ArrayList<Integer>();
		try {
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()){
				list.add(rs.getInt(1));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			JdbcUtil.freeAll(conn, ps);
		}
		return list;
	}
	
	//查询分组信息
	public Department InqDepartment(int DepartmentID) {
		Connection conn = JdbcUtil.getConnection();
		String sql = "select * from Department where DepartmentID = " +DepartmentID;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Department department = new Department();
		try {
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()){
				department.setDepartmentID(rs.getInt(1));
				department.setDepartmentName(rs.getString(2));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			JdbcUtil.freeAll(conn, ps);
		}
		return department;
	}
	
	//查询单聊聊天记录
	public LinkedHashMap<String,String> InqFriendhistory(int SenderID,int ReceiveID) throws Exception {
		Connection conn = JdbcUtil.getConnection();
		String sql = "select * from FriendHistory where SenderID = ? and ReceiveID = ? order by chatdate";
		PreparedStatement ps = conn.prepareStatement(sql);
		ResultSet rs = null;
		LinkedHashMap<String,String> friendHistory = new LinkedHashMap<String, String>();
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, SenderID);
			ps.setInt(2, ReceiveID);
			rs = ps.executeQuery();
			while(rs.next()){
				friendHistory.put(rs.getString(4), rs.getString(5));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			JdbcUtil.freeAll(conn, ps);
		}
		return friendHistory;
	}
	
	//查询聊天记录当前最大ID，用于写入时
	public int FriendHistoryMaxID() throws Exception {
		Connection conn = JdbcUtil.getConnection();
		String sql = "select * from FriendHistory order by fhistoryid";
		PreparedStatement ps = conn.prepareStatement(sql);
		ResultSet rs = null;
		int MaxID = 0;
		try {
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()){
				MaxID = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			JdbcUtil.freeAll(conn, ps);
		}
		return MaxID;
	}
	
	//查询当前用户ID最大值，用于注册时
	public int UserMaxID() throws Exception {
		Connection conn = JdbcUtil.getConnection();
		String sql = "select * from users order by userid";
		PreparedStatement ps = conn.prepareStatement(sql);
		ResultSet rs = null;
		int MaxID = 0;
		try {
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()){
				MaxID = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			JdbcUtil.freeAll(conn, ps);
		}
		return MaxID;
	}
	
	//查询群ID最大值，用于申请时
	public int GroupMaxID() throws Exception {
		Connection conn = JdbcUtil.getConnection();
		String sql = "select * from groups order by groupid";
		PreparedStatement ps = conn.prepareStatement(sql);
		ResultSet rs = null;
		int MaxID = 0;
		try {
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()){
				MaxID = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			JdbcUtil.freeAll(conn, ps);
		}
		return MaxID;
	}
	
	//用于查询群聊天记录
	public LinkedHashMap<Long,String> InqGroupHistory(int GroupID) {
		Connection conn = JdbcUtil.getConnection();
		String sql = "select * from GroupHistory where GroupID = " +GroupID;
		PreparedStatement ps = null;
		ResultSet rs = null;
		LinkedHashMap<Long,String> groupHistory = new LinkedHashMap<Long, String>();
		try {
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()){
				//Map存储（聊天时间+发言者），聊天内容
				groupHistory.put(rs.getLong(2)+rs.getLong(4), rs.getString(3));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			JdbcUtil.freeAll(conn, ps);
		}
		return groupHistory;
	}
	
	//用于查询群成员列表
	public ArrayList<Integer> InqGroupMemberlist(int GroupID) {
		Connection conn = JdbcUtil.getConnection();
		String sql = "select * from GroupList where Groupid = " +GroupID;
		PreparedStatement ps = null;
		ResultSet rs = null;
		ArrayList<Integer> list = new ArrayList<Integer>();
		try {
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()){
				list.add(rs.getInt(2));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			JdbcUtil.freeAll(conn, ps);
		}
		return list;
	}
	
	//用于查询未读离线消息
	public Message OffLineMsg(int ReceiveID) {
		Connection conn = JdbcUtil.getConnection();
		String sql = "select * from OffLineMsg where ReceiveID = ? order by SendTime DESC";
		PreparedStatement ps = null;
		ResultSet rs = null;
		Message m = new Message();
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, ReceiveID);
			rs = ps.executeQuery();
			while(rs.next()){
				m.setGetter(rs.getString(1));
				m.setSender(rs.getString(2));
				m.setSendTime(rs.getString(3));
				m.setCon(rs.getString(4));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			JdbcUtil.freeAll(conn, ps);
		}
		return m;
	}
	
	//测试
	public static void main(String[] args) throws Exception {
		Inquiry inq = new Inquiry();
		System.out.println(inq.OffLineMsg(128001).getCon());
	
	}
}
