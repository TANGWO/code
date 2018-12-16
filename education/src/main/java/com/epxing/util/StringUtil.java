package com.epxing.util;

import java.math.BigDecimal;
import java.security.MessageDigest;
import java.sql.Timestamp;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import sun.misc.BASE64Encoder;

/**
 * 字符处理工具类
 * 
 * @author wanghong
 * 
 */
public class StringUtil {
	
	/**
	 * 字符串数组转换为字符串
	 * 
	 * @param a
	 * @param sep
	 * @return
	 */
	public static String arrayToString(String[] a, String sep) {
		if (a == null || a.length == 0)
			return "";
		if (a.length == 1)
			return a[0];
		String b = "";
		for (int i = 0; i < a.length; i++) {
			b += a[i] + sep;
		}
		return b.substring(0, b.length() - sep.length());
	}

	/**
	 * 字符串重复 将某字符串复制多遍，并用分隔符组成新的字符串
	 * 
	 * @param sign      要复制的字符串单元
	 * @param separator 分隔符
	 * @param num       复制数量
	 * @return
	 */
	public static String genString(String sign, String separator, int num) {
		String r = "";
		for (int i = 0; i < num; i++) {
			r = r + sign + separator;
		}
		if (r.endsWith(separator)) {
			r = r.substring(0, r.length() - 1);
		}
		return r;
	}

	/**
	 * 去掉字符串首尾的空格
	 * 
	 * @param input
	 * @return
	 */
	public static String removeSpace(String input) {
		return removeBeginSpace(removeEndSpace(input));
	}

	public static String removeBeginSpace(String input) {
		if (input == null)
			return "";
		if (input.startsWith(" ")) {
			input = input.substring(1, input.length());
			return removeBeginSpace(input);
		}
		return input;
	}

	public static String removeEndSpace(String input) {
		if (input == null)
			return "";
		if (input.endsWith(" ")) {
			input = input.substring(0, input.length() - 1);
			return removeEndSpace(input);
		}
		return input;
	}


	/**
	 * SQL注入关键字屏蔽
	 */
	public static boolean sqlKeyWords(String str) {
		boolean flag = false;
		// SQL中的关键字，可以自己加
		String skws = "'|and|exec|insert|select|delete|update|count|*|%|chr|mid|master|truncate|char|declare|;|or|-|+|,|&|$|@|<|>|(|)";
		String[] skwArray = skws.split("\\|");
		String s = str.toLowerCase();
		for (int i = 0, len = skwArray.length; i < len; i++) {
			if (s.indexOf(skwArray[i]) > -1) {
				flag = true;
				break;
			}
		}
		return flag;
	}

	/**
	 * 将List转为String[]
	 * 
	 * @param list
	 * @return
	 */
	public static String[] listToArray(List<String> list) {
		if (list == null)
			return null;
		String[] a = new String[list.size()];
		for (int i = 0; i < list.size(); i++) {
			a[i] = list.get(i);
		}
		return a;
	}

	/**
	 * 判断不为NULL
	 * 
	 * @param a
	 * @return
	 */
	public static boolean notNull(String a) {
		return !isNull(a);
	}

	/**
	 * 判断是否为空
	 * 
	 * @param a
	 * @return
	 */
	public static boolean isNull(String a) {
		return a == null || "".equals(a.trim()) || "null".equals(a.trim());
	}

	public static String repeat(String item, int time) {
		StringBuffer b = new StringBuffer();
		for (int i = 0; i < time; i++) {
			b.append(item);
		}
		return b.toString();
	}

	public static int toInt(String a) {
		if (isNull(a))
			return 0;
		try {
			return new Integer(a).intValue();
		} catch (Exception e) {
		}
		return 0;
	}

	public static long toLong(String a) {
		if (isNull(a))
			return 0;
		try {
			return new Long(a).longValue();
		} catch (Exception e) {
		}
		return 0;
	}

	public static double toDouble(String a) {
		if (isNull(a))
			return 0;
		try {
			return new Double(a).doubleValue();
		} catch (Exception e) {
		}
		return 0;
	}

	public static float toFloat(String a) {
		if (isNull(a))
			return 0;
		try {
			return new Float(a).floatValue();
		} catch (Exception e) {
		}
		return 0;
	}

	public static String timeToString(Timestamp t) {
		if (t == null)
			return "";
		return t.toString();
	}

	public static String clobToString(java.sql.Clob clob) {
		try {
			return clob.getSubString((long) 1, (int) clob.length());
		} catch (Exception e) {
			// e.printStackTrace();
			return "";
		}
	}

	public static String objectToString(Object obj) {
		if (obj == null)
			return "";
		return obj.toString();
	}

