package com.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.OutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jsoup.Jsoup;

import net.sf.json.JSONObject;

public class FdwFileUtils {
	public static void makeDir(String path) {
		File file = new File(path);
		file.mkdirs();
	}

	public static void makeDir(File dir) {
		makeDir(dir.getAbsolutePath());
	}

	public static void writeStringToFile(String str, File file)
			throws Exception {
		if (!file.getParentFile().exists()) {
			makeDir(file.getParentFile());
		}
		FileOutputStream fw = new FileOutputStream(file);
		fw.write(str.getBytes());
		fw.close();
	}
	public static String convertStringForInsertToDb(String context){
		if (context == null || context.length() == 0) {
			context = "";
		} else {
			context = context.replaceAll("'", "\"");
			context = context.trim();
		}
		return context;
	}
	public static String readFileToString(File file) throws Exception{
		if(!file.exists()){
			return null;
		}
		StringBuffer buff=new StringBuffer();
		BufferedReader br=new BufferedReader(new FileReader(file));
		String line;
		while((line=br.readLine())!=null){
			buff.append(line).append("\n");
		}
		br.close();
		return buff.toString();
	}

	public static void copyFile(String tempStr, String dest) throws Exception {
		String str=readFileToString(new File(tempStr));
		writeStringToFile(str,new File(dest));
	}
	/** 
	 * @Title: getFileExtension 
	 * @param fileName
	 * @return String
	 * @author Yale Ren 2014-12-6
	 * @throws 
	 */
	public static String getFileExtension(String fileName) {
		if (fileName.lastIndexOf(".") != -1 && fileName.lastIndexOf(".") != 0) {
			return fileName.substring(fileName.lastIndexOf(".") + 1);
		} else {
			return "";
		}
	}

	/** 
	 * @Title: deleteFile 
	 * @param path
	 * @return void
	 * @author Yale Ren 2014-12-6
	 * @throws 
	 */
	public static void deleteFile(String path){
		File file = new File(path);
		if (file.isFile()&&file.exists()) {
			file.delete();
		}
	}
	public static JSONObject createBookCatalogFile(HttpServletRequest request,HttpServletResponse response,String filename,String content){
		JSONObject root=new JSONObject();
		try{
			String path = "/bookCatalog/";
			path=request.getSession().getServletContext().getRealPath("/")+path;
			File file = new File(path);
			if(!file.exists()){
				file.mkdirs();
			}
			//设置要显示在保存窗口的文件名，如果文件名中有中文的话，则要设置字符集，否则会出现乱码。另外，要写上文件后缀名  
			//String fileName=java.net.URLEncoder.encode(filename,"utf-8");  
			content=Jsoup.parseBodyFragment(content).toString();
			//创建要下载的文件的对象(参数为要下载的文件在服务器上的路径)  
			File serverFile=new File(path+filename+".doc");  

			
			//该步是最关键的一步，使用setHeader()方法弹出"是否要保存"的对话框，打引号的部分都是固定的值，不要改变  
			response.setHeader("Content-disposition","attachment;filename="+filename);  

			/* 
			 * 以下四行代码经测试似乎可有可无，可能是我测试的文件太小或者其他什么原因。。。 
			 */  
			response.setContentType("application/msword");  
			//定义下载文件的长度 /字节  
			long fileLength=serverFile.length();  
			//把长整形的文件长度转换为字符串  
			String length=String.valueOf(fileLength);  
			//设置文件长度(如果是Post请求，则这步不可少)  
			response.setHeader("content_Length",length);  

			/* 
			 *以上内容仅是下载一个空文件 
			 *以下内容用于将服务器中相应的文件内容以流的形式写入到该空文件中 
			 */  
			//获得一个 ServletOutputStream(向客户端发送二进制数据的输出流)对象  
			OutputStream servletOutPutStream=response.getOutputStream();  
			//获得一个从服务器上的文件myFile中获得输入字节的输入流对象  
			FileInputStream fileInputStream=new FileInputStream(serverFile);  


			byte bytes[]=new byte[1024];//设置缓冲区为1024个字节，即1KB  
			int len=0;  
			//读取数据。返回值为读入缓冲区的字节总数,如果到达文件末尾，则返回-1  
			while((len=fileInputStream.read(bytes))!=-1)  
			{     
				//将指定 byte数组中从下标 0 开始的 len个字节写入此文件输出流,(即读了多少就写入多少)  
				servletOutPutStream.write(bytes,0,len);   
			}  
			servletOutPutStream.close();  
			fileInputStream.close();   

			root.put("success", true);
			root.put("message", "生成文件成功");
		} catch (Exception e) {
			root.put("success", false);
			root.put("message", "生成文件失败："+e.getMessage());
			e.printStackTrace();
		}
		return root;
	}

}
