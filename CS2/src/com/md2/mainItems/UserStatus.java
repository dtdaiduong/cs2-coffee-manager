package com.md2.mainItems;

public enum UserStatus {
    LICENSED("LICENSED-USE"),
    UNLICENSED("UNLICENSED-USE");

    private String value;

    UserStatus(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static UserStatus parseUserStatus(String value) {
        UserStatus[] values = values();
        for (UserStatus userStatus : values) {
            if (userStatus.value.equals(value))
                return userStatus;
        }
        throw new IllegalArgumentException("invalid");
    }
}
