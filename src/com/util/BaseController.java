package com.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.Map;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

/**
 * controller 基类
 * @author ydd
 *
 */
public class BaseController {

	/**
	 * 输出response信息（JSON格式）
	 * @param obj
	 * @param response
	 * @throws Exception
	 */
	public static void responseJson(Object obj,HttpServletResponse response){
		response.setContentType("application/json");
		response.setContentType("text/html");
		response.setCharacterEncoding("utf-8");
		PrintWriter pw = null;
		try {
			pw = response.getWriter();
			if(obj==null){
				pw.write(new JSONObject().toString());
			}else{
				pw.write(obj.toString());
			}
		}catch (IOException e) {
			e.printStackTrace();
		}finally{
			pw.flush();
			pw.close();
		}
	}

	/**
	 * 获取服务级别名称
	 * @param startItem 主服务名称
	 * @param n 服务级别
	 * @param request
	 * @return 返回子级别服务名称或参数
	 */
	public static String getUriNode(String startItem, int n, HttpServletRequest request) {
		String node = null;
		String template = "/" + startItem + "/";
		String uri = request.getRequestURI();
		int startPos = uri.indexOf(template);
		if (startPos >= 0) {
			uri = uri.substring(startPos + template.length());
			if (!uri.isEmpty()) {
				String [] splitList = uri.split("/");
				if (n >= 0 && n < splitList.length)
					node = splitList[n];
			}
		}
		return (node);
	}

	/**
	 * 设置查询参数
	 * @param appObj
	 */
	public static JSONObject setQueryParams(HttpServletRequest request) {
		JSONObject queryParamJSON=new JSONObject();
		Enumeration<?> paramEnum = request.getParameterNames();
		while (paramEnum.hasMoreElements()) {
			String param = paramEnum.nextElement().toString();
			String[] values =  request.getParameterValues(param);
			//if (values.length > 0 && (values.length > 1 || !values[0].isEmpty())) {
				queryParamJSON.put(param, values[0]);
			//}
		}
		return queryParamJSON;
	}
	
	/**
	 * 获取请求GET POST参数
	 * @param request
	 */
	@SuppressWarnings("unchecked")
	public static JSONObject getParam(HttpServletRequest request){
		JSONObject param=new JSONObject();
        Map<String, String[]> params = request.getParameterMap();  
        for (String key : params.keySet()) {  
            String[] values = params.get(key);  
            for (int i = 0; i < values.length; i++) {  
                String value = values[i];  
                param.put(key, value);
            }  
        }
        return param;
    }  

	/**
	 * 获取post类型参数
	 * @return
	 */
	public static String getPostParam(HttpServletRequest request){
		BufferedReader br=null;
		StringBuilder sb =null;
		try {
			br = new BufferedReader(new InputStreamReader((ServletInputStream)request.getInputStream(),"utf-8"));
			String line = null; 
			sb = new StringBuilder(); 
			while((line = br.readLine())!=null){ 
				sb.append(line); 
			}
		} catch (IOException e) {
			e.printStackTrace();
		} 
		return sb.toString();
	}

	/**
	 * 获取用户的IP地址
	 * 
	 * @param request
	 * @return
	 */
	public static String getRemortIP(HttpServletRequest request) {
		String ip = request.getHeader("x-forwarded-for");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		return ip;
	}

	public static String getMacAddressIP(String remotePcIP) {
		String str = "";
		String macAddress = "";
		try {
			Process pp = Runtime.getRuntime().exec("nbtstat -A " + remotePcIP);
			InputStreamReader ir = new InputStreamReader(pp.getInputStream());
			LineNumberReader input = new LineNumberReader(ir);
			for (int i = 1; i < 100; i++) {
				str = input.readLine();
				if (str != null) {
					if (str.indexOf("MAC Address") > 1) {
						macAddress = str.substring(
								str.indexOf("MAC Address") + 14, str.length());
						break;
					}
				}
			}
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		return macAddress;
	}
}
