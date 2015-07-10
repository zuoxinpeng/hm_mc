package com.hm.util;

import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class MyDate extends java.util.Date {

	
	 /**
	 * serialVersionUID:TODO（用一句话描述这个变量表示什么）
	 *
	 * @since 1.0.0
	 */
	
	private static final long serialVersionUID = 1L;

	public MyDate() {
		super();
	}

	public MyDate(long time) {
		super(time);
	}

	public MyDate(java.sql.Timestamp time) {
		super(time.getTime());
	}

	public MyDate(java.sql.Date time) {
		super(time.getTime());
	}

	public MyDate(java.util.Date time) {
		super(time.getTime());
	}

	public MyDate(String time) {
		super(Converts.StrToDate(time).getTime());
	}

	/**
	 * 构造一个MyDate值
	 * 
	 * @param time
	 *            时间
	 * @return
	 */
	public static MyDate setDate() {
		return new MyDate();
	}

	/**
	 * 构造一个MyDate值
	 * 
	 * @param time
	 *            时间
	 * @return
	 */
	public static MyDate setDate(java.sql.Timestamp time) {
		if (time != null) {
			return new MyDate(time.getTime());
		} else {
			return new MyDate();
		}
	}

	/**
	 * 构造一个MyDate值
	 * 
	 * @param time
	 *            时间
	 * @return
	 */
	public static MyDate setDate(java.sql.Date time) {
		if (time != null) {
			return new MyDate(time.getTime());
		} else {
			return new MyDate();
		}
	}

	/**
	 * 构造一个MyDate值
	 * 
	 * @param time
	 *            时间
	 * @return
	 */
	public static MyDate setDate(java.util.Date time) {
		if (time != null) {
			return new MyDate(time.getTime());
		} else {
			return new MyDate();
		}
	}

	/**
	 * 转化成MyDate类型
	 * 
	 * @param s
	 *            需要转化的字符串
	 * @return
	 */
	public static MyDate Parse(String s) {
		return new MyDate(Converts.StrToDate(s).getTime());
	}

	/**
	 * 把此 Date 对象转换为以下形式的 String： <br />
	 * dow mon dd hh:mm:ss zzz yyyy<br />
	 * 其中：<br />
	 * dow 是一周中的某一天 (Sun, Mon, Tue, Wed, Thu, Fri, Sat)。 <br />
	 * mon 是月份 (Jan, Feb, Mar, Apr, May, Jun, Jul, Aug, Sep, Oct, Nov, Dec)。 <br />
	 * dd 是一月中的某一天（01 至 31），显示为两位十进制数。<br />
	 * hh 是一天中的小时（00 至 23），显示为两位十进制数。 <br />
	 * mm 是小时中的分钟（00 至 59），显示为两位十进制数。 <br />
	 * ss 是分钟中的秒数（00 至 61），显示为两位十进制数。 <br />
	 * zzz 是时区（并可以反映夏令时）。标准时区缩写包括方法 parse 识别的时区缩写。如果不提供时区信息，则 zzz 为空，即根本不包括任何字符。 <br />
	 * yyyy 是年份，显示为 4 位十进制数。<br />
	 */
	@Override
	public String toString() {
		super.toString();
		return Converts.DateToString(this, "yyyy-MM-dd HH:mm:ss");
	}

	/**
	 * 
	 * 把此 Date 对象转换为以下形式的 String： <br />
	 * dow mon dd hh:mm:ss zzz yyyy<br />
	 * 其中：<br />
	 * dow 是一周中的某一天 (Sun, Mon, Tue, Wed, Thu, Fri, Sat)。 <br />
	 * mon 是月份 (Jan, Feb, Mar, Apr, May, Jun, Jul, Aug, Sep, Oct, Nov, Dec)。 <br />
	 * dd 是一月中的某一天（01 至 31），显示为两位十进制数。<br />
	 * hh 是一天中的小时（00 至 23），显示为两位十进制数。 <br />
	 * mm 是小时中的分钟（00 至 59），显示为两位十进制数。 <br />
	 * ss 是分钟中的秒数（00 至 61），显示为两位十进制数。 <br />
	 * zzz 是时区（并可以反映夏令时）。标准时区缩写包括方法 parse 识别的时区缩写。如果不提供时区信息，则 zzz 为空，即根本不包括任何字符。 <br />
	 * yyyy 是年份，显示为 4 位十进制数。<br />
	 * 
	 * @param fromat
	 *            格式化样式
	 * @return
	 */
	public String toString(String fromat) {
		return Converts.DateToString(this, fromat);
	}

	/**
	 * 转化成 yyyy-MM-dd 格式
	 * 
	 * @return
	 */
	public String toShortDateString() {
		return Converts.DateToString(this, "yyyy-MM-dd");
	}

	/**
	 * 转化成 HH:mm 格式
	 * 
	 * @return
	 */
	public String toShortTimeString() {
		return Converts.DateToString(this, "HH:mm");
	}

	/**
	 * 转化成 HH:mm:ss 格式
	 * 
	 * @return
	 */
	public String toLongTimeString() {
		return Converts.DateToString(this, "HH:mm:ss");
	}

	/**
	 * 转化成 yyyy-MM-dd 格式
	 * 
	 * @return
	 */
	public String getShortDate() {
		return Converts.DateToString(this, "yyyy-MM-dd");
	}

	/**
	 * 转化成 yyyy年-MM月-dd日 格式
	 * 
	 * @return
	 */
	public String getChDate() {
		return Converts.DateToString(this, "yyyy年MM月dd日");
	}

	/**
	 * 转化成MM月dd日格式
	 * 
	 * @return
	 */
	public String getYMDate() {
		return Converts.DateToString(this, "MM月dd日");
	}

	/**
	 * 转化成 MM-dd 格式
	 * 
	 * @return
	 */
	public String getMidDate() {
		return Converts.DateToString(this, "MM-dd");
	}

	/**
	 * 转化成 HH:mm 格式
	 * 
	 * @return
	 */
	public String getShortTime() {
		return Converts.DateToString(this, "HH:mm");
	}

	/**
	 * 转化成 HH:mm:ss 格式
	 * 
	 * @return
	 */
	public String getLongTime() {
		return Converts.DateToString(this, "HH:mm:ss");
	}

	/**
	 * 计算当前时间前后多少日
	 * 
	 * @return
	 */
	public MyDate addDays(int delay) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(this);
		cal.add(Calendar.DAY_OF_YEAR, delay);
		return new MyDate(cal.getTime());
	}

	/**
	 * 计算当前时间前后多少小时
	 * 
	 * @return
	 */
	public MyDate addHours(int delay) {
		// long myTime = getTime() + delay * 60 * 60 * 1000;
		// return new MyDate(myTime);

		// 1、取得本地时间：
		java.util.Calendar cal = java.util.Calendar.getInstance();
		cal.setTime(this);
		cal.add(Calendar.HOUR, delay);
		return new MyDate(cal.getTime());
	}

	/**
	 * 计算当前时间前后多少分钟
	 * 
	 * @return
	 */
	public MyDate addMinutes(int delay) {
		// long myTime = getTime() + delay * 60 * 1000;
		// return new MyDate(myTime);
		Calendar cal = Calendar.getInstance();
		cal.setTime(this);
		cal.add(Calendar.MINUTE, delay);
		return new MyDate(cal.getTime());
	}

	/**
	 * 计算当前时间前后多少秒
	 * 
	 * @return
	 */
	public MyDate addSeconds(long delay) {
		long myTime = getTime() + delay * 1000;
		return new MyDate(myTime);
	}

	/**
	 * 计算当前时间前后多少毫秒
	 * 
	 * @return
	 */
	public MyDate addMilliseconds(long delay) {
		long myTime = getTime() + delay;
		return new MyDate(myTime);
	}

	/**
	 * 计算当前时间前后多少月
	 * 
	 * @return
	 */
	public MyDate addMonths(int delay) {
		java.util.Calendar c = java.util.Calendar.getInstance();
		c.setTime(this);
		c.add(java.util.Calendar.MONTH, delay); // 将当前日期加一个月
		return new MyDate(c.getTime().getTime());
	}

	/**
	 * 计算当前时间前后多少年
	 * 
	 * @return
	 */
	public MyDate addYears(int delay) {
		java.util.Calendar c = java.util.Calendar.getInstance();
		c.setTime(this);
		c.add(java.util.Calendar.YEAR, delay); // 将当前日期加一个年
		return new MyDate(c.getTime().getTime());
	}

	/**
	 * 返回指定的年份是否为闰年的指示。
	 * 
	 * @return
	 */
