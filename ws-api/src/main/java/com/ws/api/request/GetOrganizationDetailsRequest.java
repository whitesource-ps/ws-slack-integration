package com.ws.api.request;

import com.ws.api.model.ApiRequestType;

public class GetOrganizationDetailsRequest extends BaseApiRequest {

    /* --- Constructors --- */

    public GetOrganizationDetailsRequest(String orgToken) {
        super(ApiRequestType.GET_ORGANIZATION_DETAILS);
        setOrgToken(orgToken);
    }

    public GetOrganizationDetailsRequest(String orgToken, String userKey) {
        super(ApiRequestType.GET_ORGANIZATION_DETAILS);
        setOrgToken(orgToken);
        setUserKey(userKey);
    }
}
