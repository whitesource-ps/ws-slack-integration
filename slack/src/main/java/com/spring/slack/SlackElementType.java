package com.spring.slack;

import com.google.gson.annotations.SerializedName;

public enum SlackElementType {

    @SerializedName("button")
    BUTTON(0, "button");

    /* --- Members --- */

    private int key;
    private String value;

    /* --- Constructor --- */

    SlackElementType(int key, String value) {
        this.key = key;
        this.value = value;
    }

    /* --- Overridden methods --- */

    @Override
    public String toString() {
        return this.value;
    }
}
