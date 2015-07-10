package com.hm.util;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;

public class Converts {

	/**
	 * 数据默认起始时间
	 */
	public static Date DateTimeStar = StrToDate("1900-1-1 00:00:00");

	/**
	 * 数据默认结束时间
	 */
	public static Date DateTimeEnd = StrToDate("9999-12-31 23:59:59");

	// [start] Object型转换为Strint型
	// Object型转换为Strint型
	/**
	 * Object型转换为Strint型
	 *
	 * @param strValue
	 *            要转换的字符串
	 * @param defValue
	 *            缺省值 转换后的int类型结果
	 */
	public static Object objToObject(Object strValue) {
		return strValue;
	}

	// [end]

	// [start] Object型转换为Strint型
	// Object型转换为Strint型
	/**
	 * Object型转换为Strint型
	 *
	 * @param strValue
	 *            要转换的字符串
	 * @param defValue
	 *            缺省值 转换后的int类型结果
	 */
	public static String objToStr(Object strValue) {
		return objToStr(strValue, "");
	}

	// Object型转换为Strint型
	/**
	 * Object型转换为Strint型
	 *
	 * @param strValue
	 *            要转换的字符串
	 * @param defValue
	 *            缺省值 转换后的int类型结果
	 */
	public static String objToStr(Object strValue, String defValue) {
		// if (strValue.getClass() == Double.class)
		// {
		// System.out.println("double:=");
		// }
		if (strValue == null || strValue.toString().length() == 0)
			return defValue;
		return strValue.toString();
	}

	// [end]

	// [start] Date型转换为Strint型
	// [start] Date型转换为Strint型
	/**
	 * Date型转换为Strint型
	 *
	 * @param strValue
	 *            要转换的字符串
	 * @param defValue
	 *            缺省值 转换后的int类型结果
	 */
	public static String objToStr(Date strValue) {
		return objToStr(strValue, "");
	}

	// [end]

	// [start] Date型转换为Strint型
	/**
	 * Date型转换为Strint型
	 *
	 * @param strValue
	 *            要转换的字符串
	 * @param defValue
	 *            缺省值 转换后的int类型结果
	 */
	public static String objToStr(Date strValue, String defValue) {
		return objToStr(strValue, defValue, "yyyy-MM-dd HH:mm:ss");// 使用系统默认的格式
	}

	// [end]

	// [start] Date型转换为Strint型
	/**
	 * Date型转换为Strint型
	 *
	 * @param strValue
	 *            要转换的字符串
	 * @param defValue
	 *            缺省值 转换后的int类型结果
	 */
	public static String objToStr(Date strValue, String defValue, String format) {
		if (strValue == null || strValue.toString().length() == 0)
			return defValue;
		SimpleDateFormat formatter = new SimpleDateFormat(format);

		return formatter.format(strValue);
	}

	// [end]

	// [end]
	// [start] boolean型转换为Strint型
	/**
	 * boolean型转换为Strint型
	 *
	 * @param strValue
	 *            要转换的字符串
	 * @param defValue
	 *            缺省值 转换后的int类型结果
	 */
	public static String objToStr(boolean strValue) {
		return objToStr(strValue, "");
	}

	// [end]

	// [start] boolean型转换为Strint型
	/**
	 * boolean型转换为Strint型
	 *
	 * @param strValue
	 *            要转换的字符串
	 * @param defValue
	 *            缺省值 转换后的int类型结果
	 */
	public static String objToStr(boolean strValue, String defValue) {
		return strValue + "";
	}

	// [end]

	// [start] char型转换为Strint型
	/**
	 * char型转换为Strint型
	 *
	 * @param strValue
	 *            要转换的字符串
	 * @param defValue
	 *            缺省值 转换后的int类型结果
	 */
	public static String objToStr(char strValue) {
		return objToStr(strValue, "");
	}

	// [end]

	// [start] char型转换为Strint型
	/**
	 * char型转换为Strint型
	 *
	 * @param strValue
	 *            要转换的字符串
	 * @param defValue
	 *            缺省值 转换后的int类型结果
	 */
	public static String objToStr(char strValue, String defValue) {
		if (strValue == 0)
			return defValue;
		return strValue + "";
	}

	// [end]

	// [start] byte型转换为Strint型
	/**
	 * byte型转换为Strint型
	 *
	 * @param strValue
	 *            要转换的字符串
	 * @param defValue
	 *            缺省值 转换后的int类型结果
	 */
	public static String objToStr(byte strValue) {
		return objToStr(strValue, "");
	}

	// [end]

	// [start] byte型转换为Strint型
	/**
	 * byte型转换为Strint型
	 *
	 * @param strValue
	 *            要转换的字符串
	 * @param defValue
	 *            缺省值 转换后的int类型结果
	 */
	public static String objToStr(byte strValue, String defValue) {
		if (strValue == 0)
			return defValue;
		return strValue + "";
	}

	// [end]

	// [start] short型转换为Strint型
	/**
	 * short型转换为Strint型
	 *
	 * @param strValue
	 *            要转换的字符串
	 * @param defValue
	 *            缺省值 转换后的int类型结果
	 */
	public static String objToStr(short strValue) {
		return objToStr(strValue, "");
	}

	// [end]

	// [start] short型转换为Strint型
	/**
	 * short型转换为Strint型
	 *
	 * @param strValue
	 *            要转换的字符串
	 * @param defValue
	 *            缺省值 转换后的int类型结果
	 */
	public static String objToStr(short strValue, String defValue) {
		if (strValue == 0)
			return defValue;
		return strValue + "";
	}

	// [end]

	// [start] int型转换为Strint型
	/**
	 * int型转换为Strint型
	 *
	 * @param strValue
	 *            要转换的字符串
	 * @param defValue
	 *            缺省值 转换后的int类型结果
	 */
	public static String objToStr(int strValue) {
		return objToStr(strValue, "");
	}

	// [end]

	// [start] int型转换为Strint型
	/**
	 * int型转换为Strint型
	 *
	 * @param strValue
	 *            要转换的字符串
	 * @param defValue
	 *            缺省值 转换后的int类型结果
	 */
	public static String objToStr(int strValue, String defValue) {
		if (strValue == 0)
			return defValue;
		return strValue + "";
	}

	// [end]

	// [start] long型转换为Strint型
	/**
	 * long型转换为Strint型
	 *
	 * @param strValue
	 *            要转换的字符串
	 * @param defValue
	 *            缺省值 转换后的int类型结果
	 */
	public static String objToStr(long strValue) {
		return objToStr(strValue, "");
	}

