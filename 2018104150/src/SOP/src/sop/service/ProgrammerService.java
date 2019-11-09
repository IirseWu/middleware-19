package sop.service;

import java.util.ArrayList;

import sop.dao.domain.Programmer;
import sop.dao.domain.Team;

public interface ProgrammerService {
	//��½
	Programmer loginProgrammer(String pid,String password);
	//ע��
	void registerProgammer(Programmer programmer);
	//�޸ĸ�����Ϣ
	void modifyMyInfo(Programmer p);
	//�����Ŷ�
	void establishTeam(Team team);
	//��������Ŷ�
	void applyForTeam(String programmerId, String teamId);
	//����ҵ������Ŷ�
	ArrayList<Team> getAllMyTeams(String programmerId);
	//�������Ϊ�ӳ��������Ŷ�
	ArrayList<Team> getAllMyTeamsLeader(String programmerId);
}
