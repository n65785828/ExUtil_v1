package org.niyihua.encrypt.encrypt;


import cn.hutool.core.util.StrUtil;


import java.io.UnsupportedEncodingException;


public class Base64 {

	public static String encrypt(String s){
		if(StrUtil.isEmpty(s))return null;
		try {
			return java.util.Base64.getEncoder().encodeToString(s.getBytes("utf-8"));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static String encrypt(byte[] bytes){
		if(bytes==null)return null;
		return java.util.Base64.getEncoder().encodeToString(bytes);
	}

	public static String decrypt(String s){
		if(StrUtil.isEmpty(s))return null;
		try {
			return new String(java.util.Base64.getDecoder().decode(s),"utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static void main(String[] args) {

	}

}
