package com.bankpin.user.ext.kcb.config;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("local")
@RequiredArgsConstructor
@Slf4j
public class LocalPropertyConfig implements PropertyConfig {

    @Value("${environments.kcb.company-code}")
    private String cpCd;

    private final String target = "PROD";
    private final String path = "D:\\jeon\\workspace\\bankpin-user-server\\libs\\";

    public String getPath () {
        return path + this.cpCd + "_IDS_01_" + target + "_AES_license.dat";
    }

    @Override
    public String getCpCd() {
        return this.cpCd;
    }

    @Override
    public String getTarget() {
        return this.target;
    }

    @Override
    public PropertyConfig getInstance() {
        return this;
    }

}