package com.spring.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.spring.response.SlackResponse;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.io.IOException;

public class HttpUtils {

    /* --- Static members --- */

    public static final String APPLICATION_JSON = "application/json";
    public static final String CONTENT_TYPE = "Content-Type";

    /* --- Public methods --- */

    public static void sendHTTPRequest(SlackResponse response, String url) throws IOException {
        CloseableHttpClient client = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost(url);
        httpPost.setHeader(CONTENT_TYPE, APPLICATION_JSON);
        Gson gson = new GsonBuilder().disableHtmlEscaping().create();
        httpPost.setEntity(new StringEntity(gson.toJson(response)));
        System.out.println("Sending request to: " + url);
        CloseableHttpResponse httpResponse = client.execute(httpPost);
        if (httpResponse != null) {
            int statusCode = httpResponse.getStatusLine().getStatusCode();
            if (statusCode == 200) {
                System.out.println("Successfully sent response to Slack");
            } else {
                System.out.println("Error sending to Slack, got HTTP response code " + statusCode + ", cause: " + httpResponse.getStatusLine().getReasonPhrase());
            }
        }
    }
}