	// [end]

	// [start] long型转换为Strint型
	/**
	 * long型转换为Strint型
	 *
	 * @param strValue
	 *            要转换的字符串
	 * @param defValue
	 *            缺省值 转换后的int类型结果
	 */
	public static String objToStr(long strValue, String defValue) {
		if (strValue == 0)
			return defValue;
		return strValue + "";
	}

	// [end]

	// [start] float型转换为Strint型
	/**
	 * float型转换为Strint型
	 *
	 * @param strValue
	 *            要转换的字符串
	 * @param defValue
	 *            缺省值 转换后的int类型结果
	 */
	public static String objToStr(float strValue) {
		return objToStr(strValue, "");
	}

	// [end]

	// [start] float型转换为Strint型
	/**
	 * float型转换为Strint型
	 *
	 * @param strValue
	 *            要转换的字符串
	 * @param defValue
	 *            缺省值 转换后的int类型结果
	 */
	public static String objToStr(float strValue, String defValue) {
		return objToStr(strValue, defValue, "###,###.00");// 使用系统默认的格式
	}

	// [end]

	// [start] float型转换为Strint型
	/**
	 * float型转换为Strint型
	 *
	 * @param strValue
	 *            要转换的字符串
	 * @param defValue
	 *            缺省值 转换后的int类型结果
	 */
	public static String objToStr(float strValue, String defValue, String format) {
		if (strValue == 0)
			return defValue;
		java.text.DecimalFormat nf = new java.text.DecimalFormat(format);
		nf.setGroupingUsed(false);
		return nf.format(strValue);
	}

	// [end]

	// [start] double型转换为Strint型
	/**
	 * double型转换为Strint型
	 *
	 * @param strValue
	 *            要转换的字符串
	 * @param defValue
	 *            缺省值 转换后的int类型结果
	 */
	public static String objToStr(double strValue) {
		return objToStr(strValue, "");
	}

	// [end]

	// [start] double型转换为Strint型
	/**
	 * double型转换为Strint型
	 *
	 * @param strValue
	 *            要转换的字符串
	 * @param defValue
	 *            缺省值 转换后的int类型结果
	 */
	public static String objToStr(double strValue, String defValue) {
		return objToStr(strValue, defValue, "###,###.00");// 使用系统默认的格式
	}

	// [end]

	// [start] double型转换为Strint型
	/**
	 * double型转换为Strint型
	 *
	 * @param strValue
	 *            要转换的字符串
	 * @param defValue
	 *            缺省值 转换后的int类型结果
	 */
	public static String objToStr(double strValue, String defValue, String format) {
		if (strValue == 0)
			return defValue;
		java.text.DecimalFormat nf = new java.text.DecimalFormat(format);
		nf.setGroupingUsed(false);
		return nf.format(strValue);
	}

	// [end]

	// [start] String型转换为Date型
	// String型转换为Date型
	/**
	 * String型转换为Date型
	 *
	 * @param strValue
	 *            要转换的字符串
	 * @param defValue
	 *            缺省值 转换后的Date类型结果
	 */
	public static Date StrToDate(Object strValue, Date defValue) {
		if (strValue == null || strValue.toString().length() == 0)
			return defValue;

		String val = strValue.toString().trim();

		if (!Validate.IsDateTimeString(val))
			return defValue;

		try {
			// 1980-1-1
			// 1980-01-1
			// 1980-1-01
			int year1 = val.indexOf("-") + 1;
			int year2 = val.lastIndexOf("-");
			int year3 = val.indexOf(" ");
			String strFromat = "";
			// int time1 = val.indexOf(":") + 1;
			// int time2 = val.lastIndexOf(":");
			// int time3 = val.length();

			SimpleDateFormat sdf;
			if (val.indexOf(" ") > 0) {
				if (year2 - year1 == 2)
					strFromat = "yyyy-MM";
				else
					strFromat = "yyyy-M";

				if (year3 - year2 - 1 == 2)
					strFromat += "-dd";
				else
					strFromat += "-d";

				if (val.indexOf(":") == val.lastIndexOf(":"))
					// if (time2 - time1 == 2)
					// strFromat += "HH:mm";
					// else
					// strFromat += "HH:m";
					sdf = new SimpleDateFormat(strFromat + "HH:mm");
				else
					// if (time2 - time1 == 2)
					// strFromat += "HH:mm";
					// else
					// strFromat += "HH:m";
					//
					// if (time3 - time2 - 1 == 2)
					// strFromat += ":ss";
					// else
					// strFromat += ":s";
					sdf = new SimpleDateFormat(strFromat + "HH:mm:ss");
			} else {
				year3 = val.length();

				if (year2 - year1 == 2)
					strFromat = "yyyy-MM";
				else
					strFromat = "yyyy-M";

				if (year3 - year2 - 1 == 2)
					strFromat += "-dd";
				else
					strFromat += "-d";

				sdf = new SimpleDateFormat(strFromat);
			}
			Date d = sdf.parse(val);
			return d;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return defValue;
		}

	}

	// [end]

	// [start] String型转换为Date型
	// String型转换为Date型
	/**
	 * String型转换为Date型，转换不成功返回当前世界
	 *
	 * @param strValue
	 *            要转换的字符串
	 * @param defValue
	 *            缺省值 转换后的Date类型结果
	 */
	public static Date StrToDate(Object strValue) {
		return StrToDate(strValue, new Date());
	}

	// [end]

	// [start] String型转换为byte型
	// String型转换为byte型
	/**
	 * String型转换为byte型
	 *
	 * @param strValue
	 *            要转换的字符串
	 * @param defValue
	 *            缺省值 转换后的int类型结果
	 */
	public static byte StrToByte(Object strValue, byte defValue) {
		if (strValue == null || strValue.toString().length() == 0 || strValue.toString().length() > 10)
			return defValue;

		String val = strValue.toString();
		String firstletter = val.substring(0, 1);

		if (val.length() == 10 && Validate.IsNumber(firstletter) && Integer.parseInt(firstletter) > 1)
			return defValue;
		else if (val.length() == 10 && !Validate.IsNumber(firstletter))
			return defValue;

		byte intValue = defValue;
		if (strValue != null) {
			boolean IsInt = Validate.IsNumber(strValue.toString());
			if (IsInt)
				intValue = Byte.parseByte(strValue.toString());
		}

		return intValue;
	}

	// [end]

