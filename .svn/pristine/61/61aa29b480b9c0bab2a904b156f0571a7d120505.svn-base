package com.util;

import java.io.InputStream;
import java.util.Properties;



public class PropertiesUtil {

	public static String readValue(String key) {
		Properties props = new Properties();
		try {
			 InputStream in = PropertiesUtil.class.getClassLoader().getResourceAsStream(
					    "email.properties");
			props = new Properties();
			props.load(in);
			String value = props.getProperty (key);
			return value;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static String readServerConfigValue(String key) {
		Properties props = new Properties();
		try {
			 InputStream in = PropertiesUtil.class.getClassLoader().getResourceAsStream(
					    "serverConfig.properties");
			props = new Properties();
			props.load(in);
			String value = props.getProperty (key);
			return value;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static void main(String[] args) {
		System.out.println(readValue("HwCloud_Key"));
	}
}
