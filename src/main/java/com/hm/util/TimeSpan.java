package com.hm.util;

import java.util.Date;

public class TimeSpan {

	private Date d1, d2;

	// [start] 表示一个时间间隔。
	/**
	 * 表示一个时间间隔。
	 * 
	 * @param d1
	 *            结束时间
	 * @param d2
	 *            开始时间
	 */
	public TimeSpan(Date d1, Date d2) {
		this.d1 = d1;
		this.d2 = d2;
	}

	// [end]

	// [start] 获取当前 TimeSpan 结构所表示的时间间隔的天数部分。
	/**
	 * 获取当前 TimeSpan 结构所表示的时间间隔的天数部分。
	 * 
	 * @return
	 */
	public long getDays() {
		// long day = 0;
		// if ((d1.getTime() - d2.getTime()) % (24 * 60 * 60 * 1000) == 0)
		// {
		// day = (d1.getTime() - d2.getTime()) / (24 * 60 * 60 * 1000);
		// }
		// else
		// {
		// day = (d1.getTime() - d2.getTime() - getTotalMinutes() * (24 * 60 *
		// 60 * 1000)) / (24 * 60 * 60 * 1000);
		// }
		// return day;
		return getTotalDays();
	}

	// [end]

	// [start] 获取以整小时数和小时的小数部分表示的当前 TimeSpan 结构的值。
	/**
	 * 获取以整小时数和小时的小数部分表示的当前 TimeSpan 结构的值。<br />
	 * 
	 * @return
	 */
	public long getHours() {
		long total = getTotalDays() * 24 * 60 * 60 * 1000;
		long day = d1.getTime() - d2.getTime() - total;
		if (day == 0) {
			day = 0;
		} else {
			day = (day / 60 / 60 / 1000);
		}
		return day;
	}

	// [end]

	// [start] 获取以整毫秒数和毫秒的小数部分表示的当前 TimeSpan 结构的值。
	/**
	 * 获取以整毫秒数和毫秒的小数部分表示的当前 TimeSpan 结构的值。
	 * 
	 * @return
	 */
	public long getMilliseconds() {
		long total = getTotalSeconds() * 1000;
		long day = d1.getTime() - d2.getTime() - total;
		return day;
	}

	// [end]

	// [start] 获取以整分钟数和分钟的小数部分表示的当前 TimeSpan 结构的值。
	/**
	 * 获取以整分钟数和分钟的小数部分表示的当前 TimeSpan 结构的值。<br />
	 * 
	 * @return
	 */
	public long getMinutes() {
		long total = getTotalHours() * 60 * 60 * 1000;
		long day = d1.getTime() - d2.getTime() - total;
		if (day == 0) {
			day = 0;
		} else {
			day = (day / 60 / 1000);
		}
		return day;
	}

	// [end]

	// [start] 获取以整秒数和秒的小数部分表示的当前 TimeSpan 结构的值。
	/**
	 * 获取以整秒数和秒的小数部分表示的当前 TimeSpan 结构的值。<br />
	 * 
	 * @return
	 */
	public long getSeconds() {
		long total = getTotalMinutes() * 60 * 1000;
		long day = d1.getTime() - d2.getTime() - total;
		if (day == 0) {
			day = 0;
		} else {
			day = (day / 1000);
		}
		return day;
	}

	// [end]

	// [start] 获取以整天数和天的小数部分表示的当前 TimeSpan 结构的值。
	/**
	 * 获取以整天数和天的小数部分表示的当前 TimeSpan 结构的值。<br />
	 * 不够1天则舍弃
	 * 
	 * @return
	 */
	public long getTotalDays() {
		long day = 0;
		day = (d1.getTime() - d2.getTime()) / (24 * 60 * 60 * 1000);
		return day;
	}

	// [end]

	// [start] 获取以整小时数和小时的小数部分表示的当前 TimeSpan 结构的值。
	/**
	 * 获取以整小时数和小时的小数部分表示的当前 TimeSpan 结构的值。<br />
	 * 不够1小数则舍弃
	 * 
	 * @return
	 */
	public long getTotalHours() {
		long day = 0;
		day = (d1.getTime() - d2.getTime()) / (60 * 60 * 1000);
		return day;
	}

	// [end]

	// [start] 获取以整毫秒数和毫秒的小数部分表示的当前 TimeSpan 结构的值。
	/**
	 * 获取以整毫秒数和毫秒的小数部分表示的当前 TimeSpan 结构的值。
	 * 
	 * @return
	 */
	public long getTotalMilliseconds() {
		long day = 0;
		day = (d1.getTime() - d2.getTime());
		return day;
	}

	// [end]

	// [start] 获取以整分钟数和分钟的小数部分表示的当前 TimeSpan 结构的值。
	/**
	 * 获取以整分钟数和分钟的小数部分表示的当前 TimeSpan 结构的值。<br />
	 * 不够1分钟则舍弃
	 * 
	 * @return
	 */
	public long getTotalMinutes() {
		long day = 0;
		day = (d1.getTime() - d2.getTime()) / (60 * 1000);
		return day;
	}

	// [end]

	// [start] 获取以整秒数和秒的小数部分表示的当前 TimeSpan 结构的值。
	/**
	 * 获取以整秒数和秒的小数部分表示的当前 TimeSpan 结构的值。<br />
	 * 不够1秒则舍弃
	 * 
	 * @return
	 */
	public long getTotalSeconds() {
		long day = 0;
		day = (d1.getTime() - d2.getTime()) / (1000);
		return day;
	}

	// [end]

	// [start] 将当前 TimeSpan 对象的值转换为其等效的字符串表示形式。
	/**
	 * 将当前 TimeSpan 对象的值转换为其等效的字符串表示形式。
	 */
	@Override
	public String toString() {

		return getDays() + "." + getHours() + ":" + getMinutes() + ":"
				+ getSeconds();
	}

	// [end]

	public static void main(String[] args) {
		TimeSpan ts1 = new TimeSpan(Converts.StrToDate("2013-02-02 16:21:41"),
				Converts.StrToDate("2013-01-02 15:00:00"));

		for (int i = 0; i < 10000000; i++) {
			new Date();
		}
		// ts1 = new TimeSpan(d1, d2);

		System.out.println(ts1.toString());
		System.out.println(ts1.getDays());
		System.out.println(ts1.getHours());
		System.out.println(ts1.getMinutes());
		System.out.println(ts1.getSeconds());
		System.out.println(ts1.getMilliseconds());

		float a = 2;
		Float xx = a;
		Float x1 = a - 1;
		System.out.println(xx - x1);
		a = xx;

		// System.out.println(ts1.getTotalMilliseconds());
		// System.out.println(ts1.getTotalSeconds());
		// System.out.println(ts1.getTotalMinutes());
		// System.out.println(ts1.getTotalDays());
	}
}
