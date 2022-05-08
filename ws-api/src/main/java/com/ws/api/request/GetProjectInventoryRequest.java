package com.ws.api.request;

import com.ws.api.model.ApiRequestType;

public class GetProjectInventoryRequest extends BaseApiRequest {

    /* --- Constructors --- */

    public GetProjectInventoryRequest(String userKey, String projectToken) {
        super(ApiRequestType.GET_PROJECT_INVENTORY, userKey);
        setProjectToken(projectToken);
    }
}
