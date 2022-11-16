package com.bankpin.user.ext.coocon.model.type;

import com.bankpin.user.auth.model.type.AuthorityType;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;

@Getter
@RequiredArgsConstructor
public enum ApiType {

    ADL_101_IQ("SDL_101_IQ", "대출금리한도 조회"),
    ADL_102_IF("SDL_102_IF", "대출금리한도 조회결과 통지"),
    ADL_103_IQ("SDL_103_IQ", "대출신청접수"),
    ADL_104_IF("SDL_104_IF", "대출실행결과통보"),
    ADL_105_IQ("SDL_105_IQ", "대출실행결과확인"),
    ADL_106_IF("SDL_106_IF", "대출취소"),
    ;

    private final String serviceCode;
    private final String description;

    public static ApiType of(String key) {
        return Arrays.stream(ApiType.values())
                .filter(r -> r.toString().equals(key))
                .findAny()
                .orElse(null);
    }

}