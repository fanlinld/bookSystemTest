package com.book.controller;


import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.book.dto.Book;
import com.book.dto.BorrowRecord;
import com.book.dto.Classify;
import com.book.dto.ReadNote;
import com.book.service.BookService;
import com.util.BaseController;
import com.util.JsonUtil;

/**
 * BOOK
 * @author ydd
 *
 */
@Component("book")
public class BookController extends HttpServlet {
	private static final long serialVersionUID = 7113130751016094624L;
	@Autowired(required=true)
	private BookService bookService;
	private String SERVERNAME="book";

	/**
	 * GET
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		JSONObject root=null;
		String subService =BaseController.getUriNode(SERVERNAME, 0, request);
		JSONObject param=BaseController.setQueryParams(request);
		if(subService.equals("selectReadNotes")){
			ReadNote readNote=(ReadNote)JsonUtil.getJsonToObject(param.toString(), ReadNote.class);
			root=bookService.selectReadNotes(readNote);
		}
		BaseController.responseJson(root, response);
	}

	/**
	 * POST
	 */	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		JSONObject root=null;
		String subService =BaseController.getUriNode(SERVERNAME, 0, request);
		JSONObject param=BaseController.setQueryParams(request);
		JSONObject u= (JSONObject) request.getSession().getAttribute("BookManageSystem_User");
		if(subService.equals("selectBooks")){
			Book book=(Book)JsonUtil.getJsonToObject(param.toString(), Book.class);
			root=bookService.selectBooks(book);
		}else if(subService.equals("getClassifyComeBox")){
			Classify classify=(Classify)JsonUtil.getJsonToObject(param.toString(), Classify.class);
			JSONArray array=bookService.getClassifyComeBox(classify);
			BaseController.responseJson(array, response);
			return;
		}else if(subService.equals("insertBook")){
			Book book=(Book)JsonUtil.getJsonToObject(param.toString(), Book.class);
			root=bookService.insertBook(book,u.getInt("id"));
		}else if(subService.equals("deleteBook")){
			Book book=(Book)JsonUtil.getJsonToObject(param.toString(), Book.class);
			root=bookService.deleteBook(book,u.getInt("id"));
		}else if(subService.equals("insertClassify")){
			Classify classify=(Classify)JsonUtil.getJsonToObject(param.toString(), Classify.class);
			root=bookService.insertClassify(classify);
		}else if(subService.equals("deleteClassify")){
			Classify classify=(Classify)JsonUtil.getJsonToObject(param.toString(), Classify.class);
			root=bookService.deleteClassify(classify);
		}else if(subService.equals("saveBookNote")){
			root=bookService.saveBookNote(param);
		}else if(subService.equals("deleteBookNote")){
			root=bookService.deleteBookNote(param);
		}else if(subService.equals("selectReadNotes")){
			ReadNote readNote=(ReadNote)JsonUtil.getJsonToObject(param.toString(), ReadNote.class);
			root=bookService.selectReadNotes(readNote);
		}else if(subService.equals("selectBorrowRecords")){
			BorrowRecord borrowRecord=(BorrowRecord)JsonUtil.getJsonToObject(param.toString(), BorrowRecord.class);
			root=bookService.selectBorrowRecords(borrowRecord);
		}else if(subService.equals("saveBorrowRecord")){
			root=bookService.saveBorrowRecord(param);
		}else if(subService.equals("deleteBorrowRecord")){
			root=bookService.deleteBorrowRecord(param);
		}else if(subService.equals("selectMyOutBooks")){
			root=bookService.selectMyOutBooks(param);
		}
		BaseController.responseJson(root, response);
	}
}