//	public boolean getIsLeapYear() {
//		return getIsLeapYear(getYear());
//	}

	/**
	 * 返回指定的年份是否为闰年的指示。
	 * 
	 * @return
	 */
	public boolean getIsLeapYear(int year) {
		if (year < 1 || year > 0x270f) {
			return false;
		}
		if (year % 4 != 0) {
			return false;
		}
		if (year % 100 == 0) {
			return year % 400 == 0;
		}
		return true;

	}

//	/**
//	 * 当前时间的年份
//	 * 
//	 * @return
//	 */
//	@Override
//	public int getYear() {
//		Calendar c = Calendar.getInstance(Locale.CHINA);
//		c.setTime(this);
//		return c.get(Calendar.YEAR);
//	}
//
//	/**
//	 * 当前时间的月
//	 * 
//	 * @return
//	 */
//	@Override
//	public int getMonth() {
//		Calendar c = Calendar.getInstance(Locale.CHINA);
//		c.setTime(this);
//		return c.get(Calendar.MONTH);
//	}
//
//	/**
//	 * 当前时间的天
//	 * 
//	 * @return
//	 */
//	@Override
//	public int getDate() {
//		Calendar c = Calendar.getInstance(Locale.CHINA);
//		c.setTime(this);
//		return c.get(Calendar.DATE);
//	}
//
//	/**
//	 * 当前时间的小时
//	 * 
//	 * @return
//	 */
//	@Override
//	public int getHours() {
//		Calendar c = Calendar.getInstance(Locale.CHINA);
//		c.setTime(this);
//		return c.get(Calendar.HOUR_OF_DAY);
//	}
//
//	/**
//	 * 当前时间的分钟
//	 * 
//	 * @return
//	 */
//	@Override
//	public int getMinutes() {
//		Calendar c = Calendar.getInstance(Locale.CHINA);
//		c.setTime(this);
//		return c.get(Calendar.MINUTE);
//	}
//
//	/**
//	 * 当前时间的秒
//	 * 
//	 * @return
//	 */
//	@Override
//	public int getSeconds() {
//		Calendar c = Calendar.getInstance(Locale.CHINA);
//		c.setTime(this);
//		return c.get(Calendar.SECOND);
//	}

	/**
	 * 当前时间的毫秒
	 * 
	 * @return
	 */
	public int getMilliseconds() {
		MyDate d2 = new MyDate(Converts.StrToDate(this
				.toString()));
		TimeSpan ts = new TimeSpan(this, d2);
		return (int) ts.getMilliseconds();
	}

	/**
	 * 返回当前月份的第一天
	 * 
	 * @return
	 */
	public MyDate getFastMonthDay() {
		return getFastMonthDay(this);
	}

	/**
	 * 返回当前月份的第一天
	 * 
	 * @return
	 */
	public MyDate getFastMonthDay(MyDate mydate) {
		return new MyDate(Converts.StrToDate(mydate.toString("yyyy-MM-01")));
	}

	/**
	 * 返回当前月份的最后一天
	 * 
	 * @return
	 */
