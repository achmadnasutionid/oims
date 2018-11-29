package com.oims.futureprogram.error;

public enum ErrorCode {

    NOT_FOUND("400", "Data not found"), OK("200", "Data Saved");

    private  String code;

    private String message;

    ErrorCode(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
