package org.freightfox.weatherreport.utils;

public enum StatusCode {
    // CUSTOM BUSINESS CODES 1xxxx

    // Base codes
    OK(200, 0, "Success"),
    SERVER_ERROR(400, 1, "Failed!"),
    SUCCESS(201, 0, "Success"),

    // Server & security error codes
    NO_DATA(300, 2, "No data found!"),
    BAD_REQUEST(400, 1, "Bad Request"),
    INTERNAL_SERVER_ERROR(500, 1, "Sorry! Something went wrong.");

    private Integer code;
    private Integer errorState;
    private String statusMessage;

    StatusCode(Integer code, Integer errorState, String statusMessage) {
        this.code = code;
        this.statusMessage = statusMessage;
        this.errorState = errorState;
    }

    public StatusCode getByStatusCode(Integer code) {
        StatusCode[] values = values();
        for (StatusCode statusCode : values) {
            if (statusCode.getCode().equals(code)) {
                return statusCode;
            }
        }
        return StatusCode.SERVER_ERROR;
    }

    public Integer getCode() {
        return this.code;
    }

    public String getStatusMessage() {
        return this.statusMessage;
    }

    public Integer getErrorState() {
        return errorState;
    }
}
