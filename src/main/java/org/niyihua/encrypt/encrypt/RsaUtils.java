package org.niyihua.encrypt.encrypt;


import org.apache.commons.codec.binary.Base64;

import java.util.Map;


public class RsaUtils implements Encrypt {

    /**
     *  生成一对RSA秘钥  RsaEncryption.KEY_SIZE 为生成秘钥大小
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        Map<String, Object> keyPair = RsaEncryption.genKeyPair();
        String privateKey = RsaEncryption.getPrivateKey(keyPair);
        String publicKey = RsaEncryption.getPublicKey(keyPair);
        System.out.println("-------------public key------------------");
        System.out.println(publicKey);
        System.out.println("------------------------------------------");
        System.out.println("-------------private key-----------------");
        System.out.println(privateKey);
        System.out.println("------------------------------------------");
    }

    /**
     *  公钥加密 返回 密文 (BASE64编码)
     * @param plaintext 明文
     * @param publicKey 公钥 (BASE64编码)
     * @return
     * @throws Exception
     */
    public static String encryptByPublicKey(String plaintext,String publicKey) throws Exception{
        byte[] plaintextData = plaintext.getBytes();
        byte[] encryptBytes = RsaEncryption.encryptByPublicKey(plaintextData, publicKey);
        String decryptString = new String(Base64.encodeBase64(encryptBytes));
        return decryptString;
    }

    /**
     *  私钥加密 返回密文 (BASE64编码)
     * @param plaintext 明文
     * @param privateKey 私钥 (BASE64编码)
     * @return
     * @throws Exception
     */
    public static String encryptByPrivateKey(String plaintext,String privateKey) throws Exception{
        byte[] plaintextData = plaintext.getBytes();
        byte[] encryptBytes = RsaEncryption.encryptByPrivateKey(plaintextData, privateKey);
        String decryptString = new String(Base64.encodeBase64(encryptBytes));
        return decryptString;
    }

    /**
     * 私钥解密 返回明文
     * @param cipherText 密文(BASE64编码)
     * @param privateKey  私钥 (BASE64编码)
     * @return
     * @throws Exception
     */
    public static String decryptByPrivateKey(String cipherText,String privateKey) throws Exception{
        byte[] cipherTextData = Base64.decodeBase64(cipherText.getBytes());
        byte[] decryptBytes = RsaEncryption.decryptByPrivateKey(cipherTextData, privateKey);
        String decryptString = new String(decryptBytes);
        return decryptString;
    }

    /**
     * 公钥解密 返回明文
     * @param cipherText 密文(BASE64编码)
     * @param publicKey 公钥(BASE64编码)
     * @return
     * @throws Exception
     */
    public static String decryptByPublicKey(String cipherText,String publicKey) throws Exception{
        byte[] cipherTextData = Base64.decodeBase64(cipherText.getBytes());
        byte[] decryptBytes = RsaEncryption.decryptByPublicKey(cipherTextData, publicKey);
        String decryptString = new String(decryptBytes);
        return decryptString;
    }


    @Override
    public String encrypt(String plain, String publicKey) {
        try {
            return encryptByPublicKey(plain,publicKey);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public String decrypt(String cipher, String privateKey) {
        try {
            return decryptByPrivateKey(cipher,privateKey);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
