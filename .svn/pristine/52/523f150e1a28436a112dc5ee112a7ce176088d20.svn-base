package com.book.dao;

import java.util.List;

import com.book.dto.Book;
import com.book.dto.BorrowRecord;
import com.book.dto.Classify;
import com.book.dto.OutBook;
import com.book.dto.ReadNote;

public interface BookDao {
	
	/**
	 * 查询图书信息Count
	 * @param book
	 * @return
	 */
	public Integer selectBookCount(Book book);
	
	/**
	 * 查询图书信息列表
	 * @param book
	 * @return
	 */
	public List<Book> selectBooks(Book book);
	
	public Integer insertBook(Book book);

	public int deleteBook(Book book);

	public int updateBook(Book book);
	

	public List<Classify> getClassifyComeBox(Classify classify);

	public Book selectOneBook(Book ckBook);

	public int insertClassify(Classify classify);

	public int deleteClassify(Classify classify);

	public List<ReadNote> selectReadNotes(ReadNote readNote);

	public int insertReadNote(ReadNote readNote);

	public int updateReadNote(ReadNote readNote);

	public int deleteBookNote(ReadNote readNote);

	public List<BorrowRecord> selectBorrowRecords(BorrowRecord borrowRecord);

	public int insertBorrowRecord(BorrowRecord borrowRecord);

	public int updateBorrowRecord(BorrowRecord borrowRecord);

	public int deleteBorrowRecord(BorrowRecord borrowRecord);

	public List<OutBook> selectMyOutBooks(OutBook outbook);
}
