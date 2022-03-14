package org.niyihua.encrypt.encrypt;



import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


public class MD5 {

	/**
	 * 返回大写的md5加密字符串
	 */
	public static String encrypt(String s){
		char hexDigits[]={'0','1','2','3','4','5','6','7','8','9','A','B','C','D','E','F'};       
        try {
            byte[] btInput = s.getBytes();
            // 获得MD5摘要算法的 MessageDigest 对象
            MessageDigest mdInst = MessageDigest.getInstance("MD5");
            // 使用指定的字节更新摘要
            mdInst.update(btInput);
            // 获得密文
            byte[] md = mdInst.digest();
            // 把密文转换成十六进制的字符串形式
            int j = md.length;
            char str[] = new char[j * 2];
            int k = 0;
            for (int i = 0; i < j; i++) {
                byte byte0 = md[i];
                str[k++] = hexDigits[byte0 >>> 4 & 0xf];
                str[k++] = hexDigits[byte0 & 0xf];
            }
            return new String(str).toUpperCase();
        } catch (Exception e) {
			e.printStackTrace();
            return null;
        }
	}
	
	public static String md5(File file){
		if (!file.isFile()) {
			return null;
		}
		try(FileInputStream fis=new FileInputStream(file)){
			byte[] buffer = new byte[fis.available()];
			fis.read(buffer);
			return md5(buffer);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static String md5(String content){
		return md5(content.getBytes());
	}

	private static String md5(byte[] buffer){
		String result=null;
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			result=toHexString(md.digest(buffer));
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return result;
	}

	private static String toHexString(byte[] bs){
		StringBuilder sb = new StringBuilder();
		if (bs == null || bs.length <= 0) {
			return null;
		}
		for (byte b : bs) {
			int i = b & 0xff;
			String hexString = Integer.toHexString(i);
			if(hexString.length() < 2){
				hexString = "0" + hexString;
			}
			sb.append(hexString);
		}
		return sb.toString();
	}

}
