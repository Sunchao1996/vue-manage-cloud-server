package com.sc.util.base64;

import org.apache.commons.codec.binary.Base64;

import java.io.*;

/**
 * Created by 孔垂云 on 2017/9/16.
 */
public class Base64Util {
	public static String getImageStr(String imgFile) {
		InputStream inputStream = null;
		byte[] data = null;
		try {
			inputStream = new FileInputStream(imgFile);
			data = new byte[inputStream.available()];
			inputStream.read(data);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				inputStream.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return Base64.encodeBase64String(data);
	}

	/**
	 * 将base64编码字符串转换为图片
	 *
	 * @param imgStr
	 *            base64编码字符串
	 * @param path
	 *            图片路径-具体到文件
	 * @return
	 */
	public static boolean generateImage(String imgStr, String path) {
		if (imgStr == null)
			return false;
		try {
			// 解密
			byte[] b = Base64.decodeBase64(imgStr);
			// 处理数据
			for (int i = 0; i < b.length; ++i) {
				if (b[i] < 0) {
					b[i] += 256;
				}
			}
			OutputStream out = new FileOutputStream(path);
			out.write(b);
			out.flush();
			out.close();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public static void main(String[] args) {
		String imgStr = Base64Util.getImageStr("D:\\1.png");
		System.out.println(imgStr);
		// Base64Util.generateImage(imgStr, "D:\\2.png");
	}
}
