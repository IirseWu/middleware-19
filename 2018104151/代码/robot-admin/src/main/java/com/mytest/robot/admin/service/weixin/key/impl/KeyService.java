package com.zoneyet.robot.admin.service.weixin.key.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.zoneyet.robot.admin.dao.DaoSupport;
import com.zoneyet.robot.admin.entity.Page;
import com.zoneyet.robot.admin.util.PageData;
import com.zoneyet.robot.admin.service.weixin.key.KeyManager;

/** 
 * 说明： 公众平台Key信息
 * 创建人：FH Q313596790
 * 创建时间：2016-10-30
 * @version
 */
@Service("keyService")
public class KeyService implements KeyManager{

	@Resource(name = "daoSupport")
	private DaoSupport dao;
	
	/**新增
	 * @param pd
	 * @throws Exception
	 */
	public void save(PageData pd)throws Exception{
		dao.save("KeyMapper.save", pd);
	}
	
	/**删除
	 * @param pd
	 * @throws Exception
	 */
	public void delete(PageData pd)throws Exception{
		dao.delete("KeyMapper.delete", pd);
	}
	
	/**修改
	 * @param pd
	 * @throws Exception
	 */
	public void edit(PageData pd)throws Exception{
		dao.update("KeyMapper.edit", pd);
	}
	
	/**列表
	 * @param page
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<PageData> list(Page page)throws Exception{
		return (List<PageData>)dao.findForList("KeyMapper.datalistPage", page);
	}
	
	/**列表(全部)
	 * @param pd
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<PageData> listAll(PageData pd)throws Exception{
		return (List<PageData>)dao.findForList("KeyMapper.listAll", pd);
	}
	
	/**通过id获取数据
	 * @param pd
	 * @throws Exception
	 */
	public PageData findById(PageData pd)throws Exception{
		return (PageData)dao.findForObject("KeyMapper.findById", pd);
	}
	
	/**通过公众号获取数据
	 * @param pd
	 * @throws Exception
	 */
	public PageData findByWxId(PageData pd)throws Exception{
		return (PageData)dao.findForObject("KeyMapper.findByWxId", pd);
	}
	
	/**批量删除
	 * @param ArrayDATA_IDS
	 * @throws Exception
	 */
	public void deleteAll(String[] ArrayDATA_IDS)throws Exception{
		dao.delete("KeyMapper.deleteAll", ArrayDATA_IDS);
	}
	
}

