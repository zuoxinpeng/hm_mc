package com.quartz.base.task;

import org.quartz.DisallowConcurrentExecution;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.quartz.base.task.domain.ScheduleJob;

 /**
 *
 * QuartzJobFactory 计划任务执行处 无状态
 * 
 * ZUOXP
 * 2015-1-19 上午11:35:46
 * 
 * @version 1.0.0
 *
 */
@DisallowConcurrentExecution
public class QuartzJobFactory implements Job {

	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		ScheduleJob scheduleJob = (ScheduleJob) context.getMergedJobDataMap().get("scheduleJob");
		TaskUtils.invokMethod(scheduleJob);
	}
}