package sop.service;

import java.util.ArrayList;

import sop.dao.domain.Bid;
import sop.dao.domain.Company;
import sop.dao.domain.Programmer;
import sop.dao.domain.Project;
import sop.dao.domain.Team;

public interface GeneralService {
	//չʾ������Ŀ
	ArrayList<Project> getAllProjects();
	//չʾ���нӰ����û�
	ArrayList<Company> getAllCompanys();
	//չʾ���нӰ����û�
	ArrayList<Programmer> getAllProgrammers();
	//չʾ�����Ŷ�
	ArrayList<Team> getAllTeams();
	//�鿴��Ŀ��Ϣ
	Project getProjectInfo(String projectId);
	//�鿴��˾��Ϣ
	Company getCompanyInfo(String companyId);
	//�鿴������Ϣ
	Programmer getProgrammerInfo(String programmerId);
	//�鿴�Ŷ���Ϣ
	Team getTeamInfo(String teamId);
	//�鿴����Ϣ
	Bid getBidInfo(String bidId);
}
