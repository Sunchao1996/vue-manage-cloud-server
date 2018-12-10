package com.sc.util.file;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

/**
 * 文件处理工具类
 *
 * @author 孔垂云
 * @date 2017年2月7日
 */
public class FileUtil {
	/**
	 * 以行为单位读取文件，常用于读面向行的格式化文件
	 *
	 * @param filePath
	 *            文件名，路径加文件名
	 * @author 孔垂云
	 * @date 2017年2月7日
	 */
	public static String readFile(String filePath) {
		StringBuilder sb = new StringBuilder("");
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new InputStreamReader(new FileInputStream(filePath), "UTF-8"));
			String tempString = null;
			// int line = 1;
			// 一次读入一行，直到读入null为文件结束
			while ((tempString = reader.readLine()) != null) {
				sb.append(tempString).append("\r\n");
				// line++;
			}
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e1) {

				}
			}
		}
		return sb.toString();
	}

	/**
	 * 写文件
	 *
	 * @param filePath
	 *            文件全路径
	 * @param fileContent
	 *            文件内容
	 * @author 孔垂云
	 * @date 2017年2月7日
	 */
	public static int writeFile(String filePath, String fileContent) {
		int flag = 0;
		try {
			String path = filePath.substring(0, filePath.lastIndexOf("\\\\"));
			File fileDir = new File(path); // 判断文件夹是否存在,如果不存在则创建文件夹
			if (!fileDir.exists()) {
				fileDir.mkdir();
			}
			File file = new File(filePath);
			if (!file.exists()) {
				file.createNewFile();
			}
			OutputStreamWriter write = new OutputStreamWriter(new FileOutputStream(file), "UTF-8");
			BufferedWriter writer = new BufferedWriter(write);
			writer.write(fileContent);
			writer.close();
			flag = 1;
		} catch (Exception e) {
			System.out.println("写文件内容操作出错");
			System.out.println(filePath);
			e.printStackTrace();
		}
		return flag;
	}

	/**
	 * 写文件
	 *
	 * @param filePath
	 *            路径
	 * @param filename
	 *            文件名
	 * @param fileContent
	 *            文件内容
	 * @return
	 */
	public static int writeFile(String filePath, String filename, String fileContent) {
		int flag = 0;
		try {
			createFileDir(filePath);
			File file = new File(filePath + File.separator + filename);
			if (!file.exists()) {
				file.createNewFile();
			}
			OutputStreamWriter write = new OutputStreamWriter(new FileOutputStream(file), "UTF-8");
			BufferedWriter writer = new BufferedWriter(write);
			writer.write(fileContent);
			writer.close();
			flag = 1;
		} catch (Exception e) {
			System.out.println("写文件内容操作出错");
			System.out.println(filePath);
			e.printStackTrace();
		}
		return flag;
	}

	/**
	 * 新建文件夹
	 *
	 * @param path
	 */
	public static void createFileDir(String path) {
		File fileDir = new File(path); // 判断文件夹是否存在,如果不存在则创建文件夹
		if (!fileDir.exists()) {
			fileDir.mkdirs();
		}

	}

	/**
	 * 删除文件
	 *
	 * @param path_name
	 *            路径
	 */
	public static void delete(String path_name) {
		File dest = new File(path_name);
		if (dest.exists())
			dest.delete();
	}

	/**
	 * 复制单个文件
	 *
	 * @param oldPath
	 *            String 原文件路径 如：c:/fqf.txt
	 * @param newPath
	 *            String 复制后路径 如：f:/fqf.txt
	 * @return boolean
	 */
	public static void copyFile(String oldPath, String newPath) {
		InputStream inStream = null;
		FileOutputStream fs = null;
		try {
			int bytesum = 0;
			int byteread = 0;
			File oldfile = new File(oldPath);
			if (oldfile.exists()) { // 文件存在时
				inStream = new FileInputStream(oldPath); // 读入原文件
				fs = new FileOutputStream(newPath);
				byte[] buffer = new byte[1444];
				// int length;
				while ((byteread = inStream.read(buffer)) != -1) {
					bytesum += byteread; // 字节数 文件大小
					fs.write(buffer, 0, byteread);
				}
				inStream.close();
			}
		} catch (Exception e) {
			System.out.println("复制单个文件操作出错");
			e.printStackTrace();
		} finally {
			try {
				inStream.close();
				fs.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}

	/**
	 * 删除文件夹
	 *
	 * @param file
	 */
	public static void deleteFile(File file) {
		if (file.exists()) {// 判断文件是否存在
			if (file.isFile()) {// 判断是否是文件
				file.delete();// 删除文件
			} else if (file.isDirectory()) {// 否则如果它是一个目录
				File[] files = file.listFiles();// 声明目录下所有的文件 files[];
				for (int i = 0; i < files.length; i++) {// 遍历目录下所有的文件
					deleteFile(files[i]);// 把每个文件用这个方法进行迭代
				}
				file.delete();// 删除文件夹
			}
		} else {
			System.out.println("所删除的文件不存在");
		}
	}

}
