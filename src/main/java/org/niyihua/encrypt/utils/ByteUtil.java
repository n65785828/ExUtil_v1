package org.niyihua.encrypt.utils;

public class ByteUtil {

	/**
	 * 将byte字节直接转为字符串
	 * @return String  BF1503
	 */
	public static String toHexString(byte ... bs){
		String s="";
		for(byte b:bs){
			s+=((b&0xFF)<=15?"0":"")+ Integer.toHexString((b&0xFF));
		}
		return s;
	}
	
	/**
	 * 将byte字节直接转为字符串
	 * @param separator 分隔符
	 * @param bs   0xBF 0x15 0x03
	 * @return
	 */
	public static String toHexString(String separator, byte[] bs){
		String s="";
		if(bs==null)return s;
		for(byte b:bs){
			s+=((b&0xFF)<=15?"0":"")+ Integer.toHexString((b&0xFF))+separator;
		}
		return s;
	}
	
	public static String toHexString(String separator, int lineBreak, byte[] bs){
		String s="";
		for(int i=0;i<bs.length;i++){
			byte b=bs[i];
			s+=((b&0xFF)<=15?"0":"")+ Integer.toHexString((b&0xFF))+separator;
			if((i+1)%lineBreak==0)s+=(System.getProperty("line.separator"));
		}
		return s;
	}
	
	public static String toHexString(String separator, int lineBreak, byte[] bs, int length){
		String s="";
		for(int i=0;i<length;i++){
			byte b=bs[i];
			s+=((b&0xFF)<=15?"0":"")+ Integer.toHexString((b&0xFF))+separator;
			if((i+1)!=length && (i+1)%lineBreak==0)s+=(System.getProperty("line.separator"));
		}
		return s;
	}
	
	public static byte[] toByte(String s, String separator) {
		String[] ss=s.split(separator);
		byte[] bs=new byte[ss.length];
		for(int i=0;i<ss.length;i++) {
			bs[i]=(byte) Integer.parseInt(ss[i],16);
		}
		return bs;
	}
	
	public static int toInt(byte b4, byte b3, byte b2, byte b1) {
		return b1 & 0xFF | (b2 & 0xFF) << 8 | (b3 & 0xFF) << 16 | (b4 & 0xFF) << 24;
	}

	public static int toInt(byte bH,byte bL){
		return ((bH&0xFF)<<8)|(bL&0xFF);
	}
	
	public static int toIntSign(byte bH,byte bL){
		return (((bH&0x7f)<<8)|(bL&0xFF))*((bH & 0x80)==0?1:-1);
	}

	/**
	 * 将表示16进制值的字符串转换为byte数组
	 * @param strIn
	 * @return
	 * @throws Exception
	 */
	public static byte[] hexStr2ByteArr(String strIn) throws Exception {
		byte[] arrB = strIn.getBytes();
		int iLen = arrB.length;
		byte[] arrOut = new byte[iLen / 2];
		for (int i = 0; i < iLen; i = i + 2) {
			String strTmp = new String(arrB, i, 2);
			arrOut[i / 2] = (byte) Integer.parseInt(strTmp, 16);
		}
		return arrOut;
	}
	
	public static byte[] intToBytes( int value ){   
	    byte[] src = new byte[4];  
	    src[0] =  (byte) ((value>>24) & 0xFF);  
	    src[1] =  (byte) ((value>>16) & 0xFF);  
	    src[2] =  (byte) ((value>>8) & 0xFF);    
	    src[3] =  (byte) (value & 0xFF);                  
	    return src;   
	}
}
