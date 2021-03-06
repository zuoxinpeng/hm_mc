package com.quartz.base.task.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aggrepoint.dao.HibernateDao;
import com.aggrepoint.dao.HibernateServiceBase;
import com.aggrepoint.dao.PageList;
import com.quartz.base.task.dao.IMcBasScheduleJobDao;
import com.quartz.base.task.domain.ScheduleJob;
import com.quartz.base.task.service.IMcBasScheduleJobService;

/**
 * 
 * McBasScheduleJobService 调度实现service
 * 
 * ZUOXP 2015-1-19 上午11:33:23
 * 
 * @version 1.0.0
 * 
 */
@Service
@Transactional
public class McBasScheduleJobService extends HibernateServiceBase<ScheduleJob>
		implements IMcBasScheduleJobService {
	@Autowired
	private IMcBasScheduleJobDao theDao;

	public HibernateDao<ScheduleJob> getDao() {
		return theDao;
	}

	/**
	 * 根据 <br />
	 * 返回表MC_BAS_SCHEDULE_JOB 调度任务的数据列表<br />
	 * 开发者 zuoxp 2015年01月13日 17:30:53
	 * 
	 * @param order
	 *            排序字段
	 * @param orderDir
	 *            排序顺序
	 * @param pageNum
	 *            当前分页的页数
	 * @param pageSize
	 *            每页条数
	 * @return 数据列表的强类型实体类
	 */
	public PageList<ScheduleJob> findBy(String order, // 排序字段
			String orderDir, // 排序顺序
			int pageNum, // 当前分页的页数
			int pageSize// 每页条数
	) {
		return theDao.findBy(order, orderDir, pageNum, pageSize);
	}

	/**
	 * 根据 <br />
	 * JOB_NAME Name 任务名称<br />
	 * JOB_GROUP Group 任务分组<br />
	 * JOB_STATUS Status 任务状态 0禁用 1启用 2删除<br />
	 * 返回表MC_BAS_SCHEDULE_JOB 调度任务的数据列表<br />
	 * 开发者 王凯 2015年01月23日 13:35:31
	 * 
	 * @param Name
	 *            任务名称
	 * @param Group
	 *            任务分组
	 * @param Status
	 *            任务状态 0禁用 1启用 2删除
	 * @param order
	 *            排序字段
	 * @param orderDir
	 *            排序顺序
	 * @param pageNum
	 *            当前分页的页数
	 * @param pageSize
	 *            每页条数
	 * @return 数据列表的强类型实体类
	 */
	public PageList<ScheduleJob> findByNameGroupStatus(
			java.lang.String jobName, // 任务名称
			java.lang.String jobGroup, // 任务分组
			java.lang.String jobStatus, // 任务状态 0禁用 1启用 2删除
			String order, // 排序字段
			String orderDir, // 排序顺序
			int pageNum, // 当前分页的页数
			int pageSize// 每页条数
	) {
		if (jobName != null)
			jobName = jobName.toLowerCase().trim();
		if (jobGroup != null)
			jobGroup = jobGroup.toLowerCase().trim();
		if (jobStatus != null)
			jobStatus = jobStatus.toLowerCase().trim();
		return theDao.findByNameGroupStatus(jobName, jobGroup, jobStatus,
				order, orderDir, pageNum, pageSize);
	}

	/**
	 * 根据 <br />
	 * JOB_ID Id 任务ID<br />
	 * 删除数据表MC_BAS_SCHEDULE_JOB 调度任务中的内容<br />
	 * 开发者 王凯 2015年01月26日 10:25:59
	 * 
	 * @param Id
	 *            任务ID
	 */
	public int deleteById(java.lang.Long jobId // 任务ID
	) {
		return theDao.deleteById(jobId);
	}

}
