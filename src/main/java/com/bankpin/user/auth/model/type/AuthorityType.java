package com.bankpin.user.auth.model.type;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;

@Getter
@RequiredArgsConstructor
public enum AuthorityType
{
    DEFAULT("ROLE_DEFAULT", "DEFAULT"),
    GUEST("ROLE_GUEST", "GUEST"),
    USER("ROLE_USER", "USER");

    private final String key;
    private final String label;

    public static AuthorityType of(String key) {
        return Arrays.stream(AuthorityType.values())
                .filter(r -> r.getKey().equals(key))
                .findAny()
                .orElse(GUEST);
    }

}
