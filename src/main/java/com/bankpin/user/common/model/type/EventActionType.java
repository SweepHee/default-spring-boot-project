package com.bankpin.user.common.model.type;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;

@Getter
@RequiredArgsConstructor
public enum EventActionType
{
    READ("1", "list, detail"),
    ADD("2", "add"),
    EDIT("3", "edit"),
    REMOVE("4", "remove");

    private final String value;
    private final String label;

    public static EventActionType of(String value) {
        return (EventActionType) Arrays.stream(values()).filter((r) -> {
            return r.getValue().equals(value);
        }).findAny().orElse(READ);
    }
}