//	public MyDate getLastMonthDay() {
//		return getLastMonthDay(this);
//	}

	/**
	 * 返回当前月份的最后一天
	 * 
	 * @return
	 */
//	public MyDate getLastMonthDay(MyDate mydate) {
//		switch (mydate.getMonth()) {
//		case 1:
//			return new MyDate(Converts.StrToDate(mydate.toString("yyyy-MM-31")));
//		case 2:
//			if (getIsLeapYear()) {
//				return new MyDate(Converts.StrToDate(mydate
//						.toString("yyyy-MM-29")));
//			} else {
//				return new MyDate(Converts.StrToDate(mydate
//						.toString("yyyy-MM-28")));
//			}
//		case 3:
//			return new MyDate(Converts.StrToDate(mydate.toString("yyyy-MM-31")));
//		case 4:
//			return new MyDate(Converts.StrToDate(mydate.toString("yyyy-MM-30")));
//		case 5:
//			return new MyDate(Converts.StrToDate(mydate.toString("yyyy-MM-31")));
//		case 6:
//			return new MyDate(Converts.StrToDate(mydate.toString("yyyy-MM-30")));
//		case 7:
//			return new MyDate(Converts.StrToDate(mydate.toString("yyyy-MM-31")));
//		case 8:
//			return new MyDate(Converts.StrToDate(mydate.toString("yyyy-MM-31")));
//		case 9:
//			return new MyDate(Converts.StrToDate(mydate.toString("yyyy-MM-30")));
//		case 10:
//			return new MyDate(Converts.StrToDate(mydate.toString("yyyy-MM-31")));
//		case 11:
//			return new MyDate(Converts.StrToDate(mydate.toString("yyyy-MM-30")));
//		case 12:
//			return new MyDate(Converts.StrToDate(mydate.toString("yyyy-MM-31")));
//
//		default:
//			return new MyDate(Converts.StrToDate(mydate.toString("yyyy-MM-30")));
//		}
//
//	}

	/**
	 * 当前时间所在的年度是第几周
	 * 
	 * @return
	 */
	public int getWeekInYear() {
		return getWeekInYear(this);
	}

	/**
	 * 当前时间所在的年度是第几周
	 * 
	 * @return
	 */
	public int getWeekInYear(MyDate dt) {
		// MyDate dt1 = new
		// MyDate(Converts.StrToDate(dt.toString("yyyy-01-01")));
		// int iweek = dt1.getDayInWeek();
		//
		// int iyear = dt.getDayInYear();
		// int week = iyear - iweek;
		// return week / 7;
		Calendar c = Calendar.getInstance(Locale.CHINA);
		c.setTime(dt);
		return c.get(Calendar.WEEK_OF_YEAR);
	}

	/**
	 * 当前时间所在的所在周是第几天
	 * 
	 * @return
	 */
	public int getDayInWeek() {
		return getDayInWeek(this);
	}

	/**
	 * 当前时间所在的所在周是第几天
	 * 
	 * @return
	 */
	public int getDayInWeek(MyDate dt) {
		Calendar c = Calendar.getInstance();
		c.setTime(dt);
		int iDayInWeek = c.get(Calendar.DAY_OF_WEEK) - 1;
		if (iDayInWeek == 0) {
			iDayInWeek = 7;
		}
		return iDayInWeek;
	}

	/**
	 * 当前时间所在的所在周是第几天的中文表达方式
	 * 
	 * @return
	 */
	public String getDayInWeekStr() {
		return getDayInWeekStr(this);
	}

	/**
	 * 当前时间所在的所在周是第几天的中文表达方式
	 * 
	 * @return
	 */
	public String getDayInWeekStr(MyDate dt) {
		String str = "";
		int iDayInWeek = getDayInWeek(dt);
		if (7 == iDayInWeek) {
			str = "星期日";
		} else if (1 == iDayInWeek) {
			str = "星期一";
		} else if (2 == iDayInWeek) {
			str = "星期二";
		} else if (3 == iDayInWeek) {
			str = "星期三";
		} else if (4 == iDayInWeek) {
			str = "星期四";
		} else if (5 == iDayInWeek) {
			str = "星期五";
		} else if (6 == iDayInWeek) {
			str = "星期六";
		}
		return str;
	}

	/**
	 * 返回当前周的第一天
	 * 
	 * @return
	 */
	public MyDate getFastWeekDay() {
		return getFastWeekDay(this);
	}

	/**
	 * 返回当前周的第一天
	 * 
	 * @return
	 */
	public MyDate getFastWeekDay(MyDate dt) {
		int iDayInWeek = getDayInWeek(dt);
		return dt.addDays(-iDayInWeek + 1);
	}

	/**
	 * 返回当前周的最后一天
	 * 
	 * @return
	 */
	public MyDate getLastWeekDay() {
		return getLastWeekDay(this);
	}

	/**
	 * 返回当前周的最后一天
	 * 
	 * @return
	 */
	public MyDate getLastWeekDay(MyDate dt) {
		int iDayInWeek = getDayInWeek(dt);
		return dt.addDays(7 - iDayInWeek);
	}

	/**
	 * 当前时间所在的年度是第几天
	 * 
	 * @return
	 */
	public int getDayInYear() {
		return getDayInYear(this);
	}

	/**
	 * 当前时间所在的年度是第几天
	 * 
	 * @return
	 */
	public int getDayInYear(MyDate dt) {
		Calendar c = Calendar.getInstance(Locale.CHINA);
		c.setTime(dt);
		return c.get(Calendar.DAY_OF_YEAR);
	}

	/**
	 * 获得当前年月日的数字形式，例如:20130102
	 * 
	 * @return
	 */
	public int getYearMonthDay() {
		return Integer.parseInt(this.toString("yyyyMMdd"));
	}

	/**
	 * 获得当前年月日的数字形式，例如:20130102
	 * 
	 * @return
	 */
