package com.util;


import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Enumeration;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONArray;
import net.sf.json.JSONException;
import net.sf.json.JSONObject;
import net.sf.json.util.JSONTokener;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

@SuppressWarnings("deprecation")
public class CommonUtil {


	@SuppressWarnings({"resource" })
	public static JSONObject getJson(String url, String action,List<BasicNameValuePair> params) 
			throws ClientProtocolException,IOException, JSONException {
		System.out.println(url + action);
		HttpPost request = new HttpPost(url + action);
		request.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
		// 发送请求
		HttpResponse httpResponse = new DefaultHttpClient().execute(request);
		// 得到应答的字符串，这也是一个 JSON 格式保存的数据
		String retSrc = EntityUtils.toString(httpResponse.getEntity());
		JSONObject json = json(retSrc);
		return json;
	}

	/**
	 * 比较两个字符串日期的天数是否相等
	 * @param date1
	 * @param date2
	 * @return
	 */
	public static boolean comparisonDateForDay(String date1,String date2){
		date1= getDateStringFomart(date1);
		date2= getDateStringFomart(date2);
		return date1.equals(date2);
	}


	/**
	 * 将时间戳转换为时间类型yyyy-mm-dd HH:mm:ss
	 * @param timeStamp
	 * @return
	 */
	public static String time2TimeStamp(String timeStamp){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		long unixLong = 0;
		String date = "";
		try{
			unixLong = Long.parseLong(timeStamp);//时间戳是秒为单位
		}
		catch(Exception ex){
			ex.printStackTrace();
		}
		date = sdf.format(unixLong);
		return date;
	}


	public static JSONObject json(String str) {
		JSONObject json = null;
		JSONTokener js = new JSONTokener(str);
		json = (JSONObject) js.nextValue();
		return json;
	}

	public static List<JSONObject> json(JSONObject js, String key) {
		JSONObject json = null;
		List<JSONObject> li = new ArrayList<JSONObject>();
		JSONArray arr = js.getJSONArray(key);
		for (int i = 0; i < arr.size(); i++) {
			json = arr.getJSONObject(i);
			li.add(json);
		}
		return li;
	}

	/**
	 * 获取完整url连接
	 * @param request
	 * @return
	 */
	public static String getRequestURL(HttpServletRequest request) {
		if (request == null)return "";
		String url = "";
		url = request.getContextPath();
		url = url + request.getServletPath();

		Enumeration<?> names = request.getParameterNames();
		int i = 0;
		if (names != null) {
			while (names.hasMoreElements()) {
				String name = (String) names.nextElement();
				if (i == 0) {
					url = url + "?";
				} else {
					url = url + "&";
				}
				i++;
				String value = request.getParameter(name);
				if (value == null) {
					value = "";
				}
				url = url + name + "=" + value;
			}
		}
		return url;
	}  

	/**
	 * 计算两个日期之间相差的天数
	 * @author ydd
	 * @param time1
	 * @param time2
	 * @return
	 */
	public static long getQuot(String time1, String time2){
		long quot = 0;
		SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd");
		Date date1=new Date() ;Date date2=new Date();
		try {
			date1 = ft.parse( time1 );
			date2 = ft.parse( time2 );
		} catch (ParseException e) {
			e.printStackTrace();
		}
		quot = date1.getTime() - date2.getTime();
		quot = quot / 1000 / 60 / 60 / 24;
		return quot;
	}
	public static long getQuot1(String time1, String time2){
		long quot = 0;
		SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date1=new Date() ;Date date2=new Date();
		try {
			date1 = ft.parse( time1 );
			date2 = ft.parse( time2 );
		} catch (ParseException e) {
			e.printStackTrace();
		}
		quot = date1.getTime() - date2.getTime();
		quot = quot / 1000 / 60 / 60 / 24;
		return quot;
	}
	public static long getQuot2(String time1, String time2){
		try {
			long quot = 0;
			SimpleDateFormat ft = new SimpleDateFormat("yyyy年MM月dd日");
			Date date1=new Date() ;Date date2=new Date();
			date1 = ft.parse( time1 );
			date2 = ft.parse( time2 );
			quot = date1.getTime() - date2.getTime();
			quot = quot / 1000 / 60 / 60 / 24;
			return quot;
		} catch (ParseException e) {
			e.printStackTrace();
			return -1;
		}
	}
	/**
	 * 获取当前时间(yyyy-MM-dd)
	 * @author ydd
	 * @return
	 */
	public static String getDateDay(){
		SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd");
		return ft.format(new Date());
	}
	/**
	 * 获取当前时间(yyyyMMdd)
	 * @author ydd
	 * @return
	 */
	public static String getDateDayFormat2(){
		SimpleDateFormat ft = new SimpleDateFormat("yyyyMMdd");
		return ft.format(new Date());
	}
	/**
	 * 获取当前时间(yyyy年MM月dd日)
	 * @author ydd
	 * @return
	 */
	public static String getDateDayFormat3(){
		SimpleDateFormat ft = new SimpleDateFormat("yyyy年MM月dd日");
		return ft.format(new Date());
	}
	/**
	 * 获取当前时间(yyyy-MM-dd HH:mm:ss)
	 * @author ydd
	 * @return
	 */
	public static String getDateDayTime(){
		SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return ft.format(new Date());
	}


