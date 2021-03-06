package com.book.service;

import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.book.dao.BookDao;
import com.book.dto.Book;
import com.book.dto.BorrowRecord;
import com.book.dto.Classify;
import com.book.dto.OutBook;
import com.book.dto.ReadNote;
import com.util.CommonUtil;
import com.util.JsonUtil;
import com.util.PropertiesUtil;
import com.util.SharePage;
import com.util.Util;

@Service
@Transactional
public class BookServiceImpl implements BookService{

	@Autowired
	private BookDao bookDao;

	/**
	 * 查询图书列表信息
	 * @param book
	 * @return
	 */
	public JSONObject selectBooks(Book book){
		JSONObject root=new JSONObject();
		try {
			book.setIsShare(1);//需要分页
			Integer maxCount=bookDao.selectBookCount(book);
			book.setMaxCount(maxCount);
			List<Book> list=bookDao.selectBooks(book);
			String path = PropertiesUtil.readServerConfigValue("BOOKMARK.COVER.IMGAGE.PATH").toString();
			for(int i=0;i<list.size();i++){
				list.get(i).setImage("/"+path+list.get(i).getImage());
			}
			root=SharePage.getSharePageContext(bookDao.selectBooks(book),book);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return root;
	}

	/**
	 * 保存图书内容信息
	 * @param bookcontent
	 * @return
	 */
	public JSONObject insertBook(Book book,Integer userId) {
		JSONObject root=new JSONObject();
		String message="程序处理异常";
		boolean success=false;
		try{
			if(book.getId()==null || book.getId()==0){
				if(bookDao.insertBook(book)>0){
					success=true;
					message="新增成功";
				}else{
					message="数据库操作异常";
				}
			}else{
				Book ckBook=new Book();
				ckBook.setId(book.getId());
				ckBook=bookDao.selectOneBook(ckBook);
				if(ckBook.getName().equals(book.getName())){
					if(bookDao.updateBook(book)>0){
						success=true;
						message="修改成功";
					}else{
						message="数据库操作异常";
					}
				}else{
					if(bookDao.updateBook(book)>0){
						success=true;
						message="修改成功";
					}else{
						message="数据库操作异常";
					}
				}
			}
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
			root.put("success", success);
			root.put("message", message);
		}
		return root;
	}

	/**
	 * 删除教材
	 * @param bookcontent
	 * @return
	 */
	public JSONObject deleteBook(Book book,Integer userId) {
		JSONObject root=new JSONObject();
		String message="程序处理异常";
		boolean success=false;
		try{
			if(book.getId()!=null && book.getId()!=0){
				if(bookDao.deleteBook(book)>0){
					success=true;
					message="删除成功";
				}else{
					message="删除失败";
				}
			}else{
				message="数据不存在";
			}

		}catch (Exception e) {
			e.printStackTrace();
		}finally{
			root.put("success", success);
			root.put("message", message);
		}
		return root;
	}

	/**
	 * 获得书籍类别
	 */
	public JSONArray getClassifyComeBox(Classify classify) {
		JSONArray array=new JSONArray();
		try {
			List<Classify> list=bookDao.getClassifyComeBox(classify);
			if(list!=null && list.size()>0){
				JSONObject firstItem=new JSONObject();
				firstItem.put("selected", true);
				firstItem.put("id", "");
				firstItem.put("text", "--请选择--");
				array.add(firstItem);
				for(int i=0;i<list.size();i++){
					JSONObject item=new JSONObject();
					item.put("id", list.get(i).getId());
					item.put("text", list.get(i).getName());
					array.add(item);
				}
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return array;
	}
	/**
	 * 添加书籍类型
	 * @param classify
	 * @return
	 */
	public JSONObject insertClassify(Classify classify){
		JSONObject root=new JSONObject();
		String message="程序处理异常";
		boolean success=false;
		try{
			if(bookDao.insertClassify(classify)>0){
				success=true;
				message="添加成功";
			}else{
				message="添加失败";
			}
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
			root.put("success", success);
			root.put("message", message);
		}
		return root;
	}
	/**
	 * 删除书籍类型
	 * @param classify
	 * @return
	 */
	public JSONObject deleteClassify(Classify classify){
		JSONObject root=new JSONObject();
		String message="程序处理异常";
		boolean success=false;
		try{
			if(bookDao.deleteClassify(classify)>0){
				success=true;
				message="删除成功";
			}else{
				message="删除失败";
			}
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
			root.put("success", success);
			root.put("message", message);
		}
		return root;
	}
	
	/**
	 * 获得读书笔记
	 */
	public JSONObject selectReadNotes(ReadNote readNote){
		JSONObject root=new JSONObject();
		String message="程序处理异常";
		boolean success=false;
		try{
			if(readNote.getBookid()>0){
				List<ReadNote> readNotelist=bookDao.selectReadNotes(readNote);
				if(readNotelist.size()>0){
					root.put("rows", readNotelist);
					root.put("name", readNotelist.get(0).getBookname());
				}else{
					root.put("rows", "");
				}
				success=true;
				message="";
				
			}else{
				message="参数异常";
			}
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
			root.put("success", success);
			root.put("message", message);
		}
		return root;
		
	}
	
	/**
	 * 保存读书笔记
	 * @param param
	 * @return
	 */
	public JSONObject saveBookNote(JSONObject param){
		JSONObject root=new JSONObject();
		String message="程序处理异常";
		boolean success=false;
		try{
			if(Util.isJSONEmpty(param, "id,bookid,title,content")){
				ReadNote readNote=(ReadNote)JsonUtil.getJsonToObject(param.toString(), ReadNote.class);
				if(readNote.getId()==0){
					readNote.setCreatedate(CommonUtil.getDateDayTime());
					if(bookDao.insertReadNote(readNote)>0){
						success=true;
						message="添加成功";
					}else{
						message="添加失败";
					}
				}else{
					readNote.setModifydate(CommonUtil.getDateDayTime());
					if(bookDao.updateReadNote(readNote)>0){
						success=true;
						message="修改成功";
					}else{
						message="修改失败";
					}
				}
			}else{
				message="参数异常";
			}
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
			root.put("success", success);
			root.put("message", message);
		}
		return root;
	}
	
	public JSONObject deleteBookNote(JSONObject param){
		JSONObject root=new JSONObject();
		String message="程序处理异常";
		boolean success=false;
		try{
			if(Util.isJSONEmpty(param, "id")){
				ReadNote readNote=(ReadNote)JsonUtil.getJsonToObject(param.toString(), ReadNote.class);
				if(bookDao.deleteBookNote(readNote)>0){
					success=true;
					message="删除成功";
				}else{
					message="删除失败";
				}
			}else{
				message="参数异常";
			}
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
			root.put("success", success);
			root.put("message", message);
		}
		return root;
	}
	
	/**
	 * 查询借阅记录
	 * @param borrowRecord
	 * @return
	 */
	public JSONObject selectBorrowRecords(BorrowRecord borrowRecord){
		JSONObject root=new JSONObject();
		String message="程序处理异常";
		boolean success=false;
		try{
			List<BorrowRecord> brlist=bookDao.selectBorrowRecords(borrowRecord);
			if(brlist.size()==0){
				root.put("rows", "[]");
			}else{
				root.put("rows", brlist);
				success=true;
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
			root.put("success", success);
			root.put("message", message);
		}
		return root;
	}
	/**
	 * 借书
	 * @param borrowRecord
	 * @return
	 */
	public JSONObject saveBorrowRecord(JSONObject param){
		JSONObject root=new JSONObject();
		String message="程序处理异常";
		boolean success=false;
		if(Util.isJSONEmpty(param, "id,borrow_date,borrow_person,bookid")){
			BorrowRecord borrowRecord=(BorrowRecord)JsonUtil.getJsonToObject(param.toString(), BorrowRecord.class);
			if(borrowRecord.getId()==0){
				if(bookDao.insertBorrowRecord(borrowRecord)>0){
					Book book=new Book();
					book.setId(borrowRecord.getBookid());
					book.setStatus(0);
					if(bookDao.updateBook(book)>0){
						message="借阅成功";
						success=true;
					}else{
						throw new RuntimeException();
					}
					
				}else{
					message="借阅失败";
				}
			}else{
				if(bookDao.updateBorrowRecord(borrowRecord)>0){
					Book book=new Book();
					book.setId(borrowRecord.getBookid());
					book.setStatus(1);
					if(bookDao.updateBook(book)>0){
						message="还书成功";
						success=true;
					}else{
						throw new RuntimeException();
					}
				}else{
					message="还书失败";
				}
			}
		}else{
			message="参数异常";
		}
	
		root.put("success", success);
		root.put("message", message);
		return root;
	}
	
	
	public JSONObject deleteBorrowRecord(JSONObject param){
		JSONObject root=new JSONObject();
		String message="程序处理异常";
		boolean success=false;
		if(Util.isJSONEmpty(param, "id,bookid")){
			BorrowRecord borrowRecord=(BorrowRecord)JsonUtil.getJsonToObject(param.toString(), BorrowRecord.class);
			if(bookDao.deleteBorrowRecord(borrowRecord)>0){
				Book book=new Book();
				book.setId(borrowRecord.getBookid());
				book.setStatus(1);
				if(bookDao.updateBook(book)>0){
					message="删除成功";
					success=true;
				}else{
					throw new RuntimeException();
				}
			}else{
				message="删除失败";
			}
		}else{
			message="参数异常";
		}
	
		root.put("success", success);
		root.put("message", message);
		return root;
	}

	/**
	 * 查询我的借出书籍
	 */
	public JSONObject selectMyOutBooks(JSONObject param) {
		JSONObject root=new JSONObject();
		String message="程序处理异常";
		boolean success=false;
		try {
			OutBook outbook=(OutBook)JsonUtil.getJsonToObject(param.toString(), OutBook.class);
			
			List<OutBook> list=bookDao.selectMyOutBooks(outbook);
			if(list.size()==0){
				root.put("rows", "[]");
			}else{
				root.put("rows", list);
				success=true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		root.put("success", success);
		root.put("message", message);
		return root;
	}
	
}
