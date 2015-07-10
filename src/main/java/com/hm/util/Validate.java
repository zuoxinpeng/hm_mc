package com.hm.util;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validate {

	private static int CMCC = 1;//中国移动
    private static int UNICOM = 2;//中国联通
    private static int TELECOM = 3;//中国电信
    private static int UNKNWON = 5;//未知号码

	/**
	 * 判断给定的字符串(strNumber)是否是数值型
	 *
	 * @param strNumber
	 *            要确认的字符串
	 * @return
	 */
	public static boolean IsNumber(String strNumber) {
		// ^([0-9])[0-9]*(\.\w*)?$
		if (strNumber.startsWith("-")) {
			strNumber = strNumber.substring(1);
		}
		return strNumber.matches("^([0-9])[0-9]*(\\.\\w*)?$");
	}

	/**
	 * 判断给定的数字是否在执行的范围中
	 *
	 * @param strNumber
	 *            要确认的字符串
	 * @param min
	 *            最小值
	 * @param max
	 *            最大值
	 * @return
	 */
	public static boolean IsNumberRange(double strNumber, double min, double max) {
		double i = strNumber;
		if (min <= i && i <= max) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 判断给定的数字是否在执行的范围中
	 *
	 * @param strNumber
	 *            要确认的字符串
	 * @param min
	 *            最小值
	 * @param max
	 *            最大值
	 * @return
	 */
	public static boolean IsNumberRange(int strNumber, int min, int max) {
		double i = strNumber;
		if (min <= i && i <= max) {
			return true;
		} else {
			return false;
		}
	}

	// 判断给定的字符串数组(strNumber)中的数据是不是都为数值型
	/**
	 * 判断给定的字符串数组(strNumber)中的数据是不是都为数值型
	 */
	public static boolean IsNumberArray(String[] strNumber) {
		if (strNumber == null) {
			return false;
		}
		if (strNumber.length < 1) {
			return false;
		}
		for (String id : strNumber) {
			if (!IsNumber(id)) {
				return false;
			}
		}
		return true;

	}

	// 检测是否符合email格式
	/**
	 * 检测是否符合email格式
	 *
	 * @param strEmail
	 *            要判断的email字符串 判断结果
	 */
	public static boolean IsValidEmail(String strEmail) {
		return strEmail
				.matches("^([\\w-.]+)@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)|(([\\w-]+\\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\\]?)$");
	}

	// 判断是否为base64字符串
	/**
	 * 判断是否为base64字符串
	 *
	 * @param str
	 *
	 */
	public static boolean IsBase64String(String str) {
		// A-Z, a-z, 0-9, +, /, =
		return str.matches("[A-Za-z0-9+/=]");
	}

	// 检测是否有Sql危险字符
	/**
	 * 检测是否有Sql危险字符
	 *
	 * @param str
	 *            要判断字符串 判断结果
	 */
	public static boolean IsSafeSqlString(String str) {

		return !str.matches("[-|;|,|/|(|)|[|\\]|}|{|%|@|*|!|']");
	}

	// 检测是否有危险的可能用于链接的字符串
	/**
	 * 检测是否有危险的可能用于链接的字符串
	 *
	 * @param str
	 *            要判断字符串 判断结果
	 */
	public static boolean IsSafeUserInfoString(String str) {
		return !str
				.matches("/^\\s*$|^c:\\\\con\\\\con$|[%,*\"\\s\t<>&]|$guestexp/is");
	}

	// 是否是时间 HH:mm:ss
	/**
	 * 是否是时间 HH:mm:ss
	 *
	 *
	 */
	public static boolean IsTime(String timeval) {
		return timeval
				.matches("^((([0-1]?[0-9])|(2[0-3])):([0-5]?[0-9])(:[0-5]?[0-9])?)$");
	}

	// 是否为ip
	/**
	 * 是否为ip
	 *
	 * @param ip
	 *
	 */
	public static boolean IsIP(String ip) {
		return ip
				.matches("^((2[0-4]\\d|25[0-5]|[01]?\\d\\d?)\\.){3}(2[0-4]\\d|25[0-5]|[01]?\\d\\d?)$");
	}

	// 判断字符串是否是yy-mm-dd字符串
	/**
	 * 判断字符串是否是yy-mm-dd字符串
	 *
	 * @param str
	 *            待判断字符串 判断结果
	 */
	public static boolean IsDateString(String str) {
		return str.matches("(\\d{4})-(\\d{1,2})-(\\d{1,2})");
	}

	// 判断字符串是否是0001-06-16 12:00:01 AM|||2004/2/29|||3:30 PM字符串
	/**
	 * 判断字符串是否是0001-06-16 12:00:01 AM|||2004/2/29|||3:30 PM字符串
	 *
	 * @param str
	 *            待判断字符串 判断结果
	 */
	public static boolean IsDateTimeString(String str) {
		// 1980-1-1
		// 1980-01-1
		// 1980-1-01
		// 1980-1-1 1:1
		// 1980-1-1 01:1
		// 1980-1-1 1:01
		if (str.contains(" ")) {
			return IsDateString(str.substring(0, str.indexOf(" ")))
					&& IsTime(str.substring(str.indexOf(" ") + 1));
		} else {
			return IsDateString(str);
		}

		// //
		// ^((\d{2}(([02468][048])|([13579][26]))[\-\/\s]?((((0?[13578])|(1[02]))[\-\/\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\-\/\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\-\/\s]?((0?[1-9])|([1-2][0-9])))))|(\d{2}(([02468][1235679])|([13579][01345789]))[\-\/\s]?((((0?[13578])|(1[02]))[\-\/\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\-\/\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\-\/\s]?((0?[1-9])|(1[0-9])|(2[0-8]))))))(\s(((0?[1-9])|(1[0-9])|(2[0-3]))\:([0-5][0-9])((\s)|(\:([0-5][0-9])))?))?$
		// boolean foundMatch =
		// str.matches("^((\\d{2}(([02468][048])|([13579][26]))[\\-/\\s]?((((0?[13578])|(1[02]))[\\-/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-/\\s]?((0?[1-9])|([1-2][0-9])))))|(\\d{2}(([02468][1235679])|([13579][01345789]))[\\-/\\s]?((((0?[13578])|(1[02]))[\\-/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-/\\s]?((0?[1-9])|(1[0-9])|(2[0-8]))))))(\\s(((0?[1-9])|(1[0-9])|(2[0-3]))\\:([0-5][0-9])((\\s)|(\\:([0-5][0-9])))?))?$");
		// return foundMatch;
		// // return str
		// //
		// .matches("(?i)(?!(?:1582\\D10\\D(?:0?[5-9]|1[0-4]))|(?:1752\\D0?9\\D(?:0?[3-9]|1[0-3])))(?:^(?=\\d)(?:(\\d{4})([-./])(0?[1-9]|1[012])\\2( 1500 not evenly divisible by 400 are not leap year))(?:(?:\\d\\d)(?:[02468][048]|[13579][26]))\\2(?:0?2)\\2)|(?(?=(0?[1-9]|1[012])(:[0-5]\\d){0,2}(?:\\x20[AP]M))|([01]\\d|2[0-3])(:[0-5]\\d){1,2}?$");
	}

	// 判断文件名是否为浏览器可以直接显示的图片文件名
	/**
	 * 判断文件名是否为浏览器可以直接显示的图片文件名
	 *
	 * @param filename
	 *            文件名 是否可以直接显示
	 */
	public static boolean IsImgFilename(String filename) {

		filename = filename.trim();
		if (filename.endsWith(".") || filename.indexOf(".") == -1) {
			return false;
		}
		String extname = filename.substring(filename.lastIndexOf(".") + 1)
				.toLowerCase();
		return extname.equals("jpg") || extname.equals("jpeg")
				|| extname.equals("png") || extname.equals("bmp")
				|| extname.equals("gif");
	}

	// IsEmail
	/**
	 * 是否是Email
	 *
	 * @param obj
	 *            要检查的数据
	 *
	 */
	public static boolean IsEmail(Object obj) {
		return obj.toString().matches(
				"(?i)^\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*$");
	}

	// IsGUID
	/**
	 * 是否是GUID
	 *
	 * @param obj
	 *            要检查的数据
	 *
	 */
	public static boolean IsGUID(Object obj) {
		return obj
				.toString()
				.matches(
						"(?i)^([a-fA-F0-9]{8})(-)([a-fA-F0-9]{4})\\2([a-fA-F0-9]{4}\\2([a-fA-F0-9]{4})\\2([a-fA-F0-9]{12}))$");
	}

	/**
	 * 获得字符串的长度
	 *
	 * @param str
	 *            原始字符串
	 */
	public static int getLength(String str) {

		if (str == null) {
			return 0;
		}
		// byte[] bl = System.Text.Encoding.Default.GetBytes(str);
		// return bl.Length;
		return str.length();
	}

	// [start] 对比对象是否相等
	// [start] 对比对象是否相等 Object
	/**
	 * 对比对象是否相等
	 */
	public static boolean isEquals(Object str1, Object str2) {

		if (str1 == null && str2 == null) {
			return true;
		}

		if (str1 == null && str2 != null) {
			return false;
		}

		if (str1 != null && str2 == null) {
			return false;
		} else {
			return str1.equals(str2);
		}
	}

	// [end]
	// [start] 对比对象是否相等 String
	/**
	 * 对比对象是否相等
	 */
	public static boolean isEquals(String str1, String str2) {

		if (str1 == null && str2 == null) {
			return true;
		}

		if (str1 == null && str2 != null) {
			return false;
		}

		if (str1 != null && str2 == null) {
			return false;
		} else {
			return str1.equals(str2);
		}
	}

	// [end]
	// [start] 对比对象是否相等 Date
	/**
	 * 对比对象是否相等
	 */
	public static boolean isEquals(Date str1, Date str2) {

		if (str1 == null && str2 == null) {
			return true;
		}

		if (str1 == null && str2 != null) {
			return false;
		}

		if (str1 != null && str2 == null) {
			return false;
		} else {
			return str1.equals(str2);
		}
	}

	// [end]
	// [start] 对比对象是否相等 int
	/**
	 * 对比对象是否相等
	 */
	public static boolean isEquals(int str1, int str2) {
		return str1 == str2;
	}

	// [end]
	// [start] 对比对象是否相等 Short
	/**
	 * 对比对象是否相等
	 */
	public static boolean isEquals(Short str1, Short str2) {
		return str1 == str2;
	}

	// [end]
	// [start] 对比对象是否相等 long
	/**
	 * 对比对象是否相等
	 */
	public static boolean isEquals(long str1, long str2) {
		return str1 == str2;
	}

	// [end]
	// [start] 对比对象是否相等 Float
	/**
	 * 对比对象是否相等
	 */
	public static boolean isEquals(Float str1, Float str2) {
		return str1 == str2;
	}

	// [end]
	// [start] 对比对象是否相等 Double
	/**
	 * 对比对象是否相等
	 */
	public static boolean isEquals(Double str1, Double str2) {
		return str1 == str2;
	}

	// [end]
	// [start] 对比对象是否相等 BigInteger
	/**
	 * 对比对象是否相等
	 */
	public static boolean isEquals(BigInteger str1, BigInteger str2) {

		if (str1 == null && str2 == null) {
			return true;
		}

		if (str1 == null && str2 != null) {
			return false;
		}

		if (str1 != null && str2 == null) {
			return false;
		} else {
			return str1.equals(str2);
		}
	}

	// [end]
	// [start] 对比对象是否相等 BigInteger
	/**
	 * 对比对象是否相等
	 */
	public static boolean isEquals(BigDecimal str1, BigDecimal str2) {

		if (str1 == null && str2 == null) {
			return true;
		}

		if (str1 == null && str2 != null) {
			return false;
		}

		if (str1 != null && str2 == null) {
			return false;
		} else {
			return str1.equals(str2);
		}
	}

	// [end]
	// [end]

	public static boolean isMobileNO(String mobiles) {
		boolean flag = false;
		try {
			// 手机号码验证
			Pattern p1 = Pattern
					.compile("^1(([3][456789])|([5][012789])|([8][278]))[0-9]{8}$");
			Pattern p2 = Pattern
					.compile("^1(([3][456789])|([5][012789])|([8][278]))[0-9]{8}$");
			Pattern p3 = Pattern
					.compile("^1(([3][456789])|([5][012789])|([8][278]))[0-9]{8}$");

			Pattern p4 = Pattern
					.compile("^(\\(\\d{3,4}\\)|\\d{3,4}-)?\\d{7,8}$");// 电话号码
			// Pattern p =
			// Pattern.compile("^((13[0-9])|(15[^4,\\D])|(18))\\d{9}$");
			Matcher m1 = p1.matcher(mobiles);
			Matcher m2 = p2.matcher(mobiles);
			Matcher m3 = p3.matcher(mobiles);
			Matcher m4 = p4.matcher(mobiles);
			if (m1.matches() || m2.matches() || m3.matches() || m4.matches()) {
				return true;
			}
		} catch (Exception e) {
			flag = false;
		}
		return flag;
	}

	/**
	 * 手机号格式验证 规则：1开头的11位数字
	 *
	 * @param mobile
	 * @return
	 */
	public static boolean isMobile(String mobile) {
		Pattern p = Pattern.compile("1[0-9]{10}");
		Matcher m = p.matcher(mobile);
		if (!m.matches()) {
			return false;
		}
		return true;
	}

	/**
	 * 固定电话验证 规则：3到4位数字开头 + ["-"] + 7到8位数字，
	 * 或3到4位数字开头 + "-" + 4位数字 + "-" + 3到4位数字，
	 * 如：010-89920102，01089920102，010-8992-123，010-8992-1234
	 *
	 * @param telephone
	 * @return
	 */
	public static boolean isTelephone(String telephone) {
		Pattern p = Pattern
				.compile("^((\\d{3,4}|\\d{3,4}-)?\\d{7,8})|"
						+ "(\\d{3,4}?-\\d{4}-\\d{3,4})$");
		Matcher m = p.matcher(telephone);
		if (!m.matches()) {
			return false;
		}
		return true;
	}

	/**
	 * 身份证验证 规则：15位或18位数字或17数数字+1位X
	 *
	 * @param idNumber
	 * @return
	 */
	public static boolean isIdNumber(String idNumber) {
		Pattern p = Pattern.compile("^\\d{15}|\\d{18}|\\d{17}[xX]$");
		Matcher m = p.matcher(idNumber);
		if (!m.matches()) {
			return false;
		}
		return true;
	}

	/**
	 * 判断是否为中英文数字
	 *
	 * @param chinese
	 * @return
	 */
	public static boolean isChinese(String chinese) {
		Pattern p = Pattern.compile("^[\\(（\\)）\u4E00-\u9FA5A-Za-z0-9_]+$");
		Matcher m = p.matcher(chinese);
		if (!m.matches()) {
			return false;
		}
		return true;
	}

	/**
	 * 针对公司名称做的验证 中英文数字()（）
	 *
	 * @param companyName
	 * @return
	 */
	public static boolean isCompanyName(String companyName) {
		Pattern p = Pattern.compile("^[\u4E00-\u9FA5A-Za-z0-9()（）_]+$");
		Matcher m = p.matcher(companyName);
		if (!m.matches()) {
			return false;
		}
		return true;
	}

	public static boolean isPassport(String passport) {
		Pattern p = Pattern.compile("^[a-zA-Z][0-9]{7,15}$");
		Matcher m = p.matcher(passport);
		if (!m.matches()) {
			return false;
		}
		return true;
	}


	/**
	 * 判断手机号
	 * @param msg
	 * @return
	 */
    private static boolean isNumeric(String msg) {
        for (int i = 0; i < msg.length(); i++) {
            if (java.lang.Character.isDigit(msg.charAt(i))) {
                continue;
            }
            return false;
        }
        return true;
    }

    /**
     * 判断手机号码运营商
     * @param phoneNum
     * @param telCMCC
     * @param telUNICOM
     * @param telTELECOM
     * @return
     */
    public static int getOperater(String phoneNum, String telCMCC, String telUNICOM, String telTELECOM) {
        String head3 = "";
//        String head4 = "";
        phoneNum = phoneNum.trim();

        if (phoneNum == null || phoneNum.length() < 11) {
            return UNKNWON;
        } else {
            if (phoneNum.startsWith("+")) {
                phoneNum = phoneNum.substring(1);
            }
            if (phoneNum.startsWith("86")) {
                phoneNum = phoneNum.substring(2);
            }
        }

        if (phoneNum.length() != 11) {
            return UNKNWON;
        }

        if (!isNumeric(phoneNum)) {
            return UNKNWON;
        }

        head3 = phoneNum.substring(0, 3);
//        head4 = phoneNum.substring(0, 4);

		if(telCMCC.contains(head3)){
    		return CMCC;
    	}else if(telUNICOM.contains(head3)){
    		return UNICOM;
    	}else if(telTELECOM.contains(head3)){
    		return TELECOM;
    	}
        return UNKNWON;
    }

    /**
     * 校验是否是英文字母
     * @param english
     * @return
     */
    public static boolean isEnglishNumber(String english){
    	 Pattern pattern = Pattern.compile("^[A-Za-z]+$");   
         Matcher m = pattern.matcher(english);  
         if(!m.matches()){
        	return false; 
         }
    	return true;
    }
    
    
    
	public static void main(String[] args){
		System.out.println("1234567:"+isTelephone("1234567"));
		System.out.println("12345678:"+isTelephone("12345678"));
		System.out.println("11001234567:"+isTelephone("11001234567"));
		System.out.println("110012345678:"+isTelephone("110012345678"));
		System.out.println("1100-1234567:"+isTelephone("1100-1234567"));
		System.out.println("1100-12345678:"+isTelephone("1100-12345678"));
		System.out.println("1100-1234-567:"+isTelephone("1100-1234-567"));
		System.out.println("1100-1234-5678:"+isTelephone("1100-1234-5678"));
		System.out.println("11001234-567:"+isTelephone("11001234-567"));
		System.out.println("11001234-5678:"+isTelephone("11001234-5678"));
	}
}