	/**
	 * 格式化当前参数日期
	 * @param fomart
	 * @param date
	 * @return
	 */
	public static String getFomartDate(String fomart,String date){
		SimpleDateFormat ft = new SimpleDateFormat(fomart);
		Date d=null;
		try {
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			d = dateFormat.parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return ft.format(d);
	}
	/**
	 * 格式化日期字符串
	 * @param dataStr 日期字符串
	 * @return (yyyy-MM-dd)
	 */
	public static String getDateStringFomart(String dataStr){
		SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd");
		return ft.format(getDateByStringDate(dataStr));
	}
	/**
	 * 格式化日期字符串
	 * @param dataStr 日期字符串
	 * @return (yyyy-MM-dd)
	 */
	public static Date getDateFomartToString(String dataStr){
		SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd");
		try {
			return ft.parse(dataStr);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}
	/**
	 * 格式化日期字符串
	 * @param date 时间
	 * @return (yyyy-MM-dd HH:mm:ss)
	 */
	public static String getDateTimeStringFomart(Date date){
		SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return ft.format(date);
	}
	/**
	 * 日期字符串装换为Date类型
	 * @param dataStr 日期字符串(格式：yyyyMMdd)
	 * @return Date
	 */
	public static Date getDateByStringDate(String dataStr){
		SimpleDateFormat ft = new SimpleDateFormat("yyyyMMdd");
		try {
			return ft.parse(dataStr);
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 日期字符串装换为Date类型
	 * @param dataStr 日期字符串(格式：yyyy-MM-dd HH:mm:ss)
	 * @return Date
	 */
	public static Date getDateByStringDate2(String dataStr){
		SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			return ft.parse(dataStr);
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 日期字符串装换为Date类型
	 * @param dataStr 日期字符串(格式：yyyy-MM-dd)
	 * @return Date
	 */
	public static Date getDateByStringDate3(String dataStr){
		SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd");
		try {
			return ft.parse(dataStr);
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 计算年龄
	 * @param birthDay
	 * @return
	 * @throws ExceptionString
	 */
	public static String getAge(Date birthDay) {
		Calendar cal = Calendar.getInstance();
		int yearNow = cal.get(Calendar.YEAR);
		int monthNow = cal.get(Calendar.MONTH)+1;
		int dayOfMonthNow = cal.get(Calendar.DAY_OF_MONTH);

		cal.setTime(birthDay);
		int yearBirth = cal.get(Calendar.YEAR);
		int monthBirth = cal.get(Calendar.MONTH);
		int dayOfMonthBirth = cal.get(Calendar.DAY_OF_MONTH);

		int age = yearNow - yearBirth;

		if (monthNow <= monthBirth) {
			if (monthNow == monthBirth) {
				//monthNow==monthBirth
						if (dayOfMonthNow < dayOfMonthBirth) {
							age--;
						}
			} else {
				//monthNow>monthBirth
				age--;
			}
		}
		return age +"";
	}


	/**
	 * 获取随机字符串
	 */
	public static String getUUID() {
		UUID random=UUID.randomUUID();
		return random.toString().replaceAll("-", "");
	}

	/**
	 * 生成6位随即密码
	 * @param args
	 */
	public static String getDefaultPassword() {
		UUID random=UUID.randomUUID();
		String[] randoms=random.toString().split("-");
		return randoms[0];
	}

	/**
	 * 生成订单号
	 * @return
	 */
	public static String getOrderCodeNo(){
		StringBuffer orderCodeNo=new StringBuffer();
		//追加时间字符串
		orderCodeNo.append(getDateDayFormat2());
		//获取uuid
		UUID random=UUID.randomUUID();
		String[] randoms=random.toString().split("-");
		//追加uuid随机串
		orderCodeNo.append(randoms[0]);
		orderCodeNo.append(randoms[1]);
		return orderCodeNo.toString();
	}

	/**
	 * 计算两个时间差
	 * @param startTime
	 * @param endTime
	 * @param format
	 * @param str
	 * @return
	 */
	public static Long dateDiff(String startTime, String endTime,     
			String format, String str) {     
		// 按照传入的格式生成一个simpledateformate对象     
		SimpleDateFormat sd = new SimpleDateFormat(format);     
		long nd = 1000 * 24 * 60 * 60;// 一天的毫秒数     
		long nh = 1000 * 60 * 60;// 一小时的毫秒数     
		long nm = 1000 * 60;// 一分钟的毫秒数     
		long ns = 1000;// 一秒钟的毫秒数     
		long diff;     
		long day = 0;     
		long hour = 0;     
		long min = 0;     
		long sec = 0;     
		// 获得两个时间的毫秒时间差异     
		try {     
			diff = sd.parse(endTime).getTime() - sd.parse(startTime).getTime();     
			day = diff / nd;// 计算差多少天     
			hour = diff % nd / nh + day * 24;// 计算差多少小时     
			min = diff % nd % nh / nm + day * 24 * 60;// 计算差多少分钟     
			sec = diff % nd % nh % nm / ns;// 计算差多少秒     
			// 输出结果     
			System.out.println("时间相差：" + day + "天" + (hour - day * 24) + "小时"    
					+ (min - day * 24 * 60) + "分钟" + sec + "秒。");     
			System.out.println("hour=" + hour + ",min=" + min);     
			if (str.equalsIgnoreCase("h")) {     
				return hour;     
			} else {     
				return min;     
			}     

		} catch (ParseException e) {     
			e.printStackTrace();     
		}     
		if (str.equalsIgnoreCase("h")) {     
			return hour;     
		} else {     
			return min;     
		}     
	} 

	/**
	 * 获取当前参数日期增加space小时后的日期
	 * @param dqDate 当前日期
	 * @param space 要增加的小时
	 * @return
	 */
	public static String dateAddHour(String dqDate,Integer space){
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date=null;
		try {
			date = sdf.parse(dqDate);
			Calendar ca=Calendar.getInstance();
			ca.setTime(date);
			ca.add(Calendar.HOUR_OF_DAY, space);
			return sdf.format(ca.getTime());
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 获取当前参数日期增加space天后的日期
	 * @param dqDate 当前日期
	 * @param space 要增加的天数
	 * @return
	 */
	public static String dateAddDay(String dqDate,Integer space){
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date=null;
		try {
			date = sdf.parse(dqDate);
			Calendar ca=Calendar.getInstance();
			ca.setTime(date);
			ca.add(Calendar.DAY_OF_MONTH, space);
			return sdf.format(ca.getTime());
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}

	
	/**
	 * 获取当前月第一天
	 * @return
	 */
	public static String getMfirstDay() {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd"); 
		Calendar cal_1=Calendar.getInstance();//获取当前日期
		cal_1.set(Calendar.DAY_OF_MONTH,1);//设置为1号,当前日期既为本月第一天
		String firstDay = format.format(cal_1.getTime());
		return firstDay;
	}
}

