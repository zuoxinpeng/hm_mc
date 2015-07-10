package com.hm.util;

import java.util.Date;
/**
 *	任务调度测试
 * @author 王凯
 *
 */
public class QuartzJob {
	private static int counter = 1;	//计数器
	
	 public void work() {
	    System.out.print("Quartz调度测试");
	    long ms = System.currentTimeMillis();
		System.out.print("\t\t" + new Date(ms) + "\t" );
		System.out.println("第" + counter++ + "次调用");
	 }
}
