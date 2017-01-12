package com.book.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;






import net.sf.json.JSONObject;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.stereotype.Component;

import com.util.BaseController;
import com.util.FileUitl;
import com.util.PropertiesUtil;
import com.util.Util;



@Component("bookupload")
public class BookUploadaFile extends HttpServlet {

	private String SERVERNAME="bookupload";
	private static final long serialVersionUID = 5143169424076572212L;

	/**
	 * POST
	 */	
	@SuppressWarnings("unchecked")
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		JSONObject root=new JSONObject();
		String subService =BaseController.getUriNode(SERVERNAME, 0, request);
		if(subService.equals("uploadcover")){
			request.setCharacterEncoding("utf-8");
			response.setContentType("text/html;charset=utf-8");
			//为解析类提供配置信息
			DiskFileItemFactory factory = new DiskFileItemFactory();
			//创建解析类的实例
			ServletFileUpload sfu = new ServletFileUpload(factory);
			//所有上传数据的最大尺寸限制
			sfu.setSizeMax(1024*1024*2*10);
			//文件大小2M
			sfu.setFileSizeMax(1024*1024*2);
			//每个表单域中数据会封装到一个对应的FileItem对象上
			try {
				List<FileItem> items = sfu.parseRequest(request);
				//区分表单域
				for (int i = 0; i < items.size(); i++) {
					FileItem item = items.get(i);
					//isFormField为true，表示这不是文件上传表单域
					if(!item.isFormField()){
						//获得存放文件的物理路径
						//upload下的某个文件夹 得到当前在线的用户 找到对应的文件夹
						String path = PropertiesUtil.readServerConfigValue("IMAGE.SERVER.PATH").toString();
						path+=PropertiesUtil.readServerConfigValue("BOOKMARK.COVER.IMGAGE.PATH").toString();
						
						//String path = request.getSession().getServletContext().getRealPath("/")+"/cover/";
						
						System.out.println(path);
						//获得文件名
						String fileTag = item.getName().substring(item.getName().lastIndexOf("."));
						String fileName = System.currentTimeMillis()+fileTag;
						
						File folder = new File(path);
						if (!folder.exists()) {
							folder.mkdirs();
						}
						File file = new File(folder, fileName);
						if (!file.exists()) {
							item.write(file); 
						}
						root.put("image", fileName);
						root.put("success", true);
					}else{
						root.put("success", true);
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
				root.put("success", false);
			}finally{
				BaseController.responseJson(root, response);
			}
		}
	}
}
