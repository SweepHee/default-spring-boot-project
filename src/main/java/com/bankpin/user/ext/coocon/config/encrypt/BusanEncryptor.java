package com.bankpin.user.ext.coocon.config.encrypt;

public class BusanEncryptor implements Encryptor {


    @Override
    public String encrypt(String str) {
        return str;
    }

    @Override
    public String decrypt(String str) {
        return str;
    }


}