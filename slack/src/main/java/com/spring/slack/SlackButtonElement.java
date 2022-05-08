package com.spring.slack;

import java.io.Serializable;

public class SlackButtonElement implements Serializable {

    /* --- Static members --- */

    private static final long serialVersionUID = -1747059782660016223L;

    /* --- Members --- */

    private SlackTextType type;
    private String url;
    private SlackText text;

    /* --- Constructor --- */

    public SlackButtonElement() {
    }

    public SlackButtonElement(SlackTextType type, String url, SlackText text) {
        this.type = type;
        this.url = url;
        this.text = text;
    }

    /* --- Getters / Setters --- */

    public SlackTextType getType() {
        return type;
    }

    public void setType(SlackTextType type) {
        this.type = type;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public SlackText getText() {
        return text;
    }

    public void setText(SlackText text) {
        this.text = text;
    }
}
