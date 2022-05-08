package com.ws.api.response;

import java.io.Serializable;

/**
 * Base class for API response
 */
public class BaseApiResponse implements Serializable {

    /* --- Members --- */

    private String errorCode;
    private String errorMessage;

    /* --- Constructors --- */

    public BaseApiResponse() {
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
