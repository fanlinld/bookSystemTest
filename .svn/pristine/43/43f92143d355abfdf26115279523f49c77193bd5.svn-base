package com.util;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

public class FileUitl {

	/**
	 * 将文件转成base64 字符串
	 * @param path文件路径
	 * @return  *
	 * @throws Exception
	 */

	public static String encodeBase64File(String path) throws Exception {
		File file = new File(path);;
		FileInputStream inputFile = new FileInputStream(file);
		byte[] buffer = new byte[(int) file.length()];
		inputFile.read(buffer);
		inputFile.close();
		return new BASE64Encoder().encode(buffer);
	}

	/**
	 * 将base64字符解码保存文件
	 * @param base64Code
	 * @param targetPath
	 * @throws Exception
	 */

	public static void decoderBase64File(String base64Code, String targetPath)
			throws Exception {
		byte[] buffer = new BASE64Decoder().decodeBuffer(base64Code);
		FileOutputStream out = new FileOutputStream(targetPath);
		out.write(buffer);
		out.close();

	}

	/**
	 * 将base64字符保存文本文件
	 * @param base64Code
	 * @param targetPath
	 * @throws Exception
	 */

	public static void toFile(String base64Code, String targetPath)
			throws Exception {

		byte[] buffer = base64Code.getBytes();
		FileOutputStream out = new FileOutputStream(targetPath);
		out.write(buffer);
		out.close();
	}

	/** 
	 * 删除单个文件 
	 * @param   sPath    被删除文件的文件名 
	 * @return 单个文件删除成功返回true，否则返回false 
	 */  
	public static boolean deleteFile(String sPath) {  
		boolean flag = false;  
		File file = new File(sPath);  
		// 路径为文件且不为空则进行删除  
		if (file.isFile() && file.exists()) {  
			file.delete();  
			flag = true;  
		}  
		return flag;  
	}

	/**
	 * 读取某个文件夹下的所有文件
	 */
	public static boolean readfile(String filepath) throws FileNotFoundException, IOException {
		String filename="";
		try {
			File file = new File(filepath);
			if (!file.isDirectory()) {
				System.out.println("文件");
				System.out.println("path=" + file.getPath());
				System.out.println("absolutepath=" + file.getAbsolutePath());
				System.out.println("name=" + file.getName());
			} else if (file.isDirectory()) {
				System.out.println("文件夹");
				String[] filelist = file.list();
				for (int i = 0; i < filelist.length; i++) {
					File readfile = new File(filepath + "\\" + filelist[i]);
					if (!readfile.isDirectory()) {
						filename=filename+readfile.getName().substring(0,readfile.getName().lastIndexOf("."))+",";
						System.out.println("path=" + readfile.getPath());
						System.out.println("absolutepath="
								+ readfile.getAbsolutePath());
						System.out.println("name=" + readfile.getName());
					} else if (readfile.isDirectory()) {
						readfile(filepath + "\\" + filelist[i]);
					}
				}
			}
		} catch (FileNotFoundException e) {
			System.out.println("readfile()   Exception:" + e.getMessage());
		}
		System.out.println(filename);
		return true;
	}
	
	public static void main(String[] args) {
		try {
			readfile("E:\\ydd\\封面\\20150815");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
