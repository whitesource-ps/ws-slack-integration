package com.ws.api.request;

import com.ws.api.model.ApiRequestType;

import java.io.Serializable;

/**
 * Base class for API request
 */
public class BaseApiRequest implements Serializable {

    /* --- Members --- */

    private String requestType;
    private String userKey;
    private String productToken;
    private String projectToken;
    private String orgToken;

    /* --- Constructors --- */

    public BaseApiRequest(ApiRequestType requestType, String userKey, String productToken) {
        this.requestType = requestType.toString();
        this.userKey = userKey;
        this.productToken = productToken;
    }

    public BaseApiRequest(ApiRequestType requestType, String userKey) {
        this.requestType = requestType.toString();
        this.userKey = userKey;
    }

    public BaseApiRequest(ApiRequestType type) {
        this.requestType = type.toString();
    }

    /* --- Getters / Setters --- */

    public String getRequestType() {
        return requestType;
    }

    public void setRequestType(String requestType) {
        this.requestType = requestType;
    }

    public String getUserKey() {
        return userKey;
    }

    public void setUserKey(String userKey) {
        this.userKey = userKey;
    }

    public String getProductToken() {
        return productToken;
    }

    public void setProductToken(String productToken) {
        this.productToken = productToken;
    }

    public String getProjectToken() {
        return projectToken;
    }

    public void setProjectToken(String projectToken) {
        this.projectToken = projectToken;
    }

    public String getOrgToken() {
        return orgToken;
    }

    public void setOrgToken(String orgToken) {
        this.orgToken = orgToken;
    }
}
