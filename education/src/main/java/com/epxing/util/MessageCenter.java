package com.epxing.util;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;


import lombok.extern.slf4j.Slf4j;
import net.sf.json.JSONObject;


@Slf4j
public class MessageCenter {
	
public JSONObject sendSMS(String []  phone,String password,String realName) throws Exception, UnsupportedEncodingException {
		
		String appId = "shiwuId";    
		String appKey = "shiwuKey"; 
	    String url = "http://172.22.43.132:8888/"+"restful/sendmsg";  ;
        
		Map<String, Object> msgBody = new HashMap<String, Object>();
		msgBody.put("title", "南开大学找回密码短信通知");
		msgBody.put("isRich", "0"); 
		msgBody.put("content", "亲爱的"+realName+"先生/女士,您正再使用密码找回功能,您的密码已经重置，请使用新密码登录。您的密码是:"+password); //发送内容
		msgBody.put("channels", "ydxy-mobile"); 
		
		
		msgBody.put("channelIds", "9063be90-aa2c-4ed2-b4e8-26d9033fcb42"); 
		msgBody.put("isCron", "0"); 
		msgBody.put("isForce", "1"); 
		msgBody.put("typecode", "A001"); 

		List<String> intReceiver = new ArrayList<String>();
		msgBody.put("intReceiver", intReceiver); 
		Map<String, Object> extReceiver = new HashMap<String, Object>();
		extReceiver.put("ydxy-mobile",  phone);
		
		 
		msgBody.put("extReceiver", extReceiver);
		Map<String, Object> sendMap = new HashMap<String, Object>();
		sendMap.put("accountID", appId);
		sendMap.put("accountKey", appKey);
		sendMap.put("msgJson", msgBody);
		
		String postData = JSONObject.fromObject(msgBody).toString();
		String encoderJson = URLEncoder.encode(postData, HTTP.UTF_8);
		url = url + "?accountID=" + appId + "&accountKey=" + appKey + "&msgJson=" + encoderJson;
		String jsonResult = sendToEmc(url);
		log.info("返回结果：" + jsonResult);
		JSONObject obj = JSONObject.fromObject(jsonResult);
		
		return obj;

	}



public String sendToEmc(String url) {
	String jsonResult = null;
	HttpPost httpPost = null;
	HttpResponse result = null;
	try {
		httpPost = new HttpPost(url);
		httpPost.addHeader(HTTP.CONTENT_TYPE, Send.APPLICATION_JSON);
		result = Send.client.execute(httpPost);
		url = URLDecoder.decode(url, "UTF-8");
		/** 请求发送成功，并得到响应 **/
		log.info("MsgCenter Test StatusLine [" + result.getStatusLine() + "] & Entity [" + result.getEntity() + "]");
		if (result.getStatusLine().getStatusCode() == 200) {
			String str = "";
			try {
				/** 读取服务器返回过来的json字符串数据 **/
				str = EntityUtils.toString(result.getEntity());
				jsonResult = str;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	} catch (Exception e) {
		if (httpPost != null) {
			httpPost.abort();
		}
		log.error(e.getMessage());
		e.printStackTrace();
	} finally {
		if (result != null) {
			EntityUtils.consumeQuietly(result.getEntity());
		}

		if (httpPost != null) {
			httpPost.releaseConnection();
		}

	}
	return jsonResult;
}

	

}
