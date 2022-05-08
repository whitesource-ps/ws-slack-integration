package com.ws.api.response;

import java.io.Serializable;

public class GetCopyrightsFileResponse extends BaseApiResponse implements Serializable {

    private String content;

    public GetCopyrightsFileResponse(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
