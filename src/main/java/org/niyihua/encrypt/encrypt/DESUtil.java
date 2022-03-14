package org.niyihua.encrypt.encrypt;



import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import java.security.Key;


public class DESUtil {

    public static String encrypt(String s, String key){
        try {
            byte[] encrypted = encrypt(s.getBytes(),key.getBytes());
            return byteArr2HexStr(encrypted);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static byte[] encrypt(byte[] arrB, byte[] key) throws Exception {
        Cipher cipher = Cipher.getInstance("DES");
        cipher.init(Cipher.ENCRYPT_MODE,getKey(key));
        return cipher.doFinal(arrB);
    }

    public static String decrypt(String s, String key){
        try {
            byte[] encrypted = decrypt(hexStr2ByteArr(s),key.getBytes());
            return new String(encrypted,"utf-8");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static byte[] decrypt(byte[] bMi, byte[] key) {
        try {
            Cipher cipher = Cipher.getInstance("DES");
            cipher.init(Cipher.DECRYPT_MODE, getKey(key));
            return cipher.doFinal(bMi);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Key getKey(byte[] key) throws Exception {
        DESKeySpec dks = new DESKeySpec(key);
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
        SecretKey securekey = keyFactory.generateSecret(dks);
        return securekey;
    }

    /**
     * 将byte数组转换为表示16进制值的字符串
     * @param arrB
     * @return
     * @throws Exception
     */
    public static String byteArr2HexStr(byte[] arrB) throws Exception {
        int iLen = arrB.length;
        StringBuffer sb = new StringBuffer(iLen * 2);
        for (int i = 0; i < iLen; i++) {
            int intTmp = arrB[i];
            while (intTmp < 0) {
                intTmp = intTmp + 256;
            }
            if (intTmp < 16) {
                sb.append("0");
            }
            sb.append(Integer.toString(intTmp, 16));
        }
        return sb.toString();
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
}
