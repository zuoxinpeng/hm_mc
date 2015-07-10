package com.quartz.base.task.dao;

import java.util.List;

import org.springframework.stereotype.Component;

import com.aggrepoint.dao.HibernateDao;
import com.aggrepoint.dao.PageList;
import com.aggrepoint.dao.annotation.Delete;
import com.aggrepoint.dao.annotation.Find;
import com.aggrepoint.dao.annotation.Like;
import com.aggrepoint.dao.annotation.PageNum;
import com.aggrepoint.dao.annotation.PageSize;
import com.aggrepoint.dao.annotation.Param;
import com.aggrepoint.dao.annotation.Replace;
import com.quartz.base.task.domain.ScheduleJob;

/**
 * 
 * IMcBasScheduleJobDao 调度任务接口
 * 
 * ZUOXP 2015-1-19 上午11:32:45
 * 
 * @version 1.0.0
 * 
 */
@Component
public interface IMcBasScheduleJobDao extends HibernateDao<ScheduleJob> {

	/**
	 * 根据 <br />
	 * 返回表MC_BAS_SCHEDULE_JOB 调度任务的数据列表<br />
	 * 开发者 zuoxp 2015年01月12日 09:10:33
	 * 
	 * @param order
	 *            排序字段
	 * @param orderDir
	 *            排序顺序
	 * @return 数据列表的强类型实体类
	 */
	@Find(" from ScheduleJob where 1=1  #{order by :order :dir}")
	public List<ScheduleJob> findBy(@Replace("order") String order, // 排序字段
			@Replace("dir") String orderDir// 排序顺序
	);

	/**
	 * 根据 <br />
	 * 返回表MC_BAS_SCHEDULE_JOB 调度任务的数据列表<br />
	 * 开发者 zuoxp 2015年01月12日 09:10:33
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
	@Find(" from ScheduleJob where 1=1  #{order by :order :dir}")
	public PageList<ScheduleJob> findBy(@Replace("order") String order, // 排序字段
			@Replace("dir") String orderDir, // 排序顺序
			@PageNum int pageNum, // 当前分页的页数
			@PageSize int pageSize// 每页条数
	);

	/**
	 * 根据 <br />
	 * JOB_NAME Name 任务名称<br />
	 * JOB_GROUP Group 任务分组<br />
	 * JOB_STATUS Status 任务状态 0禁用 1启用 2删除<br />
	 * METHOD_NAME Name 任务调用的方法名<br />
	 * 返回表MC_BAS_SCHEDULE_JOB 调度任务的数据列表<br />
	 * 开发者 王凯 2015年01月23日 13:21:01
	 * 
	 * @param Name
	 *            任务名称
	 * @param Group
	 *            任务分组
	 * @param Status
	 *            任务状态 0禁用 1启用 2删除
	 * @param Name
	 *            任务调用的方法名
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
	@Find(" from ScheduleJob where 1=1  #{and lower(jobName) like :jobName}  #{and lower(jobGroup) like :jobGroup}  #{and lower(jobStatus) = :jobStatus}  #{order by :order :dir}")
	public PageList<ScheduleJob> findByNameGroupStatus(
			@Like("jobName") java.lang.String jobName, // 任务名称
			@Like("jobGroup") java.lang.String jobGroup, // 任务分组
			@Param("jobStatus") java.lang.String jobStatus, // 任务状态 0禁用 1启用 2删除
			@Replace("order") String order, // 排序字段
			@Replace("dir") String orderDir, // 排序顺序
			@PageNum int pageNum, // 当前分页的页数
			@PageSize int pageSize// 每页条数
	);

	/**
	 * 根据 <br />
	 * JOB_ID Id 任务ID<br />
	 * 删除数据表MC_BAS_SCHEDULE_JOB 调度任务中的内容<br />
	 * 开发者 王凯 2015年01月26日 10:24:09
	 * 
	 * @param Id
	 *            任务ID
	 */
	@Delete("delete ScheduleJob where jobId = :jobId ")
	public int deleteById(@Param("jobId") java.lang.Long jobId // 任务ID
	);

}
