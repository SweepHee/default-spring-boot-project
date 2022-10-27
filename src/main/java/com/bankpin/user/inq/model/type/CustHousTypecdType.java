package com.bankpin.user.inq.model.type;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;

@Getter
@RequiredArgsConstructor
public enum CustHousTypecdType
{
    APARTMENT("1", "아파트"),
    HOUSE("2", "단독"),
    VILLA("3", "빌라/연립"),
    ETC("4", "기타");

    private final String value;
    private final String label;

    public static CustHousTypecdType of(String value) {
        return (CustHousTypecdType) Arrays.stream(values()).filter((r) -> {
            return r.getValue().equals(value);
        }).findAny().orElse(APARTMENT);
    }

}
