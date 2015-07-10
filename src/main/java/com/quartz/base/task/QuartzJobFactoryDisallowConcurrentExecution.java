package com.quartz.base.task;

import org.quartz.DisallowConcurrentExecution;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.quartz.base.task.domain.ScheduleJob;

 /**
 *
 * QuartzJobFactoryDisallowConcurrentExecution
 * 		若一个方法一次执行不完下次轮转时则等待改方法执行完后才执行下一次操作
 * ZUOXP
 * 2015-1-19 上午11:35:55
 * 
 * @version 1.0.0
 *
 */
@DisallowConcurrentExecution
public class QuartzJobFactoryDisallowConcurrentExecution implements Job {

	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		ScheduleJob scheduleJob = (ScheduleJob) context.getMergedJobDataMap().get("scheduleJob");
		TaskUtils.invokMethod(scheduleJob);

	}
}