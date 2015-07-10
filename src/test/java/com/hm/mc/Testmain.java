/**
 * <p>项目名称：com-hm-mc<p>
 * <ul>
 * <li>1、版权所有：</li>
 * <li>2、开发日期：2014-11-24</li>
 * <li>3、开发时间：下午4:37:19</li>
 * <li>4、作          者：lim.jong.uk</li>
 * <li>5、包路径名：com.hm.mc</li>
 * <li>6、文件名称：testmain.java</li>
 * </ul>
 */
package com.hm.mc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <ul>
 * <li>1、开发日期：2014-11-24</li>
 * <li>2、开发时间：下午4:37:19</li>
 * <li>3、作 者： ZUOXP</li>
 * <li>4、类型名称：testmain</li>
 * <li>5、类型意图：</li>
 * </ul>
 * 
 */
public class Testmain {

	public static void main(String[] args) {

		Logger logger = LoggerFactory.getLogger(Testmain.class);
		logger.info("logback 成功了");
		logger.error("logback sh功了");
	}
}
