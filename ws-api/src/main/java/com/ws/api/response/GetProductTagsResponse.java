package com.ws.api.response;

import com.ws.api.model.AlertInfo;
import com.ws.api.model.ProductTags;

import java.util.Collection;
import java.util.LinkedList;

public class GetProductTagsResponse extends BaseApiResponse {

    /* --- Members --- */

    private Collection<ProductTags> productTags;

    /* --- Constructors --- */

    public GetProductTagsResponse() {
        productTags = new LinkedList<>();
    }

    public Collection<ProductTags> getProductTags() {
        return productTags;
    }

    public void setProductTags(Collection<ProductTags> productTags) {
        this.productTags = productTags;
    }

}
