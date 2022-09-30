package com.bankpin.user.auth.model.type;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;

@Getter
@RequiredArgsConstructor
public enum AuthErrorType
{
    PASSWORD_NOT_FOUND("PASSWORD_NOT_FOUND", "비밀번호를 확인해 주세요."),
    USER_ALREADY_EXISTS("USER_ALREADY_EXISTS", "이미 등록된 ID 입니다."),
    SIGN_UP_ERROR("SIGN_UP_ERROR", "회원가입이 되지 않았습니다. 관리자에게 문의 해주세요."),
    AUTH_SYSTEM_ERROR("AUTH_SYSTEM_ERROR", "요청이 처리되지 않았습니다. 관리자에게 문의 해주세요."),
    UNAUTHENTICATED("UNAUTHENTICATED", "인증되지 않았습니다."),
    DISABLED_EXCEPTION("DISABLED_EXCEPTION", "사용할 수 없는 계정입니다. 관리자에게 문의하세요."),
    CREDENTIALS_EXPIRED_EXCEPTION("CREDENTIALS_EXPIRED_EXCEPTION", "인증이 만료된 계정입니다."),
    BAD_CREDENTIALS_EXCEPTION("BAD_CREDENTIALS_EXCEPTION", "아이디 또는 비밀번호가 일치하지 않습니다."),
    LOCKED_EXCEPTION("LOCKED_EXCEPTION", "사용 정지된 계정입니다."),
    ACCOUNT_EXPIRED_EXCEPTION("ACCOUNT_EXPIRED_EXCEPTION", "계정이 만료되었습니다."),
    ;

    private final String key;
    private final String message;

    public static AuthErrorType of(String key) {
        return Arrays.stream(AuthErrorType.values())
                .filter(r -> r.getKey().equals(key))
                .findAny()
                .orElse(AUTH_SYSTEM_ERROR);
    }

}