	// [start] String型转换为byte型
	// String型转换为byte型
	/**
	 * String型转换为byte型，转换不成功返回0
	 *
	 * @param strValue
	 *            要转换的字符串
	 * @param defValue
	 *            缺省值 转换后的int类型结果
	 */
	public static byte StrToByte(Object strValue) {
		return StrToByte(strValue, (byte) 0);
	}

	// [end]

	// [start] String型转换为byte型
	// String型转换为byte型
	/**
	 * String型转换为byte[]型
	 *
	 * @param strValue
	 *            要转换的字符串
	 * @param defValue
	 *            缺省值 转换后的int类型结果
	 */
	public static byte[] StrToByteS(Object strValue, byte[] defValue) {
		if (strValue == null)
			return defValue;
		try {
			return (byte[]) strValue;
		} catch (Exception e) {
			// e.printStackTrace();
			return null;
		}
	}

	// [end]

	// [start] String型转换为byte型
	// String型转换为byte型
	/**
	 * String型转换为byte[]型，转换不成功返回0
	 *
	 * @param strValue
	 *            要转换的字符串
	 * @param defValue
	 *            缺省值 转换后的int类型结果
	 */
	public static byte[] StrToByteS(Object strValue) {
		return StrToByteS(strValue, null);
	}

	// [end]

	// [start] String型转换为int型
	// String型转换为int型
	/**
	 * String型转换为int型
	 *
	 * @param strValue
	 *            要转换的字符串
	 * @param defValue
	 *            缺省值 转换后的int类型结果
	 */
	public static int StrToInt(Object strValue, int defValue) {
		if (strValue == null || strValue.toString().length() == 0 || strValue.toString().length() > 10)
			return defValue;

		String val = strValue.toString();
		String firstletter = val.substring(0, 1);

		if (val.length() == 10 && Validate.IsNumber(firstletter) && Integer.parseInt(firstletter) > 1)
			return defValue;
		else if (val.length() == 10 && !Validate.IsNumber(firstletter))
			return defValue;

		int intValue = defValue;
		if (strValue != null) {
			String strInt = strValue.toString();
			if (strInt.contains("."))
				strInt = strInt.substring(0, strInt.indexOf("."));

			boolean IsInt = Validate.IsNumber(strInt);
			if (IsInt)
				intValue = Integer.parseInt(strInt);
		}

		return intValue;
	}

	// [end]

	// [start] String型转换为int型
	// String型转换为int型
	/**
	 * String型转换为int型，转换不成功返回0
	 *
	 * @param strValue
	 *            要转换的字符串
	 * @param defValue
	 *            缺省值 转换后的int类型结果
	 */
	public static int StrToInt(Object strValue) {
		if (strValue == null)
			return 0;
		return StrToInt(strValue.toString(), 0);
	}

	// [end]

	// [start] String型转换为Short型
	// String型转换为Short型
	/**
	 * String型转换为int型
	 *
	 * @param strValue
	 *            要转换的字符串
	 * @param defValue
	 *            缺省值 转换后的int类型结果
	 */
	public static short StrToShort(Object strValue, short defValue) {
		if (strValue == null || strValue.toString().length() == 0 || strValue.toString().length() > 10)
			return defValue;

		String val = strValue.toString();
		String firstletter = val.substring(0, 1);

		if (val.length() == 10 && Validate.IsNumber(firstletter) && Short.parseShort(firstletter) > 1)
			return defValue;
		else if (val.length() == 10 && !Validate.IsNumber(firstletter))
			return defValue;

		short intValue = defValue;
		if (strValue != null) {
			String strInt = strValue.toString();
			if (strInt.contains("."))
				strInt = strInt.substring(0, strInt.indexOf("."));

			boolean IsInt = Validate.IsNumber(strInt);
			if (IsInt)
				intValue = Short.parseShort(strInt);
		}

		return intValue;
	}

	// [end]

	// [start] String型转换为Short型
	// String型转换为Short型
	/**
	 * String型转换为float型，转换不成功返回0
	 *
	 * @param strValue
	 *            要转换的字符串
	 * @param defValue
	 *            缺省值 转换后的int类型结果
	 */
	public static short StrToShort(Object strValue) {
		return StrToShort(strValue, (short) 0);
	}

	// [end]

	// [start] String型转换为Long型
	// String型转换为Long型
	/**
	 * String型转换为int型
	 *
	 * @param strValue
	 *            要转换的字符串
	 * @param defValue
	 *            缺省值 转换后的int类型结果
	 */
	public static long StrToLong(Object strValue, long defValue) {
		if (strValue == null || strValue.toString().length() == 0 || strValue.toString().length() > 10)
			return defValue;

		String val = strValue.toString();
		String firstletter = val.substring(0, 1);

		if (val.length() == 10 && Validate.IsNumber(firstletter) && Long.parseLong(firstletter) > 1)
			return defValue;
		else if (val.length() == 10 && !Validate.IsNumber(firstletter))
			return defValue;

		long intValue = defValue;
		if (strValue != null) {
			String strInt = strValue.toString();
			if (strInt.contains("."))
				strInt = strInt.substring(0, strInt.indexOf("."));
			boolean IsInt = Validate.IsNumber(strInt);
			if (IsInt)
				intValue = Long.parseLong(strInt);
		}

		return intValue;
	}

	// [end]

	// [start] String型转换为Long型
	// String型转换为Long型
	/**
	 * String型转换为float型，转换不成功返回0
	 *
	 * @param strValue
	 *            要转换的字符串
	 * @param defValue
	 *            缺省值 转换后的int类型结果
	 */
	public static long StrToLong(Object strValue) {
		if (strValue == null)
			return 0;
		return StrToLong(strValue.toString(), 0);
	}

	// [end]

	// [start] String型转换为float型
	// String型转换为float型
	/**
	 * String型转换为float型
	 *
	 * @param strValue
	 *            要转换的字符串
	 * @param defValue
	 *            缺省值 转换后的int类型结果
	 */
	public static float StrToFloat(Object strValue, float defValue) {
		if (strValue == null || strValue.toString().length() > 10)
			return defValue;

		float intValue = defValue;
		if (strValue != null) {
			boolean IsFloat = Validate.IsNumber(strValue.toString());
			if (IsFloat)
				intValue = Float.parseFloat(strValue.toString());
		}
		return intValue;
	}

	// [end]

	// [start] String型转换为float型，转换不成功返回0
	// String型转换为float型，转换不成功返回0
	/**
	 * String型转换为float型
	 *
	 * @param strValue
	 *            要转换的字符串
	 * @param defValue
	 *            缺省值 转换后的int类型结果
	 */
	public static float StrToFloat(Object strValue) {
		if (strValue == null)
			return 0;
		return StrToFloat(strValue.toString(), 0);
	}