//	public int getDayBetween19000101() {
//		int yearall = this.getYear() - 1900;
//		int days = 0;
//		for (int i = 0; i < yearall; i++) {
//			int year = 1900 + i;
//			if (this.getIsLeapYear(year)) {
//				days += 366;
//			} else {
//				days += 365;
//			}
//		}
//		return days + getDayInYear() - 1;
//	}

	/**
	 * 获得当前年月日的数字形式，例如:20130102
	 * 
	 * @return
	 */
//	public int getWeekBetween19000101() {
//		return getDayBetween19000101() / 7;
//	}

	// public static void main(String[] args)
	// {
	//
	// MyDate d2 = new MyDate("3013-8-1");
	// // d2 = new MyDate("2012-12-30 16:21:41");
	// // d2 = new MyDate("2013-01-01 16:21:41");
	// System.out.println(d2);
	// // d2 = d2.addHours(100);
	// d2 = d2.addMinutes(60 * 24);
	// // d2 = d2.addDays(100000);
	// System.out.println(d2);
	//
	// System.out.println(d2.getDayBetween19000101());
	// System.out.println(d2.getWeekBetween19000101());
	//
	// // System.out.println(d2.getFastMonthDay());
	// // System.out.println(d2.getLastMonthDay());
	// // System.out.println(d2.getFastWeekDay());
	// // System.out.println(d2.getLastWeekDay());
	// // System.out.println(d2.getDayInWeek());
	// // System.out.println(d2.getDayInWeekStr());
	// // System.out.println(d2.getWeekInYear());
	// // System.out.println(d2.getDayInYear());
	// // System.out.println(d2.getMilliseconds());
	//
	// /*
	// * SimpleDateFormat foo = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
	// System.out.println("foo:"+foo.format(new Date()));
	// *
	// * Calendar gc = GregorianCalendar.getInstance();
	// System.out.println("gc.getTime():"+gc.getTime());
	// System.out.println("gc.getTimeInMillis():"+new
	// Date(gc.getTimeInMillis()));
	// *
	// * //当前系统默认时区的时间： Calendar calendar=new GregorianCalendar();
	// System.out.print("时区："+calendar.getTimeZone().getID()+"  ");
	// System.out.println("时间："+calendar.get(Calendar.HOUR_OF_DAY)+":"+calendar.get(Calendar.MINUTE));
	// //美国洛杉矶时区 TimeZone tz=TimeZone.getTimeZone("America/Los_Angeles"); //时区转换
	// calendar.setTimeZone(tz);
	// System.out.print("时区："+calendar.getTimeZone().getID()+"  ");
	// System.out.println("时间："+calendar.get(Calendar.HOUR_OF_DAY)+":"+calendar.get(Calendar.MINUTE));
	// Date time=new Date();
	// *
	// * //1、取得本地时间： java.util.Calendar cal = java.util.Calendar.getInstance();
	// *
	// * //2、取得时间偏移量： int zoneOffset = cal.get(java.util.Calendar.ZONE_OFFSET);
	// *
	// * //3、取得夏令时差： int dstOffset = cal.get(java.util.Calendar.DST_OFFSET);
	// *
	// * //4、从本地时间里扣除这些差量，即可以取得UTC时间： cal.add(java.util.Calendar.MILLISECOND,
	// -(zoneOffset + dstOffset));
	// *
	// * //之后调用cal.get(int x)或cal.getTimeInMillis()方法所取得的时间即是UTC标准时间。
	// System.out.println("UTC:"+new Date(cal.getTimeInMillis()));
	// *
	// * Calendar calendar1 = Calendar.getInstance(); TimeZone tztz =
	// TimeZone.getTimeZone("GMT"); calendar1.setTimeZone(tztz);
	// System.out.println(calendar.getTime());
	// System.out.println(calendar.getTimeInMillis());
	// */
	// }

	/**
	 * 获得当前年月日的数字形式，例如:2013-10-14 10:45:28 （n = 1） 执行后返回 2013-10-15 10:45:28
	 * 
	 * @param n
	 *            当前日期加N天
	 * @return Date
	 */
	public static Date getNextDate(Integer n) {
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DAY_OF_YEAR, 1);
		Date date = calendar.getTime();
		return date;
	}

	/**
	 * 获得指定年月日的数字形式，例如:2013-10-25 （n = 5） 执行后返回 2013-10-30
	 * 
	 * @param n
	 *            当前日期加N天
	 * @return Date
	 */
	public static Date getAfterDate(Date nowTime, int n) {
		Calendar c = Calendar.getInstance();
		c.setTimeInMillis(nowTime.getTime());
		c.add(Calendar.DATE, n);// n 天后的日期
		Date date = new Date(c.getTimeInMillis()); // 将c转换成Date
		return date;
	}
}
