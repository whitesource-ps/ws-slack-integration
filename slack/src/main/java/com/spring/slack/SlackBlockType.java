package com.spring.slack;

import com.google.gson.annotations.SerializedName;

public enum SlackBlockType {

    @SerializedName("section")
    SECTION(0, "section"),
    @SerializedName("divider")
    DIVIDER(1, "divider"),
    @SerializedName("context")
    CONTEXT(2, "context"),
    @SerializedName("image")
    IMAGE(3, "image"),
    @SerializedName("actions")
    ACTIONS(4, "actions");

    /* --- Members --- */

    private int key;
    private String value;

    /* --- Constructor --- */

    SlackBlockType(int key, String value) {
        this.key = key;
        this.value = value;
    }

    /* --- Overridden methods --- */

    @Override
    public String toString() {
        return this.value;
    }

    /* --- Getters / Setters --- */

    public int getKey() {
        return key;
    }

    public void setKey(int key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
