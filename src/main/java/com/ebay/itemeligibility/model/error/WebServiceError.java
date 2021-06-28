package com.ebay.itemeligibility.model.error;

import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * Generic error class for the validation error response
 */

@JsonInclude(JsonInclude.Include.NON_NULL)
public class WebServiceError {

    private int code;
    private String message;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public WebServiceError(int code, String message) {
        super();
        this.code = code;
        this.message = message;
    }

    @Override
    public String toString() {
        return "WSError [code=" + code + ", message=" + message + "]";
    }
}
