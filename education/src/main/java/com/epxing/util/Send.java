package com.epxing.util;

import org.apache.http.HttpVersion;
import org.apache.http.client.HttpClient;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.params.ConnManagerParams;
import org.apache.http.conn.params.ConnPerRouteBean;
import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.params.HttpProtocolParams;
import org.apache.http.protocol.HTTP;

public class Send {
	public static HttpClient client;

	public static final String APPLICATION_JSON = "application/json";

	public static final String CONTENT_TYPE_TEXT_JSON = "text/json";
	/**
	 * 定义client使用的字符集
	 */
	public final static String CHARSET = HTTP.UTF_8;

	/**
	 * 最大连接数
	 */
	public final static int MAX_TOTAL_CONNECTIONS = 400;

	/**
	 * 获取连接的最大等待时间
	 */
	public final static int HTTP_CLIENT_WAIT_TIMEOUT = 10000;

	/**
	 * 每个路由最大连接数
	 */
	public final static int MAX_ROUTE_CONNECTIONS = 200;

	/**
	 * 连接超时时间
	 */
	public final static int CONNECT_TIMEOUT = 40000;

	/**
	 * 读取超时时间
	 */
	public final static int READ_TIMEOUT = 4000;

	/** emc服务地址 */
	public static String SERVER_URL = "";

	static {
		if (client == null) {

			HttpParams params = new BasicHttpParams();
			ConnManagerParams.setMaxTotalConnections(params, MAX_TOTAL_CONNECTIONS);
			HttpProtocolParams.setVersion(params, HttpVersion.HTTP_1_1);
			HttpProtocolParams.setContentCharset(params, CHARSET);
			HttpProtocolParams.setUseExpectContinue(params, true);
			ConnManagerParams.setTimeout(params, HTTP_CLIENT_WAIT_TIMEOUT);
			HttpConnectionParams.setConnectionTimeout(params, CONNECT_TIMEOUT);
			HttpConnectionParams.setSoTimeout(params, READ_TIMEOUT);
			ConnPerRouteBean connPerRoute = new ConnPerRouteBean(MAX_ROUTE_CONNECTIONS);
			ConnManagerParams.setMaxConnectionsPerRoute(params, connPerRoute);

			SchemeRegistry schReg = new SchemeRegistry();
			schReg.register(new Scheme("http", PlainSocketFactory.getSocketFactory(), 80));
			schReg.register(new Scheme("https", SSLSocketFactory.getSocketFactory(), 443));

			ClientConnectionManager conMgr = new ThreadSafeClientConnManager(params, schReg);

			client = new DefaultHttpClient(conMgr, params);

		}
	}
}
