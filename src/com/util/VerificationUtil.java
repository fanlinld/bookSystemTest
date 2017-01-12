package com.util;

import java.util.Arrays;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import com.util.MD5;





public class VerificationUtil {
	

	
	private static String sortParam(Map<String, String> params) {
		String[] attr = new String[params.size()];
		int size = 0;
		Set<String> key = params.keySet();
		for (Iterator<String> it = key.iterator(); it.hasNext();) {
			attr[size] = (String) it.next();
			size++;
		}
		Arrays.sort(attr);// 按参数名升序
		// 参数字符串拼接
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < attr.length; i++) {
			sb.append(attr[i]).append(params.get(attr[i]));
		}
		String paramStr = sb.toString();
		return paramStr;
	}


	private static String encrypt(String paramStr, String secret) {
		String resultStr = "";
		StringBuffer sb = new StringBuffer();
		sb.append(paramStr).append(secret);
		resultStr = sb.toString();
		System.out.println(resultStr);
		resultStr =MD5.MD5Encode(resultStr);
		return resultStr;
	}
	
	public static String getMD5Str(Map<String, String> map, String secret) {
		String paramStr = sortParam(map);
		String MD5Str = encrypt(paramStr, secret);
		return MD5Str;
	}
	

	public static boolean verify(Map<String, String> map, String secret,
			String signature) {
		String paramStr = sortParam(map);
		String MD5Str = encrypt(paramStr, secret);
		if (MD5Str.equals(signature))
			return true;
		return false;
	}
	
	
	
}
