package com.bankpin.user.auth.model.type;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;

@Getter
@RequiredArgsConstructor
public enum AuthorityType
{
    GUEST("ROLE_GUEST", "GUEST"),
    USER("ROLE_USER", "Read User"),
    USER_PRINT("ROLE_PRINT_USER", "Read Print User"),
    USER_WRITE("ROLE_WRITE_USER", "Read Write User"),
    USER_WRITE_PRINT("ROLE_WRITE_PRINT_USER", "Read Write Print User"),
    USER_WRITE_REMOVE("ROLE_WRITE_REMOVE_USER", "Read Write Remove User"),
    USER_WRITE_REMOVE_PRINT("ROLE_WRITE_REMOVE_PRINT_USER", "Read Write Remove Print User");

    private final String key;
    private final String label;

    public static AuthorityType of(String key) {
        return Arrays.stream(AuthorityType.values())
                .filter(r -> r.getKey().equals(key))
                .findAny()
                .orElse(GUEST);
    }

}
