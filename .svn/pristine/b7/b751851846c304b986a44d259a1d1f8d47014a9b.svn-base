package com.util;

import java.io.UnsupportedEncodingException;
import java.util.zip.CRC32;

public class StringCRCTransform {
	private static CRC32 crc = new CRC32();
	
	public static String CRCTransformToHex (String str){
		String result = null;
		try {
			crc.reset();
			crc.update(36);
			crc.update(str.getBytes("UTF-8"));
			result = Long.toHexString(crc.getValue());
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public static char charSum (String str){
		char re = 0;
		for(char ch : str.toCharArray())
			re+=ch;
		return re;
	}
	
	public static String charSumToHex (String str){
		return Integer.toHexString(charSum(str));
	}
	
	public static String getLastcharToHex (String str){
		return Integer.toHexString(str.charAt(str.length() - 1));
	}
	
	public static String getCRCfilename (String str){
		String re = String.format("%s%s%s", CRCTransformToHex(str) , charSumToHex(str) , getLastcharToHex(str));
		return re;
	}
}
