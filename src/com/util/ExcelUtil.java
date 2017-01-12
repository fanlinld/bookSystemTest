package com.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtil {


	/** 
	 * @Title: getExcelContent 
	 * @param @param filePath
	 * @param @param cellIndex get which cell conent,-1 = all ,0 = 1
	 * @param @return
	 * @return List<List<String>>
	 * @author Yale Ren 2014-12-8
	 * @throws 
	 */
	public static List<List<String>> getExcelContent(String filePath,int cellIndex){
		String fileExten = FdwFileUtils.getFileExtension(filePath).toLowerCase();
		if (fileExten.equals("xls")) {
			return readXls(filePath,cellIndex);
		}else if (fileExten.equals("xlsx")) {
			return readExcel(filePath,cellIndex);
		}
		return null;
	}

	@SuppressWarnings("rawtypes")
	public static List<List<String>> readXls(String filePath,int cellIndex){  
		List<List<String>> list=new  ArrayList<List<String>>();  
		try {  
			InputStream input = new FileInputStream(filePath);  
			POIFSFileSystem fs = new POIFSFileSystem(input);  
			HSSFWorkbook wb = new HSSFWorkbook(fs);  
			HSSFSheet sheet = wb.getSheetAt(0);  
			Iterator rows = sheet.rowIterator();

			List<String> objectList=null;
			while (rows.hasNext()) {  
				objectList=new ArrayList<String>();
				HSSFRow row = (HSSFRow) rows.next();  
				Iterator cells = row.cellIterator();
				int count =0;
				while (cells.hasNext()) {  
					HSSFCell cell = (HSSFCell) cells.next();  
					objectList.add(getValue(cell));
					if (cellIndex!=-1) {
						if (cellIndex == count) {
							break;
						}
						count++;
					}
				} 
				list.add(objectList);
			}  
		} catch (IOException ex) {  
			ex.printStackTrace();  
		} 
		return list;
	}  

	@SuppressWarnings("static-access")
	private static String getValue(HSSFCell hssfCell) {
		if (hssfCell.getCellType() == hssfCell.CELL_TYPE_BOOLEAN) {
			return String.valueOf(hssfCell.getBooleanCellValue());
		} else if (hssfCell.getCellType() == hssfCell.CELL_TYPE_NUMERIC) {
			return String.valueOf(hssfCell.getNumericCellValue());
		} else {
			return String.valueOf(hssfCell.getStringCellValue());
		}
	}
	/**
	 * 读取指定目录下excel文件内容
	 * @param filePath
	 * @return
	 */
	@SuppressWarnings("deprecation")
	public static List<List<String>> readExcel(String filePath,int cellIndex){
		List<List<String>> list=new  ArrayList<List<String>>();
		List<String> objectList=new ArrayList<String>();
		// 构造 XSSFWorkbook 对象，strPath 传入文件路径  
		XSSFWorkbook xwb;
		try {
			xwb = new XSSFWorkbook(filePath);
			// 读取第一章表格内容  
			XSSFSheet sheet = xwb.getSheetAt(0);  
			// 定义 row、cell  
			XSSFRow row;  
			// 循环输出表格中的内容  
			for (int i = sheet.getFirstRowNum(); i < sheet.getPhysicalNumberOfRows(); i++) {  
				row = sheet.getRow(i);  
				objectList=new ArrayList<String>();
				for (int j = row.getFirstCellNum(); j < row.getPhysicalNumberOfCells(); j++) {
					String cell=""; 
					// 通过 row.getCell(j).toString() 获取单元格内容，
					if(row.getCell(j)!=null){
						cell = row.getCell(j).toString();  
					}
					objectList.add(cell);
					if (cellIndex!=-1) {
						if (cellIndex==j) {
							break;
						}
					}
				}  
				list.add(objectList);
			}  
		} catch (IOException e) {
			e.printStackTrace();
		}
		return list;
	}

	/**
	 * 修改文件夹内文件名称
	 * @param fileBoxName
	 */
	public static void editFileName(String fileBoxName){
		File file = new File(fileBoxName);
		File[] files = file.listFiles(); //  该文件夹下 文件数量

		if(file.isDirectory()){  
			for(int i=0;i<files.length;i++){
				String oldname = files[i].getName();
				String newname = "这是个新名字.mp4";

				if(!oldname.equals(newname)){
					File newFile = new File(file.getAbsoluteFile() +"/" +newname);
					if(newFile.exists()){
						System.out.println("您没有对文件名做修改，不能执行~");
					} else {
						files[i].renameTo(newFile);
						System.out.println("第" + (i+1)+"个，已经修改完成!");
					}
				}
			}
		}
	}
	


	/** 写excel文件
	 * @param String[] titleStrs,List<String[]> contentList,
	 * @param String path,String filename, HttpServletResponse response
	 * @author FL 2015-4-30
	 */
	// 标题字体
	private static HSSFFont titleFont = null;
	// 标题样式
	private static HSSFCellStyle titleStyle = null;
	// 行信息内容样式
	private static HSSFCellStyle contentStyle = null;
	
	@SuppressWarnings("deprecation")
	public static void writeExcel(String[] titleStrs,List<String[]> contentList,String path,String filename, HttpServletResponse response) throws IOException{
		FileOutputStream fileOut = new FileOutputStream(path);

		HSSFWorkbook wb = new HSSFWorkbook();// 创建新HSSFWorkbook对象
		
		setExcelStyle(wb);//执行样式初始化
		HSSFSheet sheet = wb.createSheet(filename);// 创建新的sheet对象

		HSSFRow titleRow = sheet.createRow((short) 0);//创建第一行

		titleRow.setHeightInPoints(20);//20像素
		int titleCount = titleStrs.length;// 列数
		
		// 写标题
		for (int k = 0; k < titleCount; k++) {
			HSSFCell cell = titleRow.createCell((short) k); // 新建一个单元格
			cell.setCellStyle(titleStyle);//设置标题样式
			cell.setCellType(HSSFCell.CELL_TYPE_STRING);
	        cell.setCellValue(titleStrs[k]); 
	        sheet.setColumnWidth((short)k, (short)5000);//设置列宽
		}

		int contentCount = contentList.size();//总的记录数
		// 写内容
		for (int i = 0; i < contentCount; i++) {
			String [] contents = contentList.get(i);
			HSSFRow row = sheet.createRow((short)(i + 1)); // 新建一行

			for (int j = 0; j < titleCount; j++) {
				HSSFCell cell = row.createCell((short) j); // 新建一个单元格

				cell.setCellStyle(contentStyle);//设置内容样式
				if (contents[j] == null || contents[j].equals("null")) {
					contents[j] = "";
				}
				//格式化日期
				if(j == 2){
					HSSFCellStyle style = wb.createCellStyle();
					style.setDataFormat(wb.getCreationHelper().createDataFormat().getFormat("yyyy-mm-dd hh:mm:ss"));
					cell.setCellValue(contents[j]);
					cell.setCellStyle(style);
				}else{
					cell.setCellValue(new HSSFRichTextString(contents[j]));
				}
			}
		}
		wb.write(fileOut);
		fileOut.flush();
		fileOut.close();
	}
	
	/** 样式初始化*/
	public static void setExcelStyle(HSSFWorkbook workBook){
		// 设置列标题字体，样式
		titleFont = workBook.createFont();
		titleFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		// 标题列样式
		titleStyle = workBook.createCellStyle();
		titleStyle.setBorderTop(HSSFCellStyle.BORDER_THIN); // 设置边框
		titleStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		titleStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		titleStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
		titleStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		titleStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		titleStyle.setFont(titleFont);
		// 内容列样式
		contentStyle = workBook.createCellStyle();
		contentStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);
		contentStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		contentStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		contentStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
		contentStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		contentStyle.setAlignment(HSSFCellStyle.ALIGN_LEFT);
	}
	
	/** 生成excel文件并提供下载
	 * @param OutputStream os,
	 * @param String [] titleStrs,List<String[]> contentList
	 * @author FL 2015-6-10
	 */
	public static void createExcel(OutputStream os,String [] titleStrs,List<String[]> contentList) throws WriteException,IOException{
	    //创建工作薄
	    WritableWorkbook workbook = Workbook.createWorkbook(os);
	    //创建新的一页
	    WritableSheet sheet = workbook.createSheet("First Sheet",0);
	    //创建要显示的内容,创建一个单元格，第一个参数为列坐标，第二个参数为行坐标，第三个参数为内容
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