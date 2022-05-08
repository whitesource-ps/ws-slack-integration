package com.spring.slack;

import com.spring.model.VulnerabilityInfo;
import com.ws.api.model.LibraryInfo;

import java.io.Serializable;

public class SlackElement implements Serializable {

    /* --- Static members --- */

    private static final long serialVersionUID = -3508426614582116263L;

    /* --- Members --- */

    private SlackTextType type;
    private String image_url;
    private String alt_text;
    private String text;

    /* --- Constructor --- */

    public SlackElement() {
    }

    public SlackElement(SlackTextType type) {
        this.type = type;
    }

    public SlackElement(SlackTextType type, String image_url, String alt_text) {
        this.type = type;
        this.image_url = image_url;
        this.alt_text = alt_text;
    }

    public SlackElement(SlackTextType type, String text) {
        this.type = type;
        this.text = text;
    }

    /* --- Public methods --- */

    public static SlackElement createWhitespaceElement() {
        return new SlackElement(SlackTextType.MRKDWN, "\\t\\t");
    }

    public static SlackElement createVulnerabilityElement(VulnerabilityInfo vulnerability) {
        SlackElement element = new SlackElement(SlackTextType.MRKDWN);
        StringBuilder sb = new StringBuilder();
        sb.append("*<");
        sb.append(vulnerability.getFullVulnerabilityUrl());
        sb.append("|");
        sb.append(vulnerability.getCve());
        sb.append(">*");
        element.setText(sb.toString());
        return element;
    }

    public static SlackElement createLibraryElement(LibraryInfo library) {
        return new SlackElement(SlackTextType.MRKDWN, "*<https://saas.whitesourcesoftware.com/Wss/WSS.html#!libraryDetails;uuid=" + library.getKeyUuid() + ";orgToken=e9a074aa-7e7c-4315-9127-19d891327827|" + library.getFilename() + ">*");
    }

    public static SlackElement createOccurrencesElement() {
        // TODO fix this
        return new SlackElement(SlackTextType.MRKDWN, "*2 projects*");
    }

    /* --- Getters / Setters --- */

    public SlackTextType getType() {
        return type;
    }

    public void setType(SlackTextType type) {
        this.type = type;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    public String getAlt_text() {
        return alt_text;
    }

    public void setAlt_text(String alt_text) {
        this.alt_text = alt_text;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
