package sop.service;

import java.util.ArrayList;

import sop.dao.domain.Bid;
import sop.dao.domain.Programmer;

public interface TeamService {
	//�鿴���������б�
	ArrayList<Programmer> getTeamApplyInfo(String teamId);
	//�鿴�Ŷӳ�Ա�б�
	ArrayList<Programmer> getTeamMemberInfo(String teamId);
	//�鿴�б��б�
	ArrayList<Bid> getTeamBidInfo(String teamId);
	//�����б�
	void applyForBid(String proId, String teamId,Bid bid);
	//ɾ����Ա
	void deleteTeammate(String programmerId,String teamId);
	//ȷ������
	void acceptRequest(String programmerId,String teamId);
	//�ܾ�����
	void refuseRequest(String programmerId,String teamId);
	//��ɢ�Ŷ�
	void deleteTeam(String teamId);
}
