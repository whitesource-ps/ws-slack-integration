package com.ws.api.request;

import com.ws.api.model.ApiRequestType;

public class GetProductDueDiligenceRequest extends BaseApiRequest {

    /* --- Static members --- */

    public static final String JSON_FORMAT = "json";

    /* --- Members --- */

    private String format;

    /* --- Constructors --- */

    public GetProductDueDiligenceRequest(String userKey, String productToken) {
        super(ApiRequestType.GET_PRODUCT_DUE_DILIGENCE_REPORT, userKey, productToken);
        format = JSON_FORMAT;
    }

    /* --- Getters / Setters --- */

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }
}
