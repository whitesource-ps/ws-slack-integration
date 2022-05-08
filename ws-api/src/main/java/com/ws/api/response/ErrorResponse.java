package com.ws.api.response;

public class ErrorResponse extends BaseApiResponse {

    /* --- Members --- */

    private String errorCode;
    private String errorMessage;

    /* --- Constructors --- */

    public ErrorResponse() {
    }

    public ErrorResponse(String errorCode, String errorMessage) {
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }

    /* --- Getters / Setters --- */

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
