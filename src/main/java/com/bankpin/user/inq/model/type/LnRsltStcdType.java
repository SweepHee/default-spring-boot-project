package com.bankpin.user.inq.model.type;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;

@Getter
@RequiredArgsConstructor
public enum LnRsltStcdType
{
    ACCEPT("1", "승인(가심사 대출가능)"),
    PROGRESS("2", "한도 산출중"),
    REJECT("3", "부결(가심사대출불가)"),
    FAIL("4", "오류");

    private final String value;
    private final String label;

    public static LnRsltStcdType of(String value) {
        return (LnRsltStcdType) Arrays.stream(values()).filter((r) -> {
            return r.getValue().equals(value);
        }).findAny().orElse(ACCEPT);
    }

}
