package com.zoneyet.robot.admin.service.system.order;


import java.util.List;

import com.zoneyet.robot.admin.entity.Page;
import com.zoneyet.robot.admin.util.PageData;


/** 
 * 说明： 订单表接口
 * 创建人：FH Q313596790
 * 创建时间：2018-03-14
 * @version
 */
public interface OrderManager{

	/**新增
	 * @param pd
	 * @throws Exception
	 */
	public void save(PageData pd)throws Exception;
	
	/**删除
	 * @param pd
	 * @throws Exception
	 */
	public void delete(PageData pd)throws Exception;
	
	/**修改
	 * @param pd
	 * @throws Exception
	 */
	public void edit(PageData pd)throws Exception;
	
	/**列表
	 * @param page
	 * @throws Exception
	 */
	public List<PageData> list(Page page)throws Exception;
	
	/**列表(全部)
	 * @param pd
	 * @throws Exception
	 */
	public List<PageData> listAll(PageData pd)throws Exception;
	
	/**通过id获取数据
	 * @param pd
	 * @throws Exception
	 */
	public PageData findById(PageData pd)throws Exception;
	
	/**批量删除
	 * @param ArrayDATA_IDS
	 * @throws Exception
	 */
	public void deleteAll(String[] ArrayDATA_IDS)throws Exception;
	
	
	/**新增退款记录
	 * @param pd
	 * @throws Exception
	 */
	public void refund(PageData pd)throws Exception;
	
	/**通过id获取退款记录
	 * @param pd
	 * @throws Exception
	 */
	public PageData findRefundById(PageData pd)throws Exception;
	
	
}

