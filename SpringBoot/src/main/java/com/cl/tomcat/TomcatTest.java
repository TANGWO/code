package com.cl.tomcat;

import org.apache.catalina.LifecycleException;
import org.apache.catalina.core.StandardContext;
import org.apache.catalina.startup.Tomcat;
import org.apache.catalina.startup.Tomcat.FixContextListener;

import com.cl.servlert.MyServlert;
/***
 * 简易版TOMCAT实现
 * @author chenl
 *
 */
public class TomcatTest {
    
	
	private static int PORT =9090;
	
	private static String CONTEXT_PATH = "/SpringBoot";
	
	private static String SERVLERT_NAME ="MyServlert";
	
	public static void main(String [] args) throws LifecycleException {
		
		//创建tomcat实例
		Tomcat tomcat = new Tomcat();
		//设置端口号
		tomcat.setPort(PORT);
		//设置tomcat自启动
		tomcat.getHost().setAutoDeploy(false);
		//创建上下文
		StandardContext  context = new StandardContext();
		//设置上下文路径
		context.setPath(CONTEXT_PATH);
		//禁止重新载入
		context.setReloadable(false);
		//设置tomcat监听，用于监听上下文
		context.addLifecycleListener(new FixContextListener());
		//将上下问添加在tomcat容器里
		tomcat.getHost().addChild(context);
		
		//创建servlert
		tomcat.addServlet(CONTEXT_PATH, SERVLERT_NAME, new MyServlert());
		//servlert映射
		context.addServletMappingDecoded("/index", SERVLERT_NAME);
		//启动tomcat
		tomcat.start();
		//打印日志
		System.out.println("Tomcat已经启动。。。。。。");
		System.out.println("Tomcat启动完成！");
		//异步获取tomcat服务
		tomcat.getServer().await();
		
	}
}
