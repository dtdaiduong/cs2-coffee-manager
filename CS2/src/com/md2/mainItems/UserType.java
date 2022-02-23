package com.md2.mainItems;

public enum UserType {
    ADMIN("ADMIN"),
    USER("USER");

    private String value;

    UserType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static UserType parseUserType(String value) {
        UserType[] values = values();
        for (UserType userType : values) {
            if (userType.value.equals(value))
                return userType;
        }
        throw new IllegalArgumentException("invalid");
    }

}
