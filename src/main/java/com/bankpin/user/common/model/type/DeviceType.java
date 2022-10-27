package com.bankpin.user.common.model.type;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;

@Getter
@RequiredArgsConstructor
public enum DeviceType
{
    DESKTOP("DESKTOP"),
    MOBILE("MOBILE"),
    TABLET("TABLET");

    private final String value;

    public static DeviceType of(String value) {
        return (DeviceType) Arrays.stream(values()).filter((r) -> {
            return r.getValue().equals(value);
        }).findAny().orElse(DESKTOP);
    }

}