	// [end]

	// [start] String型转换为Double型
	// String型转换为Double型
	/**
	 * String型转换为Double型
	 *
	 * @param strValue
	 *            要转换的字符串
	 * @param defValue
	 *            缺省值 转换后的int类型结果
	 */
	public static Double StrToDouble(Object strValue, Double defValue) {
		if (strValue == null || strValue.toString().length() > 20)
			return defValue;

		Double intValue = defValue;
		if (strValue != null) {
			boolean IsDouble = Validate.IsNumber(strValue.toString());
			if (IsDouble)
				intValue = Double.parseDouble(strValue.toString());
		}
		return intValue;
	}

	// [end]

	// [start] String型转换为Double型，转换不成功返回0
	// String型转换为Double型，转换不成功返回0
	/**
	 * String型转换为Double型
	 *
	 * @param strValue
	 *            要转换的字符串
	 * @param defValue
	 *            缺省值 转换后的int类型结果
	 */
	public static Double StrToDouble(Object strValue) {
		return StrToDouble(strValue, 0D);
	}

	// [end]

	// [start] String型转换为Boolean型
	// String型转换为Boolean型
	/**
	 * String型转换为Boolean型,0或false=false,1或true=true
	 *
	 * @param strValue
	 *            要转换的字符串
	 * @param defValue
	 *            缺省值 转换后的int类型结果
	 */
	public static boolean StrToBoolean(Object strValue, boolean defValue) {
		if (strValue == null || strValue.toString().length() > 5)
			return defValue;

		boolean intValue = defValue;
		if (strValue != null) {
			String val = strValue.toString().toLowerCase();
			int ival = StrToInt(strValue);
			if (val.endsWith("false") || ival == 0)
				intValue = false;

			if (val.endsWith("true") || ival > 0)
				intValue = true;

		}
		return intValue;
	}

	// [end]

	// [start] String型转换为Boolean型，转换不成功返回false
	// String型转换为Boolean型，转换不成功返回false
	/**
	 * String型转换为Boolean型,0或false=false,1或true=true
	 *
	 * @param strValue
	 *            要转换的字符串
	 * @param defValue
	 *            缺省值 转换后的int类型结果
	 */
	public static boolean StrToBoolean(Object strValue) {
		return StrToBoolean(strValue, false);
	}

	// [end]

	// [start] int型转换为String型
	// int型转换为String型
	/**
	 * int型转换为String型
	 *
	 * 转换后的String类型结果
	 */
	public static String IntToStr(int intValue) {
		//
		return Integer.toString(intValue);
	}

	// [end]

