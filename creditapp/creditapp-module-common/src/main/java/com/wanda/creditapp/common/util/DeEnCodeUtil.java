package com.wanda.creditapp.common.util;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.security.PublicKey;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

/**
 * ClassName: DeEnCodeUtils <br/>
 * Function: 加解密工具类 . <br/>
 * date: 2016年4月9日 上午9:58:25 <br/>
 *
 * @author chenglin.xiao
 * @author Michael Di
 * @version v1.0.0
 */
@Component
public class DeEnCodeUtil {
	
	private static final Logger logger = Logger.getLogger(DeEnCodeUtil.class);

	public static String encodeDataByPublicKey(String str, final String cer_file_path) throws Exception {
		logger.info("加密用证书路径:"+cer_file_path);
		PublicKey pk = readPublicKey(cer_file_path);
		String encodedValue = null;
		if (pk != null) {
			byte[] encodedData = DESUtil.encrypt(str.getBytes("UTF-8"),
					new SimpleDateFormat("yyyyMMdd").format(new Date()));
			encodedData = RSAUtil.encryptByPublicKey(encodedData, Base64Util.encode(pk.getEncoded()));
			encodedValue = new String(encodedData, "ISO-8859-1");
		}
		return encodedValue;
	}

	public static String decodeDataByPublicKey(String decodeStr, final String cer_file_path) throws Exception {
		byte[] decodedData = null;
		String decodedStr = "";
		logger.info("解密用证书路径:"+cer_file_path);
		PublicKey pk = readPublicKey(cer_file_path);
		if (pk != null) {
			decodedData = RSAUtil.decryptByPublicKey(decodeStr.getBytes("ISO-8859-1"),
					Base64Util.encode(pk.getEncoded()));
			decodedData = DESUtil.decrypt(decodedData, new SimpleDateFormat("yyyyMMdd").format(new Date()));
			decodedStr = new String(decodedData, "utf-8");
		}
		return decodedStr;
	}

	public static PublicKey readPublicKey(final String cer_file_path) {
		InputStream is = null;
		try {
			is = new BufferedInputStream(new FileInputStream(cer_file_path));
		} catch (FileNotFoundException e) {
			try {
				is = Thread.currentThread().getContextClassLoader().getResourceAsStream(cer_file_path);
				is.available();
			} catch (IOException ex) {
				logger.info("Generate certificate failed !!!");
				logger.error(ex.getMessage(), ex.getCause());
				try {
					if (is != null) is.close();
				} catch (IOException e1) {
					// ignore...
				}
				return null;
			}
		}
		
		try {
			CertificateFactory certificatefactory = CertificateFactory.getInstance("X.509");
			X509Certificate cert = (X509Certificate) certificatefactory.generateCertificate(is);
			PublicKey pk = cert.getPublicKey();
			return pk;
		} catch (CertificateException ex) {
			logger.info("Generate certificate failed !!!");
			logger.error(ex.getMessage(), ex.getCause());
			return null;
		} finally {
			try {
				if (is != null) is.close();
			} catch (IOException e1) {
				// ignore...
			}
		}
	}
}
