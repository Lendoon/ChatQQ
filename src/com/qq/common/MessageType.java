package com.qq.common;

public interface MessageType {
	String message_succeed_fail = "1";// ������½�ɹ�
	String message_login_fail = "2";// ������½ʧ��
	String message_comm_mes = "3";// ��ͨ��Ϣ��
	String message_get_onLineFriend = "4";// Ҫ�����ߺ��ѵİ�
	String message_ret_onLineFriend = "5";// �������ߺ��ѵİ�
	String message_get_FIND = "6";
	String message_ret_FIND = "7";
	String message_group_chat = "8";//Ⱥ�ĵ���Ϣ
	String message_create_group="9";
	String message_online = "10"; //����
	String message_offline = "11" ; //����
	String message_updata_friendlist = "12";//ˢ�º����б�
	String message_updata_grouplist = "13";//ˢ��Ⱥ�б�
}
