package sop.service;

import java.util.ArrayList;

import sop.dao.domain.Bid;
import sop.dao.domain.Company;
import sop.dao.domain.Project;

public interface CompanyService {
	//��˾�û���½
	Company loginCompany(String cid,String password);
	//ע�ṫ˾�û�
	void registerCompany(Company company);
	//������Ŀ
	void issueProject(Project project,String companyId);
	//ɾ����Ŀ
	void deleteProject(String proId);
	//ȷ���б�
	void affirmBid(String proId,String bidId);
	//�鿴��
	ArrayList<Bid> getAllMyBid(String proId);
	//�޸Ĺ�˾��Ϣ
	void modifyCompanyInfo(Company c);
	//�鿴��ʷ��Ŀ
	ArrayList<Project> getAllMyProject(String companyId);
}
