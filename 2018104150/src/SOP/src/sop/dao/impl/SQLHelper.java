package sop.dao.impl;

import java.io.FileInputStream;
import java.sql.*;
import java.util.Properties;

public class SQLHelper {
	//������������
	private static Connection ct=null;
	private static PreparedStatement ps=null;
	private static ResultSet rs=null;
	private static CallableStatement cs=null;
	
	//�������ݿ���û��������룬url������
	//˵������ʵ�ʿ����У�������������Щ����д��һ���ⲿ�ļ���
	//����������ʱ�����Ƕ�����Щ������Ϣ��java.util.Properites
	private static String username;
	private static String password;
	private static String driver;
	private static String url;

	//ʹ�þ�̬���������(����ֻ��Ҫ����һ��)
	static{
//ʹ��Properties�࣬����ȡ�����ļ�
		Properties pp=new Properties();
		FileInputStream fis=null;
		try {
			fis=new FileInputStream("D:/workspaceForMars/SOP/src/dbinfo.properties");
			//��pp��dbinfo.properties�ļ���������
			pp.load(fis);
			//��ȡdbinfo.properties�ļ�����Ϣ
			setUsername((String) pp.getProperty("username"));
			setPassword((String) pp.getProperty("password"));
			driver=(String) pp.getProperty("driver");
			setUrl((String) pp.getProperty("url"));
			
			//�������
			Class.forName(driver);
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			try {
				if(fis!=null){
					fis.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			fis=null;
		}
	}

	public static Connection getCt() {
		return ct;
	}

	public static void setCt(Connection ct) {
		SQLHelper.ct = ct;
	}

	public static PreparedStatement getPs() {
		return ps;
	}

	public static void setPs(PreparedStatement ps) {
		SQLHelper.ps = ps;
	}

	public static ResultSet getRs() {
		return rs;
	}

	public static void setRs(ResultSet rs) {
		SQLHelper.rs = rs;
	}

	public static String getUsername() {
		return username;
	}

	public static void setUsername(String username) {
		SQLHelper.username = username;
	}

	public static String getPassword() {
		return password;
	}

	public static void setPassword(String password) {
		SQLHelper.password = password;
	}

	public static String getUrl() {
		return url;
	}

	public static void setUrl(String url) {
		SQLHelper.url = url;
	}
	
	
	//���ô洢���̵ķ���
	public static void executeProcedure(String sql,String[] parameters){
		try {
			ct=DriverManager.getConnection(url,username,password);
			cs=ct.prepareCall(sql);
			if(parameters!=null){
				for(int i=0;i<parameters.length;i++){
					cs.setString(i+1, parameters[i]);
					System.out.println(parameters[i]);
				}
			}
			//ִ��
			cs.execute();
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e.getMessage());
		}
	}

	//����չʾȫ���Ĵ洢���̵ķ���
	public static void executeListAllProcedure(String sql,String id){
		try {
			ct=DriverManager.getConnection(url,username,password);
			cs=ct.prepareCall(sql);
			cs.setString(1, id);
			//���ڶ���?ע��
			cs.registerOutParameter(2, oracle.jdbc.OracleTypes.CURSOR);
			//ִ��
			cs.execute();
			rs=(ResultSet)cs.getObject(2);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e.getMessage());
		}
	}
	
	//����չʾȫ���Ĵ洢���̵ķ���
	public static void executeListAllProcedure2(String sql){
		try {
			ct=DriverManager.getConnection(url,username,password);
			cs=ct.prepareCall(sql);
			cs.registerOutParameter(1, oracle.jdbc.OracleTypes.CURSOR);
			//ִ��
			cs.execute();
			rs=(ResultSet)cs.getObject(1);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e.getMessage());
		}
	}
	
		//��ɲ�ѯ����
		public static ResultSet executeQuery(String sql,String[] parameters){
			try {
				//����ʵ��������Ƕ�sql���?��ֵ
				//�õ�����
				ct=DriverManager.getConnection(url,username,password);
				//����ps���󣬵õ�sql������
				ps=ct.prepareStatement(sql);
				//���parameters��Ϊnull���Ÿ�ֵ
				if(parameters!=null){
					for(int i=0;i<parameters.length;i++){
						ps.setString(i+1, parameters[i]);
					}
				}
				rs=ps.executeQuery();
			} catch (SQLException e) {
				e.printStackTrace();
				//�׳������쳣
				throw new RuntimeException(e.getMessage());
			} 
			return rs;
		}

		//ͳһ��curd����
		public static void executeUpdate(String sql,String[] parameters){
			try {
				ct=DriverManager.getConnection(url,username,password);
				ps=ct.prepareStatement(sql);
				if(parameters!=null){
					for(int i=0;i<parameters.length;i++){
						ps.setString(i+1, parameters[i]);
					}
				}
				//ִ��
				ps.executeUpdate();

			} catch (Exception e) {
				e.printStackTrace();
				throw new RuntimeException(e.getMessage());
			}
		}
		

	
	//�ѹر���Դд�ɺ���
		public static void close(){
			//�ر���Դ
			if(rs!=null){
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
				rs=null;
			}
			if(ps!=null){
				try {
					ps.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
				ps=null;
			}
			if(ct!=null){
				try {
					ct.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
				ct=null;
			}
			if(cs!=null){
				try {
					cs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
				cs=null;
			}
		}

		public static CallableStatement getCs() {
			return cs;
		}

		public static void setCs(CallableStatement cs) {
			SQLHelper.cs = cs;
		}
		


}