	// [start] Date型转换为String型
	// Date型转换为String型
	/**
	 * Date型转换为String型
	 *
	 * <pre>
	 * 	日期和时间模式
	 * 	日期和时间格式由日期和时间模式 字符串指定。在日期和时间模式字符串中，未加引号的字母 'A' 到 'Z' 和 'a' 到 'z' 被解释为模式字母，用来表示日期或时间字符串元素。文本可以使用单引号 (') 引起来，以免进行解释。&quot;''&quot; 表示单引号。所有其他字符均不解释；只是在格式化时将它们简单复制到输出字符串，或者在解析时与输入字符串进行匹配。
	 *
	 * 	定义了以下模式字母（所有其他字符 'A' 到 'Z' 和 'a' 到 'z' 都被保留）：
	 *
	 * 	字母  日期或时间元素  表示  示例
	 * 	G  Era 标志符  Text  AD
	 * 	y  年  Year  1996; 96
	 * 	M  年中的月份  Month  July; Jul; 07
	 * 	w  年中的周数  Number  27
	 * 	W  月份中的周数  Number  2
	 * 	D  年中的天数  Number  189
	 * 	d  月份中的天数  Number  10
	 * 	F  月份中的星期  Number  2
	 * 	E  星期中的天数  Text  Tuesday; Tue
	 * 	a  Am/pm 标记  Text  PM
	 * 	H  一天中的小时数（0-23）  Number  0
	 * 	k  一天中的小时数（1-24）  Number  24
	 * 	K  am/pm 中的小时数（0-11）  Number  0
	 * 	h  am/pm 中的小时数（1-12）  Number  12
	 * 	m  小时中的分钟数  Number  30
	 * 	s  分钟中的秒数  Number  55
	 * 	S  毫秒数  Number  978
	 * 	z  时区  General time zone  Pacific Standard Time; PST; GMT-08:00
	 * 	Z  时区  RFC 822 time zone  -0800
	 *
	 * 	模式字母通常是重复的，其数量确定其精确表示：
	 * 	Text: 对于格式化来说，如果模式字母的数量大于等于 4，则使用完全形式；否则，在可用的情况下使用短形式或缩写形式。对于解析来说，两种形式都是可接受的，与模式字母的数量无关。
	 * 	Number: 对于格式化来说，模式字母的数量是最小的数位，如果数位不够，则用 0 填充以达到此数量。对于解析来说，模式字母的数量被忽略，除非必须分开两个相邻字段。
	 * 	Year: 如果格式器的 Calendar 是格里高利历，则应用以下规则。
	 *
	 * 	对于格式化来说，如果模式字母的数量为 2，则年份截取为 2 位数,否则将年份解释为 number。
	 * 	对于解析来说，如果模式字母的数量大于 2，则年份照字面意义进行解释，而不管数位是多少。因此使用模式 &quot;MM/dd/yyyy&quot;，将 &quot;01/11/12&quot; 解析为公元 12 年 1 月 11 日。
	 * 	在解析缩写年份模式（&quot;y&quot; 或 &quot;yy&quot;）时，SimpleDateFormat 必须相对于某个世纪来解释缩写的年份。这通过将日期调整为 SimpleDateFormat 实例创建之前的 80 年和之后 20 年范围内来完成。例如，在 &quot;MM/dd/yy&quot; 模式下，如果 SimpleDateFormat 实例是在 1997 年 1 月 1 日创建的，则字符串 &quot;01/11/12&quot; 将被解释为 2012 年 1 月 11 日，而字符串 &quot;05/04/64&quot; 将被解释为 1964 年 5 月 4 日。在解析时，只有恰好由两位数字组成的字符串（如 Character.isDigit(char) 所定义的）被解析为默认的世纪。其他任何数字字符串将照字面意义进行解释，例如单数字字符串，3 个或更多数字组成的字符串，或者不都是数字的两位数字字符串（例如&quot;-1&quot;）。因此，在相同的模式下， &quot;01/02/3&quot; 或 &quot;01/02/003&quot; 解释为公元 3 年 1 月 2 日。同样，&quot;01/02/-3&quot; 解析为公元前 4 年 1 月 2 日。
	 * 	否则，则应用日历系统特定的形式。对于格式化和解析，如果模式字母的数量为 4 或大于 4，则使用日历特定的 long form。否则，则使用日历特定的 short or abbreviated form。
	 * 	Month: 如果模式字母的数量为 3 或大于 3，则将月份解释为 text；否则解释为 number。
	 * 	General time zone: 如果时区有名称，则将它们解释为 text。对于表示 GMT 偏移值的时区，使用以下语法：
	 * 	 GMTOffsetTimeZone:
	 * 	         GMT Sign Hours : Minutes
	 * 	 Sign: one of
	 * 	         + -
	 * 	 Hours:
	 * 	         Digit
	 * 	         Digit Digit
	 * 	 Minutes:
	 * 	         Digit Digit
	 * 	 Digit: one of
	 * 	         0 1 2 3 4 5 6 7 8 9
	 * 	Hours 必须在 0 到 23 之间，Minutes 必须在 00 到 59 之间。格式是与语言环境无关的，并且数字必须取自 Unicode 标准的 Basic Latin 块。
	 * 	对于解析来说，RFC 822 time zones 也是可接受的。
	 *
	 * 	RFC 822 time zone: 对于格式化来说，使用 RFC 822 4-digit 时区格式：
	 * 	 RFC822TimeZone:
	 * 	         Sign TwoDigitHours Minutes
	 * 	 TwoDigitHours:
	 * 	         Digit Digit
	 * 	TwoDigitHours 必须在 00 和 23 之间。其他定义请参阅 general time zones。
	 * 	对于解析来说，general time zones 也是可接受的。
	 *
	 * 	SimpleDateFormat 还支持本地化日期和时间模式 字符串。在这些字符串中，以上所述的模式字母可以用其他与语言环境有关的模式字母来替换。SimpleDateFormat 不处理除模式字母之外的文本本地化；而由类的客户端来处理。
	 *
	 * 	示例
	 * 	以下示例显示了如何在美国语言环境中解释日期和时间模式。给定的日期和时间为美国太平洋时区的本地时间 2001-07-04 12:08:56。
	 * 	日期和时间模式  结果
	 * 	&quot;yyyy.MM.dd G 'at' HH:mm:ss z&quot;  2001.07.04 AD at 12:08:56 PDT
	 * 	&quot;EEE, MMM d, ''yy&quot;  Wed, Jul 4, '01
	 * 	&quot;h:mm a&quot;  12:08 PM
	 * 	&quot;hh 'o''clock' a, zzzz&quot;  12 o'clock PM, Pacific Daylight Time
	 * 	&quot;K:mm a, z&quot;  0:08 PM, PDT
	 * 	&quot;yyyyy.MMMMM.dd GGG hh:mm aaa&quot;  02001.July.04 AD 12:08 PM
	 * 	&quot;EEE, d MMM yyyy HH:mm:ss Z&quot;  Wed, 4 Jul 2001 12:08:56 -0700
	 * 	&quot;yyMMddHHmmssZ&quot;  010704120856-0700
	 * 	&quot;yyyy-MM-dd'T'HH:mm:ss.SSSZ&quot;  2001-07-04T12:08:56.235-0700
	 *
	 * 	同步
	 * 	日期格式是不同步的。建议为每个线程创建独立的格式实例。如果多个线程同时访问一个格式，则它必须是外部同步的。
	 *
	 *
	 * </pre>
	 *
	 * @param strValue
	 *            要转换的日期
	 * @param simpleDateFormat
	 *            格式的样式，默认"yyyy-MM-dd"
	 */
	public static String DateToString(Date dateValue) {
		return DateToString(dateValue, "yyyy-MM-dd");
	}

	// [end]

