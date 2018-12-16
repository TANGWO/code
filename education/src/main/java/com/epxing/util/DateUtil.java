package com.epxing.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;


public class DateUtil {
	public final static String DATE_FORMAT = "yyyy-MM-dd";
	public static String DATE_FORMAT_DATEONLY = "yyyy-MM-dd"; // 年/月/日
	public final static String DATETIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
	private static SimpleDateFormat sdfDateOnly = new SimpleDateFormat(DateUtil.DATE_FORMAT_DATEONLY);

	/**
	 * 将字符串格式的日期转为java.sql.Date类型
	 * 
	 * @param date
	 * @return
	 */
	public static java.sql.Date getDateFromString(String date) {
		if (date == null || date.equals(""))
			return null;
		String format = "";
		if (date.indexOf('T') != -1) {
			format = "yyyy-MM-dd'T'HH:mm:ss";
		} else if (date.indexOf('/') != -1) {
			date = date.replaceAll("\\/", "-");
			format = "yyyy-MM-dd HH:mm:ss";
		} else {
			format = "yyyy-MM-dd HH:mm:ss";
		}
		if (date != null && date.length() <= 10) {
			format = "yyyy-MM-dd";
		}
		if (date != null && date.length() <= 7) {
			format = "yyyy-MM";
		}
		SimpleDateFormat f = new SimpleDateFormat(format);
		try {
			long l = f.parse(date).getTime();
			return new java.sql.Date(l);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 将字符串格式的日期转为java.util.Date类型
	 * 
	 * @param date
	 * @return
	 */
	public static Date getDateUtileString(String date) {
		if (date == null || date.equals(""))
			return null;
		String format = "yyyy-MM-dd HH:mm:ss";
		if (date != null && date.length() <= 10) {
			format = "yyyy-MM-dd";
		}
		SimpleDateFormat f = new SimpleDateFormat(format);
		try {
			long l = f.parse(date).getTime();
			return new Date(l);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 获取日期的年度
	 * 
	 * @param date
	 * @return
	 */
	public static String getYear(String date) {
		Date d = getDateFromString(date);
		if (d == null)
			return "";
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(d);
		return calendar.get(Calendar.YEAR) + "";
	}

	public static String getYearByString(String date) {
		date = StringUtil.removeSpace(date);
		if (StringUtil.isNull(date))
			return "";

		if (date.length() == 4) {
			return date;
		} else if (date.length() > 5) {
			date = date.substring(0, 4);
		} else if (date.length() == 5) {
			int l = StringUtil.toInt(date);
			if (l > 10000) {
				Date d = new Date(0);
				d = HSSFDateUtil.getJavaDate(l);
				date = DateUtil.toString(d).substring(0, 4);
			}
		} else {
			return date;
		}
		return date;
	}

	/**
	 * 得到日期的月份
	 * 
	 * @param date
	 * @return
	 */
	public static String getMonth(String date) {
		Date d = getDateFromString(date);
		if (d == null)
			return "";
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(d);
		return calendar.get(Calendar.MONTH) + 1 + "";
	}

	/**
	 * 得到日期的几号
	 * 
	 * @param date
	 * @return
	 */
	public static String getDay(String date) {
		Date d = getDateFromString(date);
		if (d == null)
			return "";
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(d);
		return calendar.get(Calendar.DATE) + "";
	}

	/**
	 * 判断日期格式是否合格
	 * 
	 * @return
	 */
	public static boolean isValidFormat(String dateString, String format) {
		java.text.DateFormat f = new java.text.SimpleDateFormat(format);
		try {
			java.util.Date d = f.parse(dateString);
			return true;
		} catch (Exception e) {
		}
		return false;
	}

	/**
	 * 将字符串日期格式化
	 * 
	 * @param date
	 * @return
	 */
	public static String formatDateString(String date) {
		if (date != null && date.length() > 10) {
			date = date.substring(0, 10);
		}
		return date;
	}

	/**
	 * 将字符串转为时间戳
	 * 
	 * @param time
	 * @return
	 */
	public static java.sql.Timestamp getTimeFromString(String time) {
		java.sql.Timestamp t = null;
		try {
			if (StringUtil.isNull(time))
				return t;
			java.sql.Date d = getDateFromString(time);
			if (d == null)
				return null;
			Calendar c = Calendar.getInstance();
			c.setTime(d);
			t = new java.sql.Timestamp(c.getTime().getTime());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return t;
	}

	/**
	 * 获取系统能够当前时间
	 * 
	 * @return
	 */
	public static java.sql.Timestamp getSystemTime() {
		long t = System.currentTimeMillis();
		Calendar c = Calendar.getInstance();
		c.setTimeInMillis(t);
		return new java.sql.Timestamp(c.getTime().getTime());
	}

	/**
	 * 获取系统当前时间
	 * 
	 * @return
	 */
	public static String getSystemTimeString() {
		long t = System.currentTimeMillis();
		Calendar c = Calendar.getInstance();
		c.setTimeInMillis(t);
		java.sql.Timestamp t2 = new java.sql.Timestamp(c.getTime().getTime());
		return t2.toString();
	}

	public static String getSystemSimpleTime() {
		Date currentTime = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String dateString = formatter.format(currentTime);
		return dateString;
	}

	public static String formatUSDate(String us) {
		String date = "";
		if (StringUtil.notNull(us)) {
			date = us.substring(0, us.indexOf(" "));
			if (date.contains("/")) {
				String[] dateArr = date.split("/");
				StringBuilder sb = new StringBuilder();
				sb.append(dateArr[2] + "-");
				sb.append(dateArr[0] + "-");
				sb.append(dateArr[1]);
				return sb.toString();
			}
		}
		return date;
	}

	public static String twoDateDifferent(String first, String end) {
		if (StringUtil.notNull(first) && StringUtil.notNull(end)) {
			SimpleDateFormat df = new SimpleDateFormat("yyyy-mm-dd");
			try {
				Date d1 = df.parse(first);
				Date d2 = df.parse(end);
				return d2.getDate() - d1.getDate() + "";
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		return "";
	}

	public static String longToDateString(long date) {
		java.text.SimpleDateFormat simple = new java.text.SimpleDateFormat("yyyy-MM-dd");
		Calendar cal = Calendar.getInstance();
		cal.setTimeInMillis(date);
		return simple.format(cal.getTime());
	}

	public static long stringToDateLong(String date) {
		long l = 0;
		if (date == null || date.equals(""))
			return 0;
		String format = "yyyy-MM-dd hh:mm:ss";
		if (date != null && date.length() <= 10) {
			format = "yyyy-MM-dd";
		}
		SimpleDateFormat f = new SimpleDateFormat(format);
		try {
			l = f.parse(date).getTime();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return l;
	}

	/**
	 * 当前时间(年)
	 */
	public static String getCurrentYear() {
		Calendar cal = Calendar.getInstance();
		return cal.get(Calendar.YEAR) + "";
	}

	/**
	 * 以YYYY/MM/DD格式返回系统日期
	 * 
	 * @return 系统日期
	 * @since 1.0
	 * @history
	 */
	public static String getSysDateString() {
		return toString(new java.util.Date(System.currentTimeMillis()), sdfDateOnly);
	}

	/**
	 * 利用指定SimpleDateFormat instance转换java.util.Date到String
	 * 
	 * @param dt
	 *            java.util.Date instance
	 * @param formatter
	 *            SimpleDateFormat Instance
	 * @return
	 * @since 1.0
	 * @history
	 */
	public static String toString(java.util.Date dt, SimpleDateFormat formatter) {
		String sRet = null;

		try {
			sRet = formatter.format(dt).toString();
		} catch (Exception e) {
			e.printStackTrace();
			sRet = null;
		}
		return sRet;
	}

	public static String toString(java.util.Date dt) {
		String sRet = null;
		try {
			if (dt == null)
				return sRet;

			sRet = sdfDateOnly.format(dt).toString();
		} catch (Exception e) {
			e.printStackTrace();
			sRet = null;
		}
		return sRet;
	}

	/**
	 * 利用指定java.util.Date到String
	 * 
	 * @param dt
	 *            java.util.Date instance
	 * @param formatter
	 *            SimpleDateFormat Instance
	 * @return
	 * @since 1.0
	 * @history
	 */
	public static String dateToString(java.util.Date dt) {
		String date = null;
		try {
			if (dt != null) {
				date = String.valueOf(dt);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return date;
	}

	/**
	 * 得到年月日形式的日期
	 * 
	 * @param date
	 * @return
	 */
	public static String getDateString(String date) {
		if (date == null) {
			return null;
		}
		if (date.length() > 10) {
			return date.substring(0, 10);
		} else {
			return date;
		}
	}

	/**
	 * 获取某月的最后一天的日期
	 * 
	 * @param year
	 * @param month
	 * @return
	 */
	public static String getLastDay(int year, int month) {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, year);
		cal.set(Calendar.MONTH, month - 1);
		cal.set(Calendar.DAY_OF_MONTH, 1);
		int value = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
		cal.set(Calendar.DAY_OF_MONTH, value);
		return new SimpleDateFormat("yyyy-MM-dd").format(cal.getTime());
	}

	public static String getLastDay(String year, String month) {
		return getLastDay(Integer.parseInt(year), Integer.parseInt(month));
	}

	/**
	 * 获取某月的第一天的日期
	 * 
	 * @param year
	 * @param month
	 * @return
	 */
	public static String getFirstDay(int year, int month) {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, year);
		cal.set(Calendar.MONTH, month - 1);
		cal.set(Calendar.DAY_OF_MONTH, cal.getMinimum(Calendar.DATE));
		return new SimpleDateFormat("yyyy-MM-dd").format(cal.getTime());
	}

	public static String getFirstDay(String year, String month) {
		return getFirstDay(Integer.parseInt(year), Integer.parseInt(month));
	}

	/**
	 * 判断是否过期
	 */
	public static boolean isBefore(String date) {
		try {
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			java.util.Date lastTime = df.parse(date);
			java.util.Date sysTime = df.parse(getSysDateString());
			long last = lastTime.getTime();
			long sys = sysTime.getTime();
			if (last < sys) {
				return true;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return false;
	}

	/**
	 * 判断是否小于现在时间
	 * 
	 * @param date
	 * @return
	 */
	public static boolean isAfter(String date) {
		try {
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			java.util.Date lastTime = df.parse(date);
			java.util.Date sysTime = df.parse(getSysDateString());
			long last = lastTime.getTime();
			long sys = sysTime.getTime();
			if (last > sys) {
				return true;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return false;
	}

	/**
	 * 判断结束日期是否大于等于开始日期
	 * 
	 * @param date
	 * @return
	 */
	public static boolean isAfter(String beginDate, String endDate) {
		try {
			if (StringUtil.isNull(beginDate) || StringUtil.isNull(endDate))
				return false;
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			java.util.Date lastTime = df.parse(endDate);
			java.util.Date beginTime = df.parse(beginDate);
			long last = lastTime.getTime();
			long begin = beginTime.getTime();
			if (last >= begin) {
				return true;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return false;
	}

	/**
	 * 返回系统时间 格式为yyyy-MM-dd
	 * 
	 * @return
	 */
	public static String getSystemDate() {
		Date currentTime = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		String dateString = formatter.format(currentTime);
		return dateString;
	}

	/**
	 * 获取系统时间
	 * 
	 * @param format
	 *            输出格式
	 * @return
	 */
	public static String getSystemDate(String format) {
		Date currentTime = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat(format);
		String dateString = formatter.format(currentTime);
		return dateString;
	}

	/**
	 * 获取指定月的前一月（年）或后一月（年）
	 * 
	 * @param dateStr
	 * @param addYear
	 * @param addMonth
	 * @param addDate
	 * @return 输入的时期格式为yyyy-MM，输出的日期格式为yyyy-MM
	 * @throws Exception
	 */
	public static String getLastMonth(String dateStr, int addYear, int addMonth, int addDate) throws Exception {
		try {
			java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM");
			java.util.Date sourceDate = sdf.parse(dateStr);
			Calendar cal = Calendar.getInstance();
			cal.setTime(sourceDate);
			cal.add(Calendar.YEAR, addYear);
			cal.add(Calendar.MONTH, addMonth);
			cal.add(Calendar.DATE, addDate);
			java.text.SimpleDateFormat returnSdf = new java.text.SimpleDateFormat("yyyy-MM");
			String dateTmp = returnSdf.format(cal.getTime());
			java.util.Date returnDate = returnSdf.parse(dateTmp);
			return dateTmp;
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception(e.getMessage());
		}
	}

	/**
	 * 获取指定月的前一月（年）或后一月（年）
	 * 
	 * @param dateStr
	 * @param addYear
	 * @param addMonth
	 * @param addDate
	 * @return 输入的时期格式为yyyy-MM-dd，输出的日期格式为yyyy-MM-dd
	 * @throws Exception
	 */
	public static String getLastDay(String dateStr, int addYear, int addMonth, int addDate) throws Exception {
		try {
			java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd");
			java.util.Date sourceDate = sdf.parse(dateStr);
			Calendar cal = Calendar.getInstance();
			cal.setTime(sourceDate);
			cal.add(Calendar.YEAR, addYear);
			cal.add(Calendar.MONTH, addMonth);
			cal.add(Calendar.DATE, addDate);
			java.text.SimpleDateFormat returnSdf = new java.text.SimpleDateFormat("yyyy-MM-dd");
			String dateTmp = returnSdf.format(cal.getTime());
			java.util.Date returnDate = returnSdf.parse(dateTmp);
			return dateTmp;
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception(e.getMessage());
		}
	}

	/**
	 * 当月第一天
	 * 
	 * @return
	 */
	public static String getCurrentFirstDay() {

		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		Calendar calendar = Calendar.getInstance();
		Date theDate = calendar.getTime();

		GregorianCalendar gcLast = (GregorianCalendar) Calendar.getInstance();
		gcLast.setTime(theDate);
		gcLast.set(Calendar.DAY_OF_MONTH, 1);
		String day_first = df.format(gcLast.getTime());
		StringBuffer str = new StringBuffer().append(day_first).append(" 00:00:00");
		return str.toString();
	}

	/**
	 * 两个日期年份差
	 * 
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	public static int getYears(String startDate, String endDate) {
		if (StringUtil.isNull(startDate) || StringUtil.isNull(endDate))
			return 0;

		int sYear = StringUtil.toInt(getYear(startDate));
		int eYear = StringUtil.toInt(getYear(endDate));
		return eYear - sYear;
	}

	/**
	 * 日期加年份
	 * 
	 * @param date
	 * @param y
	 * @return
	 */
	public static String getDatePlusYear(String date, int y) {
		if (StringUtil.isNull(date))
			return "";

		int year = StringUtil.toInt(getYear(date));
		int newMonth = StringUtil.toInt(getMonth(date));
		int day = StringUtil.toInt(getDay(date));
		return (year + y) + "-" + newMonth + "-" + day;
	}

	/**
	 * 日期减年份
	 * 
	 * @param date
	 * @param y
	 * @return
	 */
	public static String getDateMinusYear(String date, int y) {
		if (StringUtil.isNull(date))
			return "";

		int year = StringUtil.toInt(getYear(date));
		int newMonth = StringUtil.toInt(getMonth(date));
		int day = StringUtil.toInt(getDay(date));

		if (year > y) {
			return (year - y) + "-" + newMonth + "-" + day;
		} else {
			return "";
		}
	}

	/**
	 * 获取两个日期之间的月份差
	 * 
	 * @param newDate
	 * @param oldDate
	 * @return
	 */
	public static int getMonths(String newDate, String oldDate) {
		if (StringUtil.isNull(newDate) || StringUtil.isNull(oldDate))
			return 0;
		int newYear = StringUtil.toInt(getYear(newDate));
		int newMonth = StringUtil.toInt(getMonth(newDate));
		int oldYear = StringUtil.toInt(getYear(oldDate));
		int oldMonth = StringUtil.toInt(getMonth(oldDate));
		return (newYear - oldYear) * 12 + newMonth - oldMonth;
	}

	/**
	 * 日期一共月数
	 */
	public static int getMonths(String data) {
		if (StringUtil.isNull(data))
			return 0;
		int year = StringUtil.toInt(getYear(data));
		int month = StringUtil.toInt(getMonth(data));
		return year * 12 + month;
	}

	/**
	 * 获取两个日期之间的天数差
	 * 
	 * @param newDate
	 *            开始日期
	 * @param oldDate
	 *            结束日期
	 * @return
	 */
	public static int getDays(String newDate, String oldDate) {
		int days = 0;
		Date start = getDateFromString(newDate);
		Date end = getDateFromString(oldDate);
		long time = end.getTime() - start.getTime();
		days = (int) (time / 1000 / 60 / 60 / 24);
		return Math.abs(days);
	}

	/**
	 * 获取两个日期之间周六日天数
	 * 
	 * @param newDate
	 * @param oldDate
	 * @return
	 * @throws ParseException
	 */
	public static int getSaturdays(String newDate, String oldDate) throws ParseException {
		int days = getDays(newDate, oldDate);
		String startDate = "";
		int saturDay = 0;
		startDate = isAfter(newDate, oldDate) ? newDate : oldDate;
		for (int i = 0; i <= days; i++) {
			if (isWeekend(dateAddMinus(startDate, i))) {
				saturDay++;
			}
		}
		return saturDay;
	}

	/**
	 * 判断在某个日期阶段内
	 */
	public static boolean betweenDate(String startDate, String endtDate, String date) {
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.CHINA);
			Date dat = sdf.parse(date);
			Date start = sdf.parse(startDate);
			Date end = sdf.parse(endtDate);
			boolean flags = dat.after(start);
			boolean flage = dat.before(end);
			if (start.equals(dat)) {
				return true;
			} else if (end.equals(dat)) {
				return true;
			} else if (flage && flags) {
				return true;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return false;
	}

	/**
	 * 当前日期加减天数
	 * 
	 * @param day
	 *            负数为减
	 * @return
	 * @throws ParseException
	 */
	public static String dateAddMinus(int day) throws ParseException {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Date dd = format.parse(DateUtil.getSystemDate());
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(dd);
		calendar.add(Calendar.DAY_OF_MONTH, day);
		return format.format(calendar.getTime());
	}

	/**
	 * 日期加减天数
	 * 
	 * @param day
	 *            负数为减
	 * @return
	 * @throws ParseException
	 */
	public static String dateAddMinus(String date, int day) throws ParseException {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Date dd = format.parse(date);
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(dd);
		calendar.add(Calendar.DAY_OF_MONTH, day);
		return format.format(calendar.getTime());
	}

	/**
	 * 周六周日判断
	 * 
	 * @param date
	 * @return
	 * @throws ParseException
	 */
	public static boolean isWeekend(String date) throws ParseException {
		boolean weekend = false;
		SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
		long l = f.parse(date).getTime();
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date(l));
		if (cal.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY || cal.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
			weekend = true;
		}
		return weekend;
	}

	/**
	 * 获取下个月日期
	 * 
	 * @return
	 * @throws ParseException
	 */
	public static String getNextMonth() throws ParseException {
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.MONTH, +1);// 得到下一个月
		int year = calendar.get(Calendar.YEAR);
		int month = calendar.get(Calendar.MONTH) + 1;
		int date = calendar.get(Calendar.DATE);
		return year + "-" + month + "-" + date;
	}

	/**
	 * 日期格式转换
	 * 
	 * @param date
	 * @param format
	 * @return
	 */
	public static String formatDate(String date, String formatString) {
		String dateString = date;
		try {
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			Date d = format.parse(date);
			SimpleDateFormat formatter = new SimpleDateFormat(formatString);
			dateString = formatter.format(d);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dateString;

	}

	/**
	 * 获取出生日期， 把年转换成当前年份
	 * 
	 * @param birth
	 *            出生日期
	 * @return
	 */
	public static String birthDate(String birth) {
		try {
			if (StringUtil.notNull(birth)) {
				Date date = getDateUtileString(birth);
				date.setYear(new Date().getYear());
				SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
				String str = format.format(date);
				return str;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;

	}

	/**
	 * 根据身份证转换成出生日期
	 * 
	 * @param idCode
	 *            身份证号
	 * @return
	 */
	public static String birth(String idCode) {
		try {
			if (StringUtil.notNull(idCode)) {
				String year = idCode.substring(6, 10);
				String month = idCode.substring(10, 12);
				String date = idCode.substring(12, 14);
				return year + "-" + month + "-" + date;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 当前时间(月)
	 * 
	 * @return
	 */
	public static String getCurrentMonth() {
		Calendar cal = Calendar.getInstance();
		return cal.get(Calendar.MONTH) + 1 + "";
	}

	/**
	 * 当前时间(日)
	 * 
	 * @return
	 */
	public static String getCurrentDate() {
		Calendar cal = Calendar.getInstance();
		return cal.get(Calendar.DATE) + "";
	}

	/**
	 * 获取几年前的日期
	 * 
	 * @param year
	 *            年数
	 * @param beforeDate
	 *            不能早于该日期
	 * @return
	 */
	public static String getYearBefore(Integer year, String beforeDate) {
		String dayPart = "-01";
		if (beforeDate.length() == 7) {
			beforeDate += dayPart;
		}
		String yearBeforeDate = (StringUtil.toInt(DateUtil.getCurrentYear()) - year.intValue()) + "-" + DateUtil.getCurrentMonth() + dayPart;
		if (StringUtil.notNull(beforeDate)) {
			if (!DateUtil.isAfter(beforeDate, yearBeforeDate)) {
				yearBeforeDate = beforeDate;
			}
		}
		return yearBeforeDate;
	}

	public static String getYearBefore(String yearBeforeDate, String beforeDate) {
		String dayPart = "-01";
		if (beforeDate.length() == 7) {
			beforeDate += dayPart;
		}
		if (StringUtil.notNull(beforeDate) && StringUtil.notNull(yearBeforeDate)) {
			if (!DateUtil.isAfter(beforeDate, yearBeforeDate)) {
				yearBeforeDate = beforeDate;
			}
		}
		return yearBeforeDate;
	}
	
	/**
	 * 计算年龄-精确到年度
	 */
	public static int countAgeByYear(String date){
		String year=getYear(date);
		if(StringUtil.isNull(year)) return 0;
		return Integer.parseInt(getCurrentYear()) - Integer.parseInt(year);
	}
	
	
	/**
	 * 计算年龄-精确到月
	 */
	public static int countAgeByMonth(String date){
		String nowDate = getSystemDate();
		if(StringUtil.isNull(date)) return 0;
		int m = DateUtil.getMonths(nowDate, date);
		return m / 12;
	}

	/**
	 * 计算年龄-精确到天
	 * @param date
	 * @return
	 */
	public static int countAgeByDay(String date){
		String nowDate=getSystemDate();
		// 当前年
		String nowYear = DateUtil.getYear(nowDate);
		String nowMonth = DateUtil.getMonth(nowDate);
		String nowDay = DateUtil.getDay(nowDate);
		if (StringUtil.notNull(date) && DateUtil.isValidFormat(date, "yyyy-MM-dd")) {
			// 出生年
			String thisYear = DateUtil.getYear(date);
			// 出生月
			String thisMonth = DateUtil.getMonth(date);
			String thisDay = DateUtil.getDay(date);
			// 判断日期大小
			boolean afer = DateUtil.isAfter(nowDate, nowYear + "-" + thisMonth + "-" + thisDay);
			int aa = Integer.parseInt(nowYear) - Integer.parseInt(thisYear);
			if (afer) {
				aa = aa - 1;
			}
			return aa;
		}else{
			return 0;
		}		
	}

}
