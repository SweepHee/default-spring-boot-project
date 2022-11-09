package com.bankpin.user.ext.coocon.model.type;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ApiProperties {

    ADL_101_IQ("SDL_101_IQ", "대출금리한도 조회"),
    ADL_102_IF("SDL_102_IF", "대출금리한도 조회결과 통지"),
    ADL_103_IQ("SDL_103_IQ", "대출신청접수"),
    ADL_104_IF("SDL_104_IF", "대출실행결과통보"),
    ADL_105_IQ("SDL_105_IQ", "대출실행결과확인"),
    ADL_106_IF("SDL_106_IF", "대출취소"),
    ;

    private final String serviceCode;
    private final String description;

}