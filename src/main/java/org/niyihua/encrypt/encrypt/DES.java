package org.niyihua.encrypt.encrypt;



import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;


public class DES implements Encrypt{

	@Override
	public String encrypt(String plain, String key) {
		return encryptStr(plain,key);
	}

	@Override
	public String decrypt(String cipher, String key) {
		return decryptStr(cipher,key);
	}
	
	public static String encryptStr(String s, String key){
		try {
			byte[] encrypted = encrypt(s.getBytes("utf-8"),key.getBytes("utf-8"));
			return Base64.getEncoder().encodeToString(encrypted);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static String decryptStr(String s, String key){
		try {
			byte[] encrypted = Base64.getDecoder().decode(s.getBytes("utf-8"));
			return new String(decrypt(encrypted,key.getBytes("utf-8")),"utf-8");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static String encryptBase64(byte[] byteMing, String key){
		try {
			byte[] encrypted = encrypt(byteMing,key.getBytes("utf-8"));
			return Base64.getEncoder().encodeToString(encrypted);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 加密
	 * @param byteMing  明文(字节)
	 * @param key 密钥，长度必须是8的倍数
	 * @return 密文(字节)
	 * @throws Exception
	 */
	public static byte[] encrypt(byte[] byteMing, byte[] key){
		try {
			// DES算法要求有一个可信任的随机数源
			SecureRandom sr = new SecureRandom();
			// 从原始密匙数据创建DESKeySpec对象
			DESKeySpec dks = new DESKeySpec(key);
			// 创建一个密匙工厂，然后用它把DESKeySpec转换成一个SecretKey对象
			SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
			SecretKey securekey = keyFactory.generateSecret(dks);
			// Cipher对象实际完成加密操作
			Cipher cipher = Cipher.getInstance("DES");
			// 用密匙初始化Cipher对象
			cipher.init(Cipher.ENCRYPT_MODE, securekey, sr);
			// 现在，获取数据并加密
			// 正式执行加密操作
			return cipher.doFinal(byteMing);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 解密
	 * @param bMi 密文(字节)
	 * @param key 密钥，长度必须是8的倍数
	 * @return 明文(字节)
	 * @throws Exception
	 */
	public static byte[] decrypt(byte[] bMi, byte[] key) {
		try {
			// DES算法要求有一个可信任的随机数源
			SecureRandom sr = new SecureRandom();
			// 从原始密匙数据创建一个DESKeySpec对象
			DESKeySpec dks = new DESKeySpec(key);
			// 创建一个密匙工厂，然后用它把DESKeySpec对象转换成
			// 一个SecretKey对象
			SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
			SecretKey securekey = keyFactory.generateSecret(dks);
			// Cipher对象实际完成解密操作
			Cipher cipher = Cipher.getInstance("DES");
			// 用密匙初始化Cipher对象
			cipher.init(Cipher.DECRYPT_MODE, securekey, sr);
			// 现在，获取数据并解密
			// 正式执行解密操作
			return cipher.doFinal(bMi);
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
		KeyGenerator kgen = KeyGenerator.getInstance("DES");
		SecureRandom secureRandom = new SecureRandom();
		kgen.init(56, secureRandom);
		SecretKey key = kgen.generateKey();
		System.out.println(Base64.getEncoder().encodeToString(key.getEncoded()));
	}



}
