package com.ws.api.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.Collection;

public class ProductTags implements Serializable {

    /* --- Members --- */

    private String name;
    private String token;
    private Tags tags;

    /* --- Constructors --- */

    public ProductTags() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

}
