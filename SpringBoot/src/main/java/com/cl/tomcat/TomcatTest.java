package com.cl.tomcat;

import org.apache.catalina.LifecycleException;
import org.apache.catalina.core.StandardContext;
import org.apache.catalina.startup.Tomcat;
import org.apache.catalina.startup.Tomcat.FixContextListener;

import com.cl.servlert.MyServlert;
/***
 * ���װ�TOMCATʵ��
 * @author chenl
 *
 */
public class TomcatTest {
    
	
	private static int PORT =9090;
	
	private static String CONTEXT_PATH = "/SpringBoot";
	
	private static String SERVLERT_NAME ="MyServlert";
	
	public static void main(String [] args) throws LifecycleException {
		
		//����tomcatʵ��
		Tomcat tomcat = new Tomcat();
		//���ö˿ں�
		tomcat.setPort(PORT);
		//����tomcat������
		tomcat.getHost().setAutoDeploy(false);
		//����������
		StandardContext  context = new StandardContext();
		//����������·��
		context.setPath(CONTEXT_PATH);
		//��ֹ��������
		context.setReloadable(false);
		//����tomcat���������ڼ���������
		context.addLifecycleListener(new FixContextListener());
		//�������������tomcat������
		tomcat.getHost().addChild(context);
		
		//����servlert
		tomcat.addServlet(CONTEXT_PATH, SERVLERT_NAME, new MyServlert());
		//servlertӳ��
		context.addServletMappingDecoded("/index", SERVLERT_NAME);
		//����tomcat
		tomcat.start();
		//��ӡ��־
		System.out.println("Tomcat�Ѿ�����������������");
		System.out.println("Tomcat������ɣ�");
		//�첽��ȡtomcat����
		tomcat.getServer().await();
		
	}
}
