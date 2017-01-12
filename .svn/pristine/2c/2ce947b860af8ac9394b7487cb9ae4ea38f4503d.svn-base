package com.book.service;


import com.book.dto.Book;
import com.book.dto.BorrowRecord;
import com.book.dto.Classify;
import com.book.dto.ReadNote;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;



public interface BookService{
	
	
	/**
	 * 查询图书列表信息
	 * @param book
	 * @return
	 */
	public JSONObject selectBooks(Book book);

	public JSONObject insertBook(Book book,Integer userId);

	public JSONObject deleteBook(Book book,Integer userId);

	public JSONArray getClassifyComeBox(Classify classify);

	public JSONObject insertClassify(Classify classify);

	public JSONObject deleteClassify(Classify classify);

	public JSONObject selectReadNotes(ReadNote readNote);

	public JSONObject saveBookNote(JSONObject param);

	public JSONObject deleteBookNote(JSONObject param);

	public JSONObject selectBorrowRecords(BorrowRecord borrowRecord);


	public JSONObject saveBorrowRecord(JSONObject param);

	public JSONObject deleteBorrowRecord(JSONObject param);

	public JSONObject selectMyOutBooks(JSONObject param);
}
