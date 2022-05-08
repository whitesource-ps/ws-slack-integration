package com.ws.api;

import com.google.gson.Gson;
import com.ws.api.exception.ApiException;
import com.ws.api.model.ApiRequestType;
import com.ws.api.model.Environment;
import com.ws.api.request.*;
import com.ws.api.response.*;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

public class ApiRequestFactory {

    /* --- Static members --- */

    public static final String SAAS_URL = "https://saas.whitesourcesoftware.com";
    public static final String APP_URL = "https://app.whitesourcesoftware.com";
    public static final String APP_EU_URL = "https://app-eu.whitesourcesoftware.com";

    public static final String API_SUFFIX_1_3 = "/api/v1.3";
    public static final String API_SUFFIX = "/api";
    public static final String INTERNAL = "/internal/v1.0";
    public static final String APPLICATION_JSON = "application/json";
    public static final String CONTENT_TYPE = "Content-Type";
    public static final String UTF_8 = "UTF-8";
    public static final String LICENSES_ZIP_FILE = "licenses.zip";
    public static final String FILE_SEPARATOR = "file.separator";

    private static Map<Environment, String> urls;

    static {
        urls = new HashMap<>();
        urls.put(Environment.SAAS, SAAS_URL);
        urls.put(Environment.APP, APP_URL);
        urls.put(Environment.APP_EU, APP_EU_URL);
    }

    /* --- Public methods --- */


    public static GetOrgAlertsByTypeResponse getOrgAlertsByType(String orgToken, String url, String fromDate) throws IOException, ApiException {
        return (GetOrgAlertsByTypeResponse) sendApiRequest(new GetOrgAlertsByTypeRequest(orgToken, fromDate), url + API_SUFFIX, null,
                false, true);
    }

    public static GetProductAlertsByTypeResponse getProductAlertsByType(String productToken, String url, String fromDate) throws IOException, ApiException {
        return (GetProductAlertsByTypeResponse) sendApiRequest(new GetProductAlertsByTypeRequest(productToken, fromDate), url + API_SUFFIX, null,
                false, true);
    }

    public static GetProductTagsResponse getProductTags(String productToken, String url, String userKey) throws IOException, ApiException {
        return (GetProductTagsResponse) sendApiRequest(new GetProductTagsRequest(productToken, userKey), url + API_SUFFIX, null,
                false, true);
    }

    public static GetOrganizationDetailsResponse getOrganizationDetails(String orgToken, String url, String userKey) throws IOException, ApiException {
        return (GetOrganizationDetailsResponse) sendApiRequest(new GetOrganizationDetailsRequest(orgToken, userKey), url, null, false, true);
    }

    /* --- Private methods --- */

    private static BaseApiResponse sendApiRequest(BaseApiRequest request, String url, String directory, boolean internal,
                                                  boolean withTimeout) throws IOException, ApiException {
        if (!url.contains(API_SUFFIX)) {
            url = internal ? url + INTERNAL : url + API_SUFFIX_1_3;
        }

        CloseableHttpClient client;
        if (withTimeout) {
            int timeout = 20;
            RequestConfig config = RequestConfig.custom()
                    .setConnectTimeout(timeout * 1000)
                    .setConnectionRequestTimeout(timeout * 1000)
                    .setSocketTimeout(timeout * 1000).build();
            client = HttpClientBuilder.create().setDefaultRequestConfig(config).build();
        } else {
            client = HttpClients.createDefault();
        }
        HttpPost httpPost = new HttpPost(url);
        httpPost.setHeader(CONTENT_TYPE, APPLICATION_JSON);
        httpPost.setEntity(new StringEntity(new Gson().toJson(request)));
        System.out.println("Sending API request: " + request.getRequestType() + " to: " + url);
        CloseableHttpResponse response = client.execute(httpPost);
        BaseApiResponse apiResponse;

        // parse responses according to the request type

        if (request.getRequestType().equals(ApiRequestType.GET_PROJECT_ALERTS_BY_TYPE.toString())) {
            apiResponse = new Gson().fromJson(EntityUtils.toString(response.getEntity(), UTF_8), GetProjectAlertsByTypeResponse.class);
        } else if (request.getRequestType().equals(ApiRequestType.GET_PRODUCT_ALERTS_BY_TYPE.toString())) {
            apiResponse = new Gson().fromJson(EntityUtils.toString(response.getEntity(), UTF_8), GetProductAlertsByTypeResponse.class);
        } else if (request.getRequestType().equals(ApiRequestType.GET_PRODUCT_TAGS.toString())) {
            apiResponse = new Gson().fromJson(EntityUtils.toString(response.getEntity(), UTF_8), GetProductTagsResponse.class);
        }  else if (request.getRequestType().equals(ApiRequestType.GET_ORGANIZATION_DETAILS.toString())) {
            apiResponse = new Gson().fromJson(EntityUtils.toString(response.getEntity(), UTF_8), GetOrganizationDetailsResponse.class);
        } else {
            // json format
            apiResponse = new Gson().fromJson(EntityUtils.toString(response.getEntity(), UTF_8), BaseApiResponse.class);
        }
        verifyErrorResponse(apiResponse, request.getRequestType());

        client.close();
        return apiResponse;
    }

    private static void verifyErrorResponse(BaseApiResponse response, String requestType) throws ApiException {
        if (StringUtils.isNotBlank(response.getErrorCode())) {
            throw new ApiException("Failed to execute " + requestType + ". Got error code: "
                    + response.getErrorCode() + ", error message: " + response.getErrorMessage());
        }
    }
}
