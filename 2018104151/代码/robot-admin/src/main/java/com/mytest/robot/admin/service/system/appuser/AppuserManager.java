package com.zoneyet.robot.admin.service.system.appuser;

import java.util.List;

import com.zoneyet.robot.admin.entity.Page;
import com.zoneyet.robot.admin.util.PageData;


/** 会员接口类
 * @author fh313596790qq(青苔)
 * 修改时间：2015.11.2
 */
public interface AppuserManager {
	
	/**列出某角色下的所有会员
	 * @param pd
	 * @return
	 * @throws Exception
	 */
	public List<PageData> listAllAppuserByRorlid(PageData pd) throws Exception;
	
	/**会员列表
	 * @param page
	 * @return
	 * @throws Exception
	 */
	public List<PageData> listPdPageUser(Page page)throws Exception;
	
	/**通过用户名获取数据
	 * @param pd
	 * @return
	 * @throws Exception
	 */
	public PageData findByUsername(PageData pd)throws Exception;
	
	/**通过邮箱获取数据
	 * @param pd
	 * @return
	 * @throws Exception
	 */
	public PageData findByEmail(PageData pd)throws Exception;
	
	/**通过编号获取数据
	 * @param pd
	 * @return
	 * @throws Exception
	 */
	public PageData findByNumber(PageData pd)throws Exception;
	
	/**保存用户
	 * @param pd
	 * @throws Exception
	 */
	public void saveU(PageData pd)throws Exception;
	
	/**删除用户
	 * @param pd
	 * @throws Exception
	 */
	public void deleteU(PageData pd)throws Exception;
	
	/**修改用户
	 * @param pd
	 * @throws Exception
	 */
	public void editU(PageData pd)throws Exception;
	
	/**通过id获取数据
	 * @param pd
	 * @return
	 * @throws Exception
	 */
	public PageData findByUiId(PageData pd)throws Exception;
	
	/**全部会员
	 * @param pd
	 * @return
	 * @throws Exception
	 */
	public List<PageData> listAllUser(PageData pd)throws Exception;
	
	/**批量删除用户
	 * @param USER_IDS
	 * @throws Exception
	 */
	public void deleteAllU(String[] USER_IDS)throws Exception;
	
	/**获取总数
	 * @param pd
	 * @throws Exception
	 */
	public PageData getAppUserCount(String value)throws Exception;

	/**
	 * 根据nickname获取用户信息
	 * @param pd
	 * @return
	 */
	public PageData findByUserNickname(PageData pd)throws Exception;

	/**
	 * 根据nickname获取订单信息
	 * @param pd
	 * @return
	 */
	public List<PageData> listOrdersByUserNickname(PageData pd)throws Exception;

	/**
	 * 发送个人消息
	 * @param pd
	 * @throws Exception 
	 */
	public void sendNotice(PageData pd) throws Exception;

	/**
	 * 改变用户状态
	 * @param pd
	 * @throws Exception 
	 */
	public void changeStatus(PageData pd) throws Exception;

	/**
	 * 帮助中心列表
	 * @param page
	 * @return
	 * @throws Exception 
	 */
	public List<PageData> listPdPageNotice(Page page) throws Exception;

	/**
	 * 插入系统消息
	 * @param pd
	 * @throws Exception
	 */
	public void insertNotice(PageData pd) throws Exception;

	/**
	 * 删除系统消息
	 * @param pd
	 * @throws Exception
	 */
	public void deleteNotice(PageData pd) throws Exception;

	/**
	 * 消息上线
	 * @param pd
	 * @throws Exception
	 */
	public void setNoticeStatus(PageData pd) throws Exception;

	/**
	 * 通过消息名称获取上线时间
	 */
	public PageData findOnlineTime(PageData pd) throws Exception;

	/**
	 * 消息下线
	 * @param pd
	 * @throws Exception
	 */
	public void setNoticeStatus2(PageData pd) throws Exception;

	/**
	 * 批量删除消息
	 * @param arrayUSER_IDS
	 * @throws Exception
	 */
	public void deleteAllNotice(String[] arrayUSER_IDS) throws Exception;

	public List<PageData> helpNoticeList(PageData pd) throws Exception;

	
}