	public static boolean istoInt(String value) {
		if (value == null || value.equals(""))
			return false;
		char[] c = value.toCharArray();
		boolean blReturn = true;
		for (int ni = 0; ni < c.length; ni++) {
			if (c[ni] < 48 || c[ni] > 57) //
			{
				blReturn = false;
				break;
			}
		}
		return blReturn;
	}

	public static boolean noInt(String value) {
		if (isNull(value))
			return false;
		char[] c = value.toCharArray();
		boolean blReturn = false;
		for (int ni = 0; ni < c.length; ni++) {
			if (c[ni] < 48 || c[ni] > 57) //
			{
				blReturn = true;
				break;
			}
		}
		return blReturn;
	}

	public static boolean checkValidDate(String pDateObj) {
		boolean ret = false;
		if (pDateObj == null || pDateObj.length() != 10) {
			ret = true;
		}
		try {
			int year = new Integer(pDateObj.substring(0, 4)).intValue();
			int month = new Integer(pDateObj.substring(6, 7)).intValue();
			int day = new Integer(pDateObj.substring(9)).intValue();
			Calendar cal = Calendar.getInstance();
			cal.setLenient(true);
			cal.set(year, month - 1, day);
			cal.getTime();
		} catch (Exception e) {
			ret = true;
		}
		return ret;
	}

	public static String firstCharUpperCase(String str) {
		str = str.toUpperCase().charAt(0) + str.substring(1);
		return str;
	}

