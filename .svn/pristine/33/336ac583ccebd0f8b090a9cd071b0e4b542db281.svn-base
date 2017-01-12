package com.util;

import java.net.URI;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
@SuppressWarnings("deprecation")
public class HttpClientUtil {

	private static HttpClient hc = new DefaultHttpClient();

	/**
	 * Get请求
	 * 
	 * @param url
	 * @param params
	 * @return
	 */
	public static String get(String url, List<NameValuePair> params)
			throws Exception {
		String body = null;
		// Get请求
		HttpGet httpget = new HttpGet(url);
		// 设置参数
		String str = EntityUtils.toString(new UrlEncodedFormEntity(params));
		httpget.setURI(new URI(httpget.getURI().toString() + "?" + str));
		// 发送请求
		HttpResponse httpresponse = hc.execute(httpget);
		// 获取返回数据
		HttpEntity entity = httpresponse.getEntity();
		body = EntityUtils.toString(entity);
		if (entity != null) {
			entity.consumeContent();
		}
		return body;
	}

	/**
	 * // Post请求
	 * 
	 * @param url
	 * @param params
	 * @return
	 */
	public static String post(String url, List<NameValuePair> params)
			throws Exception {
		String body = null;
		// Post请求
		HttpPost httppost = new HttpPost(url);
		// 设置参数
		UrlEncodedFormEntity req = new UrlEncodedFormEntity(params,"utf-8");
		
		httppost.setEntity(req);
		// 发送请求
		HttpResponse httpresponse = hc.execute(httppost);
		// 获取返回数据
		HttpEntity entity = httpresponse.getEntity();
		body = EntityUtils.toString(entity);
		if (entity != null) {
			entity.consumeContent();
		}
		return body;
	}
}
