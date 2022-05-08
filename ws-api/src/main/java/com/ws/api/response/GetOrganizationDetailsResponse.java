package com.ws.api.response;

public class GetOrganizationDetailsResponse extends BaseApiResponse {

    /* --- Members --- */

    private String orgToken;
    private String orgName;

    /* --- Constructors --- */

    public GetOrganizationDetailsResponse() {
    }

    public GetOrganizationDetailsResponse(String orgToken, String orgName) {
        this.orgToken = orgToken;
        this.orgName = orgName;
    }

    /* --- Getters / Setters --- */

    public String getOrgToken() {
        return orgToken;
    }

    public void setOrgToken(String orgToken) {
        this.orgToken = orgToken;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }
}
