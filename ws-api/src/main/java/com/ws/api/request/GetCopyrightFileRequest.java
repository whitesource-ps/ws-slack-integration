package com.ws.api.request;

import com.ws.api.model.ApiRequestType;

public class GetCopyrightFileRequest extends BaseApiRequest {

    /* --- Constructors --- */

    public GetCopyrightFileRequest(String userKey, String productToken) {
        super(ApiRequestType.GET_COPYRIGHT_FILE_REQUEST, userKey, productToken);
    }
}
