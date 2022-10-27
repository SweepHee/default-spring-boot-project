package com.bankpin.user.sns.auth.model.type;

import com.bankpin.user.auth.model.type.AuthorityType;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;

@Getter
@RequiredArgsConstructor
public enum SnsType {

    NAVER, KAKAO;


}