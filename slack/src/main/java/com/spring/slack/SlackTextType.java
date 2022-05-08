package com.spring.slack;

import com.google.gson.annotations.SerializedName;

public enum SlackTextType {

    @SerializedName("mrkdwn")
    MRKDWN(0, "mrkdwn"),
    @SerializedName("image")
    IMAGE(1, "image"),
    @SerializedName("button")
    BUTTON(2, "button"),
    @SerializedName("plain_text")
    PLAIN_TEXT(3, "plain_text");

    /* --- Members --- */

    private int key;
    private String value;

    /* --- Constructor --- */

    SlackTextType(int key, String value) {
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
