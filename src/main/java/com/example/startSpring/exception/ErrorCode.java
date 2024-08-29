package com.example.startSpring.exception;

public enum ErrorCode {
    USER_EXISTED(500, "User existed."),
    INVALID_KEY(503,"Invalid key"),
    USERNAME_INVALID(501, "Username must be at least 3 characters."),
    PASSWORD_INVALID(502, "Password must be at least 8 characters."),
    USER_NOT_EXIST(504, "User not existed.")
    ;
     ErrorCode(int code, String message) {
        this.code = code;
        this.message = message;
    }

    private int code;
    private String message;

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
