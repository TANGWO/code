package com.cl.house.common.utils;

import java.nio.charset.Charset;

import com.google.common.hash.HashCode;
import com.google.common.hash.HashFunction;
import com.google.common.hash.Hashing;

public class HashUtils {
	
	private static final HashFunction FUNCTION =  Hashing.md5();
	private static final String SALT ="com.cl.house";
	
	public static String encryPassword(String password) {
		HashCode hashCode = FUNCTION.hashString(password, Charset.forName("UTF-8"));
		
		return 	hashCode.toString(); 	
	}

}
