package com.ws.api.request;

import com.ws.api.model.ApiRequestType;

public class GetLicensesZipFileRequest extends BaseApiRequest {

    /* --- Constructors --- */

    public GetLicensesZipFileRequest(String userKey, String projectToken) {
        super(ApiRequestType.GET_LICENSES_ZIP_FILE_REQUEST, userKey);
        setProjectToken(projectToken);
    }
}
