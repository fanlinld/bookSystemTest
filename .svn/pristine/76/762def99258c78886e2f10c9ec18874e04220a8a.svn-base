package com.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.jdom.Document;
import org.jdom.output.Format;
import org.jdom.output.XMLOutputter;

import com.sc.convert.util.Base64;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.util.JSONTokener;

@SuppressWarnings("deprecation")
public class Util {

	@SuppressWarnings("resource")
	public static JSONObject LinkInfo(String ipaddress) {
		JSONObject json = null;
		try {
			String url = "http://int.dpool.sina.com.cn/iplookup/iplookup.php?format=json&ip="
					+ ipaddress;
			HttpGet request = new HttpGet(url);
			HttpResponse httpResponse = new DefaultHttpClient()
					.execute(request);
			// 得到应答的字符串，这也是一个 JSON 格式保存的数据
			String retSrc = EntityUtils.toString(httpResponse.getEntity());
			json = Util.json(retSrc);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return json;
	}

	public static String escape(String src) {
		int i;
		char j;
		if (null == src)
			return "";
		StringBuffer tmp = new StringBuffer();
		tmp.ensureCapacity(src.length() * 6);
		for (i = 0; i < src.length(); i++) {
			j = src.charAt(i);
			if (Character.isDigit(j) || isLetter(j))
				tmp.append(j);
			else if (j < 256) {
				tmp.append("%");
				if (j < 16)
					tmp.append("0");
				tmp.append(Integer.toString(j, 16));
			} else {
				tmp.append("%u");
				if (Integer.toString(j, 16).length() == 3)
					tmp.append("0" + Integer.toString(j, 16));
				else
					tmp.append(Integer.toString(j, 16));
			}
		}
		return tmp.toString();
	}

	/**
	 * 
	 * @return
	 */
	public static Boolean isLetter(char cha) {

		String cc = "" + cha;
		if (cc.compareTo("a") >= 0 && cc.compareTo("z") <= 0)
			return true;
		if (cc.compareTo("A") >= 0 && cc.compareTo("Z") <= 0)
			return true;
		return false;
	}

	Boolean flag = false;

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

	public static String getTtime() {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// 设置日期格式
		String times = df.format(new Date());
		return times.split(" ").clone()[0];
	}

	/**
	 * 输出json格式数据
	 * 
	 * @param obj
	 * @param response
	 */
	public static void responseJson(Object obj, HttpServletResponse response) {
		response.setContentType("application/json;charset=utf-8");
		PrintWriter pw = null;
		try {
			pw = response.getWriter();
			if (obj == null) {
				pw.write(new JSONObject().toString());
			} else {
				pw.write(obj.toString());
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			pw.flush();
			pw.close();
		}
	}

	/**
	 * 获取当前时间(yyyy-MM-dd HH:mm:ss)
	 * 
	 * @author ydd
	 * @return
	 */
	public static String getDateDayTime() {
		SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return ft.format(new Date());
	}

	/**
	 * 获取当前时间(yyyy-MM-01 00:00:00)的次月一号
	 * 
	 * @author ydd
	 * @return
	 */
	public static String getDateNextMonth() {
		Date date = new Date();
		SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.MONTH, 1);
		cal.set(Calendar.DATE, 1);
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		String resultDate = ft.format(cal.getTime());
		return resultDate;
	}

	/**
	 * 生成XML格式字符串
	 * 
	 * @param doc
	 * @return
	 */
	public static String createStringFromXmlDoc(Document doc) {
		XMLOutputter docWriter = new XMLOutputter();
		Format ft = Format.getRawFormat();
		ft.setEncoding("UTF-8");
		ft.setIndent("	");// 设置缩进
		docWriter.setFormat(ft);
		return docWriter.outputString(doc);
	}

	/**
	 * 判断字符串数组中是否存在某字符串
	 * 
	 * @param
	 * @return
	 */
	public static Boolean isIn(String substring, String[] source) {
		if (source == null || source.length == 0) {
			return false;
		}
		for (int i = 0; i < source.length; i++) {
			String aSource = source[i];
			if (aSource.equals(substring)) {
				return true;
			}
		}
		return false;

	}

	/**
	 * 求两个数组的交集
	 * 
	 * @param
	 * @return
	 */
	public static String[] intersect(String[] arr1, String[] arr2) {
		Map<String, Boolean> map = new HashMap<String, Boolean>();
		LinkedList<String> list = new LinkedList<String>();
		for (String str : arr1) {
			if (!map.containsKey(str)) {
				map.put(str, Boolean.FALSE);
			}
		}
		for (String str : arr2) {
			if (map.containsKey(str)) {
				map.put(str, Boolean.TRUE);
			}
		}

		for (Entry<String, Boolean> e : map.entrySet()) {
			if (e.getValue().equals(Boolean.TRUE)) {
				list.add(e.getKey());
			}
		}

		String[] result = {};
		return list.toArray(result);
	}

	/**
	 * 求两个数组的集
	 * 
	 * @param
	 * @return
	 */
	public static String[] minus(String[] arr1, String[] arr2) {
		LinkedList<String> list = new LinkedList<String>();
		LinkedList<String> history = new LinkedList<String>();
		String[] longerArr = arr1;
		String[] shorterArr = arr2;
		// 找出较长的数组来减较短的数组
		if (arr1.length > arr2.length) {
			longerArr = arr2;
			shorterArr = arr1;
		}
		for (String str : longerArr) {
			if (!list.contains(str)) {
				list.add(str);
			}
		}
		for (String str : shorterArr) {
			if (list.contains(str)) {
				history.add(str);
				list.remove(str);
			} else {
				if (!history.contains(str)) {
					list.add(str);
				}
			}
		}
		String[] result = {};
		return list.toArray(result);
	}

	/**
	 * 数组转为字符串
	 * 
	 * @param
	 * @return
	 */
	public static String arrToString(String[] arr) {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < arr.length; i++) {
			sb.append(arr[i] + ",");
		}
		String s = sb.toString();
		return s.substring(0, s.length() - 1);
	}

