package com.qq.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class Delete {
	//删除好友
	public int DelFriend(int Userid,int FriendID) throws Exception {
		Connection conn = JdbcUtil.getConnection();
		String sql = "delete from friendlist where Userid = ? and FriendID = ?";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setInt(1, Userid);
		ps.setInt(2, FriendID);
		int change = ps.executeUpdate();
		conn.commit();
		ps.close();
		conn.close();
		return change;
	}
	
	//删除群成员
	public int DelGroupMenber(int GroupID,int Userid) throws Exception {
		Connection conn = JdbcUtil.getConnection();
		String sql = "delete from GroupList where GroupID = ? and Userid = ?";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setInt(1, GroupID);
		ps.setInt(2, Userid);
		int change = ps.executeUpdate();
		conn.commit();
		ps.close();
		conn.close();
		return change;
	}
	
	//删除离线消息 TODO 
	public int OffMsg(int ReceiveID,String SendTime) throws Exception {
		System.out.println(ReceiveID+","+SendTime);
		Connection conn = JdbcUtil.getConnection();
		String sql = "delete from offlinemsg where receiveid = ? and sendtime = ? ";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setInt(1, ReceiveID);
		ps.setString(2, SendTime);
		int change = ps.executeUpdate();
		conn.commit();
		ps.close();
		conn.close();
		return change;
	}
	
	
	public static void main(String[] args) throws Exception {
		Delete del = new Delete();
		Inquiry inq = new Inquiry();
		String st1 = inq.OffLineMsg(128001).getGetter();
		String st2 = inq.OffLineMsg(128001).getSendTime();
		System.out.println(del.OffMsg(Integer.parseInt(st1), st2));
		System.out.println(inq.OffLineMsg(128001).getCon());
		
		
	}

}