	public static String changeStringCharSet(String str) {
		try {
			return new String(str.getBytes("ISO-8859-1"), "GBK");
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
	}

	/**
	 * 将字符串转化成新的编码格式
	 * 
	 * @param str
	 * @param newCharset
	 * @return
	 */
	public static String changeCharset(String str, String newCharset) {
		try {
			if (str == null)
				return str;
			return new String(str.getBytes(), newCharset);
		} catch (Exception e) {
			return str;
		}
	}

	public static String[] changeStringCharSet(String[] str) {
		for (int i = 0; i < str.length; i++)
			str[i] = changeStringCharSet(str[i]);
		return str;
	}

	public static double doubleToDouble(double num, int i) {
		BigDecimal bigDecimal = new BigDecimal(num);
		return bigDecimal.setScale(i, BigDecimal.ROUND_HALF_UP).doubleValue();
	}

	public static boolean isEmpty(String str) {
		return (str == null) || "null".equals(str) || "".equals(str);
	}

	public static float floatToFloat(float num, int i) {
		BigDecimal bigDecimal = new BigDecimal(num);
		return bigDecimal.setScale(i, BigDecimal.ROUND_HALF_UP).floatValue();
	}

	public static String floatToString(float num) {
		String result = "0";
		try {
			result = Float.toString(num);
		} catch (Exception e) {
		}
		return result;
	}

	public static String[] stringToArray(String str) {
		return stringToArray(str, ",");
	}

	public static String[] stringToArray(String str, String splitString) {
		String[] staObj = str.split(splitString);
		List<String> list = new ArrayList<String>();
		for (int i = 0; i < staObj.length; i++) {
			if (!list.contains(staObj[i])) {
				list.add(staObj[i]);
			}
		}
		return list.toArray(new String[list.size()]);
	}

	// 求两个数组的交集
	public static String[] intersect(String[] arr1, String[] arr2) {
		Map<String, Boolean> map = new HashMap<String, Boolean>();
		LinkedList<String> list = new LinkedList<String>();
		for (String str : arr1) {
			if (!map.containsKey(str)) {
				map.put(str, Boolean.FALSE);
			}
		}
		for (String str : arr2) {
			if (map.containsKey(str)) {
				map.put(str, Boolean.TRUE);
			}
		}
		for (Entry<String, Boolean> e : map.entrySet()) {
			if (e.getValue().equals(Boolean.TRUE)) {
				list.add(e.getKey());
			}
		}
		String[] result = {};
		return list.toArray(result);
	}

	public static boolean isEqual(String a, String b) {
		if (a == null || b == null)
			return false;
		return a.equals(b);
	}

	public static String getNextNum(int length, String maxNum) {
		int next = toInt(maxNum) + 1;
		String nextString = next + "";
		int p = length - nextString.length();
		if (p > 0) {
			for (int i = 0; i < p; i++) {
				nextString = "0" + nextString;
			}
		}
		return nextString;
	}

	/**
	 * 根据分隔符识别某字符串的前缀
	 * 
	 * @param source 字符串
	 * @param sep    分隔符
	 * @return
	 */
	public static String getPrefix(String source, String sep) {
		if (isNull(source) || isNull(sep))
			return null;
		int index = source.indexOf(sep);
		if (index > 0) {
			return source.split(sep)[0];
		}
		return null;
	}

	/**
	 * 判断一个字符串是否为数字
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isNum(String str) {
		return str.matches("^[-+]?(([0-9]+)([.]([0-9]+))?|([.]([0-9]+))?)$");
	}

	/**
	 * 保留两位小数
	 */
	public static double mathDouble(double num) {
		BigDecimal b = new BigDecimal(num);
		return b.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
	}

	/**
	 * 数字加合
	 * 
	 * @param a
	 * @param b
	 * @return
	 */
	public static double doubleAdd(String a, String b) {
		return toDouble(a) + toDouble(b);
	}

	/**
	 * 字符加密
	 * 
	 * @param value
	 * @return
	 */
	public static String encrypt(String value) {
		try {
			MessageDigest md5 = MessageDigest.getInstance("MD5");
			BASE64Encoder base64en = new BASE64Encoder();
			return base64en.encode(md5.digest(value.getBytes("utf-8")));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return value;
	}

	/**
	 * 整数转换为字符串规定位数（位数不足补0）
	 * 
	 * @param value 要转换的值
	 * @param digit 位数
	 * @return
	 */
	public static String getIntDigits(int value, int digit) {
		NumberFormat numFormatter = NumberFormat.getNumberInstance();
		numFormatter.setMinimumIntegerDigits(digit);
		numFormatter.setGroupingUsed(false);
		return numFormatter.format(value);
	}

	/**
	 * 替换字符串里括号的值
	 * 
	 * @param value    原value : aa
	 * @param newValue 要追加的新value : bb_
	 * @return bb_aa
	 */
	public static String replaceBrackets(String value, String newValue) {
		String[] patterns = { "\\([^)]+\\(", "\\)[^(]+\\)", "\\([^()]+\\)" };
		for (int i = 0; i < patterns.length; i++) {
			Matcher matcher = Pattern.compile(patterns[i]).matcher(value);
			while (matcher.find()) {
				String aa = matcher.group().substring(1, matcher.group().length() - 1);
				String newa = newValue + aa;
				value = value.replace(matcher.group(), matcher.group().replaceAll(matcher.group(), newa));
			}
		}
		return value;
	}

	public static String getColorString(String color, String name) {
		String str = "<font color=" + color + ">";
		str += name + "</font>";
		return str;
	}

	public static final boolean isChinese(char c) {
		Character.UnicodeBlock ub = Character.UnicodeBlock.of(c);
		if (ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS
				|| ub == Character.UnicodeBlock.CJK_COMPATIBILITY_IDEOGRAPHS
				|| ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_A
				|| ub == Character.UnicodeBlock.GENERAL_PUNCTUATION
				|| ub == Character.UnicodeBlock.CJK_SYMBOLS_AND_PUNCTUATION
				|| ub == Character.UnicodeBlock.HALFWIDTH_AND_FULLWIDTH_FORMS) {
			return true;
		}
		return false;
	}

	public static final boolean isChinese(String strName) {
		char[] ch = strName.toCharArray();
		for (int i = 0; i < ch.length; i++) {
			char c = ch[i];
			if (isChinese(c)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 处理的最大数字达千万亿位 精确到分
	 * 
	 * @return
	 */
	public static String digitToUppercase(String num) throws Exception {
		String fraction[] = { "角", "分" };
		String digit[] = { "零", "壹", "贰", "叁", "肆", "伍", "陆", "柒", "捌", "玖" };
		/**
		 * 仟 佰 拾 ' ' ' ' $4 $3 $2 $1 万 $8 $7 $6 $5 亿 $12 $11 $10 $9
		 */
		String unit1[] = { "", "拾", "佰", "仟" };// 把钱数分成段,每四个一段,实际上得到的是一个二维数组
		String unit2[] = { "元", "万", "亿", "万亿" }; // 把钱数分成段,每四个一段,实际上得到的是一个二维数组
		BigDecimal bigDecimal = new BigDecimal(num);
		bigDecimal = bigDecimal.multiply(new BigDecimal(100));
		String strVal = String.valueOf(bigDecimal.toBigInteger());
		String head = strVal.substring(0, strVal.length() - 2); // 整数部分
		String end = strVal.substring(strVal.length() - 2); // 小数部分
		String endMoney = "";
		String headMoney = "";
		if ("00".equals(end)) {
			endMoney = "整";
		} else {
			if (!end.substring(0, 1).equals("0")) {
				endMoney += digit[Integer.valueOf(end.substring(0, 1))] + "角";
			} else if (end.substring(0, 1).equals("0") && !end.substring(1, 2).equals("0")) {
				endMoney += "零";
			}
			if (!end.substring(1, 2).equals("0")) {
				endMoney += digit[Integer.valueOf(end.substring(1, 2))] + "分";
			}
		}
		char[] chars = head.toCharArray();
		Map<String, Boolean> map = new HashMap<String, Boolean>();// 段位置是否已出现zero
		boolean zeroKeepFlag = false;// 0连续出现标志
		int vidxtemp = 0;
		for (int i = 0; i < chars.length; i++) {
			int idx = (chars.length - 1 - i) % 4;// 段内位置 unit1
			int vidx = (chars.length - 1 - i) / 4;// 段位置 unit2
			String s = digit[Integer.valueOf(String.valueOf(chars[i]))];
			if (!"零".equals(s)) {
				headMoney += s + unit1[idx] + unit2[vidx];
				zeroKeepFlag = false;
			} else if (i == chars.length - 1 || map.get("zero" + vidx) != null) {
				headMoney += "";
			} else {
				headMoney += s;
				zeroKeepFlag = true;
				map.put("zero" + vidx, true);// 该段位已经出现0；
			}
			if (vidxtemp != vidx || i == chars.length - 1) {
				headMoney = headMoney.replaceAll(unit2[vidx], "");
				headMoney += unit2[vidx];
			}
			if (zeroKeepFlag && (chars.length - 1 - i) % 4 == 0) {
				headMoney = headMoney.replaceAll("零", "");
			}
		}
		return headMoney + endMoney;
	}


	/**
	 * 获取uuid
	 * 
	 * @return
	 */
	public static String getUuid() {
		return UUID.randomUUID().toString().replace("-", "");
	}

	
	
	/**
	 * -*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*
	 * HTML过滤
	 * -*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*
	 */
	/**
	 * 针对前台XXS
	 * @param str
	 * @return
	 */
	public static String htmlEncode(String str) {
		if(str == null || "".equals(str.trim())) 
			return str;
		
		StringBuilder encodeStrBuilder = new StringBuilder();
		for (int i = 0, len = str.length(); i < len; i++) {
			encodeStrBuilder.append(htmlEncode1(str.charAt(i)));
		}
		return htmlWordsEncode(encodeStrBuilder.toString());
	}
	
	/**
	 * 转换<>符号
	 * @param str
	 * @return
	 */
	public static String stringWash(String str) {
		if(str == null || "".equals(str.trim())) 
			return str;
		
		StringBuilder encodeStrBuilder = new StringBuilder();
		for (int i = 0, len = str.length(); i < len; i++) {
			encodeStrBuilder.append(htmlEncode2(str.charAt(i)));
		}
		return htmlWordsEncode(encodeStrBuilder.toString());
	}

	/**
	 * 逆转换<>符号
	 * @param str
	 * @return
	 */
	public static String stringWash2(String str) {
		if (null != str) {
			StringBuilder encodeStrBuilder = new StringBuilder();
			for (int i = 0, len = str.length(); i < len; i++) {
				encodeStrBuilder.append(htmlEncode3(str.charAt(i)));
			}
			return encodeStrBuilder.toString();
		} else {
			return str;
		}
	}

	/**
	 * 特殊HTML字符转换
	 * 
	 * @param c
	 * @return
	 */
	public static String htmlEncode(char c) {
		switch (c) {
		case '&':
			return "&amp;";
		case '<':
			return "&lt;";
		case '>':
			return "&gt;";
		case '"':
			return "&quot;";
		case ' ':
			return "&nbsp;";
		default:
			return c + "";
		}
	}

	/**
	 * 特殊HTML字符转换(半角转全角)
	 * 
	 * @param c
	 * @return
	 */
	public static String htmlEncode1(char c) {
		switch (c) {
		case '&':
			return "＆";
		case '<':
			return "＜";
		case '>':
			return "＞";
		case '"':
			return "＂";
		case '(':
			return "（";
		case ')':
			return "）";
		case ';':
			return " ；";
		default:
			return c + "";
		}
	}

	public static String htmlEncode2(char c) {
		switch (c) {
		case '<':
			return "＜";
		case '>':
			return "＞";
		default:
			return c + "";
		}
	}

	public static String htmlEncode3(char c) {
		switch (c) {
		case '＜':
			return "<";
		case '＞':
			return ">";
		default:
			return c + "";
		}
	}

	public static String htmlWordsEncode(String str) {
		if (str.indexOf("script") > -1) {
			str = str.replaceAll("script", "ｓｃｒｉｐｔ");
		}

		if (str.indexOf("document") > -1) {
			str = str.replaceAll("document", "ｄｏｃｕｍｅｎｔ");
		}

		if (str.indexOf("eval") > -1) {
			str = str.replaceAll("eval", "ｅｖａｌ");
		}

		if (str.indexOf("alert") > -1) {
			str = str.replaceAll("alert", "ａｌｅｒｔ");
		}
		return str;
	}

	
	
}
