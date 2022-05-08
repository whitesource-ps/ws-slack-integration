package com.ws.api.request;

import com.ws.api.model.ApiRequestType;

public class GetProductAlertsByTypeRequest extends BaseApiRequest {

    /* --- Members --- */

    private String fromDate;
    private String alertType;

    /* --- Constructors --- */

    public GetProductAlertsByTypeRequest(String productToken, String fromDate) {
        super(ApiRequestType.GET_PRODUCT_ALERTS_BY_TYPE);
        setProductToken(productToken);
        this.fromDate = fromDate;
        this.alertType = "SECURITY_VULNERABILITY";
    }

    public GetProductAlertsByTypeRequest(String productToken, String fromDate, String alertType, String userKey) {
        super(ApiRequestType.GET_PRODUCT_ALERTS_BY_TYPE);
        setProductToken(productToken);
        setUserKey(userKey);
        this.fromDate = fromDate;
        this.alertType = alertType;
    }

    /* --- Getters / Setters --- */

    public String getFromDate() {
        return fromDate;
    }

    public void setFromDate(String fromDate) {
        this.fromDate = fromDate;
    }

    public String getAlertType() {
        return alertType;
    }

    public void setAlertType(String alertType) {
        this.alertType = alertType;
    }
}
