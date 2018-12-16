package com.cl.main;

import java.util.Scanner;


/**
 *  AI核心程序，估值1个亿
 *  @author 臣不二
 *  2018年12月14日 上午10:27:50
 *
 */
public class AIMain {
	
	
	/*@SuppressWarnings("resource")
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		String str;
		while(true) {
			str=scanner.next();
			str=str.replace( "吗",  "");
			str=str.replace( "你",  "我");
			//str=str.replace( "我",  "你");
			str=str.replace( "?", "!");
			str=str.replace( "？", "!");
			str=str.replace( "? ", "!");
			str=str.replace( " ?", "!");
			System.out.println(str);
		}
	}
	*/
	
	
	/**
	 * 
	* @Title: a1  
	* @Description: TODO(这里用一句话描述这个方法的作用)  
	* @param     参数  
	* @return void    返回类型  
	* @throws
	 */
	public static  void a1 () {
		int a =0;
		 b1(a);
		System.out.println(a);
		
	}
	
	/**
	 * 
	* @Title: b1  
	* @Description: TODO(这里用一句话描述这个方法的作用)  
	* @param @param a    参数  
	* @return void    返回类型  
	* @throws
	 */
	public  static void  b1(int a) {
		int b=10;
		for(int i=0;i<b;i++) {
			a++;
		}
	}
	
	
	public static void main(String[] args) {
		a1();
	}

}
