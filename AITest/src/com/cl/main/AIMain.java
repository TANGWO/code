package com.cl.main;

import java.util.Scanner;


/**
 *  AI���ĳ��򣬹�ֵ1����
 *  @author ������
 *  2018��12��14�� ����10:27:50
 *
 */
public class AIMain {
	
	
	/*@SuppressWarnings("resource")
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		String str;
		while(true) {
			str=scanner.next();
			str=str.replace( "��",  "");
			str=str.replace( "��",  "��");
			//str=str.replace( "��",  "��");
			str=str.replace( "?", "!");
			str=str.replace( "��", "!");
			str=str.replace( "? ", "!");
			str=str.replace( " ?", "!");
			System.out.println(str);
		}
	}
	*/
	
	
	/**
	 * 
	* @Title: a1  
	* @Description: TODO(������һ�仰�����������������)  
	* @param     ����  
	* @return void    ��������  
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
	* @Description: TODO(������һ�仰�����������������)  
	* @param @param a    ����  
	* @return void    ��������  
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