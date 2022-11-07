package com.bankpin.user.ext.coocon.encrypt;

public interface Encryptor {

    String encrypt(String str);
    String decrypt(String str);

}