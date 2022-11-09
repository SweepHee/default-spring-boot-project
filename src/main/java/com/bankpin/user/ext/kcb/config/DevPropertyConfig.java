package com.bankpin.user.ext.kcb.config;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;


@Configuration
@Profile("dev")
@RequiredArgsConstructor
@Slf4j
public class DevPropertyConfig implements PropertyConfig {

    @Value("${environments.kcb.company-code}")
    private String cpCd;

    private final String target = "PROD";
    private final String path = "/home/abcwas/test/";

    public String getCpCd () {
        return this.cpCd;
    }

    public String getTarget () {
        return this.target;
    }

    @Override
    public PropertyConfig getInstance() {
        return this;
    }

    public String getPath () {
        return path + this.cpCd + "_IDS_01_" + target + "_AES_license.dat";
    }

}