	// [start] Date型转换为String型
	// Date型转换为String型
	/**
	 * Date型转换为String型
	 *
	 * <pre>
	 * 	日期和时间模式
	 * 	日期和时间格式由日期和时间模式 字符串指定。在日期和时间模式字符串中，未加引号的字母 'A' 到 'Z' 和 'a' 到 'z' 被解释为模式字母，用来表示日期或时间字符串元素。文本可以使用单引号 (') 引起来，以免进行解释。&quot;''&quot; 表示单引号。所有其他字符均不解释；只是在格式化时将它们简单复制到输出字符串，或者在解析时与输入字符串进行匹配。
	 *
	 * 	定义了以下模式字母（所有其他字符 'A' 到 'Z' 和 'a' 到 'z' 都被保留）：
	 *
	 * 	字母  日期或时间元素  表示  示例
	 * 	G  Era 标志符  Text  AD
	 * 	y  年  Year  1996; 96
	 * 	M  年中的月份  Month  July; Jul; 07
	 * 	w  年中的周数  Number  27
	 * 	W  月份中的周数  Number  2
	 * 	D  年中的天数  Number  189
	 * 	d  月份中的天数  Number  10
	 * 	F  月份中的星期  Number  2
	 * 	E  星期中的天数  Text  Tuesday; Tue
	 * 	a  Am/pm 标记  Text  PM
	 * 	H  一天中的小时数（0-23）  Number  0
	 * 	k  一天中的小时数（1-24）  Number  24
	 * 	K  am/pm 中的小时数（0-11）  Number  0
	 * 	h  am/pm 中的小时数（1-12）  Number  12
	 * 	m  小时中的分钟数  Number  30
	 * 	s  分钟中的秒数  Number  55
	 * 	S  毫秒数  Number  978
	 * 	z  时区  General time zone  Pacific Standard Time; PST; GMT-08:00
	 * 	Z  时区  RFC 822 time zone  -0800
	 *
	 * 	模式字母通常是重复的，其数量确定其精确表示：
	 * 	Text: 对于格式化来说，如果模式字母的数量大于等于 4，则使用完全形式；否则，在可用的情况下使用短形式或缩写形式。对于解析来说，两种形式都是可接受的，与模式字母的数量无关。
	 * 	Number: 对于格式化来说，模式字母的数量是最小的数位，如果数位不够，则用 0 填充以达到此数量。对于解析来说，模式字母的数量被忽略，除非必须分开两个相邻字段。
	 * 	Year: 如果格式器的 Calendar 是格里高利历，则应用以下规则。
	 *
	 * 	对于格式化来说，如果模式字母的数量为 2，则年份截取为 2 位数,否则将年份解释为 number。
	 * 	对于解析来说，如果模式字母的数量大于 2，则年份照字面意义进行解释，而不管数位是多少。因此使用模式 &quot;MM/dd/yyyy&quot;，将 &quot;01/11/12&quot; 解析为公元 12 年 1 月 11 日。
	 * 	在解析缩写年份模式（&quot;y&quot; 或 &quot;yy&quot;）时，SimpleDateFormat 必须相对于某个世纪来解释缩写的年份。这通过将日期调整为 SimpleDateFormat 实例创建之前的 80 年和之后 20 年范围内来完成。例如，在 &quot;MM/dd/yy&quot; 模式下，如果 SimpleDateFormat 实例是在 1997 年 1 月 1 日创建的，则字符串 &quot;01/11/12&quot; 将被解释为 2012 年 1 月 11 日，而字符串 &quot;05/04/64&quot; 将被解释为 1964 年 5 月 4 日。在解析时，只有恰好由两位数字组成的字符串（如 Character.isDigit(char) 所定义的）被解析为默认的世纪。其他任何数字字符串将照字面意义进行解释，例如单数字字符串，3 个或更多数字组成的字符串，或者不都是数字的两位数字字符串（例如&quot;-1&quot;）。因此，在相同的模式下， &quot;01/02/3&quot; 或 &quot;01/02/003&quot; 解释为公元 3 年 1 月 2 日。同样，&quot;01/02/-3&quot; 解析为公元前 4 年 1 月 2 日。
	 * 	否则，则应用日历系统特定的形式。对于格式化和解析，如果模式字母的数量为 4 或大于 4，则使用日历特定的 long form。否则，则使用日历特定的 short or abbreviated form。
	 * 	Month: 如果模式字母的数量为 3 或大于 3，则将月份解释为 text；否则解释为 number。
	 * 	General time zone: 如果时区有名称，则将它们解释为 text。对于表示 GMT 偏移值的时区，使用以下语法：
	 * 	 GMTOffsetTimeZone:
	 * 	         GMT Sign Hours : Minutes
	 * 	 Sign: one of
	 * 	         + -
	 * 	 Hours:
	 * 	         Digit
	 * 	         Digit Digit
	 * 	 Minutes:
	 * 	         Digit Digit
	 * 	 Digit: one of
	 * 	         0 1 2 3 4 5 6 7 8 9
	 * 	Hours 必须在 0 到 23 之间，Minutes 必须在 00 到 59 之间。格式是与语言环境无关的，并且数字必须取自 Unicode 标准的 Basic Latin 块。
	 * 	对于解析来说，RFC 822 time zones 也是可接受的。
	 *
	 * 	RFC 822 time zone: 对于格式化来说，使用 RFC 822 4-digit 时区格式：
	 * 	 RFC822TimeZone:
	 * 	         Sign TwoDigitHours Minutes
	 * 	 TwoDigitHours:
	 * 	         Digit Digit
	 * 	TwoDigitHours 必须在 00 和 23 之间。其他定义请参阅 general time zones。
	 * 	对于解析来说，general time zones 也是可接受的。
	 *
	 * 	SimpleDateFormat 还支持本地化日期和时间模式 字符串。在这些字符串中，以上所述的模式字母可以用其他与语言环境有关的模式字母来替换。SimpleDateFormat 不处理除模式字母之外的文本本地化；而由类的客户端来处理。
	 *
	 * 	示例
	 * 	以下示例显示了如何在美国语言环境中解释日期和时间模式。给定的日期和时间为美国太平洋时区的本地时间 2001-07-04 12:08:56。
	 * 	日期和时间模式  结果
	 * 	&quot;yyyy.MM.dd G 'at' HH:mm:ss z&quot;  2001.07.04 AD at 12:08:56 PDT
	 * 	&quot;EEE, MMM d, ''yy&quot;  Wed, Jul 4, '01
	 * 	&quot;h:mm a&quot;  12:08 PM
	 * 	&quot;hh 'o''clock' a, zzzz&quot;  12 o'clock PM, Pacific Daylight Time
	 * 	&quot;K:mm a, z&quot;  0:08 PM, PDT
	 * 	&quot;yyyyy.MMMMM.dd GGG hh:mm aaa&quot;  02001.July.04 AD 12:08 PM
	 * 	&quot;EEE, d MMM yyyy HH:mm:ss Z&quot;  Wed, 4 Jul 2001 12:08:56 -0700
	 * 	&quot;yyMMddHHmmssZ&quot;  010704120856-0700
	 * 	&quot;yyyy-MM-dd'T'HH:mm:ss.SSSZ&quot;  2001-07-04T12:08:56.235-0700
	 *
	 * 	同步
	 * 	日期格式是不同步的。建议为每个线程创建独立的格式实例。如果多个线程同时访问一个格式，则它必须是外部同步的。
	 *
	 *
	 * </pre>
	 *
	 * @param strValue
	 *            要转换的日期
	 * @param simpleDateFormat
	 *            格式的样式，默认"yyyy-MM-dd"
	 */
	public static String DateToString(Date dateValue, String simpleDateFormat) {
		if (dateValue == null)
			return "";
		java.text.SimpleDateFormat formatter = new java.text.SimpleDateFormat(simpleDateFormat);
		return formatter.format(dateValue);
	}

	// [end]

	// [start] String型转换为Date型
	// String型转换为Date型
	/**
	 * String型转换为Date型，转换不成功返回当前世界
	 *
	 * @param strValue
	 *            要转换的字符串
	 * @param defValue
	 *            缺省值 转换后的Date类型结果
	 */
	public static Date TimestampToDate(java.sql.Timestamp strValue) {
		// java.util.Date dst;
		// java.util.TimeZone tst;

		// java.sql.
		if (strValue == null)
			return null;
		String Values = DateToString(strValue, "yyyy-MM-dd HH:mm:ss");
		try {
			Date dt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(Values);
			// dt.
			return dt;
		} catch (ParseException e) {
		}
		return null;
	}

	// [end]

	// [start] String型转换为BigInteger型
	// String型转换为BigInteger型
	/**
	 * String型转换为BigInteger型
	 *
	 * @param strValue
	 *            要转换的字符串
	 * @param defValue
	 *            缺省值 转换后的BigInteger类型结果
	 */
	public static java.math.BigInteger StrToBigInteger(Object strValue, java.math.BigInteger defValue) {
		if (strValue == null)
			return defValue;
		java.math.BigInteger intValue = java.math.BigInteger.valueOf(0);
		if (strValue != null) {
			boolean IsDouble = Validate.IsNumber(strValue.toString().trim());
			if (IsDouble)
				intValue = new java.math.BigInteger(strValue.toString().trim());
		}
		return intValue;
	}

