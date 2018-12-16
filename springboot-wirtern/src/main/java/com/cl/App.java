package com.cl;

import java.io.File;

import javax.servlet.ServletException;

import org.apache.catalina.LifecycleException;
import org.apache.catalina.WebResourceRoot;
import org.apache.catalina.core.StandardContext;
import org.apache.catalina.startup.Tomcat;
import org.apache.catalina.webresources.DirResourceSet;
import org.apache.catalina.webresources.StandardRoot;

public class App {
	
	public static void  main(String [] args) throws ServletException, LifecycleException {
		start();
	}
	
	public static void start() throws ServletException, LifecycleException {
		// ����Tomcat����
		Tomcat tomcatServer = new Tomcat();
		// �˿ں�����
		tomcatServer.setPort(9090);
		// ��ȡ��Ŀ·�� ���ؾ�̬��Դ
		StandardContext ctx = (StandardContext) tomcatServer.addWebapp("/", new File("src/main").getAbsolutePath());
		// ��ֹ��������
		ctx.setReloadable(false);
		// class�ļ���ȡ��ַ
		File additionWebInfClasses = new File("target/classes");
		// ����WebRoot
		WebResourceRoot resources = new StandardRoot(ctx);
		// tomcat�ڲ���ȡClassִ��
		resources.addPreResources(
				new DirResourceSet(resources, "/WEB-INF/classes", additionWebInfClasses.getAbsolutePath(), "/"));
		tomcatServer.start();
		// �첽�ȴ�����ִ��
		tomcatServer.getServer().await();
		
		
	}
}
