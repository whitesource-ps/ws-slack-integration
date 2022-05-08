package com.ws.api.model;

import java.io.Serializable;
import java.util.Collection;
import java.util.LinkedList;
import java.util.Objects;

public class LibraryInfo implements Serializable {

    /* --- Members --- */

    private String keyUuid;
    private String keyId;
    private String name;
    private String artifactId;
    private String groupId;
    private String type;
    private String filename;
    private String sha1;
    private String version;
    private LibraryReferencesInfo references;
    private Collection<LicenseInfo> licenses;
    private Integer projectId;

    /* --- Constructors --- */

    public LibraryInfo() {
        references = new LibraryReferencesInfo();
        licenses = new LinkedList<>();
    }

    public LibraryInfo(String keyUuid, String keyId, String name, String artifactId, String groupId,
                       String type, String sha1, String version, LibraryReferencesInfo references, Collection<LicenseInfo> licenses) {
        this();
        this.keyUuid = keyUuid;
        this.keyId = keyId;
        this.name = name;
        this.artifactId = artifactId;
        this.groupId = groupId;
        this.type = type;
        this.sha1 = sha1;
        this.version = version;
        this.references = references;
        this.licenses = licenses;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LibraryInfo that = (LibraryInfo) o;
        return Objects.equals(keyUuid, that.keyUuid);
    }

    @Override
    public int hashCode() {
        return Objects.hash(keyUuid);
    }

    /* --- Getters / Setters --- */

    public String getKeyUuid() {
        return keyUuid;
    }

    public void setKeyUuid(String keyUuid) {
        this.keyUuid = keyUuid;
    }

    public String getKeyId() {
        return keyId;
    }

    public void setKeyId(String keyId) {
        this.keyId = keyId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getArtifactId() {
        return artifactId;
    }

    public void setArtifactId(String artifactId) {
        this.artifactId = artifactId;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSha1() {
        return sha1;
    }

    public void setSha1(String sha1) {
        this.sha1 = sha1;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public LibraryReferencesInfo getReferences() {
        return references;
    }

    public void setReferences(LibraryReferencesInfo references) {
        this.references = references;
    }

    public Collection<LicenseInfo> getLicenses() {
        return licenses;
    }

    public void setLicenses(Collection<LicenseInfo> licenses) {
        this.licenses = licenses;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public Integer getProjectId() {
        return projectId;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }
}
