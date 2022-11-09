package com.bankpin.user.ext.coocon.config.encrypt;

public interface Encryptor {

    String encrypt(String str);
    String decrypt(String str);

}