package com.util;


/**
 * 作者：ydd
 * 时间：2011-03-28
 * 功能：json格式和java对象的转换
 */
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

import net.sf.json.JSONArray;
import net.sf.json.JSONException;
import net.sf.json.JSONObject;
import net.sf.json.util.JSONTokener;

public class JsonUtil {

	
	public static JSONObject getJson(String url, String action,
			List<BasicNameValuePair> params) throws ClientProtocolException,
			IOException, JSONException {
		System.out.println(url + action + params.toString());
		// System.out.println(params.toString());
		HttpPost request = new HttpPost(url + action);
		request.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
		// 发送请求
		HttpResponse httpResponse = new DefaultHttpClient().execute(request);
		// 得到应答的字符串，这也是一个 JSON 格式保存的数据
		String retSrc = EntityUtils.toString(httpResponse.getEntity());
		
		 System.out.println(".........................." + retSrc);
		JSONObject json = Util.json(retSrc);
		return json;
	}

	public static JSONArray getJsonArray(String url, String action,
			List<BasicNameValuePair> params) throws ClientProtocolException,
			IOException, JSONException {
		System.out.println(url + action);
		// System.out.println(params.toString());
		HttpPost request = new HttpPost(url + action);
		request.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
		// 发送请求
		HttpResponse httpResponse = new DefaultHttpClient().execute(request);
		// 得到应答的字符串，这也是一个 JSON 格式保存的数据
		String retSrc = EntityUtils.toString(httpResponse.getEntity());
		// System.out.println(".........................." + retSrc);
		JSONArray json = jsonArray(retSrc);
		return json;
	}
	
	public static JSONArray jsonArray(String str) {
		JSONArray json = null;
		JSONTokener js = new JSONTokener(str);
		json = (JSONArray) js.nextValue();
		return json;
	}
	
	/**
	 * 将json字符串格式转换为对象
	 * @param args
	 */
	@SuppressWarnings({ "static-access", "rawtypes" })
	public static Object getJsonToObject(String jsonstr,Class pojo){
		Object obj;
		JSONObject jsonObject=JSONObject.fromObject(jsonstr);
		obj=jsonObject.toBean(jsonObject, pojo);
		return obj;
	}
	
	/**
	 * 将json字符串转换为map
	 * @param args
	 */
	
	@SuppressWarnings({ "rawtypes" })
	public static Map<String,String> getJsonToMap(String jsonstr){
		JSONObject jsonObject=JSONObject.fromObject(jsonstr);
		Iterator  it=jsonObject.keys();
		String keys=null;
		String obj=null;
		Map<String,String> map=new HashMap<String,String>();
		while(it.hasNext()){
			keys=(String)it.next();
			obj=jsonObject.getString(keys);
			map.put(keys, obj);
		}
		return map;
	}
	/**
	 * 将json的字符串转换为list对象
	 */
	@SuppressWarnings({ "unchecked", "static-access", "rawtypes" })
	public static List getJsonToList(String jsonstr,Class clas){
		JSONArray arry=JSONArray.fromObject(jsonstr);
		JSONObject jsonObj=null;
		Object obj=null;
		ArrayList list=new ArrayList();
		for (int i = 0; i < arry.size(); i++) {
			jsonObj=arry.getJSONObject(i);
			obj=jsonObj.toBean(jsonObj, clas);
			list.add(obj);
		}
		return list;
	}
	/**
	 *将java对象转换成json
	 */
	public static String getObjectToJsonString(Object object){
		String str=null;
		JSONObject jsonObject=JSONObject.fromObject(object);
		str=jsonObject.toString();
		return str;
		
	}
	/**
	 * 写json文件
	 * @author 殷冬冬
	 * @param context 要输入json文件的类容
	 * @param path    json文件路径
	 * @return 是否执行成功
	 * @date 2011-10-29 15:59:17
	 * @throws IOException
	 */
	public static boolean jsonFileWrite(String context, String path)throws IOException {
		BufferedWriter utput = null;
		try {
			java.io.FileOutputStream writerStream = new java.io.FileOutputStream(path); 
			utput = new BufferedWriter(new java.io.OutputStreamWriter(writerStream, "UTF-8"));  
			utput.write(context);
			return true;
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			utput.close();
		}
		return false;
	}

	/**
	 * 写入数据
	 * @author 殷冬冬
	 * @date 2011-10-17 15:21
	 */
	public static void WriteData(String path) {
		try {
			File file = new File(path);
			file.mkdirs();
			if (file.exists()) {
				file.delete();
			}
			file.createNewFile();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
	/**
	 * 将map格式转换为对象
	 * @author fl
	 */
	@SuppressWarnings({ "static-access", "rawtypes" })
	public static Object getMapToObject(HashMap<String, String> map,Class pojo){
		Object obj;
		JSONObject jsonObject=JSONObject.fromObject(map);
		obj=jsonObject.toBean(jsonObject, pojo);
		return obj;
	}
	

}
