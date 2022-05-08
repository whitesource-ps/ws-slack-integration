package com.spring.policy;

import java.util.Collection;
import java.util.LinkedList;

public class RejectedLibrary {

    /* --- Members --- */

    private String sha1;
    private String link;
    private String name;
    private Collection<String> projects;
    private String systemPath;
    private String manifestFile;

    /* --- Constructor --- */

    public RejectedLibrary() {
        projects = new LinkedList<>();
    }

    /* --- Getters / Setters --- */

    public String getSha1() {
        return sha1;
    }

    public void setSha1(String sha1) {
        this.sha1 = sha1;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Collection<String> getProjects() {
        return projects;
    }

    public void setProjects(Collection<String> projects) {
        this.projects = projects;
    }
}
