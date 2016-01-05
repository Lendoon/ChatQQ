package com.qq.common;

public interface MessageType {
	String message_succeed_fail = "1";// 表明登陆成功
	String message_login_fail = "2";// 表明登陆失败
	String message_comm_mes = "3";// 普通信息包
	String message_get_onLineFriend = "4";// 要求在线好友的包
	String message_ret_onLineFriend = "5";// 返回在线好友的包
	String message_get_FIND = "6";
	String message_ret_FIND = "7";
	String message_group_chat = "8";//群聊的消息
	String message_create_group="9";
	String message_online = "10"; //上线
	String message_offline = "11" ; //下线
	String message_updata_friendlist = "12";//刷新好友列表
	String message_updata_grouplist = "13";//刷新群列表
}
