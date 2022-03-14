package org.niyihua.encrypt.encrypt;

public interface Encrypt {
    String encrypt(String plain,String key);
    String decrypt(String cipher,String key);
}
