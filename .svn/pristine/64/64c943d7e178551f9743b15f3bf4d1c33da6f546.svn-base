package com.book.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.book.dto.Book;
import com.book.dto.BorrowRecord;
import com.book.dto.Classify;
import com.book.dto.OutBook;
import com.book.dto.ReadNote;
@Repository
public class BookDaoImpl implements BookDao{

	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;
	@Autowired
	private SqlSessionTemplate sqlSessionTemplateR;
	
	
	/**
	 * 查询图书
	 * @param book
	 * @return
	 */
	public List<Book> selectBooks(Book book) {
		return this.sqlSessionTemplateR.selectList("selectbooksInfo", book);
	}
	public Integer selectBookCount(Book book) {
		return this.sqlSessionTemplateR.selectOne("selectbooksInfoCount", book);
	}
	
	public Book selectOneBook(Book book){
		return this.sqlSessionTemplateR.selectOne("selectOneBook", book);
	}
	
	/**
	 * 保存一本图书信息
	 * @param book
	 * @return
	 */
	public Integer insertBook(Book book) {
		this.sqlSessionTemplate.insert("insertBook", book);
		return book.getId();
	}
	/**
	 * 删除书籍
	 * @param book
	 * @return
	 */
	public int deleteBook(Book book) {
		return this.sqlSessionTemplate.delete("deleteBook", book);
	}
	/**
	 * 更新书籍
	 * @param book
	 * @return
	 */
	public int updateBook(Book book) {
		return this.sqlSessionTemplate.delete("updateBook", book);
	}
	
	/**
	 * 获得书籍类型
	 * @param classify
	 * @return
	 */
	public List<Classify> getClassifyComeBox(Classify classify) {
		return this.sqlSessionTemplateR.selectList("getClassifyComeBox", classify);
	}
	/**
	 * 添加书籍类型
	 * @param classify
	 * @return
	 */
	public int insertClassify(Classify classify){
		return this.sqlSessionTemplate.insert("insertClassify", classify);
	}
	/**
	 * 删除书籍类型
	 * @param classify
	 * @return
	 */
	public int deleteClassify(Classify classify){
		return this.sqlSessionTemplate.delete("deleteClassify", classify);
	}
	/**
	 * 获得读书笔记
	 * @param readNote
	 * @return
	 */
	public List<ReadNote> selectReadNotes(ReadNote readNote){
		return this.sqlSessionTemplateR.selectList("selectReadNotes", readNote);
	}
	
	/**
	 * 添加读书笔记
	 * @param readNote
	 * @return
	 */
	public int insertReadNote(ReadNote readNote){
		return this.sqlSessionTemplate.insert("insertReadNote", readNote);
	}
	/**
	 * 更新读书笔记
	 * @param readNote
	 * @return
	 */
	public int updateReadNote(ReadNote readNote){
		return this.sqlSessionTemplate.update("updateReadNote", readNote);
	}
	/**
	 * 删除读书笔记
	 * @param readNote
	 * @return
	 */
	public int deleteBookNote(ReadNote readNote){
		return this.sqlSessionTemplate.delete("deleteBookNote", readNote);
	}
	
	/**
	 * 查询借阅记录
	 * @param borrowRecord
	 * @return
	 */
	public List<BorrowRecord> selectBorrowRecords(BorrowRecord borrowRecord){
		return this.sqlSessionTemplateR.selectList("selectBorrowRecords", borrowRecord);
	}
	/**
	 * 添加借阅记录
	 * @param borrowRecord
	 * @return
	 */
	public int insertBorrowRecord(BorrowRecord borrowRecord){
		return this.sqlSessionTemplate.insert("insertBorrowRecord", borrowRecord);
	}
	/**
	 * 更新借阅记录
	 * @param borrowRecord
	 * @return
	 */
	public int updateBorrowRecord(BorrowRecord borrowRecord){
		return this.sqlSessionTemplate.update("updateBorrowRecord", borrowRecord);
	}
	/**
	 * 删除借阅记录
	 * @param borrowRecord
	 * @return
	 */
	public int deleteBorrowRecord(BorrowRecord borrowRecord){
		return this.sqlSessionTemplate.delete("deleteBorrowRecord", borrowRecord);
	}
	
	/**
	 *查询我的借阅书籍 
	 */
	public List<OutBook> selectMyOutBooks(OutBook outbook) {
		return this.sqlSessionTemplateR.selectList("selectMyOutBooks", outbook);
	}
}
