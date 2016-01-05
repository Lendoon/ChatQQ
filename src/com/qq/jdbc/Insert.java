package com.qq.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.qq.common.Message;
import com.qq.info.Department;
import com.qq.info.FriendHistory;
import com.qq.info.GroupHistory;
import com.qq.info.Groups;
import com.qq.info.Users;

public class Insert {
	//保存用户信息
	public int save(Users users) {
		Connection conn = JdbcUtil.getConnection();
		String sql = "insert into users values(?,?,?,?,?,?,?)";
		PreparedStatement ps = null;
		int result = 0;
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, users.getUserID());
			ps.setString(2, users.getName());
			ps.setString(3, users.getPassword());
			ps.setInt(4, users.getGender());
			ps.setDouble(5,users.getTelephonenum());
			ps.setInt(6, users.getBirthday());
			ps.setString(7, users.getSign());
			
			result = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			JdbcUtil.freeAll(conn, ps);
		}
		return result;
	}
	
	//保存群组信息
	public int save(Groups groups) {
		Connection conn = JdbcUtil.getConnection();
		String sql = "insert into Groups values(?,?,?)";
		PreparedStatement ps = null;
		int result = 0;
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, groups.getGroupID());
			ps.setInt(2,groups.getAdminid());
			ps.setString(3, groups.getName());
			
			result = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			JdbcUtil.freeAll(conn, ps);
		}
		return result;
	}
	
	//保存分组信息
	public int save(Department department) {
		Connection conn = JdbcUtil.getConnection();
		String sql = "insert into Department values(?,?)";
		PreparedStatement ps = null;
		int result = 0;
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, department.getDepartmentID());
			ps.setString(2, department.getDepartmentName());
			
			result = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			JdbcUtil.freeAll(conn, ps);
		}
		return result;
	}
	
	//保存好友列表
	public int save(int user1,int user2) {
		Connection conn = JdbcUtil.getConnection();
		String sql = "insert into Friendlist values(?,?,?)";
		PreparedStatement ps = null;
		int result = 0;
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, user1);
			ps.setInt(2, user2);
			ps.setInt(3, 0);
			
			result = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			JdbcUtil.freeAll(conn, ps);
		}
		return result;
	}
	
	//保存群成员列表
	public int saveGroupList(int GroupID,int UserID) {
		Connection conn = JdbcUtil.getConnection();
		String sql = "insert into GroupList values(?,?)";
		PreparedStatement ps = null;
		int result = 0;
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, GroupID);
			ps.setInt(2, UserID);
			
			result = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			JdbcUtil.freeAll(conn, ps);
		}
		return result;
	}
	
	//保存单聊聊天记录
	public int save(FriendHistory fh) {
		Connection conn = JdbcUtil.getConnection();
		String sql = "insert into FriendHistory values(?,?,?,?,?)";
		PreparedStatement ps = null;
		int result = 0;
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, fh.getFHistoryID());
			ps.setInt(2, fh.getSenderID());
			ps.setInt(3, fh.getReceiveID());
			ps.setLong(4, fh.getChatDate());
			ps.setString(5, fh.getRecord());
			
			result = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			JdbcUtil.freeAll(conn, ps);
		}
		return result;
	}
	
	//保存群聊天记录
	public int save(GroupHistory gh) {
		Connection conn = JdbcUtil.getConnection();
		String sql = "insert into GroupHistory values(?,?,?,?)";
		PreparedStatement ps = null;
		int result = 0;
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, gh.getGroupID());
			ps.setLong(2, gh.getChatDate());
			ps.setString(3, gh.getRecord());
			ps.setInt(4, gh.getTalkerID());
			
			result = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			JdbcUtil.freeAll(conn, ps);
		}
		return result;
	}
	
	//保存离线消息
	public int save(Message m) {
		Connection conn = JdbcUtil.getConnection();
		String sql = "insert into OffLineMsg values(?,?,?,?)";
		PreparedStatement ps = null;
		int result = 0;
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, Integer.parseInt(m.getGetter()));
			ps.setInt(2, Integer.parseInt(m.getSender()));
			ps.setString(3, m.getSendTime());
			ps.setString(4, m.getCon());
			
			result = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			JdbcUtil.freeAll(conn, ps);
		}
		return result;
	}
	
//	public static void main(String[] args) {
//		Insert ins = new Insert();
//		FriendHistory f = new FriendHistory();
//		f.setChatDate(20141209);
//		f.setFHistoryID(12220);
//		f.setRecord("你好，收到的到吗");
//		ins.save(f);
//		GroupHistory g = new GroupHistory();
//		g.setChatDate(20141208);
//		g.setGroupID(11);
//		g.setRecord("大家好，我是管理员");
//		g.setTalkerID(128001);
//		ins.save(g);
//	}

}