	// [end]

	// [start] String型转换为BigInteger型，转换不成功返回0
	// String型转换为BigInteger型，转换不成功返回0
	/**
	 * String型转换为BigInteger型
	 *
	 * @param strValue
	 *            要转换的字符串
	 * @param defValue
	 *            缺省值 转换后的BigInteger类型结果
	 */
	public static java.math.BigInteger StrToBigInteger(Object strValue) {
		return StrToBigInteger(strValue, java.math.BigInteger.valueOf(0));
	}

	// [end]

	// [start] String型转换为BigDecimal型
	// String型转换为BigDecimal型
	/**
	 * String型转换为BigDecimal型
	 *
	 * @param strValue
	 *            要转换的字符串
	 * @param defValue
	 *            缺省值 转换后的BigDecimal类型结果
	 */
	public static java.math.BigDecimal StrToBigDecimal(Object strValue, java.math.BigDecimal defValue) {
		if (strValue == null)
			return defValue;
		java.math.BigDecimal intValue = java.math.BigDecimal.valueOf(0);
		if (strValue != null) {
			boolean IsDouble = Validate.IsNumber(strValue.toString().trim());
			if (IsDouble)
				intValue = new java.math.BigDecimal(strValue.toString().trim());
		}
		return intValue;

	}

	// [end]

	// [start] String型转换为BigDecimal型，转换不成功返回0
	// String型转换为BigDecimal型，转换不成功返回0
	/**
	 * String型转换为BigDecimal型
	 *
	 * @param strValue
	 *            要转换的字符串
	 * @param defValue
	 *            缺省值 转换后的BigDecimal类型结果
	 */
	public static java.math.BigDecimal StrToBigDecimal(Object strValue) {
		return StrToBigDecimal(strValue, java.math.BigDecimal.valueOf(0));
	}

	// [end]

	// [start] BigInteger型转换为Strint型
	/**
	 * BigInteger型转换为Strint型
	 *
	 * @param strValue
	 *            要转换的字符串
	 * @param defValue
	 *            缺省值 转换后的BigInteger类型结果
	 */
	public static String BigIntegerToStr(BigInteger strValue) {
		return BigIntegerToStr(strValue, "");
	}

	// [end]
	// [start] BigInteger型转换为Strint型
	/**
	 * BigInteger型转换为Strint型
	 *
	 * @param strValue
	 *            要转换的字符串
	 * @param defValue
	 *            缺省值 转换后的BigInteger类型结果
	 */
	public static String BigIntegerToStr(BigInteger strValue, String decimalFormat) {
		return BigIntegerToStr(strValue, "", "0");
	}

	// [end]

	// [start] BigInteger型转换为Strint型
	/**
	 * BigInteger型转换为Strint型
	 *
	 * @param strValue
	 *            要转换的字符串
	 * @param defValue
	 *            缺省值 转换后的BigInteger类型结果
	 */
	public static String BigIntegerToStr(BigInteger strValue, String decimalFormat, String defValue) {
		if (strValue.equals(0))
			return defValue;
		else if (decimalFormat.equals(""))
			return strValue.toString();
		else {
			java.text.DecimalFormat df = new java.text.DecimalFormat(decimalFormat);
			return df.format(strValue);
		}
	}

	// [end]

	// [start] BigDecimal型转换为Strint型
	/**
	 * BigDecimal型转换为Strint型
	 *
	 * @param strValue
	 *            要转换的字符串
	 * @param defValue
	 *            缺省值 转换后的BigDecimal类型结果
	 */
	public static String BigDecimalToStr(BigDecimal strValue) {
		return BigDecimalToStr(strValue, "");
	}

	// [end]
	// [start] BigDecimal型转换为Strint型
	/**
	 * BigDecimal型转换为Strint型
	 *
	 * @param strValue
	 *            要转换的字符串
	 * @param defValue
	 *            缺省值 转换后的BigDecimal类型结果
	 */
	public static String BigDecimalToStr(BigDecimal strValue, String decimalFormat) {
		return BigDecimalToStr(strValue, "", "0");
	}

	// [end]

	// [start] BigDecimal型转换为Strint型
	/**
	 * BigDecimal型转换为Strint型
	 *
	 * @param strValue
	 *            要转换的字符串
	 * @param defValue
	 *            缺省值 转换后的BigDecimal类型结果
	 */
	public static String BigDecimalToStr(BigDecimal strValue, String decimalFormat, String defValue) {
		if (strValue.equals(0))
			return defValue;
		else if (decimalFormat.equals(""))
			return strValue.toString();
		else {
			java.text.DecimalFormat df = new java.text.DecimalFormat(decimalFormat);
			return df.format(strValue);
		}
	}

	// [end]

	// [start] BigDecimal型转换为int型
	/**
	 *
	 * @param decimal
	 * @param defVal
	 *            为null时默认值
	 * @return
	 */
	public static int BigDecimalToInt(BigDecimal decimal, int defVal) {
		if (decimal == null)
			return defVal;
		else
			return decimal.intValue();
	}

	// [end]

	// [start] BigDecimal型转换为double型
	/**
	 *
	 * @param decimal
	 * @param defVal
	 *            为null时默认值
	 * @return
	 */
	public static double BigDecimalToDouble(BigDecimal decimal, double defVal) {
		if (decimal == null)
			return defVal;
		else
			return decimal.doubleValue();
	}

	// [end]

	/**
	 * String型转换为Long[]型
	 *
	 * @param strValue
	 *            要转换的字符串
	 */
	public static Long[] StrToLongs(String strValue) {
		if (StringUtils.isNotBlank(strValue)) {
			String[] strIds = strValue.toString().split(",");
			Long[] ids = new Long[strIds.length];
			for (int i = 0; i < strIds.length; i++)
				ids[i] = StrToLong(strIds[i]);
			return ids;
		}
		return null;
	}

	// [end]

	/**
	 * String型转换为Double[]型
	 *
	 * @param strValue
	 *            要转换的字符串
	 */
	public static Double[] StrToDoubles(String strValue) {
		Double[] ids = null;
		if (StringUtils.isNotBlank(strValue)) {
			String[] strIds = strValue.toString().split(",");
			ids = new java.lang.Double[strIds.length];
			for (int i = 0; i < strIds.length; i++)
				ids[i] = StrToDouble(strIds[i]);
		}
		return ids;
	}

