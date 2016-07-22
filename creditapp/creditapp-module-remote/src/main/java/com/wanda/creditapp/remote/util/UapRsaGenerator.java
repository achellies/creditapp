package com.wanda.creditapp.remote.util;

import javax.crypto.Cipher;

import org.apache.log4j.Logger;

import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PublicKey;
import java.security.spec.X509EncodedKeySpec;
import java.util.HashMap;
import java.util.Map;

public class UapRsaGenerator {
	private static final Logger logger = Logger.getLogger(UapRsaGenerator.class);
	private static final String publicKey = "305c300d06092a864886f70d0101010500034b00304802410087d5e228e0a221c7f2529101c25d9cbda1d3b88fab726ab67054614d2e5a033cdb78f0e270c85298d8ad10e01afde269cba8008020b75e25b2d5ea17c3dac8290203010001";

	/**
	 * 生成RSA密钥对
	 * 
	 * @return 密钥对java.util.HsahMap对象
	 */
	public static Map<String, String> genKeyPair() {
		Map<String, String> map = null;
		try {
			map = new HashMap<String, String>();
			KeyPairGenerator keyPair = KeyPairGenerator.getInstance("RSA");
			keyPair.initialize(512);
			KeyPair kp = keyPair.generateKeyPair();
			String pubKeyStr = byteArr2HexString(kp.getPublic().getEncoded());
			String priKeyStr = byteArr2HexString(kp.getPrivate().getEncoded());

			map.put("publicKey", pubKeyStr);
			map.put("privateKey", priKeyStr);
		} catch (Exception e) {
			logger.error("生成RSA秘钥异常", e);
			e.printStackTrace();
			map = null;
		}
		return map;
	}

	/**
	 * 公钥加密数据
	 * 
	 * @param data
	 *            需要加密数据
	 * @return 加密数据
	 */
	public static String encryptToRSA(String data) {
		String encryptData = null;
		try {
			KeyFactory keyFac = KeyFactory.getInstance("RSA");
			PublicKey pubKey = keyFac.generatePublic(new X509EncodedKeySpec(hexString2ByteArr(publicKey)));

			Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
			cipher.init(Cipher.ENCRYPT_MODE, pubKey);
			byte[] result = cipher.doFinal(data.getBytes("UTF-8"));
			encryptData = byteArr2HexString(result);
		} catch (Exception e) {
			logger.error("使用RSA秘钥加密数据异常", e);
			e.printStackTrace();
		}
		return encryptData;
	}

	/**
	 * 字节数组转换为十六进制字符串
	 * 
	 * @param bytearr
	 *            字节数组
	 * @return 十六进制字符串
	 */
	public static String byteArr2HexString(byte[] bytearr) {
		if (bytearr == null) {
			return "null";
		}
		StringBuffer sb = new StringBuffer();

		for (int k = 0; k < bytearr.length; k++) {
			if ((bytearr[k] & 0xFF) < 16) {
				sb.append("0");
			}
			sb.append(Integer.toString(bytearr[k] & 0xFF, 16));
		}
		return sb.toString();
	}

	/**
	 * 十六进制字符串转换为字节数组
	 * 
	 * @param hexString
	 *            16进制字符串
	 * @return 字节数组
	 */
	public static byte[] hexString2ByteArr(String hexString) {
		if ((hexString == null) || (hexString.length() % 2 != 0)) {
			return new byte[0];
		}
		byte[] dest = new byte[hexString.length() / 2];

		for (int i = 0; i < dest.length; i++) {
			String val = hexString.substring(2 * i, 2 * i + 2);
			dest[i] = (byte) Integer.parseInt(val, 16);
		}
		return dest;
	}
}
