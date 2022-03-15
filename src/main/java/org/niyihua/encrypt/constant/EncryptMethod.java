package org.niyihua.encrypt.constant;

import org.niyihua.encrypt.encrypt.*;

public enum EncryptMethod {
    AES(new org.niyihua.encrypt.encrypt.AES()),
    DES(new org.niyihua.encrypt.encrypt.DES()),
    RSA(new RsaUtils()),
    SM4(new SM4Util()),
    SM2(new SM2Util()),
    MD5(new org.niyihua.encrypt.encrypt.MD5());

    private Encrypt encrypt;

    EncryptMethod(Encrypt encrypt) {
        this.encrypt = encrypt;
    }

    public Encrypt getEncryption(){
        return encrypt;
    }
}
