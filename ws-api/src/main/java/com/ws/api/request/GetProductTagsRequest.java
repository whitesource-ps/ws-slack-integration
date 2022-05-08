package com.ws.api.request;

import com.ws.api.model.ApiRequestType;

public class GetProductTagsRequest extends BaseApiRequest {

    /* --- Members --- */


    /* --- Constructors --- */

    public GetProductTagsRequest(String productToken) {
        super(ApiRequestType.GET_PRODUCT_TAGS);
        setProductToken(productToken);
    }

    public GetProductTagsRequest(String productToken, String userKey) {
        super(ApiRequestType.GET_PRODUCT_TAGS);
        setProductToken(productToken);
        setUserKey(userKey);

    }
}
