package com.ws.api.request;

import com.ws.api.model.ApiRequestType;

public class GetProjectDueDiligenceRequest extends BaseApiRequest {

    /* --- Static members --- */

    public static final String JSON = "json";

    /* --- Members --- */

    private String format;

    /* --- Constructors --- */

    public GetProjectDueDiligenceRequest(String userKey, String projectToken) {
        super(ApiRequestType.GET_PROJECT_DUE_DILIGENCE_REPORT, userKey);
        setProjectToken(projectToken);
        format = JSON;
    }
}
