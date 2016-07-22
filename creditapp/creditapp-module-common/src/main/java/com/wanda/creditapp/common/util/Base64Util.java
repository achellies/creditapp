package com.wanda.creditapp.common.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.apache.commons.codec.binary.Base64;

/**
 * ClassName: Base64Utils <br/>
 * Function: BASE64编码解码工具包. <br/>
 * date: 2016年4月9日 上午9:55:29 <br/>
 *
 * @author chenglin.xiao
 * @author Michael Di
 * @version 1.0
 */
public class Base64Util {

	/**
	 * BASE64字符串解码为二进制数据
	 *
	 * @author Michael Di
	 * @param base64 BASE64字符串
	 * @return BASE64解码成二进制数据
	 * @throws Exception
	 */
	public static byte[] decode(String base64) {
		return Base64.decodeBase64(base64.getBytes());
	}

	/**
	 * 二进制数据编码为BASE64字符串
	 *
	 * @author Michael Di
	 * @param bytes 二进制数据
	 * @return BASE64字符串
	 * @throws Exception
	 */
	public static String encode(byte[] bytes) {
		return new String(Base64.encodeBase64(bytes));
	}

	/**
	 * 将文件编码为BASE64字符串
	 *
	 * @author Michael Di
	 * @param filePath 文件绝对路径
	 * @return BASE64字符串
	 * @throws Exception
	 */
	public static String encodeFile(String filePath) throws Exception {
		byte[] bytes = fileToByte(filePath);
		return encode(bytes);
	}

	/**
	 * BASE64字符串转回文件
	 *
	 * @author Michael Di
	 * @param filePath 文件路径
	 * @param base64 BASE64字符串
	 * @throws Exception
	 */
	public static void decodeToFile(String filePath, String base64) throws Exception {
		byte[] bytes = decode(base64);
		byteArrayToFile(bytes, filePath);
	}

	/**
	 * 文件转换为二进制数组
	 *
	 * @author Michael Di
	 * @param filePath 文件路径
	 * @return 二进制数据
	 */
	public static byte[] fileToByte(String filePath) {
		byte[] data = new byte[0];
		File file = new File(filePath);
		if (file.exists()) {
			FileInputStream in = null;
			ByteArrayOutputStream out = null;
			try {
				in = new FileInputStream(file);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
			out = new ByteArrayOutputStream(2048);
			byte[] cache = new byte[1024];
			int nRead = 0;
			try {
				while ((nRead = in.read(cache)) != -1) {
					out.write(cache, 0, nRead);
					out.flush();
				}
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				try {
					out.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
				try {
					in.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			data = out.toByteArray();
		}
		return data;
	}

	/**
	 * 二进制数据写文件
	 *
	 * @author Michael Di
	 * @param bytes 二进制数据
	 * @param filePath 文件路径
	 * @throws Exception
	 */
	public static void byteArrayToFile(byte[] bytes, String filePath) throws Exception {
		InputStream in = new ByteArrayInputStream(bytes);
		File destFile = new File(filePath);
		if (!destFile.getParentFile().exists()) {
			destFile.getParentFile().mkdirs();
		}
		destFile.createNewFile();
		OutputStream out = new FileOutputStream(destFile);
		byte[] cache = new byte[1024];
		int nRead = 0;
		while ((nRead = in.read(cache)) != -1) {
			out.write(cache, 0, nRead);
			out.flush();
		}
		out.close();
		in.close();
	}
	
	public static String fileToBase64(File file){
		InputStream inputStream = null;
		try{
			inputStream = new FileInputStream(file);
		}catch(FileNotFoundException e){
			return null;
		}
		byte[] targetBytes = new byte[(int)file.length()];
		try {
			inputStream.read(targetBytes);
			inputStream.close();
		} catch (IOException e) {
			return null;
		}
		return Base64Util.encode(targetBytes);
	}
}