	/**
	 * @Title: getCookieValue
	 * @param @param req
	 * @param @return
	 * @return String
	 * @author Yale Ren 2014-12-7
	 * @throws
	 */
	public static String getCookieValue(HttpServletRequest req, String key) {

		Cookie cookie[] = req.getCookies();
		if (cookie != null) {
			for (Cookie c : cookie) {
				if (c.getName().equals(key)) {
					return c.getValue();
				}
			}
		}
		return "";
	}

	/**
	 * 生成推荐码
	 * 
	 * @param schoolId
	 * @param type
	 * @return code
	 */
	public static String generateCode(int schoolId, int type, int i) {
		String md5 = "";
		long t = System.currentTimeMillis();
		String tp = "";
		switch (type) {
		case (1):
			tp = "t";
			break;
		case (2):
			tp = "s";
			break;
		}
		md5 = MD5.MD5Encode(schoolId + t + "" + i);
		int et = (int) (Math.random() * 10);
		String code = tp + md5.substring(et, et + 5);
		return code;
	}

	/**
	 * 图片下载
	 * 
	 * @param fileName
	 * @param folderName
	 * @param urlPath
	 * @return
	 */
	public static Boolean downloadImage(String fileName, String folderName,
			String urlPath) {
		boolean result = true;
		try {
			// 生成的文件路径
			String path = PropertiesUtil.readServerConfigValue(
					"IMAGE.SERVER.PATH").toString();
			path = path + folderName;
			File folder = new File(path);
			if (!folder.exists()) {
				boolean b = folder.mkdirs();
				System.out.println(path + "文件夹创建：" + b);
			}
			File file = new File(folder, fileName);
			if (!file.exists()) {
				file.createNewFile();
			}
			URL imageUrl = new URL(urlPath);
			imageUrl.openConnection().setConnectTimeout(1500);
			imageUrl.openConnection().setReadTimeout(10000);
			BufferedInputStream inputStream = new BufferedInputStream(
					imageUrl.openStream());
			BufferedOutputStream outputStream = new BufferedOutputStream(
					new FileOutputStream(file));
			byte[] buf = new byte[2048];
			int length = inputStream.read(buf);
			while (length != -1) {
				outputStream.write(buf, 0, length);
				length = inputStream.read(buf);
			}
			inputStream.close();
			outputStream.close();
		} catch (Exception e) {
			e.printStackTrace();
			result = false;
		}
		return result;
	}

