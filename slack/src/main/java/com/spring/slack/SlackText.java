package com.spring.slack;

import java.io.Serializable;


public class SlackText implements Serializable {

    /* --- Static members --- */

    private static final long serialVersionUID = 6933716987386564645L;

    /* --- Members --- */

    private SlackTextType type;
    private String text;

    /* --- Constructor --- */

    public SlackText() {
    }

    public SlackText(SlackTextType type, String text) {
        this.type = type;
        this.text = text;
    }

    /* --- Getters / Setters --- */

    public SlackTextType getType() {
        return type;
    }

    public void setType(SlackTextType type) {
        this.type = type;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
