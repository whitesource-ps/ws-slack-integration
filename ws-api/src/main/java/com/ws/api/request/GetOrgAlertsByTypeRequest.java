package com.ws.api.request;

import com.ws.api.model.ApiRequestType;

public class GetOrgAlertsByTypeRequest extends BaseApiRequest {

    /* --- Members --- */

    private String fromDate;
    private String alertType;

    /* --- Constructors --- */

    public GetOrgAlertsByTypeRequest(String orgToken, String fromDate) {
        super(ApiRequestType.GET_ORG_ALERTS_BY_TYPE);
        setOrgToken(orgToken);
        this.fromDate = fromDate;
        this.alertType = "SECURITY_VULNERABILITY";
    }

    public GetOrgAlertsByTypeRequest(String orgToken, String fromDate, String alertType, String userKey) {
        super(ApiRequestType.GET_ORG_ALERTS_BY_TYPE);
        setOrgToken(orgToken);
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
