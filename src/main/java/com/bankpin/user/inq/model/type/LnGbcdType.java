package com.bankpin.user.inq.model.type;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;

@Getter
@RequiredArgsConstructor
public enum LnGbcdType
{
    CREDIT("1", "Loan Credit"),
    MORTGAGE("2", "Loan Mortgage");

    private final String value;
    private final String label;

    public static LnGbcdType of(String value) {
        return (LnGbcdType) Arrays.stream(values()).filter((r) -> {
            return r.getValue().equals(value);
        }).findAny().orElse(CREDIT);
    }

}