	/**
	 * base64生成图片保存
	 * 
	 * @param fileName
	 * @param folderName
	 * @param ImgBase64
	 * @return
	 */
	public static Boolean writeImage(String fileName, String folderName,
			String ImgBase64) {
		boolean success = false;
		try {
			String path = PropertiesUtil.readServerConfigValue(
					"IMAGE.SERVER.PATH").toString();
			path = path + folderName;
			// 要生成的文件内容
			byte[] bt = Util.base64ToByte(ImgBase64);
			if (bt != null) {
				File folder = new File(path);
				if (!folder.exists()) {
					boolean b = folder.mkdirs();
					System.out.println(path + "文件夹创建：" + b);
				}
				File file = new File(folder, fileName);
				if (!file.exists()) {
					file.createNewFile();
				}
				FileOutputStream writerStream = new FileOutputStream(path
						+ File.separator + fileName);
				writerStream.write(bt);
				writerStream.close();
				success = true;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return success;
	}

	/**
	 * base64字符串转byte
	 * 
	 * @param imageStr
	 * @return
	 */
	public static byte[] base64ToByte(String imageStr) {
		byte[] bt = null;
		if (imageStr != null && !"".equals(imageStr.trim())) {
			String regex = "\\<image\\>\r*\n*([^\\<]+)\r*\n*\\</image\\>";
			Matcher m = Pattern.compile(regex, Pattern.UNIX_LINES).matcher(
					imageStr);
			while (m.find()) {
				String imgStr = m.group(1);
				try {
					bt = Base64.decode(imgStr);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		return bt;
	}

	/**
	 * 混淆导学号前四位
	 * 
	 * @param num
	 * @return
	 */
	public static String encryptDxh(String num) {
		if (num != null && num.length() > 0) {
			if (num.length() == 6 || num.length() == 3) {
				return num;
			} else {
				String prefix = "S";
				if (num.indexOf(prefix) < 0) {
					prefix = "";
				} else {
					num = num.replace(prefix, "");
				}
				int[][] _bookArray = new int[][] {
						{ 8, 7, 5, 6, 9, 4, 2, 1, 3, 0 },
						{ 7, 5, 3, 8, 0, 9, 6, 2, 1, 4 },
						{ 5, 0, 2, 1, 3, 6, 8, 4, 7, 9 },
						{ 6, 5, 4, 1, 2, 3, 0, 8, 9, 7 } };
				int num0 = Integer.parseInt(num.substring(0, 1));
				int num1 = Integer.parseInt(num.substring(1, 2));
				int num2 = Integer.parseInt(num.substring(2, 3));
				int num3 = Integer.parseInt(num.substring(3, 4));

				return prefix + _bookArray[0][num0] + "" + _bookArray[1][num1]
						+ _bookArray[2][num2] + _bookArray[3][num3]
						+ num.substring(4, num.length());
			}
		} else {
			return null;
		}
	}

	/**
	 * 解码number前四位
	 * 
	 * @param String
	 * @return String
	 */
	public static String decryptDxh(String number) {
		if (number != null && number.length() > 0) {
			String prefix = "S";
			if (number.indexOf("S") < 0) {
				prefix = "";
			} else {
				number = number.replace("S", "");
			}
			if (number.length() == 6 || number.length() == 3) {
				return number;
			}
			String rsult0 = "";
			String rsult1 = "";
			String rsult2 = "";
			String rsult3 = "";
			int[] arr0 = { 8, 7, 5, 6, 9, 4, 2, 1, 3, 0 };
			int[] arr1 = { 7, 5, 3, 8, 0, 9, 6, 2, 1, 4 };
			int[] arr2 = { 5, 0, 2, 1, 3, 6, 8, 4, 7, 9 };
			int[] arr3 = { 6, 5, 4, 1, 2, 3, 0, 8, 9, 7 };
			int num0 = Integer.parseInt(number.substring(0, 1));
			int num1 = Integer.parseInt(number.substring(1, 2));
			int num2 = Integer.parseInt(number.substring(2, 3));
			int num3 = Integer.parseInt(number.substring(3, 4));
			for (int i = 0; i < 10; i++) {
				if (arr0[i] == num0) {
					rsult0 = i + "";
				}
				if (arr1[i] == num1) {
					rsult1 = i + "";
				}
				if (arr2[i] == num2) {
					rsult2 = i + "";
				}
				if (arr3[i] == num3) {
					rsult3 = i + "";
				}
			}
			return prefix + rsult0 + rsult1 + rsult2 + rsult3
					+ number.substring(4, number.length());
		} else {
			return null;
		}
	}

	

	/**
	 * 处理因单引号导致的SQL语句错误
	 * 
	 * @param st
	 * @return
	 */
	public static String repalceDanYinHao(String st) {
		st = st.replaceAll("'", "''");
		return st;
	}

	
	public static boolean isJSONEmpty(JSONObject param, String keys) {
		boolean result = false;
		String[] keyArray = keys.split(",");
		for (int i = 0; i < keyArray.length; i++) {
			if (param.containsKey(keyArray[i])
					&& param.getString(keyArray[i]) != null) {
				result = true;
			} else {
				result = false;
				break;
			}
		}
		return result;
	}



	public static void sendEmail2(String fromMail, String user,
			String password, String[] toMail, String mailTitle,
			String mailContent) throws Exception {
		Properties props = new Properties(); // 可以加载一个配置文件
		// 使用smtp：简单邮件传输协议
		props.put("mail.smtp.host", "smtp.exmail.qq.com");// 存储发送邮件服务器的信息
		props.put("mail.smtp.port", "25");// 存储发送邮件服务器的信息
		props.put("mail.smtp.auth", "true");// 同时通过验证

		Session session = Session.getInstance(props);// 根据属性新建一个邮件会话
		// session.setDebug(true); //有他会打印一些调试信息。

		MimeMessage message = new MimeMessage(session);// 由邮件会话新建一个消息对象
		message.setFrom(new InternetAddress(fromMail));// 设置发件人的地址
		for (int i = 0; i < toMail.length; i++) {
			if (toMail[i] != null) {
				message.addRecipient(Message.RecipientType.TO,
						new InternetAddress(toMail[i]));// 设置收件人,并设置其接收类型为TO
			}
		}
		// message.setRecipient(Message.RecipientType.TO, new InternetAddress(
		// toMail));// 设置收件人,并设置其接收类型为TO
		message.setSubject(mailTitle);// 设置标题
		// 设置信件内容
		// message.setText(mailContent); //发送 纯文本 邮件 todo
		message.setContent(mailContent, "text/html;charset=gbk"); // 发送HTML邮件，内容样式比较丰富
		message.setSentDate(new Date());// 设置发信时间
		message.saveChanges();// 存储邮件信息

		// 发送邮件
		// Transport transport = session.getTransport("smtp");
		Transport transport = session.getTransport();
		transport.connect(user, password);
		transport.sendMessage(message, message.getAllRecipients());// 发送邮件,其中第二个参数是所有已设好的收件人地址
		transport.close();
	}
}
