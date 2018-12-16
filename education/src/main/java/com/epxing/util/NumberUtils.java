package com.epxing.util;

import java.math.BigDecimal;

public class NumberUtils {

	private NumberUtils() {

	}

	public static double add(double v1, double v2) {
		BigDecimal b1 = new BigDecimal(v1);
		BigDecimal b2 = new BigDecimal(v2);
		return b1.add(b2).doubleValue();
	}

	public static double subtract(double v1, double v2) {
		BigDecimal b1 = new BigDecimal(Double.toString(v1));
		BigDecimal b2 = new BigDecimal(Double.toString(v2));
		return b1.subtract(b2).doubleValue();
	}

	public static double multiply(double v1, double v2) {
		BigDecimal b1 = new BigDecimal(v1);
		BigDecimal b2 = new BigDecimal(v2);
		return b1.multiply(b2).doubleValue();
	}

	public static double multiply(double v1, double v2, int scale) {
		if (scale < 0) {
			throw new IllegalArgumentException("The scale must be a positive integer or zero");
		}
		return round(multiply(v1,v2), scale);
	}

	public static double divide(double v1, double v2) {
		BigDecimal b1 = new BigDecimal(v1);
		BigDecimal b2 = new BigDecimal(v2);
		return b1.divide(b2).doubleValue();
	}

	public static double divide(double v1, double v2, int scale) {
		if (scale < 0) {
			throw new IllegalArgumentException("The scale must be a positive integer or zero");
		}
		BigDecimal b1 = new BigDecimal(v1);
		BigDecimal b2 = new BigDecimal(v2);
		return b1.divide(b2,scale,BigDecimal.ROUND_HALF_UP).doubleValue();
	}

	public static double round(double v, int scale) {
		if (scale < 0) {
			throw new IllegalArgumentException("The scale must be a positive integer or zero");
		}
		BigDecimal b = new BigDecimal(String.valueOf(v));
		return b.setScale(scale, BigDecimal.ROUND_HALF_UP).doubleValue();
	}
	/**
	 * 保留位数
	 * @param v  
	 * @param scale  保留几位小数
	 * @param type
	 *            0：始终对非零舍弃部分前面的数字加1 例如1.13=2 
	 *            1：从不对舍弃部分前面的数字加1，即截短 例如1.13=1
	 *            2: 1.13=2为正 -1.13=-1为负
	 *            3: 1.13=1为正 -1.13=-2为负
	 *            4：四舍五入求整数 只计算小数点后一位
	 *            5：如果舍弃部分 > 0.5 四舍五入 0.5 = 0 0.51=1
	 * @return
	 */
	public static double round(double v, int scale,int type) {
		if (scale < 0) {
			throw new IllegalArgumentException("The scale must be a positive integer or zero");
		}
		BigDecimal b = new BigDecimal(String.valueOf(v));
		return b.setScale(scale, type).doubleValue();
	}

	/**
	 * 四舍五入求整数 只计算小数点后一位
	 * 
	 * @param doubleValue
	 * @param scale
	 * @return
	 */
	public static int roundInt(Double doubleValue) {
		String text = doubleValue.toString();
		BigDecimal bd = new BigDecimal(text).setScale(0, BigDecimal.ROUND_HALF_UP);
		// flag=bd.intValue();
		return bd.intValue();
	}

	/**
	 * 根据类型取值
	 * 
	 * @param doubleValue
	 *            待取值
	 * @param type
	 *            0：始终对非零舍弃部分前面的数字加1 例如1.13=2 
	 *            1：从不对舍弃部分前面的数字加1，即截短 例如1.13=1
	 *            2: 1.13=2为正 -1.13=-1为负
	 *            3: 1.13=1为正 -1.13=-2为负
	 *            4：四舍五入求整数 只计算小数点后一位
	 *            5：如果舍弃部分 > 0.5 四舍五入 0.5 = 0 0.51=1
	 * @return
	 */
	public static int roundIntByType(Double doubleValue, int type) {
		String text = doubleValue.toString();
		BigDecimal bd = new BigDecimal(text).setScale(0, type);
		return bd.intValue();
	}
}
