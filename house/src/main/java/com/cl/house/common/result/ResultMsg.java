package com.cl.house.common.result;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.jetty.util.UrlEncoded;

import com.google.common.base.Joiner;
import com.google.common.collect.Maps;

import lombok.Data;

@Data
public class ResultMsg {
	
	public final String errorMsgKey="errorMsg";
	public final String successMsgKey="successMsg";
	private String errorMsg;
	private String successMsg;
	
	public boolean isSuccess(){
		return errorMsg == null ;
	}

	public static ResultMsg errorMsg(String msg) {
		ResultMsg  resultMsg = new ResultMsg();
		resultMsg.setErrorMsg(msg);
		return resultMsg;
	}
	
	public static ResultMsg successMsg(String msg) {
		ResultMsg  resultMsg = new ResultMsg();
		resultMsg.setSuccessMsg(msg);
		return resultMsg;
	}
	
	public Map<String, String> asMap(){
		Map<String, String> map = new HashMap<>();
		map.put(successMsgKey, successMsg);
		map.put(errorMsgKey, errorMsg);
		return map;
	}
	
	public String asUrlParams() {
		
		Map<String,String> map = asMap();
		Map<String,String> newMap = Maps.newHashMap();
		map.forEach((k,v) -> {if(v!=null)
			try {
				newMap.put(k, newMap.put(k, URLEncoder.encode(v,"utf-8")));
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}});
 		
		return Joiner.on("&").useForNull("").withKeyValueSeparator("=").join(newMap);
	}

}
