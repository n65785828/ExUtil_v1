package org.niyihua.encrypt.encrypt;

import org.bouncycastle.crypto.digests.SM3Digest;
import org.bouncycastle.crypto.macs.HMac;
import org.bouncycastle.crypto.params.KeyParameter;
import org.niyihua.encrypt.utils.ByteUtil;
import org.niyihua.encrypt.utils.CommonUtil;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;

/**
 * @author: zzy
 * @create: 2019/12/24
 */
public class SM3Util extends GMBaseUtil{
    
    public static int saltLength = 8;

    /**
     * 用国密加盐hash
     * @param src 原文
     * @return hash值
     */
    public static String saltHash(String src){
        String salt = CommonUtil.UUID().substring(0, saltLength);
        return salt + ByteUtil.toHexString(SM3Util.hash((src+salt).getBytes(StandardCharsets.UTF_8)));
    }

    /**
     * 用国密验证加盐hash
     * @param src 原文
     * @param dst hash值
     * @return true通过 false不通过
     */
    public static boolean saltVerify(String src, String dst){
        String salt = dst.substring(0, saltLength);
        String hashed = dst.substring(saltLength);
        return ByteUtil.toHexString(SM3Util.hash((src+salt).getBytes(StandardCharsets.UTF_8))).equals(hashed);
    }
    
    public static byte[] hash(byte[] srcData) {
        SM3Digest digest = new SM3Digest();
        digest.update(srcData, 0, srcData.length);
        byte[] hash = new byte[digest.getDigestSize()];
        digest.doFinal(hash, 0);
        return hash;
    }
    
    public static String hash32(String src){
        return ByteUtil.toHexString(hash(src.getBytes(StandardCharsets.UTF_8))).substring(0, 32);
    }

    public static boolean verify(byte[] srcData, byte[] sm3Hash) {
        byte[] newHash = hash(srcData);
        if (Arrays.equals(newHash, sm3Hash)) {
            return true;
        } else {
            return false;
        }
    }

    public static byte[] hmac(byte[] key, byte[] srcData) {
        KeyParameter keyParameter = new KeyParameter(key);
        SM3Digest digest = new SM3Digest();
        HMac mac = new HMac(digest);
        mac.init(keyParameter);
        mac.update(srcData, 0, srcData.length);
        byte[] result = new byte[mac.getMacSize()];
        mac.doFinal(result, 0);
        return result;
    }
}
