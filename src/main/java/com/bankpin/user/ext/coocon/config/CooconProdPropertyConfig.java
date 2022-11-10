package com.bankpin.user.ext.coocon.config;

import com.bankpin.user.ext.coocon.model.type.ApiType;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("prod")
@RequiredArgsConstructor
@Slf4j
public class CooconProdPropertyConfig implements CooconPropertyConfig {

    private final String uri = "https://vaoaapi.coocon.co.kr/";

    @Override
    public String getUri(String apiNm) {
        return uri + apiNm + ".vapi";
    }
}
