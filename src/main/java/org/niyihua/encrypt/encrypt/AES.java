package org.niyihua.encrypt.encrypt;


import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;


public class AES implements Encrypt{


	@Override
	public String encrypt(String plain, String key) {
		return encryptStr(plain,key);
	}

	@Override
	public String decrypt(String cipher, String key) {
		return decryptStr(cipher,key);
	}

	/**
	 * AES加密
	 * @param s
	 * @param key
	 */
	public static String encryptStr(String s, String key){
		try {
			byte[] raw = key.getBytes("utf-8");
			SecretKeySpec keySpec=new SecretKeySpec(raw, "AES");
			Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");//"算法/模式/补码方式"
			cipher.init(Cipher.ENCRYPT_MODE, keySpec);
			byte[] encrypted = cipher.doFinal(s.getBytes("utf-8"));
			return Base64.getEncoder().encodeToString(encrypted);
		} catch (UnsupportedEncodingException | NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException | IllegalBlockSizeException | BadPaddingException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * AES解密
	 * @param s
	 * @param key
	 */
	public static String decryptStr(String s, String key){
		try {
			byte[] raw = key.getBytes("utf-8");
			SecretKeySpec keySpec=new SecretKeySpec(raw, "AES");
			Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");//"算法/模式/补码方式"
			cipher.init(Cipher.DECRYPT_MODE, keySpec);
			byte[] encrypted = Base64.getDecoder().decode(s.getBytes("utf-8"));
			byte[] original = cipher.doFinal(encrypted);
			return new String(original,"utf-8");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 生成AES秘钥
	 * @param args
	 */
	public static void main(String[] args) throws NoSuchAlgorithmException {
		KeyGenerator kgen = KeyGenerator.getInstance("AES");
		SecureRandom secureRandom = new SecureRandom();
		kgen.init(128, secureRandom);
		SecretKey key = kgen.generateKey();
		System.out.println(Base64.getEncoder().encodeToString(key.getEncoded()));
	}


}
