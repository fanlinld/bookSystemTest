package com.book.controller;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.book.dao.BookDao;
import com.book.dto.Book;
import com.util.BaseController;
import com.util.CommonUtil;
import com.util.JsonUtil;

import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import net.sf.json.JSONObject;

@Component("exportBookInfo")
public class BookInfoExport extends HttpServlet {

	private static final long serialVersionUID = -2301681252628147526L;
	@Autowired
	BookDao bookDao;
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {     
		JSONObject param=BaseController.setQueryParams(request);
		Book book=(Book)JsonUtil.getJsonToObject(param.toString(), Book.class);
        // 生成xls     
		try{
			
			List<Book> booklist=bookDao.selectBooks(book);
			
			// 新建一张表    
			String filename ="书籍信息汇总"+CommonUtil.getDateDayFormat2();
			filename = new String(filename.getBytes(), "ISO8859-1");
            String targetFile = filename + ".xls";     
            response.setContentType("application/vnd.ms-excel");     
            response.addHeader("Content-Disposition", "attachment;filename=\"" + targetFile + "\"");  
            //response.setHeader("Content-Disposition","attachment;filename="+new String(fname.getBytes("UTF-8"),"GBK")+".xls");
            OutputStream os = response.getOutputStream();     
            // 新建一张表     
            createBookExcel(os,booklist);
            response.flushBuffer();  
		}catch(Exception e) {
			e.printStackTrace();   
			System.out.println("生成信息表(Excel格式)时出错：");     
		}
	}

	private void createBookExcel(OutputStream os, List<Book> booklist) throws WriteException,IOException{
		//创建工作薄
	    WritableWorkbook workbook = Workbook.createWorkbook(os);
	    //创建新的一页
	    WritableSheet sheet = workbook.createSheet("books",0);
	   
	   //将书籍信息写入
	    List<String[]> contentList = new ArrayList<String[]>();
		for(int i=0;i<booklist.size();i++){
			String name=booklist.get(i).getName();
			String author=booklist.get(i).getAuthor();
			String english_name=booklist.get(i).getEnglish_name();
			String translator=booklist.get(i).getTranslator();
			String press=booklist.get(i).getPress();
			String revison=booklist.get(i).getRevison();
			String classify=booklist.get(i).getClassify();
			String listing=booklist.get(i).getListing();
			String brand=booklist.get(i).getBrand();
			String buydate=booklist.get(i).getBuydate();
			int intstatus=booklist.get(i).getStatus();
			String status="";
			if(intstatus==0){
				status="未读";
			}else{
				status="已读";
			}
			String [] contents = {name,author,english_name,translator,press,revison,classify,				
					listing,brand,buydate,status};
			contentList.add(contents);
		}
		String [] titleStrs = {"书名","作者","外文书名","译者","出版社","版次","类别","丛书","品牌","购买日期","是否读"};
		
		int titleCount = titleStrs.length;// 列数
	    for(int i=0;i<titleCount;i++){
	    	Label head = new Label(i,0,titleStrs[i]);
	    	sheet.addCell(head);
	    }
	    for(int i=0;i<contentList.size();i++){
	    	String [] contents = contentList.get(i);
	    	for (int j = 0; j < titleCount; j++) {
	    		Label content=new Label(j,i+1,contents[j]);
	    		 sheet.addCell(content);
	    	}
	    	
	    }
	    
	  //把创建的内容写入到输出流中，并关闭输出流
	    workbook.write();
	    workbook.close();
	    os.close();
	}
	
	

}
