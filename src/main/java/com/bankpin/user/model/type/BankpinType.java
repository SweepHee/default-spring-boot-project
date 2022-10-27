package com.bankpin.user.model.type;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;

@Getter
@RequiredArgsConstructor
public enum BankpinType
{
    SYSTEM_ID("bankpinsystemadmbankpinsystemadm"),
    SIGNOUT_ID("signoutsignoutsignoutsignoutsign")
    ;

    private final String value;

    public static BankpinType of(String key) {
        return (BankpinType) Arrays.stream(values()).filter((r) -> {
            return r.getValue().equals(key);
        }).findAny().orElse(SYSTEM_ID);
    }

}