	/**
	 * 将十进制数转换为01编码，然后在转换成十进制（即为序号、权值操作）
	 *
	 * @param params
	 *            十进制数
	 * @return 转换后的数字 liqingbo
	 */
	public static int dataToBinary(int params) {
		int pos = params - 1;
		StringBuilder str = new StringBuilder();
		for (int i = 0; i < pos; i++)
			str.append(0);
		str.append(1);
		long v = Long.parseLong(str.reverse().toString(), 2);
		System.err.println(v);
		return (int) v;

	}

	// [end]

	public static Long[] StrArrayToLongArray(String[] strArray) {
		if (strArray == null)
			return null;
		int len = strArray.length;
		Long[] result = new Long[len];
		for (int i = 0; i < len; i++)
			result[i] = Long.parseLong(strArray[i]);
		return result;
	}

	public static Float[] strArrayToFloatArray(String[] strArray) {
		if (strArray == null)
			return null;
		int len = strArray.length;
		Float[] result = new Float[len];
		for (int i = 0; i < len; i++)
			result[i] = Float.parseFloat(strArray[i]);
		return result;
	}

	/**
	 * 根据商品重量与数量的比例，获得购买的重量<br/>
	 * 比例为：原有重量/原有数量 = 改变的重量/改变的数量
	 *
	 * @param numer
	 *            商品现有数量
	 * @param weight1
	 *            商品现有重量
	 * @param buySaleNumber
	 *            购买数量
	 * @return 购买的商品重量
	 */
	public static BigDecimal getSaleWeight(long saleNumber, BigDecimal weight, long buySaleNumber) {
		if (weight == null)
			return new BigDecimal("0.00");
		if (saleNumber == buySaleNumber)
			return weight;
		else {
			BigDecimal saleNumberBig = new BigDecimal(saleNumber);
			BigDecimal buySaleNumberBig = new BigDecimal(buySaleNumber);
			BigDecimal buyWeight = weight.divide(saleNumberBig, 3, BigDecimal.ROUND_HALF_UP).multiply(buySaleNumberBig);
			// 小于或等于0
			if (buyWeight.compareTo(new BigDecimal("0.00")) == -1 || buyWeight.compareTo(new BigDecimal("0.00")) == 0)
				return new BigDecimal("0.00");
			return buyWeight;
		}
	}

	public static String longArrayToStr(Long[] longArray) {
		if (longArray == null)
			return null;
		if (longArray.length == 0)
			return "";
		String result = "";
		for (Long val : longArray)
			result = val + "," + result;
		return result.substring(0, result.length() - 1);
	}

	/**
	 * 去除字符串中的空格、回车、换行符、制表符 （主要作用在模糊查询、各种搜索）
	 *
	 * @param str
	 * @return
	 */
	public static String strToTrim(String str) {
		if (str != null) {
			Pattern p = Pattern.compile("\\s*|\t|\r|\n");
			Matcher m = p.matcher(str);
			str = m.replaceAll("");
		}
		return str;
	}

	/**
	 * 去除字符串的前后空格 （主要作用在查询、添加）
	 *
	 * @param str
	 * @return
	 */
	public static String toTrim(String str) {
		if (str != null)
			str = str.trim();
		return str;
	}

	/**
	 * 验证金额格式
	 *
	 * @param fee
	 * @return
	 */
	public static boolean isMoney(Double fee) {
		String str = formatMoney(fee);
		Pattern pattern = Pattern.compile("\\d{1,8}([\\.]\\d{2})?"); // 判断小数点前8位后2位
		Matcher match = pattern.matcher(str);
		if (match.matches() == false)
			return false;
		else
			return true;
	}

	/**
	 * 格式化金额
	 *
	 * @param s
	 * @param len
	 * @return
	 */
	public static String formatMoney(Double num) {
		DecimalFormat fmt = new DecimalFormat("########.00");
		return fmt.format(num);
	}

	/**
	 * 非法字符
	 *
	 * @param str
	 * @return
	 */
	public static boolean illgelChar(String str) {
		if (str.indexOf(",") > -1 || str.indexOf("，") > -1)
			return false;
		if (str.indexOf("`") > -1 || str.indexOf("｀") > -1)
			return false;
		if (str.indexOf("~") > -1 || str.indexOf("￣") > -1)
			return false;
		if (str.indexOf("!") > -1 || str.indexOf("！") > -1)
			return false;
		if (str.indexOf("@") > -1 || str.indexOf("＠") > -1)
			return false;
		if (str.indexOf("#") > -1 || str.indexOf("#") > -1)
			return false;
		if (str.indexOf("$") > -1 || str.indexOf("＄") > -1)
			return false;
		if (str.indexOf("%") > -1 || str.indexOf("％") > -1)
			return false;
		if (str.indexOf("^") > -1 || str.indexOf("＾") > -1)
			return false;
		if (str.indexOf("&") > -1 || str.indexOf("＆") > -1)
			return false;
		if (str.indexOf("*") > -1 || str.indexOf("×") > -1)
			return false;
		if (str.indexOf("(") > -1 || str.indexOf("（") > -1)
			return false;
		if (str.indexOf(")") > -1 || str.indexOf("）") > -1)
			return false;
		if (str.indexOf("_") > -1 || str.indexOf("——") > -1)
			return false;
		if (str.indexOf("-") > -1 || str.indexOf("－") > -1)
			return false;
		if (str.indexOf("=") > -1 || str.indexOf("＝") > -1)
			return false;
		if (str.indexOf("+") > -1 || str.indexOf("＋") > -1)
			return false;
		if (str.indexOf("|") > -1 || str.indexOf("｜") > -1)
			return false;
		if (str.indexOf("/") > -1 || str.indexOf("／") > -1)
			return false;
		if (str.indexOf("?") > -1 || str.indexOf("？") > -1)
			return false;
		if (str.indexOf(":") > -1 || str.indexOf("，") > -1)
			return false;
		if (str.indexOf(".") > -1 || str.indexOf("．") > -1)
			return false;
		if (str.indexOf("'") > -1 || str.indexOf("＇") > -1)
			return false;
		if (str.indexOf("\\") > -1 || str.indexOf("＼＼") > -1)
			return false;
		return true;
	}
	
	public static String strArrayToStr(ArrayList<String> strList) {
		if (strList == null)
			return null;
		if (strList.size() == 0)
			return "";
		String result = "";
		for (String val : strList)
			result = val + "," + result;
		return result.substring(0, result.length() - 1);
	}

}
