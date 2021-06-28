package com.ebay.itemeligibility.model;

import com.ebay.itemeligibility.model.error.WebServiceError;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
/**
 * Generic response class for the ouputs from APIs
 *
 */

public class WebServiceResponse<T> {

    private T data;

    private String status;

    private WebServiceError error;

    public WebServiceResponse() {
        super();
    }

    public WebServiceResponse(T data) {
        super();
        if (data instanceof WebServiceError) {
            this.error = (WebServiceError) data;
            this.status = "FAIL";
        } else {
            this.data = data;
            this.status = "OK";
        }
    }

    public Object getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public WebServiceError getError() {
        return error;
    }

    public void setError(WebServiceError error) {
        this.error = error;
    }

    @Override
    public String toString() {
        return "WebServiceResponse [data=" + data + ", status=" + status + ", error=" + error + "]";
    }
}
